<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入库企业添加</title>
<jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-master/src/css/layui.css">
</head>
<script src="${pageContext.request.contextPath}/res/plugin/layui-master/src/layui.js"></script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //时间范围
        laydate.render({
            elem: '#enterpriseTime',range: '至'
        });
    });
</script>
<style type="text/css">
		
		.combo-panel {
		    overflow: auto;
		}
		.table_edit td:last-child td{
            border-right: 1px solid #dbe5ee;
            border-bottom: 1px solid #dbe5ee;
            
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
    <form action="" id="formId" >
    	<c:if test="${!empty obj.id}">
				<input type="hidden" value="${obj.id}" name="id" id="rukuEnId">
		</c:if>
        	<table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
                <tbody>
                    <tr>
                        <td style="width:12%;" class="bgcolor"><strong>*</strong>入库企业名称：</td>
                        <td style="width:38%;">
                            <input type="text" id="name" name="name" value="${obj.name}" placeholder="请输入入库企业名称" style="width:100%;">
                        </td>
                        <td style="width:12%;" class="bgcolor"><strong>*</strong>简称：</td>
                        <td style="width:38%;">
                            <input type="text" id="simpleName" name="simpleName" value="${obj.simpleName}" placeholder="请输入简称" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>企业编号：</td>
                        <td >
                            <input type="text" id="code" name="code" value="${obj.code}" placeholder="请输入企业编号" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong>公司固话：</td>
                        <td>
                            <input type="text" id="telephone" name="telephone" value="${obj.telephone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-/]+/,'');}).call(this)" onblur="this.v();"  placeholder="请输入固话" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>负责人：</td>
                        <td >
                            <input type="text" id="contacts" name="contacts" value="${obj.contacts}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5_/]+/,'');}).call(this)" onblur="this.v();"
                                   placeholder="请输入负责人" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>*</strong>负责人联系方式：</td>
                        <td>
                            <input type="text" id="contactsPhone" name="contactsPhone" value="${obj.contactsPhone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-/]+/,'');}).call(this)" onblur="this.v();"  placeholder="请输入负责人手机号" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor">投标联系人：</td>
                        <td >
                            <input type="text" id="bidder" name="bidder" value="${obj.bidder}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5_/]+/,'');}).call(this)" onblur="this.v();"
                                   placeholder="请输入投标联系人" style="width:100%;">
                        </td>
                        <td class="bgcolor">投标联系人联系方式：</td>
                        <td>
                            <input type="text" id="bidderPhone" name="bidderPhone" value="${obj.bidderPhone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_/]+/,'');}).call(this)" onblur="this.v();"  placeholder="请输入投标联系人联系方式" style="width:100%;">
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>公司传真：</td>
                        <td>
                            <input type="text" name="fax" value="${obj.fax}" placeholder="请输入传真" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong>服务邮箱：</td>
                        <td>
                       		<div class="parentCls">
                       		       <input type="text" name="email" value="${obj.email}" placeholder="请输入服务邮箱"  style="width:100%;" id="email" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class='inputElem'>
                       		</div>
                        </td>
                    </tr>
                    <tr>
                    	<td class="bgcolor">公司地址：</td>
                        <td>
                            <input type="text" id="address" name="address" value="${obj.address}" placeholder="请输入公司地址" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>*</strong>入库有效期：</td>
	                    <td>
	                    	<input class="Wdate search_text_form" type="text" name="enterpriseTime" id="enterpriseTime" value="<fmt:formatDate value="${obj.enterpriseStart}" pattern="yyyy-MM-dd"/><c:if test="${!empty obj.enterpriseStart and !empty obj.enterpriseEnd }"> 至 </c:if><fmt:formatDate value="${obj.enterpriseEnd}" pattern="yyyy-MM-dd"/>" style="width:50%;margin-right:20px">
	                    </td>
                    </tr>
                    <c:if test="${add_edit eq 'edit' }">
                    <tr>
			            <td class="bgcolor"><strong>&nbsp;</strong>是否驻场：</td>
			            <td>
			            	<input type="text" readonly="readonly" value="${obj.stationing}" style="width:100%;">
			            </td>
			            <td class="bgcolor"></td>
			            <td style="text-align: left">
			            </td>
			        </tr>
			        </c:if>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                        <td colspan="3">
                            <textarea name="description"cols="3" rows="4">${obj.description}</textarea>
                        </td>
                    </tr>
                    <c:if test="${(param.editFlag eq 'y' && user.name eq obj.creater or dataUpdate eq true) and editFlag eq 'y'}">
                    <tr style="height:40px">
                        <shiro:hasPermission name="order:update">
                        <td colspan="4" style="text-align: center">
                            <input type="button" id="save" class="btn btn-primary" onclick="" value="保存">&nbsp;&nbsp;
                            <input type="button" onclick="top.closeWindow('入库企业添加');top.closeWindow('入库企业修改')" class="btn btn-success" value="取消">
                        </td>
                        </shiro:hasPermission>
                    </tr>
                 </c:if>
                </tbody>
            </table>
            <c:if test="${add_edit eq 'edit'}">
            <table  class="table_edit" style="margin-top: 1px;">
				<tr style="height: 48px;background-color: #cce8cf;">
			            <td colspan="6" style="text-align: left;position: relative">
			                <span class="title">入库企业批次年份</span>
			            </td>
			    </tr>
			    
				<tr class="sonlist2" >
					<td style="padding:2px 2px;">
						 <div id="enterpriseRecord"></div>
				           <div id=tit7>
								<%--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addEnterpriseRecord()">新增批次</a>--%>
				                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="updEnterpriseRecord()">修改入库状态</a>
				                <%--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delEr()">删除批次</a>--%>
						   </div>
					</td>
				</tr>
			</table>
			</c:if>
    </form>
    <div id="tt" style="width:100%;">
    	<div title="资料" class="subWrap">
            <table id="material" style="width: 100%">
            </table>
            <div id="tit2">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog()">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog();">修改文件夹名称</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile()">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile()">上传附件</a>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>
        </div>
        
        <div title="服务项目统计" class="subWrap">
        	<table id="serviceProjectStatistics" style="width: 100%">
            </table>
            <div id="tit1">
                <div class="row">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
                          <div class="form-group">
                                <label for="">摇号批次：</label>
                                <input type="text" placeholder="请输入关键字" id="orderCode" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">中签项目名称：</label>
                                <input type="text"  id="enterpriseName" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">摇号年份：</label>
                                <select class="form-control input-sm" onchange="searchWin()" id="yaohaoYear">
                                    <option value="">请选择</option>
                                    <option value="2021" >2021</option>
                                    <option value="2020" >2020</option>
                                    <option value="2019" >2019</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">摇号时间：</label>
                                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                                <label for="">至</label>
                                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                            </div>
                            <div class="form-group">
                                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="searchWin()"/>&nbsp;
                                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick=""/>&nbsp;
                                <input type="button" name="daochu" value="导出" class="btn btn-danger" onclick="erExportDate1('${obj.code}')"/>&nbsp;
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn1"></a>
                </div>
            </div>
        </div>
        
        <div title="奖惩记录" class="subWrap">
            <table id="rewardsPunishRecord" style="width: 100%">
            </table>
            <div id="tit4">
                <div class="row">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
	                        <div class="form-group">
				               	<label for="">奖/惩：</label>
				               	<select class="form-control input-sm" onchange="searchPun()" id="punishFlag" style="width: 100px">
				                   	<option value="" >请选择</option>
				                   	<option value="奖励" >奖励</option>
				                   	<option value="惩罚" >惩罚</option>
				               	</select>
				           	</div>
				           	<div class="form-group">
				               	<label for="">履行状态：</label>
				               	<select class="form-control input-sm" onchange="searchPun()" id="status" style="width: 100px">
				                   	<option value="" >请选择</option>
				                   	<option value="正在执行" >正在执行</option>
				                   	<option value="结束" >结束</option>
				                   	<option value="取消" >取消</option>
				               	</select>
				           	</div>
				           	<div class="form-group">
                                <label for="">执行时间：</label>
                                <input id="executeStartTimeStr" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                            </div>
				           	<div class="form-group">
				                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchPun()">&nbsp;
				                <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="refresh()">&nbsp;
				                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate()">&nbsp;
				            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn2"></a>
                </div>
            </div>
       </div>
        
        <div title="驻场记录" class="subWrap">
            <table id="residentRecord" style="width: 100%">
            </table>
        </div>
        
        <div title="综合考核记录" class="subWrap">
            <table id="assessRecord" style="width: 100%">
            </table>
        </div>
    </div>
</div>
<div id="dlg"></div>
<div id="editDialog"></div>
<div id="dialog"></div>
<div id="dlgjc"></div>
<div id="enterpriseRecordDialog"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/enterprise/rukuEnterpriseAdd.js"></script>
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
    var url="${url}";
    var editFlag="${param.editFlag}";
    var code=$("#code").val();
    var name=$("#name").val();
    var workEnterpriseId="${workEnterpriseId}";
    var stationing="${obj.stationing}";
    var rukuEnId="${obj.id}";
    var rukuEnCode="${obj.code}";
	$(function(){
		if(editFlag=="n"){
			disableForm("formId",true);
		}
		$("#save").click(function(){
			var simpleName=$("#simpleName").val();
			var name=$("#name").val();
			var code=$("#code").val();
			var contacts=$("#contacts").val();
			var contactsPhone=$("#contactsPhone").val();
			var batch=$("#batch").val();
			var effectiveFlag=$("#effectiveFlag").val();
			var enterpriseTime=$("#enterpriseTime").val();
			var address=$("#address").val();
			if(simpleName==""){
	        	$.messager.alert({title:'温馨提示', msg:'请填写简称 ！', icon: 'error', top:100});
	        	return;
	    	}else if(name==""){
	        	$.messager.alert({title:'温馨提示', msg:'请填写入库企业名称 ！', icon: 'error', top:100});
	        	return;
	    	}else if(code==""){
	        	$.messager.alert({title:'温馨提示', msg:'请填写企业编号 ！', icon: 'error', top:100});
	        	return;
	    	}else if(contacts==""){
	        	$.messager.alert({title:'温馨提示', msg:'请填写法定代表人！', icon: 'error', top:100});
	        	return;
	    	}else if(contactsPhone==""){
	        	$.messager.alert({title:'温馨提示', msg:'请填写法定代表人手机号 ！', icon: 'error', top:100});
	        	return;
	    	}else if(batch=="请选择"){
	        	$.messager.alert({title:'温馨提示', msg:'请填写入库批次 ！', icon: 'error', top:100});
	        	return;
	    	}else if(enterpriseTime==""){
	        	$.messager.alert({title:'温馨提示', msg:'请选择入库有效期 ！', icon: 'error', top:100});
	        	return;
	    	}else if(effectiveFlag=="请选择"){
	        	$.messager.alert({title:'温馨提示', msg:'请选择入库状态 ！', icon: 'error', top:100});
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
				  		url="/costEnterprise/upd";
				  	 	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	 	var json1={tabTitle:'入库企业',url:'/forward_enterprise_list'};
					 	window.parent.refreshTab(json1);
				  	}else if(result.status==300){
				  			$.messager.alert({title:'温馨提示', msg:result.msg, icon: 'error', top:100});
	             					return;
				  	}else{
						$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  	}
			  	}
			});
		});
	});

	
	function savePunish() {
		var pUrl=fileFramePro.$("#pUrl").val();
		var punishFlag=fileFramePro.$("#punishFlag").val();
		var content=fileFramePro.$("#content").val();
		var handleOpinion=fileFramePro.$("#handleOpinion").val();
		var punishTime=fileFramePro.$("#punishTime").val();
		if(punishTime==""){
        	$.messager.alert({title:'温馨提示', msg:'请选择执行时间 ！', icon: 'error', top:100});
        	return;
    	}else if(punishFlag=="请选择"){
        	$.messager.alert({title:'温馨提示', msg:'请选择奖惩类型 ！', icon: 'error', top:100});
        	return;
    	}else if(content==""){
        	$.messager.alert({title:'温馨提示', msg:'请填写奖惩事由 ！', icon: 'error', top:100});
        	return;
    	}else if(handleOpinion==""){
        	$.messager.alert({title:'温馨提示', msg:'请填写处理意见 ！', icon: 'error', top:100});
        	return;
    	}
		$.ajax({
		  	url:pUrl,
		  	dataType: "json",
		  	type:"post",
		  	async: false,
		  	data: fileFramePro.$("#formId2").serialize(),
		  	success: function(result){
			  	if(result.status==200){
                    $('#dlgjc').dialog('close');
                    $('#rewardsPunishRecord').datagrid('reload');
			  	 	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			  	 	//var json1={tabTitle:'入库企业',url:'/forward_enterprise_list'};
				 	//window.parent.refreshTab(json1);
			  	}else if(result.status==300){
			  			$.messager.alert({title:'温馨提示', msg:result.msg, icon: 'error', top:100});
             					return;
			  	}else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  	}
		  	}
		});
	};
	
	function addEnterpriseRecord() {
	    $('#enterpriseRecordDialog').dialog({
	        title: '新增批次',
	        width: 400,
	        height: 220,
	        closed: false,
	        cache: false,
	        top:100,
	        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/costEnterprise/toAddEr?enterpriseId='+rukuEnId+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
	        modal: true
	    });
	}
	function updEnterpriseRecord() {
		var selections = $('#enterpriseRecord').datagrid('getSelections');
		if (selections.length != 1) {
	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
	        return;
	    }
        var myDate = new Date();
        var oneYear = myDate.getFullYear();

       /* var selectionsOne = $('#enterpriseRecord').datagrid('getRows')[0];
		var oneYear=selectionsOne.year;
		console.log(oneYear);
		console.log(selections);*/
		if(selections[0].year<oneYear){
			 $.messager.alert('提示信息', '不能修改历史入库状态', 'warn');
		     return;
		}
		var id=selections[0].id;
	    $('#enterpriseRecordDialog').dialog({
	        title: '修改入库状态',
	        width: 400,
	        height: 180,
	        closed: false,
	        cache: false,
	        top:100,
	        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/costEnterprise/toAddEr?id='+id+'&enterpriseId='+rukuEnId + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
	        modal: true
	    });
	}
</script>
<script src="${pageContext.request.contextPath}/res/js/cost/enterprise/attachment.js"></script>
</body>
</html>