package org.openurp.edu.base.web.action

import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.{ Project, ProjectCode }

class ProjectCodeAction extends RestfulAction[ProjectCode] {
  override def editSetting(entity: ProjectCode) = {
    val projects = findItems(classOf[Project])
    put("projects", projects)
    //
    //    val metas = findItems(classOf[CodeMeta])
    //    put("metas", metas)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

}