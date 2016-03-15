package org.openurp.edu.base.web.action

import org.beangle.data.model.Entity
import org.beangle.webmvc.api.view.View
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.Project
import org.openurp.base.model.TimeSetting
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.StdType
import org.openurp.base.model.Campus
import org.openurp.base.model.School
import org.beangle.data.dao.OqlBuilder
import org.openurp.edu.base.code.model.StdLabel
import org.openurp.edu.base.code.model.Education
import org.openurp.base.model.Calendar

class ProjectAction extends RestfulAction[Project] {
  override def editSetting(entity: Project) = {
    val schools = findItems(classOf[School])
    put("schools", schools)

    val calendars = findItems(classOf[Calendar])
    put("calendars", calendars)

    val campuses = findItems(classOf[Campus])
    put("campuses", campuses)

    val departments = findItems(classOf[Department])
    put("departments", departments)

    val educations = findItems(classOf[Education])
    put("educations", educations)

    val labels = findItems(classOf[StdLabel])
    put("labels", labels)

    val types = findItems(classOf[StdType])
    put("types", types)

    val timeSettings = findItems(classOf[TimeSetting])
    put("timeSettings", timeSettings)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }
  protected override def saveAndRedirect(entity: Project): View = {
    val project = entity.asInstanceOf[Project]

    project.campuses.clear()
    val campusIds = ids("campusesId2nd", classOf[Int])
    project.campuses ++= entityDao.find(classOf[Campus], campusIds)

    project.departments.clear()
    val departmentIds = ids("departmentsId2nd", classOf[Int])
    project.departments ++= entityDao.find(classOf[Department], departmentIds)

    project.educations.clear()
    val educationIds = ids("educationsId2nd", classOf[Int])
    project.educations ++= entityDao.find(classOf[Education], educationIds)

    project.stdLabels.clear()
    val labelsIds = ids("labelsId2nd", classOf[Int])
    project.stdLabels ++= entityDao.find(classOf[StdLabel], labelsIds)

    project.timeSettings.clear()
    val timeSettingsIds = ids("timeSettingsId2nd", classOf[Int])
    project.timeSettings ++= entityDao.find(classOf[TimeSetting], timeSettingsIds)

    super.saveAndRedirect(entity)
  }

}

