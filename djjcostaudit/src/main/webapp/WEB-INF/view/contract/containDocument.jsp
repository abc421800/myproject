<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>包含往来文件</title>
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
                <label for="">文号：</label>
                <input type="text" id="symbol" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">往来文件名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit"  value="查询" class="btn btn-primary" onclick="queryDocument()">&nbsp;
            	<input type="reset"  value="重置" class="btn btn btn-success" onclick="$('#name').val('');$('#code').val('');queryDocument();">&nbsp;
            </div>
        </div>
    </div>
</div>

<div class="row">
    <table id="containDocument" style="width: 100%;"></table>
</div>
<div class="text-center" style="margin-top: 10px;">
    <input type="button" class="btn btn-primary"  id="saveBtn" value="确定">&nbsp;&nbsp;
    <input type="button" class="btn btn-success" onclick="window.parent.$('#document').dialog('close');" value="取消">
</div>
</body>
<script>
$(function() {

    $('#containDocument').datagrid({
    	url: "costDocument/getDocumentList?projId=${param.projId}",
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
        columns: [
            [
            {
                field: 'id',
                title : '单选框',
                checkbox: true
            },{
                field:'symbol',
                title:'文号',
                width:150,
                align:'center',
                sortable:false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            },{
                field:'name',
                title:'文件标题',
                width:230,
                align:'left',
                sortable:false,
                formatter: function(value, rowData, index) {
                    if(value!=null){
                        //return '<a href="javascript:void(0);" style="color:#549de3;padding-left:2px;display: inline-block;" onclick="showInfo('+rowData.id+')">'+value+'</a>';
                        return "<a href=\"javascript:void(0)\" title="+value+"  style=\"color:#549de3;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: block;\" onclick=\"showInfo('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return " "
                    }

                }
            },{
                field: 'comeGoFlag',
                title: '往/来',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'auditPriceFlag',
                title: '是否审价',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'comeGoUnit',
                title: '往来单位',
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'documentTimeStr',
                title: '文件日期',
                align: 'center',
                width: 150,
                sortable: false,

            }
            /*,
            {
                field: 'personLiable',
                title: '负责人',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\" >"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }*/
            ]
        ],
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#containDocument').datagrid('loadData', {total: 1, rows: [{symbol: "未找到相关信息！"}]});
            }
        }

    });
	
    $("#saveBtn").click(function(){
    	 var rows = $('#containDocument').datagrid('getSelections');
    	    if (rows == undefined || rows == null || rows.length == 0) {
    	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
    	        return;
    	    }
    	    if (rows.length > 1) {
    	        $.messager.alert('提示信息', '只能选择一条记录', 'warn');
    	        return;
    	    }
    	    
    	    var contractId="${param.contractId}";
    	    var projectId="${param.projId}";
    	    var documentId=rows[0].id;
    	    $.ajax({
			  	url:"/costDocument/saveDpctRelation",
			  	dataType: "json",
			  	type:"post",
			  	async: false,
			  	data: {"projectId":projectId,"contractId":contractId,"id":documentId},
			  	success: function(result){
				  	if(result.status==200){
			    	    window.parent.$('#document').dialog('close');
			    	    window.parent.$('#wlfile').datagrid("reload");
				  	 	window.parent.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	}else {
						$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  	}
			  	}
			});
    })
    
});

function queryDocument(){
	var name=$("#name").val();
	var symbol=$("#symbol").val();
	$('#containDocument').datagrid({
		queryParams: {
			name:name,
			symbol:symbol
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