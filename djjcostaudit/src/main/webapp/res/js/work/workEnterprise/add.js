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
    $('.con').height(500);
    residentPerson();

});
function residentPerson() {
    var bool=false;
    $("#switchBtn1").on('click',function () {
        $("#t0 .filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#residentPerson').datagrid('resize', {
                height: 500
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#residentPerson').datagrid('resize', {
                height: 500
            });
        }
    });
    
    $('#residentPerson').datagrid({
        url: '/workPerson/list',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height:  500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        queryParams:{
        	enterpriseId:enterpriseId,
        },
        columns: [
            [
                {
                    field: 'id',
                    title : '单选框',
                    checkbox: true
                },{
                field:'name',
                title:'姓名',
                width:100,
                align:'center',
                sortable:false,
                formatter: function (value, rowData, index) {
                	if(value){
                		return "<a href='javascript:void(0)' title='"+value+"' style='color:#549de3;padding-left:2px;display: inline-block;' onclick='editInfo(\"" + rowData.id + "\")'>"+value+"</a>";
                	}
                }
                },{
                    field:'sex',
                    title:'性别',
                    width:100,
                    align:'center',
                    sortable:false
                },{
                    field: 'job',
                    title: '岗位',
                    width: 80,
                    align: 'center',
                    sortable: false

                }, {
                    field: 'phone',
                    title: '联系电话',
                    width: 120,
                    align: 'center',
                    sortable: false

                }, {
                    field: 'email',
                    title: '邮箱',
                    width: 120,
                    align: 'center',
                    sortable: false
                }, {
                    field: 'wechat',
                    title: '微信号',
                    align: 'center',
                    width: 120,
                    sortable: false,

                }, {
                    field: 'planStartTimeStr',
                    title: '计划驻场开始时间',
                    align: 'center',
                    width: 140,
                    sortable: false,

                }, {
                    field: 'planEndTimeStr',
                    title: '计划驻场结束时间',
                    align: 'center',
                    width: 140,
                    sortable: false,

                }, {
                    field: 'actualStartTimeStr',
                    title: '实际驻场开始时间',
                    align: 'center',
                    width: 140,
                    sortable: false,

                }, {
                    field: 'actualEndTimeStr',
                    title: '实际驻场结束时间',
                    align: 'center',
                    width: 140,
                    sortable: false,

                }, {
                    field: 'totalDay',
                    title: '累计驻场天数',
                    align: 'center',
                    width: 110,
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(rowData.id){
                    		var actualStartTimeStr=rowData.actualStartTimeStr;
                    		var cuurDate=new Date();
                    		var actualEndTimeStr=rowData.actualEndTimeStr;
                    		if(rowData.effectiveFlag=='有效'){
                    			var t=DateMinus(actualStartTimeStr,cuurDate);
                    			if(t<0){
                    				return 0;
                    			}else{
                    				return DateMinus(actualStartTimeStr,cuurDate)+" 天";
                    			}
                    		}else{
                    			if(actualEndTimeStr){
                    				return DateMinus(actualStartTimeStr,actualEndTimeStr)+" 天";
                    			}else{
                    				return DateMinus(actualStartTimeStr,cuurDate)+" 天";
                    			}
                    		}
                    	}
                    }

                }, {
                    field: 'effectiveFlag',
                    title: '是否有效',
                    align: 'center',
                    width: 80,
                    sortable: false,

                }
            ]
        ],
        toolbar: "#tit1",
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#residentPerson').datagrid('loadData', {total: 1, rows: [{sex: "未找到相关信息！"}]});
            }
        },

    });
    $(window).resize(function () {
        $('#residentPerson').datagrid('resize', {
            height: 500
        });
    });
}

//2个时间之前的天数
function DateMinus(date1,date2){//date1:小日期   date2:大日期
  　　var sdate = new Date(date1); 
  　　var now = new Date(date2); 
  　　var days = now.getTime() - sdate.getTime(); 
  　　var day = parseInt(days / (1000 * 60 * 60 * 24)); 
  　　return day; 
}
function edit() {
    var rows = $('#residentPerson').datagrid('getSelections');
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

function deleteHandlerPerson() {
    var selections = $('#residentPerson').datagrid('getSelections');
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
            url: '/workPerson/del.do',
            type: 'POST',
            data: {'ids': ids},
            traditional: true,
        	success: function(result){
        		  $.messager.progress('close');
				  if(result.status==200){
				  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	$('#residentPerson').datagrid('reload');
				  }else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
        });
    });
}
function addPerson() {
	if("有效"==effectiveFlag){
		var href = "/workPerson/toEdit?editFlag=y&enterpriseId="+enterpriseId;
		var title = "添加驻场人员";
		top.addTabGrid(title, href);
	}else {
		$.messager.alert('提示信息', '企业无效，禁止添加驻场人员！请选择有效并保存后添加', 'warn');
        return;
	}
}
function editInfo(zjkClId) {
    var href = "/workPerson/toEdit?editFlag=y&id="+zjkClId;
    var title = "驻场人员修改";
    top.addTabGrid(title, href);
}
//提交驻场人员查询条件
function searchPer(){
	var namePer = $("#namePer").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var effectiveFlagPer = $("#effectiveFlagPer").val();
	var shenTime=$("#shenTime").val();
    $('#residentPerson').datagrid({
		queryParams: {
			name:namePer,
			shenTime:shenTime,
			startTime:startTime,
			endTime:endTime,
			effectiveFlag:effectiveFlagPer,
			enterpriseId:enterpriseId
		}
	}); 
}