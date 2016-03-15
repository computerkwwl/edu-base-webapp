package org.openurp.edu.base.web.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.CourseHour
import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.openurp.edu.base.code.model.CourseHourType
import org.openurp.edu.base.model.Course

class CourseHourAction extends RestfulAction[CourseHour] {

  override def indexSetting(): Unit = {
    put("courses", findItems(classOf[Course]))
  }

  override def editSetting(entity: CourseHour) = {
    put("courses", findItems(classOf[Course]))
    put("hourTypes", findItems(classOf[CourseHourType]))

  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }
}

