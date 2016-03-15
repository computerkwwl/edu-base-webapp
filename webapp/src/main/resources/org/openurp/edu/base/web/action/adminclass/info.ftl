[#ftl]
[@b.head/]
[@b.toolbar title="行政班级信息"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">年级</td>
    <td class="content">${adminclass.grade}</td>
  </tr>
  <tr>
    <td class="title" width="20%">代码</td>
    <td class="content">${adminclass.code}</td>
  </tr>
  <tr>
    <td class="title" width="20%">名称</td>
    <td class="content">${adminclass.name}</td>
  </tr>
  <tr>
    <td class="title" width="20%">简称</td>
    <td class="content">${adminclass.shortName!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">院系</td>
    <td class="content">${(adminclass.department.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">专业(方向)</td>
    <td class="content">${(adminclass.major.name)!} ${(adminclass.direction.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">学生类别</td>
    <td class="content">${(adminclass.stdType.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">学籍有效人数</td>
    <td class="content">${adminclass.stdCount!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">辅导员</td>
    <td class="content">${(adminclass.instructor.person.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">生效时间</td>
    <td class="content" >${adminclass.beginOn!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">失效时间</td>
    <td class="content" >${adminclass.endOn!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">备注</td>
    <td class="content">${adminclass.remark!}</td>
  </tr>
</table>

[@b.foot/]