package org.openurp.edu.base.web.action

import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.Department
import org.openurp.edu.base.model.Teacher
import org.openurp.people.base.model.Person

@action("{project}/teacher")
class TeacherAction extends ProjectRestfulAction[Teacher] {

  override def editSetting(entity: Teacher) = {
    put("departments", findItemsBySchool(classOf[Department]))

    val persons = findItems(classOf[Person])
    put("persons", persons)

    super.editSetting(entity)
  }

  override protected def indexSetting(): Unit = {
    put("departments", findItemsBySchool(classOf[Department]))
  }

}

