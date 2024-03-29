<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    
    <title>项目指标台账</title>
 <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
 <style>
    .combo-panel
     {
        overflow: auto;
    }
    #datagrid-td-group1-0-4{
        background-color: #c1d8ef;
    }
    #datagrid-td-group1-0-5{
        background-color: #ffcbb3;
    }
    #datagrid-td-group1-0-6{
        background-color: #fff0ac;
    }
    #datagrid-td-group1-0-7{
        background-color: #ffdcb9;
    }
    #datagrid-td-group1-0-8{
        background-color: #ffffb9;
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
                <label for="">项目名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">项目编号：</label>
                <input type="text" id="code" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
             <div class="form-group">
                <label for="">项目分类：</label>
                <select id="projectClassificationId"  style="width:160px;" class="form-control input-sm">
                </select>
            </div>
             <div class="form-group">
                <label for="">创建时间：</label>
                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                <label for="">至</label>
                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            </div>
            <div class="form-group">
                <label for="">项目负责人：</label>
                <input type="text" name="" id="personLiableId" class="form-control input-sm"/>
                <%--<select id="personLiableId"  style="width:160px;" class="form-control input-sm">
                </select>
            --%></div>
            <div class="form-group">
                <label for="">工程地点（区）：</label>
                <select id="area" class="form-control input-sm" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="广州市">广州市</option>
                    <option value="天河区">天河区</option>
                    <option value="海珠区">海珠区</option>
                    <option value="越秀区">越秀区</option>
                    <option value="萝岗区">萝岗区</option>
                    <option value="白云区">白云区</option>
                    <option value="番禺区">番禺区</option>
                    <option value="从化区">从化区</option>
                    <option value="荔湾区">荔湾区</option>
                    <option value="花都区">花都区</option>
                    <option value="南沙区">南沙区</option>
                    <option value="黄埔区">黄埔区</option>
                    <option value="增城区">增城区</option>
                </select>
            </div>
            <div class="form-group">
                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="selectSubmit();">&nbsp;
                <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="reset1();">&nbsp;
                <input type="button" name="aa" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="projectIndexAccounts" style="width: 100%;">
    </table>
    <div id="tit1" >
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    </div>
</div>
<script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/project/projectZb.js"></script>
  </body>
  <script>
	var currentUser="${user.name}";

function exportDate(){
    var selections = $('#projectIndexAccounts').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.confirm('提示信息', '确认要导出全部记录吗？', function (isOk) {
            if(!isOk) {
                return;
            }
            window.location.href = "/costProject/exportProjectZb";
        });
    }else{
        $.messager.confirm('提示信息', '确认要导出这' + selections.length + '条记录吗？', function (isOk) {
            if(!isOk) {
                return;
            }
            var ids = '';
            for (var i in selections) {
                ids+=selections[i].id+",";
            }
            ids = ids.substring(0,ids.length-1);
            window.location.href = "/costProject/exportProjectZb?ids="+ids;
        });
    }
}
</script>
</html>
