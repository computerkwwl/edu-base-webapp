[#ftl]
[@b.head/]
[@b.toolbar title="行政班级信息"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="searchForm" action="!search" target="adminclasslist" title="ui.searchForm" theme="search"]
      [@b.textfields names="adminclass.grade;年级"/]
      [@b.textfields names="adminclass.code;代码"/]
      [@b.textfields names="adminclass.name;名称"/]
      [@b.select style="width:100px" name="adminclass.department.id" label="所属部门" items=departments option="id,name" empty="..." /]
      [@b.select style="width:100px" name="adminclass.education.id" label="培养层次" items=educations option="id,name" empty="..." /]
      [@b.select style="width:100px" name="adminclass.campus.id" label="校区" items=campuses option="id,name" empty="..." /]
      <input type="hidden" name="orderBy" value="adminclass.code desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="adminclasslist" href="!search?orderBy=adminclass.code desc"/]
    </td>
  </tr>
</table>
[@b.foot/]