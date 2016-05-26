package org.openurp.edu.base.web.action

import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.{ Direction, Major }
import org.beangle.commons.collection.Order
import org.beangle.webmvc.api.view.View
import org.openurp.edu.base.model.DirectionJournal

@action("{project}/direction")
class DirectionAction extends ProjectRestfulAction[Direction] {

  override def indexSetting() = {
    put("majors", findItems(classOf[Major]))
  }
  override def editSetting(entity: Direction) = {

    val majors = findItemsByProject(classOf[Major])
    put("majors", majors)

    super.editSetting(entity)
  }

  protected override def saveAndRedirect(entity: Direction): View = {
    entity.project = currentProject

    if (!entity.persisted) {
      val major = entityDao.get(classOf[Major], entity.major.id)
      val departs = major.journals.map(j => j.depart).toSet
      val educations = major.journals.map(j => j.education).toSet
      if (departs.size == 1 && educations.size == 1) {
        val dj = new DirectionJournal
        dj.direction = entity
        dj.depart = departs.head
        dj.education = educations.head
        dj.beginOn = new java.sql.Date(System.currentTimeMillis())
        entity.journals += dj
      }
    }
    super.saveAndRedirect(entity)
  }

}

