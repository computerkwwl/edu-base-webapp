[#ftl/]
[@b.head/]
[@b.toolbar title="班级信息"]bar.addBack();[/@]
<table class="formTable" style="width:70%;margin:10px auto auto;">
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
		<td class="title" width="20%">计划人数:</td>
		<td width="30%">${(adminclass.planCount)!}</td>
		<td class="title" width="20%">学籍有效人数:</td>
		<td width="30%">${(adminclass.stdCount)!}</td>
	</tr>
	<tr>
    <td class="title" width="20%">辅导员</td>
    <td width="30%">[#if adminclass.instructor.user??]${adminclass.instructor.user.name}(${adminclass.instructor.user.code})</td>
		<td class="title" width="20%">更新日期:</td>
		<td width="30%">${(adminclass.updatedAt?string('yyyy-MM-dd HH:mm:ss'))!}</td>
	</tr>
	<tr>
		<td class="title" width="20%">生效日期:</td>
		<td width="30%">${adminclass.beginOn?string('yyyy-MM-dd')}</td>
		<td class="title" width="20%">失效日期:</td>
		<td width="30%">${(adminclass.endOn?string('yyyy-MM-dd'))!}</td>
	</tr>
</table>
<div style="width:70%;margin:auto;background-color:#E1ECFF;text-align:center">
	<b>班级学生列表</b>
</div>
	<div style="width:70%;margin:auto;">
		[#list students! as student]
			[#if student_index%3==0]
				<table align="center" width="100%" style="border:1px solid #A6C9E2;">
					<tr>
			[/#if]	
		    	<td width="30%" style="background-color:#e8eefa;">
					<table width="100%">
				      <tr>
				        <td rowspan="3" width="80px"><img src="${base}/avatar/user.action?user.name=${(student.code)!}" width="40px" height="55px"/></td>
				      </tr>
				      <tr>
				           [#--<td><a href="#" onClick="infoStd(${(student.id)!})"  title="查看学生详细信息">${(student.code)!}</a></td>--]
                   <td>${(student.code)!}</td>
				      </tr>
				      <tr>
				           <td>
					           ${(student.person.name)!}&nbsp;
					           ${(student.person.gender.name)!}<br>
					           [#if status[student.code]?? && status[student.code].inschool]
					           		在校
					           [/#if]
					           [#if status[student.code]?? && !status[student.code].inschool]
					           		<font color="red">${(status[student.code].status.name)!}</font>
					           [/#if]
				           </td>
				      </tr>
			 		</table>
			 	</td>
			[#if student_index%3==2]
					</tr>
				</table>
			[/#if]
			[#if (students?size)%3==1&&student_index==(students?size-1)]
					</tr>
				</table>
			[/#if]
			[#if (students?size)%3==2&&student_index==(students?size-1)]
						<td width="30%" style="background-color:#e8eefa;">
							<table width="100%">
						      <tr><td>&nbsp;</td></tr>
						      <tr><td>&nbsp;</td></tr>
						      <tr><td>&nbsp;</td></tr>
					 		</table>
					 	</td>
			 		</tr>
				</table>
			[/#if]
		[/#list]
		[#if (students!?size)==0]
			<div align="center" style="color:#666666;background:#E1ECFF;"><b>该班级没有学生!</b></div>
		[/#if]
	</div>
<script>
	function infoStd(stdId){
		bg.Go("${base}/studentSearch!info.action?studentId="+stdId,"_blank");
	}
</script>
[@b.foot /]