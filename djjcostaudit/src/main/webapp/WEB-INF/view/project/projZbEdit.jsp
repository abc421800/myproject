<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目指标修改</title>
      <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <!-- 审批新建页面样式 -->
<style>
.combo-panel
 {

    overflow: auto;

}
.tabs-tool, .tabs-header, .tabs-tool {
	background-color: #f4f8fb;
}

.title {
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
        .title1 {
            font-size: 12px;
            color: #283747;
            font-weight: bold;
            background: url(${pageContext.request.contextPath}/res/images/pagecommon/flag1.png) 0 center no-repeat;
            padding-left: 28px;
            display: inline-block;
            line-height: 28px;
            margin-right: 16px;
            height: 28px;
        }
        a.moreBtn {
            display: inline-block;
            height: 28px;
            line-height: 28px;
            border: 1px solid #e2e8ee;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            position: absolute;
            text-decoration: none;
            box-sizing: border-box;
            -webkit-box-shadow: 0 3px 3px rgba(201, 205, 209, 0.58);
            -moz-box-shadow: 0 3px 3px rgba(201, 205, 209, 0.58);
            box-shadow: 0 3px 3px rgba(201, 205, 209, 0.58);
            padding: 0 8px;
            background-color: #f4f7fb;
            color: #283747;
            font-size: 12px;
            font-weight: bold;
            font-family: "微软雅黑";
            right:80px;
        }
        .switchBtn1,.switchBtn2 {
            position: absolute;
            display: inline-block;
            width: 28px;
            height: 28px;
            line-height: 48px;
            border: 1px solid #dbe5ee;
            -webkit-border-radius: 0 0 4px 4px;
            -moz-border-radius: 0 0 4px 4px;
            border-radius: 0 0 4px 4px;
            background: #f4f7fb url(${pageContext.request.contextPath}/res/images/pagecommon/top.png) center center no-repeat;
            right:20px;
            top:-1px;
        }
        .switchBtn1.active, .switchBtn2.active {
            background: #f4f7fb url(${pageContext.request.contextPath}/res/images/pagecommon/bottom.png) center center no-repeat;
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
    <form  id="formId" method="post">
    	<input type="hidden" name="id" id="projId" value="${project.id }"/>
		<input type="hidden" name="code"  value="${project.code }"/>
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" style="margin-bottom:20px">
                <tbody>
		           <!-- 项目指标信息 -->
		           <tr style="height: 48px">
		               <td colspan="4" style="text-align: left;position: relative">
		                   <span class="title1">项目指标信息</span>
		                   <a href="javascript:void(0);" class="switchBtn2"></a>
		               </td>
		           </tr>
				   <tr class="sonlist2">
					   <td class="bgcolor" ><strong>&nbsp;</strong>工程名称：</td>
					   <td colspan="3">
						   <input type="text" id="projectName" name="name" value="${project.name}" readonly style="width:100%;"/>
					   </td>
				   </tr>
				   <tr class="sonlist2">
					   <td class="bgcolor" ><strong>&nbsp;</strong>项目编号：</td>
					   <td colspan="3">
						   <input type="text" id="projectNo" name="name" value="${project.code}" readonly style="width:100%;"/>
					   </td>
				   </tr>
				   <tr class="sonlist2">
					   <td class="bgcolor" ><strong>&nbsp;</strong>建设地点：</td>
					   <td colspan="3">
						   <input type="text" id="address" name="area" value="${project.area}" <%--readonly--%> style="width:100%;"/>
					   </td>
				   </tr>
				   <tr class="sonlist2">
					   <td class="bgcolor" ><strong>&nbsp;</strong>项目概况：</td>
					   <td colspan="3">
						   <input type="text" id="info" name="projectIntroduction" value="${project.projectIntroduction}" <%--readonly--%> style="width:100%;"/>
					   </td>
				   </tr>
				   <tr class="sonlist2">
					   <td class="bgcolor" ><strong>&nbsp;</strong>说明：</td>
					   <td colspan="3">
						   <textarea name="description" id="illustration" cols="3" rows="2"></textarea>
					   </td>
				   </tr>
				   <tr class="sonlist2">
					   <td class="bgcolor" ><strong>&nbsp;</strong>工程单位：</td>
					   <td colspan="3">
						   <input type="text" id="unit" name="unit" value="${project.unit}" <%--readonly--%> style="width:100%;"/>
					   </td>
				   </tr>
		           <tr class="sonlist2">
		               <td class="bgcolor" style="text-align: center;background: #cce8cf;width:12%;" ></td>
		               <td style="text-align: center;background: #cce8cf;font-weight: bold" >项建估算指标</td>
		               <td style="text-align: center;background: #cce8cf;font-weight: bold" >可研估算指标</td>
		               <td style="text-align: center;background: #cce8cf;font-weight: bold" >概算指标</td>
		           </tr>
		           <tr class="sonlist2">
		               <td class="bgcolor"><strong>&nbsp;</strong>工程规模：</td>
		               <td>
		                   <input type="text" id="xjGsProjectScale" name="xjGsProjectScale"  value="${project.xjGsProjectScale }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="width:100%;height:30px;line-height: 30px">
		               </td>
		               <td><input type="text" id="kyGsProjectScale" name="kyGsProjectScale"  value="${project.kyGsProjectScale }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="width:100%;height:30px;line-height: 30px"></td>
		               <td>
		                   <input type="text" id="gsProjectScale" name="gsProjectScale"  value="${project.gsProjectScale }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="width:100%;height:30px;line-height: 30px">
		               </td>
		           </tr>
		           <tr class="sonlist2" id="cost">
		               <td class="bgcolor"><strong>&nbsp;</strong>单方造价：</td>
		               <td>
		                   <input type="text"  id="xjGsCost" name="xjGsCost"  value="${project.xjGsCost }" readonly class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
		                    <input type="text" name="costUnit" value="${project.costUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
		                    <div style="clear: both"></div>
		               </td>
		               <td><input type="text" id="kyGsCost" name="kyGsCost"  value="${project.kyGsCost }" readonly class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
		                    <input type="text" name="" value="${project.costUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
		                    <div style="clear: both"></div>
		               </td>
		               <td>
		                   <input type="text" id="gsCost" name="gsCost"  value="${project.gsCost }" readonly class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
		                    <input type="text" name="" value="${project.costUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
		                    <div style="clear: both"></div>
		               </td>
		           </tr>
		           <tr class="sonlist2" id="architectrural">
		               <td class="bgcolor"><strong>&nbsp;</strong>建筑工程：</td>
		               <td>
		                   <input type="text" name="xjGsArchitectural"  value="${project.xjGsArchitectural }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
		                    <input type="text" name="architecturalUnit" value="${project.architecturalUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
		                    <div style="clear: both"></div>
		               </td>
		               <td><input type="text" name="kyGsArchitectural"  value="${project.kyGsArchitectural }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2" style="text-align:right;width:70%;height:30px;line-height: 30px">
		                    <input type="text" name="" value="${project.architecturalUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
		                    <div style="clear: both"></div>
		               </td>
		               <td>
		                   <input type="text" name="gsArchitectural"  value="${project.gsArchitectural }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
		                    <input type="text" name="" value="${project.architecturalUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
		                    <div style="clear: both"></div>
		               </td>
		           </tr>
		           <tr class="sonlist2" id="installation">
					   <td class="bgcolor"><strong>&nbsp;</strong>安装工程：</td>
					   <td>
						   <input type="text" name="xjGsInstallation"  value="${project.xjGsInstallation }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
						   <input type="text" name="installationUnit" value="${project.installationUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
						   <div style="clear: both"></div>
					   </td>
					   <td><input type="text" name="kyGsInstallation"  value="${project.kyGsInstallation }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
						   <input type="text" name="" value="${project.installationUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
						   <div style="clear: both"></div>
					   </td>
					   <td>
						   <input type="text" name="gsInstallation"  value="${project.gsInstallation }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
						   <input type="text" name="" value="${project.installationUnit }" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
						   <div style="clear: both"></div>
					   </td>
				   </tr>
				   <tr class="sonlist2" id="outdoorpt">
					   <td class="bgcolor"><strong>&nbsp;</strong>室外配套：</td>
					   <td>
						   <input type="text" name="xjGsInstallation"  value="${project.outdoorCost}"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
						   <input type="text" name="outdoorUnit" value="${project.outdoorUnit}" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
						   <div style="clear: both"></div>
					   </td>
					   <td><input type="text" name="kyGsInstallation"  value="${project.outdoorKyGs}"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
						   <input type="text" name="" value="${project.outdoorUnit}" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
						   <div style="clear: both"></div>
					   </td>
					   <td>
						   <input type="text" name="gsInstallation"  value="${project.outdoorGsCost}"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  style="text-align:right;width:70%;height:30px;line-height: 30px">
						   <input type="text" name="" value="${project.outdoorUnit}" readonly class="unitStyle" style="width:28%;display: inline-block;float:right;height:30px;line-height: 30px"/>
						   <div style="clear: both"></div>
					   </td>
				   </tr>
		            <shiro:hasPermission name="project:update">
                    <c:if test="${(param.editFlag eq 'y')}">
                    <tr style="height:40px">
                        <td colspan="4" style="text-align: center">
                            <input type="button" id="save" class="btn btn-primary"  value="保存"/>&nbsp;&nbsp;
                            <input type="button" id="cancel" class="btn btn-success" onclick="top.closeWindow('项目指标信息修改')" value="取消"/>
                        </td>
                    </tr>
                    </c:if>
                    </shiro:hasPermission>
                </tbody>
            </table>
    </form>
	<div id="tt" style="width:100%;">
		<div title="单位工程汇总表" class="subWrap" >
			<table id="unitProjectAllList" style="width: 100%">
			</table>
		</div>
		<div title="钢筋、混凝土、模板含量表" class="subWrap">
			<table id="contentTable" style="width: 100%">
			</table>
		</div>
	</div>
	<div class="tab-tools" style="border-left:none;border-right:none;border-top:none;padding:6px 0 0 0 ;display: none">
		<input type="button" name="reset" value="导出" class="btn btn-danger" onclick="InExport()">&nbsp;
	</div>
</div>
<div id="projectPersonDialog"></div>
<div id="dlg"></div>
<div id="dialog"></div>
<div id="editDialog"></div>
  <div id="project"></div>
   <div id="person"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/project/projectAccounts_add2.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/taskType.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/org-combotree-multiple.js"></script>
<script>
    function InExport(){
        window.location.href = "/costProject/costContentTemplate/InExport?id=${project.id}";
    }
    <!-- 禁用所有Form表单 -->
    var taskTypeId="";
    var projectType = '${project.projectClassificationId}';
    var orgId="";
    function disableForm(formId,isDisabled) {
        var attr="disable";
        if(!isDisabled){
            attr="enable";
        }
        $("form[id='"+formId+"'] :text").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] textarea").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] select").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] :radio").attr("readonly",isDisabled);
        $("form[id='"+formId+"'] :checkbox").attr("readonly",isDisabled);
        $("#save").hide();
        $("#cancel").hide();
    }
    var editFlag="${param.editFlag}";
	$(function(){
		if(editFlag=="n"){
			disableForm("formId",true);
		}
		 var deliveryFlag=$("#allProcess").val();
		  	if(deliveryFlag=='是'){
				$("#unitTr").show();
			}else if(deliveryFlag=='否'){
				$("#unitTr").hide();
			}
	});
 //审价单位
   function unitEnterprise() {
       $('#project').dialog({
           title: '审价单位',
           width: 805,
           height: 520,
           closed: false,
           cache: false,
           top:50,
           content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_project_containEnterprise'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
           modal: true
       });
   }
   //项目负责人
   function person() {
       $('#person').dialog({
           title: '系统用户',
           width: 805,
           height: 520,
           closed: false,
           cache: false,
           top:50,
           content:"<iframe name=\"fileFramePro\" frameborder=\"0\" src="+'/forward_project_person'+" scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
           modal: true
       });
   }
   function isAllProcess(){
		  var value = $("#allProcess option:selected").val();
		  if(value=="是"){
			  $("#unitTr").show();
		  }
		  else{
			  $("#unitTr").hide();
			  $("#auditPriceUnit").val("");
		  }
	}
	//合同文件导出
	function exportHt(){
   	window.location.href = "${path}/costContract/exportContract?projectId=${project.id }";
   }
	//往来文件导出
	function exportWl(){
   	window.location.href = "${path}/costDocument/exportDocument.do";
   }
   //审价任务导出
	function exportSj(){
   	window.location.href = "${path}/costContract/exportTask.do";
   }
	
	//自动刷新
	function resetMethod(tableId){
	$('#'+tableId).datagrid('options').queryParams = {
		
    };
	$('#' + tableId).datagrid("reload");
	}
	var flag1=false;var flag2=false;
	  $(function () {
	      $(".wrap").height(tableHeight());

	      $(".switchBtn1").click(function(){

	          $(".sonlist1").toggle(500);

	          if(!flag1){
	              flag1=true;
	              $(this).addClass("active");
	              $("#unitTr").hide();
	          }else{
	              flag1=false;
	              $(this).removeClass("active");
	              isAllProcess();

	          }
	          setHeight();
	      });
	      $(".switchBtn2").click(function(){
	          $(".sonlist2").toggle(500);
	          if(!flag2){
	              flag2=true;
	              $(this).addClass("active");
	          }else{
	              flag2=false;
	              $(this).removeClass("active");
	          }
	          setHeight();
	      });
	      $("#xjGsProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	  var xjGsProjectScale=$('#xjGsProjectScale').val();
	          if(Number(xjGsProjectScale)!=0){
		       	  var xjGsJe="${project.xjGsJe}";
		          var xjGsCost=Number(xjGsJe)*Number(10000)/Number(xjGsProjectScale);
		          $('#xjGsCost').numberbox('setValue',xjGsCost);
	          }
	      });
	      $("#kyGsProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	var kyGsProjectScale=$('#kyGsProjectScale').val();
	          if(Number(kyGsProjectScale)!=0){
		       	  var kyGsJe="${project.kyGsJe}"
		          var kyGsCost=Number(kyGsJe)*Number(10000)/Number(kyGsProjectScale);
		          $('#kyGsCost').numberbox('setValue',kyGsCost);
	          }
	      });
	      $("#gsProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	var gsProjectScale=$('#gsProjectScale').val();
	          if(Number(gsProjectScale)!=0){
		       	  var sumGsJe="${project.sumGsJe}"
		          var gsCost=Number(sumGsJe)/Number(gsProjectScale);
		          $('#gsCost').numberbox('setValue',gsCost);
	          }
	      });
	  });
	function setHeight(){
	      if(flag2==true){
	          $('#unitProjectAllList').treegrid('resize', {
	              height: $(window).height()-96-100
	          });
	          $('#contentTable').datagrid('resize', {
	              height:  $(window).height()-96-100
	          });
	      }else{
	          $('#unitProjectAllList').treegrid('resize', {
	              height: 500
	          });
	          $('#contentTable').datagrid('resize', {
	              height: 500
	          });
	      }
	  }
	  function changeUnit(){
		  var unit = $("#unit option:selected").val(); 
		  if(unit!=''){
			  $("#cost .unitStyle").val("元/"+unit);
			  $("#architectrural .unitStyle").val("元/"+unit);
			  $("#installation .unitStyle").val("元/"+unit);
			  $("#rebar .unitStyle").val("t/"+unit);
			  $("#concrete .unitStyle").val("m3/"+unit);
			  $("#template .unitStyle").val("m2/"+unit);
			  $("#scaffolding .unitStyle").val("m2/"+unit);
		  }
	  }
</script>
</body>
</html>