package org.openurp.edu.base.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.Department
import org.openurp.edu.base.Adminclass
import org.openurp.edu.base.Major
import org.openurp.edu.base.StudentJournal
import org.openurp.edu.base.code.StdStatus
import org.openurp.edu.base.Direction

class StudentJournalAction extends RestfulAction[StudentJournal] {
  override def editSetting(entity: StudentJournal) = {
    val departments = findItems(classOf[Department])
    put("departments", departments)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    val adminclasses = findItems(classOf[Adminclass])
    put("adminclasses", adminclasses)

    val statuses = findItems(classOf[StdStatus])
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

