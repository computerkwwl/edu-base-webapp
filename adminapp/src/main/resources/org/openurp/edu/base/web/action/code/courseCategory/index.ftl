[#ftl]
[@b.head/]
[@b.toolbar title="课程种类"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="courseCategorySearchForm" action="!search" target="courseCategorylist" title="ui.searchForm" theme="search"]
      [@b.textfields names="courseCategory.code;代码"/]
      [@b.textfields names="courseCategory.name;名称"/]
      <input type="hidden" name="orderBy" value="courseCategory.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="courseCategorylist" href="!search?orderBy=courseCategory.code"/]
    </td>
  </tr>
</table>
[@b.foot/]