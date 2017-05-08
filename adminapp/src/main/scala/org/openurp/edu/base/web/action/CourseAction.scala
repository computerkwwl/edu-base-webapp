package org.openurp.edu.base.web.action

import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.api.view.View
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.{ CourseCategory, CourseType, Education, ExamMode, ScoreMarkStyle }
import org.openurp.edu.base.model.{ Course, Major }
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.web.action.assist.SearchQueryCollection

@action("{project}/course")
class CourseAction extends ProjectRestfulAction[Course] {

  protected override def indexSetting(): Unit = {
    val courseTypes = findItems(classOf[CourseType])
    put("courseTypes", courseTypes)

    val departments = findItemsBySchool(classOf[Department])
    put("departments", departments)
  }
  
  override def getQueryBuilder(): OqlBuilder[Course] = {
    SearchQueryCollection.addBeginOnQuery(super.getQueryBuilder(), getBoolean("active"))
  }

  override def editSetting(entity: Course) = {
    val courseTypes = findItems(classOf[CourseType])
    put("courseTypes", courseTypes)

    val examModes = findItems(classOf[ExamMode])
    put("examModes", examModes)

    val markStyles = findItems(classOf[ScoreMarkStyle])
    put("markStyles", markStyles)

    put("departments", findItemsBySchool(classOf[Department]))

    var educations = findItems(classOf[Education]).toBuffer
    educations --= entity.educations
    put("educations", educations)

    val categories = findItems(classOf[CourseCategory])
    put("categories", categories)

    var majors = findItemsByProject(classOf[Major]).toBuffer
    majors --= entity.majors
    put("majors", majors)

    var xmajors = findItemsByProject(classOf[Major]).toBuffer
    xmajors --= entity.xmajors
    put("xmajors", xmajors)

    if (null == entity.project) {
      entity.project = currentProject
    }
    super.editSetting(entity)
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

