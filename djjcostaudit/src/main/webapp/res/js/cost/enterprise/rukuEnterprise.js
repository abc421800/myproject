$(function() {
    var bool=false;
    $(".switchBtn").on('click',function () {
        $(".filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#rukuEnterprise').datagrid('resize', {
                height: $(window).height()  -40
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#rukuEnterprise').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    var batch=$("#batch").val();
    $('#rukuEnterprise').datagrid({
        url: '/costEnterprise/list',
        loadMsg: '数据加载中,请稍候...',
        nowrap: true,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: false,
        striped: true,
        collapsilble: true,
        remoteSort:false,
        pagination: true, //分页控件
        pageSize: 50,
        singleSelect: false,
        queryParams:{
        	effectiveFlag:"在库",
        	batch:batch
        },
        frozenColumns:[[
                        {
                            field: 'id',
                            title : '单选框',
                            checkbox: true

                        },{
                            field: 'name',
                            title: '入库企业名称',
                            width: 250,
                            align: 'left',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                           	if(value!=null){
                               return "<a href=\"javascript:void(0)\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                                	  "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                                	  "max-width: 100%;vertical-align: middle;\" onclick=\"editInfo('"+rowData.id+"')\">"+value+"</a>";
                               }else{
                                   return ""
                               }
                           }

                        }, {
                            field:'simpleName',
                            title:'简称',
                            width:100,
                            align:'left',
                            sortable:false
                            
                        },
                        /*{
                            field:'code',
                            title:'企业编号',
                            width:120,
                            align:'center'
                        }*/
                    ]],
        columns: [[
            {
                field: 'contacts',
                title: '负责人',
                width: 150,
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
                field: 'contactsPhone',
                title: '负责人联系方式',
                width: 200,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            },
            /*
            , {
                field: 'fax',
                title: '传真',
                align: 'center',
                width: 120,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            }, {
                field: 'email',
                title: '邮箱',
                align: 'center',
                width: 170,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            }, {
                field: 'address',
                title: '联系地址',
                align: 'left',
                width: 200,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }
            */
               {
                field: 'status',
                title: '入库状态',
                align: 'center',
                width: 100,
                sortable: false

            }, {
                field: 'r_year',
                title: '入库批次',
                width: 130,
                align: 'center',
                sortable: false,

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
            }, {
                field: 'yaohaoGradeRk',
                title: '本年度摇号档次',
                width: 100,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index){
                    if(rowData.id){
                        if(value!=null){
                            return value;
                        }else{
                            return "第二档";
                        }
                    }
                }
            }, {
                field: 'winNum',
                title: '中签项目（个）',
                width: 100,
                align: 'center',
                sortable: false,

            }, {
                field: 'serviceAmountRk',
                title: '累计服务金额（万元）',
                width: 130,
                align: 'center',
                sortable: false,
                formatter:function(value,rowData,index){
                    if(value!=null){
                    	var tem=Math.round(value/10000 * 100)/100;
                        return addQianFenFu(String(tem))
                    }else{
                        return ""
                    }
                }
            }, {
                field: 'stationingStr',
                title: '是否驻场',
                width: 80,
                align: 'center',
                sortable: false,

            }, {
                field: 'enterpriseEndStr',
                title: '服务到期时间',
                width: 150,
                align: 'center',
                sortable: false,
                formatter:function(value,rowData,index){
                    if(value!=null){
                    	
                        return value.substring(0,10);
                    }else{
                        return ""
                    }
                }

            }/*, {
                field: 'creater',
                title: '创建人',
                align: 'center',
                width: 100,
                sortable: false,

            }, {
                field: 'description',
                title: '备注',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }*/
            ]
        ],
        toolbar:'#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#rukuEnterprise').datagrid('loadData', {total: 1, rows: [{simpleName: "未找到相关信息！"}]});
            }
        },
    });
    $(window).resize(function () {
        if(bool){
            $('#rukuEnterprise').datagrid('resize', {
                height: $(window).height() - 40
            });
        }else{
            $('#rukuEnterprise').datagrid('resize', {
                height: tableHeight()
            });
        }
    });

});
function edit() {
    var rows = $('#rukuEnterprise').datagrid('getSelections');
    if (rows == undefined || rows == null || rows.length == 0) {
        $.messager.alert('提示信息', '请选择一条记录', 'warn');
        return;
    }
    if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一条记录', 'warn');
        return;
    }
    editInfo(rows[0].id);

}
function editInfo(zjkClId) {
    var href = "/costEnterprise/toEdit?editFlag=y&id="+zjkClId;
    var title = "入库企业修改";
    top.addTabGrid(title, href);
}
function add() {
    var href = "/costEnterprise/toEdit?editFlag=y";
    var title = "入库企业添加";
    top.addTabGrid(title, href);
}
function showInfo(zjkClId) {
    var href = "/costEnterprise/toEdit?editFlag=n&id="+zjkClId;
    var title = "入库企业详情";
    top.addTabGrid(title, href);
}
function deleteHandler() {
    var selections = $('#rukuEnterprise').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '确认要删除这' + selections.length + '条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        var ids = '';
        for (var i in selections) {
            ids = ids+','+selections[i].id;
        }
        console.log(ids);
        $.ajax({
            url: '/costEnterprise/deleteProject.do',
            type: 'POST',
            data: {'ids': ids},
            traditional: true,
        	success: function(result){
        		  $.messager.progress('close');
				  if(result.status==200){
				  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	$('#rukuEnterprise').datagrid('reload');
				  }else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
        });
    });
}
//提交入库企业查询条件
function searchCon(){
	var name = $("#name").val();
	var effectiveFlag = $("#effectiveFlag").val();
	var batch = $("#batch").val();
	var stationing = $("#stationing").val();
	var effectiveFlag = $("#effectiveFlag").val();
	var assessResultRk = $("#assessResultRk").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
    $('#rukuEnterprise').datagrid({
		queryParams: {
			name:name,
			effectiveFlag:effectiveFlag,
			batch:batch,
			stationing:stationing,
			assessResultRk:assessResultRk,
			startTime:startTime,
			endTime:endTime
		}
	}); 
}
