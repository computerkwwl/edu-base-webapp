package org.openurp.edu.base.web.action

import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.openurp.edu.base.model.{ Direction, Major, MajorDiscipline, Project }
import org.beangle.webmvc.entity.action.RestfulAction

@action("{project}/major")
class MajorAction extends ProjectRestfulAction[Major] {
  override def editSetting(entity: Major) = {

    val projects = findItems(classOf[Project])
    put("projects", projects)

    val disciplines = findItems(classOf[MajorDiscipline])
    put("disciplines", disciplines)

    if (null == entity.project) {
      entity.project = currentProject
    }

    super.editSetting(entity)
  }

  //  protected override def saveAndRedirect(entity: Major): View = {
  //    
  //    val major = entity.asInstanceOf[MajorBean]
  //  
  //    major.journals.clear()
  //    val journalsIds = getAll("journalsId2nd", classOf[Integer])
  //    major.journals ++= entityDao.find(classOf[MajorJournal], journalsIds)
  //    
  //    major.educations.clear()
  //    val educationsIds = getAll("educationsId2nd", classOf[Integer])
  //    major.educations ++= entityDao.find(classOf[Education], educationsIds)
  //    
  //    major.directions.clear()
  //    val directionsIds = getAll("directionsId2nd", classOf[Integer])
  //    major.directions ++= entityDao.find(classOf[Direction], directionsIds)
  //    
  //    super.saveAndRedirect(entity)
  //  }

}

