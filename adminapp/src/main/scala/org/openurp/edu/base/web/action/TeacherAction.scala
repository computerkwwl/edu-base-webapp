package org.openurp.edu.base.web.action

import org.beangle.commons.lang.Strings
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.Department
import org.openurp.edu.base.model.Teacher
import org.openurp.people.base.model.Person
import org.openurp.code.person.model.Gender
import org.openurp.code.person.model.IdType
import org.beangle.commons.bean.Properties
import org.beangle.webmvc.api.view.View
import org.openurp.base.model.User
import org.openurp.people.base.model.Name
import org.beangle.webmvc.execution.Handler
import org.beangle.commons.dao.OqlBuilder
import org.openurp.base.code.model.UserCategory
import org.openurp.edu.base.code.model.TeacherType

@action("{project}/teacher")
class TeacherAction extends ProjectRestfulAction[Teacher] {

  override def editSetting(entity: Teacher) = {
    if (!entity.persisted) {
      put("departments", findItemsBySchool(classOf[Department]))
      put("genders", entityDao.getAll(classOf[Gender]))
      put("idTypes", entityDao.getAll(classOf[IdType]))
    }
    put("teacherTypes", entityDao.getAll(classOf[TeacherType]))
    super.editSetting(entity)
  }

  override protected def saveAndRedirect(entity: Teacher): View = {
    Properties.set(entity, "project", currentProject)

    if (!entity.persisted) {
      var user = populate(classOf[User])
      val school = currentProject.school
      val userQuery = OqlBuilder.from(classOf[User], "user").where("user.code=:code", user.code).where("user.school =:school", school)
      val users = entityDao.search(userQuery)
      if (users.size == 1) {
        user = users.head
      } else {
        user.school = school
        user.category = new UserCategory
        //FIXME Teacher Category ID =1
        user.category.id = 1
        user.updatedAt = new java.util.Date
      }

      var person = populate(classOf[Person])
      if (Strings.isNotEmpty(person.code)) {
        val people = entityDao.findBy(classOf[Person], "code", List(person.code))
        if (people.size == 0) {
          person.name = new Name
          person.name.formatedName = user.name
          person.updatedAt = new java.util.Date
        } else if (people.size == 1) {
          person = people.head
          if (!user.persisted) user.name = person.name.formatedName
        }
      }
      entity.user = user
      entity.updatedAt = new java.util.Date
      try {
        if (Strings.isNotEmpty( person.code)) {
          entityDao.saveOrUpdate(user, person, entity)
        }else{
          entityDao.saveOrUpdate(user, entity)
        }
        redirect("search", "info.save.success")
      } catch {
        case e: Exception => {
          val redirectTo = Handler.mapping.method.getName match {
            case "save" => "editNew"
            case "update" => "edit"
          }
          logger.info("save forwad failure", e)
          redirect(redirectTo, "info.save.failure")
        }
      }
    } else {
      super.saveAndRedirect(entity)
    }
  }
  override protected def indexSetting(): Unit = {
    put("departments", findItemsBySchool(classOf[Department]))
    put("teacherTypes", entityDao.getAll(classOf[TeacherType]))
  }

}

