<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户添加</title>
<jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
<style>
.combo-panel
 {

    overflow: auto;

}
</style>

</head>
<body>
<div id="loading-wrapper">
    <div id="loader">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
        <div class="line4"></div>
        <div class="line5"></div>
        <div class="line6"></div>
    </div>
</div>
<form action="" id="formId">
	<c:if test="${!empty obj.id}">
		<input type="hidden" value="${obj.id}" name="id">
	</c:if>
	<table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
	<tbody>
           	<tr>
           		<td class="bgcolor"><strong>*</strong>账号：</td>
           		<td>
                   <input type="text" id="account" name="account" value="${obj.account}" placeholder="请输入账号" >
           		</td>
           		<td class="bgcolor"><strong>*</strong>姓名：</td>
           		<td>
                	<input type="text" style="width:100%;" id="name" name="name" value="${obj.name}" placeholder="请输入姓名" >
            	</td>
        	</tr>
	        <tr>
	            <td class="bgcolor"><strong></strong>性别：</td>
	            <td style="text-align: left">
	                <select id="sex" name="sex" class="custom-select" style="width:100px;">
	                    <c:forTokens items="男,女" delims="," var="i">
	                    	<option <c:if test="${i eq obj.sex}">selected = "selected"</c:if>  value="${i}">${i}</option>
	                 	</c:forTokens>
	                </select>
	            </td>
	            <td class="bgcolor"><strong>*</strong>电话：</td>
	            <td >
	                <input type="text" id="phone" name="phone" value="${obj.phone}" placeholder="请输入电话" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" >
	            </td>
	        </tr>
	        <tr>
	        	<td class="bgcolor"><strong></strong>邮箱：</td>
	            <td>
	            	<div class="parentCls">
	                <input type="text" id="email" class='inputElem' name="email" value="${obj.email}" placeholder="请输入邮箱" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9+-.+a-z+A-Z+@+_]+/,'');}).call(this)" onblur="this.v();" >
	           		</div>
	            </td>
	            <td class="bgcolor"><strong></strong>微信号：</td>
	            <td >
	                <input type="text" id="wechat" name="wechat" value="${obj.wechat}" placeholder="请输入微信号" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9+a-z+A-Z+_]+/,'');}).call(this)" onblur="this.v();" >
	            </td>
	        </tr>
	        <tr>
	         	<td class="bgcolor"><strong>*</strong>部门：</td>
	            <td >
	            	<input type="hidden" id="hidden-orgId" name="orgId"  value="${obj.orgId}"/>
	                <input type="text" id="orgCombotree" name="org" style="width:100%;">
	            </td>
	            <td class="bgcolor"><strong>*</strong>角色：</td>
	            <td >
                    <input type="hidden" id="hidden-roleId" name="roleId" value="${obj.roleId}" />		
	            	<input id='roleIds' type="text" value="${obj.roleId}" style="width:100%;">
	            </td>
	        </tr>
	        <tr style="height:40px">
	            <td colspan="4" style="text-align: center">
	        	<shiro:hasPermission name="user:update">
	                <input type="button" id="save" class="btn btn-primary" onclick="" value="保存">&nbsp;&nbsp;
	                <input type="button" onclick="parent.closeWindow('修改用户');parent.closeWindow('添加用户')" class="btn btn-success" value="取消">
	            </shiro:hasPermission>
	            </td>
	        </tr>
	</tbody>
</table>
</form>
<script>
    <!-- 禁用所有Form表单 -->
    function disableForm(formId,isDisabled) {
        var attr="disable";
        if(!isDisabled){
            attr="enable";
        }
        $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] select").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);
    }
    	var orgId="${obj.orgId}";
    	var roleId="${obj.roleId}"
    	var url="${url}";
		$(function(){
			$("#save").click(function(){
			var account=$("#account").val();
			var name=$("#name").val();
			var department=$("#department").val();
			var phone=$("#phone").val();
			//获取部门
			var hidden_orgId=$("#orgCombotree").combotree("getValue");
			$("#hidden-orgId").val(hidden_orgId);
			//获取角色
			var hidden_roleId=$('#roleIds').combobox('getValues');
			$("#hidden-roleId").val(hidden_roleId);
			if(account==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写账号 ！', icon: 'error', top:100});
               	return;
           	}
			if(name==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写姓名 ！', icon: 'error', top:100});
               	return;
           	}
			if(department==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写部门 ！', icon: 'error', top:100});
               	return;
           	}
			if(phone==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写电话 ！', icon: 'error', top:100});
               	return;
           	}
			if(hidden_orgId=="" || hidden_orgId=='-1' ){
               	$.messager.alert({title:'温馨提示', msg:'请选择部门！', icon: 'error', top:100});
               	return;
           	}
			if(hidden_roleId=="" || hidden_roleId=='请选择' ){
               	$.messager.alert({title:'温馨提示', msg:'请选择角色！', icon: 'error', top:100});
               	return;
           	}
				$.ajax({
				  	url: url,
				  	dataType: "json",
				  	type:"post",
				  	async: false,
				  	data: $("#formId").serialize(),
				  	success: function(result){
					  	if(result.status==200){
					  	  url="/sysUser/upd";
					  	  var json1={tabTitle:'用户列表',url:'/forward_sys_userList'};
						  window.parent.refreshTab(json1);
					  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
					  	}else if(result.status==600){
					  		$.messager.alert("温馨提示","系统已有该账号，账号不能重名!", "error");
					  	}else{
							$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
					  	}
				  	}
				});
			});
			
			/* 角色 */
		    $('#roleIds').combobox({
		        url:'/sysRole/roleList',
		        valueField:'id',
		        textField:'name',
		    	editable:true,
		        multiple:true,
		        onLoadSuccess: function(none){
		        	if(typeof(roleId)=="undefined" || roleId=="" || roleId=="请选择"){
		        		$('#roleIds').combobox('setText','请选择');
		        	}
		    	},
		        onSelect: function(record){
		    	},
		    	onUnselect: function(record){
		    		console.log(record);
		    	}
		    });
			
			
		});
		
		
		
</script>
<script src="${pageContext.request.contextPath}/res/js/sys/org/org-combotree.js"></script>
</body>
</html>