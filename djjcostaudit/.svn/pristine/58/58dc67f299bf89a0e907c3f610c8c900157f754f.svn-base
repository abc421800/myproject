<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>包含合同</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div class="row">
    <div class="filter">
        <div class="form-inline">
        	<div class="form-group">
                <label for="">合同编号：</label>
                <input type="text" id="code" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">合同名称：</label>
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
    <table id="containContract" style="width: 100%;">
    </table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary"  id="saveContainCon" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#contractChildren').dialog('close');" id="cancelContainCon" value="取消">
</div>
</body>
<script>
$(function() {

    $('#containContract').datagrid({
        url: '/costContract/list?projectId=${param.projectId}',
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
        	mainFlag:"主合同",
        },
        columns: [
            [
            {
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field: 'code',
                title: '合同编号',
                width: 100,
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
                field:'name',
                title:'合同名称',
                width:200,
                align:'left',
                sortable:false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            },{
                field: 'contractType',
                title: '合同类型',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'projectName',
                title: '所属项目',
                width: 200,
                align: 'left',
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
                $('#containContract').datagrid('loadData', {total: 1, rows: [{fileName: "未找到相关信息！"}]});
            }
        },

    });

    $("#saveContainCon").click(function(){
   	 var rows = $('#containContract').datagrid('getSelections');
   	    if (rows == undefined || rows == null || rows.length == 0) {
   	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
   	        return;
   	    }
   	    if (rows.length > 1) {
   	        $.messager.alert('提示信息', '只能选择一条记录', 'warn');
   	        return;
   	    }
   	    window.parent.$("#parentName").val(rows[0].name);
   	    window.parent.$("#parentId").val(rows[0].id);
   	    
	    window.parent.$('#contractChildren').dialog('close');
   	
   })

});
	
	function queryContract(){
		var name=$("#name").val();
		var code=$("#code").val();
		$('#containContract').datagrid({
			queryParams: {
				mainFlag:"主合同",
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