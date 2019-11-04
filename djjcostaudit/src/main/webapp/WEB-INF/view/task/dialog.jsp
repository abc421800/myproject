<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加文件夹</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div class="dialog-file">
    <form name="pageForm" action="" method="post" id="form1">
        <div class="tabox table-responsive float-table">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr>
                    <th>文件夹名称</th>
                    <td>
                        <input name="projName" id="projName" type="text" style="width:100%;" class="form-control input-sm easyui-validatebox" required="required" missingMessage="项目名称必填" />
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" value="保存" class="btn btn-primary" onclick="saveFileName('${param.flag}')" />
            <input type="button"  class="btn btn-danger" value="取消" onclick="$('#dlg').dialog('close');" />
        </div>
    </form>
</div>
</body>
<script>

</script>
</html>