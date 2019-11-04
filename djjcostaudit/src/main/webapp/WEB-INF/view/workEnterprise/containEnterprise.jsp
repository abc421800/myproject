<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
                <label for="">企业名称：</label>
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
    <input type="button" class="btn btn-success" onclick="window.parent.$('#unitEnterprise').dialog('close');" value="取消">
</div>
</body>
<script>
$(function() {

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
        columns: [
            [
            {
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field:'name',
                title:'企业名称',
                width:150,
                align:'left',
                sortable:false,
                /* formatter: function(value, rowData, index) {
                    if(value!=null){
                    	return "<a href=\"javascript:void()\" style=\"color:#549de3;padding-left:2px;\" title="+value+" onclick=\"showInfoEnt('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
                } */
            }, {
                field: 'simpleName',
                title: '简称',
                width: 120,
                align: 'left',
                sortable: false
            },{
                field: 'effectiveFlag',
                title: '是否有效',
                width: 80,
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
    	    window.parent.$("#name").val(rows[0].name);
    	    window.parent.$("#personLiable").val(rows[0].contacts);
    	    window.parent.$("#phone").val(rows[0].contactsPhone);
    	    window.parent.$("#telephone").val(rows[0].contactsPhone);
    	    window.parent.$("#address").val(rows[0].address);
    	    window.parent.$("#email").val(rows[0].email);
    	    window.parent.$("#fax").val(rows[0].fax);
    	    window.parent.$("#remark").val(rows[0].description);
    	    window.parent.$('#unitEnterprise').dialog('close');
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