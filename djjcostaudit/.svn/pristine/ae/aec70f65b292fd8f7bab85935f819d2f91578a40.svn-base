<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/jquery.form.min.js"></script>
	<style>
        .imgChange{
            width:180px;height:200px;border:1px solid #c0c0c0;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            background-color:white;
            box-sizing: border-box;
            padding:15px 0;
            text-align: center;
            -webkit-box-shadow: 0 2px 4px 0 rgba(0,0,0,.12);
		    -moz-box-shadow: 0 2px 4px 0 rgba(0,0,0,.12);
		    box-shadow: 0 2px 4px 0 rgba(0,0,0,.12);
        }
        .imgChange img{
            width:128px;
            height:128px;
            -webkit-border-radius: 50%;
            -moz-border-radius: 50%;
            border-radius: 50%;
            margin-bottom:10px;
        }
        .info{
            width:calc(100% - 200px);
        }
        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
        label{
            color:red
        }
    </style>
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
<form  id="formId" enctype="multipart/form-data" onsubmit="return false">
    <div class="imgChange fl">
    <c:choose>
    	<c:when test="${user.headImage eq null or user.headImage eq '' }">
    	 <img id="headImage" src="${pageContext.request.contextPath}/res/images/sys/user_img.png" alt="" >
    	</c:when>
    	<c:otherwise><img id="headImage" src="${pageContext.request.contextPath}/file/${user.headImage}"></c:otherwise>
    </c:choose>
       
        <a href="javascript:void(0);" class="file" onclick="uploadFile();">更换头像<%--
            <input type="file" name="file" id="file" >
        --%></a>
    </div>
    <div class="info fr">
        <div class="tabox  float-table" >
            <table class="table table-hover table-condensed table-bordered">
                <tr>
                    <th>账号：</th>
                    <td>
                    <input class="form-control input-sm" value="${user.account}" disabled name="account" onkeypress="if(event.keyCode==13){this.blur();return false;}" vld="{required:true,rangelength:[2,20],username:true,remote:'checkUsername.do',messages:{remote:'用户名已存在！'}}"  tabindex="100" type="text">
                    <input type="hidden" name="id" value="${user.id}">
                    </td>
                    <th>姓名：</th>
                    <td><input class="form-control input-sm" value="${orgName}" name="name" onkeypress="if(event.keyCode==13){this.blur();return false;}" vld="{number:true}"  tabindex="100" type="text"></td>
                </tr>
                <tr>
                    <th>部门：</th>
                    <td><input class="form-control input-sm" disabled name="department" value="${orgName}" onkeypress="if(event.keyCode==13){this.blur();return false;}" vld=""  tabindex="100" type="text">
                    </td>
                    <th>电话：</th>
                    <td><input class="form-control input-sm" name="phone" value="${user.phone}"  onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"  tabindex="100" type="text"></td>
                </tr>
                <tr>
                    <th>性别：</th>
                    <td>
                        <label style="color:#333">
                        <input type="radio" name="sex" <c:if test="${user.sex eq '男' }">checked="checked"</c:if>  id="optionsRadios1" value="男">
                            		男
                        </label>
                        <label style="color:#333">
                            <input type="radio" name="sex" <c:if test="${user.sex eq '女' }">checked="checked"</c:if> id="optionsRadios2"  value="女">
                            		女
                        </label>
                    </td>
                    <th>微信：</th>
                    <td><input class="form-control input-sm" value="${user.wechat}" name="wechat" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9+a-z+A-Z+_]+/,'');}).call(this)" onblur="this.v();"  tabindex="100" type="text"></td>
                </tr>
                <tr>
                    <th>邮箱：</th>
                    <td colspan="3">
                    <div class="parentCls">
	                    <input style="height:30px;" class="inputElem form-control input-sm"  value="${user.email}" name="email" tabindex="100" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9+-.+a-z+A-Z+@+_]+/,'');}).call(this)" onblur="this.v();">
                    </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                    	<div class="buttonbox" style="text-align: center">
			                <div class="btn-group">
			                    <input value="保存" id="save" class="btn btn-primary btn-lg" type="button">
			                </div>
			            </div>
                    </td>
                </tr>
            </table>

        </div>
    </div>
</form>
</div>
<div id="dialog"></div>
</body>
<script type="text/javascript">
var headImgUrl='';
$(function(){
	
	$("#save").click(function(){
		$.ajax({
		  	url: "/sysUser/upd",
		  	dataType: "json",
		  	type:"post",
		  	async: false,
		  	data: $("#formId").serialize(),
		  	success: function(result){
			  	if(result.status==200){
			  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			  	}else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  	}
		  	}
		});
	});
});
function doUpload(){  
	//ajax
   	var options={
   		url:"${path}/htProcess/submitTask2.do",
   		type:'post',
   		dataType : 'JSON',
   		data:htProcess,
   		success: function(data){
			  if(data.status == 200){
				    if(data.msg){
				    	$.messager.alert("温馨提示",data.msg, "error");
				    }else{
				    	parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				    	$(".btn-primary").hide();
						$(".btn-success").hide();
				    }
				    
      	  		}else{
  	    	    	$.messager.alert("温馨提示","操作异常,请联系管理员", "error");
  	    	  }
		  }
   			
   	};
   	$("#contracts").ajaxSubmit(options); 
}
function uploadFile(){
    var url = "/forward_sys_fileinput";
    $("#dialog").dialog({
        title: "选择文件",
        minimizable:false,
        content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+url+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        width:620,
        height:500,
        iconCls: 'icon-info',
        modal: true,
        buttons:[{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(data){
                $("#dialog").dialog("close");
                if(headImgUrl!=''){
	                setHeadImage(headImgUrl);
	                parent.parent.setHeadImg(headImgUrl);
                }
            }
        }]
    });
}
function setHeadImage(url){
 	$("#headImage").attr("src",url);
}
</script>
</html>