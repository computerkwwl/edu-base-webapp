[#ftl]
[@b.head/]
[@b.toolbar title="教师信息"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="teacherSearchForm" action="!search" target="teacherlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="teacher.user.code;工号"/]
      [@b.textfields names="teacher.user.name;姓名"/]
      [@b.select name="teacher.user.department.id" label="部门" items=departments empty="..." style="width:100px"/]
      [@b.select name="teacher.teacherType.id" label="类型" items=teacherTypes empty="..." style="width:100px"/]
      [@b.select style="width:100px" name="active" label="是否有效" items={"1":"是", "0":"否"} empty="..." /]
      <input type="hidden" name="orderBy" value="teacher.user.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="teacherlist" href="!search?orderBy=teacher.user.code"/]</td>
  </tr>
</table>
[@b.foot/]