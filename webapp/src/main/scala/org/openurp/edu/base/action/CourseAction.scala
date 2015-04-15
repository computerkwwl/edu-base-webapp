package org.openurp.edu.base.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.view.View
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.Department
import org.openurp.code.edu.Education
import org.openurp.edu.base.code.ScoreMarkStyle
import org.openurp.edu.base.model.CourseBean
import org.openurp.edu.base.code.CourseType
import org.openurp.edu.base.code.CourseCategory
import org.openurp.edu.base.Major
import org.openurp.edu.base.code.ExamMode
import org.openurp.edu.base.Course
import org.beangle.commons.lang.JLong

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

    val subcourses = findItems(classOf[Course])
    put("subcourses", subcourses)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }
  protected override def saveAndRedirect(entity: Course): View = {
    val course = entity.asInstanceOf[CourseBean]

    course.majors.clear()
    val majorIds = getAll("majorsId2nd", classOf[JLong])
    course.majors ++= entityDao.find(classOf[Major], majorIds)

    course.xmajors.clear()
    val xmajorIds = getAll("xmajorsId2nd", classOf[JLong])
    course.xmajors ++= entityDao.find(classOf[Major], xmajorIds)

    course.prerequisites.clear()
    val prerequisityIds = getAll("prerequisitesId2nd", classOf[java.lang.Long])
    course.prerequisites ++= entityDao.find(classOf[Course], prerequisityIds)

    super.saveAndRedirect(entity)
  }

}

