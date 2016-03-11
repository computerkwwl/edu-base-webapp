[#ftl]
[@b.head/]
[@b.toolbar title="新建项目基础代码配置"]bar.addBack();[/@]
[@b.tabs]
  [@b.form action="!save" theme="list"]

    [@b.select name="projectCodeBean.project.id" label="项目名称" value="${(projectCodeBean.project.id)!}" required="true" 
               style="width:200px;" items=projects option="id,name" empty="..."/]
    [@b.select name="projectCodeBean.meta.id" label="代码元" value="${(projectCodeBean.meta.id)!}" required="true" 
               style="width:200px;" items=metas option="id,name" empty="..."/]
    [@b.textfield name="projectCodeBean.codeId" label="代码ID" value="${projectCodeBean.codeId!}" required="true" maxlength="30"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]

