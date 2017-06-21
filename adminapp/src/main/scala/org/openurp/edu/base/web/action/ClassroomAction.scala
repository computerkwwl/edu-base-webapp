package org.openurp.edu.base.web.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.Classroom
import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.openurp.code.edu.model.ClassroomType
import org.openurp.base.model.Room
import org.openurp.base.model.School
import scala.collection.mutable.Buffer
import org.beangle.webmvc.api.annotation.action
import org.openurp.base.model.Campus
import org.beangle.webmvc.api.view.View
import org.openurp.edu.base.web.action.helper.QueryHelper
import java.time.Instant
import java.time.LocalDate

@action("{project}/classroom")
class ClassroomAction extends ProjectRestfulAction[Classroom] {

  protected override def indexSetting(): Unit = {
    val roomTypes = findItems(classOf[ClassroomType])
    put("roomTypes", roomTypes)
  }
  
  override def getQueryBuilder(): OqlBuilder[Classroom] = {
    QueryHelper.addTemporalOn(super.getQueryBuilder(), getBoolean("active"))
  }

  override protected def saveAndRedirect(entity: Classroom): View = {
    entity.updatedAt = Instant.now
    entity.beginOn = LocalDate.now()
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