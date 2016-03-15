[#ftl]
[@b.head/]
[@b.toolbar title="课程信息"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">代码</td>
    <td class="content">${course.code}</td>
  </tr>
  <tr>
    <td class="title" width="20%">名称</td>
    <td class="content">${course.name}</td>
  </tr>
  <tr>
    <td class="title" width="20%">英文名</td>
    <td class="content">${course.enName!}</td>
  </tr>
  [#if course.educations ??]
  <tr>
    <td class="title" width="20%">学历层次</td>
    <td class="content">
      [#list course.educations as education]
        ${education.name}
        [#if education_has_next],[/#if]
      [/#list]
    </td>
  </tr>
  [/#if]
  <tr>
    <td class="title" width="20%">课程种类代码</td>
    <td class="content">${(course.category.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">学分</td>
    <td class="content">${course.credits!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">学时</td>
    <td class="content">${course.period!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">周课时</td>
    <td class="content">${course.weekHour!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">周数</td>
    <td class="content">${course.weeks!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">院系</td>
    <td class="content">${(course.department.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">建议课程类别</td>
    <td class="content">${course.courseType!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">考试方式</td>
    <td class="content">${course.examMode!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">成绩记录方式</td>
    <td class="content">${course.markStyle!}</td>
  </tr>
  <tr>
   <td class="title" width="20%">是否计算绩点</td>
   <td class="content">${(course.calgp?string("是","否"))!}</td>
  </tr>
  [#if course.majors ??]
  <tr>
    <td class="title" width="20%">学历层次</td>
    <td class="content">
      [#list course.majors as major]
        ${major.name}
        [#if major_has_next],[/#if]
      [/#list]
    </td>
  </tr>
  [/#if]
  [#if course.xmajors ??]
  <tr>
    <td class="title" width="20%">学历层次</td>
    <td class="content">
      [#list course.xmajors as major]
        ${major.name}
        [#if major_has_next],[/#if]
      [/#list]
    </td>
  </tr>
  [/#if]
  <tr>
    <td class="title" width="20%">生效时间</td>
    <td class="content" >${course.beginOn!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">失效时间</td>
    <td class="content" >${course.endOn!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">备注</td>
    <td class="content">${course.remark!}</td>
  </tr>
</table>

[@b.foot/]