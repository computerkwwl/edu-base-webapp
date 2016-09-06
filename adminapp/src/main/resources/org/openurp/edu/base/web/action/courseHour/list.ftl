[#ftl]
[@b.head/]
[@b.grid items=courseHours var="courseHour"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="course" title="对应课程"]${(courseHour.course.name)!}[/@]
    [@b.col width="15%" property="period" title="学时"]${courseHour.period!}[/@]
    [@b.col width="15%" property="weekHour" title="周课时"]${courseHour.weekHour!}[/@]
    [@b.col width="15%" property="weeks" title="周数"]${courseHour.weeks!}[/@]
    [@b.col width="15%" property="hourType" title="课时类别代码"]${(courseHour.hourType.name)!}[/@]
  [/@]
  [/@]
[@b.foot/]
