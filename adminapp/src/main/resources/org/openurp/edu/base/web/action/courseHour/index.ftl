[#ftl]
[@b.head/]
[@b.toolbar title="课程分类课时信息"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="courseHourSearchForm" action="!search" target="courseHourlist" title="ui.searchForm" theme="search"]
      [@b.select name="courseHour.course.id" label="对应课程" items=courses empty="..." style="width:100px"/]
      <input type="hidden" name="orderBy" value="courseHour.id"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="courseHourlist" href="!search?orderBy=courseHour.id"/]
    </td>
  </tr>
</table>
[@b.foot/]