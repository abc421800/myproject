<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>关联项目和合同</title>
    <%--<jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
--%></head>
<body>
    <form action="" id="formId">
     <input type="hidden"  value="${obj.id}"  id="dpctRelationId" style="width:80%;margin-top:5px;">
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
                <tbody>
                    <tr>
                        <td class="bgcolor" style="width:20%"><strong>*</strong>关联项目：</td>
                        <td>
                            <input type="text"  value="${project.name}" disabled id="containProject" style="width:80%;margin-top:5px;">
                            <input type="hidden"  value="${project.id}"  id="containProjectId" style="width:80%;margin-top:5px;">
                            <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="agreeToProject();"><img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19"></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong></strong>关联合同：</td>
                        <td>
                            <input type="text"  value="${contract.name}" disabled id="containContract" style="width:80%;margin-top:5px;">
                            <input type="hidden"  value="${contract.id}"  id="containContractId" style="width:80%;margin-top:5px;">
                            <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="agreeToContract();"><img src="${pageContext.request.contextPath}/res/images/pagecommon/contract.png" alt="" width="19"height="19"></a>
                        </td>
                    </tr>
                    <tr style="height:40px">
                        <td colspan="2" style="text-align: center">
                            <input type="button" class="btn btn-primary" onclick="saveBtn()" value="保存">&nbsp;&nbsp;
                            <input type="button" class="btn btn-success" onclick="$('#info_add').dialog('close')" value="取消">
                        </td>
                    </tr>
                </tbody>
            </table>

    </form>
<div id="all">
<div id="project"></div>
<div id="contract"></div>
</div>

</body>
<script>

</script>
</html>