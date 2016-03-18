[#ftl/]
[@b.head/]
  <script type="text/javascript" src="${base}/static/jquery-colorbox/jquery-colorbox.js"></script>
  <link rel="stylesheet" href="${base}/static/jquery-colorbox/colorbox.css" type="text/css"/>
	[@b.toolbar title="维护班级学生"]
		bar.addItem("移出", "removeStd()");
		bar.addItem("添加", "newStd()");
		bar.addItem("保存", "saveStd()");
		bar.addItem("重置", "resetStd()");
		jQuery(function(){
			var model=" <button onclick='#click'>#title</button> ";
			jQuery("#showButton").append(model.replace("#click","removeStd()").replace("#title","移出"))
				.append(model.replace("#click","newStd()").replace("#title","添加"))
				.append(model.replace("#click","saveStd()").replace("#title","保存"))
				.append(model.replace("#click","resetStd()").replace("#title","重置"));
				
		});
		function newStd(){
			jQuery("#searchStdForm #stdCodes").val("");
			jQuery.colorbox({transition : 'none', title : "添加班级学生", overlayClose : false, width:"800px", inline:true, href:"#doAddDiv"});
		}
		function removeStd(){
			var ids = bg.input.getCheckBoxValues("student.id");
			if (ids == "") {
				alert("请选择至少一个学生")
				return;
			}
			if(confirm("确定要从本班删除选中的学生？")){
				jQuery("#stdShow :checkbox:checked").each(function(){
					jQuery(this).parent().parent().hide();
					jQuery(this).prop("name","student.id.remove");
				});
			}
		}
		function saveStd(){
			var studentIds="";
			jQuery("#stdShow :checkbox[name='student.id']").each(function(){
				studentIds=studentIds+","+jQuery(this).val();
			});
			var studentRemoveIds="";
			jQuery("#stdShow :checkbox[name='student.id.remove']").each(function(){
				studentRemoveIds=studentRemoveIds+","+jQuery(this).val();
			});
			var form=document.saveClassStudent;
			bg.form.addInput(form, 'studentIds', studentIds);
			bg.form.addInput(form, 'studentRemoveIds', studentRemoveIds);
			bg.form.submit(form, "${b.url('!saveClassStudent')}", 'adminclassListFrame');
		}
		function resetStd(){
			jQuery("#stdShow .grid .gridtable tbody tr[title='添加']").remove();
			jQuery("#stdShow :checkbox[name='student.id.remove']").each(function(){
				jQuery(this).parent().parent().show();
				jQuery(this).prop("name","student.id");
			});
		}
	[/@]
	<form action="${b.url('!saveClassStudent')}" method="post" name="saveClassStudent">
		<input type="hidden" name="adminclassId" value="${adminclass.id}"/>
		<input type="hidden" name="studentIds" value=""/>
		<input type="hidden" name="studentRemoveIds" value=""/>
		<table class="formTable" width="90%" align="center">
			<tr><td align="center" colspan="4" class="index_view"><b>班级基本信息</b></td></tr>
			<tr>
				<td class="title" width="20%">班级名称:</td>
				<td class="brightStyle" width="30%">${(adminclass.name)!}</td>
				<td class="title" width="20%">班级代码:</td>
				<td class="brightStyle" width="30%">${(adminclass.code)!}</td>
			</tr>
			<tr>
				<td class="title" width="20%">院系:</td>
				<td width="30%">${(adminclass.department.name)!}</td>
				<td class="title" width="20%">学生类别:</td>
				<td width="30%">${(adminclass.stdType.name)!}</td>
			</tr>
			<tr>
				<td class="title" width="20%">专业:</td>
				<td width="30%">${(adminclass.major.name)!}</td>
				<td class="title" width="20%">方向:</td>
				<td width="30%">${(adminclass.direction.name)!}</td>
			</tr>
			<tr>
				<td class="title" width="20%">年级:</td>
				<td width="30%">${(adminclass.grade)!}</td>
				<td class="title" width="20%">计划人数/实际人数:</td>
				<td width="30%">${(adminclass.planCount)!}/${(adminclass.stdCount)!}</td>
			</tr>
			<tr>
				<td class="title" width="20%">创建日期:</td>
				<td width="30%">${(adminclass.createdAt)!}</td>
				<td class="title" width="20%">修改日期:</td>
				<td width="30%">${(adminclass.updatedAt)!}</td>
			</tr>
		</table>
		<div style="width:90%;margin-left:auto;margin-right:auto;background-color:#E1ECFF;text-align:center">
			<b>班级学生列表</b>
		</div>
		<div style="width:90%;margin-left:auto;margin-right:auto" id="stdShow">
			[@b.grid items=students var="student" sortable="false"]
		       	[@b.row]
		       		[@b.boxcol /]
					[@b.col property="code" title="学号" width="20%" ]
						${(student.code)!}
					[/@]
					[@b.col property="name" title="姓名" width="20%"]
		     	  		${(student.name)!}
		     	    [/@]
					[@b.col property="department.name" title="院系" width="25%" ]
						${(student.department.name)!}
					[/@]
					[@b.col property="stdType.name" title="学生类别" width="15%" ]
						${(student.stdType.name)!}
					[/@]
					[@b.col property="registed" title="是否有学籍" ]
						${((student.registed)?string("是","<font color='red'否</font>"))!}
					[/@]
		    	[/@]
			[/@]
		</div>
	</form>
	<div style="width:90%;margin-left:auto;margin-right:auto" id="showButton" align="center" >
	</div>
	<div style="width:90%;margin-left:auto;margin-right:auto;color:red" id="showErrorStd" >
	</div>
	[#if (students!?size)==0]
		<div style="width:60%;margin-left:auto;margin-right:auto">
			<div align="center" style="color:#666666;background:#E1ECFF;"><b>该班级没有学生!</b></div>
		</div>
	[/#if]
	<div style="display:none">
		[@b.div id='doAddDiv' href="!addClassStudentList?student.project.id=${adminclass.project.id}&first=1&adminclassId=${adminclass.id}" /]
	</div>
	

[@b.foot /]