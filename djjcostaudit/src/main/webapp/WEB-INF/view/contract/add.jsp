<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>合同台账添加</title>
	<jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
	<style type="text/css">
		.datagrid-cell-c2-name {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap !important;
		}
    	.datagrid-cell-c2-comeGoUnit {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap !important;
		}
		.datagrid-cell-c2-contractName {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap !important;
		}   
		.datagrid-cell-c2-taskName {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap !important;
		}
		.datagrid-cell-c3-name {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap !important;
		}
		.combo-panel {
		    overflow: auto;
		}
	</style>
</head>
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
		<input type="hidden" value="${obj.id}" name="id" id="contractId">
	</c:if>
	<input type="hidden" value="主合同" name="mainFlag" id="mainFlag">
<table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<td class="bgcolor"><strong>*</strong>合同名称：</td>
				<td colspan="5"><input type="text" id="name" name="name" value="${obj.name}" placeholder="请输入合同名称" style="width:100%;">
			</td>
		</tr>
		
		<tr>
			<td style="width:16%;" class="bgcolor"><strong>*</strong>合同编号：</td>
			<td>
				<input type="text" id="code" name="code" value="${obj.code}" placeholder="请输入合同编号" style="width:100%;">
			</td>
			<td class="bgcolor" style="width:16%;"><strong>*</strong>合同类型：</td>
			<td>
				<select style="width:100%;" id="contractType" name="contractType" class="custom-select">
					<option value="">请选择</option>
					<c:forEach items="${conTypeList}" var="t">
						<option <c:if test="${t.name eq obj.contractType}">selected = "selected"</c:if>  value="${t.name}">${t.name}</option>
					</c:forEach>
				</select>
			</td>
			<td class="bgcolor" style="width:16%;"><strong>&nbsp;</strong>合同状态：</td>
			<td style="text-align: center;">
				<select name="status" class="custom-select">
					<c:forTokens items="请选择,实施中,结算中,完成,终止" delims="," var="i">
                    	<option <c:if test="${i eq obj.status}">selected = "selected"</c:if>  value="${i}">${i}</option>
                    </c:forTokens>
				</select>
			</td>
		</tr>
		
		<tr>
			<td class="bgcolor"><strong>*</strong>所属项目：</td>
			<td colspan="3">
	            <input type="text" onclick="top.addTabGrid('项目修改', '/costProject/editProject?editFlag=y&projId=${obj.projectId}');" readonly="readonly" value="${obj.projectName}" placeholder="请选择所属项目" id="containProjectShowName" style="width:80%;margin-top:5px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;color:#549de3;cursor:pointer;">
	            <input type="hidden"  value="${obj.projectId }"  id="containProjectId" name="projectId" style="width:80%;margin-top:5px;">
	            <input type="hidden"  value="${obj.projectName }"  id="containProjectName" name="projectName" style="width:80%;margin-top:5px;">
	               	<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="agreeToProject()">
	               		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
	               	</a>
			</td>
			<td class="bgcolor"><strong>&nbsp;</strong>签订日期：</td>
            	<td>
                	<input class="Wdate search_text_form" id="signingTimeStr" name="signingTimeStr" value="${fn:substring(obj.signingTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                </td>
		</tr>
		
		<tr>
			<td class="bgcolor"><strong id="auditPriceUnit_star">&nbsp;</strong>审价单位：</td>
			<td colspan="3">
            	<input type="text"  id="auditPriceUnit" value="${obj.auditPriceUnit}" name="auditPriceUnit" placeholder="请输入/选择审价单位" style="width:60%;margin-top:5px;">
					<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="clearUnitEnterprise();">
            			<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
            		</a>
					<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="unitEnterprise()">
            			<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
            		</a>
            </td>
			<td class="bgcolor"><strong>&nbsp;</strong>合同负责人：</td>
			<td>
				<input type="text" id="personLiableId" name="personLiable" style="margin-top:5px;width:80%;" value="${obj.personLiable}" placeholder="请选择合同责任人全名" style="width:80%;"/>
				<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="person();">
					<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                </a>
			</td>
		</tr>
		
		<tr>
			<td class="bgcolor"><strong>&nbsp;</strong>合同乙方：</td>
			<td>
				<input type="text" name="partyB" value="${obj.partyB}" placeholder="请输入合同乙方" style="width:100%;">
			</td>
                <td class="bgcolor"><strong>&nbsp;</strong>乙方联系人：</td>
			<td>
				<input type="text" name="partyBContacts" value="${obj.partyBContacts}" placeholder="请输入联系人全名" style="width:100%;">
			</td>
			<td class="bgcolor"><strong>&nbsp;</strong>乙方联系电话：</td>
			<td>
				<input type="text" name="partyBPhone" value="${obj.partyBPhone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" placeholder="请输入联系电话" style="width:100%;">
			</td>
		</tr>
		
		<tr>
			<td class="bgcolor"><strong>*</strong>需结算：</td>
			<td style="text-align: center;">
				<select id="settlement" name="settlement" class="custom-select" onchange="statusChange();">
					<c:forTokens items="请选择,需要,不需要" delims="," var="j">
                    	<option <c:if test="${j eq obj.settlement}">selected = "selected"</c:if>  value="${j}">${j}</option>
                    </c:forTokens>
				</select>
			</td>
			<td class="bgcolor"><strong>&nbsp;</strong>合同变更金额（元）：</td>
			<td>
				<input type="text" name="changeAmount" value="${obj.changeAmountSum}" class="easyui-numberbox" placeholder="取该合同结算审价任务汇总"
				data-options="groupSeparator:',',precision:2" style="width:100%;height:30px;line-height: 30px">
			</td>
			<td class="bgcolor"><strong>&nbsp;</strong>合同结算金额（元）：</td>
			<td>
				<input type="text" name="settlementAmount" value="${obj.settlementAmount}" class="easyui-numberbox" placeholder="取该合同结算审价任务汇总"
				data-options="groupSeparator:',',precision:2" style="width:100%;height:30px;line-height: 30px">
			</td>
		</tr>
		<tr>
			<td class="bgcolor"><strong id="decideAmount_star">*</strong>合同金额（元）：</td>
			<td>
				<input type="text" id="contractAmount" name="contractAmount" value="${obj.contractAmount}" class="easyui-numberbox"  placeholder="只输入金额数值"
				data-options="groupSeparator:',',precision:2" style="width:100%;height:30px;line-height: 30px">
			</td>
			<td class="bgcolor"><strong>&nbsp;</strong>暂列金额（元）：</td>
			<td>
				<input type="text" name="temporaryAmount" value="${obj.temporaryAmount}" class="easyui-numberbox"  placeholder="只输入金额数值"
				data-options="groupSeparator:',',precision:2" style="width:100%;height:30px;line-height: 30px">
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td class="bgcolor"><strong>*</strong>合同执行部门：</td>
			<td>
				<input type="hidden" id="hidden-orgId" name="executiveDepartment"  value="${obj.executiveDepartment}"/>
                <input type="text" id="orgCombotree"  style="width:100%;">
			</td>
			<td class="bgcolor"><strong>*</strong>经办人：</td>
			<td>
				<input type="text" id="operator" name="operator" value="${obj.operator}" placeholder="请输入经办人全名">
				<a href="javascript:void(0)" class="fr" onclick="handle();">
					<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                </a>
			</td>
			<td class="bgcolor"><strong>&nbsp;</strong>经办人电话：</td>
			<td>
				<input type="text" name="operatorPhone" id="operatorPhone" value="${obj.operatorPhone}"  placeholder="请输入电话，多个号码用/间隔" style="width:100%;">
			</td>
		</tr>
		
		<tr>
			<td class="bgcolor"><strong>&nbsp;</strong>合同摘要：</td>
			<td colspan="5"><textarea name="description" cols="5" rows="3">${obj.description}</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
			<td colspan="5"><textarea name="remark" cols="5" rows="3">${obj.remark}</textarea>
			</td>
		</tr>
		<shiro:hasPermission name="contract:update">
		<c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater) or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name)) or dataUpdate eq true}">
			<tr style="height:40px">
				<td colspan="6" style="text-align: center">
					<input type="button" id="save" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
					<input type="button" id="cancel" onclick="top.closeWindow('合同添加');top.closeWindow('合同修改')" class="btn btn-success" value="取消">
				</td>
			</tr>
		</c:if>
		</shiro:hasPermission>
	</tbody>
</table>
</form>
<div id="tt" style="width:100%;">
	<div title="合同附件" class="subWrap">
		<table id="fujian" style="width: 100%"></table>
			<c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater) or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name)) or dataUpdate eq true}">
				<div id=tit1>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('fujian')">添加文件夹</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog('fujian');">修改文件夹名称</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('fujian')">删除</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('fujian')">上传文件</a>
	                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
				</div>
			</c:if>
	</div>
	<div title="招标资料" class="subWrap">
	<table id="tendering" style="width: 100%"></table>
		<div id=tit2>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('tendering')">添加文件夹</a>
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog('tendering');">修改文件夹名称</a> -->
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('tendering')">删除</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('tendering')">上传文件</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-share" plain="false" onclick="shareTask()">共享</a>
            <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
		</div>
	</div>
	<shiro:hasPermission name="contract:WLSJ">
	<div title="往来文件" class="subWrap">
	
	<div id="selectDiv" style="margin-bottom: 2px;">
		<div class="row">
	    	<form action="">
			<div class="filter" style="margin-bottom:0px">
		        <div class="form-inline">
		            <div class="form-group">
		                <label for="">文号：</label>
		                <input type="text" id="symbol" value="" placeholder="请输入关键字" class="form-control input-sm">
		            </div>
		            <div class="form-group">
		                <label for="">文件标题：</label>
		                <input type="text" id="documentName" value="" placeholder="请输入关键字" class="form-control input-sm">
		            </div>
		            <div class="form-group">
		                <label for="">往来性质：</label>
		                <select class="form-control input-sm" id="comeGoFlag" style="width: 100px" onchange="documentSelectSubmit();">
		                    <option value="">请选择</option>
		                    <option value="来" >来</option>
		                    <option value="往" >往</option>
		                </select>
		            </div>
		            <div class="form-group">
		                <label for="">是否审价：</label>
		                <select class="form-control input-sm" id="auditPriceFlag" style="width: 100px" onchange="documentSelectSubmit();">
		                    <option value="">请选择</option>
		                    <option value="是" >是</option>
		                    <option value="否" >否</option>
		                </select>
		            </div>
		            <div class="form-group">
		                <label for="">往来单位：</label>
		                <input type="text"  id="comeGoUnit" value="" placeholder="请输入关键字" class="form-control input-sm">
		            </div>
		
		            <div class="form-group">
		                <label for="">负责人：</label>
		                <input type="text" id="personLiable5" placeholder="请输入关键字" class="form-control input-sm"/>
		            </div>
		            <div class="form-group">
		                <label for="">文件日期：</label>
		                <input id="documentTimeStart" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
		                <label for="">至</label>
		                <input id="documentTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
		            </div>
		            <div class="form-group">
		                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="documentSelectSubmit();">&nbsp;
		                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="resetMethod('wlfile');">&nbsp;
		                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportWl();">&nbsp;
		                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
		            </div>
		        </div>
	        </div>
			<a href="javascript:void(0);" class="switchBtn" id="switchBtn1"></a>
			</form>
		</div>
	</div>
	<table id="wlfile" style="width: 100%"></table>
	<div id=tit6>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="agreeToDocument()">添加</a>
            <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
	</div>
	</div>
	<div title="审价任务" class="subWrap">
	<table id="sjTask" style="width: 100%"></table>
		<div id="tit7">
		    <div class="row">
	        <form action="">
	        <div class="filter" style="margin-bottom:0px">
	            <div class="form-inline">
	                <div class="form-group">
	                    <label for="">审价编号：</label>
	                    <input type="text" id="taskCode" value="" placeholder="请输入关键字" class="form-control input-sm">
	                </div>
	                <div class="form-group">
	                    <label for="">审价任务名称：</label>
	                    <input type="text" id="taskName" value="" placeholder="请输入关键字" class="form-control input-sm">
	                </div>
	                <div class="form-group">
		                <label for="">审价类型：</label>
		                <select id="auditPriceType"  class="custom-select" style="width: 200px"></select>
            		</div>
	                <!-- <div class="form-group">
	                    <label for="">审价类型：</label>
	                    <select class="form-control input-sm" id="taskType" onchange="priceInquiry()" style="width: 200px">
	                        <option value="">请选择</option>
	                        <option value="估概预结" >估概预结</option>
	                        <option value="合同变更" >合同变更</option>
	                        <option value="单价审核" >单价审核</option>
	                    </select>
	                </div> -->
	                <div class="form-group">
	                    <label for="">审价任务负责人：</label>
	                    <input type="text" id="personLiable6" placeholder="请输入关键字" class="form-control input-sm"/>
	                </div>
	                <div class="form-group">
	                    <label for="">审价单位：</label>
	                    <input type="text" id="taskUnit" value="" placeholder="请输入关键字" class="form-control input-sm"/>
	                </div>
	                <div class="form-group">
	                    <label for="">当前状态：</label>
	                    <select class="form-control input-sm" id="status" style="width: 100px" onchange="priceInquiry()">
	                        <option value="">请选择</option>
		                    <option value="新建" >新建</option>
		                    <option value="办内审核中" >办内审核中</option>
		                    <option value="办内审核完" >办内审核完</option>
		                    <option value="审批完成待确认" >审批完成待确认</option>
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
	                    <select class="form-control input-sm" style="width: 100px" id="coordinateFlag" onchange="priceInquiry()">
	                        <option value="">请选择</option>
	                        <option value="是" >是</option>
	                        <option value="否" >否</option>
	                    </select>
	                </div>
	                <div class="form-group">
	                    <label for="">创建日期：</label>
	                    <input id="createTimeStart" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
	                    <label for="">至</label>
	                    <input id="createTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
	                </div>
	                <div class="form-group">
	                    <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="priceInquiry();">&nbsp;
	                    <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="resetMethod('sjTask');">&nbsp;
	                    <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportSj();">&nbsp;
	                    <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
	                </div>
	            </div>
	        </div>
	        <a href="javascript:void(0);" class="switchBtn" id="switchBtn2"></a>
	        </form>
		    </div>
		</div>
	</div>
	</shiro:hasPermission>
</div>
</div>
<div id="taskDialog" style="background: #f4f8fb"></div>
<div id="dlg"></div>
<div id="info_add"></div>
<div id="dialog"></div>
<div id="project"></div>
<div id="unitEnterprise"></div>
<div id="editDialog"></div>
<div id="task"></div>
<div id="document"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/add.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/attachment.js"></script>
<script src="${pageContext.request.contextPath}/res/js/pagecommon/page.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/taskType.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/org-combotree.js"></script>
<div id="person"></div>
<script>
var contractId=$("#contractId").val();
var taskTypeId="";
<!-- 禁用所有Form表单 -->
	function disableForm(formId, isDisabled) {
		var attr = "disable";
		if (!isDisabled) {
			attr = "enable";
		}
		$("form[id='" + formId + "'] :text").attr("readonly", isDisabled);
		$("form[id='" + formId + "'] textarea").attr("readonly", isDisabled);
		$("form[id='" + formId + "'] select").attr("readonly", isDisabled);
		$("form[id='" + formId + "'] :radio").attr("readonly", isDisabled);
		$("form[id='" + formId + "'] :checkbox").attr("readonly", isDisabled);
		$("#save").hide();
		$("#cancel").hide();
	}
	var url="${url}";
	var orgId="${obj.executiveDepartment}";
	var editFlag="${param.editFlag}";
	$(function(){
		if(editFlag=="n"){
			disableForm("formId",true);
		}
		$("#save").click(function(){
			var name=$("#name").val();
			var code=$("#code").val();
			var contractType=$("#contractType").val();
			var containProjectId=$("#containProjectId").val();
			var personLiable=$("#personLiableId").val();
			var signingTimeStr=$("#signingTimeStr").val();
			var operator=$("#operator").val();
			var settlement=$("#settlement").val();
			var contractAmount=$("#contractAmount").val();
			var hidden_orgId=$("#orgCombotree").combotree("getValue");
			$("#hidden-orgId").val(hidden_orgId);
			if(name==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写合同名称 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(code==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写合同编号 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(contractType==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择合同类型 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(containProjectId==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择所属项目 ！', icon: 'error', top:100});
               	return;
           	}
           	/*
           	 if(signingTimeStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择签订日期 ！', icon: 'error', top:100});
               	return;
           	}
           	*/
           	 /* if(personLiable==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写合同负责人 ！', icon: 'error', top:100});
               	return;
           	} */
           	if(settlement=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择需结算 ！', icon: 'error', top:100});
               	return;
           	}
           	if(settlement=="需要"){
	    		if(contractAmount==""){
	    			$.messager.alert({title:'温馨提示', msg:'请填写合同金额！', icon: 'error', top:100});
		          	return;
	    		}
	    	}
           	 if(hidden_orgId=="-1"){
               	$.messager.alert({title:'温馨提示', msg:'请填写合同执行部门 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(operator==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写经办人 ！', icon: 'error', top:100});
               	return;
           	}
			$.ajax({
			  	url: url,
			  	dataType: "json",
			  	type:"post",
			  	async: false,
			  	data: $("#formId").serialize(),
			  	success: function(result){
				  	if(result.status==200){
				  		url="/costContract/upd";
				  	 	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");	
				  	 	/*var json1={tabTitle:'合同台账',url:'/forward_contract_list'};
					 	window.parent.refreshTab(json1); */
				  	}else if(result.status==400){
				  		$.messager.alert("温馨提示","请不要输入重复的合同名称!", "error");
				  	}else if(result.status==500){
				  		$.messager.alert("温馨提示","请不要输入重复的合同编号!", "error");
				  	}else{
						$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  	}
			  	}
			});
		});
	});
//关联项目
function agreeToProject() {
    $('#project').dialog({
        title: '关联项目',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:100,
        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_containProject'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
//是否选填
function statusChange(){
  		var settlement=$("#settlement").val();
  		if(settlement=="需要"){
  			$("#decideAmount_star").text("*");
  		}
  		else{
  			$("#decideAmount_star").text("");
  		}
  	}
//共享审价任务
function shareTask() {
	var projectId=$("#containProjectId").val();
    $('#task').dialog({
        title: '共享资料',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:100,
        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_containTask?projectId='+projectId+'&contractId='+contractId+  " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
//审价单位
function unitEnterprise() {
    $('#unitEnterprise').dialog({
        title: '审价单位',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:50,
        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_containEnterprise'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
function clearUnitEnterprise(){
	$("#auditPriceUnitName").val("");
	$("#auditPriceUnit").val("");
}
	//合同负责人,经办人
	function person() {
	    $('#person').dialog({
	        title: '系统用户',
	        width: 805,
	        height: 500,
	        closed: false,
	        cache: false,
	        top:50,
	        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_project_person'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
	        modal: true
	    });
	}
  //经办人
  function handle() {
  	  var hidden_orgId=$("#orgCombotree").combotree("getValue");
  	  if(hidden_orgId=='-1'){
  	  	$.messager.alert("温馨提示","请先选择合同执行部门!", "error");
  	  	return;
  	  }
      $('#person').dialog({
          title: '经办人列表',
          width: 805,
          height: 520,
          closed: false,
          cache: false,
          top:100,
          content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_handle?orgId='+hidden_orgId+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
          modal: true
      });
  }
//关联往来文件
  function agreeToDocument() {
	  var projId=$("#containProjectId").val();
	  var href = "/costDocument/toEdit?editFlag=y&contractId="+contractId+"&projId="+projId;
	  var title = "往来文件添加";
	  top.addTabGrid(title, href);
	 /*
      $('#document').dialog({
          title: '关联往来文件',
          width: 805,
          height: 520,
          closed: false,
          cache: false,
          top:50,
          content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_containDocument?projId='+projId+'&contractId='+contractId+ " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
          modal: true
      });
      */
  }
  
  
  //往来文件导出
	function exportWl(){
   	window.location.href = "/costDocument/exportDocument.do";
   }
   //审价任务导出
	function exportSj(){
   	window.location.href = "/costContract/exportTask.do";
   }
   //自动刷新
	function resetMethod(tableId){
	$('#'+tableId).datagrid('options').queryParams = {
		
    };
	$('#' + tableId).datagrid("reload");
	}
</script>

</body>
</html>