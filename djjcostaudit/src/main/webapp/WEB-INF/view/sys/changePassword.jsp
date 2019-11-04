<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
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
<div class="row">
	<form id="pwdForm"  method="post" >
            <table class="table_edit"  cellspacing="0" cellpadding="0" >
                <tbody>
                <tr>
                    <td class="bgcolor" style="width: 20%"><strong>*</strong>旧密码：</td>
                    <td>
                        <input style="width: 80%" name="oldPwd" type="password"  placeholder="旧密码" class="form-control input-sm" required="true" validType="length[1,30]"/>
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>*</strong>新密码：</td>
                    <td>
                       <input style="width: 80%" name="newPwd1" type="password"  placeholder="新密码" class="form-control input-sm" required="true" validType="length[1,30]"/>
                    </td>
                </tr>
                <tr>
                    <td class="bgcolor"><strong>*</strong>确认新密码：</td>
                    <td>
                       <input style="width: 80%" name="newPwd2" type="password"  placeholder="确认新密码" class="form-control input-sm" required="true" validType="length[1,30]"/>
                    </td>
                </tr>
                <tr>
                	<td colspan="2">
                		<div class="text-center">
				            <input type="button" value="保存" id="changePwd" class="btn btn-primary"  />
				            <input type="button" class="btn btn-danger" value="取消" onclick="parent.closeWindow('修改密码');" />
				        </div>
                	</td>
                </tr>
                </tbody>
            </table>
    </form>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#changePwd").on("click",function(e){
			$.ajax({
				  url: "/changePwd",
				  dataType: "json",
				  async: false,
				  data: $("#pwdForm").serialize(),
				  success: function(resMsg){
				    if(resMsg.flag){
				    	window.parent.showMessager("温馨提示", "<b>修改成功,下次登录生效!</b>", 5000, "slide");
				    }else
				    	$.messager.alert("温馨提示",resMsg.msg, "error");
				  }
			});
		});
	});
</script>
</html>