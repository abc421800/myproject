//参数 documentId
function sjTask(id) {
    var bool=false;
    $(".switchBtn").on('click',function () {
        $(".filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#sjTask').datagrid('resize', {
                height: 500
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#sjTask').datagrid('resize', {
                height: 500
            });
        }
    });
    $('#sjTask').datagrid({
        url: '/costTask/list?documentId='+id,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
            [{
                field: 'code',
                title: '审价编号',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                  	 if(value!=null){
                  		 return "<a href=\"javascript:void()\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                   		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                   		"max-width: 100%;vertical-align: middle;\" onclick=\"showInfo('"+rowData.id+"')\">"+value+"</a>";
                       }else{
                           return ""
                       }
                  }
            }, {
                field: 'name',
                title: '审价任务名称',
                width: 230,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'auditPriceType',
                title: '审价类型',
                width: 130,
                align: 'center',
                sortable: false

            }, {
                field: 'giveAmount',
                title: '送审金额（元）',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'decideAmount',
                title: '审定金额（元）',
                width: 140,
                align: 'center',
                sortable: false
            }, {
                field: 'personLiable',
                title: '审价任务负责人',
                align: 'center',
                width: 100,
                sortable: false,

            }, {
                field: 'auditPriceUnit',
                title: '审价单位',
                align: 'center',
                width: 100,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'status',
                title: '当前状态',
                align: 'center',
                width: 80,
                sortable: false

            }, {
                field: 'finalizeNumber',
                title: '定案文号',
                width: 140,
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
                field: 'coordinateFlag',
                title: '协调',
                width: 50,
                align: 'center',
                sortable: false
            }
            ]
        ],
        toolbar: "#tit2",
        onLoadSuccess: function (data) {
	        if (data.total == 0) {
	            $('#sjTask').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
	        }
	    }


    });
    $(window).resize(function () {
        $('#sjTask').datagrid('resize', {
            height: 500
        });
    });
}

//添加审价任务
function taskDialog(dpctRelationId) {
    $('#taskDialog').dialog({
        title: '选择审价类型',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top:100,
        href:'/forward_document_sjTask?dpctRelationId='+dpctRelationId,
        //content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'sjTask.html'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
function toAddTask(dpctRelationId){
    var typeId = $("#valuationTask").combotree("getValue"); 
    var typePid=$("#valuationTaskParent").val();
    //var dpctRelationId=$("#dpctRelationId").val();
    if(typeId==null || typeId=='' || typeId=='-1'){
    	 $.messager.alert("温馨提示","请选择审价类型!", "error");
    	 return;
    }
	var href="/costTask/toAddTask?editFlag=y&documentId="+documentId+"&typeId="+typeId+"&dpctRelationId="+dpctRelationId+"&typePid="+typePid;
	$('#taskDialog').dialog('close');
	top.addTabGrid("审价任务添加", href);
}
function showInfo(zjkClId) {
	 var href = "/costTask/toEdit?editFlag=y&id="+zjkClId;
    var title = "审价任务修改";
    top.addTabGrid(title, href);
}
function searchTask(){
	var code = $("#task_code").val();
	var name = $("#task_name").val();
	var personLiable = $("#task_personLiable").val();
	var auditPriceUnit = $("#task_auditPriceUnit").val();
	var status = $("#task_status").val();
	var finalizeNumber = $("#task_finalizeNumber").val();
	var coordinateFlag= $("#coordinateFlag").val();
	var auditPriceType=$("#auditPriceType").combotree('getText');
  	if(auditPriceType=="请选择"){
		auditPriceType="";
	}
    $('#sjTask').datagrid({
		queryParams: {
			code:code,
			name:name,
			personLiable:personLiable,
			auditPriceUnit:auditPriceUnit,
			status:status,
			finalizeNumber:finalizeNumber,
			auditPriceType:auditPriceType,
			coordinateFlag:coordinateFlag
		}
	}); 
	
}