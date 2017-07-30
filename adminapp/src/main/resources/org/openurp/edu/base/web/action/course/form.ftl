[#ftl]
[@b.head/]
[@b.toolbar title="修改课程"]bar.addBack();[/@]
[@b.tabs]
  [#assign sa][#if course.persisted]!update?id=${course.id}[#else]!save[/#if][/#assign]
  [@b.form action=sa theme="list"]
    [@b.textfield name="course.code" label="代码" value="${course.code!}" required="true" maxlength="20"/]
    [@b.textfield name="course.name" label="名称" value="${course.name!}" required="true" maxlength="20"/]
    [@b.textfield name="course.enName" label="英文名" value="${course.enName!}" maxlength="100" style="width:400px"/]
    [@b.select2 label="培养层次" name1st="educationId1st" name2nd="educationId2nd" style = "height:80px;width:152px"
      items1st=educations items2nd= course.educations
      option="id,name"  required="true" /]
    [@b.select name="course.category.id" label="课程种类代码" value="${(course.category.id)!}" required="true" 
               style="width:200px;" items=categories option="id,name" empty="..." /]
    [@b.textfield name="course.credits" label="学分" value="${course.credits!}" required="true" maxlength="20"/]
    [@b.textfield name="course.period" label="学时" value="${course.period!}" maxlength="100"/]
    [@b.textfield name="course.weekHour" label="周课时" value="${course.weekHour!}" required="true" maxlength="20"/]
    [@b.textfield name="course.weeks" label="周数" value="${course.weeks!}" maxlength="100"/]
    [@b.select name="course.department.id" label="院系" value="${(course.department.id)!}" required="true" 
               style="width:200px;" items=departments option="id,name" empty="..."/]
    [@b.datepicker name="course.beginOn" label="设立时间" value=course.beginOn! required="true" /]
    [@b.select name="course.courseType.id" label="建议课程类别" value="${(course.courseType.id)!}"  
               style="width:200px;" items=courseTypes option="id,name" empty="..." required="true"/]
    [@b.select name="course.examMode.id" label="考试方式" value="${(course.examMode.id)!}" required="true" 
               style="width:200px;" items=examModes option="id,name" empty="..."/]
    [@b.select name="course.markStyle.id" label="成绩记录方式" value="${(course.markStyle.id)!}" required="true" 
               style="width:200px;" items=markStyles option="id,name" empty="..."/]
    [@b.radios label="是否计算绩点"  name="course.calgp" value=course.calGp items="1:common.yes,0:common.no" required="true"/]
    [@b.select2 label="针对专业" name1st="majorsId1st" name2nd="majorsId2nd" 
      items1st=majors items2nd= course.majors
      option="id,name"/]
    [@b.select2 label="排除专业" name1st="xmajorsId1st" name2nd="xmajorsId2nd" 
      items1st=xmajors items2nd= course.xmajors 
      option="id,name"/]
    [@b.textarea name="course.remark" label="备注" value="${course.remark!}"  maxlength="100"/]
    [@b.formfoot]
      <input type="hidden" name="course.project.id" value="${course.project.id}"/>
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[/@]
[@b.foot/]