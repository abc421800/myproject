var myview = $.extend({}, $.fn.datagrid.defaults.view, {
    renderFooter: function(target, container, frozen){
        var opts = $.data(target, 'datagrid').options;
        var rows = $.data(target, 'datagrid').footer || [];
        var fields = $(target).datagrid('getColumnFields', frozen);
        var table = ['<table class="datagrid-ftable" cellspacing="0" cellpadding="0" border="0"><tbody>'];
        for(var i=0; i<rows.length; i++){
            var styleValue = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : '';
            var style = styleValue ? 'style="' + styleValue + '"' : '';
            table.push('<tr class="datagrid-row" datagrid-row-index="' + i + '"' + style + '>');
            table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
            table.push('</tr>');
        }
        table.push('</tbody></table>');
        $(container).html(table.join(''));
    }
});

$(function () {
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#priceCheck').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#priceCheck').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#priceCheck').datagrid({
    	view:myview,
        url: '/costTask/list?self=1',
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
        showFooter: true,
        rowStyler: function(index,row){
            if (row.code == '汇总'){
                return 'background-color:#ffffcc;color:red;font-weight:bold;';
            }
        },
        queryParams:{
        	auditPriceTypecn:"单价审核",
        },
        frozenColumns:[[
            {
                field: 'id',
                title : '单选框',
                checkbox: true

            }, {
                field: 'code',
                title: '审价编号',
                width: 130,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'name',
                title: '审价任务名称',
                width: 200,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<a href=\"javascript:void(0)\" title="+value+" style=\"color:#549de3;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: block;\" onclick=\"editInfo('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
                }
            },{
                field: 'projectNameShow',
                title: '所属项目',
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: block;\" >"+value+"</span>";
                    }else{
                        return ""
                    }
                }
            },{
                field: 'contractNameShow',
                title: '所属合同',
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: block;\" >"+value+"</span>";
                    }else{
                        return ""
                    }
                }
            }
        ]],
        columns: [
            [
               {
                field: 'auditPriceType',
                title: '审价类型',
                width: 120,
                align: 'left',
                sortable: false

            }, {
                field: 'giveNumber',
                title: '送审条数',
                width: 120,
                align: 'center',
                sortable: false

            }, {
                field: 'auditNumber',
                title: '审定条数',
                width: 120,
                align: 'center',
                sortable: false
            }, {
                field: 'creater',
                title: '创建人',
                width: 100,
                align: 'center',
                sortable: false
            },  {
                field: 'personLiable',
                title: '审价任务负责人',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'auditPriceUnit',
                title: '审价单位',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'status',
                title: '当前状态',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'inlineNumber',
                title: '内联单号',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'reviewReportn',
                title: '评审报告书',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'approval',
                title: '审批表编号',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'receiveTimeStr',
                title: '接收日期',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'deliveryTimeStr',
                title: '送出日期',
                width: 100,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value){
                		return value.substring(0,10);
                	}else{
                		return "";
                	}
                }
            }, {
                field: 'decideTimeStr',
                title: '定审日期',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'category',
                title: '审定资料单价共享',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if(value==null){
                        return "无";
                    }else if(value=="已入库"){
                        return "已入库";
                    }else if (value=="否"){
                        return "已上传";
                    }
                }
            }, {
                field: 'shts',
                title: '办内审核天数',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(rowData.id){
                		var decideTimeStr=rowData.decideTimeStr;
                		var receiveTimeStr=rowData.receiveTimeStr;
                		var taskEndTimeStr=rowData.taskEndTimeStr;
                		var createTimeStr=rowData.createTimeStr;
                		var deliveryTimeStr=rowData.deliveryTimeStr;
                		if(deliveryTimeStr){
                			return DateMinus(receiveTimeStr,deliveryTimeStr.substring(0,10))+" 天";
                		}else{
                			if(decideTimeStr){
                				return DateMinus(receiveTimeStr,decideTimeStr)+" 天";
                			}else{
                				return DateMinus(receiveTimeStr,getNowFormatDate())+" 天";
                			}
                		}
                	}else{
                		return "";
                	}
                }
            }, {
                field: 'coordinateFlag',
                title: '协调',
                width: 80,
                align: 'center',
                sortable: false
            }]
        ],
        toolbar: '#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#priceCheck').datagrid('loadData', {total: 1, rows: [{code: "未找到相关信息！"}]});
            }
        }

    });
    $(window).resize(function () {
        if (bool) {
            $('#priceCheck').datagrid('resize', {
                height: $(window).height() - 40
            });
        } else {
            $('#priceCheck').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
});

function edit() {
    var rows = $('#priceCheck').datagrid('getSelections');
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
	 var href = "/costTask/toEdit?editFlag=y&id="+zjkClId;
    var title = "审价任务修改";
    top.addTabGrid(title, href);
}
function showInfo(zjkClId) {
    var href = "/costTask/toEdit?editFlag=n&id="+zjkClId;
    var title = "审价任务详情";
    top.addTabGrid(title, href);
}
//在审价任务列表上添加审价任务（第二种）
function add(flag) {
    var href = "/costTask/toAdd?editFlag=y&auditPriceType="+flag;
    var title = "审价任务添加";
    top.addTabGrid(title, href);
}
//删除
function delTask(){
	 var selections = $('#priceCheck').datagrid('getSelections');
	    if (selections.length == 0) {
	        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
	        return;
	    }
	    $.messager.confirm('提示信息', '确认要删除这' + selections.length + '条记录吗？', function (isOk) {
	        if (!isOk) {
	            return;
	        }
	        var ids = "";
			for(var i = 0;i<selections.length;i++){
	    		ids+=selections[i].id+",";
	    		if(currentUser!=selections[i].creater && dataUpdate=="false"){
	            	$.messager.alert('提示信息', '抱歉！您没权限删除该条数据：'+selections[i].name, 'warn');
	                return;
	            }
	    	}
	    	ids = ids.substring(0,ids.length-1);
	    	$.post("/costTask/del", {"ids":ids},function(result){
	    		if(result.status==200){
	    		    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				    $("#priceCheck").datagrid('reload');
				}else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				}
	    	}, "json");
	    });
}
function searchTask(){
	var projectName = $("#projectName").val().trim();
	var contractName = $("#contractName").val().trim();
	var name = $("#name").val();
	var personLiable = $("#personLiable").val();
	var auditPriceUnit = $("#auditPriceUnit").val();
	var status = $("#status").val();
	var code = $("#code").val();
	var coordinateFlag= $("#coordinateFlag").val();
	var shenTime=$("#shenTime").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var auditPriceType=$("#auditPriceType").val();
    var category = $("#category").val();
    $('#priceCheck').datagrid({
		queryParams: {
			auditPriceTypecn:"单价审核",
			projectName:projectName,
			contractName:contractName,
			name:name,
			personLiable:personLiable,
			auditPriceUnit:auditPriceUnit,
			status:status,
			code:code,
			coordinateFlag:coordinateFlag,
			shenTime:shenTime,
			startTime:startTime,
			endTime:endTime,
            auditPriceType: auditPriceType,
            category: category
		}
	}); 
}
//重置
function refresh() {
	var json1={tabTitle:'单价审核',url:'/forward_task_priceCheck'};
	window.parent.refreshTab(json1); 
}
function DateMinus(date1,date2){//date1:小日期   date2:大日期
  　　var sdate = new Date(date1); 
  　　var now = new Date(date2); 
  　　var days = now.getTime() - sdate.getTime(); 
  　　var day = parseInt(days / (1000 * 60 * 60 * 24)); 
  　　return day; 
}
//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
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
    return currentdate;
}
//审定资料单价共享显示
