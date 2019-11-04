<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>驻场人员详情</title>
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
    	<c:if test="${!empty obj.id}">
				<input type="hidden" value="${obj.id}" name="id" id="id">
		</c:if>
        <table class="table_edit" id="table" cellspacing="0" cellpadding="0" >
            <tbody>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场企业名称：</td>
                <td>
                	<input type="hidden" value="${obj.enterpriseId}" name="enterpriseId">
                    <input type="text" value="${obj.enterpriseName}" readonly="readonly" placeholder="请输入驻场企业名称" style="width:80%;margin-top:5px;">
                </td>
                <td class="bgcolor"><strong>*</strong>计划驻场开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" id="planStartTimeStr" name="planStartTimeStr" value="${fn:substring(obj.planStartTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'planEndTimeStr\')}'})">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场人员姓名：</td>
                <td>
                    <input type="text" id="name" name="name" value="${obj.name}" placeholder="请选择或输入驻场人员姓名" style="width:80%;margin-top:5px;">
                    	<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="clearUnitUser();">
		            		<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
		            	</a>
						<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="unitUser()">
		            		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
		            	</a>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>计划驻场结束时间：</td>
                <td>
                    <input class="Wdate search_text_form" id="planEndTimeStr" name="planEndTimeStr" value="${fn:substring(obj.planEndTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'planStartTimeStr\')}'})">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>账号：</td>
                <td >
                    <input type="text" id="account" name="account" value="${obj.account}" placeholder="请输入账号" style="width:100%;" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class="inputElem">
                </td>
                <td class="bgcolor"><strong>*</strong>实际驻场开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" id="actualStartTimeStr" name="actualStartTimeStr" value="${fn:substring(obj.actualStartTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'actualEndTimeStr\')}'})">
                </td>
            </tr>
            <tr>

                <td class="bgcolor"><strong>&nbsp;</strong>驻场服务岗位：</td>
                <td>
                    <input type="text" id="job" name="job" value="${obj.job}" placeholder="请输入驻场服务岗位" style="width:100%;">
                </td>
                <td class="bgcolor"><strong id="actualEndTimeStr_star">&nbsp;</strong>实际驻场结束时间：</td>
                <td>
                    <input class="Wdate search_text_form" id="actualEndTimeStr" name="actualEndTimeStr" value="${fn:substring(obj.actualEndTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'actualStartTimeStr\')}'})"})">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>联系电话：</td>
                <td style="text-align: left">
                    <input type="text" id="phone" name="phone" value="${obj.phone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" placeholder="请输入联系电话" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>累计驻场天数（天）：</td> 
                <td style="text-align: left">
                    <input type="text" id="totalDay" readonly="readonly" name="totalDay" value="${fn:replace(obj.totalDay, '.0', '')}" placeholder="累计驻场天数" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>性别：</td>.
                <td style="text-align: left">
                    <select id="sex" name="sex" class="custom-select" style="width:100px;">
                        <c:forTokens items="请选择,男,女" delims="," var="i">
                        	<option <c:if test="${i eq obj.sex}">selected = "selected"</c:if>  value="${i}">${i}</option>
                     	</c:forTokens>
                    </select>
                </td>
                <td class="bgcolor"><strong>*</strong>是否有效：</td>
                <td style="text-align: left">
                    <select id="effectiveFlag" name="effectiveFlag" class="custom-select" onchange="effectiveFlagChange();" style="width:100px;">
                        <c:forTokens items="请选择,有效,无效" delims="," var="i">
                        	<option <c:if test="${i eq obj.effectiveFlag}">selected = "selected"</c:if>  value="${i}">${i}</option>
                     	</c:forTokens>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>微信号：</td>
                <td style="text-align: left">
                    <input type="text" id="wechat" name="wechat" value="${obj.wechat}" placeholder="请输入微信号" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>邮箱：</td>
                <td style="text-align: left">
                    <div class="parentCls">
                        <input type="text" name="email" value="${obj.email}" placeholder="请输入邮箱"  style="width:100%;" id="email" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class="inputElem">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>年假开始时间：</td>
                <td style="text-align: left">
                    <input id="annualLeaveStartTimeStr" name="annualLeaveStartTimeStr" value="<fmt:formatDate value="${obj.annualLeaveStartTime}" pattern="yyyy-MM-dd"/>" class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'annualLeaveEndTimeStr\')}'})" style="width:50%;">
                </td>
                <td class="bgcolor"><strong>*</strong>年假结束时间：</td>
                <td style="text-align: left">
                    <input id="annualLeaveEndTimeStr" name="annualLeaveEndTimeStr" value="<fmt:formatDate value="${obj.annualLeaveEndTime}" pattern="yyyy-MM-dd"/>" class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'annualLeaveStartTimeStr\')}'})" style="width:50%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>总年假（天）：</td>
                <td style="text-align: left">
                   <select class="form-control input-sm" id="annualLeaveTotal" style="width:20%;" name="annualLeaveTotal"  >
                    	<c:forTokens items="请选择,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15" delims="," var="t">
	                    	<option <c:if test="${t eq obj.annualLeaveTotal}"> selected="selected"</c:if> value="${t}">${t}</option>
                   		</c:forTokens>
                   </select>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>已休年假（天）：</td>
                <td style="text-align: left">
                    <input type="text" name="annualLeaveUseup" readonly="readonly"  value="${obj.annualLeaveUseup }" placeholder="已休年假" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                <td colspan="3">
                    <textarea name="remark"cols="3" rows="4">${obj.remark}</textarea>
                </td>
            </tr>
            <tr style="height:40px">
                <td colspan="4" style="text-align: center">
                    <input type="button" id="save" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
                    <input type="button" class="btn btn-success" onclick="top.closeWindow('添加驻场人员');top.closeWindow('驻场人员修改');" value="取消">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="unitUser"></div>
<script>
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
    var enterpriseId="${obj.enterpriseId}";
    var url="${url}";
    $(function(){
    	$("#save").click(function(){
    		var enterpriseName=$("#enterpriseName").val();
			var planStartTimeStr=$("#planStartTimeStr").val();
			var name=$("#name").val();
			var phone=$("#phone").val();
			var sex=$("#sex").val();
			var actualStartTimeStr=$("#actualStartTimeStr").val();
			var account=$("#account").val();
			var effectiveFlag=$("#effectiveFlag").val();
			var annualLeaveTotal=$("#annualLeaveTotal").val();
			var actualEndTimeStr=$("#actualEndTimeStr").val();
			var annualLeaveStartTimeStr=$("#annualLeaveStartTimeStr").val();
			var annualLeaveEndTimeStr=$("#annualLeaveEndTimeStr").val();
			if(enterpriseName==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写驻场企业名称 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(planStartTimeStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择计划驻场开始时间 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(name==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写驻场人员姓名！', icon: 'error', top:100});
               	return;
           	}
           	 if(account==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写账号！', icon: 'error', top:100});
               	return;
           	}
           	 if(actualStartTimeStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择实际驻场开始时间！', icon: 'error', top:100});
               	return;
           	}
           	 if(phone==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写联系电话！', icon: 'error', top:100});
               	return;
           	}
           	 if(sex=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择性别！', icon: 'error', top:100});
               	return;
           	}
           	 if(effectiveFlag=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择是否有效！', icon: 'error', top:100});
               	return;
           	}else if(effectiveFlag=="无效"){
           		if(actualEndTimeStr==""){
           			$.messager.alert({title:'温馨提示', msg:'请选择实际驻场结束时间！', icon: 'error', top:100});
               		return;
           		}
           	}
           	 if(annualLeaveStartTimeStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写年假开始时间！', icon: 'error', top:100});
               	return;
           	}
           	 if(annualLeaveEndTimeStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写年假结束时间！', icon: 'error', top:100});
               	return;
           	}
           	 if(annualLeaveTotal=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择总年假天数！', icon: 'error', top:100});
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
				  		url="/workPerson/upd";
				  	 	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");	
				  	 	var json1={tabTitle:'驻场企业修改',url:'/workEnterprise/toEdit?id='+enterpriseId};
						window.parent.refreshTab(json1); 
				  	}else if(result.status==300){
				  		$.messager.alert("温馨提示","请不要添加重复的驻场人员账号!", "error");
				  	}else if(result.status==400){
				  		$.messager.alert("温馨提示","请不要添加重复的驻场人员姓名!", "error");
				  	}else {
						$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  	}
			  	}
			});
		});
    	
    })
    //删除驻场人员姓名
	function clearUnitUser(){
		$("#name").val("");
   	    $("#account").val("");
   	    $("#phone").val("");
   	    $("#sex").val("");
   	    $("#wechat").val("");
   	    $("#email").val("");
	}
	//用户列表
    function unitUser() {
        $('#unitUser').dialog({
            title: '用户列表',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_workEnterprise_containUser'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    
    function effectiveFlagChange(){
  		var effectiveFlag=$("#effectiveFlag").val();
  		if(effectiveFlag=="无效"){
  			$("#actualEndTimeStr_star").text("*");
  		}else{
  			$("#actualEndTimeStr_star").text("");
  		}
  	}

</script>
</body>
</html>