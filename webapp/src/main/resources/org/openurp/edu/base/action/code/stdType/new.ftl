[#ftl]
[@b.head/]
[@b.toolbar title="新建学生类别"]bar.addBack();[/@]
[@b.tabs]
  [@b.form action="!save" theme="list"]
    [@b.textfield name="stdType.code" label="代码" value="${stdType.code!}" required="true" maxlength="20"/]
    [@b.textfield name="stdType.name" label="名称" value="${stdType.name!}" required="true" maxlength="20"/]
    [@b.select name="stdType.labelType.id" label="标签类型" value="${(stdType.labelType.id)!}" required="true" 
               style="width:200px;" items=labelTypes option="id,name" empty="..."/]
    [@b.textfield name="stdType.enName" label="英文名" value="${stdType.enName!}" maxlength="100"/]
    [@b.startend label="生效失效时间" 
      name="stdType.beginOn,stdType.endOn" required="false,false" 
      start=stdType.beginOn end=stdType.endOn format="date"/]
    [@b.textfield name="stdType.remark" label="备注" value="${stdType.remark!}" maxlength="30"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]