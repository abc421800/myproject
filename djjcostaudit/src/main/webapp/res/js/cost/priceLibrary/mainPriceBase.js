$(function () {
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#mainPriceBase').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#mainPriceBase').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#mainPriceBase').datagrid({
        url: '/costPriceLibrary/list',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: false,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        idField:"id",
        singleSelect: false,
        queryParams:{
        	typeLibraryAll:"主材定价,设计变更费用,现场签证",
        },
        frozenColumns:[[
            {
                field: 'id',
                title : '单选框',
                checkbox: true
            },
            {
                field: 'name',
                title: '新增材料<br>或设备名称',
                width: 160,
                halign:'center',
                align: 'left',
                sortable: false,
               formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }
            }, {
                field: 'feature',
                title: '规格型号<br>及技术参数',
                width: 230,
                halign:'center',
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }
            }
        ]],
        columns: [
            [
               {
                field: 'engineeringNumber',
                title: '使用数量',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }
            }, {
                field: 'usePosition',
                title: '使用部位',
                width: 220,
                halign:'center',
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }
            }, {
                field: 'basis',
                title: '申报依据<br>（含产生原因及报价依据）简要说明',
                width: 220,
                halign:'center',
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }

            }, {
                field: 'biddingBrand',
                title: '投标品牌<br>/产地',
                align: 'center',
                width: 150,
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }

            }, {
                field: 'useBrand',
                title: '使用品牌<br>/产地',
                align: 'center',
                width: 150,
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }

            }, {
                field: 'unit',
                title: '计量单位',
                width: 180,
                align: 'center',
                sortable: false
            }, {
                field: 'contractingPrice',
                title: '承包单位<br>申报单价（元）',
                width: 150,
                halign:'center',
                align: 'center',
                sortable: false,
                formatter:function(value,rowData,index){
                	if(value!=null){
                		return addQianFenFu(String(value))
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'supervisorPrice',
                title: '监理单位<br>审核单价（元）',
                width: 150,
                align: 'center',
                sortable: false,
                formatter:function(value,rowData,index){
                	if(value!=null){
                		return addQianFenFu(String(value))
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'settlementPrice',
                title: '结算审价<br>部审核单价（元）',
                width: 150,
                align: 'center',
                sortable: false,
                formatter:function(value,rowData,index){
                	if(value!=null){
                		return addQianFenFu(String(value))
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'description',
                title: '备注',
                width: 200,
                halign:'center',
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
	               	if(value!=null){
	                       return "<span title="+value.replace(/\s+/g,"")+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
	               	}else{
	               		return ""
	               	}
               }
            }, {
                field: 'projectName',
                title: '项目名称',
                width: 220,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(null!=rowData.id && value!=null){
                		return '<a href="javascript:void(0)" style="color:#549de3;padding-left:2px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;max-width: 100%;vertical-align: middle;" onclick="showInfoProject('+"'"+rowData.projectId+"'"+')">'+value+'</a>';
                	}
                }
            }, {
                field: 'contractName',
                title: '合同名称',
                width: 220,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(null!=rowData.id && value!=null){
                	 return "<a href='javascript:void(0)' title='"+value+"' style='color:#549de3;padding-left:2px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;max-width: 100%;vertical-align: middle;' onclick=\"showInfoContract('"+rowData.contractId+"')\">"+value+"</a>";
                	}
                }
            }, {
                field: 'contractExeDepartment',
                title: '合同执行部门',
                width: 150,
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
                field: 'taskCode',
                title: '审价编号',
                width: 220,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(null!=rowData.id){
                	 return "<a href=\"javascript:void(0)\" style=\"color:#549de3;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;max-width: 100%;vertical-align: middle;\" title=\""+value+"\" onclick=\"showInfoTask('"+rowData.taskId+"')\">"+value+"</a>";
                	}
                }
            }, {
                field: 'taskPersonLiable',
                title: '审价任务负责人',
                width: 120,
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
                field: 'typeLibrary',
                title: '审价类型',
                width: 120,
                align: 'center',
                sortable: false
            }
            /*
            , {
                field: 'dingshenDate',
                title: '定审日期',
                width: 120,
                align: 'center',
                sortable: false
            }*/
            , {
                field: 'createTimeStr',
                title: '发布时间',
                width: 120,
                align: 'center',
                sortable: false
            },{
                field: 'manager',
                title: '管理',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(null!=rowData.id){
                		if(currentUser == rowData.taskPersonLiable || dataUpdate=="true"){
                			return '<a href="javascript:void(0)" class="btn btn-outline-danger" onclick="deleteHandler('+"'"+rowData.id+"'"+')">删除</a>&nbsp;&nbsp;';
                		}
                	}
                }
            }]
        ],
        toolbar: '#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#mainPriceBase').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        }
    });
    $(window).resize(function () {
        if (bool) {
            $('#mainPriceBase').datagrid('resize', {
                height: $(window).height() - 40
            });
        } else {
            $('#mainPriceBase').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
});

function addTab() {
    window.parent.addTabGrid('合同台账详情', 'page/mainPriceBase/mainPriceBaseDetails.html')
}
function edit() {
    var rows = $('#mainPriceBase').datagrid('getSelections');
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
//导出
function exportDate(){
    var selections = $('#mainPriceBase').datagrid('getSelections');
    if (selections.length == 0) {
    	$.messager.confirm('提示信息', '确认要导出全部记录吗？', function (isOk) {
    		if(!isOk) {
    			return;
    		}
    		window.location.href = "/costPriceLibrary/exportPriceLibraryZc";
    	});
    }else{
    	$.messager.confirm('提示信息', '确认要导出这' + selections.length + '条记录吗？', function (isOk) {
    		if(!isOk) {
    			return;
    		}
    		var ids = '';
    		for (var i in selections) {
    			ids+=selections[i].id+",";
    		}
    		ids = ids.substring(0,ids.length-1);
    		window.location.href = "/costPriceLibrary/exportPriceLibraryZc?id="+ids;
    	});
    }
}
function editInfo(zjkClId) {
    var href = "page/mainPriceBase/mainPriceBase_add.html";
    var title = "合同台账修改";
    top.addTabGrid(title, href);
}

function deleteHandler(id) {
    $.messager.confirm('提示信息', '确认要删除这条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        $.post("/costPriceLibrary/del", {"ids":id},function(result){
    		if(result.status==200){
			    //更新treegrid数据
    		    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			    $("#mainPriceBase").datagrid('reload');
			}else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			}
    	}, "json");
    });
}
//提交主材单价库查询条件
function searchMai(){
	var name = $("#name").val().trim();
	var feature = $("#feature").val();
	var biddingBrand = $("#biddingBrand").val();
	var useBrand = $("#useBrand").val();
	var projectName = $("#projectName").val();
	var contractName = $("#contractName").val();
	var taskPersonLiable = $("#taskPersonLiable").val();
	var createTimeStart = $("#createTimeStart").val();
	var createTimeEnd = $("#createTimeEnd").val();
	var typeLibrary= $("#typeLibrary").val();
    $('#mainPriceBase').datagrid({
		queryParams: {
			name:name,
			feature:feature,
			biddingBrand:biddingBrand,
			useBrand:useBrand,
			projectName:projectName,
			contractName:contractName,
			taskPersonLiable:taskPersonLiable,
			typeLibrary:typeLibrary,
			typeLibraryAll:"主材定价,设计变更费用,现场签证",
			createTimeStart:createTimeStart,
			createTimeEnd:createTimeEnd
		}
	}); 
}
//项目详情
function showInfoProject(zjkClId) {
	var href = "/costProject/editProject?editFlag=n&projId="+zjkClId;
	var title = "项目详情";
	top.addTabGrid(title, href);
}
//合同详情
function showInfoContract(zjkClId) {
    var href = "/costContract/toEdit?editFlag=n&id="+zjkClId;
    var title = "合同详情";
    top.addTabGrid(title, href);
}
//任务详情
function showInfoTask(zjkClId) {
    var href = "/costTask/toEdit?editFlag=y&id="+zjkClId;
    var title = "审价任务详情";
    top.addTabGrid(title, href);
}