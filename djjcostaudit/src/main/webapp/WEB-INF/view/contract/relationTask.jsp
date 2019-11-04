<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>关联任务</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div class="row">
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">审价任务名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">审价任务编号：</label>
                <input type="text" id="code" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit"  value="查询" class="btn btn-primary" onclick="queryTask()">&nbsp;
                <input type="reset"  value="重置" class="btn btn btn-success" onclick="$('#name').val('');$('#code').val('');queryTask();">&nbsp;
            </div>
        </div>
    </div>
</div>
<div class="row">
    <table id="containTask" style="width: 100%;">
    </table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary"  id="saveContainCon" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" id="cancelBtn"  value="取消">
</div>
</body>
<script>
$(function() {
    $('#containTask').datagrid({
        url: '/costTask/list?projectId=${param.projectId}&contractCongId=flag',
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
        queryParams:{
        	auditPriceTypecn:"合同变更",
        },
        columns: [
            [{
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field: 'code',
                title: '审价编号',
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
                title:'审价任务名称',
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
                field: 'auditPriceType',
                title: '审价类型',
                width: 130,
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
   	 var rows = $('#containTask').datagrid('getSelections');
   	    if (rows == undefined || rows == null || rows.length == 0) {
   	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
   	        return;
   	    }
	   	 var ids = '';
	     for (var i in rows) {
	         ids+=rows[i].id+",";
	     }
	     ids = ids.substring(0,ids.length-1);
   	    $.ajax({
            url: '/costContract/relationTask',
            type: 'POST',
            data: {'taskIds':ids,'contractId':"${param.contractId}","projectId":"${param.projectId}","parentId":"${param.parentId}"},
            traditional: true,
        	success: function(result){
				  if(result.status==200){
				  	 window.parent.$('#relationTask').dialog('close');
				  	 window.parent.$('#contract_task').datagrid("reload");
				  	 window.parent.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  }else{
					 $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			}
        });
   	    
   })

});

$("#cancelBtn").click(function(){
    window.parent.$('#relationTask').dialog('close');
})
	
function queryTask(){
	var name=$("#name").val();
	var code=$("#code").val();
	$('#containTask').datagrid({
		queryParams: {
			name:name,
			taskCode:code,
			auditPriceType:"招标清单/控制价审核"
		}
	}); 
}

</script>
</html>