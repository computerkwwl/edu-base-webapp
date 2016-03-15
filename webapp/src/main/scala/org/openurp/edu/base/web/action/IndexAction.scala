package org.openurp.edu.base.web.action

import org.openurp.platform.api.app.UrpApp
import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.security.context.SecurityContext
import org.beangle.data.dao.EntityDao
import org.beangle.data.dao.OqlBuilder
import org.beangle.security.realm.cas.CasConfig
import org.beangle.webmvc.api.annotation.param
import org.beangle.webmvc.api.view.View
import org.beangle.security.mgt.SecurityManager
import org.openurp.platform.api.util.JSON
import org.beangle.commons.io.IOs
import java.net.URL

/**
 * @author xinzhou
 */
class IndexAction extends ActionSupport {
  var entityDao: EntityDao = _
  var casConfig: CasConfig = _
  var securityManager: SecurityManager = _

  def index(): String = {
    val menus = IOs.readString(new URL("http://platform.urp.sfu.edu.cn/security/" + UrpApp.name + "/menus.json").openStream())
    put("menus", menus);
    forward()
  }

  def logout(): View = {
    securityManager.logout(SecurityContext.session)
    redirect(to(casConfig.casServer + "/logout"), null)
  }
}