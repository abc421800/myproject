$(function() {
    var bool=false;
    $(".switchBtn").on('click',function () {
        $(".filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#register').datagrid('resize', {
                height: $(window).height()  -40
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#register').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    
    $('#register').datagrid({
        url: '/workEnterprise/list',
        loadMsg: '数据加载中,请稍候...',
        nowrap: true,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: true,
        striped: true,
        collapsilble: true,
        remoteSort:false,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        queryParams:{
        	deleteFlag:'1',
        },
        columns: [[
            {
                field: 'id',
                title : '单选框',
                checkbox: true
            },{
                field:'name',
                title:'驻场企业名称',
                width:200,
                align:'left',
                sortable:false,
                formatter: function (value, rowData, index) {
                  	 if(value!=null){
                           return "<a href=\"javascript:void(0)\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                           		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                           		"max-width: 100%;vertical-align: middle;\" onclick=\"editInfoEnterprise('"+rowData.id+"')\">"+value+"</a>";
                       }else{
                           return ""
                       }
                  }
            },{
                field: 'type',
                title: '企业类型',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'personLiable',
                title: '负责人',
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

            }, {
                field: 'phone',
                title: '手机',
                width: 100,
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
                field: 'telephone',
                title: '固话',
                align: 'center',
                width: 100,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            }, {
                field: 'serviceStartStr',
                title: '服务开始时间',
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
                field: 'serviceEndStr',
                title: '服务结束时间',
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
                field: 'effectiveFlag',
                title: '是否有效',
                align: 'center',
                width: 70,
                sortable: false

            }, {
                field: 'creater',
                title: '创建人',
                width: 110,
                align: 'center',
                sortable: false,

            }, {
                field: 'remark',
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
            }
            ]
        ],
        toolbar:'#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#register').datagrid('loadData', {total: 1, rows: [{type: "未找到相关信息！"}]});
            }
        },
    });
    $(window).resize(function () {
        if(bool){
            $('#register').datagrid('resize', {
                height: $(window).height() - 40
            });
        }else{
            $('#register').datagrid('resize', {
                height: tableHeight()
            });
        }
    });

});
function editInfoEnterprise(zjkClId) {
    var href = "/workEnterprise/toEdit?editFlag=y&id="+zjkClId;
    var title = "驻场企业修改";
    top.addTabGrid(title, href);
}
function add() {
    var href = "/workEnterprise/toEdit?editFlag=y";
    var title = "添加驻场企业";
    top.addTabGrid(title, href);
}

function deleteHandler() {
    var selections = $('#register').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '该驻场企业包含的驻场人员将一并删除，确认要删除这' + selections.length + '条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        var ids = [];
        for (var i in selections) {
            ids[i] = selections[i].id;
        }
        console.log(ids);
        $.ajax({
            url: '/workEnterprise/del.do',
            type: 'POST',
            data: {'ids': ids.toString()},
            traditional: true,
        	success: function(result){
        		  $.messager.progress('close');
				  if(result.status==200){
				  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	$('#register').datagrid('reload');
				  }else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
        });
    });
}
//提交驻场企业查询条件
function searchCon(){
	var name = $("#name").val();
	var type = $("#type").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var effectiveFlag = $("#effectiveFlag").val();
    $('#register').datagrid({
		queryParams: {
			name:name,
			type:type,
			startTime:startTime,
			endTime:endTime,
			effectiveFlag:effectiveFlag,
			deleteFlag:1
		}
	}); 
}
