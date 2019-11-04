<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>驻场企业</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
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
	<div class="row">
	<form action="">
    	<div class="filter">
        	<div class="form-inline">
            	<div class="form-group">
                	<label for="">驻场企业名称：</label>
                	<input type="text" id="name" value="" placeholder="请输入驻场企业名称关键字" class="form-control input-sm" style="width: 200px">
            	</div>
            	<div class="form-group">
                	<label for="">企业类型：</label>
                	<select class="form-control input-sm" onchange="searchCon()" id="type" style="width: 100px">
                    	<option value="">请选择</option>
                        <option value="造价咨询">造价咨询</option>
                        <option value="招标代理">招标代理</option>
                        <option value="工程咨询">工程咨询</option>
                        <option value="工程监理">工程监理</option>
                        <option value="勘察设计">勘察设计</option>
                        <option value="建材供货商">建材供货商</option>
                        <option value="检测机构">检测机构</option>
                        <option value="其他">其他</option>
                	</select>
            	</div>
            	<div class="form-group">
                <label for="">服务时间：</label>
                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                <label for="">至</label>
                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            </div>
            	<div class="form-group">
                	<label for="">是否有效：</label>
                	<select class="form-control input-sm" onchange="searchCon()" id="effectiveFlag" style="width: 100px">
                    	<option value="">请选择</option>
                    	<option value="有效" >有效</option>
                    	<option value="无效" >无效</option>
                	</select>
            	</div>
            	<div class="form-group">
                	<input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchCon()">&nbsp;
                	<input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="Refresh()">&nbsp;
                	<input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
                	<!-- <input type="button" name="reset" value="驻场企业导入" class="btn btn-warning" onclick="importReg()">&nbsp;
                	<input type="button" name="reset" value="驻场人员导入" class="btn btn-warning" onclick="importPer()">&nbsp; -->
                	
            	</div>
        	</div>
    	</div>
    	<a href="javascript:void(0);" class="switchBtn"></a>
    	</form>
	</div>
	<div class="row">
    	<table id="register" style="width: 100%;">
    	</table>
    	<div id="tit1" >
    		<shiro:hasPermission name="workEnterprise:add">
        		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="add()">添加</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="workEnterprise:delete">
        		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
        	</shiro:hasPermission>
        	<span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    	</div>
	</div>

<div id="importWorkEnt"></div>
<div id="importPerson"></div>
<script>
	function Refresh() {
		var json1={tabTitle:'驻场企业',url:'/forward_workEnterprise_list'};
		window.parent.refreshTab(json1); 
	}
	//导出
	function exportDate(){
	  	window.location.href = "${path}/workEnterprise/exportEnterprise.do";
	}
	//导入
	function importReg() {
	    var url = "/forward_workEnterprise_importWorkEnt";
	    $("#importWorkEnt").dialog({
	        title: "选择文件",
	        iconCls: 'icon-info',
	        minimizable: false,
	        content: "<iframe name=\"fileFrame\" frameborder=\"0\" src=" + url + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>", ${dataInit['sitePath']}
	        width: 600,
	        height: 500,
	        iconCls: 'icon-edit-lx',
	        modal: true,
	        buttons: [{
	            text: '关闭',
	            iconCls: 'icon-info',
	            handler: function (data) {
	                $("#contractAccounts").datagrid('reload');
	                $("#importWorkEnt").dialog("close");
	            }
	        }]
	    });
	}
	//驻场人员导入
	function importPer() {
	    var url = "/forward_workEnterprise_importPerson";
	    $("#importPerson").dialog({
	        title: "选择文件",
	        iconCls: 'icon-info',
	        minimizable: false,
	        content: "<iframe name=\"fileFrame\" frameborder=\"0\" src=" + url + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>", ${dataInit['sitePath']}
	        width: 600,
	        height: 500,
	        iconCls: 'icon-edit-lx',
	        modal: true,
	        buttons: [{
	            text: '关闭',
	            iconCls: 'icon-info',
	            handler: function (data) {
	                $("#contractAccounts").datagrid('reload');
	                $("#importPerson").dialog("close");
	            }
	        }]
	    });
	}
</script>
<script src="${pageContext.request.contextPath}/res/js/work/workEnterprise/list.js"></script>
</body>
</html>