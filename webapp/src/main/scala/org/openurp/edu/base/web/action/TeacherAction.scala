package org.openurp.edu.base.web.action

import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.data.dao.OqlBuilder
import org.openurp.edu.base.model.Teacher
import org.openurp.base.model.Department

class TeacherAction extends RestfulAction[Teacher] {
  override def editSetting(entity: Teacher) = {
    val departments = findItems(classOf[Department])
    put("departments", departments)

    val persons = findItems(classOf[Teacher])
    put("persons", persons)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    //    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

}

