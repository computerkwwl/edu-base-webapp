package org.openurp.edu.base.web.action

import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.Adminclass
import org.openurp.edu.base.model.Major
import org.openurp.edu.base.model.Direction
import org.beangle.data.dao.OqlBuilder
import org.openurp.base.model.Department
import org.openurp.edu.base.model.StudentState
import org.openurp.code.edu.model.StudentStatus

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

