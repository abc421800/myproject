<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>惩罚记录</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-master/src/css/layui.css">
</head>
<script src="${pageContext.request.contextPath}/res/plugin/layui-master/src/layui.js"></script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //时间范围
        laydate.render({
            elem: '#punishTime',range: '至'
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
    <form action="" id="formId1">
    	<input type="hidden" value="${obj.id}" name="id" id="punishId">
        <table class="table_edit" id="" cellspacing="0" cellpadding="0" style="margin-bottom: 2px">
                <tbody>
					<tr>
						<td class="bgcolor"><strong>*</strong>入库企业：</td>
                        <td colspan="5">
                        	<input type="hidden" id="enterpriseId" value="${obj.enterpriseId }" name="enterpriseId">
                            <input type="text" id="enterpriseName" readonly="readonly" name="enterpriseName"  value="${obj.enterpriseName }" placeholder="请选择入库企业" style="margin-top:5px;width:80%;">
                           	<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="clearEnterprise();">
		            			<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
			            	</a>
							<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="addEnterprise()">
			            		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
			            	</a>
                        </td>
                        <%--
                        <td class="bgcolor" style="width:10%;"><strong></strong>入库批次：</td>
                        <td style="text-align: left;width: 20%">
                            <select id="punishFlag" name="punishFlag" class="form-control input-sm easyui-validatebox">
		                        <c:forTokens items="2019,2018,2017" delims="," var="i">
		                        	<option <c:if test="${i eq obj.punishYear}">selected = "selected"</c:if>  value="${i}">${i}</option>
		                        </c:forTokens>
                       		</select>
                        </td>
					--%></tr>
                    <tr>
                        <td class="bgcolor" style="width:10%;"><strong>*</strong>执行时间：</td>
                        <td style="width:20%;">
                           <input class="Wdate search_text_form" type="text" name="punishTimeStr" id="punishTime" value="<fmt:formatDate value="${obj.executeStartTime}" pattern="yyyy-MM-dd"/><c:if test="${!empty obj.executeStartTime and !empty obj.executeEndTime}"> 至 </c:if><fmt:formatDate value="${obj.executeEndTime}" pattern="yyyy-MM-dd"/>" >
                        </td>
                        <td class="bgcolor" style="width:10%;"><strong>*</strong>奖惩类型：</td>
                        <td style="text-align: left;width: 20%">
                            <select id="punishFlag" name="punishFlag" class="form-control input-sm easyui-validatebox">
		                        <c:forTokens items="奖励,惩罚" delims="," var="i">
		                        	<option <c:if test="${i eq obj.punishFlag}">selected = "selected"</c:if>  value="${i}">${i}</option>
		                        </c:forTokens>
                       		</select>
                        </td>
                        <td class="bgcolor" style="width:10%;"><strong>*</strong>履行状态：</td>
                        <td style="width:20%">
                           <select id="status" name="status" class="form-control input-sm easyui-validatebox">
		                        <c:forTokens items="正在执行,结束,取消" delims="," var="i">
		                        	<option <c:if test="${i eq obj.status}">selected = "selected"</c:if>  value="${i}">${i}</option>
		                        </c:forTokens>
                      	   </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>奖惩事由：</td>
                        <td colspan="5">
                            <textarea name="content" cols="5" rows="2" placeholder="请输入奖惩事由">${obj.content}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>处理意见：</td>
                        <td colspan="5">
                            <textarea name="handleOpinion" cols="5" rows="2" placeholder="请输入处理意见">${obj.handleOpinion}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                        <td colspan="5">
                            <textarea name="remark" cols="5" rows="2" placeholder="请输入备注">${obj.remark}</textarea>
                        </td>
                    </tr>
	                <tr style="height:40px" class="saveTrId">
		                <td colspan="6" style="text-align: center">
		                    <input type="button" class="btn btn-primary" value="保存" id="save">&nbsp;&nbsp;
		                    <input type="button" class="btn btn-success" onclick="top.closeWindow('惩罚记录添加');top.closeWindow('惩罚记录修改');" value="取消">
		                </td>
		            </tr>
                </tbody>
            </table>
        </form>
        <div id="tt" style="width:100%;">
            <div title="奖惩资料" class="subWrap">

                <table id="fujian" style="width: 100%">
                </table>

                <div id="tit1">
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog()">添加文件夹</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog();">修改文件夹名称</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile()">删除</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile()">上传附件</a>
                    <%--<input type="button" name="reset" value="《摇珠会议通知模版》下载" class="btn btn-info" onclick="">--%>
                    <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
                </div>

            </div>
        </div>
</div>
<div id="dlg"></div>
<div id="dialog"></div>
<div id="enterpriseDialog" style="overflow-y: hidden"></div>
<script type="text/javascript">
var url="${url}";
var orderId="${obj.id}";
$(function(){
	$("#save").click(function(){
		var enterpriseId=$("#enterpriseId").val();
		var enterpriseName=$("#enterpriseName").val();
		var punishFlag=$("#punishFlag").val();
		var content=$("#content").val();
		var handleOpinion=$("#handleOpinion").val();
		var punishTime=$("#punishTime").val();
		
		if(enterpriseName==""){
           	$.messager.alert({title:'温馨提示', msg:'请填选择入库企业 ！', icon: 'error', top:100});
           	return;
       	}
		if(punishTime==""){
        	$.messager.alert({title:'温馨提示', msg:'请选择执行时间 ！', icon: 'error', top:100});
        	return;
    	}
		if(punishFlag=="请选择"){
        	$.messager.alert({title:'温馨提示', msg:'请选择奖惩类型 ！', icon: 'error', top:100});
        	return;
    	}
		$.ajax({
			  url: url,
			  dataType: "json",
			  type:"post",
			  async: false,
			  data: $("#formId1").serialize(),
			  success: function(result){
				  if(result.status==200){
					  url="/yaohaoPunish/upd";
					  var json1={tabTitle:'奖惩记录',url:'/yaohaoPunish/toPunishList'};
					  window.parent.refreshTab(json1); 
					  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");	
				  }else{
					  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
		});
	});
})

function addEnterprise() {
    $('#enterpriseDialog').dialog({
        title: '入库企业',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:50,
        content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_yaohao_punish_containEnterprise'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
function clearEnterprise(){
	$("#enterpriseId").val("");
	$("#enterpriseName").val("");
}

   
</script>
<script src="${pageContext.request.contextPath}/res/js/yaohao/yaohaoMng/punishAttachment.js"></script>
</body>
</html>