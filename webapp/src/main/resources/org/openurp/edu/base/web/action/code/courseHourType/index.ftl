[#ftl]
[@b.head/]
[@b.toolbar title="课时类别代码"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="courseHourTypeSearchForm" action="!search" target="courseHourTypelist" title="ui.searchForm" theme="search"]
      [@b.textfields names="courseHourType.code;代码"/]
      [@b.textfields names="courseHourType.name;名称"/]
      <input type="hidden" name="orderBy" value="courseHourType.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="courseHourTypelist" href="!search?orderBy=courseHourType.code"/]
    </td>
  </tr>
</table>
[@b.foot/]