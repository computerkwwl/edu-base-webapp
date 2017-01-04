package org.openurp.edu.base.web.action

import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.Department
import org.openurp.code.edu.model.StudentStatus
import org.openurp.edu.base.model.{ Adminclass, Direction, Major, StudentState }

@action("{project}/student-state")
class StudentStateAction extends RestfulAction[StudentState] {
  override def editSetting(entity: StudentState) = {
    val departments = findItems(classOf[Department])
    put("departments", departments)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    val adminclasses = findItems(classOf[Adminclass])
    put("adminclasses", adminclasses)

    val statuses = findItems(classOf[StudentStatus])
    put("statuses", statuses)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

}

