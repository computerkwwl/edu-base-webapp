[#ftl]
[@b.head/]
[@b.toolbar title="修改专业方向建设过程"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if directionJournal.id??]!update?id=${directionJournal.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.select name="directionJournal.education.id" label="培养层次" value="${(directionJournal.education.id)!}" required="true" 
               style="width:200px;" items=educations option="id,name" empty="..."/]
    [@b.select name="directionJournal.direction.id" label="专业方向" value="${(directionJournal.direction.id)!}" required="true" 
               style="width:200px;" items=directions option="id,name" empty="..." disabled="true"/]
    [@b.select name="directionJournal.depart.id" label="部门" value="${(directionJournal.depart.id)!}" required="true" 
               style="width:200px;" items=departs option="id,name" empty="..."/]
    [@b.startend label="生效失效日期" 
      name="directionJournal.beginOn,directionJournal.endOn" required="false,false" 
      start=directionJournal.beginOn end=directionJournal.endOn format="date"/]
    [@b.textfield name="directionJournal.remark" label="备注" value="${directionJournal.remark!}" maxlength="30"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]