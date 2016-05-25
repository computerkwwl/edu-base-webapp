[#ftl]
[@b.head/]
[@b.toolbar title="修改学籍状态日志"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if studentState.persisted]!update?id=${studentState.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="studentState.grade" label="年级" value="${studentState.grade!}" required="true" /]    
    [@b.select name="studentState.department.id" label="行政管理院系" value="${(studentState.department.id)!}" required="true" 
               style="width:200px;" items=departments option="id,name" empty="..."/]    
    [@b.select name="studentState.major.id" label="专业" value="${(studentState.major.id)!}" required="true" 
               style="width:200px;" items=majors option="id,name" empty="..."/]    
    [@b.select name="studentState.direction.id" label="专业方向" value="${(studentState.direction.id)!}" required="true" 
               style="width:200px;" items=directions option="id,name" empty="..."/]    
    [@b.radios label="是否在校"  name="studentState.inschool" value=studentState.inschool items="1:common.yes,0:common.no"/]
    [@b.textfield name="studentState.remark" label="备注" value="${studentState.remark!}"/]
    [@b.select name="studentState.status.id" label="学籍状态" value="${(studentState.status.id)!}" required="true" 
               style="width:200px;" items=statuses option="id,name" empty="..."/]
    [@b.select name="studentState.adminclass.id" label="行政班级" value="${(studentState.adminclass.id)!}" required="true" 
               style="width:200px;" items=adminclasses option="id,name" empty="..."/]
    [@b.startend label="生效失效日期" 
      name="studentState.beginOn,studentState.endOn" required="true,false" 
      start=studentState.beginOn end=studentState.endOn format="date"/]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]

[@b.foot/]