package org.openurp.edu.base.web.action.assist

import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity


/**
 * 查询条件辅助类
 * 
 * @author zhouqi 2017年5月9日
 *
 */
object QueryHelper {

  def addTemporalOn[T <: Entity[_]](builder: OqlBuilder[T], active: Option[Boolean]): OqlBuilder[T] = {
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