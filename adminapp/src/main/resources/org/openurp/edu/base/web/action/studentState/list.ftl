[#ftl]
[@b.head/]
[@b.grid items=studentStates var="studentState"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="id" title="ID"]${studentState.id}[/@]
    [@b.col width="15%" property="grade" title="年级"]${studentState.grade!}[/@]
    [@b.col width="15%" property="major" title="专业"]${studentState.major.name!}[/@]
    [@b.col width="10%" property="department" title="行政管理院系"]${studentState.department.name!}[/@]
  [/@]
[/@]
[@b.foot/]
