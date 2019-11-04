<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>上传附件</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <form enctype="multipart/form-data">
        <div class="file-loading">
            <input id="file-zh" class="file" type="file" multiple>
        </div>
    </form>
    <br>
    <p><strong>文档类型:</strong><br/>doc、docx、zip、jpg、jpeg、xls、xlsx、pdf</p>
    <p><strong>文档大小：</strong>请控制在20m以内</p>
</div>
</body>

<script>
    $('#file-zh').fileinput({
        theme: 'zh',
        language: 'zh',
        uploadUrl:"/iSSP/achContracts/fileUpload.do", // server upload action
        maxFileCount:6,//最多上传个数
        allowedFileExtensions: ['doc','docx','zip','jpg','png','xls','xlsx','pdf'],//接收的文件后缀
    });
    //异步上传成功结果处理
    $("#file-zh").on("fileuploaded", function (event, data, previewId, index) {
        console.log(data);
        console.log(data.response.status);
        //alert("上传成功！");
        if(data.response.success){
            window.parent.returnJson = data.response.obj[0];
        }
    });
    //异步上传错误结果处理
    $('#file-zh').on('fileerror', function(event, data, msg) {
        alert("上传失败！");
    });

</script>
</html>