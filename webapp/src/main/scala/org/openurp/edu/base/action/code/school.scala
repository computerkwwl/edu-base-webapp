package org.openurp.edu.base.action.code

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.annotation.code
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.code.CourseAbilityRate
import org.openurp.edu.base.code.CourseCategory
import org.openurp.edu.base.code.CourseHourType
import org.openurp.edu.base.code.CourseType
import org.openurp.edu.base.code.ExamMode
import org.openurp.edu.base.code.ExamStatus
import org.openurp.edu.base.code.ScoreMarkStyle
import org.openurp.edu.base.code.StdLabel
import org.openurp.edu.base.code.StdLabelType
import org.openurp.edu.base.code.StdStatus
import org.openurp.edu.base.code.StdType

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

class StdTypeAction extends RestfulAction[StdType] {
  override def editSetting(entity: StdType) = {
    val query = OqlBuilder.from(classOf[StdLabelType])
    query.orderBy("name")
    val labelTypes = entityDao.search(query)
    put("labelTypes", labelTypes)
    super.editSetting(entity)
  }
}
class StdStatusAction extends RestfulAction[StdStatus]

class CourseAbilityRateAction extends RestfulAction[CourseAbilityRate]

class CourseCategoryAction extends RestfulAction[CourseCategory]

class CourseHourTypeAction extends RestfulAction[CourseHourType]

class CourseTypeAction extends RestfulAction[CourseType]

class ScoreMarkStyleAction extends RestfulAction[ScoreMarkStyle]

class ExamModeAction extends RestfulAction[ExamMode]

class ExamStatusAction extends RestfulAction[ExamStatus] 
