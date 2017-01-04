package org.openurp.edu.base.web.action

import scala.collection.mutable.Buffer

import org.beangle.commons.collection.Order
import org.beangle.commons.lang.Strings
import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.beangle.webmvc.api.annotation.{ action, mapping }
import org.beangle.webmvc.api.view.View
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.{ Campus, Department }
import org.openurp.code.edu.model.StudyType
import org.openurp.code.person.model.{ Gender, Nation }
import org.openurp.edu.base.code.model.{ StdLabel, StdType }
import org.openurp.edu.base.model.{ Adminclass, Direction, Major, Student, StudentState, Teacher }

@action("{project}/student")
class StudentAction extends RestfulAction[Student] {

  override protected def indexSetting(): Unit = {

    val nations = findItems(classOf[Nation])
    put("nations", nations)

    val genders = findItems(classOf[Gender])
    put("genders", genders)

    val labels = findItems(classOf[StdLabel])
    put("labels", labels)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val states = findItems(classOf[StudentState])
    put("states", states)

    val majorDeparts = findItems(classOf[Department])
    put("majorDeparts", majorDeparts)
    super.indexSetting()
  }

  protected override def getQueryBuilder(): OqlBuilder[Student] = {
    val builder: OqlBuilder[Student] = OqlBuilder.from(classOf[Student], simpleEntityName)
    populateConditions(builder)
    get("stdLabelId") match {
      case Some(labelId) =>
        if (Strings.isNotEmpty(labelId)) {
          builder.join("student.labels", "label")
          builder.where("label.id=:labelId", Integer.valueOf(labelId))
        }
      case None =>
    }

    builder.orderBy(get(Order.OrderStr).orNull).limit(getPageLimit)
  }

  override def editSetting(entity: Student) = {
    val departments = findItems(classOf[Department])
    put("departments", departments)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    val genders = findItems(classOf[Gender])
    put("genders", genders)

    val majorDeparts = findItems(classOf[Department])
    put("majorDeparts", majorDeparts)

    val type1s = findItems(classOf[StdType])
    put("type1s", type1s)

    val campuse = findItems(classOf[Campus])
    put("campuse", campuse)

    val adminclasses = findItems(classOf[Adminclass])
    put("adminclasses", adminclasses)

    val studyTypes = findItems(classOf[StudyType])
    put("studyTypes", studyTypes)

    val tutors = findItems(classOf[Teacher])
    put("tutors", tutors)

    val labels = findItems(classOf[StdLabel])
    labels.asInstanceOf[Buffer[StdLabel]] --= entity.asInstanceOf[Student].labels.values
    entity.asInstanceOf[Student].labels.keys
    put("labels", labels)

    //    val people= findItems(classOf[Person])
    //    put("people",people)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    //    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

  protected override def saveAndRedirect(entity: Student): View = {
    val student = entity.asInstanceOf[Student]

    student.labels.clear()
    val labelsIds = ids("labelsId2nd", classOf[Int])
    import java.{ util => ju, io => jo }
    entityDao.find(classOf[StdLabel], labelsIds) foreach { label =>
      student.labels.put(label.labelType, label)
    }
    super.saveAndRedirect(entity)
  }

  @mapping(value = "batchUpdateLabel", method = "put")
  def batchUpdateLabel(): String = {
    //    val entityId = getLongId(shortName)
    //    val students: Seq[Student] =
    //      if (null == entityId) getModels(entityName, getLongIds(shortName))
    //      else List(getModel[Student](entityName, entityId))
    put("students", getModels[Student](entityName, longIds(simpleEntityName)))
    put("labels", findItems(classOf[StdLabel]))
    forward()
  }

  @mapping(value = "saveBatchUpdateLabel", method = "put")
  def saveBatchUpdateLabel(): View = {
    val idclass = entityMetaData.getType(entityName).get.idType
    val entityId = getId(simpleEntityName, idclass)
    val students: Seq[Student] =
      if (null == entityId) getModels(entityName, ids(simpleEntityName, idclass))
      else List(getModel[Student](entityName, entityId))

    val addLabelsId2nd = ids("addLabelsId2nd", classOf[Int])
    val addLabels = entityDao.find(classOf[StdLabel], addLabelsId2nd)
    students.foreach(student => {
      addLabels foreach { label =>
        student.asInstanceOf[Student].labels.put(label.labelType, label)
      }
    })
    val removeLabelsId2nd = ids("removeLabelsId2nd", classOf[Int])
    val removeLabels = entityDao.find(classOf[StdLabel], removeLabelsId2nd)
    students foreach (student => {
      removeLabels foreach { label =>
        student.asInstanceOf[Student].labels.remove(label.labelType)
      }
    })
    entityDao.saveOrUpdate(students)
    redirect("search", "info.save.success")
  }

  def batchInputLabel(): String = {
    forward()
  }

  def searchStd(): String = {
    val stds =
      get("codes") match {
        case Some(codes) => entityDao.findBy(classOf[Student], "code", Strings.split(codes))
        case None => List.empty
      }
    put("students", stds)
    put("labels", findItems(classOf[StdLabel]))
    return "batchUpdateLabel"
  }
}

