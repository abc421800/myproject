//参数 documentId
function fujian(id) {
    $("#fujian").treegrid({
        url: "/costAttachment/list?typeId="+id,
        height: 500,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        lines: true,//下面三个是树属性
        animate: true,
        rownumbers: false,// 行号
        idField: 'id',
        parentField: "_parentId",
        treeField: 'name',
        singleSelect: false,
        toolbar: '#tit1',
        columns: [[
            {field: 'ck',   title : '单选框', checkbox: true},
            {
                field: 'name',
                title: '文档标题',
                width: 320,
                halign: 'center',
                formatter: function (value, rowData, index) {
                	/*if(value!=null && value.length>15){
                        return "<span title="+value+" >"+value.substring(0,15)+"...</span>";
                	}
                	else if(value!=null){
                		return value;
                	}else{
                		return ""
                	}*/
                	return "<span title="+value+">"+value+"</span>";
                }
            }, {
                field: 'suffix',
                title: '类型',
                halign: 'center',
                align: 'center',
                width: 100
            }, {
                field: 'size',
                title: '大小',
                halign: 'center',
                align: 'center',
                width: 100,
            },{
                field: 'creater',
                title: '发布人',
                halign: 'center',
                align: 'center',
                width: 100,
            },  {
                field: 'createrTime',
                title: '发布时间',
                halign: 'center',
                align: 'center',
                width: 100,
                formatter: formatterdate,
            } , {
                field: 'opt',
                title: '操作',
                halign: 'center',
                align: 'center',
                width: 120,
                formatter: function (value, rowData, rowIndex) {
                	if(rowData.isLeaf == "Y" ){
                		var a='<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach('+"'"+rowData.id+"'"+')">预览</a>&nbsp;&nbsp;';
                		var b='<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach('+"'"+rowData.id+"'"+')">下载</a>&nbsp;&nbsp;';
                       // var c='<a href="javascript:void(0)" class="btn btn-outline-danger" onclick="delFileById('+"'"+rowData.id+"'"+')">删除</a>&nbsp;&nbsp;';
                        return a+b;
                	}
                }
            }]],
            onDrop:function(targetRow,sourceRow,point){
    			//alert("原来行名称："+sourceRow.name);
    			//alert("原来行名称："+targetRow.name);
    			$.ajax({
    				type:'post',
    				url:'/costAttachment/findDndTree' , 
    				data:{
    					targetId:targetRow.id , 
    					sourceId:sourceRow.id , 
    					point:point
    				} , 
    				dataType:'json' , 
    				cache:false , 
    				success:function(data){
    					if(data.status == 200){
    						parent.window.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
    				    }else{
    				    	$.messager.alert("温馨提示","删除异常,请联系管理员", "error");
    				    }
    				}
    			});
    		},     
        onBeforeCheck: function (rows, data) {
            if (rows == null) {
                return true;
            }
            if (rows.isLink == "false") {
                return false;
            }
        },
	    onLoadSuccess: function(row){
			$('#fujian').treegrid('enableDnd', row?row.id:null);
		}	
    });
    $(window).resize(function () {
        $('#fujian').treegrid('resize', {
            height: 500
        });
    });
}
//添加文件夹名称
function getDialog() {
    $('#dlg').dialog({
        title: '添加文件夹',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top:100,
        href: '/forward_document_dialog',
        modal: true
    });
}
function saveFileName(){
	var projName=$("#projName").val();
	var rows = $("#fujian").treegrid("getSelections");
	var json1={};
	if(rows.length== 0){
		json1.name=projName;
		json1.typeId=documentId;
	}else{
		json1.name=projName;
		json1.typeId=documentId;
		json1.pid=rows[0].id;
	}
	if(projName==""){
		$.messager.alert("温馨提示","请输入文件夹名称!", "error");
		return;
	}
	//插入附件表
	$.ajax({
		  url: "/costAttachment/save",
		  dataType: "json",
		  type:"post",
		  async: false,
		  data: json1,
		  success: function(result){
			  if(result.status==200){
				  //更新treegrid数据
				  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			      $('#dlg').dialog('close');
			      $("#fujian").treegrid('reload');
			  }else{
				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
	});
}
//修改文件名
function updateFileName(){
	var projName=$("#fileName").val();
	if(projName==""){
		$.messager.alert("温馨提示","请输入文件夹名称!", "error");
		return;
	}
	var rows = $("#fujian").treegrid("getSelections");
	var json1={};
	json1.name=projName;
	json1.typeId=documentId;
	json1.id=rows[0].id;
	//插入附件表
	$.ajax({
		  url: "/costAttachment/update",
		  dataType: "json",
		  type:"post",
		  async: false,
		  data: json1,
		  success: function(result){
			  if(result.status==200){
				  //更新treegrid数据
				  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			      $('#editDialog').dialog('close');
			      $("#fujian").treegrid('reload');
			  }else{
				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
	});
}
//修改文件夹名称
function getEditDialog() {
	var rows = $("#fujian").treegrid("getSelections");
	if(rows.length==1 && rows[0].suffix=="文件夹"){
		//console.log(rows[0].name);
	    $('#editDialog').dialog({
	        title: '修改文件夹',
	        width: 600,
	        height: "auto",
	        closed: false,
	        cache: false,
	        top:100,
	        href: "/costAttachment/toEdit?id="+rows[0].id,
	        modal: true
	    });
	}else{
		$.messager.alert("温馨提示","请选中一个文件夹!", "error");
		return;
	}
}
//删除附件
function delFile(){
	var rows = $("#fujian").treegrid("getSelections");
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
        	$.post("/costAttachment/del", {"ids":ids},function(result){
        		if(result.status==200){
  				  //更新treegrid数据
        		  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
  			      $("#fujian").treegrid('reload');
  			      $('#fujian').treegrid('clearSelections');
  			    }else{
  				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
  			    }
        	}, "json");
		}
	});
}
function delFileById(id){
	$.messager.confirm('温馨提示','确定删除吗？',function(r){
		if(r){
        	$.post("/costAttachment/del", {"ids":id},function(result){
        		if(result.status==200){
  				  //更新treegrid数据
        		  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
  			      $("#fujian").treegrid('reload');
  			    }else{
  				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
  			    }
        	}, "json");
		}
	});
}

function uploadFile(){
	var rows = $("#fujian").treegrid("getSelections");
	if(rows.length == 0 || rows[0].isLeaf == 'Y'){
		$.messager.alert("温馨提示","请选择文件夹！", "error");
		return;
	}
	if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一个文件夹', 'warn');
        return;
    }
	var folderId=rows[0].id;
    var url = "/forward_document_fileinput?folderId="+folderId+"&typeId="+documentId;
    $("#dialog").dialog({
        title: "选择文件",
        minimizable:false,
        content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+url+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        width:820,
        height:480,
        iconCls: 'icon-info',
        modal: true,
        buttons:[{
            text:'关闭',
            iconCls:'icon-close',
            handler:function(data){
            	$("#fujian").treegrid('reload');
                $("#dialog").dialog("close");
            }
        }]
    });
}
//附件下载
function downloadAttach(id){
	window.location.href='/costAttachment/downLoadAttach.do?id='+id + '&timestamp=' + new Date().getTime();
}
/* 预览  */
function previewAttach(id){
	top.addTabGrid('预览','/costAttachment/preview?id='+id);
}

