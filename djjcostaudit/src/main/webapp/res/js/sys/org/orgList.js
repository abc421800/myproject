
var IsCheckFlag = true;
var IsCheckFlag_user = true;
var datagrid=$("#list");
var datagrid_user=$("#user");
function list(parentId){
	datagrid.datagrid({
        url: "/sysOrg/list.do?pid="+parentId,
        height: tableHeight(),
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        //scrollbarSize:0,//不用滚动条
        fitColumns: true,
        singleSelect: false,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        rownumbers: true, //行号
        showFooter: true,
        pageSize: 10, //每页显示的记录条数，默认为30
        pageList:[10,20,30],
        frozenColumns: [[{field: 'ck',checkbox: true}]],
        toolbar: '#tit1',
        columns: [[
        	{field:'name',title:'机构名称',width:150,align:'left'},
	        {field:'description',title:'描述',width:150,align:'left'},
	        {field:'createTimeStr',title:'创建时间',width:100,align:'center'},
            {field: 'manager',title: '管理',align: 'center',halign: 'center',width: 100,  
            	formatter: function(value, row, index) {
                    var result;
                        a= "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='upd(\"" + row.id + "\");'>编辑</a>&nbsp;&nbsp;"; 
                    return a;
                }   
                
            }]
        ],
        onBeforeCheck:function(rowIndex,rowData){
            if(rowIndex==null){
                return true;
            }
            if(rowIndex.isLink=="false"){
                return false;
            }
        },
    	onClickCell: function (rowIndex, field, value) {
	    	if("manager"==field){
	    		datagrid.datagrid("unselectRow", rowIndex);
	    		IsCheckFlag = false;
	    	}
	    },
	    onSelect: function (rowIndex, rowData) {
	         if (!IsCheckFlag) {
	             IsCheckFlag = true;
	          datagrid.datagrid("unselectRow", rowIndex);
	         }
	    },                    
	    onUnselect: function (rowIndex, rowData) {
	         if (!IsCheckFlag) {
	             IsCheckFlag = true;
	          datagrid.datagrid("selectRow", rowIndex);
	         }
	    }
    });
    $(window).resize(function () {
    	datagrid.datagrid('resize', {
            height: tableHeight()
        });
    });
}

function user(parentId){
	datagrid_user.datagrid({
        url: "sysOrg/userList?orgId="+parentId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height:"auto",
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        toolbar: '#tit2',
        columns: [[
            {field:'account', title:'账号',width:100,align:'center',sortable:false,
        		formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<a href=\"javascript:void(0)\" style=\"color:#549de3;\" onclick=\"editInfo('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
                }	
            },
            {field:'name',title:'姓名',width:100,align:'center',sortable:false},
            {field: 'sex',title: '性别',width: 80,align: 'center',sortable: false},  
            {field: 'phone',title: '电话',width: 100,align: 'center',sortable: false},
            {field: 'wechat',title: '微信号',width: 100,align: 'center',sortable: false}  
            ]
        ],
        onBeforeCheck:function(rowIndex,rowData){
            if(rowIndex==null){
                return true;
            }
            if(rowIndex.isLink=="false"){
                return false;
            }
        },
    	onClickCell: function (rowIndex, field, value) {
	    	if("manager"==field){
	    		datagrid_user.datagrid("unselectRow", rowIndex);
	    		IsCheckFlag_user = false;
	    	}
	    },
	    onSelect: function (rowIndex, rowData) {
	         if (!IsCheckFlag_user) {
	        	 IsCheckFlag_user = true;
	             datagrid_user.datagrid("unselectRow", rowIndex);
	         }
	    },                    
	    onUnselect: function (rowIndex, rowData) {
	         if (!IsCheckFlag_user) {
	        	 IsCheckFlag_user = true;
	             datagrid_user.datagrid("selectRow", rowIndex);
	         }
	    }
    });
    $(window).resize(function () {
    	datagrid_user.datagrid('resize', {
            height: tableHeight()
        });
    });
}

/* 删除机构下面的用户  */
function delUser(id){
	$.messager.confirm('温馨提示','确定删除吗？',function(r){
		if(r){
			$.post("/sysUser/del",{"ids":id},function(result){
				if(result){
					datagrid_user.datagrid("reload");
					window.parent.showMessager("温馨提示", "<b>删除成功!</b>", 5000, "slide");
				}else{
					$.messager.alert("温馨提示","删除异常,请联系管理员!", "error");
				}
			});
		}
	});
}

/* 修改机构*/
function upd(id){
	$("#dataList").hide();
	$("#details").show();
	loadForm(id); //加载表单的数据
	//loadUser(id); 
	$("#user_data").hide();
	//$("#fatherMenu").val(note.id); //选中下拉
	//保存按钮的事件
	$("#save").off().click(function(){
		ajaxUpdate();  
	});
}
/* 修改机构下面用户*/
function updUser(id){
	window.parent.addTabGrid('编辑用户', '/user/toedit?id='+id);
	
}
function editInfo(id) {
    var href = "/sysUser/toEdit?id="+id;
    var title = "修改用户";
    parent.addTabGrid(title, href);
}

