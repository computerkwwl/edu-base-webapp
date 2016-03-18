[#ftl]
[@b.head/]
[@b.toolbar title="行政班级信息"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="searchForm" action="!search" target="adminclasslist" title="ui.searchForm" theme="search"]
      [@b.textfields names="adminclass.grade;年级"/]
      [@b.textfields names="adminclass.name;名称"/]
      <input type="hidden" name="orderBy" value="adminclass.code desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="adminclasslist" href="!search?orderBy=adminclass.code desc"/]
    </td>
  </tr>
</table>
[@b.foot/]