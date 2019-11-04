<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div style="padding: 5px 5px 5px 5px;">
  <form enctype="multipart/form-data">
        <!-- <label>French Input</label> -->
        <div class="file-loading">
            <input id="file-zh" name="file" type="file" multiple>
        </div>
    </form>
</div>
<%--
<p><strong>文档类型:</strong><br/>doc、docx、xls、xlsx、zip、rar、pdf</p>
<p><strong>文档大小：</strong>请控制在20m以内</p>
--%>
</body>
<script type="text/javascript">
$('#file-zh').fileinput({
    theme: 'zh',
    language: 'zh',
    uploadUrl:"/sysUser/importUser", // server upload action
    maxFileCount:1,//最多上传个数
    allowedFileExtensions: ['xls','xlsx'],//接收的文件后缀
});

//异步上传成功结果处理
$("#file-zh").on("fileuploaded", function (event, data, previewId, index) {
	console.log(data);
	console.log(data.response.success);
    //alert("上传成功！");
	if(data.response.status == 200){
		console.log("上传成功");
		$.messager.alert('提示信息', '导入成功！', 'info');
	}else{
		$.messager.alert('提示信息', '导入异常，请重新导入！', 'info');
	}
})
//异步上传错误结果处理
$('#file-zh').on('fileerror', function(event, data, msg) {
	alert("上传失败！");
});
</script>
</html>