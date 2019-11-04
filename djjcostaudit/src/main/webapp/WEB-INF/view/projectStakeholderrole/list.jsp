<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
  <head>
  <title>项目干系人</title>
   <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>

  </head>
  
  <body>
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
  <div class="row">
    <table id="stakeholders" style="width: 100%;">
    </table>
    <div id="tit1">
	    <shiro:hasPermission name="projectGXR:add">
	        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="top.addTabGrid('项目干系人添加', '/forward_projectStakeholderrole_add');">添加</a>
	        </shiro:hasPermission>
	    <shiro:hasPermission name="projectGXR:delete">
	        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
	    </shiro:hasPermission>
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
    </div>
</div>
<script type="text/javascript">
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
     	         <shiro:hasPermission name="projectGXR:update">
                     formatter:function(value,row,index){  
                   	    var a = '<a class="btn btn-outline-primary" href="javascript:void(0);"  onclick="editInfo(\''+ row.id +'\')">修改</a>'; 
                           return a;  
                     }  
                     </shiro:hasPermission>
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
</script>
<%-- <script src="${pageContext.request.contextPath}/res/js/cost/projectStakeholderrole/list.js"></script> --%>
  </body>
  </html>