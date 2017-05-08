package org.openurp.edu.base.web.action.assist

import org.beangle.commons.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.commons.dao.OqlBuilder
import org.openurp.edu.base.web.action.ProjectRestfulAction
import org.beangle.commons.collection.Order


object SearchQueryCollection {

  //  def getQueryBuilder(): OqlBuilder[T] = {
  //    val builder: OqlBuilder[T] = OqlBuilder.from(entityName, simpleEntityName)
  //    populateConditions(builder)
  //    builder.where(simpleEntityName + ".project = :project", currentProject)
  //    getBoolean("active") foreach { active => 
  //      if (active) {
  //        builder.where(simpleEntityName + ".beginOn <= :now and (" + simpleEntityName + ".endOn is null or " + simpleEntityName + ".endOn >= :now)", new java.sql.Date(System.currentTimeMillis()))
  //      } else {
  //        builder.where("not (" + simpleEntityName + ".beginOn <= :now and (" + simpleEntityName + ".endOn is null or " + simpleEntityName + ".endOn >= :now))", new java.sql.Date(System.currentTimeMillis()))
  //      }
  //    }
  //    builder.orderBy(get(Order.OrderStr).orNull).limit(getPageLimit)
  //  }

  def addBeginOnQuery[T <: Entity[_]](builder: OqlBuilder[T], active: Option[Boolean]): OqlBuilder[T] = {
    active.foreach { active =>
      if (active) {
        builder.where(builder.alias + ".beginOn <= :now and (" + builder.alias + ".endOn is null or " + builder.alias + ".endOn >= :now)", new java.sql.Date(System.currentTimeMillis()))
      } else {
        builder.where("not (" + builder.alias + ".beginOn <= :now and (" + builder.alias + ".endOn is null or " + builder.alias + ".endOn >= :now))", new java.sql.Date(System.currentTimeMillis()))
      }
    }
    
    builder
  }
}