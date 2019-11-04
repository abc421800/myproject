<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门列表</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div class="row">
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">机构名称</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit"  value="查询" class="btn btn-primary" onclick="queryContract()">&nbsp;
                <input type="reset"  value="重置" class="btn btn btn-success" onclick="$('#name').val('');$('#code').val('');queryContract();">&nbsp;
            </div>
        </div>
    </div>
</div>
<div class="row">
    <table id="sysOrg" style="width: 100%;">
    </table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary"  id="saveOrg" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#agreeToOrg').dialog('close');" id="sysOrg" value="取消">
</div>
</body>
<script>
$(function() {
    $('#sysOrg').datagrid({
        url: '/sysOrg/list',
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
        	pidIsNotNull:"1",
        },
        columns: [
            [
            {
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field: 'name',
                title: '机构名称',
                width: 350,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            },{
                field:'description',
                title:'描述',
                width:400,
                align:'left',
                sortable:false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }
            ]
        ],
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#sysOrg').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        },

    });

    $("#saveOrg").click(function(){
   	 var rows = $('#sysOrg').datagrid('getSelections');
   	    if (rows == undefined || rows == null || rows.length == 0) {
   	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
   	        return;
   	    }
   	    if (rows.length > 1) {
   	        $.messager.alert('提示信息', '只能选择一条记录', 'warn');
   	        return;
   	    }
   	    window.parent.$("#comeGoUnitId").val(rows[0].name);
	    window.parent.$('#agreeToOrg').dialog('close');
   	
   })

});

function queryContract(){
	var name=$("#name").val();
	$('#sysOrg').datagrid({
		queryParams: {
			pidIsNotNull:"1",
			name:name
		}
	}); 
}

</script>
</html>