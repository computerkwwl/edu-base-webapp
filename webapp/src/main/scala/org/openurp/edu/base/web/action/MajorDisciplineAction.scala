package org.openurp.edu.base.web.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.MajorDiscipline
import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.openurp.edu.base.model.Major
import org.openurp.code.edu.model.DisciplineCategory

/**
 * @author xinzhou
 */
class MajorDisciplineAction extends RestfulAction[MajorDiscipline] {

  override def editSetting(entity: MajorDiscipline) {
    put("majors", findItems(classOf[Major]))
    put("categories", classOf[DisciplineCategory])

  }
  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }
}