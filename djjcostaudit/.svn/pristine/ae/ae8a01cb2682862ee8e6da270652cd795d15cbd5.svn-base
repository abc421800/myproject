$(function () {
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#stakeholders').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#stakeholders').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#stakeholders').datagrid({
        url: '/projectStakeholderroler/list',
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
        columns:[[   
                  {field:'ck',checkbox:true},
     			 {field:'name',title:'角色名称',width:150,align:'center'},
     	         {field:'description',title:'描述',width:150,align:'center'},
     	         {field:'num',title:'排序号',width:150,align:'center'},
     	         {field:'creater',title:'创建者',width:150,align:'center'},
     	         {field:'opt',title:'操作',width:100,align:'center',  
                     formatter:function(value,row,index){  
                   	    var a = '<a class="btn btn-outline-primary" href="javascript:void(0);"  onclick="editInfo(\''+ row.id +'\')">修改</a>'; 
                           return a;  
                     }  
                }
     	     ]],
     	   // frozenColumns:[[{field:'ck',checkbox:true}]],
        toolbar: '#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#stakeholders').datagrid('loadData', {total: 1, rows: [{code: "未找到相关信息！"}]});
            }
        },



    });
    $(window).resize(function () {
        $('#stakeholders').datagrid('resize', {
            height: tableHeight()
        });
    });

});
function editInfo(zjkClId) {
    var href = "/projectStakeholderroler/toEdit?id="+zjkClId;
    var title = "项目干系人修改";
    top.addTabGrid(title, href);
}
function deleteHandler() {
    var selections = $('#stakeholders').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '确认要删除这' + selections.length + '条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        $.messager.progress();
        var ids = '';
        for (var i in selections) {
            ids = ids+','+selections[i].id;
        }
        $.ajax({
            url: '/projectStakeholderroler/del.do',
            type: 'POST',
            data: {'ids': ids},
            traditional: true,
        	success: function(result){
        		  $.messager.progress('close');
				  if(result.status==200){
				  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	$('#stakeholders').datagrid('reload');
				  }else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
        });
    });
}