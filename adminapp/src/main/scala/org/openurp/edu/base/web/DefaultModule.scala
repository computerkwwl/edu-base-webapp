package org.openurp.edu.base.web

import org.beangle.commons.cdi.bind.AbstractBindModule
import org.openurp.edu.base.web.action.{ AdminclassAction, CourseAction, CourseHourAction, DirectionAction, DirectionJournalAction, MajorAction, MajorDisciplineAction, MajorJournalAction, ProjectAction, ProjectCodeAction, StudentAction, StudentStateAction, TeacherAction }
import org.openurp.edu.base.web.action.code.{ CourseAbilityRateAction, CourseCategoryAction, CourseHourTypeAction, CourseTypeAction, ExamModeAction, ExamStatusAction, ScoreMarkStyleAction, StdLabelAction, StdLabelTypeAction, StdTypeAction }
import org.openurp.edu.base.web.action.IndexAction
import org.openurp.edu.base.web.action.ClassroomAction

class DefaultModule extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[AdminclassAction], classOf[MajorAction], classOf[DirectionAction], classOf[DirectionJournalAction], classOf[MajorJournalAction], classOf[MajorDisciplineAction])
    bind(classOf[ProjectAction], classOf[ProjectCodeAction])
    bind(classOf[StudentAction], classOf[StudentStateAction])
    bind(classOf[CourseAction], classOf[CourseHourAction], classOf[CourseHourTypeAction])
    bind(classOf[StdLabelAction], classOf[StdLabelTypeAction], classOf[StdTypeAction])
    bind(classOf[ExamModeAction], classOf[ExamStatusAction])
    bind(classOf[CourseAbilityRateAction], classOf[CourseCategoryAction], classOf[CourseTypeAction])
    bind(classOf[ScoreMarkStyleAction])
    bind(classOf[TeacherAction])
    bind(classOf[IndexAction])
    bind(classOf[ClassroomAction])
  }
}