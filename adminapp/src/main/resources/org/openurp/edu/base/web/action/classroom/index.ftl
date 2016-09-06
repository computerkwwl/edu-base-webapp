[#ftl]
[@b.head/]
[@b.toolbar title="教室基本信息"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="classroomSearchForm" action="!search" target="classroomlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="classroom.code;代码"/]
      [@b.textfields names="classroom.name;名称"/]
      [@b.select style="width:100px" name="classroom.roomType.id" label="教室类型" items=roomTypes option="id,name" empty="..." /]
      <input type="hidden" name="orderBy" value="classroom.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="classroomlist" href="!search?orderBy=classroom.code"/]
    </td>
  </tr>
</table>
[@b.foot/]
