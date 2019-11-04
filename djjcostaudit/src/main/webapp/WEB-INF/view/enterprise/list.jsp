<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>入库企业</title>
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
	                <label for="">入库批次：</label>
	                <select class="form-control input-sm" onchange="searchCon()" id="batch" style="width: 100px">
	                     <c:forTokens items="${entYear}" delims="," var="y">
                        	<option <c:if test="${y eq currentYear}"> selected="selected"</c:if> value="${y}">${y}年</option>
                   		 </c:forTokens>
	                </select>
	            </div>
            	<div class="form-group">
                	<label for="">入库状态：</label>
                	<select class="form-control input-sm" onchange="searchCon()" id="effectiveFlag" style="width: 100px">
                    	<option value="在库" >在库</option>
                    	<option value="出库" >出库</option>
                    	<option value="暂停" >暂停</option>
                	</select>
            	</div>
            	<div class="form-group">
                	<label for="">入库企业名称：</label>
                	<input type="text" id="name" value="" placeholder="请输入入库企业名称关键字" class="form-control input-sm" style="width: 200px">
            	</div>
            	<!-- <div class="form-group">
	                <label for="">入库时间：</label>
	                <select class="form-control input-sm" onchange="searchCon()" id="year" style="width: 100px">
	                    <option value="">请选择</option>
	                    <option value="2021">2021</option>
	                    <option value="2020">2020</option>
	                    <option value="2019">2019</option>
	                </select>
            	</div> -->
            	<div class="form-group">
	                <label for="">入库时间：</label>
		                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
		                <label for="">至</label>
		                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            	</div>
	            <div class="form-group">
	                <label for="">上一年度综评：</label>
	                <select class="form-control input-sm" onchange="searchCon()" id="assessResultRk" style="width: 100px">
	                    <option value="">请选择</option>
	                    <option value="第一档">第一档</option>
	                    <option value="第二档">第二档</option>
	                    <option value="不合格">不合格</option>
	                    <option value="未评定">未评定</option>
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="">是否驻场：</label>
	                <select class="form-control input-sm" onchange="searchCon()" id="stationing" style="width: 100px">
	                    <option value="">请选择</option>
	                    <option value="是">是</option>
	                    <option value="否">否</option>
	                </select>
	            </div>
            	<div class="form-group">
                	<input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchCon()">&nbsp;
                	<input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="Refresh()">&nbsp;
                	<input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
                	<!-- <input type="button" name="reset" value="下载导入模版" class="btn btn-info" onclick="importEnt();">&nbsp; -->
                	<!-- <input type="button" value="导入" class="btn btn-warning" onclick="importEnterprise()">&nbsp; -->
            	</div>
        	</div>
    	</div>
    	<a href="javascript:void(0);" class="switchBtn"></a>
    	</form>
	</div>
	<div class="row">
    	<table id="rukuEnterprise" style="width: 100%;">
    	</table>
    	<div id="tit1" >
    	<shiro:hasPermission name="enterprise:add">
        	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="add()">添加</a>
        </shiro:hasPermission>
      <!--   <shiro:hasPermission name="enterprise:update">	
        	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit()">修改</a>
        </shiro:hasPermission> -->
        <shiro:hasPermission name="enterprise:delete">	
        	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
        </shiro:hasPermission>	
        	<span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    	</div>
	</div>
<div id="importEnterprise"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/enterprise/rukuEnterprise.js"></script>
<script>
function importEnterprise(){
	var url = "/forward_enterprise_importEnterprise";
	$("#importEnterprise").dialog({
	title: "选择文件",
	iconCls:'icon-info',
    minimizable:false,
    content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+url+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
    width:600,
    height:500,
    iconCls: 'icon-edit-lx',
    modal: true,
    buttons:[{
		text:'关闭',
		iconCls:'icon-info',
		handler:function(data){
			$("#rukuEnterprise").datagrid('reload');
			$("#importEnterprise").dialog("close");
		}
	}]
	});
}
var currentUser="${user.name}";
//导出
function exportDate(){
	var batch = $("#batch").val();
	var effectiveFlag = $("#effectiveFlag").val();
	var flag="";
	if(effectiveFlag=='在库'){
		flag="zk";
	}else if(effectiveFlag=='暂停'){
		flag="zt";
	}else{
		flag="ck";
	}
  	window.location.href = "/costEnterprise/exportEnterprise?flag="+flag+"&batch="+batch;
}
//下载导入模板
function importEnt(){
  	window.location.href = "/costEnterprise/importEnt";
}
//自动刷新
function Refresh() {
	var json1={tabTitle:'入库企业',url:'/costEnterprise/toEnterpriseList'};
	window.parent.refreshTab(json1); 
}
</script>
</body>
</html>