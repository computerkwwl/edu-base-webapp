package org.openurp.edu.base.web.action

import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.code.model.CourseCategory
import org.openurp.edu.base.model.Major
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.code.model.ExamMode
import org.openurp.edu.base.model.Course
import org.beangle.data.dao.OqlBuilder
import org.openurp.edu.base.code.model.Education
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.view.View

class CourseAction extends RestfulAction[Course] {
  override def editSetting(entity: Course) = {
    val courseTypes = findItems(classOf[CourseType])
    put("courseTypes", courseTypes)

    val examModes = findItems(classOf[ExamMode])
    put("examModes", examModes)

    val markStyles = findItems(classOf[ScoreMarkStyle])
    put("markStyles", markStyles)

    val departments = findItems(classOf[Department])
    put("departments", departments)

    val educations = findItems(classOf[Education])
    put("educations", educations)

    val categories = findItems(classOf[CourseCategory])
    put("categories", categories)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val xmajors = findItems(classOf[Major])
    put("xmajors", xmajors)

    val prerequisites = findItems(classOf[Course])
    put("prerequisites", prerequisites)

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
    //    val majorIds = getAll("majorsId2nd", classOf[JLong])
    val majorIds = longIds("majorsId2nd")
    course.majors ++= entityDao.find(classOf[Major], majorIds)

    course.xmajors.clear()
    val xmajorIds = longIds("xmajorsId2nd")
    course.xmajors ++= entityDao.find(classOf[Major], xmajorIds)

    //    course.prerequisites.clear()
    //    val prerequisityIds = getAll("prerequisitesId2nd", classOf[java.lang.Long])
    //    course.prerequisites ++= entityDao.find(classOf[Course], prerequisityIds)

    super.saveAndRedirect(entity)
  }

}

