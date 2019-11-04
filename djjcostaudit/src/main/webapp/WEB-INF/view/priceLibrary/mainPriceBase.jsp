<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>主材单价库</title>
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
<div class="row">
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">材料名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">规格型号：</label>
                <input type="text" id="feature" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">投标品牌/产地：</label>
                <input type="text" id="biddingBrand" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">使用品牌/产地：</label>
                <input type="text" id="useBrand" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">所属项目：</label>
                <input type="text" id="projectName" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">所属合同：</label>
                <input type="text" id="contractName" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">审价任务负责人：</label>
                <input type="text" id="taskPersonLiable" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
             <div class="form-group">
                <label for="">审价类型：</label>
                <select  class="form-control input-sm" onchange="searchMai()" id="typeLibrary" style="width: 100px">
						<option value="">请选择</option>
						<option value="主材定价">主材定价</option>
						<option value="设计变更费用">设计变更费用</option>
						<option value="现场签证">现场签证</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">发布日期：</label>
                <input id="createTimeStart" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                <label for="">至</label>
                <input id="createTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            </div>
            
            <div class="form-group">
                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="searchMai()">&nbsp;
                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="Refresh()">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate()">&nbsp;
                <input type="button" name="reset" value="变更/签证主材单价导入模版下载" class="btn btn-info" onclick="uploadZc();">&nbsp;
        		<input type="button" name="reset" value="导入" class="btn btn-warning" onclick="importZc();">&nbsp;
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="mainPriceBase" style="width: 100%;">
    </table>
    <div id="tit1">
        <!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="top.addTabGrid('添加合同', 'page/contractAccounts/contractAccounts_add.html');">添加</a>-->
        <!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit()">修改</a>-->
        <!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>-->
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    </div>
</div>
<div id="importZc"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/priceLibrary/mainPriceBase.js"></script>
<script>
var currentUser="${user.name}";
var dataUpdate="${dataUpdate}";
//自动刷新
function Refresh() {
	var json1={tabTitle:'主材单价库',url:'/forward_priceLibrary_mainPriceBase'};
	window.parent.refreshTab(json1); 
}
//主材单价模板下载
function uploadZc(){
  	window.location.href = "/costPriceLibrary/uploadZc";
}
//导入
function importZc(){
	var url = "/forward_priceLibrary_importZh?flagType=zc";
	$("#importZc").dialog({
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
			$("#mainPriceBase").datagrid('reload');
			$("#importZc").dialog("close");
		}
	}]
	});
}
</script>
</body>
</html>