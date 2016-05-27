[#ftl]
[@b.head/]
[@b.toolbar title="修改专业建设过程"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if majorJournal.persisted]!update?id=${majorJournal.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.select name="majorJournal.education.id" label="培养层次" value="${(majorJournal.education.id)!}" required="true" 
               style="width:200px;" items=educations option="id,name" empty="..."/]
    [@b.select name="majorJournal.depart.id" label="部门" value="${(majorJournal.depart.id)!}" required="true" 
               style="width:200px;" items=departs option="id,name" empty="..."/]
    [@b.textfield name="majorJournal.duration" label="修读年限" value="${majorJournal.duration!}" required="true"/]
    [@b.startend label="生效失效时间" 
      name="majorJournal.beginOn,majorJournal.endOn" required="true,false" 
      start=majorJournal.beginOn end=majorJournal.endOn format="date"/]
    [@b.textfield name="majorJournal.remark" label="备注" value="${majorJournal.remark!}" maxlength="30"/]
    [@b.formfoot]
      <input type="hidden" name="majorJournal.major.id" value="${majorJournal.major.id}"/>
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]