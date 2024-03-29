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
<table id="info_error" class="table_edit" style="height:300px;width: 520px;border: 1px solid #ccc;padding:5px;display: none;">
	<tr>
		<td style="color:red;font-weight: bold;">未导入的合同编号</td>
	</tr>
	<tr>
		<td>
			<textarea id="info_content" cols="" rows="25" style="width: 100%;text-align:left;" id="p1"></textarea>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
$('#file-zh').fileinput({
    theme: 'zh',
    language: 'zh',
    uploadUrl:"/costDocument/importDocument", // server upload action
    maxFileCount:1,//最多上传个数
    allowedFileExtensions: ['xls','xlsx'],//接收的文件后缀
});

//异步上传成功结果处理
$("#file-zh").on("fileuploaded", function (event, data, previewId, index) {
	var list=data.response.data;
	if(data.response.status == 200){
		console.log("上传成功");
		$.messager.alert('提示信息', '导入成功！', 'info');
	}else{
		var str='';
		for (var i=0;i<list.length;i++){
			str+=list[i].code+","
		}
		console.log("str="+str);
		str=str.substring(0, str.length-1);
		//$.messager.alert('提示信息', "编号为【"+str+"】未导入成功，请检查后重新导入！", 'info');
		alertResut(str);
	}
})
function alertResut(str){
	$("#info_error").show();
	$("#info_content").html(str);
	
}
//异步上传错误结果处理
$('#file-zh').on('fileerror', function(event, data, msg) {
	alert("上传失败！");
});
</script>
</html>