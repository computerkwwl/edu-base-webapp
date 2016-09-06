[#ftl]
[@b.head /]
	[@b.form name="searchStdForm" id="searchStdForm" action='!addClassStudentList' method="post"]
		[@b.toolbar title="学生学号或姓名(重复或无效者被忽略)，多个学号或姓名可用空格、逗号、分号、回车分割"]
			bar.addItem("添加","newSubmit()");
		[/@]
		<p align="center">
		<textarea name="stdCodes" id="stdCodes" cols="100" rows="5">${stdCodes!}</textarea>
		</p>
		<input type="hidden" name="student.project.id" value="${adminclass.project.id}" />
		<input type="hidden" name="adminclassId" value="${(adminclassId)!}" />
		[@b.toolbar title="查询学号添加"]
			bar.addItem("单号","checkStd(1)");
			bar.addItem("双号","checkStd(0)");
			bar.addItem("添加学号","newStdCodes()");
		[/@]
		[@b.grid sortable="false" items=studentStates! var="ss" style='width:98%;' filterable='true']
			[@b.filter property="std.code"]<input type="text" style="width:90%;" name="studentState.std.code" value="${(studentState.std.code)!}"/>[/@]
			[@b.filter property="std.person.name"]<input type="text" style="width:90%;" name="studentState.std.person.name.formatedName" value="${(studentState.std.person.name.formatedName)!}"/>[/@]
			[@b.filter property="std.person.gender.name"]${(studentState.std.person.gender.name)!}[/@]
			[@b.filter property="grade"]<input type="text" style="width:90%;" name="student.grade" value="${(studentState.grade)!}"/>[/@]
			[@b.filter property="department.name"]<input type="text" style="width:90%;" name="studentState.department.name" value="${(studentState.department.name)!}"/>[/@]
			[@b.filter property="major.name"]<input type="text" style="width:90%;" name="studentState.major.name" value="${(studentState.major.name)!}"/>[/@]
			[@b.filter property="adminclass.name"]<input type="text" style="width:90%;" name="studentState.adminclass.name" value="${(studentState.adminclass.name)!}"/>[/@]
			[@b.row]
				[@b.boxcol/]
				[@b.col width="12%" property="std.code" title="学号"/]
				[@b.col width="10%" property="std.person.name" title="姓名"/]
				[@b.col width="8%" property="std.person.gender.name" title="性别"/]
				[@b.col width="10%" property="grade" title="年级"/]
				[@b.col width="20%" property="department.name" title="院系"/]
				[@b.col width="20%" property="major.name" title="专业"/]
				[@b.col width="20%" property="adminclass.name" title="班级"/]
			[/@]
		[/@]
	[/@]

<script type="text/javascript">
	function newSubmit(){
		var stdCodes=jQuery("#searchStdForm #stdCodes").val();
		var projectId=jQuery("#searchStdForm #student.project.id").val();
		var adminclassId=jQuery("#searchStdForm #adminclassId").val();
		if(!stdCodes){
			if(confirm("没有添加任何学生学号，放弃添加？")){
				jQuery.colorbox.close();
			}
			return;
		}
		bg.form.submit(document.searchStdForm, '${b.url("!addClassStudent.action")}', 'showErrorStd');
		jQuery.colorbox.close();
	}
	function newStdCodes(){
		jQuery("#searchStdForm :checkbox[name='std.id']").each(function(){
			var flag = jQuery(this).prop("checked");
			if(flag){
				var code=jQuery(this).parent().next("td").html();
				var codes=jQuery("#searchStdForm #stdCodes").val();
				if(codes.indexOf(code)<0)
					jQuery("#searchStdForm #stdCodes").val((codes==""?"":codes+",")+code);
			}
		});
	}
	
	function checkStd(type){
		jQuery("#searchStdForm :checkbox[name='std.id']").each(function(idx,item){
				jQuery.each(stdList.data,function(idx2,item2){
					if(item.value==item2.id){
						var code=item2.code.replace(/[\D]/g,'');
						var codeTypeToken=code.substring(code.length-1,code.length);						
						if(codeTypeToken%2==type){
							jQuery(item).prop("checked",true); 
							return false;
						}else{
							jQuery(item).prop("checked",false); 
						}					
					}else{
						jQuery(item).prop("checked",false); 
					}				
				});
				
		});
	}
	
	var stdList ={
	data:[
	[#list students! as std]{
		id : '${std.id}',
		code : '${std.code!}'}
	[#if std_has_next],[/#if]
	[/#list]]
	};

	
	
</script>
[@b.foot /]