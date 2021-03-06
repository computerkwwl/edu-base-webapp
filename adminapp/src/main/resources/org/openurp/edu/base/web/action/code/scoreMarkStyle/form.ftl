[#ftl]
[@b.head/]
[@b.toolbar title="修改成绩记录方式"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if scoreMarkStyle.persisted]!update?id=${scoreMarkStyle.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="scoreMarkStyle.code" label="代码" value="${scoreMarkStyle.code!}" required="true" maxlength="20"/]
    [@b.textfield name="scoreMarkStyle.name" label="名称" value="${scoreMarkStyle.name!}" required="true" maxlength="20"/]
    [@b.textfield name="scoreMarkStyle.enName" label="英文名" value="${scoreMarkStyle.enName!}" maxlength="100"/]
    [@b.startend label="生效失效时间" 
      name="scoreMarkStyle.beginOn,scoreMarkStyle.endOn" required="true,false" 
      start=scoreMarkStyle.beginOn end=scoreMarkStyle.endOn format="date"/]
    [@b.textfield name="scoreMarkStyle.remark" label="备注" value="${scoreMarkStyle.remark!}" maxlength="3"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]