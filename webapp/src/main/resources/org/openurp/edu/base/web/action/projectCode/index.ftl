[#ftl]
[@b.head/]
[@b.toolbar title="项目基础代码配置"/]
<table class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="projectCodeBeanSearchForm" action="!search" target="projectCodeBeanlist" title="ui.searchForm" theme="search"]
      
      [@b.textfields names="projectCodeBean.project.name;项目名称"/]
      <input type="hidden" name="orderBy" value="projectCodeBean.project.name"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="projectCodeBeanlist" href="!search?orderBy=projectCodeBean.id"/]
    </td>
  </tr>
</table>
[@b.foot/]