[#ftl]
[@b.head/]
[@b.grid items=classrooms var="classroom"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="10%" property="code" title="代码"]${classroom.code}[/@]
    [@b.col width="15%" property="name" title="名称"][@b.a href="!info?id=${classroom.id}"]${classroom.name}[/@][/@]
    [@b.col width="15%" property="room" title="房间"]${(classroom.room.name)!}[/@]
    [@b.col width="15%" property="roomType" title="教室类型"]${(classroom.roomType.name)!}[/@]
    [@b.col width="10%" property="courseCapacity" title="上课容量"]${classroom.courseCapacity!}[/@]
    [@b.col width="10%" property="examCapacity" title="考试容量"]${classroom.examCapacity!}[/@]
  [/@]
  [/@]
[@b.foot/]
