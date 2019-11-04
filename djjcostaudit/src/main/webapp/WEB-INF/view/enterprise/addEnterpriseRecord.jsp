<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加批次</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body style="padding: 0;">
<form action="" id="formId">
  <input type="hidden" name="id" value="${obj.id }">
  <input type="hidden" name="enterpriseId" value="${obj.enterpriseId }">
  <div class="tabox table-responsive float-table">
        <table class="table table-condensed table-bordered">
            <tbody>
            <c:if test="${add_edit eq 'add' }">
            <tr>
                <th><strong style="color:red">*</strong>年份：</th>
                <td>
               <select class="form-control input-sm" name="year">
	               	<c:forTokens items="2020,2019,2018,2017" delims="," var="i">
	                	<option <c:if test="${i eq obj.year }">selected = "selected"</c:if>  value="${i}">${i}</option>
	                </c:forTokens>
               </select>
            </td>
            </tr>
            </c:if>
        	<tr>
                <th><strong style="color:red">*</strong>状态：</th>
                <td>
		           <select class="form-control input-sm" name="status">
		               <c:forTokens items="在库,暂停,出库" delims="," var="i">
		               	<option <c:if test="${i eq obj.status }">selected = "selected"</c:if>  value="${i}">${i}</option>
		               </c:forTokens>
		           </select>
                </td>
            </tr>
            </tbody>
        </table>
        </div>
        <div class="form-group text-center" style="margin-top: 10px;">
		    <input type="button" class="btn btn-primary"  id="saveEr" value="确定">&nbsp;&nbsp;
		    <input type="button" class="btn btn-success" onclick="window.parent.$('#enterpriseRecordDialog').dialog('close');" id="cancelContainCon" value="取消">
		</div>
</form>
</body>
<script>
$(function() {

    $("#saveEr").click(function(){
    	var url="${url}";
	   	 $.ajax({
	         url: url,
	         type: 'POST',
	         data: $("#formId").serialize(),
	     	 success: function(result){
				  if(result.status==200){
				  	  window.parent.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	  window.parent.$('#enterpriseRecord').datagrid('reload');
				  	  window.parent.$('#enterpriseRecordDialog').dialog('close');
				  }else if(result.status==300){
					  $.messager.alert("温馨提示","已有该年份数据,不要重复添加!", "error");
				  }else{
					  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
	     });
   })

});
	
	
</script>
</html>