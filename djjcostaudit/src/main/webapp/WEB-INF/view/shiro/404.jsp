<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>页面找不到</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <style>
        .header{
            text-align: center;
            margin-bottom:48px;
        }
        .title{
            text-align: center;
            color:#007ad2;
            font-weight: bold;
            font-size: 30px;
            margin-bottom:30px;

        }
        .text-center{
            text-align: center;
        }
       .text-center a:hover {
            background-color: #297cdd;
        }
        .text-center a {
            display: inline-block;
            width: 88px;
            height: 36px;
            line-height: 36px;
            background: #4a90e2;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            font-size: 14px;
            color: #fff;
            text-decoration: none;
        }
    </style>
</head>
<body>

 	  <div class="header">
          <img src="${pageContext.request.contextPath}/res/images/login/reload.png" alt="" width="815" height="284">
      </div>
      <div class="title">404错误，页面丢失。。。</div>
      <div class="text-center">
          <a href="javascript:void(0)" class="loginBtn" onclick="javascript:top.location.href='/login'">登&nbsp;&nbsp;录</a>
      </div>



</body>
</html>