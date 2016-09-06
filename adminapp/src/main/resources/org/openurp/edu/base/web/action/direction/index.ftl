[#ftl]
[@b.head/]
[@b.toolbar title="专业方向"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="directionSearchForm" action="!search" target="directionlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="direction.code;代码"/]
      [@b.textfields names="direction.name;名称"/]
      [@b.select name="direction.major.id" label="专业" items=majors empty="..." style="width:100px"/]
      <input type="hidden" name="orderBy" value="direction.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="directionlist" href="!search?orderBy=direction.code"/]
    </td>
  </tr>
</table>
[@b.foot/]