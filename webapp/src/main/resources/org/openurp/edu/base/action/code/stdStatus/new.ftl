[#ftl]
[@b.head/]
[@b.toolbar title="新建学生学籍状态"]bar.addBack();[/@]
[@b.tabs]
  [@b.form action="!save" theme="list"]
    [@b.textfield name="stdStatus.code" label="代码" value="${stdStatus.code!}" required="true" maxlength="20"/]
    [@b.textfield name="stdStatus.name" label="名称" value="${stdStatus.name!}" required="true" maxlength="20"/]
    [@b.textfield name="stdStatus.enName" label="英文名" value="${stdStatus.enName!}" maxlength="100"/]
    [@b.startend label="生效失效时间" 
      name="stdStatus.beginOn,stdStatus.endOn" required="false,false" 
      start=stdStatus.beginOn end=stdStatus.endOn format="date"/]
    [@b.textfield name="stdStatus.remark" label="备注" value="${stdStatus.remark!}" maxlength="30"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]