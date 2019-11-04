<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>添加项目干系人</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>

<body>
<div class="dialog-file">
    <form name="pageForm" action="" method="post" id="stakeholderForm">
    <input type="hidden" name="id" value="${stakeholder.id }"/>
    <input type="hidden" name="projectId" value="${param.projId }"/>
        <div class="tabox table-responsive float-table">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr>
                    <th><strong style="color:red">*</strong>角色：</th>
                    <td>
                        <select class="form-control input-sm" id="roleId" name="roleId"   style="width: 100%">
                        	<option value="">请选择</option>
                            <c:forEach var="role" items="${roleList }">
                            	<option value="${role.id }" <c:if test="${stakeholder.roleId eq role.id}">selected</c:if> >${role.name }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><strong style="color:red">*</strong>职能：</th>
                    <td>
                        <input name="function" id="functions" value="${stakeholder.function }" type="text" style="width:100%;" class="form-control input-sm"/>
                    </td>
                </tr>
                <tr>
                    <th><strong style="color:red">*</strong>人名：</th>
                    <td style="position:relative">
                        <input name="name" id="userName" onfocus="assignTask();" list="nameCon" value="${stakeholder.name }" type="text" class="form-control input-sm" placeholder="请输入全名"  style="width:90%;"/>
                        <a href="javascript:void(0)" class="fr" style="position: absolute;right:18px;top:8px" onclick="person1();">
                    	<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                    	</a>
                        <dataList id="nameCon">
                        </dataList>
                    </td>
                </tr>
                <tr>
                    <th><strong style="color:red">*</strong>联系电话：</th>
                    <td>
                        <input name="phone" id="phone" value="${stakeholder.phone }" type="text" style="width:100%;" class="form-control input-sm"   onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" />
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" value="保存" class="btn btn-primary" onclick="saveStakeholder();" />
            <input type="button" name="Submit22" class="btn btn-danger" value="取消" onclick="$('#projectPersonDialog').dialog('close');" />
        </div>
    </form>
</div>
<div id="person1"></div>
</body>
<script>

</script>
</html>
