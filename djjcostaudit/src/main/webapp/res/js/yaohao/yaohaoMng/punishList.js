$(function () {
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#punishList').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#punishList').datagrid('resize', {
                height: tableHeight()
            });
        }
    });

   var punishYear = $("#punishYear").val();
   var enterpriseStatus=$("#enterpriseStatus").val();
   $('#punishList').datagrid({
    url: '/yaohaoPunish/listTz',
    loadMsg: '数据加载中,请稍候...',
    nowrap: false,
    rownumbers: true,
    height: tableHeight(),
    fitColumns: false,
    striped: true,
    collapsilble: true,
    pagination: true, //分页控件
    pageSize: 10,
    singleSelect: false,
    queryParams:{
    	punishYear:punishYear
    	//enterpriseStatus:enterpriseStatus
    	
    },
    frozenColumns:[[
        {
            field: 'id',
            title : '单选框',
            checkbox: true

        },{
            field: 'enterpriseName',
            title: '入库企业名称',
            width: 250,
            align: 'left',
            sortable: false,
            formatter: function (value, rowData, index) {
	            if(rowData.id==undefined){
	        		return "未找到相关信息！";
	        	}else{
	        		return "<a href=\"javascript:void(0)\" title='修改' style=\"color:#549de3;padding-left:2px;" +
	        		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
	        		"max-width: 100%;vertical-align: middle;\" onclick=\"editPun('"+rowData.id+"')\">"+value+"</a>";
	        	}
           }
        },{
            field:'punishYear',
            title:'入库批次',
            width:100,
            align:'center',
            sortable:false
        }, {
            field:'enterpriseStatus',
            title:'入库状态',
            width:100,
            align:'center',
            sortable:false,
            formatter: function (value, rowData, index) {
	            if(rowData.id==undefined){
	        		return "";
	        	}else{
	        		return "<a href=\"javascript:void(0)\" style=\"color:#549de3;padding-left:2px;" +
	        		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
	        		"max-width: 100%;vertical-align: middle;\" onclick=\"editInfo('"+rowData.enterpriseId+"')\">"+value+"</a>";
	        	}
           }
        }, {
            field: 'assessResult',
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
        }
    ]],
    columns: [
        [{
            field: 'content',
            title: '事由',
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
        }, {
            field: 'punishFlag',
            title: '奖/惩',
            width: 80,
            align: 'center',
            sortable: false
        }, {
            field: 'handleOpinion',
            title: '处理意见',
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

        }, {
            field: 'executeStartTimeStr',
            title: '执行开始时间',
            width: 100,
            align: 'center',
            sortable: false

        }, {
            field: 'executeEndTimeStr',
            title: '执行结束时间',
            width: 100,
            align: 'center',
            sortable: false,
            formatter: function (value, rowData, index) {
                if(rowData.status!="结束"){
                    var date = new Date();
                    var seperator1 = "-";
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var strDate = date.getDate();
                    if (month >= 1 && month <= 9) {
                        month = "0" + month;
                    }
                    if (strDate >= 0 && strDate <= 9) {
                        strDate = "0" + strDate;
                    }
                    var currentdate = year + seperator1 + month + seperator1 + strDate;
                    if (currentdate <rowData.executeStartTimeStr || currentdate >rowData.executeEndTimeStr) {

                        return "<span style=color:red; >"+value+"</span>";
                    }else{
                        return "<span>"+value+"</span>";
                    }
                }else{
                    return "<span>"+value+"</span>";
                }
            }
        }, {
            field: 'status',
            title: '履行状态',
            align: 'center',
            width: 80,
            sortable: false,
        }, {
            field: 'remark',
            title: '备注',
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

        },{
            field: 'creater',
            title: '登记人',
            width: 100,
            align: 'center',
            sortable: false

        },{
            field: 'createTimeStr',
            title: '登记时间',
            width: 100,
            align: 'center',
            sortable: false

        }
        ]
    ],
    toolbar: "#tit1",
	onLoadSuccess: function (data) {
        if (data.total == 0) {
            $('#punishList').datagrid('loadData', {total: 1, rows: [{enterpriseName: "未找到相关信息！"}]});
        }
    }
});
    $(window).resize(function () {
        if(bool){
            $('#punishList').datagrid('resize', {
                height: $(window).height() - 40
            });
        }else{
            $('#punishList').datagrid('resize', {
                height: tableHeight()
            });
        }

    });

});



//添加惩罚记录
function addPun() {
	top.addTabGrid("惩罚记录添加", "/yaohaoPunish/toEdit");
}
function editPun(id) {
	top.addTabGrid("惩罚记录修改", "/yaohaoPunish/toEdit?id="+id);
}
function deleteHandler() {
    var selections = $('#punishList').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '确认要删除这' + selections.length + '条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        var ids = [];
        for (var i in selections) {
            ids[i] = selections[i].id;
        }
        $.ajax({
            url: '/yaohaoPunish/delete',
            type: 'POST',
            data: {'ids': ids.toString()},
            traditional: true,
            success: function (result) {
            	if(result.status==200){
 				  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
 				  	$('#punishList').datagrid('reload');
 				}else{
 					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
 				}
            }
        });
    });
}

function searchPun(){
	var enterpriseName = $("#enterpriseName").val().trim();
	var punishYear = $("#punishYear").val();
	var enterpriseStatus = $("#enterpriseStatus").val();
	var punishFlag = $("#punishFlag").val();
	var status=$("#status").val();
    $('#punishList').datagrid({
		queryParams: {
			enterpriseName:enterpriseName,
			punishYear:punishYear,
			enterpriseStatus:enterpriseStatus,
			punishFlag:punishFlag,
			status:status
		}
	}); 
}
function refresh() {
	var json1={tabTitle:'奖惩记录',url:'/yaohaoPunish/toPunishList'};
	window.parent.refreshTab(json1); 
}
function editInfo(id) {
	$("#punishList").datagrid("clearChecked");
    var href = "/costEnterprise/toEdit?editFlag=y&id="+id;
    var title = "入库企业修改";
    top.addTabGrid(title, href);
}

//导出
function exportDate(){
	var selections = $('#punishList').datagrid('getSelections');
	var ids = "";
    var y = $("#punishYear").val();
	if (selections.length != 0) {
		for(var i = 0;i<selections.length;i++){
    		ids+=selections[i].id+",";
    	}
    	ids = ids.substring(0,ids.length-1);
    }
	window.location.href = "/yaohaoPunish/exportDate?ids="+ids+"&&y="+y;
}

