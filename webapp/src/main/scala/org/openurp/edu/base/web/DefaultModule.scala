package org.openurp.edu.base.web

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.edu.base.web.action.AdminclassAction
import org.openurp.edu.base.web.action.CourseAction
import org.openurp.edu.base.web.action.CourseHourAction
import org.openurp.edu.base.web.action.DirectionAction
import org.openurp.edu.base.web.action.DirectionJournalAction
import org.openurp.edu.base.web.action.MajorAction
import org.openurp.edu.base.web.action.MajorJournalAction
import org.openurp.edu.base.web.action.ProjectAction
import org.openurp.edu.base.web.action.ProjectCodeAction
import org.openurp.edu.base.web.action.StudentAction

class DefaultModule extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[AdminclassAction], classOf[MajorAction], classOf[DirectionAction], classOf[DirectionJournalAction], classOf[MajorJournalAction])
    bind(classOf[ProjectAction], classOf[ProjectCodeAction])
    bind(classOf[StudentAction])
    bind(classOf[CourseAction], classOf[CourseHourAction])
  }
}