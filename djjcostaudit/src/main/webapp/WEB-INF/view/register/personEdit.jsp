<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>人员详情</title>
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
        <table class="table_edit" id="table" cellspacing="0" cellpadding="0" >
            <tbody>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场企业名称：</td>
                <td>
                    <input type="text" readonly="readonly"  value="${obj.enterpriseName }" placeholder="请输入驻场企业名称" style="width:80%;margin-top:5px;">
                    <a href="javascript:void(0)" class="fr" style="margin-top:5px;"><img src="../../img/project.png" alt="" width="19" height="19"></a>
                </td>
                <td class="bgcolor"><strong>*</strong>计划驻场开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" name="planStartTimeStr" value="<fmt:formatDate value="${obj.planStartTime}" pattern="yyyy-MM-dd"/>" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:50%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场人员姓名：</td>
                <td >
                    <input type="text" name="name" value="${obj.name}" placeholder="请输入驻场人员姓名" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>计划驻场结束时间：</td>
                <td>
                    <input class="Wdate search_text_form" name="planEndTimeStr" value="<fmt:formatDate value="${obj.planEndTime}" pattern="yyyy-MM-dd"/>" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:50%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>性别：</td>
                <td>
                	
                    <input type="radio"  value="男" id="man"  <c:if test="${obj.sex eq '男' }">checked</c:if>  name="sex">
                    <label for="man" style="font-weight: normal">男</label>
                    <input type="radio"  value="女" id="woman" <c:if test="${obj.sex eq '女' }">checked</c:if> name="sex">
                    <label for="woman" style="font-weight: normal">女</label>
                </td>
                <td class="bgcolor"><strong>*</strong>实际驻场开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" name="actualStartTimeStr" value="<fmt:formatDate value="${obj.actualStartTime}" pattern="yyyy-MM-dd"/>" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:50%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>驻场服务岗位：</td>
                <td>
                    <input type="text" name="job"  value="${obj.job }" placeholder="请输入驻场服务岗位" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>实际驻场结束时间：</td>
                <td>
                    <input class="Wdate search_text_form" name="actualEndTimeStr" value="<fmt:formatDate value="${obj.actualEndTime}" pattern="yyyy-MM-dd"/>" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:50%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>联系电话：</td>
                <td style="text-align: left">
                    <input type="text" name="phone"  value="${obj.phone }" placeholder="请输入联系电话" style="width:100%;" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" onblur="this.v();">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>累计驻场天数：</td>
                <td style="text-align: left">
                    <input type="text"  value="${obj.totalDay }" readonly="readonly" placeholder="累计驻场天数" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>邮箱：</td>
                <td style="text-align: left">
                    <div class="parentCls">
                        <input type="text" name="email" value="${obj.email }" placeholder="请输入邮箱"  style="width:100%;" id="email" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class="inputElem">
                    </div>
                </td>
                <td class="bgcolor"><strong>*</strong>是否有效：</td>
                <td style="text-align: left">

                    <input type="radio"  value="有效"  checked  name="effectiveFlag" <c:if test="${obj.effectiveFlag eq '有效' }">checked</c:if>>
                    <label for="effective" style="font-weight: normal">有效</label>
                    <input type="radio"  value="无效"  name="effectiveFlag" <c:if test="${obj.effectiveFlag eq '无效' }">checked</c:if>>
                    <label for="ineffective" style="font-weight: normal">无效</label>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>微信号：</td>
                <td style="text-align: left">
                    <input type="text" name="wechat"  value="${obj.wechat }" placeholder="请输入联系电话" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>创建人：</td>
                <td style="text-align: left">
                    <input type="text" name="creater" readonly="readonly"  value="${obj.creater }" placeholder="请输入创建人" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>年假开始时间：</td>
                <td style="text-align: left">
                    <input id="annualLeaveStartTimeStr" name="annualLeaveStartTimeStr" value="<fmt:formatDate value="${obj.annualLeaveStartTime}" pattern="yyyy-MM-dd"/>" class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:50%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>年假结束时间：</td>
                <td style="text-align: left">
                    <input id="annualLeaveEndTimeStr" name="annualLeaveEndTimeStr" value="<fmt:formatDate value="${obj.annualLeaveEndTime}" pattern="yyyy-MM-dd"/>" class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:50%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>可休年假（天）：</td>
                <td style="text-align: left">
                   <select class="form-control input-sm" style="width:20%;" name="annualLeaveTotal"  >
                    	<c:forTokens items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15" delims="," var="t">
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
                    <textarea name="remark" cols="3" rows="4">${obj.remark }</textarea>
                </td>
            </tr>
            <tr style="height:40px">
                <td colspan="4" style="text-align: center">
                    <input type="button" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
                    <input type="button" class="btn btn-success" onclick="top.closeWindow('驻场人员详情')" value="取消">
                </td>
            </tr>
            </tbody>
        </table>
    </form>

</div>
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
    $(function(){
    	$("#save").click(function(){
    		var enterpriseName=$("#enterpriseName").val();
			var planStartTimeStr=$("#planStartTimeStr").val();
			var name=$("#name").val();
			var sex=$("#sex").val();
			var actualStartTimeStr=$("#actualStartTimeStr").val();
			var phone=$("#phone").val();
			var effectiveFlag=$("#effectiveFlag").val();
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
           	 if(sex=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择性别！', icon: 'error', top:100});
               	return;
           	}
           	 if(actualStartTimeStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择实际驻场开始时间 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(phone==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写联系电话	 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(effectiveFlag=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择是否有效 ！', icon: 'error', top:100});
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
				  	 	var json1={tabTitle:'驻场企业',url:'/forward_workEnterprise_list'};
					 	window.parent.refreshTab(json1); 
				  	}else {
						$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  	}
			  	}
			});
		});
    	
    })
</script>
</body>
</html>