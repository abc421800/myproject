<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>惩罚记录</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
	<style>
        .menu-text{
            overflow:hidden;text-overflow: ellipsis;white-space: nowrap;width:140px;
        }
    </style>
</head>
<script type="text/javascript">
var codeB="${code.lunNumB}";
var codeA="${code.lunNumA}";
</script>
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
                <label for="">入库企业名称：</label>
                <input type="text" id="enterpriseName"  value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">入库批次：</label>
                <select class="form-control input-sm" onchange="searchPun()" id="punishYear" style="width: 100px">
                     <c:forTokens items="${entYear}" delims="," var="y">
                       	<option <c:if test="${y eq currentYear}"> selected="selected"</c:if> value="${y}">${y}年</option>
                  		 </c:forTokens>
                </select>
            </div>
           	<div class="form-group">
               	<label for="">入库状态：</label>
               	<select class="form-control input-sm" onchange="searchPun()" id="enterpriseStatus" style="width: 100px">
                   	<option value="" >请选择</option>
                   	<option value="在库" >在库</option>
                   	<option value="出库" >出库</option>
                   	<option value="暂停" >暂停</option>
               	</select>
           	</div>
           	<div class="form-group">
               	<label for="">奖/惩：</label>
               	<select class="form-control input-sm" onchange="searchPun()" id="punishFlag" style="width: 100px">
                   	<option value="" >请选择</option>
                   	<option value="奖励" >奖励</option>
                   	<option value="惩罚" >惩罚</option>
               	</select>
           	</div>
           	<div class="form-group">
               	<label for="">履行状态：</label>
               	<select class="form-control input-sm" onchange="searchPun()" id="status" style="width: 100px">
                   	<option value="" >请选择</option>
                   	<option value="正在执行" >正在执行</option>
                   	<option value="结束" >结束</option>
                   	<option value="取消" >取消</option>
               	</select>
           	</div>
            <div class="form-group">
                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchPun()">&nbsp;
                <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="refresh()">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate()">&nbsp;
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="punishList" style="width: 100%;"></table>
    <div id="tit1" >
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addPun()">添加</a>
    	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
    </div>
</div>
<script src="${pageContext.request.contextPath}/res/js/yaohao/yaohaoMng/punishList.js"></script>
<div id="addCompany" style="overflow-y: hidden"></div>
</body>
<script type="text/javascript">


</script>
</html>