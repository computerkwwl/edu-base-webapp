[#ftl]
[@b.head/]
[@b.toolbar title="课程分类课时信息"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">对应课程</td>
    <td class="content">${(courseHour.course.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">学时</td>
    <td class="content">${courseHour.period!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">周课时</td>
    <td class="content" >${courseHour.weekHour!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">周数</td>
    <td class="content" >${courseHour.weeks!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">课时类别代码</td>
    <td class="content">${courseHour.hourType.name!}</td>
  </tr>
</table>

[@b.foot/]