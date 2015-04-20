[#ftl]
[@b.head/]
[@b.grid items=projectCodeBeans var="projectCodeBean"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="project.name" title="项目名称"][@b.a href="!info?id=${projectCodeBean.id}"]${projectCodeBean.project.name!}[/@][/@]
    [@b.col width="20%" property="meta" title="代码元"]${projectCodeBean.meta.name!}[/@]
    [@b.col width="20%" property="codeId" title="代码Id"]${projectCodeBean.codeId!}[/@]
  [/@]
[/@]
[@b.foot/]
