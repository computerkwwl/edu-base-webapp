package org.openurp.edu.base.web.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.data.model.Entity
import org.beangle.data.dao.OqlBuilder
import org.beangle.commons.collection.Order
import org.openurp.edu.base.model.Project

class ProjectRestfulAction[T <: Entity[_]] extends RestfulAction[T] {

  protected override def getQueryBuilder(): OqlBuilder[T] = {
    val builder: OqlBuilder[T] = OqlBuilder.from(entityName, simpleEntityName)
    populateConditions(builder)
    builder.where(simpleEntityName + ".project = :project", cuurentProject)
    builder.orderBy(get(Order.OrderStr).orNull).limit(getPageLimit)
  }

  protected def cuurentProject: Project = {
    get("projectCode") match {
      case Some(code) =>
        val projects = entityDao.search(OqlBuilder.from(classOf[Project], "p").where("p.code= :code", code).cacheable())
        if (projects.size != 1) throw new RuntimeException("Cannot find unique project according to " + code)
        projects.head
      case None => throw new RuntimeException("Cannot find projectCode parameter")
    }

  }
}