<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>审价任务类型树</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>

</head>
<body>
   <div class="dialog-file">
    <form name="pageForm" action="" method="post" id="form1">
        <div class="tabox  float-table">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr>
                    <th width="25%">审价类型</th>
                        
                    <td width="75%" class="form-inline">
                        <select id="valuationTask" class="form-control input-sm" style="min-width: 75%;height:30px"></select>
                        <input type="hidden" id="valuationTaskParent" value="">
                        <input type="hidden" id="dpctRelationId" value="${param.dpctRelationId }">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" value="确定" onclick="toAddTask('${param.dpctRelationId }');" class="btn btn-primary" />
            <input type="button" name="" class="btn btn-danger" value="取消" onclick="$('#taskDialog').dialog('close');" />
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/res/js/cost/document/valuationTask.js"></script>
</body>
</html>