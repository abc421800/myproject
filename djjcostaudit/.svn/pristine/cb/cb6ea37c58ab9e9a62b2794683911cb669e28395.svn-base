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
    songshenMaterial(taskId);
    shendingMaterial(taskId);
    wlfile();
    coordinationMatters();
    recordTask(taskId);
});
function recordTask(taskId) {
    $('#record_task').datagrid({
        url: '/costTaskOpinion/list?taskId='+taskId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: "auto",
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: false, //分页控件
        pageSize: 10,
        singleSelect: false,
        columns: [
            [{
                field: 'status',
                title: '当前状态',
                width: 100,
                align: 'left',
                sortable: false,
               
            }, {
                field: 'opinion',
                title: '审核意见',
                width: 300,
                align: 'left',
                sortable: false,
                formatter: function(value, rowData, index) {
                    return value;
                }
            }, {
                field: 'creater',
                title: '操作人',
                width: 80,
                align: 'center',
                sortable: false
                
            }, {
                field: 'createTimeStr',
                title: '审核时间',
                align: 'center',
                width: 100,
                sortable: false,

            }
            ]
        ],
    
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#record_task').datagrid('loadData', {total: 1, rows: [{status: "未找到相关信息！"}]});
            }
        }
    });
    $(window).resize(function () {
        $('#sjTask').datagrid('resize', {
            height: 500
        });
    });
}
function songshenMaterial(id) {
    $("#songshen").treegrid({
    	url: "/costAttachment/list?dataType=songshen&typeId="+id,
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
            }, {
                field: 'opt',
                title: '操作',
                halign: 'center',
                align: 'center',
                width: 130,
                formatter: function (value, rowData, rowIndex) {
                	if(rowData.isLeaf == "Y" ){
                		var a='<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach('+"'"+rowData.id+"'"+')">预览</a>&nbsp;&nbsp;';
                		var b='<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach('+"'"+rowData.id+"'"+')">下载</a>&nbsp;&nbsp;';
                        //var c='<a href="javascript:void(0)" class="btn btn-outline-danger" onclick="delFileById('+"'"+rowData.id+"'"+')">删除</a>&nbsp;&nbsp;';
                        return a+b;
                	}
                }
            }]],
        onBeforeCheck: function (rows, data) {
            if (rows == null) {
                return true;
            }
            if (rows.isLink == "false") {
                return false;
            }
        },
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
    	onLoadSuccess: function(row){
			$('#songshen').treegrid('enableDnd', row?row.id:null);
		}		
    });
    $(window).resize(function () {
        $('#songshen').treegrid('resize', {
            height: 500
        });
    });
}
function shendingMaterial(id) {
    $("#shending").treegrid({
    	url: "/costAttachment/list?dataType=shending&typeId="+id,
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
        toolbar: '#tit2',
        columns: [[
            {field: 'ck',   title : '单选框', checkbox: true},
            {
                field: 'name',
                title: '文档标题',
                width: 320,
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
            },  {
                field: 'opt',
                title: '操作',
                halign: 'center',
                align: 'left',
                width: 130,
                formatter: function (value, rowData, rowIndex) {
                	var a="";
                	var b="";
                	var c="";
                	if(rowData.isLeaf == "Y" ){
                		 if(rowData.category == '否'){
                			c='<a href="javascript:void(0)" class="btn btn-outline-info" onclick="ruku('+"'"+rowData.id+"'"+')">入库</a>&nbsp;&nbsp;';
                		 }
                		 a='<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach('+"'"+rowData.id+"'"+')">预览</a>&nbsp;&nbsp;';
                		 b='<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach('+"'"+rowData.id+"'"+')">下载</a>&nbsp;&nbsp;';
                        return c+a+b;
                	}
                }
            }]],
        onBeforeCheck: function (rows, data) {
            if (rows == null) {
                return true;
            }
            if (rows.isLink == "false") {
                return false;
            }
        },
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
        onLoadSuccess: function(row){
			$('#shending').treegrid('enableDnd', row?row.id:null);
		}		
    });
    $(window).resize(function () {
        $('#shending').treegrid('resize', {
            height: 500
        });
    });
}
function wlfile() {
    var bool=false;
    $("#switchBtn1").on('click',function () {
        $(".filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#wlfile').datagrid('resize', {
                height: 500
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#wlfile').datagrid('resize', {
                height: 500
            });
        }
    });
    $('#wlfile').datagrid({
        url: '/costTask/docList?taskId='+taskId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height:  500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
            [
                {
                    field: 'id',
                    title : '单选框',
                    checkbox: true
                },{
                field:'symbol',
                title:'文号',
                width:130,
                align:'center',
                sortable:false,
                formatter: function(value, rowData, index) {
                	if(value!=null){
                        return "<a href=\"javascript:void()\" style=\"color:#549de3;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\" onclick=\"showInfo('"+rowData.id+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
                }
            },{
                field:'name',
                title:'文件标题',
                width:230,
                align:'center',
                sortable:false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            },{
                field: 'comeGoFlag',
                title: '往/来',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'auditPriceFlag',
                title: '是否审价',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'comeGoUnit',
                title: '往来单位',
                width: 140,
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
                field: 'documentTimeStr',
                title: '文件日期',
                align: 'center',
                width: 150,
                sortable: false,

            },  {
                field: 'contractName',
                title: '对应合同',
                align: 'center',
                width: 200,
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            }, {
                field: 'taskName',
                title: '对应审价',
                width: 140,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                        return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }

            }, {
                field: 'number',
                title: '份数',
                width: 100,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index){
                	if(value!=null){
                        return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                	}else{
                		return ""
                	}
                }
            }
            /*{
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
            }*/
            ]
        ]

    });
    $(window).resize(function () {
        $('#wlfile').datagrid('resize', {
            height: 500
        });
    });
}
function coordinationMatters() {
    
    $('#coordinationMatters').datagrid({
        url: '/costTaskCoordinate/list?taskId='+taskId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
            [{
                field: 'id',
                title : '单选框',
                checkbox: true
            },{
                field: 'name',
                title: '协调事项',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'status',
                title: '状态',
                width: 230,
                align: 'center',
                sortable: false
            }, {
                field: 'creater',
                title: '创建人',
                width: 130,
                align: 'center',
                sortable: false

            }, {
                field: 'createTimeStr',
                title: '创建日期',
                width: 100,
                align: 'center',
                sortable: false

            }, {
                field: 'description',
                title: '备注',
                align: 'center',
                width: 150,
                sortable: false

            }
            ]
        ],
        toolbar: "#tit4"


    });
    $(window).resize(function () {
        $('#coordinationMatters').datagrid('resize', {
            height: 500
        });
    });
}
//往来文件详情
function showInfo(zjkClId) {
    var href = "/costDocument/toEdit?editFlag=y&id="+zjkClId;
    var title = "往来文件详情";
    top.addTabGrid(title, href);
}


/**************送审和审定资料******************/
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
		json1.typeId=taskId;
	}else{
		json1.name=projName;
		json1.typeId=taskId;
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
	json1.typeId=taskId;
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
		if("shending"==flag){
			if("主材定价"==auditPriceType || "综合单价"==auditPriceType){
				if(rows[0].name=="新增主材单价审批表" || rows[0].name=="换算（合同外）新增项目综合单价"){
					$.messager.alert("温馨提示","抱歉该文件夹不能修改!", "error");
					return;
				}
			}
		}
		//console.log(rows[0].name);
	    $('#editDialog').dialog({
	        title: '修改文件夹',
	        width: 600,
	        height: "auto",
	        closed: false,
	        cache: false,
	        top:100,
	        href: "/costAttachment/toEdit?flag="+flag+"&id="+rows[0].id,
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
	if("shending"==flag){
		if("主材定价"==auditPriceType || "综合单价"==auditPriceType){
			if(rows[0].name=="新增主材单价审批表" || rows[0].name=="换算（合同外）新增项目综合单价"){
				$.messager.alert("温馨提示","抱歉该文件夹不能删除!", "error");
				return;
			}
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
        			$("#"+flag).treegrid('reload');
        			$("#"+flag).treegrid("clearSelections");
        		  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
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
	if(rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一个文件夹', 'warn');
        return;
    }
	var folderId=rows[0].id;
    var url = "/forward_task_fileinput?folderId="+folderId+"&typeId="+taskId+"&dataType="+flag;
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
//下载主材导入模板
/*function importTemplateZc(){
  	window.location.href = "/costPriceLibrary/importTemplateZc";
}*/
/*//下载主材导入模板
function importTemplateZc(){
    window.location.href = "/Test/importTemplateZc";
}*/

//下载综合导入模板
function importTemplateZh(){
  	window.location.href = "/costPriceLibrary/importTemplateZh";
}
//入库
function ruku(id){
	$.post('/costPriceLibrary/importPriceLibrary?id='+id +'&taskId='+taskId, {"ids":id},function(result){
		if(result.status==200){
		  //更新treegrid数据
		  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
		  $("#shending").treegrid('reload');
		    }else{

			  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
		    }
	}, "json");
}
/**************送审和审定资料******************/


