[#ftl]
[@b.head/]
[@b.toolbar title="修改学籍信息"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="基本信息"]
  [#assign sa][#if student.persisted]!update?id=${student.id!}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.field label="学号"]${student.code!}[/@]
    [@b.field label="姓名"]${(student.person.name)!}[/@]
    [@b.select name="student.stdType.id" label="学生类别" value="${(student.stdType.id)!}" required="true" 
               style="width:200px;" items=type1s option="id,name" empty="..."/]
    [@b.select name="student.state.major.id" label="学籍状态" value="${(student.state.id)!}" required="true" 
               style="width:200px;" items=states option="id,name" empty="..."/]      
    [@b.select name="student.department.id" label="行政管理院系" value="${(student.department.id)!}"  
               style="width:200px;" items=departments option="id,name" empty="..."/]    
    [@b.select name="student.state.major.id" label="专业" value="${(student.state.major.id)!}" required="true" 
               style="width:200px;" items=majors option="id,name" empty="..."/]    
    [@b.select name="student.direction.id" label="专业方向" value="${(student.direction.id)!}" 
               style="width:200px;" items=directions option="id,name" empty="..."/]    
    [@b.select name="student.campus.id" label="校区" value="${(student.campus.id)!}" required="true" 
               style="width:200px;" items=campuse option="id,name" empty="..."/]
    [@b.textfield name="student.duration" label="学制" value="${student.duration!}" required="true" /]
    [@b.radios label="是否有学籍"  name="student.registed" value=student.registed items="1:common.yes,0:common.no"/]
    [@b.textfield name="student.enrollOn" label="入学报到日期" value="${student.enrollOn!}"/]
    [@b.textfield name="student.registOn" label="学籍生效日期" value="${student.registOn!}"/]
    [@b.textfield name="student.graduateOn" label="预计毕业日期" value="${student.graduateOn!}"/]
    [@b.textfield name="student.remark" label="备注" value="${student.remark!}"/]    
    [@b.select name="student.adminclass.id" label="行政班级" value="${(student.adminclass.id)!}" required="true" 
               style="width:200px;" items=adminclasses option="id,name" empty="..."/]
    [@b.select name="student.studyType.id" label="学习形式" value="${(student.studyType.id)!}" 
               style="width:200px;" items=studyTypes option="id,name" empty="..."/]    
    [@b.select name="student.tutor.id" label="导师" value="${(student.tutor.id)!}" 
               style="width:200px;" items=tutors option="id,name" empty="..."/]
    [@b.select2 label="学生标签" name1st="labelsId1st" name2nd="labelsId2nd" 
      items1st=labels items2nd= student.labels.values option="id,name"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
  [/@]
[/@]
[/@]
  [#if student.persisted]
  [@b.tab label="建设过程"]
  [@b.div href="student-state!search?studentState.student.id=${student.id}"/]
    [/@]
  [/#if]
[/@]
[@b.foot/]