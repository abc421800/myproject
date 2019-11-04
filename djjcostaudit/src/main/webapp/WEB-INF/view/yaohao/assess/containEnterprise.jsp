<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>入库企业</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <style type="text/css">
        .datagrid-cell-c1-name {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap !important;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">入库企业名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="queryEnterprise()">&nbsp;
            </div>
        </div>
    </div>
</div>

<div class="row">
    <table id="containProject" style="width: 100%;">
    </table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary" id="saveBtn" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#unitEnterprise').dialog('close');"
           value="取消">
</div>
</body>
<script>
    var year = localStorage.getItem("year");
    $(function () {
        $('#containProject').datagrid({
            url: '/assess/queryEnterprise',
            loadMsg: '数据加载中,请稍候...',
            nowrap: false,
            rownumbers: true,
            height: 300,
            fitColumns: true,
            striped: true,
            collapsilble: true,
            pagination: true, //分页控件
            pageSize: 10,
            singleSelect: true,
            queryParams: {
                startTime: year,
            },
            columns: [
                [
                    {
                        field: 'id',
                        title: '复选框',
                        checkbox: true
                    }, {
                    field: 'enterpriseName',
                    title: '企业名称',
                    width: 150,
                    align: 'left',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            if (value == "未找到相关信息！") {
                                return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                            }
                            return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                            // return "<a href=\"javascript:void()\" style=\"color:#549de3;padding-left:2px;\" title=" + value + " onclick=\"showInfoEnt('" + rowData.workEnterpriseId + "')\">" + value + "</a>";
                        } else {
                            return ""
                        }
                    }
                }, {
                    field: 'simpleName',
                    title: '简称',
                    width: 120,
                    align: 'left',
                    sortable: false
                }, {
                    field: 'status',
                    title: '入库状态',
                    width: 80,
                    align: 'center',
                    sortable: false
                }
                ]
            ],
            onLoadSuccess: function (data) {
                if (data.total == 0) {
                    $('#containProject').datagrid('loadData', {total: 1, rows: [{enterpriseName: "未找到相关信息！"}]});
                }
            },
        });
        $("#saveBtn").click(function () {
            var rows = $('#containProject').datagrid('getSelections');
            var rows2 = $('#containProject').datagrid('getSelected');
            if (rows == undefined || rows == null || rows.length == 0) {
                $.messager.alert('提示信息', '请选择一条记录', 'warn');
                return;
            }
            if (rows.length > 1) {
                $.messager.alert('提示信息', '只能选择一条记录', 'warn');
                return;
            }
            if (rows2.length = 1 && rows2.enterpriseName == "未找到相关信息！") {
                $.messager.alert('提示信息', '此信息无效', 'warn');
                return;
            }
            window.parent.$("#auditPriceUnit").val(rows[0].enterpriseName);
            window.parent.$("#enterpriseCode").val(rows[0].enterpriseCode);
            window.parent.$("#winbidNum").val(rows[0].winbidNum);
            window.parent.costEnterpriseId=rows[0].costEnterpriseId;
            window.parent.$("#entStatus").val(rows[0].status);
            window.parent.$("#assessScore").val("0");
            if(rows[0].status=='暂停'){
            	window.parent.$("#joinAssessFlag").val("否");
            }
            window.parent.$('#unitEnterprise').dialog('close');
        })

    });

    function queryEnterprise() {
        var enterpriseName = $("#name").val();
        $('#containProject').datagrid({
            queryParams: {
                enterpriseName: enterpriseName,
                startTime: year,
            }
        });
    }

    //点击企业名称跳转企业详情
    function showInfoEnt(workEnterpriseId) {
        var href = "/costEnterprise/toEdit?editFlag=n&id=" + workEnterpriseId;
        var title = "入库企业详情";
        top.addTabGrid(title, href);
    }

</script>
</html>