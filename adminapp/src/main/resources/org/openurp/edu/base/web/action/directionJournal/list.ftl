[#ftl]
[@b.head/]
[@b.grid items=directionJournals var="directionJournal"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="20%" property="education" title="培养层次"]${(directionJournal.education.name)!}[/@]
    [@b.col width="20%" property="direction" title="专业方向"]${(directionJournal.direction.name)!}[/@]
    [@b.col width="20%" property="depart" title="部门"]${(directionJournal.depart.name)!}[/@]
    [@b.col width="15%" property="beginOn" title="生效时间"]${directionJournal.beginOn!}[/@]
    [@b.col width="15%" property="endOn" title="失效时间"]${directionJournal.endOn!}[/@]
    [@b.col width="20%" property="endOn" title="备注"]${directionJournal.remark!}[/@]
  [/@]
[/@]
[@b.foot/]
