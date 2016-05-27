package org.openurp.edu.base.web.action

import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.Department
import org.openurp.code.edu.model.DisciplineCategory
import org.openurp.edu.base.code.model.Education
import org.openurp.edu.base.model.{ Major, MajorJournal }
import org.beangle.commons.collection.Order

@action("{project}/major-journal")
class MajorJournalAction extends RestfulAction[MajorJournal] with ProjectSupport {

  override def editSetting(entity: MajorJournal) = {

    put("categories", findItems(classOf[DisciplineCategory]))

    put("educations", findItems(classOf[Education]))

    put("departs", findItemsBySchool(classOf[Department]))

    super.editSetting(entity)
  }

}
