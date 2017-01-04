package org.openurp.edu.base.web.action

import org.beangle.commons.dao.EntityDao
import org.beangle.data.transfer.TransferListener
import org.beangle.data.transfer.TransferResult

/**
 * @author xinzhou
 */
class AdminclassImportListener(entityDao: EntityDao) extends TransferListener {
  override def onItemFinish(tr: TransferResult) {
    entityDao.saveOrUpdate(tr.transfer.current)
  }
  /**
   * 结束转换
   */
  override def onStart(tr: TransferResult) {}

  /**
   * 结束转换
   */
  override def onFinish(tr: TransferResult) {}

  /**
   * 开始转换单个项目
   */
  override def onItemStart(tr: TransferResult) {}

}