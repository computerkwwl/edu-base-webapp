package org.openurp.edu.base.web.action

import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.model.{ Direction, Major }
import org.beangle.commons.collection.Order

@action("{project}/direction")
class DirectionAction extends ProjectRestfulAction[Direction] {

  override def indexSetting() = {
    put("majors", findItems(classOf[Major]))
  }
  override def editSetting(entity: Direction) = {

    val majors = findItemsByProject(classOf[Major])
    put("majors", majors)

    super.editSetting(entity)
  }

}

