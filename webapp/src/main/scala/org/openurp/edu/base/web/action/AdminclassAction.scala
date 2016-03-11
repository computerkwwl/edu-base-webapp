package org.openurp.edu.base.web.action

import org.openurp.base.model.Department
import org.openurp.edu.base.model.Adminclass
import org.openurp.edu.base.code.model.StdType
import org.openurp.edu.base.model.Major
import org.openurp.edu.base.model.Teacher
import org.beangle.data.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.api.view.View
import org.openurp.edu.base.model.Direction
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.model.User
import org.openurp.edu.base.model.Instructor

class AdminclassAction extends RestfulAction[Adminclass] {
  override def editSetting(entity: Adminclass) = {
    val departments = findItems(classOf[Department])
    put("departments", departments)

    val majors = findItems(classOf[Major])
    put("majors", majors)

    val directions = findItems(classOf[Direction])
    put("directions", directions)

    val stdTypes = findItems(classOf[StdType])
    put("stdTypes", stdTypes)

    val instructors = findItems(classOf[Instructor])
    put("instructors", instructors)

    val tutors = findItems(classOf[Teacher])
    put("tutors", tutors)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    //    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

}

