package org.openurp.security

import org.beangle.commons.inject.bind.AbstractBindModule
import org.beangle.commons.inject.PropertySource

class SecurityModule extends AbstractBindModule with PropertySource {

  protected override def binding() {
    bind(classOf[DaoUserStore])
    bind(classOf[RemoteAuthorizer])
  }
  override def properties: collection.Map[String, String] = {
    Map("security.cas.server" -> "http://portal.shfc.edu.cn/cas")
  }
}