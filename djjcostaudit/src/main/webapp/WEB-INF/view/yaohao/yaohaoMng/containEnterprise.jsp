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
                <label for="">企业名称：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="queryEnterprise()">&nbsp;
            	<input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="$('#name').val('');queryEnterprise();">&nbsp;
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
    <input type="button" class="btn btn-success" onclick="window.parent.$('#addCompany').dialog('close');" value="取消">
</div>
</body>
<script>
$(function() {

    $('#containProject').datagrid({
        url: '/costEnterprise/candidateEnterpriseList',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 300,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 50,
        singleSelect: false,
        queryParams:{
        	effectiveFlag:"在库",
        	yaohaoGradeFlag:"${param.yaohaoGradeFlag}"
        },
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
                formatter: function(value, rowData, index) {
                	if(rowData.id==undefined){
                        return "未找到相关信息！";
                    }else{
                    	return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                    	/*return "<a href=\"javascript:void(0)\" style=\"color:#549de3;padding-left:2px;\" title="+value+" onclick=\"showInfoEnt('"+rowData.id+"')\">"+value+"</a>";*/
                    }
                }
            }, {
                field: 'simpleName',
                title: '简称',
                width: 120,
                align: 'left',
                sortable: false
            }, {
                field: 'assessResultRk',
                title: '上一年度综评',
                width: 100,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index){
                    if(rowData.id){
                        if(value!=null){
                            return value;
                        }else{
                            return "未评定";
                        }
                    }
                }
            },{
                field: 'r_year',
                title: '入库批次',
                width: 80,
                align: 'center',
                sortable: false
            },{
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
                $('#containProject').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        },

    });
	
    $("#saveBtn").click(function(){
    	 var rows = $('#containProject').datagrid('getSelections');
    	    if (rows == undefined || rows == null || rows.length == 0) {
    	        $.messager.alert('提示信息', '请选择一条记录', 'warn');
    	        return;
    	    }
    	    var ids =[];
    	    for (var i in rows) {
    	    	ids[i] = rows[i].code;
    	    }
    		$.post("/yaohaoCandidate/save", {"ids":ids.toString(),"yaohaoGradeFlag":"${param.yaohaoGradeFlag}"},function(result){
        		if(result.status==200){
        		  	window.parent.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
        			window.parent.$("#addRockNumber").datagrid('reload');
        			window.parent.$('#addCompany').dialog('close');
  			    }else{
  				  	$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
  			    }
        	}, "json");
    })
    
});
function queryEnterprise(){
	var projectName=$("#name").val();
	$('#containProject').datagrid({
		queryParams: {
			name:projectName,
			effectiveFlag:"在库",
        	yaohaoGradeFlag:"${param.yaohaoGradeFlag}"
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