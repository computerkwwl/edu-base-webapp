package org.openurp.edu.base.web.action

import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.MajorJournal
import org.openurp.edu.base.model.Major
import org.beangle.data.dao.OqlBuilder
import org.openurp.edu.base.code.model.Education
import org.openurp.base.model.Department
import org.openurp.code.edu.model.DisciplineCategory

class MajorJournalAction extends RestfulAction[MajorJournal] {
  override def editSetting(entity: MajorJournal) = {

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val categories = findItems(classOf[DisciplineCategory])
    put("categories", categories)

    val educations = findItems(classOf[Education])
    put("educations", educations)

    val departs = findItems(classOf[Department])
    put("departs", departs)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }
}

