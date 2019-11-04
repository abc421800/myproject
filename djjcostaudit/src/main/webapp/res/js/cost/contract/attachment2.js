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
    fujian(contractId);
    sjTask(contractId);
});

//参数 contractId
//合同附件
function fujian(id) {
    $("#bcxyfujian").treegrid({
        url: "/costAttachment/list?&typeId="+id,
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
                width: 290,
                halign: 'center',
                formatter: function (value, rowData, index) {
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
                        return a+b;
                	}
                }
            }]],
            onDrop:function(targetRow,sourceRow,point){
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
			$('#bcxyfujian').treegrid('enableDnd', row?row.id:null);
		}	
    });
    $(window).resize(function () {
        $('#bcxyfujian').treegrid('resize', {
            height: 500
        });
    });
}

function sjTask(contractId) {
	//var a="b6d4d6e011594ad98361ccfb2c80d259";
    $('#contract_task').datagrid({
        url: '/costTask/list?contractCongId='+contractId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: "auto",
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        columns: [
            [{
                field: 'id',
                title : '复选框',
                checkbox: true
            },{
                field: 'code',
                title: '审价编号',
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
               	 if(value!=null){
               		 return "<a href=\"javascript:void(0)\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                		"max-width: 100%;vertical-align: middle;\" onclick=\"editInfo('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
               }
            }, {
                field: 'name',
                title: '审价任务名称',
                width: 230,
                align: 'left',
                sortable: false,
                formatter: function(value, rowData, index) {
                    return value;
                }
            }, {
                field: 'auditPriceType',
                title: '审价类型',
                width: 100,
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
                field: 'personLiable',
                title: '审价任务负责人',
                align: 'center',
                width: 80,
                sortable: false,

            },{
                field: 'auditPriceUnit',
                title: '审价单位',
                align: 'left',
                halign:'center',
                width: 100,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }, {
                field: 'status',
                title: '当前状态',
                align: 'center',
                width: 100,
                sortable: false

            }
            ]
        ],
        toolbar: "#tit7",
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#contract_task').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        }
    });
    $(window).resize(function () {
        $('#sjTask').datagrid('resize', {
            height: 500
        });
    });
}

function editInfo(zjkClId) {
	 var href = "/costTask/toEdit?editFlag=y&id="+zjkClId;
   var title = "审价任务修改";
   top.addTabGrid(title, href);
}

//添加文件夹名称
function getDialog(flag) {
    $('#dlg').dialog({
        title: '添加文件夹',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top:100,
        href: '/forward_task_dialog?flag='+flag,
        modal: true
    });
}
function saveFileName(flag){
	var projName=$("#projName").val();
	var rows = $("#"+flag).treegrid("getSelections");
	var json1={};
	if(rows.length== 0){
		json1.name=projName;
		json1.typeId=contractId;
	}else{
		json1.name=projName;
		json1.typeId=contractId;
		json1.pid=rows[0].id;
	}
		json1.dataType=flag;
		
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
			      $("#"+flag).treegrid('reload');
			  }else{
				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
	});
}
//修改文件名
function updateFileName(flag){
	var projName=$("#fileName").val();
	if(projName==""){
		$.messager.alert("温馨提示","请输入文件夹名称!", "error");
		return;
	}
	var rows =  $("#"+flag).treegrid("getSelections");
	var json1={};
	json1.name=projName;
	json1.typeId=contractId;
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
			      $("#"+flag).treegrid('reload');
			  }else{
				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
	});
}
//修改文件夹名称
function getEditDialog(flag) {
	var rows = $("#"+flag).treegrid("getSelections");
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
function delFile(flag){
	var rows = $("#"+flag).treegrid("getSelections");
	if(rows.length == 0){
		$.messager.alert("温馨提示","请选择要删除的数据！", "error");
		return;
	}
	if("tendering"==flag){
			if(rows[0].name=="审定资料" || rows[0].name=="送审资料"){
				$.messager.alert("温馨提示","抱歉该文件夹不能删除!", "error");
				return;
		}
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
        		  $("#"+flag).treegrid('reload');
        		  $("#"+flag).treegrid("clearSelections");
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
        		  $("#"+flag).treegrid('reload');
  			    }else{
  				  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
  			    }
        	}, "json");
		}
	});
}

function uploadFile(flag){
	var rows = $("#"+flag).treegrid("getSelections");
	if(rows.length == 0 || rows[0].isLeaf == 'Y'){
		$.messager.alert("温馨提示","请选择文件夹！", "error");
		return;
	}
	if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一个文件夹', 'warn');
        return;
    }
	var folderId=rows[0].id;
    var url = "/forward_document_fileinput?folderId="+folderId+"&typeId="+contractId+"&dataType="+flag;
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
            	$("#"+flag).treegrid('reload');
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

