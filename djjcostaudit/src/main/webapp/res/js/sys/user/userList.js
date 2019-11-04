$(function() {
    var bool=false;
    $(".switchBtn").on('click',function () {
        $(".filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#user').datagrid('resize', {
                height: $(window).height()  -40
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#user').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#user').datagrid({
        url: '/sysUser/list',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        columns: [
            [{field: 'id',title : '单选框',checkbox: true},
             {field:'account', title:'账号',width:100,align:'center',sortable:false},
             {field:'name',title:'姓名',width:100,align:'center',sortable:false},
             {field:'orgId',title:'部门',width:100,align:'center',sortable:false},
             {field:'roleId',title:'角色',width:100,align:'center',sortable:false},
             {field: 'sex',title: '性别',width: 80,align: 'center',sortable: false},
             {field: 'phone',title: '电话',width: 100,align: 'center',sortable: false},
             {field: 'wechat',title: '微信号',width: 100,align: 'center',sortable: false},
             {field: 'manager',title: '管理',align: 'center',halign: 'center',width:100,
            	formatter: function(value, row, index) {
                    var result;
                        a= "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='editInfo(\"" + row.id + "\");'>编辑</a>&nbsp;&nbsp;"; 
                       /* b= "<a href='javascript:void(0)' class='btn btn-outline-danger' onclick='del(\"" + row.id + "\");'>删除</a>&nbsp;&nbsp;";*/
                    return a;
                }   
                
            }
            ]
        ],
        toolbar:'#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#user').datagrid('loadData', {total: 1, rows: [{account: "未找到相关信息！"}]});
            }
        },

    });
    $(window).resize(function () {
        $('#user').datagrid('resize', {
            height: tableHeight()
        });
    });
    
    

});


function edit() {
    var rows = $('#user').datagrid('getSelections');
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
function editInfo(id) {
    var href = "/sysUser/toEdit?id="+id;
    var title = "修改用户";
    parent.addTabGrid(title, href);
}
function add() {
    var href = "/sysUser/toEdit";
    var title = "添加用户";
    parent.addTabGrid(title, href);
}
//删除
function deleteHandler() {
    var selections = $('#user').datagrid('getSelections');
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
    	}
    	ids = ids.substring(0,ids.length-1);
    	$.post("/sysUser/del", {"ids":ids},function(result){
    		if(result.status==200){
			    //更新treegrid数据
    		    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			    $("#user").datagrid('reload');
			}else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			}
    	}, "json");
    });
}
function del(id) {
    $.messager.confirm('提示信息', '确认要删除这条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        var ids = "";
    	$.post("/sysUser/del", {"ids":id},function(result){
    		if(result.status==200){
			    //更新treegrid数据
    		    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			    $("#user").datagrid('reload');
			}else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			}
    	}, "json");
    });
}
//查询
function searchUser(){
	var account = $("#account").val().trim();
	var name = $("#name").val().trim();
	var sex = $("#sex").val();
	var orgId=$("#department").val();
    $('#user').datagrid({
		queryParams: {
			account:account,
			name:name,
			sex:sex,
			orgId:orgId
		}
	}); 
}

