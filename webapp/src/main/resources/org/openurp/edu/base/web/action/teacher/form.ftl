[#ftl]
[@b.head/]
[@b.toolbar title="修改教师信息"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if teacher.id??]!update?id=${teacher.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="teacher.code" label="职工号" value="${teacher.code!}" required="true" maxlength="20"/]
    [@b.textfield name="teacher.person.name" label="姓名" value="${teacher.person.name!}" required="true" maxlength="20"/]
    [@b.startend label="生效失效日期" 
      name="teacher.beginOn,teacher.endOn" required="false,false" 
      start=teacher.beginOn end=teacher.endOn format="date"/]
    [@b.textfield name="teacher.remark" label="备注" value="${teacher.remark!}" maxlength="30"/]
    [@b.select name="teacher.person.id" label="人员信息" value="${(teacher.person.id)!}" required="true" 
               style="width:200px;" items=persons option="id,name" empty="..."/]
    [@b.select name="teacher.department.id" label="部门" value="${(teacher.department.id)!}" required="true" 
               style="width:200px;" items=departments option="id,name" empty="..."/]
    [@b.select name="teacher.teacherType.id" label="教职工类别" value="${(teacher.teacherType.id)!}"  
               style="width:200px;" items=teacherTypes option="id,name" empty="..."/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]