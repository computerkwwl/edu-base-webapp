package org.openurp.edu.base.web.action

import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.beangle.webmvc.api.view.View
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.{ Calendar, Campus, Department, School, TimeSetting }
import org.openurp.edu.base.code.model.{ Education, StdLabel, StdType }
import org.openurp.edu.base.model.Project
import scala.collection.mutable.Buffer

class ProjectAction extends RestfulAction[Project] {
  override def editSetting(entity: Project) = {

    val school = entity.school

    val schools = findItems(classOf[School])
    put("schools", schools)

    val calendars = findItemsBySchool(classOf[Calendar], school)
    put("calendars", calendars -- entity.calendars)

    val campuses = findItemsBySchool(classOf[Campus], school)
    put("campuses", campuses -- entity.campuses)

    val departments = findItemsBySchool(classOf[Department], school)
    put("departments", departments -- entity.departments)

    val educations = findItems(classOf[Education])
    put("educations", educations -- entity.educations)

    val labels = findItems(classOf[StdLabel])
    put("labels", labels -- entity.stdLabels)

    val types = findItems(classOf[StdType])
    put("types", types -- entity.stdTypes)

    val timeSettings = findItemsBySchool(classOf[TimeSetting], school)
    put("timeSettings", timeSettings -- entity.timeSettings)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Buffer[T] = {
    val query = OqlBuilder.from(clazz)
    entityDao.search(query).toBuffer
  }

  private def findItemsBySchool[T <: Entity[_]](clazz: Class[T], school: School): Buffer[T] = {
    val query = OqlBuilder.from(clazz, "aa")
    query.where("aa.school=:school", school)
    entityDao.search(query).toBuffer
  }

  private def findItemsByProject[T <: Entity[_]](clazz: Class[T], project: Project): Buffer[T] = {
    val query = OqlBuilder.from(clazz, "aa")
    query.where("aa.school=:project", project)
    entityDao.search(query).toBuffer
  }

  protected override def saveAndRedirect(entity: Project): View = {

    entity.campuses.clear()
    val campusIds = getAll("campusesId2nd", classOf[Int])
    entity.campuses ++= entityDao.find(classOf[Campus], campusIds)

    entity.departments.clear()
    val departmentIds = getAll("departmentsId2nd", classOf[Int])
    entity.departments ++= entityDao.find(classOf[Department], departmentIds)

    entity.educations.clear()
    val educationIds = getAll("educationsId2nd", classOf[Int])
    entity.educations ++= entityDao.find(classOf[Education], educationIds)

    entity.stdLabels.clear()
    val labelsIds = getAll("labelsId2nd", classOf[Int])
    entity.stdLabels ++= entityDao.find(classOf[StdLabel], labelsIds)

    entity.stdTypes.clear()
    val typesIds = getAll("typesId2nd", classOf[Int])
    entity.stdTypes ++= entityDao.find(classOf[StdType], typesIds)

    entity.calendars.clear()
    val calendarIds = getAll("calendarId2nd", classOf[Int])
    entity.calendars ++= entityDao.find(classOf[Calendar], calendarIds)

    entity.timeSettings.clear()
    val timeSettingsIds = getAll("timeSettingsId2nd", classOf[Int])
    entity.timeSettings ++= entityDao.find(classOf[TimeSetting], timeSettingsIds)

    super.saveAndRedirect(entity)
  }

}

