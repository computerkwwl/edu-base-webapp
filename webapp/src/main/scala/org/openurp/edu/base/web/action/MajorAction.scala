package org.openurp.edu.base.web.action

import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.Major
import org.openurp.edu.base.model.Project
import org.beangle.data.dao.OqlBuilder
import org.openurp.code.edu.model.DisciplineCategory
import org.openurp.edu.base.model.Direction
import org.openurp.edu.base.model.MajorDiscipline

class MajorAction extends ProjectRestfulAction[Major] {
  override def editSetting(entity: Major) = {

    val projects = findItems(classOf[Project])
    put("projects", projects)

    val disciplines = findItems(classOf[MajorDiscipline])
    put("disciplines", disciplines)

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    if (null == entity.project) {
      entity.project = cuurentProject
    }

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    //    query.orderBy("name")
    val items = entityDao.search(query)
    items
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

