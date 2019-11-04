<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head>
  <title>合同类型添加</title>
   <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
  </head>
  
  <body>
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
		<form method="post" id="form">
			<div class="tabox table-responsive float-table">
				<input type="hidden" id="currentId" name="id" value="${obj.id}" />
				<table class="table table-bordered table-condensed">
					<tr>
						<th class="text-right">合同类型名称</th>
						<td class="text-left">
						<span>
							<input name="name" id="name" value="${obj.name }" type="text" class="input-sm form-control" />
						</span></td>
					</tr>
					<tr>
						<th class="text-right">排序号</th>
						<td class="text-left">
							<span>
								<input name="num" id="num" value="${obj.num}" type="text" class="input-sm form-control" />
							</span>
						</td>
					</tr>
					<tr>
						<th class="text-right">描述</th>
						<td class="text-left">
							<span>
								<textarea name="description"  rows="5" cols="70" class="form-control">${obj.description}</textarea>
							</span>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="row">
				<div class="col-md-12 text-center">
					<input type="button" class="btn btn-primary btn-lg" id="save" value="保存"/>
					<input type="button" class="btn btn-success btn-lg" onClick="top.closeWindow('合同类型添加');top.closeWindow('合同类型修改')" value="返回" />
				</div>
			</div>
		</form>
    </div>
    </div>
    <script>
    $(function(){
    var url="${url}";
		$("#save").click(function(){
			var name=$("#name").val();
	    	var num=$("#num").val();
	    	if(name==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写合同类型名称！', icon: 'error', top:100});
	          	return;
		    }
	    	if(num==""){
	          	$.messager.alert({title:'温馨提示', msg:'请填写排序号！', icon: 'error', top:100});
	          	return;
		    }
			$.ajax({
				  url:url,
				  dataType: "json",
				  type:"post",
				  async: false,
				  data: $("#form").serialize(),
				  success: function(result){
					  if(result.status==200){
						  var json1={tabTitle:'合同类型',url:'/forward_contractType_list'};
						  window.parent.refreshTab(json1);
					  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
					  	  url="/costContractType/upd";
					  }else{
						  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
					  }
				  }
			});
		});
	   
	});
   
    </script>
  </body>
</html>
