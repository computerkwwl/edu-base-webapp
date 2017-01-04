package org.openurp.edu.base.web.action

import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.code.edu.model.DisciplineCategory
import org.openurp.edu.base.model.{ Major, MajorDiscipline }

/**
 * @author xinzhou
 */
@action("{project}/major-discipline")
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