<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>单价审核</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
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
    	<input type="hidden" name="id" value="${obj.id }">
    	<input type="hidden" name="dpctRelationId" value="${dpctRelationId}">
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
                <tbody>
                <tr>
                        <td class="bgcolor"><strong>*</strong>所属项目：</td>
                        <td colspan="5">
                            <input type="text"  value="${obj.projectName}" readonly="readonly" placeholder="请选择所属项目" id="containProjectShowName" style="width:80%;margin-top:5px;">
	           		 		<input type="hidden"  value="${obj.projectId }"  id="containProjectId" name="projectId" style="width:80%;margin-top:5px;">
	            			<input type="hidden"  value="${obj.projectName }"  id="containProjectName" name="projectName" style="width:80%;margin-top:5px;">
                            <a href="javascript:void(0)" onclick="agreeToProject()" class="fr" style="margin-top:5px;">
                            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                            </a>
                        	<input type="hidden" value="${obj.projectName }"  style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>所属合同：</td>
                        <td colspan="5">
                           <input type="text"  value="${obj.contractName}" readonly="readonly" placeholder="请选择所属项目" id="containContractShowName" style="width:80%;margin-top:5px;">
	           		 		<input type="hidden"  value="${obj.contractId }"  id="containContractId" name="contractId" style="width:80%;margin-top:5px;">
	            			<input type="hidden"  value="${obj.contractName }"  id="containContractName" name="contractName" style="width:80%;margin-top:5px;">
	            			<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="clearUnitContract();">
		                    	<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
		                    </a>
                            <a href="javascript:void(0)" onclick="agreeToContract()" class="fr" style="margin-top:5px;margin-right:5px;">
                            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/contract.png" alt="" width="19"height="19">
                            </a>
                        	<input type="hidden" value="${obj.contractName }"  style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                    	<td class="bgcolor"><strong>*</strong>所属往来文件：</td>
                        <td colspan="5">
                            <input type="text"  value="" readonly="readonly" placeholder="请选择所往来文件" id="containDocumentShowName" style="width:80%;margin-top:5px;">
	           		 		<input type="hidden"  value="${obj.documentId }"  id="containDocumentId" name="documentId" style="width:80%;margin-top:5px;">
                            <a href="javascript:void(0)" onclick="agreeToDocument();" class="fr" style="margin-top:5px;">
                            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/contract.png" alt="" width="19"height="19">
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>审价单位：</td>
                        <td colspan="5">
                            <input type="text" name="auditPriceUnit" value="${obj.auditPriceUnit }"  placeholder="自审的非全过程项目任务不用填" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>审价任务名称：</td>
                        <td colspan="3">
                            <input type="text" id="name"  name="name"  value="${obj.name }" placeholder="请输入审核内容" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>*</strong>审价任务负责人：</td>
                        <td>
                            <input type="text" id="personLiableId" name="personLiable" style="margin-top:5px;width:80%;" value="${obj.personLiable}" placeholder="请输入负责人" style="width:80%;"/>
                         	<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="person();">
                    		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>审价编号：</td>
                        <td>
                            <input type="text" id="code" name="code"   value="${obj.code }" placeholder="请输入审价编号" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>*</strong>审价类型：</td>
                        <td>
                            <select style="width:100%;" id="auditPriceType" name="auditPriceType" class="custom-select">>
                            	<option value="">请选择</option>
                            	<c:forEach items="${taskTypeList}" var="i">
                            		<option value="${i.name}">${i.name}</option>
                            	</c:forEach>
                            </select>
                        </td>
                        <input type="hidden" value="单价审核" name="auditPriceTypecn">
                        <td class="bgcolor"><strong>*</strong>接收日期：</td>
                        <td>
                            <input placeholder="接收日期" class="Wdate search_text_form" type="text" name="receiveTimeStr" value="${fn:substring(obj.receiveTimeStr, 0, 10)}" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})">
                        </td>

                    </tr>
                    <tr>
                        <td class="bgcolor" style="width:14%;"><strong>*</strong>送审条数：</td>
                        <td style="width:18%">
                            <input type="text" name="giveNumber" value="${obj.giveNumber }" class="easyui-numberbox" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入送审条数" style="width:100%;height:30px;line-height: 30px">
                        </td>
                        <td class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>审核条数：</td>
                        <td style="text-align: center;width: 15%">
                            <input type="text" name="auditNumber" value="${obj.auditNumber }" class="easyui-numberbox" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入审核条数" style="width:100%;height:30px;line-height: 30px">
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong>内联单号：</td>
                        <td>
                            <input type="text" name="inlineNumber" value="${obj.inlineNumber }" placeholder="请输入内联单号" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                    	<td  class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>定审日期：</td>
                        <td style="width:15%;">
                            <input onblur="setStatus();" placeholder="定审日期" class="Wdate search_text_form" type="text" id="decideTimeStr" name="decideTimeStr" value="${fn:substring(obj.decideTimeStr, 0, 10)}" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                        </td>
                        <td class="bgcolor"><strong>*</strong>当前状态：</td>
                        <td>
                            <select id="status" name="status" class="custom-select" onchange="statusChange();">
                                <c:forTokens items="请选择,新建,办内审核中,办内审核完,退审,审结" delims="," var="i">
                                	<option <c:if test="${i eq obj.status }">selected = "selected"</c:if>  value="${i}">${i}</option>
                                </c:forTokens>
                            </select>
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong><span style="display: none;" id="submissionTime">资料送审日期：</span></td>
                        <td>
                        	<div id="submissionTimeStr" style="display: none;">
	                            <input placeholder="资料送审日期" class="Wdate search_text_form" name="submissionTimeStr" value="${fn:substring(obj.submissionTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                        	</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>审批表编号：</td>
                        <td colspan="3">
                            <input type="text"  name="approval" value="${obj.approval }" placeholder="填写审批表编号" style="width:100%;">
                        </td>
                        <td  class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>送出日期：</td>
                        <td style="width:15%;">
                            <input placeholder="送出日期" type="text" class="Wdate search_text_form" name="deliveryTimeStr" value="${fn:substring(obj.deliveryTimeStr, 0, 10)}" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>评审报告书：</td>
                        <td colspan="5">
                            <input type="text"  name="reviewReportn" value="${obj.reviewReportn }" placeholder="评审报告书" style="width:100%;">
                        </td>
                    </tr>
                    <tr id="show_retrial" >
                        <td class="bgcolor"><strong>&nbsp;</strong>退审文号及原因：</td>
                        <td colspan="5">
                            <textarea id="retrial" name="retrial" cols="5" rows="2" placeholder="填入退审文号及原因">${obj.retrial }</textarea>
                        </td>
                    </tr>
                     <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                        <td colspan="5">
                            <textarea name="progressDescription" cols="5" rows="2" placeholder="填入审价任务情况说明">${obj.progressDescription }</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>审核意见：</td>
                        <td colspan="5">
                            <textarea name="checkExplain" cols="5" rows="2" placeholder="填入审核意见、附注">${obj.checkExplain }</textarea>
                        </td>
                    </tr>
                    <tr style="height:40px">
                        <td colspan="6" style="text-align: center">
                            <input type="button" class="btn btn-primary" id="save" value="保存">&nbsp;&nbsp;
                            <input type="button" class="btn btn-success" onclick="top.closeWindow('审价任务添加');top.closeWindow('审价任务修改');top.closeWindow('审价任务详情')" value="取消">
                        </td>
                    </tr>
                </tbody>
            </table>
    </form>
    <%--
    <div id="tt" style="width:100%;">
        <div title="送审资料" class="subWrap">
            <table id="songshen" style="width: 100%">
            </table>

            <div id="tit1">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('songshen')">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('songshen')">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('songshen')">上传文件</a>
            </div>

        </div>
        <div title="审定资料" class="subWrap">
            <table id="shending" style="width: 100%">
            </table>

            <div id="tit2">
                <<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('shending')">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('shending')">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('shending')">上传文件</a>
            </div>

        </div>
        <div title="往来文件" class="subWrap">
            <table id="wlfile" style="width: 100%">
            </table>
        </div>
        <div title="协调事项" class="subWrap">
            <table id="coordinationMatters" style="width: 100%">
            </table>
            <div id="tit4">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getTaskCoordinageDialog('添加协调事项')">新增</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getTaskCoordinageDialog('修改协调事项')">修改</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteTc()">删除</a>
            </div>
        </div>
    </div>
	--%>
</div>
<div id="taskDialog" style="background: #f4f8fb"></div>
<div id="taskCoordinateDialog"></div>
<div id="dlg"></div>
<div id="info_add"></div>
<div id="dialog"></div>
<div id="project"></div>
<div id="contract"></div>
<div id="document"></div>
<div id="person"></div>
<script>
	var taskId="${obj.id }";
	var url="${url}";
    <!-- 禁用所有Form表单 -->
    function disableForm(formId,isDisabled) {
        var attr="disable";
        if(!isDisabled){
            attr="enable";
        }
        $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] select").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);
    }
    $(function(){
	    $("#save").click(function(){
	    	
	    	var name= $("input[name='name']").val();
	    	var code= $("input[name='code']").val();
	    	var giveNumber=$("input[name='giveNumber']").val();
	    	var personLiable= $("input[name='personLiable']").val();
	    	var receiveTimeStr= $("input[name='receiveTimeStr']").val();
	    	var containProjectId=$("#containProjectId").val();
	    	var containDocumentId=$("#containDocumentId").val();
	    	
	    	var status=$("#status").val();
	    	var auditPriceType=$("#auditPriceType").val();
	    	
	    	if(containProjectId==""){
	          	$.messager.alert({title:'温馨提示', msg:'请选择所属项目！', icon: 'error', top:100});
	          	return;
		    }
	    	if(containDocumentId==""){
	          	$.messager.alert({title:'温馨提示', msg:'请选择所属往来文件！', icon: 'error', top:100});
	          	return;
		    }
	    	if(name==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写审价任务名称！', icon: 'error', top:100});
	          	return;
		    }
	    	if(code==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写审价编号！', icon: 'error', top:100});
	          	return;
		    }
	    	if(auditPriceType==""){
	          	$.messager.alert({title:'温馨提示', msg:'请选择审价类型！', icon: 'error', top:100});
	          	return;
		    }
	    	if(receiveTimeStr==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写接收日期！', icon: 'error', top:100});
	          	return;
		    }
	    	if(giveNumber==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写送审条数！', icon: 'error', top:100});
	          	return;
		    }
	    	if(status=="请选择"){
	          	$.messager.alert({title:'温馨提示', msg:'请选择当前状态！', icon: 'error', top:100});
	          	return;
		    }
	    	if(personLiable==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写负责人！', icon: 'error', top:100});
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
						  url="/costTask/upd2";
					  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
						  var json1={tabTitle:'单价审核',url:'/forward_task_priceCheck'};
						  window.parent.refreshTab(json1);
						  editInfo(taskId);
						  top.closeWindow('审价任务添加')
					  }else if(result.status==600){
					  		$.messager.alert("温馨提示","系统已有该审价任务名称,请不要重复!", "error");
					  }else if(result.status==800){
						  $.messager.alert("温馨提示","系统已有该审价任务编号,请不要重复!", "error");
					  }else{
						  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
					  }
				  }
			});
		});
	    
	    
    });
    
    function editInfo(zjkClId) {
        var href = "/costTask/toEdit?editFlag=y&id="+zjkClId;
        var title = "审价任务修改";
        top.addTabGrid(title, href);
    }
    //关联项目
    function agreeToProject() {
        $('#project').dialog({
            title: '关联项目',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_task_containProject'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    //关联合同
    function agreeToContract() {
    	var projectId=$("#containProjectId").val();
    	if(projectId){
	        $('#contract').dialog({
	            title: '关联合同',
	            width: 805,
	            height: 520,
	            closed: false,
	            cache: false,
	            top:50,
	            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_task_containContract?projectId='+projectId+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
	            modal: true
	        });
    	}else{
    		$.messager.alert("温馨提示","请先选择所属项目!", "error");
    	}
    }
  	//关联往来文件
    function agreeToDocument(){
    	var projId=$("#containProjectId").val();
        $('#document').dialog({
            title: '关联往来文件',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_task_containDocument?projId='+projId+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
  	//项目负责人
    function person() {
        $('#person').dialog({
            title: '系统用户',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_project_person'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    //删除合同
    function clearUnitContract(){
    	$("#containContractShowName").val("");
    	$("#contractName").val("");
    }
    function setStatus(){
    	var decideTimeStr=$("#decideTimeStr").val();
    		if(decideTimeStr){
	    		$("#status").val("审结");
    		}
    }
    function statusChange(){
  		var status=$("#status").val();
  	    if(status=="办内审核完"){
  			$("#submissionTime").show();
  			$("#submissionTimeStr").show();
  		}else{
  			$("#submissionTime").hide();
  			$("#submissionTimeStr").hide();
  			//$("#show_retrial").hide();
  		}
  	}
</script>
</body>
</html>