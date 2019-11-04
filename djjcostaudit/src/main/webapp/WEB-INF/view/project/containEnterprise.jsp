<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>审价单位</title>
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
                <label for="">审价单位：</label>
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
    <input type="button" class="btn btn-primary"  id="saveBtn" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#project').dialog('close');" value="取消">
</div>
</body>
<script>
$(function() {
    var tYear = new Date().getFullYear();
    $('#containProject').datagrid({
        url: '/costEnterprise/list',
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
        queryParams:{
        	effectiveFlag:"在库",
            batch:tYear
        },
        columns: [
            [
            {
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field: 'code',
                title: '企业编号',
                width: 100,
                align: 'left',
                sortable: false,

            },{
                field:'name',
                title:'企业名称',
                width:130,
                align:'left',
                sortable:false,
                formatter: function(value, rowData, index) {
                    if(value!=null){
                    	return "<a href=\"javascript:void()\" style=\"color:#549de3;padding-left:2px;\" onclick=\"showInfoEnt('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
                }
            }, {
                field: 'simpleName',
                title: '简称',
                width: 120,
                align: 'center',
                sortable: false
            },{
                field: 'effectiveFlag',
                title: '入库状态',
                width: 100,
                align: 'center',
                sortable: false
            }
            ]
        ],
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#containProject').datagrid('loadData', {total: 1, rows: [{fileName: "未找到相关信息！"}]});
            }
        },

    });
	
    $("#saveBtn").click(function(){
    	 var rows = $('#containProject').datagrid('getSelections');
    	    if (rows == undefined || rows == null || rows.length == 0) {
    	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
    	        return;
    	    }
    	    if (rows.length > 1) {
    	        $.messager.alert('提示信息', '只能选择一条记录', 'warn');
    	        return;
    	    }
    	    window.parent.$("#auditPriceUnit").val(rows[0].name);
    	   // window.parent.$("#auditPriceUnitName").val(rows[0].name);
    	    window.parent.$('#project').dialog('close');
    })
    
});
function queryEnterprise(){
	var projectName=$("#name").val();
	$('#containProject').datagrid({
		queryParams: {
			name:projectName
		}
	}); 
}
//点击企业名称跳转企业详情
function showInfoEnt(zjkClId) {
    var href = "/costEnterprise/toEdit?editFlag=n&id="+zjkClId;
    var title = "入库企业详情";
    top.addTabGrid(title, href);
}
</script>
</html>