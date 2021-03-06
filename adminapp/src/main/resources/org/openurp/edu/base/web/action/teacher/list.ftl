[#ftl]
[@b.head/]
[@b.grid items=teachers var="teacher"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="user.code" title="工号"]${teacher.user.code}[/@]
    [@b.col width="20%" property="user.name" title="姓名"][@b.a href="!info?id=${teacher.id}"]${teacher.user.name}[/@][/@]
    [@b.col width="25%" property="user.department.id" title="部门"]${(teacher.user.department.name)!}[/@]
    [@b.col width="10%" property="teacherType.name" title="类型"/]
    [@b.col width="10%" property="beginOn" title="生效时间"]${teacher.beginOn!}[/@]
    [@b.col width="10%" property="endOn" title="失效时间"]${teacher.endOn!}[/@]
  [/@]
[/@]
[@b.foot/]
