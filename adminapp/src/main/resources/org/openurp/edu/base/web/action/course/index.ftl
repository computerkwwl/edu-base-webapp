[#ftl]
[@b.head/]
[@b.toolbar title="课程"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="courseSearchForm" action="!search" target="courselist" title="ui.searchForm" theme="search"]
      [@b.textfields names="course.code;代码"/]
      [@b.textfields names="course.name;名称"/]
      [@b.select style="width:100px" name="course.courseType.id" label="课程类别" items=courseTypes option="id,name" empty="..." /]
      [@b.select style="width:100px" name="course.department.id" label="所属部门" items=departments option="id,name" empty="..." /]
      <input type="hidden" name="orderBy" value="course.code"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="courselist" href="!search?orderBy=course.code"/]
    </td>
  </tr>
</table>
[@b.foot/]