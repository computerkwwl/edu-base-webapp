package org.openurp.edu.base.web.action

import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.api.view.View
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.{ CourseCategory, CourseType, Education, ExamMode, ScoreMarkStyle }
import org.openurp.edu.base.model.{ Course, Major }

@action("{project}/course")
class CourseAction extends ProjectRestfulAction[Course] {

  protected override def indexSetting(): Unit = {
    val courseTypes = findItems(classOf[CourseType])
    put("courseTypes", courseTypes)

    val departments = findItems(classOf[Department])
    put("departments", departments)
  }

  override def editSetting(entity: Course) = {
    val courseTypes = findItems(classOf[CourseType])
    put("courseTypes", courseTypes)

    val examModes = findItems(classOf[ExamMode])
    put("examModes", examModes)

    val markStyles = findItems(classOf[ScoreMarkStyle])
    put("markStyles", markStyles)

    val departments = findItems(classOf[Department])
    put("departments", departments)

    var educations = findItems(classOf[Education]).toBuffer
    educations --= entity.educations
    put("educations", educations)

    val categories = findItems(classOf[CourseCategory])
    put("categories", categories)

    var majors = findItems(classOf[Major]).toBuffer
    majors --= entity.majors
    put("majors", majors)

    var xmajors = findItems(classOf[Major]).toBuffer
    xmajors --= entity.xmajors
    put("xmajors", xmajors)

    val prerequisites = findItems(classOf[Course])
    put("prerequisites", prerequisites)

    if (null == entity.project) {
      entity.project = cuurentProject
    }
    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }
  protected override def saveAndRedirect(entity: Course): View = {
    val course = entity.asInstanceOf[Course]

    course.majors.clear()
    val majorIds = getAll("majorsId2nd", classOf[Long])
    course.majors ++= entityDao.find(classOf[Major], majorIds)

    course.xmajors.clear()
    val xmajorIds = getAll("xmajorsId2nd", classOf[Long])
    course.xmajors ++= entityDao.find(classOf[Major], xmajorIds)

    course.educations.clear()
    val educationIds = getAll("educationId2nd", classOf[Int])
    course.educations ++= entityDao.find(classOf[Education], educationIds)

    super.saveAndRedirect(entity)
  }

}

