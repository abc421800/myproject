<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加驻场企业</title>
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
				<input type="hidden" value="${obj.id}" name="id" id="enterpriseId">
		</c:if>
        <table class="table_edit" id="table" cellspacing="0" cellpadding="0" >
            <tbody>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场企业名称：</td>
                <td>
                    <input type="text" id="name" name="name" value="${obj.name}" placeholder="请选择或输入驻场企业名称" style="width:80%;margin-top:5px;">
                    	<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="clearUnitEnterprise();">
		            		<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
		            	</a>
						<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="unitEnterprise()">
		            		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
		            	</a>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>负责人：</td>
                <td>
                    <input type="text" id="personLiable" name="personLiable" value="${obj.personLiable}" placeholder="请输入负责人" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>企业类型：</td>
                <td>
					<select id="type" name="type" class="custom-select">
	                	<c:forTokens items="请选择,造价咨询,招标代理,工程咨询,工程监理,勘察设计,建材供货商,检测机构,其他" delims="," var="i">
	                    	<option <c:if test="${i eq obj.type}">selected = "selected"</c:if>  value="${i}">${i}</option>
	                    </c:forTokens>
	                </select>
				</td>
                <td class="bgcolor"><strong>&nbsp;</strong>手机：</td>
                <td>
                    <input type="text" id="phone" name="phone" value="${obj.phone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" placeholder="请输入联系电话" style="width:100%;">
                </td>
            </tr>
            <tr>

                <td class="bgcolor"><strong>&nbsp;</strong>联系地址：</td>
                <td>
                    <input type="text" id="address" name="address" value="${obj.address}" placeholder="请输入联系地址" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>*</strong>固话：</td>
                <td>
                    <input type="text" id="telephone" name="telephone" value="${obj.telephone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" placeholder="请输入联系电话" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>服务开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" id="serviceStartStr" name="serviceStartStr" value="${fn:substring(obj.serviceStartStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>传真：</td>
                <td>
                    <input type="text" id="fax" name="fax" value="${obj.fax}" placeholder="请输入传真" style="width:100%;">
                </td>
            </tr>

            <tr>
                <td class="bgcolor"><strong>*</strong>服务结束时间：</td>
                <td style="text-align: left">
                    <input class="Wdate search_text_form" id="serviceEndStr" name="serviceEndStr" value="${fn:substring(obj.serviceEndStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>邮箱：</td>
                <td style="text-align: left">
                    <div class="parentCls">
                        <input type="text" id="email" name="email" value="${obj.email}" placeholder="请输入邮箱"  style="width:100%;" id="email" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class="inputElem">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>是否有效：</td>
                <td style="text-align: left">
                    <select id="effectiveFlag" name="effectiveFlag" class="custom-select" style="width:100px;">
                        <c:forTokens items="请选择,有效,无效" delims="," var="i">
                        	<option <c:if test="${i eq obj.effectiveFlag}">selected = "selected"</c:if>  value="${i}">${i}</option>
                     	</c:forTokens>
                    </select>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>创建人：</td>
                <td style="text-align: left">
                    <input type="text" name="creater" value="${obj.creater}" placeholder="请输入创建人" style="width:100%;">
                </td>

            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>累计派出驻场人员：</td>
                <td style="text-align: left">
                    <input type="text" name="sendPerson" readonly="readonly" value="${obj.sendPerson}" placeholder="累计派出驻场人员汇总数" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>当前有效派出人员：</td>
                <td style="text-align: left">
                    <input type="text" name="sendPersonEffective" readonly="readonly" value="${obj.sendPersonEffective}" placeholder="当前有效派出人员汇总数" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                <td colspan="3">
                    <textarea id="remark" name="remark" cols="3" rows="4">${obj.remark}</textarea>
                </td>
            </tr>
            <shiro:hasPermission name="workEnterprise:update">
	            <tr style="height:40px">
	                <td colspan="4" style="text-align: center">
	                    <input type="button" id="save" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
	                    <input type="button" class="btn btn-success" onclick="top.closeWindow('添加驻场企业');top.closeWindow('驻场企业修改')" value="取消">
	                </td>
	            </tr>
            </shiro:hasPermission>
            </tbody>
        </table>
    </form>
    <c:if test="${add_edit eq 'edit'}">
    <div id="tt" style="width:100%;">
        <div title="驻场人员" class="subWrap">
            <div id="t0" style="margin-bottom: 5px">
                <div class="row">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">姓名：</label>
                                <input type="text" id="namePer" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <select style="font-weight: bold" id="shenTime" class="form-control input-sm" name="shenTime">
                                    <option value="planStartTime">计划驻场时间</option>
                                    <option value="actualStartTime">实际驻场时间</option>
                                </select>
                                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                                <label for="">至</label>
                                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">

                            </div>
                            <div class="form-group">
			                	<label for="">是否有效：</label>
			                	<select class="form-control input-sm" onchange="searchPer()" id="effectiveFlagPer" style="width: 100px">
			                    	<option value="">请选择</option>
			                    	<option value="有效" >有效</option>
			                    	<option value="无效" >无效</option>
			                	</select>
			            	</div>
                            <div class="form-group">
                                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchPer()">&nbsp;
                                <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="Refresh()">&nbsp;
                                <input type="button" name="button" value="导出" class="btn btn-danger" onclick="exportDatePer();">&nbsp;
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn1"></a>
                </div>
            </div>
            <table id="residentPerson" style="width: 100%">
            </table>
            <div id="tit1">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addPerson()">添加</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandlerPerson()">删除</a>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>
        </div>
    </div>
    </c:if>
</div>
<div id="unitEnterprise"></div>
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
    var enterpriseId="${obj.id}";
    var effectiveFlag="${obj.effectiveFlag}";
    var url="${url}";
    $(function(){
    	$("#save").click(function(){
    		var name=$("#name").val();
			var telephone=$("#telephone").val();
			var serviceStartStr=$("#serviceStartStr").val();
			var serviceEndStr=$("#serviceEndStr").val();
			var effectiveFlag=$("#effectiveFlag").val();
			var type=$("#type").val();
			if(name==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写驻场企业名称 ！', icon: 'error', top:100});
               	return;
           	}
			 if(type=="请选择"){
               	$.messager.alert({title:'温馨提示', msg:'请选择驻场企业类型 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(telephone==""){
               	$.messager.alert({title:'温馨提示', msg:'请填写固话 ！', icon: 'error', top:100});
               	return;
           	}
           	 if(serviceStartStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择服务开始时间！', icon: 'error', top:100});
               	return;
           	}
           	 if(serviceEndStr==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择服务结束时间！', icon: 'error', top:100});
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
				  		url="/workEnterprise/upd";
					 	var href = "/workEnterprise/toEdit?editFlag=y&id=${obj.id}";
					    var title = "驻场企业修改";
					    top.addTabGrid(title, href);
				  	 	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");	
				  	 	var json1={tabTitle:'驻场企业',url:'/forward_workEnterprise_list'};
					 	window.parent.refreshTab(json1); 
					 	top.closeWindow('添加驻场企业');
				  	}else if(result.status==300){
				  		$.messager.alert("温馨提示","请不要添加重复的驻场企业!", "error");
				  	}else {
						$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  	}
			  	}
			});
		});
    	
    })
    //删除驻场企业名称
	function clearUnitEnterprise(){
		$("#name").val("");
	    $("#personLiable").val("");
	    $("#phone").val("");
	    $("#address").val("");
	    $("#email").val("");
	    $("#fax").val("");
	    $("#remark").val("");
	}
  	//审价单位
    function unitEnterprise() {
        $('#unitEnterprise').dialog({
            title: '驻场企业',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_workEnterprise_containEnterprise'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
  	//重置
    function Refresh() {
		var json1={tabTitle:'驻场企业修改',url:'/workEnterprise/toEdit?id='+enterpriseId};
		window.parent.refreshTab(json1); 
	}
	//导出
	function exportDatePer(){
		window.location.href = "${path}/workPerson/exportPerson?enterpriseId="+enterpriseId;
	}
</script>
<script src="${pageContext.request.contextPath}/res/js/work/workEnterprise/add.js"></script>
</body>
</html>