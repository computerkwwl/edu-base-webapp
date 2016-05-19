[#ftl]
[@b.head/]
[@b.toolbar title="教师信息"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="teacherSearchForm" action="!search" target="teacherlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="teacher.person.code;代码"/]
      [@b.textfields names="teacher.person.name;名称"/]
      [@b.select name="teacher.department.id" label="部门" items=departments empty="..." style="width:100px"/]
      <input type="hidden" name="orderBy" value="teacher.user.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="teacherlist" href="!search?orderBy=teacher.user.code"/]
    </td>
  </tr>
</table>
[@b.foot/]