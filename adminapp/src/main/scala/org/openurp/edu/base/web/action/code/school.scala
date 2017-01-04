package org.openurp.edu.base.web.action.code

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.openurp.edu.base.code.model.StdType
import org.openurp.edu.base.code.model.CourseHourType
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.code.model.CourseCategory
import org.openurp.edu.base.code.model.StdLabelType
import org.openurp.edu.base.code.model.ExamMode
import org.beangle.commons.dao.OqlBuilder
import org.openurp.edu.base.code.model.StdLabel
import org.openurp.edu.base.code.model.ExamStatus
import org.openurp.edu.base.code.model.CourseAbilityRate

class StdLabelAction extends RestfulAction[StdLabel] {
  override def editSetting(entity: StdLabel) = {
    val query = OqlBuilder.from(classOf[StdLabelType])
    query.orderBy("name")
    val labelTypes = entityDao.search(query)
    put("labelTypes", labelTypes)
    super.editSetting(entity)
  }
}

class StdLabelTypeAction extends RestfulAction[StdLabelType]

class StdTypeAction extends RestfulAction[StdType]
//class StdStatusAction extends RestfulAction[StdStatus]

class CourseAbilityRateAction extends RestfulAction[CourseAbilityRate]

class CourseCategoryAction extends RestfulAction[CourseCategory]

class CourseHourTypeAction extends RestfulAction[CourseHourType]

class CourseTypeAction extends RestfulAction[CourseType]

class ScoreMarkStyleAction extends RestfulAction[ScoreMarkStyle]

class ExamModeAction extends RestfulAction[ExamMode]

class ExamStatusAction extends RestfulAction[ExamStatus]
