package org.openurp.edu.base.web.action

import org.beangle.commons.collection.Collections
import org.beangle.data.dao.OqlBuilder
import org.beangle.commons.lang.{ ClassLoaders, Strings }
import org.beangle.data.transfer.TransferListener
import org.beangle.data.transfer.listener.ForeignerListener
import org.beangle.webmvc.api.annotation.{ action, mapping, param }
import org.beangle.webmvc.api.context.ActionContext
import org.beangle.webmvc.api.view.{ Status, Stream, View }
import org.openurp.base.model.{ Campus, Department }
import org.openurp.edu.base.code.model.{ Education, StdType }
import org.openurp.edu.base.model.{ Adminclass, Direction, Instructor, Major, Student, StudentState, Teacher }
import org.openurp.edu.base.web.action.helper.QueryHelper

import net.sf.jxls.transformer.XLSTransformer

@action("{project}/adminclass")
class AdminclassAction extends ProjectRestfulAction[Adminclass] with ImportDataSupport[Adminclass] {

  protected override def indexSetting(): Unit = {
    put("educations", findItems(classOf[Education]))
    put("departments", findItemsBySchool(classOf[Department]))
    put("campuses", findItemsBySchool(classOf[Campus]))
    println(this)
  }

  override def getQueryBuilder(): OqlBuilder[Adminclass] = {
    QueryHelper.addTemporalOn(super.getQueryBuilder(), getBoolean("active"))
  }

  override def editSetting(entity: Adminclass) = {
    put("educations", findItems(classOf[Education]))
    put("departments", findItemsBySchool(classOf[Department]))
    put("campuses", findItemsBySchool(classOf[Campus]))

    val majors = findItemsByProject(classOf[Major])
    put("majors", majors)

    val directions = findItemsByProject(classOf[Direction])
    put("directions", directions)

    val stdTypes = findItems(classOf[StdType])
    put("stdTypes", stdTypes)

    val instructors = findItemsByProject(classOf[Instructor], "user.name")
    put("instructors", instructors)

    val tutors = findItemsByProject(classOf[Teacher])
    put("tutors", tutors)

    super.editSetting(entity)
  }

  /**
   * 查看班级信息
   * @return @
   */
  @mapping(value = "{id}")
  override def info(@param("id") id: String): View = {
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
    val list = collection.JavaConverters.asJavaCollection(entityDao.search(getQueryBuilder().limit(null)))
    //查出信息并放到map中
    val beans = new java.util.HashMap[String, Any]
    beans.put("list", list)
    //获得模板路径
    val path = ClassLoaders.getResourceAsStream("template/adminclass.xls").get
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
    Stream(ClassLoaders.getResourceAsStream("template/adminclass.xls").get, "application/vnd.ms-excel", "班级信息.xls")
  }

  /**
   * 维护班级学生
   */

  def setClassStudentForm(): View = {
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
            studentState.adminclass = Some(adminclassCur)
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
}

