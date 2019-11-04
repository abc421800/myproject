var scrollHeight=0;
var str='';
$(function () {
    $(window).resize(function () {
        $("#tt").tabs({
            tabHeight: 32,
            plain: true,
            height: 'auto',
            onSelect:function (title) {
                if(title!=str){
                    $('.wrap_center').scrollTop(scrollHeight);
                    str=title;
                }
            }
        });
    }).resize();
    $(".tabs").on("click","li",function(){
        scrollHeight=$('.wrap_center').scrollTop();
    });
    fujian(contractId);
    wlfile(contractId);
    sjTask(contractId);
    tendering(contractId);
});


function wlfile(id) {
    var bool=false;
    $("#switchBtn1").on('click',function () {
        $("#selectDiv .filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#wlfile').datagrid('resize', {
                height: 500
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#wlfile').datagrid('resize', {
                height: 500
            });
        }
    });
    $('#wlfile').datagrid({
        url: '/costContract/docList?contractId='+id,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height:  500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
                  [
//                   {
//                       field: 'id',
//                       title : '单选框',
//                       checkbox: true
//                   },
				{
                   field:'symbol',
                   title:'文号',
                   width:150,
                   align:'center',
                   sortable:false,
                   formatter: function (value, rowData, index) {
                    	 if(value!=null){
                    		 return "<a href=\"javascript:void()\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                     		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                     		"max-width: 100%;vertical-align: middle;\" onclick=\"showInfoDoc('"+rowData.id+"')\">"+value+"</a>";
                         }else{
                             return ""
                         }
                    }
               },{
                   field:'name',
                   title:'文件标题',
                   width:150,
                   align:'left',
                   sortable:false
                   
               },{
                   field: 'comeGoFlag',
                   title: '往/来',
                   width: 50,
                   align: 'center',
                   sortable: false

               }, {
                   field: 'auditPriceFlag',
                   title: '是否审价',
                   width: 50,
                   align: 'center',
                   sortable: false

               }, {
                   field: 'comeGoUnit',
                   title: '往来单位',
                   width: 100,
                   align: 'left',
                   sortable: false,
                   formatter: function (value, rowData, index) {
                   	if(value!=null){
                   		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                   	}else{
                   		return ""
                   	}
                   }
               }, {
                   field: 'documentTime',
                   title: '文件日期',
                   align: 'center',
                   width: 120,
                   sortable: false,
                   formatter: formatterdate

               }
               /*
               ,  {
                   field: 'contractName',
                   title: '对应合同',
                   align: 'left',
                   width: 200,
                   sortable: false

               }*/
               , {
                   field: 'taskName',
                   title: '对应审价',
                   width: 140,
                   align: 'left',
                   sortable: false,

               }, {
                   field: 'number',
                   title: '份数',
                   width: 100,
                   align: 'left',
                   sortable: false,
                   formatter: function (value, rowData, index){
                   	if(value!=null){
                           return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                   	}else{
                   		return ""
                   	}
                   }
               },
               /*
               , {
                   field: 'personLiable',
                   title: '负责人',
                   width: 80,
                   align: 'center',
                   sortable: false
               }*/
               {field: 'manager',title: '管理',align: 'center',halign: 'center',width:120,  
               	formatter: function(value, row, index) {
               		if(row.id){
               			var  a= "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='taskDialog(\"" + row.dpctRelationId + "\",\"" + row.id + "\");'>添加任务</a>&nbsp;&nbsp;"; 
               			var  b= "<a href='javascript:void(0)' class='btn btn-outline-danger' onclick='del(\"" + row.id + "\",\"" + row.taskName + "\");'>删除</a>&nbsp;&nbsp;";
               			return a+b;
               		}else{
               			return "";
               		}
                 }   
                   
               }
               ]
           ],
        toolbar:'#tit6',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#wlfile').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        }
    });
    $(window).resize(function () {
        $('#wlfile').datagrid('resize', {
            height: 500
        });
    });
}
function sjTask(id) {
    var bool=false;
    $("#switchBtn2").on('click',function () {
        $("#tit7 .filter").toggle();
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
        url: '/costTask/list?contractId='+id,
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
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
               	 if(value!=null){
               		 return "<a href=\"javascript:void()\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                		"max-width: 100%;vertical-align: middle;\" onclick=\"showInfoTas('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
               }
            }, {
                field: 'name',
                title: '审价任务名称',
                width: 230,
                align: 'left',
                sortable: false,
                formatter: function(value, rowData, index) {
                    return value;
                }
            }, {
                field: 'auditPriceType',
                title: '审价类型',
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

            }, {
                field: 'createTime',
                title: '创建日期',
                width: 100,
                align: 'center',
                sortable: false,
                formatter: formatterdate

            }, {
                field: 'personLiable',
                title: '审价任务负责人',
                align: 'center',
                width: 80,
                sortable: false,

            },{
                field: 'auditPriceUnit',
                title: '审价单位',
                align: 'left',
                halign:'center',
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
                width: 100,
                sortable: false

            }, {
                field: 'finalizeNumber',
                title: '定案文号',
                width: 180,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            },  {
                field: 'coordinateFlag',
                title: '协调',
                width: 50,
                align: 'center',
                sortable: false
            }
            ]
        ],
        toolbar: "#tit7",
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
//删除往来文件关联
function del(id,taskName){
	var msg=""
	if(taskName=="null"){
		msg="确认要删除吗？";
	}else{
		msg="当前往来文件以及它已关联的审价任务也将一并删除，确认要删除？";
	}
	$.messager.confirm('提示信息',msg, function (isOk) {
        if (!isOk) {
            return;
        }
		var projectId=$("#containProjectId").val();
		$.ajax({
		  	url:"/costDocument/removeDpctRelation",
		  	dataType: "json",
		  	type:"post",
		  	async: false,
		  	data: {"projectId":projectId,"contractId":contractId,"id":id},
		  	success: function(result){
			  	if(result.status==200){
		    	    window.parent.$('#document').dialog('close');
		    	    $('#wlfile').datagrid("reload");
			  	 	window.parent.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			  	}else {
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  	}
		  	}
		});
	});
}

//添加审价任务
function taskDialog(dpctRelationId,documentId) {
    $('#taskDialog').dialog({
        title: '选择审价类型',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top:100,
        href:'/forward_contract_sjTask?dpctRelationId='+dpctRelationId+'&documentId='+documentId,
        //content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'sjTask.html'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}

function toAddTask(dpctRelationId,documentId){
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
function showInfoDoc(zjkClId) {
    var href = "/costDocument/toEdit?editFlag=y&id="+zjkClId;
    var title = "往来文件修改";
    top.addTabGrid(title, href);
}
function showInfoTas(zjkClId) {
    var href = "/costTask/toEdit?editFlag=y&id="+zjkClId;
    var title = "审价任务修改";
    top.addTabGrid(title, href);
}
//提交往来文件查询条件
function documentSelectSubmit(){
  	var tableId='wlfile';
  	var name=$("#documentName").val();
  	var symbol=$("#symbol").val();
  	var comeGoFlag=$("#comeGoFlag option:selected").val();	
  	var auditPriceFlag = $("#auditPriceFlag option:selected").val();
	var comeGoUnit=$("#comeGoUnit").val();	  
	var personLiable = $("#personLiable5").val();
  	var documentTimeStart=$('#documentTimeStart').val();
  	var documentTimeEnd=$("#documentTimeEnd").val();
 
 	$('#'+tableId).datagrid('options').queryParams = {
           'symbol': symbol,  
           'name':name,
           'personLiable':personLiable,
           'comeGoFlag':comeGoFlag,
           'auditPriceFlag':auditPriceFlag,
           'comeGoUnit':comeGoUnit,
            'startTime':documentTimeStart,
            'endTime':documentTimeEnd
       };
   $('#' + tableId).datagrid("reload");
}
//提交审价任务查询条件
function priceInquiry(){
  	var tableId='sjTask';
  	var code=$("#taskCode").val();
  	var name=$("#taskName").val();
	var personLiable=$("#personLiable6").val();	  
	var auditPriceUnit = $("#taskUnit").val();
	var status = $("#status option:selected").val();
	var finalizeNumber = $("#finalizeNumber").val();
	var coordinateFlag = $("#coordinateFlag option:selected").val();
  	var createTimeStart=$('#createTimeStart').val();
  	var createTimeEnd=$("#createTimeEnd").val();
  	var coordinateFlag= $("#coordinateFlag").val();
  	var auditPriceType=$("#auditPriceType").combotree('getText');
  	if(auditPriceType=="请选择"){
		auditPriceType="";
	}
 	$('#'+tableId).datagrid('options').queryParams = {
           'code': code,  
           'name':name,
           'personLiable':personLiable,
           'auditPriceType':auditPriceType,
           'auditPriceUnit':auditPriceUnit,
           'status':status,
           'finalizeNumber':finalizeNumber,
           'coordinateFlag':coordinateFlag,
            'startTime':createTimeStart,
            'endTime':createTimeEnd,
            'coordinateFlag':coordinateFlag
       };
   $('#' + tableId).datagrid("reload");
}