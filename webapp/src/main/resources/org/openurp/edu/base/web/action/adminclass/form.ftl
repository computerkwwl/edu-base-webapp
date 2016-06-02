[#ftl]
[@b.head/]
[@b.toolbar title="行政班级信息"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if adminclass.persisted]!update?id=${adminclass.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="adminclass.grade" label="年级" value="${adminclass.grade!}" required="true" /]
    [@b.textfield name="adminclass.code" label="代码" value="${adminclass.code!}" required="true" maxlength="20"/]
    [@b.textfield name="adminclass.name" label="名称" value="${adminclass.name!}" required="true" maxlength="20"/]
    [@b.textfield name="adminclass.shortName" label="简称" value="${adminclass.shortName!}" maxlength="100"/]
    [@b.select name="adminclass.campus.id" label="校区" value="${(adminclass.campus.id)!}"  style="width:100px;" items=campuses option="id,name" empty="..."/]
    [@b.select name="adminclass.department.id" label="院系" value="${(adminclass.department.id)!}" required="true" 
               style="width:200px;" items=departments option="id,name" empty="..."/]
    [@b.select name="adminclass.education.id" label="培养层次" value="${(adminclass.education.id)!}" style="width:200px;" items=educations option="id,name" empty="..."/]
    [@b.select name="adminclass.major.id" label="专业" value="${(adminclass.major.id)!}"  
               style="width:200px;" items=majors option="id,name" empty="..."/]
    [@b.select name="adminclass.direction.id" label="方向" value="${(adminclass.direction.id)!}" 
               style="width:200px;" items=directions option="id,name" empty="..."/]
    [@b.select name="adminclass.stdType.id" label="学生类别" value="${(adminclass.stdType.id)!}" required="true" 
               style="width:200px;" items=stdTypes option="id,name" empty="..."/]
    [@b.select name="adminclass.instructor.id" label="辅导员" value="${(adminclass.instructor.id)!}" 
               style="width:200px;" items=instructors option=r"${item.user.code} ${item.user.name}" empty="..."/]
    [@b.select name="adminclass.tutor.id" label="班导师" value="${(adminclass.tutor.id)!}" 
               style="width:200px;" items=tutors option=r"${item.user.name}" empty="..."/]
    [@b.textfield name="adminclass.remark" label="备注" value="${adminclass.remark!}" maxlength="30"/]
    [@b.textfield name="adminclass.planCount" label="计划人数" value="${adminclass.planCount!}" /]
    [@b.textfield name="adminclass.stdCount" label="学籍有效人数" value="${adminclass.stdCount!}"/]
    [@b.startend label="生效失效时间" 
      name="adminclass.beginOn,adminclass.endOn" required="true,false" 
      start=adminclass.beginOn end=adminclass.endOn format="date"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]