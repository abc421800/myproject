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
                <label for="">姓名：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="queryEnterprise()">&nbsp;
                <input type="submit" name="Submit11" value="重置" class="btn btn btn-success" onclick="$('#name').val('');queryEnterprise()">&nbsp;
            </div>
        </div>
    </div>
</div>

<div class="row">
    <table id="userList" style="width: 100%;">
    </table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary"  id="saveBtn" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#addPerson').dialog('close');" value="取消">
</div>
</body>
<script>
$(function() {

    var tem="${param.names}";
    var a=decodeURI(tem);
    var b=encodeURI(encodeURI(a));
    $('#userList').datagrid({
        url: '/sysUser/workPersonList?names='+b,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 300,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        idField:'id',
        queryParams:{
        	orgName:"造价审核部",
        	status:"可用"
        },
        columns: [
            [
            {
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field: 'account',
                title: '账号',
                width: 100,
                align: 'left',
                sortable: false,

            },{
                field:'name',
                title:'姓名',
                width:130,
                align:'left',
                sortable:false
            }, {
                field: 'orgId',
                title: '部门',
                width: 120,
                align: 'center',
                sortable: false
            }, {
                field: 'phone',
                title: '电话',
                width: 120,
                align: 'center',
                sortable: false
            }
            ]
        ],
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#userList').datagrid('loadData', {total: 1, rows: [{account: "未找到相关信息！"}]});
            }else{
            	$.each(data.rows, function (index, value) {
	                 if(value){
	                	 if(value.checked){
	                		 $('#userList').datagrid("selectRow", index);
	                	 }
	                 }
	             });
            }
        },

    });
	
    $("#saveBtn").click(function(){
    	 var rows = $('#userList').datagrid('getSelections');
    	    if (rows == undefined || rows == null || rows.length == 0) {
    	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
    	        return;
    	    }
    	    console.log(rows);
    	    var names = '';
            for (var i in rows) {
            	names += rows[i].name +",";
            }
            names=names.substring(0, names.length-1);
            /*
            var orldName=window.parent.$("#workingPerson").val();
            if(orldName){
	    	    window.parent.$("#workingPerson").val(orldName+","+names);
            }else{
            	window.parent.$("#workingPerson").val(orldName+names);
            }*/
            window.parent.$("#workingPerson").val(names);
    	    window.parent.$('#addPerson').dialog('close');
    })
    
});
function queryEnterprise(){
	var name=$("#name").val();
	$('#userList').datagrid({
		queryParams: {
			name:name,
			orgId:"e67ddbe615664c6d9705cbe26a2001bf",
			status:"可用"
		}
	}); 
}

</script>
</html>