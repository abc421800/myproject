<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色添加</title>
<jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
<style>
.permissionName{
	color:black;
	border: 1px solid #ced2d6;
	margin: 2px 2px 2px 2px;
	padding: 5px 10px;
	background-color: #dde3e8;
	border-radius: 2px;
	display: inline-block;
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
		<input type="hidden" value="${obj.id}" name="id" >
	</c:if>
	<input type="hidden" class="hidden-perId" name="permissionId" value="${obj.permissionId}">
	<div class="col-md-6 col-xs-6" >
        <table class="table_edit" cellspacing="0" cellpadding="0" style="height:385px;">
            <tbody>
                <tr style="height:36px;">
                  <td style="background-color: #dde3e8;border: 1px solid #ced2d6;" colspan="4">
              			<span class="glyphicon glyphicon-user"></span> 角色信息
                   </td> 
                </tr>
                <tr>
                    <td class="bgcolor" style="width: 25%;"><strong>*</strong>角色名称：</td>
                    <td colspan="3">
                        <input type="text" id="name" name="name"  value="${obj.name }" placeholder="请输入角色名称" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="bgcolor" ><strong>&nbsp;</strong>是否禁用：</td>
                    <td style="width: 25%;">
                        <select name="status"  class="custom-select">
                        	<c:forTokens items="可用,禁用" delims="," var="status">
                        		<option value="${status}" <c:if test="${!empty obj.status and obj.status eq status}">selected="selected"</c:if> >${status}</option>
                        	</c:forTokens>
                        </select>
                    </td>
                    <td class="bgcolor" style="width: 20%;">排序号：</td>
                    <td>
                        <input type="text" name="num" onkeyup ="value=value.replace(/[^\d]/g,'')" value="${obj.num }" placeholder="请输入排序号，最小的排在最前面" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>描述：</td>
                    <td colspan="3">
                        <textarea placeholder="描述内容" name="description" cols="5" rows="2">${obj.description}</textarea>
                    </td>
                </tr>
                <tr style="height:165px">
               		 <td class="bgcolor"><strong>&nbsp;</strong>权限：</td>
                    <td  colspan="3" style="text-align: left;padding:0 0 0 14px;">
	                    <div class="box-body" style="overflow:auto;height:165px">
	                    	<span id="permissionName"></span>
	                    </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align: center">
                        <div id="btn-group">
                        <shiro:hasPermission name="role:update">
                            <input type="button" id="save" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
                            <input type="button" id="cancel" class="btn btn-success" value="返回">
                        </shiro:hasPermission>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
      </div>
      <div class="col-md-6 col-xs-6" >
      	<table class="table_edit" cellspacing="0" cellpadding="0" style="height:384px;">
            <tbody>
                <tr style="height:36px;">
                   <td style="background-color: #dde3e8;border: 1px solid #ced2d6;"><span class="glyphicon glyphicon-th-list"></span> 权限菜单</td>
                </tr>
                <tr style="height:348px">
                	<td style="vertical-align:top;">
                    	<div  class="box-body" style="height:348px;padding-top:3px;overflow-y:auto;">
							<ul id="permissionTree" ></ul>
						 </div>
                	</td>
                </tr>
               
            </tbody>
        </table>
      </div>
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
    var url="${url}";
    var permissionTree=$("#permissionTree");
	$(function(){
		$("#save").click(function(){
			var name=$("#name").val();
			if(name==""){
	         	$.messager.alert({title:'温馨提示', msg:'请填写角色名称 ！', icon: 'error', top:100});
	         	return;
	     	}
			var permissionIds=getChecked();
    		$('.hidden-perId').val(permissionIds);
			$.ajax({
				  url: url,
				  dataType: "json",
				  type:"post",
				  async: false,
				  data: $("#formId").serialize(),
				  success: function(result){
					  if(result.status==200){
						  url="/sysRole/upd";
					  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
					  	  var json1={tabTitle:'角色列表',url:'/forward_sys_roleList'};
						  window.parent.refreshTab(json1);
					  }else{
							$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
					  }
				  }
			});
		});
		
		$("#cancel").click(function(){
    		window.parent.closeWindow("修改角色");
			window.parent.closeWindow("添加角色");
    	})
	});
</script>
<script src="${pageContext.request.contextPath}/res/js/sys/permission/permission-tree.js"></script>
</body>
</html>