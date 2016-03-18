[#ftl]
[@b.head/]
[@b.grid items=adminclasses var="adminclass"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));

    var bar1=bar.addMenu("导入导出班级");
    bar1.addItem("导入班级","importStdOrAdminclass('adminclass')");
    bar1.addItem("下载模版","downloadTemplate('adminclass')");
    bar1.addItem("导出班级","exportData('adminclass')");
   [#-- 
    var bar2=bar.addMenu("导入导出班级学生");
    bar2.addItem("导入班级学生","importStdOrAdminclass('std')");
    bar2.addItem("下载模版","downloadTemplate('std')");
    bar2.addItem("导出班级学生","exportData('std')");
   --] 
    var bar3 = bar.addMenu("班级学生管理");
    bar3.addItem("维护班级学生","stdClassOperat()");
    bar3.addItem("计算最新班级人数", "totalStdCount()");
    bar3.addItem('打印学生名单', 'printStdList()');
  [/@]
  [@b.row]
    [@b.boxcol name="adminclass.id"/]
    [@b.col width="7%" property="grade" title="年级"]${adminclass.grade!}[/@]
    [@b.col width="8%" property="code" title="代码"]${adminclass.code}[/@]
    [@b.col width="20%" property="name" title="名称"][@b.a href="!info?id=${adminclass.id}"]${adminclass.name}[/@][/@]
    [@b.col width="10%" property="education" title="学历层次"]${(adminclass.education.name)!}[/@]
    [@b.col width="15%" property="department" title="院系"]${(adminclass.department.name)!}[/@]
    [@b.col width="20%" property="major" title="专业(方向)"]${(adminclass.major.name)!} ${(adminclass.direction.name)!}[/@]
    [@b.col width="10%" property="stdType" title="学生类别"]${(adminclass.stdType.name)!}[/@]
    [@b.col width="7%" property="stdCount" title="学籍人数"]${adminclass.stdCount!}[/@]
    [@b.col width="7%" property="instructor" title="辅导员"]${(adminclass.instructor.person.name)!}[/@]
  [/@]
[/@]
<form name="ImportExportForm" id="ImportExportForm" method="post" target="_blank"></form>
<script>
  //导入班级学生或者班级
  function importStdOrAdminclass(value){
    var form = document.ImportExportForm;
    form.action="${b.url('!importForm')}";
    if(value=='std'){
      bg.form.addInput(form,"templateType","std");
      bg.form.addInput(form,"importTitle","班级学生数据上传");
      bg.form.addInput(form,"display","班级学生信息模板");
      bg.form.addInput(form,"file","template/excel/班级学生导入数据模版.xls");
    }
    if(value=='adminclass'){
      bg.form.addInput(form,"templateType","adminclass");
      bg.form.addInput(form,"importTitle","班级数据上传");
          bg.form.addInput(form,"display","班级信息模板");
          bg.form.addInput(form,"file","template/excel/班级导入数据模版.xls");
    }
        form.target="_blank";
        bg.form.submit(form);
        form.target="adminclassListFrame";
  } 
  
  //导出班级学生或者班级
  function exportData(value){
    if (bg.input.getCheckBoxValues("adminclass.id") == "") {
      if(confirm("是否导出查询条件内的所有数据?")){
        getExportData(value);
      }
      return;
    }else{
      getExportData(value);
    }
  }
  
  function getExportData(value){
    if(value=='std'){
      bg.form.addInput(document.searchForm,"exportType",value);
      bg.form.addInput(document.searchForm,"adminclassIds",bg.input.getCheckBoxValues("adminclass.id"));
      bg.form.addInput(document.searchForm,"keys","adminclass.code,adminclass.name,adminclass.department.name,adminclass.major.name,adminclass.stdType.name,code,name,gender.name,studentJournal.status");
      bg.form.addInput(document.searchForm,"titles","班级代码,班级名称,班级院系,班级专业,班级学生类别,学生代码,学生姓名,学生性别,学籍状态");
      bg.form.addInput(document.searchForm,"fileName","班级学生列表");
    }
    if(value=='adminclass'){
      bg.form.addInput(document.searchForm,"exportType",value);
      bg.form.addInput(document.searchForm,"adminclassIds",bg.input.getCheckBoxValues("adminclass.id"));
      bg.form.addInput(document.searchForm,"keys","code,name,grade,project.name,department.name,education.name,major.name,direction.name,stdType.name,planCount,stdCount,createdAt,updatedAt");
      bg.form.addInput(document.searchForm,"titles","班级代码,班级名称,年级,教学项目,院系,学历层次,专业,方向,学生类别,计划人数,学籍有效人数,创建时间,修改时间");
      bg.form.addInput(document.searchForm,"fileName","班级信息");
    }
    bg.form.submit(document.searchForm, "${b.url('!export')}", '_blank');
  }
  
    //模版下载
  function downloadTemplate(value){
    var form=document.ImportExportForm;
    if(value=='std'){
      bg.form.addInput(form,"templateType","std");
    }
    if(value=='adminclass'){
      bg.form.addInput(form,"templateType","adminclass");
    }
    form.action="${b.url('!downloadAdminclassStdTemp')}";
    form.submit();
  }
  
    //维护班级学生
  function stdClassOperat(){
    var adminclassId=bg.input.getCheckBoxValues('adminclass.id');
    if(adminclassId==""||adminclassId.indexOf(',')!=-1){alert('请仅选择一个班级操作!');return;}
    bg.form.addInput(document.searchForm,"adminclassId",adminclassId);
    bg.form.submit(document.searchForm, "${b.url('!setClassStudentForm')}");
  }
  
  function printStdList() {
    var ids = bg.input.getCheckBoxValues("adminclass.id");
    if (ids == "") {
      alert("请选择至少一个班级")
      return;
    }
    bg.form.addInput(document.searchForm,"adminclassIds",ids);
    [#--bg.form.submit(document.searchForm, "${b.url('!batchPrint')}","_blank");--]
  }
</script>
[@b.foot/]
