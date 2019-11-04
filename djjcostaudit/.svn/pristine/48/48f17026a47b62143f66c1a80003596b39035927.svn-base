<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>重启详情</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-master/src/css/layui.css">
	<style type="text/css">
        .table_edit tr:last-child td {
         	border-bottom: 1px solid #dbe5ee;
        }
		.table_edit td:last-child {
            border-right: 1px solid #dbe5ee;
        }
        .title {
            font-size: 12px;
            color: #283747;
            font-weight: bold;
            background: url(${pageContext.request.contextPath}/res/images/pagecommon/flag.png) 0 center no-repeat;
            padding-left: 28px;
            display: inline-block;
            line-height: 28px;
            margin-right: 16px;
            height: 28px;
        }
        a.moreBtn {
            display: inline-block;
            height: 28px;
            line-height: 28px;
            border: 1px solid #e2e8ee;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            position: absolute;
            text-decoration: none;
            box-sizing: border-box;
            -webkit-box-shadow: 0 3px 3px rgba(201, 205, 209, 0.58);
            -moz-box-shadow: 0 3px 3px rgba(201, 205, 209, 0.58);
            box-shadow: 0 3px 3px rgba(201, 205, 209, 0.58);
            padding: 0 8px;
            background-color: #f4f7fb;
            color: #283747;
            font-size: 12px;
            font-weight: bold;
            font-family: "微软雅黑";
            right:80px;
        }
        .switchBtn1,.switchBtn2 {
            position: absolute;
            display: inline-block;
            width: 28px;
            height: 28px;
            line-height: 48px;
            border: 1px solid #dbe5ee;
            -webkit-border-radius: 0 0 4px 4px;
            -moz-border-radius: 0 0 4px 4px;
            border-radius: 0 0 4px 4px;
            background: #f4f7fb url(${pageContext.request.contextPath}/res/images/pagecommon/top.png) center center no-repeat;
            right:20px;
            top:-1px;
        }
        .switchBtn1.active, .switchBtn2.active {
            background: #f4f7fb url(${pageContext.request.contextPath}/res/images/pagecommon/bottom.png) center center no-repeat;
        }
        .datagrid .datagrid-pager tr{
        	height: auto;
        }
        .datagrid .datagrid-pager td{
        	padding: 0px;
        	border-right:none; 
        }
	</style>
</head>
<script src="${pageContext.request.contextPath}/res/plugin/layui-master/src/layui.js"></script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //时间范围
        laydate.render({
            elem: '#leaveTime',range: true
        });
    });
</script>
<body class="easyui-layout">
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
<div data-options="region:'center',border:0" class="wrap_center">
    <form action="" id="formId">
    	<c:if test="${!empty obj.id}">
	    	<input type="hidden" value="${obj.id}" name="id">
    	</c:if>
    	<table class="table_edit" id="table" style="margin-bottom: 10px;" cellspacing="0" cellpadding="0" >
                <tbody>
                <tr style="height:40px;background-color: #dde3e8;">
                    <td colspan="2" style="text-align: center">
                        <strong style="text-align: center;font-size: 16px">请假申请单</strong>
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>
                       	 编号：
                    </td>
                    <td>
                        <input type="text" id="code" name="code"  value="${obj.code}" readonly placeholder="请输入联系地址" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor" style="width:30%"><strong>&nbsp;</strong>申请人：</td>
                    <td>
                    	<input  name="proposer" type="hidden" value="${obj.proposer }">
                        <input name="proposerName" type="text"  value="${obj.proposerName }" placeholder="请输入申请人" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>所属驻场企业：</td>
                    <td>
                    	<input name="enterpriseId" type="hidden" value="${obj.enterpriseId }">
                        <input type="text" name="enterpriseName"  value="${obj.enterpriseName}" placeholder="请输入所属驻场企业" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>可休年假：</td>
                    <td style="text-align: left">
                        <input readonly="readonly"  type="text" style="width:15%;display: inline-block" value="<fmt:formatDate value="${workPerson.annualLeaveStartTime}" pattern="yyyy-MM-dd"/>" class="form-control input-sm" /> 至
                        <input readonly="readonly"  type="text" style="width:15%;display: inline-block" value="<fmt:formatDate value="${workPerson.annualLeaveEndTime}" pattern="yyyy-MM-dd"/>" class="form-control input-sm" />
                        &nbsp;&nbsp;&nbsp;
                        <label for="">共</label>
                        <input type="text" style="width:30px;display: inline-block;text-align: center" value="${workPerson.annualLeaveTotal }" />
                        <label for="" style="margin-right: 50px">天</label>
                        <label for="">已休：</label>
                        <input type="text" style="width:30px;display: inline-block;text-align: center" value="${workPerson.annualLeaveUseup }" />
                        <label for="">天</label>
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>*</strong>请假事由：</td>
                    <td>
                        <textarea id="content" readonly="readonly" name="content"  rows="4">${obj.content }</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>*</strong>请假类型：</td>
                    <td >
                        <select id="type" name="type" class="custom-select" onchange="typeChange();" style="width:50%">
                           	<c:forTokens items="年休假,探亲假,事假,生育假,病假,婚假,丧假,离岗学习,出差,其他" delims="," var="i">
                           		<option <c:if test="${i eq obj.type }">selected = "selected"</c:if>  value="${i}">${i}</option>
                           	</c:forTokens>
                        </select>
                    </td>
                </tr>
                 <c:if test="${obj.type eq '其他' }">
                <tr id="remarkBz">
                	<td class="bgcolor"><strong>*</strong><span id="remarkStar">备注：</span></td>
                	<td>
                    	<textarea id="remark" name="remark" rows="2">${obj.remark}</textarea>
                	</td>
            	</tr>
            	</c:if>
                <tr>
                    <td class="bgcolor"><strong>*</strong>请假时间：</td>
                    <td>
                        <input class="Wdate search_text_form"  type="text" value="<fmt:formatDate value="${obj.leaveStartTime}" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${obj.leaveEndTime}" pattern="yyyy-MM-dd"/>" name="leaveTime" id="leaveTime" style="width:50%;margin-right:20px">
                        <input id="am"  <c:if test="${obj.morAftAll eq '上午' }">checked</c:if>    name="morAftAll" value="上午" type="radio"><label for="am">上午</label>&nbsp;&nbsp;
                        <input id="pm"   <c:if test="${obj.morAftAll eq '下午' }">checked</c:if>    name="morAftAll" value="下午" type="radio"><label for="pm">下午</label>&nbsp;&nbsp;
                        <input id="all"  <c:if test="${obj.morAftAll eq '全天' }">checked</c:if>  value="全天" name="morAftAll" type="radio"><label for="all">全天</label>&nbsp;&nbsp;
                    </td>

                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>审批人：</td>
                    <td style="text-align: left">
                        <input type="text" id="nextperson" readonly="readonly" name="nextperson" value="${obj.nextpersonName }" placeholder="请选择审批人"  style="width:80%;margin-top:5px;">
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>传阅对象：</td>
                    <td style="text-align: left">
                        <input type="text" id="relatedId" readonly="readonly" name="relatedId" value="${obj.relatedName }" placeholder="请选择传阅对象"  style="width:80%;margin-top:5px;">
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>&nbsp;</strong>重启原因：</td>
                    <td>
                        <textarea id="opinion" name="opinion"  rows="2"></textarea>
                    </td>
                </tr>
                <c:if test="${user.account eq obj.nextperson}"></c:if>
                <tr style="height:40px">
                    <td colspan="2" style="text-align: center">
                        <input type="button" class="btn btn-primary" id="save" value="确认重启">&nbsp;&nbsp;
                        <input type="button" class="btn btn-success" onclick="top.closeWindow('请假申请重启')" value="取消">
                    </td>
                </tr>
                </tbody>
            </table>
            <table  class="table_edit" style="margin-bottom: 50px;" >
				<tr style="height: 40px;background-color: #dde3e8;">
			            <td style="text-align:center;position: relative">
			               <strong style="text-align: center;font-size: 16px">审批记录</strong>
			            </td>
			    </tr>
			    <tr>
			        <td style="padding:2px 2px;">
						 <div id="processRecord"></div>
					</td>
			    </tr>
			</table>
    </form>
</div>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/taskType.js"></script>
<script>
    <!-- 禁用所有Form表单 -->
    function disableForm(formId,isDisabled) {
        var attr="disable";
        if(!isDisabled){
            attr="enable";
        }
        $("form[id='"+formId+"'] :text").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] textarea").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] select").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] :radio").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] :checkbox").attr("readonly",isDisabled);
    }
    var editFlag="${param.editFlag}";
    var processRecordId="${obj.id}";
	$(function(){
		if(editFlag=="n"){
			disableForm("formId",true);
		}
		
		$("#save").click(function(){
            var opinion=$("#opinion").val();
            if(opinion==""){
                $.messager.alert({title:'温馨提示', msg:'请填写重启原因！', icon: 'error', top:100});
                return;
            }
			$.ajax({
				  url: "/workProcessApply/restartTask",
				  dataType: "json",
				  type:"post",
				  async: false,
				  data: $("#formId").serialize(),
				  success: function(result){
					  if(result.status==200){
						  var json1={tabTitle:'请假申请',url:'/forward_processApply_list'};
						  window.parent.refreshTab(json1);
					  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
					  	  top.closeWindow('请假申请重启')
					  } if(result.status==400){
                          $.messager.alert("温馨提示","请假日期冲突，请核实请假日期。", "error");
                      } else{
						  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
					  }
				  }
			});
		});
		
	});
   
	
</script>
<script src="${pageContext.request.contextPath}/res/js/work/processApply/processRecord.js"></script>
</body>
</html>