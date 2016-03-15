[#ftl]
[@b.head/]
[@b.toolbar title="修改课程能力等级"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if courseAbilityRate.id??]!update?id=${courseAbilityRate.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="courseAbilityRate.code" label="代码" value="${courseAbilityRate.code!}" required="true" maxlength="20"/]
    [@b.textfield name="courseAbilityRate.name" label="名称" value="${courseAbilityRate.name!}" required="true" maxlength="20"/]
    [@b.textfield name="courseAbilityRate.enName" label="英文名" value="${courseAbilityRate.enName!}" maxlength="100"/]
    [@b.startend label="生效失效时间" 
      name="courseAbilityRate.beginOn,courseAbilityRate.endOn" required="false,false" 
      start=courseAbilityRate.beginOn end=courseAbilityRate.endOn format="date"/]
    [@b.textfield name="courseAbilityRate.remark" label="备注" value="${courseAbilityRate.remark!}" maxlength="3"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]