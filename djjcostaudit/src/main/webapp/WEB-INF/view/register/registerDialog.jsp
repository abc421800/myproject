<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登记弹窗</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-master/src/css/layui.css">
</head>
<script src="${pageContext.request.contextPath}/res/plugin/layui-master/src/layui.js"></script>
<script>
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            //时间范围
            laydate.render({
                elem: '#recordTime',range: true
            });
        });
    </script>
<style>
    td{padding:5px !important;}
</style>
<body  style="padding: 0">
<div class="dialog-file">
    <form name="pageForm" action="" method="post" id="formId">
        <div class="tabox table-responsive float-table">
            <table class="table table-condensed table-bordered">
                <tbody>
                <tr>
                    <th style="width:22%"><strong style="color:red">&nbsp;</strong>驻场人姓名：</th>
                    <td>
                        <input name="projName"  type="text" style="width:100%;" readonly value="${obj.name }" class="form-control input-sm" />
                        <input name="personId" id="personId"   type="hidden"  value="${obj.account}" />
                    </td>
                </tr>
                <tr>
                    <th style="width:20%"><strong style="color:red">&nbsp;</strong>驻场人所在企业 ：</th>
                    <td>
                        <input name="projName"  type="text" style="width:100%;" readonly value="${obj.enterpriseName }" class="form-control input-sm" />
                    	<input name="enterpriseId" id="enterpriseId"   type="hidden"  value="${obj.enterpriseId}" />
                    </td>
                </tr>
                <tr>
                    <th><strong style="color:red">&nbsp;</strong>可休年假：</th>
                    <td>
                        <%--<input readonly="readonly" class="Wdate search_text_form" type="text" id="recordTime" value="<fmt:formatDate value="${obj.annualLeaveStartTime}" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${obj.annualLeaveEndTime}" pattern="yyyy-MM-dd"/>" style="width:50%;display:inline-block;margin-right:5px">--%>
                        <input readonly="readonly"  type="text" style="width:15%;display: inline-block" value="<fmt:formatDate value="${obj.annualLeaveStartTime}" pattern="yyyy-MM-dd"/>" class="form-control input-sm" /> 至
                        <input readonly="readonly"  type="text" style="width:15%;display: inline-block" value="<fmt:formatDate value="${obj.annualLeaveEndTime}" pattern="yyyy-MM-dd"/>" class="form-control input-sm" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label for="">总共：</label>
                        ${obj.annualLeaveTotal }&nbsp;&nbsp;天
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label for="">已休：</label>
                        ${obj.annualLeaveUseup }&nbsp;&nbsp;天
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<label for="">可休：${obj.annualLeaveTotal-obj.annualLeaveUseup }</label>&nbsp;&nbsp;天
                    </td>
                </tr>
                <tr>
                    <th><strong style="color:red">&nbsp;</strong>考勤登记时间：</th>
                    <td>
                    	<!-- ,specialDates:['....-..-01','....-..-02','....-..-15'] -->
                        <input id="registerTimeStrStart" name="registerTimeStrStart" class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'${param.minDate}',maxDate:'${param.maxDate}',specialDates:${regDays} })" style="width:47%;"> 至
                        <input id="registerTimeStrEnd" name="registerTimeStrEnd" class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'${param.minDate}',maxDate:'${param.maxDate}',specialDates:${regDays} })" style="width:47%;">
                       
                    </td>
                </tr>

                <tr>
                    <th><strong style="color:red">&nbsp;</strong>出勤情况：</th>
                    <td>	
                    	<span>上午：</span>
                        <select class="form-control input-sm" style="width:40%;margin-right:20px;display: inline-block" name="morning" id="morning">
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
                            <option value="休息">休息</option>
                            <option value="加班">加班</option>
                            <option value="其他">其他</option>
                        </select>
                        	<span>下午：</span>
                        <select class="form-control input-sm" style="width:40%;margin-right:20px;display: inline-block" name="afternoon" id="afternoon">
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
                            <option value="休息">休息</option>
                             <option value="加班">加班</option>
                            <option value="其他">其他</option>
                        </select>
                    </td>
                </tr>
                <tr id="showQt1" style="display: none;">
                    <th><strong id="remark_star1" style="color:red">*</strong>备注：</th>
                    <td>
                        <select name="remarkQt" class="form-control input-sm" style="width:98%;display: inline-block"  id="remarkQt">
                            <option value="">请选择</option>
                            <option value="回公司">回公司</option>
                            <option value="公司会议">公司会议</option>
                            <option value="其他">其他</option>
                        </select>
                    </td>
                </tr>
                <tr id="showQt2">
                    <th><strong id="remark_star2" style="color:red">&nbsp;</strong>备注：</th>
                    <td>
                        <textarea id="remark" name="remark" rows="4" class="form-control input-sm" ></textarea>
                    </td>
                </tr>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" id="saveBtn" value="保存" class="btn btn-primary" onclick="" />
            <input type="button" name="cancel" class="btn btn-danger" value="取消" onclick="window.parent.$('#personalDlg').dialog('close');" />
        </div>
    </form>
</div>

<script>
	var kx="${obj.annualLeaveTotal-obj.annualLeaveUseup }";
    $("#morning").change(function(){
        var str=$(this).val();
        var afternoon=$("#afternoon").val();
        if(str=='其他'){
        	$("#showQt1").show();
        	$("#showQt2").hide();
        }else{
        	if(afternoon!='其他'){
        		$("#showQt1").hide();
        		$("#showQt2").show();
        	}
        }
    })
    $("#afternoon").change(function(){
        var str=$(this).val();
        var morning=$("#morning").val();
        if(str=='其他'){
        	$("#showQt1").show();
        	$("#showQt2").hide();
        }else{
        	if(morning!='其他'){
        		$("#showQt1").hide();
        		$("#showQt2").show();
        	}
        }
    })
    $("#remarkQt").change(function(){
        var str=$(this).val();
        var morning=$("#morning").val();
        if(str=='其他'){
        	$("#showQt2").show();
        	$("#remark_star2").text("*");
        	
        }else{
        	$("#showQt2").hide();
        	$("#showQt2").val("");
        }
    })
    
    $(function(){
    	$("#saveBtn").click(function(){
    		var registerTimeStrStart=$("#registerTimeStrStart").val();
    		var registerTimeStrEnd=$("#registerTimeStrEnd").val();
    		var afternoon=$("#afternoon").val();
    		var morning=$("#morning").val();
    		var remark=$("#remark").val();
    		var actualEndTimeStr="${param.actualEndTimeStr}";
    	    var actualStartTimeStr="${param.actualStartTimeStr}";
    		
    		if(registerTimeStrStart==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择 考勤登记开始时间 ！', icon: 'error', top:100});
               	return;
           	}
    		if(registerTimeStrEnd==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择 考勤登记结束时间 ！', icon: 'error', top:100});
               	return;
           	}
           	if(registerTimeStrStart>registerTimeStrEnd){
           		$.messager.alert({title:'温馨提示', msg:'开始时间不能大于结束时间 ！', icon: 'error', top:100});
               	return;
           	}
           	
    		if(morning==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择上午出勤情况！', icon: 'error', top:100});
               	return;
           	}
    		if(afternoon==""){
               	$.messager.alert({title:'温馨提示', msg:'请选择下午出勤情况！', icon: 'error', top:100});
               	return;
           	}
    		if(morning=='其他' || afternoon=='其他'){
    			var remarkQt=$("#remarkQt").val();
    			if(remarkQt==""){
                   	$.messager.alert({title:'温馨提示', msg:'请填写备注！', icon: 'error', top:100});
                   	return;
               	}
    			if(remarkQt=='其他'){
    				if(remark==""){
    					$.messager.alert({title:'温馨提示', msg:'请填写备注！', icon: 'error', top:100});
                       	return;
    				}
    			}
    		}
    		if(morning=='年休假' || afternoon=='年休假'){
    			if(kx==0){
                   	$.messager.alert({title:'温馨提示', msg:'年假已经休完,请选择其他类型！', icon: 'error', top:100});
                   	return;
               	}
    		}
    		var regDateStart=new Date(registerTimeStrStart);
    		var regDateEnd=new Date(registerTimeStrEnd);
    		var nowDate=new Date();
    		if(regDateStart>nowDate || regDateEnd>nowDate){
    			if(morning=='上班' || afternoon=='上班'){
    				$.messager.alert({title:'温馨提示', msg:'将来时不可登记为上班！', icon: 'error', top:100});
                   	return;
    			}
    		}
    		if(actualEndTimeStr!="null"){
   				if(registerTimeStrStart>actualEndTimeStr){
   					$.messager.alert("温馨提示","该驻场人员的实际驻场时间已经结束，不能进行考勤登记!", "error");
                    return;
   				}
    		}
    		if(actualStartTimeStr!="null"){
   				if(registerTimeStrStart<actualStartTimeStr){
   					$.messager.alert("温馨提示","登记时间小于实际驻场时间，不能进行考勤登记!", "error");
                    return;
   				}
    		}
    		$.ajax({
			  	url: "/workRegister/save",
			  	dataType: "json",
			  	type:"post",
			  	async: false,
			  	data: $("#formId").serialize(),
			  	success: function(result){
				  	if(result.status==200){
				  		 window.parent.$('#personalDlg').dialog('close');
		        		 window.parent.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
		        		 window.parent.$("#recordManager").datagrid('reload');
				  	}else{
	    				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
	    			}
			  	}
			});
    	});
    });
</script>
</body>
</html>