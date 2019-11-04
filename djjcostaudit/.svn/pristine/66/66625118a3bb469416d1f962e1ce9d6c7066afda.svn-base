<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>机构列表</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
	<style type="text/css">
		.panel-title {
		    padding-left:5px;
		    color:#283747;
		    font-size: 12px;
		    height: 25px;
		    line-height: 25px;
		}
		.panel-header {
		    border-color: #ced2d6;
		    background:#dde3e8;
		}
	</style>
</head>
<body class="easyui-layout">
<div id="loading-wrapper">
    <div id="loader">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
        <div class="line4"></div>
        <div class="line5"></div>
        <div class="line6"></div>
    </div>
</div>
<div data-options="region:'center',border:0" class="wrap" style="padding:10px;">
	<div class="easyui-layout" id="cc" data-options="fit:true">
		<!-- 左边 -->
		<div data-options="region:'west',collapsed:false,hideCollapsedContent:true,split:true,title:'部门机构'" id="panelWest" style="width:200px;">
		    <div class="">
		        <ul style="padding: 3px;" id="permissionTree" class="ztree"></ul>
		    </div>
		</div>
		<!-- 右边 -->
		<div data-options="region:'center',border:true" class="wrap_center" style="padding:10px;" >
			<div id="dataList">
				<div class="row">
					<form action="" id="searchForm"  onsubmit="return false;">
					    <div class="filter">
					        <div class="form-inline">
					            <div class="form-group">
					                <label for="">机构名称：</label>
					                <input name="name" id="name" type="text"  value="" placeholder="请输入关键字" class="form-control input-sm">
					            </div>
					           
					            <div class="form-group">
					                <input type="button" name="search" value="查询" class="btn btn-primary" onclick="searchParam()">&nbsp;
					                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="">&nbsp;
					            </div>
					        </div>
					    </div>
					    <a href="javascript:void(0);" class="switchBtn"></a>
					</form>
				</div>
			
				<div class="row">
					<div id="tit1" >
						<div class="form-inline">
							<div class="form-group">
								<shiro:hasPermission name="org:add">
							    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addMenu()">新增</a>
							    </shiro:hasPermission>
							    <shiro:hasPermission name="org:delete">
							    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delOrg()">删除</a>
						 		</shiro:hasPermission>
						 	</div>
				       </div>
					</div>
				    <div class="table">
				        <table id="list" style="width: 100%"></table>
				    </div>
				</div>
			</div>
			<!-- 右边详情页面 -->
			<div id="details" class="row">
				<div class="row">
					<form id="form">
				        <table class="table_edit"  cellspacing="0" cellpadding="0" >
				            <tbody>
				            	<tr>
				            		<td style="background-color: #dde3e8;border: 1px solid #ced2d6;" colspan="4">
				            		<span class="glyphicon glyphicon-tasks"></span> 部门机构信息
				            		</td>
				            	</tr>
				                <tr class="first">
				                   <td colspan="4">
				                          <div class="fr" style="float: left;">
				                              <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addMenu()">添加子菜单</a>
				                          </div>    
				                          <div class="fr" style="float: right;">
				                          	  <a href="javascript:void(0);" id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="false" >修改</a>
				                          </div>
				                   </td>
				                </tr>
				                <tr>
				                 	<td style="width: 15%;" class="bgcolor" ><strong>&nbsp;</strong>父级菜单：</td>
				                    <td style="width: 40%;">
				                        <input type="text"   id="pidName" name="pidName"  value="${pidName}" readonly="readonly" style="width:100%;">
				                        <input type="hidden" id="fatherMenu" name="pid"  value="${pid}" >
				                    	<input type="hidden" id="currentId" name="id"  value="${id}" >
				                    </td>
				                    <td class="bgcolor" style="width: 10%;"><strong>*</strong>排序号：</td>
				                    <td style="width: 15%;">
				                        <input type="text" onkeyup ="value=value.replace(/[^\d]/g,'')" name="num" value="${num}" placeholder="请输入排序号" style="width:100%;">
				                    </td>
				                </tr>
				                <tr>
				                    <td class="bgcolor"><strong>*</strong>名称：</td>
				                    <td colspan="3">
				                        <input type="text" name="name" value="${name}" placeholder="请输入名称" style="width:100%;">
				                    </td>
				                </tr>
				                <tr>
				                    <td class="bgcolor"><strong>&nbsp;</strong>描述：</td>
				                    <td colspan="3">
				                        <textarea name="description" cols="3" rows="3">${description}</textarea>
				                    </td>
				                </tr>
				                
				                <tr style="height: 52px">
				                    <td colspan="4" style="text-align: center">
				                        <div id="btn-group">
				                            <input type="button" id="save" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
				                            <input type="button" id="cancel" class="btn btn-success" onclick="returnList()" value="返回">
				                        </div>
				                    </td>
				                </tr>
				            </tbody>
				        </table>
					</form>
				</div>
				<div id="user_data" class="row">
				    <div>
				    	<table class="table_edit" style="margin-top: 2px;">
				    		<tr>
					    		<td style="background-color: #dde3e8;border: 1px solid #ced2d6;text-align: left;">
					    			<span class="glyphicon glyphicon-user"></span> 部门机构下的用户信息
					    		</td>
				    		</tr>
				    	</table>
				        <table id="user" style="width: 100%"></table>
				    </div>
				</div>
			</div>
		</div>
	</div>
</div>
<%--<script src="${pageContext.request.contextPath}/res/js/sys/org/orgList.js"></script>
--%><script type="text/javascript">
	/* 搜索条件的展开与隐藏  */
	var bool=false;
	$(".switchBtn").on('click',function () {
	    $(".filter").toggle();
	    if(!bool){
	        $(this).addClass("active");
	        bool=true;
	        $('#list').datagrid('resize', {
	            height: $(window).height()- 45
	        });
	    }else{
	        $(this).removeClass("active");
	        bool=false;
	        $('#list').datagrid('resize', {
	            height: tableHeight()
	        });
	    }
	})
	/* 搜索条件的展开与隐藏     */
	
	var permissionTree=$('#permissionTree');
	
	
	$(function(){
		$("#details").hide();
		permissionTree.tree({
			url:'/sysOrg/orgTree', 
			animate:true ,
			cascadeCheck : true ,
			checkbox:false,
			lines:"true",  
			dnd: false,
			onContextMenu: function(e,node){
				//禁止浏览器的菜单打开
				e.preventDefault();
				$(this).tree('select',node.target);
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
					
			},
			onClick:function(note){
				if(permissionTree.tree('isLeaf',note.target)){ 
					$("#dataList").hide();
					$("#details").show();
					loadForm(note.id); //加载表单的数据
					loadUser(note.id);  
					//保存按钮的事件
					$("#save").off().click(function(){
						ajaxUpdate();  
					});
				}else{
					loadChildren(note.id);  //加载右边子列表
				}
			},
			onLoadSuccess:function(node, data){  //初始化显示
				var rootNode = permissionTree.tree('getRoot');
			    //选中根节点
				permissionTree.tree('select',rootNode.target);
			    //根节点收缩
				//$("#permissionTree").tree('collapseAll',rootNode.target);
				//加载右边子列表
				loadChildren(rootNode.id);  
				//loadSelect(data);  //加载下拉选项
			}
		});
	})
    
	/* 加载表单  */
	function loadForm(id){
		if(id){
			var url = "/sysOrg/orgDetails?id="+id;
			$("#form").form("load",url);
		}else{  
			//请空表单的数据
			$("#form").form("clear");
		}
	}	
	
	/* 加载右边表格数据  */
	function loadChildren(parentId){
		$("#details").hide();
		$("#dataList").show();
		$("#btn-group").hide();
		list(parentId);
	}
	/* 加载右边部门下的用户表格数据  */
	function loadUser(parentId){
		$("#btn-group").hide();
		$("#user_data").show();
		user(parentId);
	}
	//ajax添加
	function ajaxSave(){
		$.ajax({
			  url: "/sysOrg/save",
			  type:"post",
			  dataType: "json",
			  async: false,
			  data: $("#form").serialize(),
			  success: function(data){
				  if(data.status == 200){
		    		  parent.window.showMessager("温馨提示", "<b>添加成功!</b>", 5000, "slide");
		    		  permissionTree.tree('reload'); 
	     		  }else{
	 	    	  	  $.messager.alert("温馨提示","添加异常,请联系管理员", "error");
	 	    	  }
			  }
		});
	}
	//ajax更新
	function ajaxUpdate(){
		$.ajax({
			  url: "/sysOrg/upd",
			  type:"post",
			  dataType: "json",
			  async: false,
			  data: $("#form").serialize(),
			  success: function(data){
			     if(data.status == 200){
		    		 parent.window.showMessager("温馨提示", "<b>修改成功!</b>", 5000, "slide");
		    		 permissionTree.tree('reload'); 
			     }else{
	 	    	  	 $.messager.alert("温馨提示","修改异常,请联系管理员", "error");
	 	    	 }
			  }
		});
	}
	//返回到列表
	function returnList(){
		var node = permissionTree.tree('getSelected');
		var parentNode = permissionTree.tree('getParent',node.target);
		if(parentNode!=null){
			//permissionTree.tree('collapse',parentNode.target);
			permissionTree.tree('select',parentNode.target);
			loadChildren(parentNode.id);
		}else{
			permissionTree.tree('collapse',node.target);
			permissionTree.tree('select',node.target);
			loadChildren(node.id);
		}
	}
	
	//添加菜单功能
	function addMenu(){
		$("#dataList").hide();
		$("#details").show();
		$("#btn-group").show();
		loadForm("");
		var node = permissionTree.tree('getSelected');
		$("#fatherMenu").val(node.id);
		$("#pidName").val(node.text);
		//select默认选中可用
		$("#status").val("可用");
		//改变保存按扭的onclick事件的方法
		$("#save").off().click(function(){
			ajaxSave();
		});
	}
	//删除部门机构
	function delOrg(){
		var ids="";
    	var rows = datagrid.datagrid("getSelections");
    	if(rows.length == 0){
    		$.messager.alert("温馨提示","请选择要删除的数据！", "error");
    		return;
    	}
    	$.messager.confirm('温馨提示','确定删除吗？',function(r){
    		if(r){
    			for(r in rows){
            		ids+=rows[r].id+",";
            	}
            	ids = ids.substring(0,ids.length-1);
            	$.post("/sysOrg/del", {"ids":ids},function(data){
            		if(data.status == 200){
    	    			parent.window.showMessager("温馨提示", "<b>删除成功!</b>", 5000, "slide");
    	    			datagrid.datagrid("reload");
    	    			permissionTree.tree('reload'); 
        			}else{
    	    	    	$.messager.alert("温馨提示","删除异常,请联系管理员", "error");
    	    	    }
            	}, "json");
    		}
    	});	
	}
	/* 删除机构下面的用户  */
	function delUserBatch(){
		var ids="";
    	var rows = datagrid_user.datagrid("getSelections");
    	if(rows.length == 0){
    		$.messager.alert("温馨提示","请选择要删除的数据！", "error");
    		return;
    	}
    	$.messager.confirm('温馨提示','确定删除吗？',function(r){
    		if(r){
    			for(r in rows){
            		ids+=rows[r].id+",";
            	}
            	ids = ids.substring(0,ids.length-1);
            	$.post("/sysUser/del", {"ids":ids},function(data){
            		if(data.status == 200){
    	    			parent.window.showMessager("温馨提示", "<b>删除成功!</b>", 5000, "slide");
    	    			datagrid_user.datagrid("reload");
        			}else{
    	    	    	$.messager.alert("温馨提示","删除异常,请联系管理员", "error");
    	    	    }
            	}, "json");
    		}
    	});
	}
	
	$("#edit").click(function(){
	  	$("#btn-group").toggle();
    });
	
	//查询
    function searchParam(){
    	var name = $("input[name='name']").val();
	    $('#list').datagrid({
			queryParams: {
				name:name
			}
		}); 
	}
    
    
</script>
<script type="text/javascript">

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
                    var a="";
                    <shiro:hasPermission name="org:update">
                        a= "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='upd(\"" + row.id + "\");'>编辑</a>&nbsp;&nbsp;"; 
                    </shiro:hasPermission>
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


</script>
</body>
</html>