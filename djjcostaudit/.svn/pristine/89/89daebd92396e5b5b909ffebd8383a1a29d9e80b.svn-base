<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目台账</title>
      <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <!-- 审批新建页面样式 -->

</head>
<style>
.panel-body {

    overflow: auto;

}
.datagrid-cell-c1-name{
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap!important;
}
.datagrid-cell-c1-projectOwner{
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap!important;
}
.datagrid-cell-c1-projectOwner{
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap!important;
}
.datagrid-cell-c1-settlementAgreement{
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap!important;
}
.datagrid-cell-c1-settlementReivewMethod{
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap!important;
}
</style>
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
                <label for="">项目名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">管理协议签订：</label>
                <select id="projectManagementAgreement" class="form-control input-sm" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="未签" >未签</option>
                    <option value="已签" >已签</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">责任人：</label>
                <select id="personLiableId"  style="width:160px;" class="form-control input-sm">
                </select>
            </div>
            <div class="form-group">
                <label for="">工程类别：</label>
                <select id="projectCategoryId" class="form-control input-sm" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="" >一类</option>
                    <option value="" >二类</option>
                    <option value="" >三类</option>
                    <option value="" >四类</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">项目分类：</label>
                <select id="projectClassificationId"  style="width:160px;" class="form-control input-sm">
                </select>
            </div>
            <br>
            <div class="form-group">
                <label for="">项目所在地点：</label>
                <input type="text" id="projectLocation" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">当前节点：</label>
                <select id="projectNode" class="form-control input-sm" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="前期报建" >前期报建</option>
                    <option value="初期设计" >初期设计</option>
                    <option value="概算评审" >概算评审</option>
                    <option value="预算评审" >预算评审</option>
                    <option value="工程结算" >工程结算</option>
                </select>
            </div>
            <!--<br>-->
            <!--<div class="form-group">-->
                <!--<label for="">节点控制日期：</label>-->
                <!--<input id="contractSigningTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">-->
                <!--<label for="">至</label>-->
                <!--<input id="contractSigningTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">-->
            <!--</div>-->
            <div class="form-group">
                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="selectSubmit();">&nbsp;
                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="reset();">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="">&nbsp;
            </div>
            
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="projectAccounts" style="width: 100%;">
    </table>
    <div id="tit1">
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="top.addTabGrid('项目添加', '/costProject/editProject?editFlag=y');">添加</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit();">修改</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
        
    </div>
</div>

<script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/project/projectAccounts.js"></script>

</body>
</html>