<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>包含项目</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div class="row">
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">项目编号：</label>
                <input type="text" id="code" placeholder="请输入关键字" class="form-control input-sm">
            </div>
        	<div class="form-group">
                <label for="">项目名称：</label>
                <input type="text" id="name" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit"  value="查询" class="btn btn-primary" onclick="queryProject()">&nbsp;
                <input type="reset"  value="重置" class="btn btn btn-success" onclick="$('#name').val('');$('#code').val('');queryProject();">&nbsp;
            </div>
        </div>
    </div>
</div>

<div class="row">
    <table id="containProject" style="width: 100%;"></table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary"  id="saveBtn" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#project').dialog('close');" value="取消">
</div>
</body>
<script>
$(function() {

    $('#containProject').datagrid({
        url: '/costProject/getProjectlist',
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
                field: 'code',
                title: '项目编号',
                width: 100,
                align: 'left',
                sortable: false,

            },{
                field:'name',
                title:'项目名称',
                width:130,
                align:'left',
                sortable:false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'projectCategoryId',
                title: '工程类别',
                width: 120,
                align: 'center',
                sortable: false
            },{
                field: 'projectClassification',
                title: '项目分类',
                width: 100,
                align: 'center',
                sortable: false, 
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }
            ]
        ],
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#containProject').datagrid('loadData', {total: 1, rows: [{code: "未找到相关信息！"}]});
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
    	    window.parent.$("#containProjectShowName").val(rows[0].name);
    	    window.parent.$("#containProjectShowName").attr("title",rows[0].name);
    	    window.parent.$("#containProjectName").val(rows[0].name);
    	    window.parent.$("#containProjectId").val(rows[0].id);
    	    //
    	    window.parent.$("input[name='auditPriceUnit']").val(rows[0].auditPriceUnit);
    	    window.parent.$("#auditPriceUnitName").val(rows[0].auditPriceUnit);
    	    //是否全过程
    	    window.parent.$("#isAllProcess").val(rows[0].allProcess);
    	    var allprocess=rows[0].allProcess;
    	    if(rows[0].allProcess=="是"){
    	   		window.parent.$("#auditPriceUnit_star").text("*"); 
    	    }
    	    //清空合同信息
    	    window.parent.$("#containContractShowName").val("");
    	    window.parent.$("#containContractName").val("");
	    	window.parent.$("#containContractId").val("");
    	    window.parent.$('#project').dialog('close');
    })
    
});

function queryProject(){
	var name=$("#name").val();
	var code=$("#code").val();
	$('#containProject').datagrid({
		queryParams: {
			name:name,
			code:code
		}
	}); 
}
//点击项目名称跳转项目详情
function showInfoPro(zjkClId) {
    var href = "/costProject/editProject?editFlag=n&projId="+zjkClId;
    var title = "项目台账详情";
    top.addTabGrid(title, href);
}
</script>
</html>