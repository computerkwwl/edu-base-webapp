package org.openurp.edu.base.web.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.Classroom
import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.openurp.code.edu.model.ClassroomType
import org.openurp.base.model.Room
import org.openurp.base.model.School
import scala.collection.mutable.Buffer
import org.beangle.webmvc.api.annotation.action
import org.openurp.base.model.Campus
import org.beangle.webmvc.api.view.View

@action("{project}/classroom")
class ClassroomAction extends ProjectRestfulAction[Classroom] {

  protected override def indexSetting(): Unit = {
    val roomTypes = findItems(classOf[ClassroomType])
    put("roomTypes", roomTypes)
  }

  override protected def saveAndRedirect(entity: Classroom): View = {
    entity.updatedAt = new java.util.Date();
    entity.beginOn = new java.sql.Date(System.currentTimeMillis);
    super.saveAndRedirect(entity)
  }

  override def editSetting(entity: Classroom) = {

    if (null == entity.project) {
      entity.project = currentProject
    }
    val roomTypes = findItems(classOf[ClassroomType])
    put("roomTypes", roomTypes)

    val campuses = findItemsBySchool(classOf[Campus])
    put("campuses", campuses)

    val rooms = findItemsBySchool(classOf[Room])
    put("rooms", rooms)
  }

}