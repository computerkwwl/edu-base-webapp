[#ftl]
[@b.head/]
[@b.toolbar title="项目基础代码配置信息"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
   <tr>
    <td class="title" width="20%">项目名称</td>
    <td class="content">${projectCodeBean.project.name!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">代码元</td>
    <td class="content">${projectCodeBean.meta.name!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">代码id</td>
    <td class="content">${projectCodeBean.codeId!}</td>
  </tr>
</table>

[@b.foot/]