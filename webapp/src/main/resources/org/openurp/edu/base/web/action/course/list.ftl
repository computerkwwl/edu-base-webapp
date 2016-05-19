[#ftl]
[@b.head/]
[@b.grid items=courses var="course"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="10%" property="code" title="代码"/]
    [@b.col width="15%" property="name" title="名称"][@b.a href="!info?id=${course.id}"]${course.name}[/@][/@]
    [@b.col width="10%" property="period" title="学时"/]
    [@b.col width="10%" property="weekHour" title="周课时"/]
    [@b.col width="10%" property="credits" title="学分"/]
    [@b.col width="20%" property="department.name" title="课程所属部门"/]
    [@b.col width="20%" property="courseType.name" title="课程类型"/]
  [/@]
  [/@]
[@b.foot/]
