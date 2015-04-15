package org.openurp.edu.base.action

import org.beangle.commons.inject.bind.AbstractBindModule


class DefaultModule extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[AdminclassAction], classOf[MajorAction], classOf[DirectionAction], classOf[DirectionJournalAction], classOf[MajorJournalAction])
    bind(classOf[ProjectAction], classOf[ProjectCodeAction], classOf[ProjectClassroomAction])
    bind(classOf[StudentAction], classOf[StudentJournalAction])
    bind(classOf[CourseAction], classOf[CourseHourAction])
  }
}