<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
  <title>合同类型</title>
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
    <table id="contractType" style="width: 100%;">
    </table>
    <div id="tit1">
	    <shiro:hasPermission name="contractType:add">
	        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="add()">添加</a>
	    </shiro:hasPermission>
	    <shiro:hasPermission name="contractType:update">
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
            $('#contractType').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#contractType').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#contractType').datagrid({
        url:'/costContractType/list',
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
        columns: [[
                   {
                       field: 'id',
                       title : '单选框',
                       checkbox: true
                       
                   },{
                       field:'name',
                       title:'合同类型',
                       width:150,
                       align:'center',
                       sortable:false
                       
                   },{
                       field:'num',
                       title:'排序号',
                       width:150,
                       align:'center',
                       sortable:false
                       
                   }, {
                       field: 'description',
                       title: '描述',
                       width: 150,
                       align: 'center',
                       sortable: false

                   }, {
                       field: 'creater',
                       title: '创建人',
                       width: 150,
                       align: 'center',
                       sortable: false
                   }, /*{
                       field: 'createTime',
                       title: '创建时间',
                       align: 'center',
                       width: 150,
                       sortable: false,

                   },*/{
                	   field:'opt',
                	   title:'操作',
                	   width:150,
                	   align:'center',  
                	   <shiro:hasPermission name="contractType:delete">
                       formatter:function(value,row,index){  
                     	    var a = '<a class="btn btn-outline-primary" href="javascript:void(0);"  onclick="editInfo(\''+ row.id +'\')">修改</a>'; 
                            return a;  
                       }  
                       </shiro:hasPermission>
                  }
               ]],
        toolbar: '#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#contractType').datagrid('loadData', {total: 1, rows: [{code: "未找到相关信息！"}]});
            }
        }
        
    });  
    $(window).resize(function () {
        $('#contractType').datagrid('resize', {
            height: tableHeight()
        });
    });

});
function editInfo(zjkClId) {
    var href = "/costContractType/toEdit?id="+zjkClId;
    var title = "合同类型修改";
    top.addTabGrid(title, href);
}
function add() {
    var href = "/costContractType/toEdit?editFlag=y";
    var title = "合同类型添加";
    top.addTabGrid(title, href);
}
function deleteHandler() {
    var selections = $('#contractType').datagrid('getSelections');
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
            url: '/costContractType/del.do',
            type: 'POST',
            data: {'ids': ids},
            traditional: true,
        	success: function(result){
        		  $.messager.progress('close');
				  if(result.status==200){
				  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
				  	$('#contractType').datagrid('reload');
				  }else{
					$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
        });
    });
}
</script>
<%-- <script src="${pageContext.request.contextPath}/res/js/cost/contractType/type.js"></script> --%>
  </body>
  </html>