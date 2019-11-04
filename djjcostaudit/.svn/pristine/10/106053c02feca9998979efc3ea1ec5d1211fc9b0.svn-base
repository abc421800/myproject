<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>考勤管理</title>
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
    <div class="filter">
        <div class="form-inline">
            <div class="form-group">
                <label for="">人员姓名：</label>
                <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">企业名称：</label>
                <input type="text" id="enterpriseName" value="" placeholder="请输入关键字" class="form-control input-sm">
            </div>
            <div class="form-group">
                <label for="">出勤情况：</label>
                <select id="morningOrAfternoon" onchange="searchReg()" class="form-control input-sm"
                        style="width: 100px">
                    <option value="">请选择</option>
                    <option value="上班">上班</option>
                    <option value="年休假">年休假</option>
                    <option value="探亲假">探亲假</option>
                    <option value="事假">事假</option>
                    <option value="生育假">生育假</option>
                    <option value="计生假">计生假</option>
                    <option value="病假">病假</option>
                    <option value="婚假">婚假</option>
                    <option value="丧假">丧假</option>
                    <option value="离岗学习">离岗学习</option>
                    <option value="出差">出差</option>
                    <option value="旷工">旷工</option>
                    <option value="其他">其他</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">考勤时间：</label>
                <input id="startTime" class="Wdate form-control input-sm" type="text"
                       onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})" })">
                <label for="">至</label>
                <input id="endTime" class="Wdate form-control input-sm" type="text"
                       onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})">
            </div>
            <div class="form-group">
                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="searchReg()">&nbsp;
                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="refresh()">&nbsp;
                <shiro:hasPermission name="register:export">
                	<input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportDate()">&nbsp;
                </shiro:hasPermission>
                <shiro:hasPermission name="register:Import">
	                <input type="button" name="reset" value="导入" class="btn btn-warning" onclick="importReg()">&nbsp;
	                <input type="button" name="reset" value="下载导入模版" class="btn btn-info" onclick="importREG();">&nbsp; 
                </shiro:hasPermission>
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="switchBtn"></a>
</div>
<div class="row">
    <table id="recordManager" style="width: 100%;">
    </table>
    <div id="tit1">
        <div class="row">
            <div class="form-group" style="margin-bottom: 0;display: inline-block">
                <select onchange="searchReg()" id="regYear" class="form-control input-sm"
                        style="width: 80px;color:#549de3 ;padding-top:0;padding-bottom:0">
                    <c:forTokens items="${year }" delims="," var="y">
                        <option <c:if test="${y eq currentYear}"> selected="selected"</c:if> value="${y}">${y}年</option>

                    </c:forTokens>
                </select>
            </div>
            <div class="form-group" style="margin-bottom: 0;display: inline-block">
                <select onchange="searchReg()" id="regMonth" class="form-control input-sm"
                        style="width: 80px;color:#549de3 ;padding-top:0;padding-bottom:0">
                    <c:forTokens items="${month}" delims="," var="m">
                        <option <c:if test="${m eq currentMonth}"> selected="selected"</c:if> value="${m}">${m}月
                        </option>
                    </c:forTokens>

                </select>
            </div>
            <shiro:hasPermission name="register:add">
            	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="" plain="false" onclick="getBatchDialog()">批量登记</a>
            </shiro:hasPermission>
            <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
        </div>

    </div>
</div>
<div id="dlg"></div>
<div id="personalDlg" style="overflow-y: hidden"></div>
<div id="importAttendance"></div>
<script type="text/javascript">
	var currentYearMonth= "${currentYear}${currentMonth}";
    var currentYear = "${currentYear}";
    var currentMonth ="${currentMonth}";
    
    //导出
    function exportDate() {
    	var y=$("#regYear").val();
    	var m=$("#regMonth").val();
	   	var selections = $('#recordManager').datagrid('getSelections');
	   	    var ids = "";
	   	    if (selections.length != 0) {
	   	        for(var i = 0;i<selections.length;i++){
	   	        	ids+=selections[i].id+",";
	   	        }
	   	     ids = ids.substring(0,ids.length-1);
	   	    }
    	
        window.location.href = "/workRegister/exportRegister?currentYear=" + y+"&currentMonth="+m+"&ids="+ids;
    }
    //导入
    function importReg() {
        var url = "/forward_register_importAttendance";
        $("#importAttendance").dialog({
            title: "选择文件",
            iconCls: 'icon-info',
            minimizable: false,
            content: "<iframe name=\"fileFrame\" frameborder=\"0\" src=" + url + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>", ${dataInit['sitePath']}
            width: 600,
            height: 500,
            iconCls: 'icon-edit-lx',
            modal: true,
            buttons: [{
                text: '关闭',
                iconCls: 'icon-info',
                handler: function (data) {
                    $("#recordManager").datagrid('reload');
                    $("#importAttendance").dialog("close");
                }
            }]
        });
    }
    $(function () {
    	$.fn.datagrid.defaults.onHeaderContextMenu =null;
        var bool = false;
        $(".switchBtn").on('click', function () {
            $(".filter").toggle();
            if (!bool) {
                $(this).addClass("active");
                $('#recordManager').datagrid('resize', {
                    height: $(window).height() - 40
                });
                bool = true;
            } else {
                $(this).removeClass("active");
                bool = false;
                $('#recordManager').datagrid('resize', {
                    height: tableHeight()
                });
            }
        });
        $('#recordManager').datagrid({
            url: '/workRegister/list',
            loadMsg: '数据加载中,请稍候...',
            nowrap: false,
            rownumbers: true,
            height: tableHeight(),
            fitColumns: false,
            striped: true,
            collapsilble: true,
            pagination: true, //分页控件
            pageSize: 50,
            singleSelect: false,
            queryParams:{
            	regYearMonth:currentYearMonth,
            },
            frozenColumns:[[
                {
                    field: 'id',
                    title : '单选框',
                    checkbox: true
                }, 
                <shiro:hasPermission name="register:add">
                {
                    field: 'manager',
                    title: '管理',
                    width: 80,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(rowData.id==undefined){
                    		return "";
                    	}else{
	                        return "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='getPersonnalDialog(\"" + rowData.id + "\",\"" + rowData.actualStartTimeStr + "\")'>登记</a>";
                    	}
                    }
                }, 
               	</shiro:hasPermission>
                {
                    field: 'name',
                    title: '姓名',
                    width: 120,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(rowData.id==undefined){
                    		return "未找到相关信息！";
                    	}else{
	                        return "<a href='javascript:void(0)' title='"+value+"' style='color:#549de3;padding-left:2px;display: inline-block;' onclick='addPersonTab(\"" + rowData.id + "\")'>"+value+"</a>";
                    	}
                    }
                }
                , {
                    field: 'enterpriseName',
                    title: '驻场企业名称',
                    width: 180,
                    align: 'left',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                     	  if(value!=null){
                              return "<a href=\"javascript:void()\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                              		"overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                              		"max-width: 100%;vertical-align: middle;\" onclick=\"editInfo('"+rowData.enterpriseId+"')\">"+value+"</a>";
                          }else{
                              return ""
                          }
                    }
                }
            ]],
            columns: [
                [
                    {
                        title: '1号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '2号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '3号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '4号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '5号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '6号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '7号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '8号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '9号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '10号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '11号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '12号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '13号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '14号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '15号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '16号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '17号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '18号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '19号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '20号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '21号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '22号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '23号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '24号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '25号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '26号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '27号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '28号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '29号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '30号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                    {
                        title: '31号',
                        align: 'center',
                        width: 80,
                        sortable: false,
                        colspan: 2

                    },
                     {
                    title: '出 勤 情 况 累 总（天）',
                    align: 'center',
                    width: 825,
                    sortable: false,
                    colspan: 15

                },  {
                    field: 'kxnj',
                    title: '结余可休<br/>年假（天）',
                    width: 100,
                    align: 'center',
                    sortable: false,
                    rowspan:2
                },{
                    field: 'reg_remark',
                    title: '备注',
                    width: 200,
                    align: 'left',
                    sortable: false,
                    rowspan:2,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		var qt=rowData.qt;
                            return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+qt+"天"+value+"</span>";
                    	}else{
                    		return ""
                    	}
                    }
                }, {
                    field: 'reg_creater',
                    title: '登记人',
                    width: 120,
                    align: 'left',
                    sortable: false,
                    rowspan:2,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                            return "<span title="+value+" style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">"+value+"</span>";
                    	}else{
                    		return ""
                    	}
                    }
                }],[
                    {
                        field: 'morning_1',
                        title:'上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                        	return setColor(value);
                        }

                    },
                    {
                        field: 'afternoon_1',
                        title:'下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                       	 	return setColor(value);
                        }

                    },
                    {
                        field: 'morning_2',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                                return setColor(value);
                        }
                    }, {
                        field: 'afternoon_2',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	  return setColor(value);
                        }
                    },{
                        field: 'morning_3',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_3',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_4',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_4',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_5',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_5',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_6',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	return setColor(value);
                        }
                    },{
                        field: 'afternoon_6',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_7',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_7',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_8',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_8',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_9',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_9',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_10',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_10',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_11',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_11',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_12',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_12',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_13',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_13',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_14',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_14',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_15',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_15',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_16',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_16',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_17',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_17',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_18',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_18',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_19',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_19',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_20',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_20',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_21',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_21',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_22',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_22',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_23',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_23',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_24',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_24',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_25',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_25',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_26',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_26',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_27',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_27',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_28',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_28',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_29',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_29',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_30',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'afternoon_30',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },{
                        field: 'morning_31',
                        title: '上午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	 return setColor(value);
                        }
                    },
                    {
                        field: 'afternoon_31',
                        title: '下午',
                        width: 60,
                        align: 'center',
                        sortable: false,
                        formatter:function (value,rowData,Index){
                        	return setColor(value);
                        }
                    },
                    {
                        field: 'sb',
                        title: '上班',
                        width: 55,
                        align: 'center',
                        sortable: false,
                    },
                    {
                        field: 'nxj',
                        title: '年休假',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'tqj',
                        title: '探亲假',
                        width: 55,
                        align: 'center',
                        sortable: false

                    },
                    {
                        field: 'sj',
                        title: '事假',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'syj',
                        title: '生育假',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'jsj',
                        title: '计生假',
                        width: 55,
                        align: 'center',
                        sortable: false

                    },{
                        field: 'bj',
                        title: '病假',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'hj',
                        title: '婚假',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'sangj',
                        title: '丧假',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'lgxx',
                        title: '离岗学习',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'cc',
                        title: '出差',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'kg',
                        title: '旷工',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'qt',
                        title: '其他',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'jb',
                        title: '加班',
                        width: 55,
                        align: 'center',
                        sortable: false
                    },{
                        field: 'xx',
                        title: '休息',
                        width: 55,
                        align: 'center',
                        sortable: false
                    }

                ]
            ],
            toolbar: '#tit1',
            onLoadSuccess: function (data) {
                if (data.total == 0) {
                    $('#recordManager').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
                }
            },
        });
        $(window).resize(function () {
        	if(bool){
                $('#recordManager').datagrid('resize', {
                    height: $(window).height() - 40
                });
            }else{
                $('#recordManager').datagrid('resize', {
                    height: tableHeight()
                });
            }
        });

    });
    function getBatchDialog() {
        selections = $('#recordManager').datagrid('getSelections');
        if (selections.length == 0) {

            $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
            // $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
            return;
        }
        var ids = "";
        var flag=true;
    	for(var i = 0;i<selections.length;i++){
    		var cuurDate=new Date();
        	var actualStartTime=new Date(selections[i].actualStartTimeStr);
        	if(actualStartTime>cuurDate){
        		$.messager.alert("温馨提示","驻场人员【"+selections[i].name+"】的实际驻场时间还未开始，不能进行考勤登记!", "error");
        		return;	
        	}
    		ids+=selections[i].id+",";
    	}
    	ids = ids.substring(0,ids.length-1);
        var regYear=$("#regYear").val();
    	var regMonth=$("#regMonth").val();
    	var days=getDaysInYearMonth(regYear,regMonth);
    	var minDate=regYear+"-"+regMonth+"-"+"01";
    	var maxDate=regYear+"-"+regMonth+"-"+days;
        $('#dlg').dialog({
            title: '考勤批量登记单',
            width: 800,
            height:400,
            closed: false,
            cache: false,
            top: 100,
            content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/workRegister/toRegisterBatchDialog?personIds='+ids+"&minDate="+minDate+"&maxDate="+maxDate+" scrolling=\"no\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    function getPersonnalDialog(id,actualStartTimeStr,actualEndTimeStr) {
    	var cuurDate=new Date();
    	var actualStartTime=new Date(actualStartTimeStr);
    	if(actualStartTime>cuurDate){
    		$.messager.alert("温馨提示","该驻场人员的实际驻场时间还未开始，不能进行考勤登记!", "error");
       	 	return;	
    	}
    	$("#recordManager").datagrid("clearChecked");
    	$("#recordManager").datagrid('clearSelections');
    	var regYear=$("#regYear").val();
    	var regMonth=$("#regMonth").val();
    	var days=getDaysInYearMonth(regYear,regMonth);
    	var minDate=regYear+"-"+regMonth+"-"+"01";
    	var maxDate=regYear+"-"+regMonth+"-"+days;
        $('#personalDlg').dialog({
            title: '个人考勤登记单',
            width: 800,
            height:450,
            closed: false,
            cache: false,
            top: 50,
            // href: 'personalDialog.html',
            content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/workRegister/toRegisterDialog?personId='+id+"&minDate="+minDate+"&maxDate="+maxDate+"&actualEndTimeStr="+actualEndTimeStr+"&actualStartTimeStr="+actualStartTimeStr+" scrolling=\"no\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    //某年某月有几天
    function getDaysInYearMonth(year, month){
    	if(year=="" || month==""){
    		var curDate = new Date();
    		return curDate.getDate();
    	}else{
    		var curDate = new Date(year+"-"+month);
    		/* 获取当前月份 */
    		var curMonth = curDate.getMonth();
    		/* 生成实际的月份: 由于curMonth会比实际月份小1, 故需加1 */
    		curDate.setMonth(curMonth + 1);
    		/* 将日期设置为0 */
    		curDate.setDate(0);
    		/* 返回当月的天数 */
    		return curDate.getDate();
    	}
    }

    function addPersonTab(id) {
        top.addTabGrid('驻场人员修改', '/workPerson/toEdit?id='+id);
    }
    
    function toProcess(id) {
    	window.parent.addTabGrid('请假申请详情', '/workProcessApply/toTaskDetailsShow?id='+id)
    }
    
    function editInfo(id) {
        var href = "/workEnterprise/toEdit?id="+id;
        var title = "驻场企业修改";
        top.addTabGrid(title, href);
    }
    //查询
    function searchReg(){
    	var name = $("#name").val().trim();
    	var enterpriseName = $("#enterpriseName").val().trim();
    	var morningOrAfternoon = $("#morningOrAfternoon").val().trim();
    	var regYear=$("#regYear").val();
    	var regMonth=$("#regMonth").val();
    	var startTime=$("#startTime").val();
    	var endTime=$("#endTime").val();
    	
        $('#recordManager').datagrid({
    		queryParams: {
    			name:name,
    			enterpriseName:enterpriseName,
    			morningOrAfternoon:morningOrAfternoon,
    			regYearMonth:regYear+regMonth,
    			startTime:startTime,
    			endTime:endTime
    		}
    	}); 
    }
    //重置
    function refresh() {
    	var json1={tabTitle:'考勤登记',url:'/workRegister/tolist'};
    	window.parent.refreshTab(json1); 
    }

    function setColor(value){
    	var spanStr="";
    	if(value){
	    	if(value.indexOf("-") !=-1){
	    		var m=value.split("-");
	    		if(m[0]=='休息'){
	    			spanStr="<span style=\"color:darkgray;\">"+m[0]+"</span>";
	    		}else{
		    	 	spanStr="<a href='javascript:void(0)' title='请假单' onclick='toProcess(\"" + m[1] + "\")' style=\"color:#549de3;\">"+m[0]+"</a>";
	    		}
	    	}else{
	    		if(value=='上班'){
	    			spanStr="<span style=\"color:#000;\">"+value+"</span>";
	    		}else if(value=='休息'){
	    			spanStr="<span style=\"color:darkgray;\">"+value+"</span>";
	    		}else{
	    			spanStr="<span style=\"color:#14446a;\">"+value+"</span>";
	    		}
	    	}
    	}
    	
    	return spanStr;
    }
    
    //下载导入模板
	function importREG(){
  		window.location.href = "/workRegister/importREG";
	}

</script>
<script src="${pageContext.request.contextPath}/res/js/work/register/list.js"></script>
</body>
</html>