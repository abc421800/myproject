//参数 documentId
function linkInfo(id) {
    $('#linkInfo').datagrid({
        url: '/costDocument/containProConList?documentId='+id,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        toolbar:'#tit3',
        columns: [
            [ {field: 'ck',   title : '单选框', checkbox: true},
                {
                field: 'projectName',
                title: '包含项目',
                width: 230,
                align: 'left',
                sortable: false,
                formatter: function(value, rowData, index) {
                    if(value!=null){
                    	return "<a href=\"javascript:void(0)\" title="+value+" style=\"color:#549de3;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display:inline-block;max-width:100%;vertical-align: middle;\" onclick=\"showInfoPro('"+rowData.projectId+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }

                }
            }, {
                field: 'contractName',
                title: '包含合同',
                width: 230,
                align: 'left',
                sortable: false,
                formatter: function(value, rowData, index) {
                    if(value!=null){
                    	return "<a href=\"javascript:void(0)\" title="+value+" style=\"color:#549de3;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display:inline-block;max-width:100%;vertical-align: middle;\" onclick=\"showInfoCon('"+rowData.contractId+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }

                }
            }
            , {
                field: 'manager',
                title: '管理',
                width: 100,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, rowIndex) {
                    if (rowData.id) {
                        return "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='taskDialog(\"" + rowData.id + "\")'>添加任务</a>";
                    }
                }
            }
            ]
        ]

    });
    $(window).resize(function () {
        $('#linkInfo').datagrid('resize', {
            height: 500
        });
    });
}
//关联项目中 项目名称点击跳转项目详情
function showInfoPro(zjkClId) {
    var href = "/costProject/editProject?editFlag=y&projId="+zjkClId;
    var title = "项目修改";
    top.addTabGrid(title, href);
}
function showInfoCon(zjkClId) {
    var href = "/costContract/toEdit?editFlag=y&id="+zjkClId;
    var title = "合同修改";
    top.addTabGrid(title, href);
}
//关联信息添加
function addInfoTabs() {
    //top.addTabGrid('关联信息添加', 'page/wlfile/linkInfo_add.html');
    $('#info_add').dialog({
        title: '关联信息添加',
        width: 600,
        height: 200,
        closed: false,
        cache: false,
        top:100,
        href:'/forward_document_relation',
        //content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/forward_document_relation'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
function editInfoTabs() {
	var rows = $('#linkInfo').datagrid('getSelections');
    if (rows == undefined || rows == null || rows.length == 0) {
        $.messager.alert('提示信息', '请选择一条记录!', 'warn');
        return;
    }
    if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一条记录!', 'warn');
        return;
    }
    if(rows[0].taskId){
    	$.messager.alert('提示信息', '请选择未生成审价任务的数据 !', 'warn');
        return;
    }
    $('#info_add').dialog({
        title: '关联信息修改',
        width: 600,
        height: 200,
        closed: false,
        cache: false,
        top:100,
        href:'/costDocument/toEditProCon?id='+rows[0].id,
        //content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/forward_document_relation'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
//关联项目
function agreeToProject() {
    $('#project').dialog({
        title: '关联项目',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:50,
        content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/forward_document_containProject'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
//关联合同
function agreeToContract() {
	var containProjectId=$("#containProjectId").val();
	if(containProjectId=="" || containProjectId==null){
		 $.messager.alert({title:'温馨提示', msg:'请先选择关联项目 ！', icon: 'error', top:100});
	        return;
	}
    $('#contract').dialog({
        title: '关联合同',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top:50,
        content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/forward_document_containContract?projectId='+containProjectId+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}
//保存关联的项目和合同
function saveBtn(){
	var containProjectId=$("#containProjectId").val();
    var containContractId=$('#containContractId').val();
    var dpctRelationId=$('#dpctRelationId').val();
    if(containProjectId==""){
        $.messager.alert({title:'温馨提示', msg:'请选择关联项目 ！', icon: 'error', top:100});
        return;
    }
    var json1={};
    if(containContractId==""){
    	json1.documentId=documentId;
    	json1.projectId=containProjectId;
    }else{
    	json1.documentId=documentId;
    	json1.projectId=containProjectId;
    	json1.contractId=containContractId
    }
    if(dpctRelationId!=""){
    	json1.id=dpctRelationId
    }
	$.ajax({
		  url: "/costDocument/containProCon",
		  dataType: "json",
		  type:"post",
		  async: false,
		  data: json1,
		  success: function(result){
			  if(result.status==200){
				  $('#info_add').dialog('close');
				  $("#linkInfo").datagrid('reload');
			  	  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			  }else{
				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
	});
}
//删除关联
function delProCon(id){
	var rows = $("#linkInfo").datagrid("getSelections");
	if(rows.length == 0){
		$.messager.alert("温馨提示","请选择要删除的数据！", "error");
		return;
	}
	$.messager.confirm('温馨提示','确定删除吗？',function(r){
		if(r){
			var ids = "";
			for(var i = 0;i<rows.length;i++){
        		ids+=rows[i].id+",";
        	}
        	ids = ids.substring(0,ids.length-1);
        	$.post("/costDocument/delProCon", {"ids":ids},function(result){
        		if(result.status==200){
  				  //更新treegrid数据
        		  $("#linkInfo").datagrid('reload');
        		  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
  			    }else{
  				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
  			    }
        	}, "json");
		}
	});
	
}


	
	function closePro(){
		$("#project").dialog("close");
		$("#project").dialog("destroy");
	    $('#all').after("<div id='project'></div>");
	}
	function closeCon(){
		$("#contract").dialog("close");
		$("#contract").dialog("destroy");
		$('#all').after("<div id='contract'></div>");
	}