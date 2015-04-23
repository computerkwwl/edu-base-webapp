package org.openurp.edu.base.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.Department
import org.openurp.hr.base.code.TeacherType
import org.openurp.edu.base.Teacher
import org.openurp.people.base.Person
import org.openurp.code.edu.Degree
import org.openurp.code.edu.Education
import org.openurp.hr.base.code.TutorType
import org.openurp.code.job.ProfessionalTitle
import org.openurp.hr.base.Staff

class TeacherAction extends RestfulAction[Teacher] {
  override def editSetting(entity: Teacher) = {
//    val departments = findItems(classOf[Department])
//    put("departments", departments)

    val persons = findItems(classOf[Staff])
    put("persons", persons)

//    val titles = findItems(classOf[ProfessionalTitle])
//    put("titles", titles)
//
//    val educations = findItems(classOf[Education])
//    put("educations", educations)
//
//    val degrees = findItems(classOf[Degree])
//    put("degrees", degrees)
//
//    val tutorTypes = findItems(classOf[TutorType])
//    put("tutorTypes", tutorTypes)
//
//    val parttimeDeparts = findItems(classOf[Department])
//    put("parttimeDeparts", parttimeDeparts)
//
//    val teacherTypes = findItems(classOf[TeacherType])
//    put("teacherTypes", teacherTypes)
//
//    val unitTypes = findItems(classOf[TeacherUnitType])
//    put("unitTypes", unitTypes)
//
//    val states = findItems(classOf[TeacherState])
//    put("states", states)

    super.editSetting(entity)
  }

  private def findItems[T <: Entity[_]](clazz: Class[T]): Seq[T] = {
    val query = OqlBuilder.from(clazz)
    query.orderBy("name")
    val items = entityDao.search(query)
    items
  }

}

