<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
  <head>
<style>
        .tree-aru {
            border: 1px solid #ddd;
            width: 200px;
            float: left;
            border-radius: 4px;
            min-height: 200px;
            overflow-y: auto;
            margin-right: 12px;
            background: #fff;
        }
        .tree_new{
            padding:5px;
        }
        .tit{
            color:#777;
            font-size: 12px;
            font-weight: bold;
            padding-left: 10px;
            background-color: #F2F2F2;
            background: -webkit-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
            background: -moz-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
            background: -o-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
            background: linear-gradient(to bottom,#ffffff 0,#F2F2F2 100%);
            background-repeat: repeat-x;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,endColorstr=#F2F2F2,GradientType=0);
            height: 27px;
            line-height: 27px;
            margin-top:0;
            margin-bottom:0;
            border-bottom: 1px solid #ddd;
        }
        .tree li{
            padding:2px 0
        }
    </style>

    
    <title>项目分类</title>
   <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>

  </head>
  
  <body class="clearfix">
    <div class="tree-aru">
        <h4 class="tit">项目分类</h4>
        <ul id="permissionTree" class="tree_new easyui-tree" data-options="lines:true,animate:true"></ul>
    </div>
     <div class="embed-responsive iframeHeight">
        <div class="table" id="functionDiv">
            <table id="cljgList" style="width: 100%;">
            </table>
        </div>
        <div class="slider-db" style="margin-top: 10px;">
			<div class="row">
				<div class="btn-toolbar pull-left clearfix margin-bottom">
					<div class="btn-group">
						<button type="button" onclick="addMenu();" class="btn btn-primary btn-lg">添加菜单</button>
					</div>
				</div>
			</div>
			
			<div class="row">
				<form method="post" id="form">
					<div class="tabox table-responsive float-table">
						<input type="hidden" id="currentId" name="id" value="${id}" />
						<table class="table table-bordered table-condensed">
							<tr>
								<th class="text-right" width="25%">上级菜单</th>
								<td class="text-left" width="75%">
									<select id="fatherMenu" name="pid" class="input-sm form-control easyui-validatebox" validType="notChangeToChild"></select>
								</td>
							</tr>
							<tr>
								<th class="text-right">名称</th>
								<td class="text-left">
								<span>
									<input name="name" value="${name }" type="text" class="input-sm form-control" />
								</span></td>
							</tr>
							<tr>
								<th class="text-right">排序</th>
								<td class="text-left">
									<span>
										<input name="num" value="${num}" type="text" class="input-sm form-control" />
									</span>
								</td>
							</tr>
							<tr>
								<th class="text-right">描述</th>
								<td class="text-left">
									<span>
										<textarea name="description"  rows="5" cols="70" class="form-control">${description}</textarea>
									</span>
								</td>
							</tr>
						</table>
					</div>
					<shiro:hasPermission name="projectType:update">
						<div class="row">
							<div class="col-md-12 text-center">
								<input type="button" class="btn btn-primary btn-lg" id="save" value="保存"/>
								<input type="button" class="btn btn-success btn-lg" onClick="returnList();" value="返回" />
							</div>
						</div>
					</shiro:hasPermission>
				</form>
			</div>
		</div>
    </div>
  </body>
<script type="text/javascript">
var permissionTree = $('#permissionTree');  
var datagrid=$('#cljgList');
var IsCheckFlag = true;
var dialog = $("#dialog");
$(function(){
    $('.slider-db').hide();
	permissionTree.tree({
		url:'/costProjectType/treeList', 
		animate:true ,
		cascadeCheck : true ,
		checkbox:false,
		lines:"true",  
		dnd: true,
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
				loadForm(note.id); //加载表单的数据
				$("#fatherMenu").attr("disabled","disabled"); //选中下拉
				//保存按钮的事件
				$("#save").off().click(function(){
					ajaxUpdate();  
				});//~
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
			loadSelect(data);  //加载下拉选项
			permissionTree.tree("expandAll");
		}
	});
});	
//加载表单
function loadForm(id){
	$("#functionDiv").hide();
	$('.slider-db').show();
	if(id){
		var url = "/costProjectType/findDetails.do?id="+id;
		$("#form").form("load",url);
	}else{  
		//请空表单的数据
		$("#form").form("clear");
	}
	
}	
//加载子列表
  function loadChildren(parentId){
     $('.slider-db').hide();
	   $("#functionDiv").show();
     datagrid.datagrid({ 
	   title:"工程分类列表",
     height: $(window).height()-34, 
     nowrap: true,
     striped: true,
     border: true, 
     collapsible:false,
     fitColumns:true,
	   pagination:true,
	   pageSize: 10,
	   pageList:[10,20,30],
	   columns:[[   
			 {field:'name',title:'分类名称',width:150,align:'center'},
	         {field:'description',title:'描述',width:150,align:'center'},
	         {field:'num',title:'排序号',width:150,align:'center'},
	         {field:'creater',title:'创建者',width:150,align:'center'},
	         {field:'opt',title:'操作',width:100,align:'center', 
	         <shiro:hasPermission name="projectType:update"> 
                formatter:function(value,row,index){  
              	    var a = '<a class="btn btn-outline-primary" href="javascript:void(0);"  onclick="update(\''+ row.id +'\')">修改</a>'; 
                      return a;  
                }  
                </shiro:hasPermission>
           }
	         ]],
      remoteSort:false,  
      idField:'id', 
      singleSelect:false,//是否单选 
      rownumbers:true,//行号 
      frozenColumns:[[{field:'ck',checkbox:true}]],
      onClickCell: function (rowIndex, field, value) {
      	if("opt"==field){
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
 	    },
      toolbar: [
      <shiro:hasPermission name="projectType:add">
      { 
           text: '添加', 
           iconCls: 'icon-add', 
           handler: function() { 
          	 addMenu();
           } 
       },
       </shiro:hasPermission>
       <shiro:hasPermission name="projectType:delete">
       { 
           text: '删除', 
           iconCls: 'icon-remove', 
           handler: function() { 
          		var ids="";
	            	var rows = datagrid.datagrid("getSelections");
	            	if(rows.length == 0){
	            		$.messager.alert("温馨提示","请选择要删除的数据!", "error");
	            		return;
	            	}
	            	$.messager.confirm('提示','确定删除？',function(r){
	            		if(r){
	            			for(r in rows){
			            		ids+=rows[r].id+",";
			            	}
			            	ids = ids.substring(0,ids.length-1);
			            	$.post("/costProjectType/del", {"ids":ids},function(data){
			            		if(data.status == 200){
			    	    			parent.window.showMessager("温馨提示", "<b>删除成功!</b>", 5000, "slide");
			    	    			datagrid.datagrid("reload");
			        			}else{
			    	    	    	$.messager.alert("温馨提示","删除异常,请联系管理员", "error");
			    	    	    }
			            	}, "json");
	            		}
	            	});
           } 
       }
       </shiro:hasPermission>
       ],
	    onBeforeLoad:function(param){
	    	$.post("/costProjectType/list?parentId="+parentId,param,function(result){
	    		  if(result){
	    			datagrid.datagrid("loadData",result);
	    		 }
	    	}, "json");
	    }
});
}   
//加载下拉选项
	function loadSelect(data){
		$("#fatherMenu").empty();
		for(var i=0;i<data.length;i++){
			var option = "<option value='"+data[i].id+"'>"+data[i].text+"</option>"
			$("#fatherMenu").append(option);
			var children = data[i].children;
			if(children!=undefined){
				loadChildrenSelect(data[i].children,4);
			}
		}
	}
	//递归加载下拉选项
	function loadChildrenSelect(data,spaceCount){
		var space = "";
		for(var i=0;i<spaceCount;i++){
			space += "&nbsp;"
		}
		for(var i=0;i<data.length;i++){
			var option = "<option value='"+data[i].id+"'>"+space+data[i].text+"</option>"
			$("#fatherMenu").append(option);
			var children = data[i].children;
			if(children!=undefined){
				loadChildrenSelect(data[i].children,spaceCount+4);
			}
		}
	}
	//添加菜单功能
	function addMenu(id){
		/*var node = permissionTree.tree('getSelected');
		top.addTabGrid('项目分类添加', '/forward_projectType_projectTypeAdd?pid='+node.id);*/
		$("#fatherMenu").removeAttr("disabled");
		loadForm("");
		var node = permissionTree.tree('getSelected');
		$("#fatherMenu").val(node.id);
		//改变保存按扭的onclick事件的方法
		$("#save").off().click(function(){
			ajaxSave();
		});
		//location.href="/forward_projectType_projectTypeAdd";
		
	}
	//ajax保存
	function ajaxSave(){
		//var f = validate();
		//if(f){
			$.ajax({
				  url: "/costProjectType/save.do",
				  type:"post",
				  dataType: "json",
				  async: false,
				  data: $("#form").serialize(),
				  success: function(data){
					  if(data.status == 200){
			    		  parent.window.showMessager("温馨提示", "<b>添加成功!</b>", 5000, "slide");
			    		  reloadTree();
		     		  }else{
		 	    	  	  $.messager.alert("温馨提示","添加异常,请联系管理员", "error");
		 	    	  }
				  }
			});
		//}
	} 
	
	//ajax更新
	function ajaxUpdate(){
		//var f = validate();
		//if(f){
			$.ajax({
				  url: "/costProjectType/upd.do",
				  type:"post",
				  dataType: "json",
				  async: false,
				  data: $("#form").serialize(),
				  success: function(data){
				     if(data.status == 200){
			    		 parent.window.showMessager("温馨提示", "<b>修改成功!</b>", 5000, "slide");
			    		 reloadTree();
				     }else{
		 	    	  	 $.messager.alert("温馨提示","修改异常,请联系管理员", "error");
		 	    	 }
				  }
			});
		//}	
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
			//permissionTree.tree('collapse',node.target);
			permissionTree.tree('select',node.target);
			loadChildren(node.id);
		}
	}

	//刷新树
	function reloadTree(){  
		permissionTree.tree('reload');  
	}  

	function update(id){
		//加载表单的数据
		loadForm(id); 
		//保存按钮的事件
		$("#save").off().click(function(){
			ajaxUpdate();  
		});//~
	}
</script>
<%-- <script src="${pageContext.request.contextPath}/res/js/cost/projectType/tree.js"></script> --%>
</html>
