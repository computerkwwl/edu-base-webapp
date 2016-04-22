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
import org.openurp.platform.api.Urp
import org.openurp.base.model.School
import org.openurp.platform.api.security.Securities
import org.openurp.hr.base.model.Staff
import org.openurp.base.model.User
import org.beangle.webmvc.api.annotation.mapping
import org.beangle.security.mgt.SecurityManager
import org.openurp.edu.base.model.Project

/**
 * @author xinzhou
 */
class IndexAction extends ActionSupport {
  var entityDao: EntityDao = _
  var casConfig: CasConfig = _
  var securityManager: SecurityManager = _

  @mapping("{project}")
  def project(): String = {
    val menuJson = IOs.readString(new URL("http://platform.urp.sfu.edu.cn/security/func/" + UrpApp.name + "/menus/user/" + Securities.user + ".json").openStream())
    put("menuJson", menuJson)

    val appJson = IOs.readString(new URL("http://platform.urp.sfu.edu.cn/user/apps/" + Securities.user + ".json").openStream())
    put("appJson", appJson)

    entityDao.getAll(classOf[School]) foreach { school => put("school", school) }

    put("user", getUser())
    put("casConfig", casConfig)
    put("webappBase", "http://webapp.urp.sfu.edu.cn")
    put("thisAppName", UrpApp.name)
    forward()
  }

  def getUser(): User = {
    val users = entityDao.findBy(classOf[User], "code", List(Securities.user))
    if (users.isEmpty) {
      throw new RuntimeException("Cannot find staff with code " + Securities.user)
    } else {
      users.head
    }
  }

  def index(): View = {
    val now = new java.sql.Date(System.currentTimeMillis())
    val builder = OqlBuilder.from(classOf[Project], "p").where("p.beginOn <= :now and( p.endOn is null or p.endOn >= :now)", now).orderBy("p.code").cacheable()
    val projects = entityDao.search(builder)
    if (projects.isEmpty) throw new RuntimeException("Cannot find any valid projects")

    redirect("project", "&project=" + projects.head.code, null)
  }

  def logout(): View = {
    securityManager.logout(SecurityContext.session)
    redirect(to(casConfig.casServer + "/logout"), null)
  }
}