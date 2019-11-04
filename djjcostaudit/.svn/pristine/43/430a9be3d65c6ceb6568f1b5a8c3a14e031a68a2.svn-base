<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目台账</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <!-- 审批新建页面样式 -->

</head>
<style>
    .panel-body {

        overflow: auto;

    }

    .datagrid-cell-c1-name {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap !important;
    }

    .datagrid-cell-c1-projectOwner {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap !important;
    }

    .datagrid-cell-c1-projectOwner {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap !important;
    }

    .datagrid-cell-c1-settlementAgreement {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap !important;
    }

    .datagrid-cell-c1-settlementReivewMethod {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap !important;
    }

    .menu-text {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 140px;
    }
</style>
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
    <form method="post" action="">
        <div class="filter">
            <div class="form-inline">
                <div class="form-group">
                    <label for="">项目名称：</label>
                    <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
                </div>
                <div class="form-group">
                    <label for="">项目编号：</label>
                    <input type="text" id="code" value="" placeholder="请输入关键字" class="form-control input-sm">
                </div>
                <div class="form-group">
                    <label for="">管理协议签订：</label>
                    <select onchange="selectSubmit();" id="projectManagementAgreement" class="form-control input-sm"
                            style="width: 100px">
                        <option value="">请选择</option>
                        <option value="未签">未签</option>
                        <option value="已签">已签</option>
                        <option value="无需签订">无需签订</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="">结算台账显示：</label>
                    <select onchange="selectSubmit();" id="showFlag2" class="form-control input-sm"
                            style="width: 100px">
                        <option value="">请选择</option>
                        <option value="是">是</option>
                        <option value="否">否</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="">项目负责人：</label>
                    <input type="text" name="" id="personLiableId" class="form-control input-sm"
                           placeholder="请输入项目负责人"/>
                </div>
                <div class="form-group">
                    <label for="">项目分类：</label>
                    <select id="projectClassificationId" style="width:160px;" class="form-control input-sm">
                    </select>
                </div>
                <div class="form-group">
                    <label for="">项目所在地点：</label>
                    <input type="text" id="projectLocation" value="" placeholder="请输入关键字" class="form-control input-sm">
                </div>
                <div class="form-group">
                    <label for="">当前节点：</label>
                    <select onchange="selectSubmit();" id="projectNode" class="form-control input-sm"
                            style="width: 100px">
                        <option value="">请选择</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="">创建时间：</label>
                    <input id="startTime" class="Wdate form-control input-sm" type="text"
                           onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                    <label for="">至</label>
                    <input id="endTime" class="Wdate form-control input-sm" type="text"
                           onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                </div>
                <!--<br>-->
                <!--<div class="form-group">-->
                <!--<label for="">节点控制日期：</label>-->
                <!--<input id="contractSigningTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">-->
                <!--<label for="">至</label>-->
                <!--<input id="contractSigningTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">-->
                <!--</div>-->
                <div class="form-group">
                    <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="selectSubmit();">&nbsp;
                    <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="reset1();">&nbsp;
                    <input type="button" name="aa" value="导出" class="btn btn-danger" onclick="exportDate();">&nbsp;
                </div>

            </div>
        </div>
    </form>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="projectAccounts" style="width: 100%;">
    </table>
    <div id="tit1">
        <shiro:hasPermission name="project:add">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="top.addTabGrid('项目添加', '/forward_project_projAdd');">添加</a>
        </shiro:hasPermission>

        <shiro:hasPermission name="project:delete">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="project:update">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="" plain="false" onclick="updateShowFlagDialog();">统计查询显示</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="project:update">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="" plain="false" onclick="updateClassificationDialog();">批量设置</a>
        </shiro:hasPermission>
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    </div>
</div>
<div id="importProject"></div>
<div id="dlg" title="统计查询显示" style="display:none" class="dialog-file">
    <div class="tabox table-responsive float-table">
        <table class="table table-condensed table-bordered">
            <tbody>
            <tr>
                <th><strong style="color:red">*</strong>结算台账是否显示：</th>
                <td>
                    <input onclick="" name="currentNode2" type="radio" checked value="是">是&nbsp;&nbsp;
                    <input onclick="" name="currentNode2" type="radio" value="否">否
                </td>
            </tr>
        </table>
    </div>
    <div class="form-group text-center">
        <input type="button" value="保存" class="btn btn-primary" onclick="updateShowFlag();"/>
        <input type="button" name="Submit22" class="btn btn-danger" value="取消" onclick="$('#dlg').dialog('close');"/>
    </div>
</div>
<div id="dlg2" title="批量设置" style="display:none" class="dialog-file">
    <div class="tabox table-responsive float-table">
        <table class="table table-condensed table-bordered">
            <tbody>
            <tr>
                <td>
                    <label for="">项目分类：</label>
                </td>
                <td>
                    <select id="projectClassificationId2" style="width:200px;" class="form-control input-sm"></select>
                </td>
            </tr>
        </table>
    </div>
    <div class="form-group text-center">
        <input type="button" value="保存" class="btn btn-primary" onclick="updateClassification();"/>
        <input type="button" name="Submit22" class="btn btn-danger" value="取消" onclick="$('#dlg2').dialog('close');"/>
    </div>
</div>
<script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree2.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/project/projectAccounts.js"></script>
<script>
    var currentUser = "${user.name}";
    var dataUpdate = "${dataUpdate}";

    function updateShowFlagDialog() {
        var selections = $('#projectAccounts').datagrid('getSelections');
        if (selections.length == 0) {
            $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
            return;
        }
        $("#dlg").show();
        $("#dlg").dialog({
            width: 450,
            top: 200
        });
        $('input:radio[name="currentNode2"][value="是"]').prop('checked', true);
    }

    function updateClassificationDialog() {
        var selections = $('#projectAccounts').datagrid('getSelections');
        if (selections.length == 0) {
            $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
            return;
        }
        $("#dlg2").show();
        $("#dlg2").dialog({
            width: 450,
            top: 200
        });
    }

    function exportDate() {
        var selections = $('#projectAccounts').datagrid('getSelections');
        var ids = "";
        if (selections.length != 0) {
            for (var i = 0; i < selections.length; i++) {
                ids += selections[i].id + ",";
            }
            ids = ids.substring(0, ids.length - 1);
        }
        window.location.href = "${path}/costProject/exportProject?ids=" + ids;
        //window.location.href = "${path}/costProject/exportProject.do";
    }

    $(function () {
        $.post("/costProjectNode/getData", function (result) {
            for (var i in result) {
                var v = result[i].name;
                console.log(v);
                var div = "<option value=" + v + ">" + v + "</option>";
                $("#projectNode").append(div);
            }
        }, "json");

    });

    function importProject() {
        var url = "/forward_project_importProject";
        $("#importProject").dialog({
            title: "选择文件",
            iconCls: 'icon-info',
            minimizable: false,
            content: "<iframe name=\"fileFrame\" frameborder=\"0\" src=" + url + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            width: 600,
            height: 500,
            iconCls: 'icon-edit-lx',
            modal: true,
            buttons: [{
                text: '关闭',
                iconCls: 'icon-info',
                handler: function (data) {
                    $("#projectAccounts").datagrid('reload');
                    $("#importProject").dialog("close");
                }
            }]
        });
    }
</script>
</body>
</html>