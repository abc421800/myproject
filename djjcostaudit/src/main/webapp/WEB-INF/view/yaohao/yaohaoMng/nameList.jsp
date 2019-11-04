<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>摇号名单</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <style>
        .menu-text {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            width: 140px;
        }
    </style>
</head>
<script type="text/javascript">
    var codeB = "${code.lunNumB}";
    var codeA = "${code.lunNumA}";
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
                <label for="">候选摇珠企业名称：</label>
                <input type="text" id="enterpriseName" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">上一年度综评：</label>
                <select class="form-control input-sm" id="yearAssess" style="width:80px" onchange="searchCan()">
                    <option value="">请选择</option>
                    <option value="第一档">第一档</option>
                    <option value="第二档">第二档</option>
                    <option value="不及格">不及格</option>
                    <option value="未评定">未评定</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">摇号档次：</label>
                <select class="form-control input-sm" style="width:80px" id="yaohaoGrade"
                        onchange="searchCan();changeHandler();">
                    <option value="第一档">第一档</option>
                    <option value="第二档" selected>第二档</option>
                </select>
            </div>
            <%--<div class="form-group">
              	<label for="">候选摇号：</label>
              	<select class="form-control input-sm" onchange="searchCon()" id="status" style="width:60px">
                  	<option value="在库" >在库</option>
                  	<option value="出库" >出库</option>
                  	<option value="暂停" >暂停</option>
              	</select>
          	</div>
            --%>
            <div class="form-group">
                <label for="">候选摇号：</label>
                <select class="form-control input-sm" style="width:80px" id="yaohaoJoin" onchange="searchCan();">
                    <option value="cy">参加</option>
                    <option value="bcy">不参加</option>
                </select>
            </div>
            <div class="form-group">
                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchCan()">&nbsp;
                <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="refresh()">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate()">&nbsp;
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="addRockNumber" style="width: 100%;">
    </table>
    <div id="tit1">
        <shiro:hasPermission name="candidate:add">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false"
               onclick="addCompany()">添加</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false"
               onclick="deleteHandler()">剔除本次摇号机会</a>
        </shiro:hasPermission>
        <input type="button" name="reset" value="摇珠会议签到表下载" class="btn btn-info" onclick="uploadQdb();">&nbsp;
        <input type="button" name="reset" value="摇珠记录表下载" class="btn btn-info" onclick="uploadJlb();">&nbsp;
    </div>
</div>
<script src="${pageContext.request.contextPath}/res/js/yaohao/yaohaoMng/nameList.js"></script>
<div id="addCompany" style="overflow-y: hidden"></div>
</body>
</html>