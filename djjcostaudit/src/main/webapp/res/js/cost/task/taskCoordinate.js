function getTaskCoordinageDialog(title) {
	var id="";
	if(title=='修改协调事项'){
		var rows = $('#coordinationMatters').datagrid('getSelections');
		if (rows == undefined || rows == null || rows.length == 0) {
			$.messager.alert('提示信息', '请选择一条记录', 'warn');
			return;
		}
		if (rows.length > 1) {
			$.messager.alert('提示信息', '只能选择一条记录', 'warn');
			return;
		}
		id=rows[0].id;
	}
    $('#taskCoordinateDialog').dialog({
        title: title,
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top:100,
        href: '/costTaskCoordinate/toEdit?taskId='+taskId+"&id="+id,
        modal: true
    });
}

function saveTc(){
	$.ajax({
		  url: "/costTaskCoordinate/save",
		  dataType: "json",
		  type:"post",
		  async: false,
		  data: $("#tcForm").serialize(),
		  success: function(result){
			  if(result.status==200){
				  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  $('#taskCoordinateDialog').dialog('close');
				  $('#coordinationMatters').datagrid('reload');
			  }else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
	});
}

//删除
function deleteTc() {
    var selections = $('#coordinationMatters').datagrid('getSelections');
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
    	$.post("/costTaskCoordinate/del", {"ids":ids},function(result){
    		if(result.status==200){
			    //更新treegrid数据
    		    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			    $("#coordinationMatters").datagrid('reload');
			}else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			}
    	}, "json");
    });
}