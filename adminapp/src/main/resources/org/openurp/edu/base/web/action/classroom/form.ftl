[#ftl]
[@b.head/]
[@b.toolbar title="修改教室"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if classroom.persisted]!update?id=${classroom.id}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="classroom.code" label="代码" value="${classroom.code!}" required="true" maxlength="20"/]
    [@b.textfield name="classroom.name" label="名称" value="${classroom.name!}" required="true" maxlength="20"/]
    [@b.textfield name="classroom.enName" label="英文名" value="${classroom.enName!}" maxlength="100" style="width:400px"/]
    [@b.select name="classroom.campus.id" label="校区" value="${(classroom.campus.id)!}"  
               style="width:200px;" items=campuses option="id,name" empty="..."  required="true"/]
    [@b.select name="classroom.room.id" label="房间" value="${(classroom.room.id)!}"  
               style="width:200px;" items=rooms option="id,name" empty="..."/]
    [@b.select name="classroom.roomType.id" label="教室类型" value="${(classroom.roomType.id)!}"  
               style="width:200px;" items=roomTypes option="id,name" empty="..."  required="true"/]
    [@b.textfield name="classroom.courseCapacity" label="上课容量" required="true"  value="${classroom.courseCapacity!}" maxlength="20"/]
    [@b.textfield name="classroom.examCapacity" label="考试容量"  required="true" value="${classroom.examCapacity!}" maxlength="20"/]
    [@b.startend label="生效失效时间"  name="classroom.beginOn,classroom.endOn" required="false,false" 
      start=classroom.beginOn end=classroom.endOn format="date"/]
    [@b.formfoot]
      <input type="hidden" name="classroom.project.id" value="${classroom.project.id}"/>
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]