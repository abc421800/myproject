<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>合同台账</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
 <style type="text/css">
.datagrid-cell-c1-projectName {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap !important;
}
.datagrid-cell-c1-partyB {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap !important;
}  
.combo-panel {
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
<div class="row">
	<form action="">
    <div class="filter">
        <div class="form-inline">
        	<div class="form-group">
                <label for="">所属项目：</label>
                <input type="text" id="projectName" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">合同名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">合同编号：</label>
                <input type="text" id="code" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">合同乙方：</label>
                <input type="text" id="partyB" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">合同类型：</label>
                <select  class="form-control input-sm" onchange="searchCon()" id="contractType" style="width: 100px">
						<option value="">请选择</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">合同执行部门：</label>
                <input type="text" id="orgCombotreeSelect" style="width:200px;" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">需结算：</label>
                <select class="form-control input-sm" onchange="searchCon()" id="settlement" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="需要" >需要</option>
                    <option value="不需要" >不需要</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">合同状态：</label>
                <select class="form-control input-sm" onchange="searchCon()" id="status" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="实施中" >实施中</option>
                    <option value="结算中" >结算中</option>
                    <option value="完成" >完成</option>
                    <option value="终止" >终止</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">签订时间：</label>
	                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
	                <label for="">至</label>
	                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            </div>
            <div class="form-group">
                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchCon()">&nbsp;
                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="Refresh()">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
           		<!-- <input type="button" name="reset" value="下载导入模版" class="btn btn-info" onclick="importCon();">&nbsp; -->
       	   		<!-- <input type="button" value="导入" class="btn btn-warning" onclick="importContract();">&nbsp; -->
           		<!-- <input type="button" value="临时导入" class="btn btn-warning" onclick="importContract2();">&nbsp; -->
            </div>
        </div>
    </div>
    	<a href="javascript:void(0);" class="switchBtn"></a>
    </form>
</div>
<div class="row">
    <table id="contractAccounts" style="width: 100%;"></table>
    <div id="tit1" >
    	<shiro:hasPermission name="contract:add">
        	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="add()">添加</a>
       	</shiro:hasPermission>
       	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="add2()">添加补充协议</a>
       	 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-descend" plain="false" onclick="add3()">降为补充协议</a>
       	<!-- <shiro:hasPermission name="contract:update">
        	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit()">修改</a>
        </shiro:hasPermission> -->
        <shiro:hasPermission name="contract:delete">
        	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
    	</shiro:hasPermission>
    	<span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    </div>
</div>
<div id="importContract"></div>
<div id="contract"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/list.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/org-combotree-multiple.js"></script>
<script>
var currentUser="${user.name}";
var dataUpdate="${dataUpdate}";
var orgId="";
//导出
function exportDate(){
	var selections = $('#contractAccounts').datagrid('getSelections');
		var ids = "";
		if (selections.length != 0) {
			for(var i = 0;i<selections.length;i++){
	    		ids+=selections[i].id+",";
	    	}
	    	ids = ids.substring(0,ids.length-1);
	    }
   	window.location.href = "${path}/costContract/exportContract?ids="+ids;
  }
//自动刷新
function Refresh() {
	var json1={tabTitle:'合同台账',url:'/forward_contract_list'};
	window.parent.refreshTab(json1); 
}
//获取合同类型
$(function(){
	$.post("/costContractType/contractTypeList",function(result){
		for(var i in result){
		var v=result[i].name;
		var div="<option value="+v+">"+v+"</option>";
			$("#contractType").append(div);
		}
   	}, "json");
});
//导入
function importContract(){
	var url = "/forward_contract_importContract";
	$("#importContract").dialog({
	title: "选择文件",
	iconCls:'icon-info',
    minimizable:false,
    content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+url+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
    //content:"<iframe id=\"editFrame\" name=\"editFrame\" frameborder=\"0\" src=\"${dataInit['sitePath']}commonUploadPage.action?fileType=1&type=xls\" scrolling=\"auto\" style=\"width:100%;height:100%;\"></iframe>",
    width:600,
    height:500,
    iconCls: 'icon-edit-lx',
    modal: true,
    buttons:[{
		text:'关闭',
		iconCls:'icon-info',
		handler:function(data){
			$("#contractAccounts").datagrid('reload');
			$("#importContract").dialog("close");
		}
	}]
	});
}
//临时导入
function importContract2(){
	var url = "/forward_contract_importContract2";
	$("#importContract").dialog({
	title: "选择文件",
	iconCls:'icon-info',
    minimizable:false,
    content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+url+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
    //content:"<iframe id=\"editFrame\" name=\"editFrame\" frameborder=\"0\" src=\"${dataInit['sitePath']}commonUploadPage.action?fileType=1&type=xls\" scrolling=\"auto\" style=\"width:100%;height:100%;\"></iframe>",
    width:600,
    height:500,
    iconCls: 'icon-edit-lx',
    modal: true,
    buttons:[{
		text:'关闭',
		iconCls:'icon-info',
		handler:function(data){
			$("#contractAccounts").datagrid('reload');
			$("#importContract").dialog("close");
		}
	}]
	});
}
//下载导入模板
function importCon(){
  	window.location.href = "/costContract/importCon";
}
</script>
</body>
</html>