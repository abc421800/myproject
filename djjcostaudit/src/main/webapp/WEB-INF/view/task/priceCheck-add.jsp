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
    	<style type="text/css">
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
    	<input type="hidden" name="id" value="${obj.id }">
    	<input type="hidden" name="documentId" value="${obj.documentId}">
    	<input type="hidden" name="dpctRelationId" value="${dpctRelationId}">
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
                <tbody>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>审价任务名称：</td>
                        <td colspan="5">
                            <input type="text"  name="name"  value="${obj.name }" placeholder="请输入审核内容" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>审价编号：</td>
                        <td>
                            <input type="text"   name="code"  value="${obj.code }" placeholder="请输入审价编号" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>*</strong>审价类型：</td>
                        <td>
                            ${obj.auditPriceType }
                        </td>
                        <input type="hidden" value="${obj.auditPriceType }" name="auditPriceType">
                        <input type="hidden" value="${obj.auditPriceTypecn}" name="auditPriceTypecn">
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
                        <td class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>定审日期：</td>
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
                        <td colspan="3">
                            <input type="text"  name="reviewReportn" value="${obj.reviewReportn }" placeholder="评审报告书" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>*</strong>审价任务负责人：</td>
                        <td>
                            <input type="text" id="personLiableId" name="personLiable" style="margin-top:5px;width:80%;" value="${obj.personLiable}" placeholder="请输入负责人" style="width:80%;"/>
                         	<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="person();">
                    		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                        </td>
                        <%--<td class="bgcolor"><strong>&nbsp;</strong>份数：</td>
                        <td>
                            <input type="text"  name="umber" value="${obj.umber }" placeholder="份数">
                        </td>
                    	--%>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>所属项目：</td>
                        <td colspan="5">
                            <input type="text"  value="${obj.projectName}" readonly="readonly" placeholder="请选择所属项目" id="containProjectShowName" onclick="top.addTabGrid('项目修改', '/costProject/editProject?editFlag=y&projId=${obj.projectId}');" style="width:80%;margin-top:5px;color:#549de3;cursor: pointer;">
	           		 		<input type="hidden"  value="${obj.projectId }"  id="containProjectId" name="projectId" style="width:80%;margin-top:5px;">
	            			<input type="hidden"  value="${obj.projectName }"  id="containProjectName" name="projectName" style="width:80%;margin-top:5px;">
                            <a href="javascript:void(0)" onclick="agreeToProject()" class="fr" style="margin-top:5px;">
                            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                            </a>
                        	<input type="hidden" value="${obj.projectName }"  style="width:100%;">
                        	<%--<a href="javascrept:void(0)" onclick="top.addTabGrid('项目修改', '/costProject/editProject?editFlag=y&projId=${obj.projectId}');">${obj.projectName }</a>
                        	--%>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>所属合同：</td>
                        <td colspan="5">
                            <input type="text"  value="${obj.contractName}" readonly="readonly" placeholder="请选择所属合同" id="containContractShowName" onclick="top.addTabGrid('合同修改', '/costContract/toEdit?editFlag=y&id=${obj.contractId}');" style="width:80%;margin-top:5px;color:#549de3;cursor: pointer; ">
	           		 		<input type="hidden"  value="${obj.contractId }"  id="containContractId" name="contractId" style="width:80%;margin-top:5px;">
	            			<input type="hidden"  value="${obj.contractName }"  id="containContractName" name="contractName" style="width:80%;margin-top:5px;">
	            			<a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="clearUnitContract();">
		                    	<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
		                    </a>
                            <a href="javascript:void(0)" onclick="agreeToContract()" class="fr" style="margin-top:5px;margin-right:5px;">
                            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/contract.png" alt="" width="19"height="19">
                            </a>
                        	<input type="hidden" value="${obj.contractName }"  style="width:100%;">
                        	<%--<a href="javascrept:void(0)" onclick="top.addTabGrid('合同修改', '/costContract/toEdit?editFlag=y&id=${obj.contractId}');">${obj.contractName }</a>--%>
                        
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>审价单位：</td>
                        <td colspan="5">
                            <input type="text" readonly="readonly" name="auditPriceUnit" value="${obj.auditPriceUnit }"  placeholder="自审的非全过程项目任务不用填" style="width:100%;">
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
                    <c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater and obj.status eq '新建') or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name)) or dataUpdate eq true}">
                    <tr style="height:40px">
                        <td colspan="6" style="text-align: center">
                            <input type="button" class="btn btn-primary" id="save" value="保存">&nbsp;&nbsp;
                            <input type="button" class="btn btn-success" onclick="top.closeWindow('审价任务添加');top.closeWindow('审价任务修改');top.closeWindow('审价任务')" value="取消">
                        </td>
                    </tr>
                    </c:if>
                </tbody>
            </table>
             <table  class="table_edit" style="margin-top: 1px;">
				<tr style="height: 48px;background-color: #cce8cf;">
			            <td colspan="6" style="text-align: left;position: relative">
			                <span class="title">历史审核意见记录</span>
			            </td>
			    </tr>
				<tr class="sonlist2" >
					<td style="padding:2px 2px;">
						 <div id="record_task" ></div>
					</td>
				</tr>
				<tr style="display: none;"><td></td></tr>
           </table>
    </form>
    <div id="tt" style="width:100%;">
        <div title="送审资料" class="subWrap">
            <table id="songshen" style="width: 100%">
            </table>
			<c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater and obj.status eq '新建') or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name)) or dataUpdate eq true}">
            <div id="tit1">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('songshen')">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog('songshen');">修改文件夹名称</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('songshen')">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('songshen')">上传文件</a>
            </div>
			</c:if>
        </div>
        <div title="审定资料" class="subWrap">
            <table id="shending" style="width: 100%">
            </table>
			<c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater and obj.status eq '新建') or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name)) or dataUpdate eq true}">
            <div id="tit2">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('shending')">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog('shending');">修改文件夹名称</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('shending')">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('shending')">上传文件</a>
            	<c:if test="${obj.auditPriceType eq '主材定价'}">
	            	<input type="button" name="reset" value="新增主材单价库导入模版下载" class="btn btn-info" onclick="importTemplateZc();">
            	</c:if>
            	<c:if test="${obj.auditPriceType eq '综合单价'}">
	                <input type="button" name="reset" value="新增项目综合单价导入模版下载" class="btn btn-info" onclick="importTemplateZh();">
            	</c:if>
            </div>
			</c:if>
        </div>
        <div title="往来文件" class="subWrap">
            <table id="wlfile" style="width: 100%">
            </table>
        </div>
        <div title="协调事项" class="subWrap">
            <table id="coordinationMatters" style="width: 100%">
            </table>
            <c:if test="${(param.editFlag eq 'y' and user.name eq obj.creater) or (param.editFlag eq 'y' and fn:contains(obj.personLiable,user.name)) or dataUpdate eq true}">
            <div id="tit4">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getTaskCoordinageDialog('添加协调事项')">新增</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getTaskCoordinageDialog('修改协调事项')">修改</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteTc()">删除</a>
            </div>
            </c:if>
        </div>
    </div>
</div>
<div id="taskDialog" style="background: #f4f8fb"></div>
<div id="taskCoordinateDialog"></div>
<div id="dlg"></div>
<div id="info_add"></div>
<div id="dialog"></div>
<div id="person"></div>
<div id="editDialog"></div>
<div id="project"></div>
<div id="contract"></div>
<%--
<script src="${pageContext.request.contextPath}/res/js/cost/task/priceCheck_add.js"></script>
--%>
<script src="${pageContext.request.contextPath}/res/js/cost/task/estimatePrefix_add.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/task/taskCoordinate.js"></script>
<script>
	var taskId="${obj.id }";
	var url="${url}";
	var status=$("#status").val();
	var auditPriceType ="${obj.auditPriceType }";
	var documentTime="${document.documentTimeStr}";
	var registrantTime="${document.registrantTimeStr}";
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
    $(function(){
    	var editFlag="${param.editFlag}";
    	if(editFlag=="n"){
			disableForm("formId",true);
		}
		if(status=="办内审核完"){
  			$("#submissionTime").show();
  			$("#submissionTimeStr").show();
  		}else{
  			$("#submissionTime").hide();
  			$("#submissionTimeStr").hide();
  			//$("#show_retrial").hide();
  		}
    	
	    $("#save").click(function(){
	    	
	    	var name= $("input[name='name']").val();
	    	var code= $("input[name='code']").val();
	    	var giveNumber=$("input[name='giveNumber']").val();
	    	var personLiable= $("input[name='personLiable']").val();
	    	var receiveTimeStr= $("input[name='receiveTimeStr']").val();
	    	
	    	var status=$("#status").val();
	    	
	    	if(name==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写审价任务名称！', icon: 'error', top:100});
	          	return;
		    }
	    	if(code==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写审价编号！', icon: 'error', top:100});
	          	return;
		    }
	    	if(receiveTimeStr==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写接收日期！', icon: 'error', top:100});
	          	return;
		    }
	    	var oDate1 = new Date(documentTime);
	    	var oDate2 = new Date(registrantTime);
	    	var oDate3 = new Date(receiveTimeStr);
	    	oDate3.setHours(23);
	    	if( oDate3.getTime() < oDate1.getTime() || oDate3.getTime() < oDate2.getTime()){
	    		$.messager.alert({title:'温馨提示', msg:'接收日期不能早于往来文件的接收日期和往来文件日期！', icon: 'error', top:100});
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
						  $("#shending").treegrid('reload');
						  url="/costTask/upd";
					  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
					  	  $("#record_task").datagrid('reload');
					  	  var json1={tabTitle:'单价审核',url:'/forward_task_priceCheck'};
						  window.parent.refreshTab(json1);
						  
					  }else if(result.status==600){
					  		$.messager.alert("温馨提示","系统已有该审价任务名称,请不要重复!", "error");
					  }else{
						  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
					  }
				  }
			});
		});
    });
    
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
    //删除合同
    function clearUnitContract(){
    	$("#containContractShowName").val("");
    	$("#contractName").val("");
    }
</script>
</body>
</html>