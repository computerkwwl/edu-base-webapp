package org.openurp.edu.base.web.action

import org.beangle.commons.dao.OqlBuilder
import org.beangle.commons.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.Education
import org.openurp.edu.base.model.{ Direction, DirectionJournal }
import org.beangle.commons.collection.Order

@action("{project}/direction-journal")
class DirectionJournalAction extends RestfulAction[DirectionJournal] with ProjectSupport {
  override def editSetting(entity: DirectionJournal) = {

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    val educations = findItems(classOf[Education])
    put("educations", educations)

    put("departs", findItemsBySchool(classOf[Department]))

    super.editSetting(entity)
  }

}

