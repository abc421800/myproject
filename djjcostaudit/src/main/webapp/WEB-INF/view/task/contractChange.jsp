<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>合同变更</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
<style type="text/css">
 .combo-panel{
 	overflow:auto;
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
                <label for="">所属合同：</label>
                <input type="text" id="contractName"  value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <%--<label for="">审价类型：</label>
                <select style="width:100%;" id="auditPriceType"  class="custom-select"></select>
--%>
                <label for="">审价类型：</label>
                <select onchange="searchTask();" id="auditPriceType" class="form-control input-sm">
                    <option value="">请选择</option>
                </select>

                <%--<select onchange="searchTask();" id="auditPriceType" class="form-control input-sm">
                    <option value="">请选择</option>
	                </select>
	            --%>
            </div>
            <div class="form-group">
                <label for="">审价编号：</label>
                <input type="text" id="code"  value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">审价任务负责人：</label>
                <input type="text" id="personLiable" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">审价单位：</label>
                <input type="text" id="auditPriceUnit"  value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">当前状态：</label>
                <select id="status" onchange="searchTask();" class="form-control input-sm" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="新建" >新建</option>
                    <option value="办内审核中" >办内审核中</option>
                    <option value="办内审核完" >办内审核完</option>
                    <option value="退审" >退审</option>
                    <option value="审结" >审结</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">定案文号：</label>
                <input type="text" id="finalizeNumber" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">协调：</label>
                <select class="form-control input-sm" onchange="searchTask()" id="coordinateFlag" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="是" >是</option>
                    <option value="否" >否</option>
                </select>
            </div>
            <div class="form-group">
                <label for=""></label>
                    <select style="width:90px;" id="shenTime" class="form-control input-sm" name="queryTimeType">
                        <option value="receiveTime">接收日期</option>
                        <option value="decideTime">定审日期</option>
                        <option value="deliveryTime">送出日期</option>
                    </select>
                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                <label for="">至</label>
                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            </div>
            <div class="form-group">
                <label for="">审价任务名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="button" id="Submit11" name="Submit11" value="查询" class="btn btn-primary" onclick="searchTask()">&nbsp;
                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="refresh()">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
                <!-- <input type="button" value="导入" class="btn btn-warning" onclick="importTask();">&nbsp; -->
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
    </form>
</div>
<div class="row">
    <table id="contractChange" style="width: 100%;">
    </table>
    <div id="tit1">
    <shiro:hasPermission name="task:add">
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="add('htbg');">添加</a>
    </shiro:hasPermission>
    <%--
    <shiro:hasPermission name="task:update">
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit()">修改</a>
    </shiro:hasPermission>
    --%>
    <shiro:hasPermission name="task:delete">
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delTask()">删除</a>
    </shiro:hasPermission>
    </div>
</div>
<div id="importTask"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/task/auditPriceType-combotree-select.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/task/contractChange.js"></script>
<script>
var auditPriceType="";
var currentUser="${user.name}";
var dataUpdate="${dataUpdate}";
//默认查询负责人为当前登录人
var oneLoad="${param.oneLoad}";
function exportDate(){
	var selections = $('#contractChange').datagrid('getSelections');
		var ids = "";
		if (selections.length != 0) {
			for(var i = 0;i<selections.length;i++){
	    		ids+=selections[i].id+",";
	    	}
	    	ids = ids.substring(0,ids.length-1);
	    }
   	window.location.href = "${path}/costTask/exportTaskHt?ids="+ids;
}
/*
$(function(){
	 $.post("/costTaskType/listByName", {"name":"合同变更"},function(result){
		 for(var i in result){
			var v=result[i].name;
			var div="<option value="+v+">"+v+"</option>";
				$("#auditPriceType").append(div);
		 }
 	}, "json");
 });
 */
//导入
function importTask(){
	var url = "/forward_task_importTaskHt";
	$("#importTask").dialog({
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
			$("#contractChange").datagrid('reload');
			$("#importTask").dialog("close");
		}
	}]
	});
}
</script>
<script type="text/javascript">
//默认查询负责人为当前登录人
//var oneLoad="${param.oneLoad}";
//$(function(){
	//if(oneLoad){
		//$("#personLiable").val(currentUser);
		//$("#Submit11").click();
	//}
//})
</script>
</body>
</html>