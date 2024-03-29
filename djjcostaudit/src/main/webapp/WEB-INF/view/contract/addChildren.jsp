<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>补充协议添加</title>
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
	<input type="hidden" value="${parent.id}" name="parentId" id="parentId">
	<input type="hidden" value="从合同" name="mainFlag" id="mainFlag">
<table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
    <tbody>
        <tr>
            <td class="bgcolor"><strong>&nbsp;</strong>从属合同：</td>
            <td colspan="3">
                <%-- <input type="text"  value="${parent.name }" readonly style="width:100%;"> --%>
                <input type="text" id="parentName" name="parentName" value="${parent.name}" placeholder="请选择从属合同" style="width:80%;margin-top:5px;">
                    <a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="clearUnitParent();">
		            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
		            </a>
					<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="unitParent()">
		            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/contract.png" alt="" width="19"height="19">
		            </a>
            </td>
            <td class="bgcolor"><strong>&nbsp;</strong>合同负责人：</td>
           <td>
           		<c:choose>
            		<c:when test="${add_edit eq 'add'}">
            			<input type="text" id="personLiableId" name="personLiable" style="margin-top:5px;width:80%;" value="${parent.personLiable}" placeholder="请输入合同责任人" style="width:80%;" readonly/>
            		</c:when>
            		<c:otherwise>
            			<input type="text" id="personLiableId" name="personLiable" style="margin-top:5px;width:80%;" value="${obj.personLiable}" placeholder="请输入合同责任人" style="width:80%;" readonly/>
            		</c:otherwise>
            	</c:choose>
				<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="person();">
					<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                </a>
			</td>
        </tr>
        <tr>
            <td class="bgcolor"><strong>&nbsp;</strong>所属项目：</td>
            <td colspan="3">
                <input type="text"  value="${project.name}" readonly style="width:100%;">
                <input type="hidden"  value="${parent.projectId }"  id="containProjectId" name="projectId" style="width:80%;margin-top:5px;">
            </td>
            <td class="bgcolor" style="width:14%;"><strong>*</strong>合同状态：</td>
            <td style="text-align: center;width: 15%">
                <select id="status" name="status" class="custom-select">
					<c:forTokens items="请选择,实施中,结算中,完成" delims="," var="i">
                    	<option <c:if test="${i eq obj.status}">selected = "selected"</c:if>  value="${i}">${i}</option>
                    </c:forTokens>
				</select>
            </td>
        </tr>
        <tr>
            <td class="bgcolor"><strong>*</strong>合同名称：</td>
            <td colspan="3">
                <input type="text" id="name"  name="name" value="${obj.name }" placeholder="请输入合同名称" style="width:100%;">
            </td>
            <td class="bgcolor"><strong>&nbsp;</strong>需结算：</td>
            <td style="text-align: center;">
               <select id="settlement" name="settlement" class="custom-select" onchange="statusChange();">
					<c:forTokens items="不需,需要" delims="," var="j">
                    	<option <c:if test="${j eq obj.settlement}">selected = "selected"</c:if>  value="${j}">${j}</option>
                    </c:forTokens>
				</select>
            </td>
        </tr>
        <tr>
            <td class="bgcolor"><strong>*</strong>合同编号：</td>
            <td>
                <input type="text" id="code" name="code"  value="${obj.code}" placeholder="请输入合同编号" style="width:100%;">
            </td>
            <td class="bgcolor" style=""><strong>*</strong>合同类型：</td>
            <td>
            	${parent.contractType }
            	<input type="hidden" value="${parent.contractType }" id="contractType" name="contractType">
                <%--<select  style="width:100%;" id="contractType" name="contractType"  class="custom-select">
						<option value="">请选择</option>
						<c:forEach items="${conTypeList}" var="t">
							<option <c:if test="${t.name eq parent.contractType}">selected = "selected"</c:if>  value="${t.name}">${t.name}</option>
						</c:forEach>
					</select>
            	--%>
            </td>
            <td class="bgcolor"><strong>&nbsp;</strong>签订日期：</td>
            	<td>
                	<input class="Wdate search_text_form" id="signingTimeStr" name="signingTimeStr" value="${fn:substring(obj.signingTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                </td>
        </tr>
        <tr>

            <td class="bgcolor" style="width:14%"><strong>&nbsp;</strong>合同乙方：</td>
            <td style="width:17%">
		         <input type="text" name="partyB" readonly="readonly"  value="${parent.partyB }"  placeholder="读取主合同乙方" style="width:100%;">
            </td>
            <td class="bgcolor" style="width:12%"><strong>&nbsp;</strong>乙方联系人：</td>
            <td style="width:17%">
            	<c:choose>
            		<c:when test="${add_edit eq 'add'}">
		                <input type="text" name="partyBContacts" value="${parent.partyBContacts}" placeholder="读取，允许修改" style="width:100%;">
            		</c:when>
            		<c:otherwise>
            			<input type="text" name="partyBContacts" value="${obj.partyBContacts}" placeholder="读取，允许修改" style="width:100%;">
            		</c:otherwise>
            	</c:choose>
            </td>
            <td class="bgcolor" style="width:14%"><strong>&nbsp;</strong>乙方联系电话：</td>
            <td style="width:19%">
            	<c:choose>
            		<c:when test="${add_edit eq 'add'}">
		                <input type="text" name="partyBPhone"  value="${parent.partyBPhone}" placeholder="读取，允许修改" style="width:100%;">
            		</c:when>
            		<c:otherwise>
            			<input type="text" name="partyBPhone"  value="${obj.partyBPhone}" placeholder="读取，允许修改" style="width:100%;">
            		</c:otherwise>
            	</c:choose>
            </td>

        </tr>

        <tr>
            <td class="bgcolor"><strong id="decideAmount_star">&nbsp;</strong>合同金额（元）：</td>
			<td>
				<input type="text" id="contractAmount" name="contractAmount" value="${obj.contractAmount}" class="easyui-numberbox"  placeholder="请输入合同金额"
				data-options="groupSeparator:',',precision:2" style="width:100%;height:30px;line-height: 30px">
			</td>
            <td class="bgcolor"><strong>*</strong>合同执行部门：</td>
			<td>
				<input type="hidden" id="hidden-orgId" name="executiveDepartment"  value="${parent.executiveDepartment}"/>
                <input type="text" id="orgCombotree"  style="width:100%;">
			</td>
            <td class="bgcolor"><strong>*</strong>经办人：</td>
            <td>
               	<c:choose>
            		<c:when test="${add_edit eq 'add'}">
		                <input type="text" id="operator" name="operator"  value="${parent.operator}" placeholder="经办人" style="width:80%;margin-top:5px;">
            		</c:when>
            		<c:otherwise>
            			 <input type="text" id="operator" name="operator"  value="${obj.operator}" placeholder="经办人" style="width:80%;margin-top:5px;">
            		</c:otherwise>
            	</c:choose>
               
                <a href="javascript:void(0)" onclick="handle();" class="fr" style="margin-top:5px;"><img src="${pageContext.request.contextPath}/res/images/pagecommon/person.png" alt="" width="19"height="19"></a>
            </td>
        </tr>
        <tr>


            <td class="bgcolor"><strong>&nbsp;</strong>经办人电话：</td>
            <td  colspan="5">
            	<c:choose>
            		<c:when test="${add_edit eq 'add'}">
		                <input type="text"  name="operatorPhone" id="operatorPhone" value="${parent.operatorPhone}" placeholder="请输入电话" style="width:100%;">
            		</c:when>
            		<c:otherwise>
            			<input type="text"  name="operatorPhone" id="operatorPhone" value="${obj.operatorPhone}" placeholder="请输入电话" style="width:100%;">
            		</c:otherwise>
            	</c:choose>
            </td>
        </tr>
        <tr>
            <td class="bgcolor"><strong>&nbsp;</strong>合同摘要：</td>
            <td colspan="5">
                <textarea  name="description" cols="5" rows="3">${obj.description}</textarea>
            </td>
        </tr>
        <tr>
            <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
            <td colspan="5">
                <textarea name="remark" cols="5" rows="3">${obj.remark}</textarea>
            </td>
        </tr>
        
    </tbody>
</table>
<table  class="table_edit" style="margin-top: 1px;">
	<tr style="height: 48px;background-color: #cce8cf;">
            <td colspan="6" style="text-align: left;position: relative">
                <span class="title">关联审价任务</span>
                <a href="javascript:void(0);" class="switchBtn2"></a>
            </td>
    </tr>
    
	<tr class="sonlist2" >
		<td style="padding:2px 2px;">
			 <div id="contract_task"></div>
	           <div id=tit7>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="relationTask()">添加关联</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="removeRelationTask()">取消关联</a>
			   </div>
		</td>
	</tr>
	<tr style="height:40px">
            <td colspan="6" style="text-align: center">
                <input type="button" id="save" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
					<input type="button" onclick="top.closeWindow('补充协议添加');top.closeWindow('补充协议修改')" class="btn btn-success" value="取消">
            </td>
    </tr>
</table>
</form>
			  
<div id="tt" style="width:100%;">
	<div title="补充协议附件" class="subWrap">
		<table id="bcxyfujian" style="width: 100%"></table>
			<c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater) or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name))}">
				<div id=tit1>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('bcxyfujian')">添加文件夹</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog('bcxyfujian');">修改文件夹名称</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('bcxyfujian')">删除</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('bcxyfujian')">上传文件</a>
	                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
				</div>
			</c:if>
	</div>
	
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
<div id="relationTask"></div>
<div id="contractChildren"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/org-combotree.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/attachment2.js"></script>
<script src="${pageContext.request.contextPath}/res/js/pagecommon/page.js"></script>
<div id="person"></div>
<script>
var contractId=$("#contractId").val();
var parentId="${parent.id}";
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
	}
	var url="${url}";
	var orgId="${parent.executiveDepartment}";
	var editFlag="${param.editFlag}";
	$(function(){
		if(editFlag=="n"){
			disableForm("formId",true);
		}
		$("#save").click(function(){
			var status=$("#status").val();
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
			if(status==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写合同状态 ！', icon: 'error', top:100});
               	return;
           	}
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
           	/*
           	 if(personLiable==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写合同负责人 ！', icon: 'error', top:100});
               	return;
           	}*/
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
           	//从合同升级为主合同
           	var parentName=$("#parentName").val();
           	if(parentName==""){
           		$("#parentId").val("");
           		$("#mainFlag").val("主合同");
           	}else{
           		$("#mainFlag").val("从合同");
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
				  	 	var json1={tabTitle:'合同台账',url:'/forward_contract_list'};
					 	window.parent.refreshTab(json1); 
				  	}else if(result.status==500){
				  		$.messager.alert("温馨提示","请不要输入重复的合同编号!", "error");
				  	}else {
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
//关联审价任务
function relationTask() {
	var projectId=$("#containProjectId").val();
    $('#relationTask').dialog({
        title: '关联任务',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:100,
        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_relationTask?projectId='+projectId+'&contractId='+contractId+'&parentId='+parentId+  " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
//取消关联
function removeRelationTask(){
	var projectId=$("#containProjectId").val();
	var selections = $('#contract_task').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '确认要取消关联这' + selections.length + '条审价任务吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        var ids = "";
		for(var i = 0;i<selections.length;i++){
    		ids+=selections[i].id+",";
    	}
    	ids = ids.substring(0,ids.length-1);
    	$.post("/costContract/removeRelationTask", {"taskIds":ids,"contractId":contractId,"projectId":projectId,"parentId":parentId},function(result){
    		if(result.status==200){
			    //更新treegrid数据
    		    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			    $("#contract_task").datagrid('reload');
			}else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			}
    	}, "json");
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
          height: 520,
          closed: false,
          cache: false,
          top:100,
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
   
	var flag2=false;
	 $(".switchBtn2").click(function(){
         $(".sonlist2").toggle(500);
         if(!flag2){
             flag2=true;
             $(this).addClass("active");
         }else{
             flag2=false;
             $(this).removeClass("active");
         }
     });
    //删除从属合同
    function clearUnitParent(){
		$("#parentName").val("");
	}
	//关联合同
    function unitParent() {
    	var projectId=$("#containProjectId").val();
        $('#contractChildren').dialog({
            title: '所属合同',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_contract_containChildren?projectId='+projectId+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
</script>

</body>
</html>