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
});
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
        rownumbers: true,// 行号
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
                halign: 'center'
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
                align: 'center',
                width: 120,
                formatter: function (value, rowData, rowIndex) {
                	if(rowData.isLeaf == "Y" ){
                		var a='<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach('+"'"+rowData.id+"'"+')">预览</a>&nbsp;&nbsp;';
                		var b='<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach('+"'"+rowData.id+"'"+')">下载</a>&nbsp;&nbsp;';
                        var c='<a href="javascript:void(0)" class="btn btn-outline-danger" onclick="delFileById('+"'"+rowData.id+"'"+')">删除</a>&nbsp;&nbsp;';
                        return a+b+c;
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
        rownumbers: true,// 行号
        idField: 'id',
        parentField: "_parentId",
        treeField: 'name',
        toolbar: '#tit2',
        singleSelect: false,
        columns: [[
            {field: 'ck',   title : '单选框', checkbox: true},
            {
                field: 'name',
                title: '文档标题',
                width: 290,
                halign: 'center'
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
                align: 'center',
                width: 120,
                formatter: function (value, rowData, rowIndex) {
                	if(rowData.isLeaf == "Y" ){
                		var a='<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach('+"'"+rowData.id+"'"+')">预览</a>&nbsp;&nbsp;';
                		var b='<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach('+"'"+rowData.id+"'"+')">下载</a>&nbsp;&nbsp;';
                        var c='<a href="javascript:void(0)" class="btn btn-outline-danger" onclick="delFileById('+"'"+rowData.id+"'"+')">删除</a>&nbsp;&nbsp;';
                        return a+b+c;
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
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
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
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
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
                		return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
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
    var href = "/costDocument/toEdit?editFlag=n&id="+zjkClId;
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

//删除附件
function delFile(flag){
	var rows = $("#"+flag).treegrid("getSelections");
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
        		  $("#"+flag).treegrid('reload');
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
/**************送审和审定资料******************/


