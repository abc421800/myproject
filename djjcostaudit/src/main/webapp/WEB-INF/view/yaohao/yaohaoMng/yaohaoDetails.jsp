<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>摇号登记</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
	<style>
        .title1 {
            font-size: 12px;
            color: #283747;
            font-weight: bold;
            background: url(${pageContext.request.contextPath}/res/images/pagecommon/flag.png) 0 center no-repeat;
            padding-left: 28px;
            display: inline-block;
            line-height: 28px;
            margin-right: 16px;
            height: 28px;
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

<div data-options="region:'center',border:0" class="wrap_center">
    <form action="" id="formId1">
    	<input type="hidden" id="winbid" name="winbid" value="">
	    <input type="hidden" value="${obj.id}" name="id" id="orderId">
    	<input type="hidden" id="lunNumber" name="lunNumber" value="${obj.lunNumber }">
        <table class="table_edit" id="" cellspacing="0" cellpadding="0" style="margin-bottom: 2px">
                <tbody>
                    <tr>
                        <td colspan="6" class="bgcolor" style="text-align: center">
                            <input type="hidden" id="code" name="code"  value="${obj.code }" >
                            <h4 style="display: inline-block;font-size: 20px;color:#169BD5;font-weight: bold"><span id="prefix"></span></h4><span style="font-size: 20px;font-weight: bold"><span id="suffix"></span> 摇号单</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="bgcolor" style="width:20%;"><strong>*</strong>摇号时间：</td>
                        <td style="width:13%;">
                            <input class="Wdate search_text_form" type="text" id="yaohaoTimeStr" name="yaohaoTimeStr" value="${obj.yaohaoTimeStr}" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">

                        </td>
                        <td class="bgcolor" style="width:14%;"><strong>*</strong>摇号档次：</td>
                        <td style="text-align: left;width: 15%">
                            <select <c:if test="${add_edit eq 'edit' }">disabled="disabled"</c:if>  id="yaohaoGrade" name="yaohaoGrade" onchange="changeCode()" class="custom-select" >
                            	<c:forTokens items="第二档,第一档" delims="," var="i">
                            		<option <c:if test="${i eq obj.yaohaoGrade }">selected = "selected"</c:if>  value="${i}">${i}</option>
                            	</c:forTokens>
                                
                            </select>
                        </td>
                        <td class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>经办人：</td>
                        <td style="width:14%">
                            <input type="text"  placeholder="读取操作者账号"  value="${obj.creater}" readonly style="width:100%;">
                        </td>


                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>摇珠地点：</td>
                        <td colspan="2">
                            <input type="text" id="address" name="address" value="${obj.address }" placeholder="请输入摇珠地点" style="width:100%;">
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong>其他工作人员：</td>
                        <td colspan="2">
                            <input type="text" id="workingPerson" name="workingPerson"  value="${obj.workingPerson }" placeholder="请输入或选择其他工作人员" style="margin-top:5px;width:80%;">
                           	<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="clearPerson();">
		            			<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19"height="19">
			            	</a>
							<a href="javascript:void(0)" class="fr" style="margin-top:5px;margin-right:5px;" onclick="addPerson()">
			            		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
			            	</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>*</strong>参加摇珠会议企业数量</td>
                        <td>
                            <input type="text" readonly="readonly" id="enterpriseNum" name="enterpriseNum" value="${obj.enterpriseNum }"  placeholder="参加摇珠会议企业数量" style="width:100%;height:30px;line-height: 30px">
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong>中签企业数量：</td>
                        <td>
                            <input type="text" readonly="readonly" id="winbidNum" name="winbidNum" value="${obj.winbidNum }" placeholder="中签企业数量" style="width:100%;height:30px;line-height: 30px"/>
                        </td>
                        <td class="bgcolor">摇号年份</td>
                        <td>
                        	<input type="text" readonly="readonly" id="yaohaoYear" name="yaohaoYear" value="${obj.yaohaoYear }" placeholder="摇号年份" style="width:100%;height:30px;line-height: 30px"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                        <td colspan="5">
                            <textarea name="remark" cols="5" rows="2">${obj.remark}</textarea>
                        </td>
                    </tr>
                    <tr id="show2">
                        <td class="bgcolor"><strong>&nbsp;</strong>本次“回避”不参加摇号的企业：</td>
                        <td colspan="5" style="padding: 10px;2px;">
                        	<c:forEach var="i" items="${candidateRemoveListB }" varStatus="status">
                        		<c:choose>
	                        		<c:when test="${status.last }">
	                        			<span style="">${status.count} ${i.enterpriseName } </span>
	                        		</c:when>
	                        		<c:otherwise>
		                        		<span style="">${status.count} ${i.enterpriseName } </span>,
	                        		</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                        	
                        </td>
                    </tr>
                    <tr id="show1" style="display: none;padding: 10px;2px;">
                        <td class="bgcolor"><strong>&nbsp;</strong>本次摇号剔除摇号机会的企业：</td>
                        <td colspan="5">
                        	<c:forEach var="i" items="${candidateRemoveListA }" varStatus="status">
                        		<c:choose>
	                        		<c:when test="${status.last }">
	                        			<span>${status.count} ${i.enterpriseName } </span>
	                        		</c:when>
	                        		<c:otherwise>
		                        		<span>${status.count} ${i.enterpriseName } </span>,
	                        		</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                        	
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <form action="" id="formId2">    
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
            <tbody id="data_list">
            <tr style="height: 48px">
                <td colspan="6" class="bgcolor" style="text-align: left;position: relative">
                	<c:choose>
                		<c:when test="${add_edit eq 'edit' }">
                			 <span class="title1">中签企业列表</span>
                			 <c:if test="${edit_win_flag eq 'y' }">
                			 	<a href="javascript:void(0);" class="btn btn-primary" onclick="addWinningCompany()">添加</a>
                			 </c:if>
                		</c:when>
                		<c:otherwise>
		                    <span class="title1">添加中签企业名称</span>
		                    <a href="javascript:void(0);" class="btn btn-primary" onclick="addWinningCompany()">添加</a>
                		</c:otherwise>
                	</c:choose>
                </td>

            </tr>
            <tr>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:23%"><strong>*</strong>中签企业名称</td>
             <%--   <td class="bgcolor" style="text-align: center;background: #cce8cf;width:12%"><strong>&nbsp;</strong>企业派出代表</td>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:12%"><strong>&nbsp;</strong>联系电话</td>--%>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:23%"><strong>*</strong>中签项目</td>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:15%"><strong>*</strong>请示金额（元）</td>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:15%"><strong>&nbsp;</strong>合同金额（元）</td>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:15%"><strong>&nbsp;</strong>结算金额（元）</td>
                <td class="bgcolor" style="text-align: center;background: #cce8cf;width:15%"><strong>&nbsp;</strong>备注</td>
            </tr>
            <c:if test="${add_edit eq 'edit' }">
            	<c:forEach var="winbid" items="${winbidList}">
		            <tr>
			            <td>
			            	<input type="hidden" name="id" value="${winbid.id}">
			            	<input type="hidden" name="enterpriseId" value="${winbid.enterpriseId}">
			            	<input type="hidden" name="candidateId" value="${winbid.candidateId}">
			            	<%--
				            <input type="text" name="enterpriseName" value="${winbid.enterpriseName }" readonly="readonly" onclick="editInfo('${winbid.enterpriseId}')" placeholder="请选择中签企业名称"  style="width:90%;margin-top:5px;color:#549de3;cursor:pointer;">
				            --%>
				            <input type="text" name="enterpriseName" value="${winbid.enterpriseName }" readonly="readonly"  placeholder="请选择中签企业名称"  style="width:90%;margin-top:5px;">
				            <c:if test="${edit_win_flag eq 'y' }">
				            <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="removeHandler(this)">
				            	<img src="${pageContext.request.contextPath}/res/images/pagecommon/remove.png" alt="" width="19" height="19">
				            </a>
				            </c:if>
				            <div style="clear: both"></div>
			            </td>
                        <td>
			            	<input type="text" name="name" value="${winbid.name }" placeholder="请输入中签项目" style="width:100%;">
			            </td>
                        <td style="text-align: center">
                            <input type="text" name="serviceAmount" value="${winbid.serviceAmount }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入请示金额" style="width:140px;height:30px;line-height: 30px"/>
                        </td>
                        <td style="text-align: center">
                            <input readonly="readonly" type="text" name="decideAmount" value="${winbid.decideAmount }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2" placeholder="请输入合同金额" style="width:140px;height:30px;line-height: 30px"/></td>
                        </td>
                        <td style="text-align: center">
                            <input readonly="readonly" type="text" name="settlementAmount" value="${winbid.settlementAmount }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入结算金额" style="width:140px;height:30px;line-height: 30px"/>
                        </td>
                        <td style="text-align: center">
                            <textarea name="remark" cols="1" rows="1">${winbid.remark}</textarea>
                        </td>
		            </tr>
	            </c:forEach>
			</c:if>

            <shiro:hasPermission name="order:update">
			<c:if test="${editFlag eq 'y'}">
	            <tr style="height:40px" class="saveTrId">
	                <td colspan="6" style="text-align: center">
	                    <input type="button" class="btn btn-primary" value="保存" onclick="saveHandler()">&nbsp;&nbsp;
	                    <input type="button" class="btn btn-success" onclick="top.closeWindow('摇号单登记');top.closeWindow('摇号单修改');" value="取消">
	                </td>
	            </tr>
            </c:if>
            </shiro:hasPermission>
            </tbody>
        </table>
    </form>
    <div id="tt" style="width:100%;">
        <div title="摇号资料" class="subWrap">

            <table id="fujian" style="width: 100%">
            </table>

            <div id="tit1">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog()">添加文件夹</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog();">修改文件夹名称</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile()">删除</a>
	                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile()">上传附件</a>
                	<%--<input type="button" name="reset" value="《摇珠会议通知模版》下载" class="btn btn-info" onclick="">--%>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>

        </div>
    </div>
</div>


<div id="taskDialog" style="background: #f4f8fb"></div>
<div id="dlg"></div>
<div id="editDialog"></div>
<div id="dialog"></div>
<div id="info_add"></div>
<div id="addPerson" style="overflow-y: hidden"></div>
<div id="addWinningCompany" style="overflow-y: hidden"></div>
<script type="text/javascript">

var codeA="${codeA}";
var codeB="${codeB}";
var lunA="${lunA}";
var lunB="${lunB}";
var add_edit="${add_edit}";
var yaohaoGradeA="${yaohaoGradeA}";
var yaohaoGradeB="${yaohaoGradeB}";
var url="${url}";
var orderId="${obj.id}";
$(function(){
	if(add_edit=='add'){
		$("#prefix").text("B");
		$("#suffix").text(codeB);
	}else{
		var code="${obj.code}";
		var pre=code.substring(0,1);
		var suf=code.substring(1,code.length);
		$("#prefix").text(pre);
		$("#suffix").text(suf);
	}
})

function changeCode(){
	if(add_edit=='add'){
		var yaohaoGrade=$("#yaohaoGrade").val();
		if(yaohaoGrade=="第二档"){
			$("#prefix").text("B");
			$("#suffix").text(codeB);
			$("#code").val("B"+codeB)
			$("#enterpriseNum").val(yaohaoGradeB);
			$("#lunNumber").val(lunB);
			$("#show1").hide();
			$("#show2").show();
		}else{
			$("#prefix").text("A");
			$("#suffix").text(codeA);
			$("#code").val("A"+codeA);
			$("#enterpriseNum").val(yaohaoGradeA);
			$("#lunNumber").val(lunA);
			$("#show1").show();
			$("#show2").hide();
		}
	}
}

//添加中签
 var count=0;
    var times=0;
    var comArrTemp=[];
    var comArr=[];//企业名称
    var tempArr=[];

    var comArrTempId=[];
    var comArrId=[];//企业id
    var tempArrId=[];
    
    var comArrTempWinbidIds=[];
    var comArrWinbidIds=[];//中签id
    var tempArrWinbidIds=[];
    
    function addCompanyHandler(){
        //获取Id
        var tr_str="";
        //名称
        tempArr=comArrTemp.concat(comArr);
        comArrTemp=tempArr;
        //企业id
        tempArrId=comArrTempId.concat(comArrId);
        comArrTempId=tempArrId;
        //中签id
        tempArrWinbidIds=comArrTempWinbidIds.concat(comArrWinbidIds);
        comArrTempWinbidIds=tempArrWinbidIds;
        
        for(var i=times; i<tempArr.length;i++){
            tr_str+="<tr>";
            tr_str+="<td>" +
                "<input type=\"hidden\" name=\"id\"  value=\"\"  > " +
                "<input type=\"hidden\" name=\"enterpriseId\" value="+tempArrId[i]+"> " +
                "<input type=\"hidden\" name=\"candidateId\" value="+tempArrWinbidIds[i]+"> " +
                "<input type=\"text\" id='' value="+tempArr[i]+"  readonly=\"readonly\" title="+tempArr[i]+" name=\"enterpriseName\" placeholder=\"请选择中签企业名称\"  style=\"width:80%;margin-top:5px;\">" +
                "<a href=\"javascript:void(0)\" class=\"fr\" style=\"margin-top:5px;\" onclick=\"removeHandler(this)\">" +
                "<img src=\"${pageContext.request.contextPath}/res/images/pagecommon/remove.png\" alt=\"\" width=\"19\" height=\"19\">" +
                "</a>" +
                "<div style=\"clear: both\"></div>" +
                "</td>";
            tr_str+="<td>" +
                "<input type=\"text\"  value=\"\" name=\"name\" placeholder=\"请输入中签项目\" style=\"width:100%;\">" +
                "</td>";
            tr_str+="<td>" +
                "<input type=\"text\" value=\"\"  name=\"serviceAmount\" class=\"money\"  placeholder='请输入请示金额' style=\"width:140px;height:30px;line-height: 30px\"/>" +
                "</td>";
            tr_str+="<td>" +
                "<input type=\"text\" value=\"\"  name=\"decideAmount\" readonly=\"readonly\" class=\"money\" placeholder='请输入合同金额' style=\"width:140px;height:30px;line-height: 30px\"/>" +
                "</td>";
            tr_str+="<td style='text-align: center'>" +
                "<input type=\"text\" value=\"\"  name=\"decideAmount\" readonly=\"readonly\" class=\"money\" placeholder='请输入结算金额' style=\"width:140px;height:30px;line-height: 30px\"/>" +
                "</td>";
            tr_str+="<td style='text-align: center'>" +
                "<input type=\"text\" value=\"\"  name=\"remark\"  placeholder='请输入备注' style=\"width:140px;height:30px;line-height: 30px\"/>" +
                "</td>";
            tr_str+="</tr>";
            $("#table_edit .saveTrId").before(tr_str);
            tr_str='';

        }
        $('.money').numberbox({
            groupSeparator:',',
            precision:2
        });
        times=tempArr.length;
        numberInputPlaceholder();
    }
    function numberInputPlaceholder(){
        $(".money").each(function(i){
            var span = $(this).siblings("span")[0];
            var targetInput = $(span).find("input:first");
            if(targetInput){
                $(targetInput).attr("placeholder", $(this).attr("placeholder"));
            }

        });
    }
    //保存方法
    function saveHandler(){
    	var yaohaoTimeStr=$("#yaohaoTimeStr").val();
    	var address=$("#address").val();
    	if(yaohaoTimeStr==''){
    		 $.messager.alert({title:'温馨提示', msg:'请选择摇号时间 ！', icon: 'error', top:100});
             return;
    	}
    	if(address==''){
   		 $.messager.alert({title:'温馨提示', msg:'请填写摇珠地点 ！', icon: 'error', top:100});
            return;
   		}
        var data=$("#formId2").serializeArray();
        var result = [];
        for(var i=0,len=data.length;i<len;i+=9){
            result.push(data.slice(i,i+9));
        }
        var dataArr=[];
        var obj = {};
        for(var j=0;j<result.length;j++){
            $.each(result[j],function(i,v){
                obj[v.name] = v.value;
            });
            dataArr.push(JSON.stringify(obj));
        }
        
        var trList = $("#data_list").children("tr");
        for (var i=2;i<trList.length-1;i++) {
            var tdArr = trList.eq(i).find("td");
            var winningProjectName = tdArr.eq(1).find('input').val();//中签项目
            if(winningProjectName==""){
                $.messager.alert({title:'温馨提示', msg:'请填写中签项目 ！', icon: 'error', top:100});
                return;
            }
            var serviceMoney = tdArr.eq(2).find('input').val();//服务金额
            if(serviceMoney==""){
                $.messager.alert({title:'温馨提示', msg:'请填写请示金额 ！', icon: 'error', top:100});
                return;
            }
        }

        if(trList.length<=3){
            $.messager.alert({title:'温馨提示', msg:'请添加中签企业名称 ！', icon: 'error', top:100});
            return;
        }
        //alert(dataArr);
        var winbid= JSON.stringify(dataArr);
        var winbidStr= dataArr.toString();
      	$("#winbid").val(winbidStr);
		$.ajax({
			  url: url,
			  dataType: "json",
			  type:"post",
			  async: false,
			  data: $("#formId1").serialize(),
			  success: function(result){
				  if(result.status==200){
					  url="/yaohaoOrder/upd";
					  var json1={tabTitle:'摇号台账',url:'/forward_yaohao_yaohaoMng_yaohaoList'};
					  window.parent.refreshTab(json1); 
					  window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");	
				  }else if(result.status==300){
					  $.messager.alert("温馨提示","保存失败,中签项目不能重复!", "error");
				  }else{
					  $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
				  }
			  }
		});
        
        
        
    }
    
    function removeHandler(_this) {
        var index=$(_this).parent().parent().closest("tr").index();
        var data_id=$(_this).parent().find("input:hidden[name='id']").val();//获取本行数据Id
        $.messager.confirm('提示信息', '确认要删除这条记录吗？', function (isOk) {
            if (!isOk) {
                return;
            }
            $("#table_edit tr").eq(index).remove();
	      	//计算中签个数
	        var trList = $("#data_list").children("tr").length-3;
	        $('#winbidNum').val(trList);
        });
    }
    
    function addWinningCompany() {
        var data=$("#formId2").serializeArray();
        var result = [];
        for(var i=0,len=data.length;i<len;i+=9){
            result.push(data.slice(i,i+9));
        }
        var dataArr=[];
        var obj = {};
        for(var j=0;j<result.length;j++){
            $.each(result[j],function(i,v){
                obj[v.name] = v.value;
            });
            dataArr.push(JSON.stringify(obj));
        }
        var idArr=[];//获取已中签企业的id
        for(var i=0;i<dataArr.length;i++){
            idArr.push(JSON.parse(dataArr[i]).enterpriseId);
        }
        //alert(idArr);//排除已选id
        var yaohaoGrade=$("#yaohaoGrade").val();
        var yaohaoYear=$("#yaohaoYear").val();
        var lunNumber=$("#lunNumber").val();
        var yaohaoGradeFlag=2;
		if(yaohaoGrade=='第二档'){
			yaohaoGradeFlag=2;
		}else{
			yaohaoGradeFlag=1;
		}
        
        $('#addWinningCompany').dialog({
            title: '摇号名单',
            width: 820,
            height: 510,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/forward_yaohao_yaohaoMng_yaohaoAddWinEnt?yaohaoGradeFlag='+yaohaoGradeFlag+"&yaohaoYear="+yaohaoYear+"&noEntId="+idArr+"&roundNum="+lunNumber +"  scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    function addPerson() {
    	var name=$("#workingPerson").val();
        $('#addPerson').dialog({
            title: '系统用户',
            width: 820,
            height: 510,
            closed: false,
            cache: false,
            top:50,
            content:"<iframe name=\"fileFrame\" frameborder=\"0\" src="+'/forward_yaohao_yaohaoMng_containUser?names='+encodeURI(encodeURI(name))+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
    function clearPerson(){
		$("#workingPerson").val("");
	}
    
    function editInfo(id) {
        var href = "/costEnterprise/toEdit?editFlag=y&id="+id;
        var title = "入库企业修改";
        top.addTabGrid(title, href);
    }
</script>
<script src="${pageContext.request.contextPath}/res/js/yaohao/yaohaoMng/attachment.js"></script>
</body>
</html>