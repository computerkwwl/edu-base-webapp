package org.openurp.edu.base.web.action

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.{ ClassLoaders, Strings }
import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.data.transfer.listener.ForeignerListener
import org.beangle.webmvc.api.annotation.{ action, mapping, param }
import org.beangle.webmvc.api.context.ActionContext
import org.beangle.webmvc.api.view.{ Status, Stream, View }
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.{ Education, StdType }
import org.openurp.edu.base.model.{ Adminclass, Direction, Instructor, Major, Student, StudentState, Teacher }
import net.sf.jxls.transformer.XLSTransformer
import org.beangle.data.transfer.TransferListener

@action("{project}/adminclass")
class AdminclassAction extends RestfulAction[Adminclass] with ImportDataSupport[Adminclass] {
  override def editSetting(entity: Adminclass) = {
    val departments = findItems(classOf[Department])
    put("departments", departments)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    val stdTypes = findItems(classOf[StdType])
    put("stdTypes", stdTypes)

    val instructors = findItems(classOf[Instructor])
    put("instructors", instructors)

    val tutors = findItems(classOf[Teacher])
    put("tutors", tutors)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    //    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

  /**
   * 查看班级信息
   * @return @
   */

  @mapping(value = "{id}")
  override def info(@param("id") id: String): String = {
    val builder = OqlBuilder.from(classOf[StudentState], "studentState")
    builder.where("studentState.adminclass.id=:id", id.toLong)
    val studentStates = entityDao.search(builder)
    val students = Collections.newBuffer[Student]
    studentStates.foreach { studentState => students += studentState.std }
    val status = Collections.newMap[String, StudentState]
    studentStates.foreach { studentState => status.put(studentState.std.code, studentState) }
    put("students", students)
    put("status", status)
    super.info(id)
  }
  /**
   * 导出
   */
  def export(): View = {
    val list = collection.JavaConversions.asJavaCollection(entityDao.search(getQueryBuilder().limit(null)))
    //查出信息并放到map中
    val beans = new java.util.HashMap[String, Any]
    beans.put("list", list)
    //获得模板路径
    val path = ClassLoaders.getResourceAsStream("template/adminclass.xls")
    //准备输出流
    val response = ActionContext.current.response
    response.setContentType("application/x-excel")
    response.setHeader("Content-Disposition", "attachmentfilename=adminclass.xls")
    val os = response.getOutputStream()
    val transformer = new XLSTransformer()
    try {
      //将beans通过模板输入流写到workbook中
      val workbook = transformer.transformXLS(path, beans)
      //将workbook中的内容用输出流写出去
      workbook.write(os)
    } finally {
      if (os != null) {
        os.close()
      }
    }
    Status.Ok
  }
  /**
   * 下载模板
   */
  def downloadAdminclassStdTemp: View = {
    Stream(ClassLoaders.getResourceAsStream("template/adminclass.xls"), "application/vnd.ms-excel", "班级信息.xls")
  }

  /**
   * 维护班级学生
   */

  def setClassStudentForm(): String = {
    val adminclassId = longId("adminclass")
    val adminclass = entityDao.get(classOf[Adminclass], adminclassId)
    put("adminclass", adminclass)
    val builder = OqlBuilder.from[Student](classOf[StudentState].getName, "studentState")
    builder.select("studentState.std")
    builder.where("studentState.adminclass=:adminClass", adminclass)
    put("students", entityDao.search(builder))
    forward()
  }

  /**
   * 保存设置的班级学生
   *
   */
  def saveClassStudent(): View = {
    val adminclassId = longId("adminclass")
    val adminclasses = entityDao.find(classOf[Adminclass], adminclassId)
    val adminclassCur = adminclasses.head
    // 移除学生
    val removeIds = longIds("studentRemoveIds")
    val students = entityDao.find(classOf[Student], removeIds)
    val studentStates = Collections.newBuffer[StudentState]
    val ssBuilder = OqlBuilder.from(classOf[StudentState], "studentState")
    students.foreach { student =>
      ssBuilder.where("studentState.std=student", student)
      studentStates ++= entityDao.search(ssBuilder)
      studentStates.foreach { studentState =>
        val adminclass = studentState.adminclass
        studentState.adminclass = null
        //        entityDao.saveOrUpdate(studentState)
        entityDao.refresh(adminclass)
        //        entityDao.saveOrUpdate(adminclass)
      }
    }
    // 保存现在班级学生列表中可以看的到的所有学生
    val stdIds = longIds("student")
    if (!stdIds.isEmpty) {
      val students = entityDao.find(classOf[Student], stdIds)
      val msg = new StringBuffer()
      var errorNum = 0
      val studentStates = Collections.newBuffer[StudentState]
      val ssBuilder = OqlBuilder.from(classOf[StudentState], "studentState")
      students.foreach { student =>
        ssBuilder.where("studentState.std=student", student)
        studentStates ++= entityDao.search(ssBuilder)
        studentStates.foreach { studentState =>
          // 比较所关联学生与所关联 到的班级，若发现有一名学生的专业或者方向与班级中的专业或者方向不匹配，则不允许关联
          if (!studentState.major.equals(adminclassCur.major) || studentState.direction != adminclassCur.direction) {
            msg.append("\n失败：").append(studentState.std.code).append(" ").append(studentState.std.person.name)
            errorNum = errorNum + 1
          } else {
            val oriAdminclass = studentState.adminclass
            studentState.adminclass = adminclassCur
            entityDao.saveOrUpdate(studentState)
            if (oriAdminclass != null) {
              entityDao.refresh(oriAdminclass)
              entityDao.saveOrUpdate(oriAdminclass)
            }
          }
        }
        if (errorNum > 0) {
          addFlashMessage("\n不符合要求的学生 " + errorNum + "；分别是：" + msg)
        }
      }
      entityDao.refresh(adminclassCur)
      entityDao.saveOrUpdate(adminclassCur)
    }
    redirect("setClassStudentForm", "&adminclassId=" + adminclassId, "info.save.success")
  }

  protected override def importerListeners: List[_ <: TransferListener] = {
    List(new ForeignerListener(entityDao), new AdminclassImportListener(entityDao))
  }

  /**
   * 根据条件查询不是现在班级内的学生的其他学生信息
   *
   * @return
   */
  def addClassStudentList(): String = {
    val builder = OqlBuilder.from(classOf[StudentState], "ss")
    val studentState = populate(classOf[StudentState], "studentState")
    val adminclassId = longId("adminclass")
    val adminclass = entityDao.get(classOf[Adminclass], adminclassId)
    // 第一次进入查询学生页面
    if (getBoolean("first") != null && getBoolean("first", true)) {
      //      studentState.grade = adminclass.grade
      //      studentState.department = adminclass.department
      //      studentState.major = adminclass.major
      //      studentState.adminclass = adminclass
      builder.where("ss.grade = :grade", adminclass.grade)
      builder.where("ss.department.name = :department", adminclass.department.name)
      //      builder.where("ss.major.name = :major", adminclass.major.name)
    } else {
      // 根据输入的条件
      if (studentState.std.code != null)
        builder.where("ss.std.code like :code", "%" + studentState.std.code + "%")
      if (studentState.std.person.name != null)
        builder.where("ss.std.person.name like :name", "%" + studentState.std.person.name.formatedName + "%")
      if (studentState.grade != null)
        builder.where("ss.grade like :grade", "%" + studentState.grade + "%")
      if (studentState.department != null && studentState.department.name != null)
        builder.where("ss.department.name like :department", "%" + studentState.department.name + "%")
      if (studentState.major != null && studentState.major.name != null)
        builder.where("ss.major.name like :major", "%" + studentState.major.name + "%")
      if (studentState.adminclass != null && studentState.adminclass.name != null)
        builder.where("ss.adminclass.name like :adminclass", "%" + studentState.adminclass.name + "%")
    }
    // 不是本班级的学生
    builder.where("ss.adminclass is null or ss.adminclass.id <> :adminClass", longId("adminclass"))
    builder.where("ss.department in (:departments)", getDeparts())
    //    builder.where("ss.education in (:educations)", getEducations())
    builder.orderBy("ss.std.code desc")
    builder.limit(getPageLimit)
    val studentStates = entityDao.search(builder)
    put("studentStates", studentStates)
    put("adminclass", adminclass)
    put("adminclassId", adminclassId)
    put("student", studentStates.head.std)
    put("stdCodes", get("stdCodes")) // 已经输入的学生
    return forward()
  }

  def getDeparts(): Seq[Department] = {
    val builder = OqlBuilder.from(classOf[Department])
    builder.orderBy("code")
    entityDao.search(builder)
  }

  def getEducations(): Seq[Education] = {
    val builder = OqlBuilder.from(classOf[Education])
    builder.orderBy("code")
    entityDao.search(builder)
  }

  /**
   * 根据学号或名称查询学生
   *
   * @return
   */
  def addClassStudent(): String = {
    var codes = get("stdCodes").orNull
    if (Strings.isNotEmpty(codes)) {
      codes = codes.replaceAll("[\\s，；]", ",").replaceAll(",,", ",")
      val projectId = getInt("student.project.id").get
      val studentList = Collections.newBuffer[Student]
      val notAddCodes = Collections.newBuffer[String]

      val codeArr = Strings.split(codes)
      val codeIterater = codeArr.iterator
      while (codeIterater.hasNext) {
        val students = entityDao.findBy(classOf[Student], "code", List(codeIterater.next()))
        var b = false // 是否存在可以使用的学号
        if (!students.isEmpty) {
          val std = students.head
          if (std.project.id.equals(projectId) && !studentList.contains(std)) {
            studentList += std
            b = true
          }
        } else {
          val students1 = entityDao.search(OqlBuilder.from(classOf[Student], "c").where("c.person.name.formatedName like :name", "%" + codeIterater.next().trim() + "%"))
          students1.foreach { std =>
            if (std.project.id.equals(projectId) && !studentList.contains(std)) {
              studentList += std
              b = true
            }
          }
        }
        if (!b) {
          notAddCodes += codeIterater.next()
        }
      }
      put("studentList", studentList)
      put("notAddCodes", notAddCodes)
    }
    return forward()
  }

}

