package org.openurp.edu.base.action

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.edu.base.action.code.CourseCategoryAction
import org.openurp.edu.base.action.code.StdStatusAction
import org.openurp.edu.base.action.code.StdLabelTypeAction
import org.openurp.edu.base.action.code.ScoreMarkStyleAction
import org.openurp.edu.base.action.code.StdTypeAction
import org.openurp.edu.base.action.code.CourseAbilityRateAction
import org.openurp.edu.base.action.code.ExamStatusAction
import org.openurp.edu.base.action.code.StdLabelAction
import org.openurp.edu.base.action.code.ExamModeAction
import org.openurp.edu.base.action.code.CourseTypeAction
import org.beangle.commons.inject.PropertySource
import org.openurp.security.RemoteAuthorizer
import org.openurp.security.DaoUserStore

class DefaultModule extends AbstractBindModule   {

  protected override def binding() {
    bind(classOf[AdminclassAction], classOf[MajorAction], classOf[DirectionAction], classOf[DirectionJournalAction], classOf[MajorJournalAction])
    bind(classOf[ProjectAction], classOf[ProjectCodeAction])
    bind(classOf[StudentAction])
    bind(classOf[CourseAction], classOf[CourseHourAction])
    bind(classOf[StdLabelAction], classOf[StdLabelTypeAction], classOf[StdTypeAction], classOf[StdStatusAction])
    bind(classOf[ExamModeAction], classOf[ExamStatusAction])
    bind(classOf[CourseAbilityRateAction], classOf[CourseCategoryAction], classOf[CourseTypeAction])
    bind(classOf[ScoreMarkStyleAction])
    bind(classOf[TeacherAction])
  }

}
