[#ftl]
[@b.head/]
[@b.toolbar title="修改专业建设过程"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if majorDiscipline.id??]!update?id=${majorDiscipline.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.select name="majorDiscipline.major.id" label="专业" value="${(majorDiscipline.major.id)!}" required="true" 
               style="width:200px;" items=majors option="id,name" empty="..." disabled="true"/]       
    [@b.select name="majorDiscipline.category.id" label="学科门类" value="${(majorDiscipline.category.id)!}" required="true" 
               style="width:200px;" items=categories option="id,name" empty="..."/]
    [@b.textfield name="disciplineCode.remark" label="教育部代码" value="${majorDiscipline.disciplineCode!}" maxlength="30"/]
    [@b.startend label="生效失效时间" 
      name="majorDiscipline.beginOn,majorDiscipline.endOn" required="false,false" 
      start=majorDiscipline.beginOn end=majorDiscipline.endOn format="date"/]
    [@b.textfield name="majorDiscipline.remark" label="备注" value="${majorDiscipline.remark!}" maxlength="30"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]