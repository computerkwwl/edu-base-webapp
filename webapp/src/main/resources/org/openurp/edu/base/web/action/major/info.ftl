[#ftl]
[@b.head/]
[@b.toolbar title="专业信息"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">代码</td>
    <td class="content">${major.code}</td>
  </tr>
  <tr>
    <td class="title" width="20%">名称</td>
    <td class="content">${major.name}</td>
  </tr>
  <tr>
    <td class="title" width="20%">英文名</td>
    <td class="content">${major.enName!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">简称</td>
    <td class="content">${major.shortName!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">方向列表</td>
    <td class="content">[#list major.directions as d]${d.name}(${d.code}) [/#list]
    </td>
  </tr>
  <tr>
    <td class="title" width="20%">生效时间</td>
    <td class="content" >${major.beginOn!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">失效时间</td>
    <td class="content" >${major.endOn!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">备注</td>
    <td class="content">${major.remark!}</td>
  </tr>
</table>

[@b.foot/]