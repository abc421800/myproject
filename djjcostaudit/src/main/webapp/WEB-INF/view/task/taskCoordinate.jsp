<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>添加协调事项</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>

<body>
<div class="dialog-file">
    <form id="tcForm"  action="" method="post" id="stakeholderForm">
    <input type="hidden" name="id" value="${obj.id }"/>
    <input type="hidden" name="taskId" value="${obj.taskId }"/>
        <div class="tabox table-responsive float-table">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr>
                    <th>协调事项名称：</th>
                    <td>
                        <input id="name" name="name" value="${obj.name }" type="text" style="width:100%;" class="form-control input-sm"  missingMessage="协调事项名称必填" />
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                       <select id="status" name="status" class="form-control input-sm easyui-validatebox">
                           <c:forTokens items="新建,完成" delims="," var="i">
                           	<option <c:if test="${i eq obj.status }">selected = "selected"</c:if>  value="${i}">${i}</option>
                           </c:forTokens>
                       </select>
                    </td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td>
                         <textarea id="description" class="form-control" style="margin: 3px 0px"  name="description" cols="5" rows="4">${obj.description }</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" value="保存" class="btn btn-primary" onclick="saveTc();" />
            <input type="button" name="Submit22" class="btn btn-danger" value="取消" onclick="$('#taskCoordinateDialog').dialog('close');" />
        </div>
    </form>
</div>
</body>
<script>

</script>
</html>
