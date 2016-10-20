[#ftl]
[@b.head/]
[@b.toolbar title="修改教师信息"]bar.addBack();[/@]
  [@b.form action=b.rest.save(teacher) theme="list"]
    [@b.textfield name="user.code" label="职工号" value="" required="true" style="width:100px;" maxlength="20"/]
    [@b.textfield name="user.name" label="姓名" value="" required="true" style="width:100px;" maxlength="20"/]
    [@b.select name="person.gender.id" label="性别" value="" style="width:100px;" items=genders option="id,name" empty="..."/]
    [@b.select name="person.idType.id" label="证件类型" value="" style="width:100px;" items=idTypes option="id,name" empty="..."/]
    [@b.textfield name="person.code" label="证件号码" value="" maxlength="30" style="width:200px;" /]
    [@b.datepicker name="person.birthday" label="出生日期" value="" /]
    
    [@b.select name="user.department.id" label="部门" value="" required="true" style="width:200px;" items=departments option="id,name" empty="..."/]
    [@b.select name="teacher.teacherType.id" label="教师类型" value="" required="true" style="width:200px;" items=teacherTypes option="id,name" empty="..."/]
    [@b.startend label="任教时间" name="teacher.beginOn,teacher.endOn" required="true,false" format="date"/]
    [@b.textfield name="teacher.remark" label="备注" value="${teacher.remark!}" maxlength="30"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[@b.foot/]