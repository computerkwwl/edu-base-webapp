[#ftl]
[@b.head/]
[@b.grid items=majorDisciplines var="majorDiscipline"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="major" title="专业"]${(majorDiscipline.major.name)!}[/@]
    [@b.col width="15%" property="category" title="学科门类"]${(majorDiscipline.category.name)!}[/@]
    [@b.col width="15%" property="disciplineCode" title="教育部代码"]${majorDiscipline.disciplineCode!}[/@]
    [@b.col width="10%" property="beginOn" title="生效时间"]${majorDiscipline.beginOn!}[/@]
    [@b.col width="10%" property="endOn" title="失效时间"]${majorDiscipline.endOn!}[/@]
    [@b.col width="10%" property="endOn" title="备注"]${majorDiscipline.remark!}[/@]
  [/@]
[/@]
[@b.foot/]
