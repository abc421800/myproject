<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>合同结算统计表</title>
     <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <!-- 审批新建页面样式 -->
    <style>
        .menu-text{
            overflow:hidden;text-overflow: ellipsis;white-space: nowrap;width:140px;
        }
        .combo-panel {
		    overflow: auto;
		} 
		 .datagrid-view2 .datagrid-footer tr.datagrid-row {
            background-color: #ffffcc;
            color: red;
            font-weight: bold;
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
<form action="/costProject/exportContractJsList2.do" method="post" id="form1">
<div class="row">
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">项目名称：</label>
                <input type="text" id="projectName" name="projectName" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
 			 <div class="form-group">
                <label for="">合同执行部门：</label>
                <input type="text" id="orgCombotreeSelect" style="width:200px;" value="" placeholder="请输入关键字" class="form-control input-sm">
                   <input type="hidden" id="orgId" name="orgId" value=""/>
            </div>
            <%--<div class="form-group">
                <label for="">审价类型：</label>
                <select class="form-control input-sm" onchange="selectSubmit()" id="auditPriceType" style="width: 100px">
                    <option value="">请选择</option>
                    <option value="所有" >所有</option>
                    <option selected="selected" value="结算审核" >结算审核</option>
                </select>
            </div>
            --%><div class="form-group">
                <label for="">送审时间：</label>
                <input id="startTime" name="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                <label for="">至</label>
                <input id="endTime" name="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
            </div>
            <div class="form-group">
                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="selectSubmit();">&nbsp;
                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="Refresh();">&nbsp;
                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
</form>
<div class="row">
    <table id="contractStatistics" style="width: 100%;">
    </table>
    <div id="tit1">
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    </div>
</div>
<div id="dialog" style="overflow: hidden"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/project/contractStatistics2.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/org-combotree-multiple.js"></script>
<script type="text/javascript">
var orgId="";
function exportDate(){
   	//window.location.href = "${path}/costProject/exportContractJsList2.do";
	 var orgId = $("#orgCombotreeSelect").combotree("getValues");
     var dep = orgId.join(",");
     if (dep == -1) {
         dep = "";
     }
     $("#orgId").val(dep);
     $("#form1").submit();
   }
</script>
</body>
</html>