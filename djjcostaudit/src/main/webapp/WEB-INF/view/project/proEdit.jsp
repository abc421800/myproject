<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目台账编辑</title>
      <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <!-- 审批新建页面样式 -->

<style>
.combo-panel
 {

    overflow: auto;

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
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" >
                <tbody>
                	 <tr style="height: 48px">
		                <td colspan="6" style="text-align: left;position: relative">
		                    <span class="title">项目基本情况</span>
                            <input type="button"  class="btn btn-primary" onclick="editInfo('${project.id}')"  value="项目指标信息修改"/>
                            <a href="javascript:void(0);" class="switchBtn1"></a>
                            <%--
		                    <a href="javascript:void(0)" class="moreBtn">
		                        <i class="icon-edit_new"></i>
		                        <span>修改</span>
		                    </a>

		                --%></td>
		            </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor" style="width:18%;" ><strong>*</strong>项目名称：</td>
                        <td colspan="5">
                            <input type="text" id="name" name="name" value="${project.name }" placeholder="请输入文件标题" style="width:100%;"/>
                        </td>
                    </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor" ><strong>*</strong>项目编号：</td>
                        <td style="width:15%;" >
                            <input type="text" id="code" name="code" value="${project.code }" placeholder="请输入文号" style="width:100%;"/>
                        </td>
                        <td class="bgcolor" style="width:16%;" ><strong>*</strong>项目业主：</td>
                        <td style="text-align: center;">
                            <input type="text" id="projectOwner" name="projectOwner"  value="${project.projectOwner }" placeholder="请输入项目业主" style="width:100%;"/>
                        </td>
                         <td class="bgcolor"style="width:16%;" ><strong>&nbsp;</strong>项目业主联系方式：</td>
                        <td style="width:20%;" >
                            <input type="text"  name="projectOwnerPhone" value="${project.projectOwnerPhone }" placeholder="示例：“李某：13699886655”" style="width:100%;">
                        </td>
                    </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor"><strong>&nbsp;</strong>立项批复文号：</td>
                        <td>
                            <input type="text" name="lxpfNumber" value="${project.lxpfNumber }" placeholder="请输入立项批复文号" style="width:100%;"/>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>计划竣工日期：</td>
                        <td>
                            <input class="Wdate search_text_form" name="planOverTimeStr" value="${fn:substring(project.planOverTimeStr, 0, 10)}" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                         <td class="bgcolor" ><strong>*</strong>项目负责人：</td>
                        <td >
                           <input type="text" id="personLiableId" name="personLiableId" style="margin-top:5px;width:80%;" value="${project.personLiableId }" style="width:80%;" readonly/>
                         <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="person();">
                    	<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                    	</a>
                        </td>
                    </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor"><strong>&nbsp;</strong>当前节点：</td>
                        <td>
                            <input type="text" id="projectNode" name="projectNode" value="${project.projectNode }" disabled style="width:100%;"/>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>节点控制日期：</td>
                        <td>
                            <input class="Wdate search_text_form" id="nodeTimeStr" name="nodeTimeStr" type="text" value="${fn:substring(project.nodeTimeStr, 0, 10)}" disabled  onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <td class="bgcolor"><strong>&nbsp;</strong>项建估算批复(万元)：</td>
                        <td>
                            <input type="text" id="xjGsJe" name="xjGsJe" value="${project.xjGsJe }"   class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入项建估算金额" style="width:100%;height:30px;line-height: 30px">
                        </td>

                    </tr>
                     <tr class="sonlist1">

                        <td class="bgcolor" ><strong>&nbsp;</strong>项建估算工程费(万元)：</td>
                        <td>
                            <input type="text" id="xjGsGcf" name="xjGsGcf"  value="${project.xjGsGcf }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入项建估算工程费" style="width:100%;height:30px;line-height: 30px">
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>项建估算二类费用(万元)：</td>
                        <td>
                            <input type="text" id="xjGsElfy" name="xjGsElfy" value="${project.xjGsElfy }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入项建估算二类费用" style="width:100%;height:30px;line-height: 30px"/>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>项建估算三类费用(万元)：</td>
                        <td>
                            <input type="text" id="xjGsSlfy" name="xjGsSlfy"  value="${project.xjGsSlfy }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入项建估算三类费用" style="width:100%;height:30px;line-height: 30px">
                        </td>
                    </tr>
                    <tr class="sonlist1">
                         <td class="bgcolor"><strong>&nbsp;</strong>可研估算批复(万元)：</td>
                        <td>
                            <input type="text" id="kyGsJe" name="kyGsJe"  value="${project.kyGsJe }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入可研估算金额" style="width:100%;height:30px;line-height: 30px">
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>可研估算工程费(万元)：</td>
                        <td>
                            <input type="text" id="kyGsGcf" name="kyGsGcf"  value="${project.kyGsGcf }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入可研估算工程费" style="width:100%;height:30px;line-height: 30px">
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>可研估算二类费用(万元)：</td>
                        <td>
                            <input type="text" id="kyGsElfy" name="kyGsElfy"  value="${project.kyGsElfy }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入可研估算二类费用" style="width:100%;height:30px;line-height: 30px">
                        </td>
                    </tr>
                    <tr class="sonlist1">

                         <td class="bgcolor" ><strong>&nbsp;</strong>可研估算三类费用(万元)：</td>
                        <td>
                            <input type="text" id="kyGsSlfy" name="kyGsSlfy" value="${project.kyGsSlfy }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2" placeholder="请输入可研估算三类费用" style="width:100%;height:30px;line-height: 30px"/>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>概算金额(元)：</td>
                        <td>
                        <c:choose>
                        	<c:when test="${gaisEdit eq false }">
                        	<input type="text" id="sumGsJe" name="sumGsJe" readonly value="${project.sumGsJe }"   class="easyui-numberbox" data-options="groupSeparator:',',precision:2" placeholder="请输入概算金额" style="width:100%;height:30px;line-height: 30px">
                        	</c:when>
                        	<c:otherwise>
                        		<input type="text" id="sumGsJe" name="sumGsJe"  value="${project.sumGsJe }"   class="easyui-numberbox" data-options="groupSeparator:',',precision:2" placeholder="请输入概算金额" style="width:100%;height:30px;line-height: 30px">
                        	</c:otherwise>
                        </c:choose>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>概算工程费(元)：</td>
                        <td>
                         <c:choose>
                        	<c:when test="${gaisEdit eq false }">
                        	<input type="text" id="gsGcf" name="gsGcf" readonly value="${project.gsGcf }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入概算工程费" style="width:100%;height:30px;line-height: 30px">
                        	</c:when>
                        	<c:otherwise>
                            <input type="text" id="gsGcf" name="gsGcf"  value="${project.gsGcf }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入概算工程费" style="width:100%;height:30px;line-height: 30px">
                            </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                    <tr class="sonlist1">

                         <td class="bgcolor"><strong>&nbsp;</strong>概算二类费用(元)：</td>
                        <td>
                         <c:choose>
                        <c:when test="${gaisEdit eq false }">
                        	<input type="text" id="gsElfy" name="gsElfy" readonly value="${project.gsElfy }" class="easyui-numberbox" data-options="groupSeparator:',',precision:2" placeholder="请输入概算二类费用" style="width:100%;height:30px;line-height: 30px"/>
                        	</c:when>
                        	<c:otherwise>
                            <input type="text" id="gsElfy" name="gsElfy" value="${project.gsElfy }" class="easyui-numberbox" data-options="groupSeparator:',',precision:2" placeholder="请输入概算二类费用" style="width:100%;height:30px;line-height: 30px"/>
                             </c:otherwise>
                        </c:choose>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>概算三类费用(元)：</td>
                        <td>
                         <c:choose>
                        <c:when test="${gaisEdit eq false }">
                        	 <input type="text" id="gsSlfy" name="gsSlfy" readonly value="${project.gsSlfy }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入概算三类费用" style="width:100%;height:30px;line-height: 30px">
                        	</c:when>
                        	<c:otherwise>
                            <input type="text" id="gsSlfy" name="gsSlfy"  value="${project.gsSlfy }"  class="easyui-numberbox" data-options="groupSeparator:',',precision:2"  placeholder="请输入概算三类费用" style="width:100%;height:30px;line-height: 30px">
                             </c:otherwise>
                        </c:choose>
                        </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>项目管理协议书：</td>
                        <td>
                            <select name="projectManagementAgreement" class="custom-select">
                            	<option value="">请选择</option>
                                <c:forTokens items="未签,已签,无需签订" delims="," var="i">
                                	<option <c:if test="${i eq project.projectManagementAgreement }">selected = "selected"</c:if>  value="${i}">${i}</option>
                                </c:forTokens>
                            </select>
                        </td>
                    </tr>
                       <tr class="sonlist1">
                    	<td class="bgcolor"><strong>*</strong>是否属于全过程项目：</td>
                        <td>
                            <select name="allProcess" class="custom-select" id="allProcess" style="width:28%" onchange="isAllProcess();">
                                <option value="是" <c:if test="${'是' eq project.allProcess }">selected = "selected"</c:if> >是</option>
                                <option value="否" <c:if test="${'否' eq project.allProcess }">selected = "selected"</c:if>>否</option>
                            </select>
                        </td>
                       <td class="bgcolor"><strong>*</strong>项目分类：</td>
                        <td colspan="2">
                            <select id="projectClassificationId" style="width:100%;" name="projectClassificationId" class="custom-select">
                            </select>
                        </td>
                        <td></td>
                    </tr>
                     <tr class="sonlist1">
                         <td class="bgcolor" ><strong>*</strong>工程单位：</td>
                         <td >
                             <select id="unit" name="unit" class="custom-select"onchange="changeUnit();">
                                 <option value="">请选择</option>
                                 <option value="m2" <c:if test="${'m2' eq project.unit }">selected = "selected"</c:if>>m2</option>
                                 <option value="km" <c:if test="${'km' eq project.unit }">selected = "selected"</c:if>>km</option>
                             </select>
                         </td>
                         <td class="bgcolor"><strong>*</strong>工程所在地：</td>
                         <td style="text-align: center;" >
                             <select id="area" name="area" class="custom-select">
                                 <option value="">请选择</option>
                                 <option  <c:if test="${'广州市' eq project.area }">selected = "selected"</c:if> value="广州市">广州市</option>
                                 <option  <c:if test="${'越秀区' eq project.area }">selected = "selected"</c:if>value="越秀区">越秀区</option>
                                 <option  <c:if test="${'海珠区' eq project.area }">selected = "selected"</c:if> value="海珠区">海珠区</option>
                                 <option  <c:if test="${'荔湾区' eq project.area }">selected = "selected"</c:if>value="荔湾区">荔湾区</option>
                                 <option  <c:if test="${'天河区' eq project.area }">selected = "selected"</c:if> value="天河区">天河区</option>
                                 <option  <c:if test="${'白云区' eq project.area }">selected = "selected"</c:if>value="白云区">白云区</option>
                                 <option  <c:if test="${'黄埔区' eq project.area }">selected = "selected"</c:if>value="黄埔区">黄埔区</option>
                                 <option  <c:if test="${'花都区' eq project.area }">selected = "selected"</c:if>value="花都区">花都区</option>
                                 <option  <c:if test="${'番禺区' eq project.area }">selected = "selected"</c:if>value="番禺区">番禺区</option>
                                 <option  <c:if test="${'南沙区' eq project.area }">selected = "selected"</c:if>value="南沙区">南沙区</option>
                                 <option  <c:if test="${'从化区' eq project.area }">selected = "selected"</c:if>value="从化区">从化区</option>
                                 <option  <c:if test="${'萝岗区' eq project.area }">selected = "selected"</c:if>value="萝岗区">萝岗区</option>
                                 <option  <c:if test="${'增城区' eq project.area }">selected = "selected"</c:if>value="增城区">增城区</option>
                             </select>
                         </td>
                         <td class="bgcolor"><strong>&nbsp;</strong>建设地点：</td>
                         <td>
                             <input type="text" name="projectLocation"  value="${project.projectLocation }" placeholder="请输入详细地址" style="width:100%;">
                         </td>
                     </tr>
                     <tr id="unitTr" class="sonlist1">
                        <td class="bgcolor"><strong>*</strong>审价单位：</td>
                        <td colspan="5">
                           <input type="text" id="auditPriceUnit" name="auditPriceUnit" value="${project.auditPriceUnit }" style="margin-top:5px;width:80%;" readonly/>
                           <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="unitEnterprise()">
                    		<img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt="" width="19"height="19">
                   		 </a>
                        </td>
                    </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor"><strong>&nbsp;</strong>结算评审方式：</td>
                        <td colspan="5">
                            <input type="text" name="settlementReivewMethod" value="${project.settlementReivewMethod }" placeholder="请输入结算评审方式" style="width:100%;"/>
                        </td>

                    </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor"><strong>&nbsp;</strong>结算方式约定：</td>
                        <td  colspan="5">
                            <input type="text" name="settlementAgreement"  value="${project.settlementAgreement }" placeholder="请输入结算方式约定" style="width:100%;"/>
                        </td>
                      </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor"><strong>&nbsp;</strong>项目概况：</td>
                        <td colspan="5">
                            <textarea name="projectIntroduction" cols="5" rows="4">${project.projectIntroduction }</textarea>
                        </td>
                    </tr>
                    <tr class="sonlist1">
                        <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                        <td colspan="5">
                            <textarea name="description" cols="5" rows="2">${project.description }</textarea>
                        </td>
                    </tr>
		            <shiro:hasPermission name="project:update">
                    <c:if test="${(param.editFlag eq 'y' and user.name eq project.creator) or (param.editFlag eq 'y' and fn:contains(project.personLiableId, user.name)) or dataUpdate eq true }">
                    <tr style="height:40px">
                        <td colspan="6" style="text-align: center">
                            <input type="button" id="save" class="btn btn-primary"  value="保存"/>&nbsp;&nbsp;
                            <input type="button" id="cancel" class="btn btn-success" onclick="top.closeWindow('项目修改')" value="取消"/>
                        </td>
                    </tr>
                    </c:if>
                    </shiro:hasPermission>
                </tbody>
            </table>
    </form>
    <div id="tt" style="width:100%;">
        <div title="项目干系人" class="subWrap" >
            <table id="projectPerson" style="width: 100%">
            </table>
            <div id="tit1" >
            	 <c:if test="${(param.editFlag eq 'y' and user.name eq project.creator) or (param.editFlag eq 'y' and fn:contains(project.personLiableId, user.name)) or dataUpdate eq true }">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getProjectPersonDialog();">添加</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="updateStakeholder();">修改</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delStakeholder();">删除</a>
                </c:if>
                     <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>

        </div>
        <div title="节点工期" class="subWrap">
            <div class="con" style="overflow-y: auto">
                <form id="form1" action="" method="post" novalidate="novalidate">
                  <c:if test="${(param.editFlag eq 'y' and user.name eq project.creator) or (param.editFlag eq 'y' and fn:contains(project.personLiableId, user.name))  or dataUpdate eq true  }">
                    <div class="row margb clearfix">
                        <div class="buttonbox pull-left">
                            <div class="btn-group">
                                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="false" onclick="updateNode();">保存</a>
                            </div>
                        </div>
                    </div>
                    </c:if>
                     <div class="row">
                    <div class="tabox table-responsive table-wrap">
                        <table class="table table-hover table-striped table-bordered table-condensed" >
                            <tbody>
                                <tr>
                                    <th width="8%">序号</th>
                                    <th width="25%">节点名称</th>
                                    <th width="8%">当前节点</th>
                                    <th width="20%">节点日期</th>
                                    <th width="30%">备注</th>
                                </tr>
                                <c:forEach var="node" items="${nodeList }">
                                	 <tr>
                                    <td class="text-center">
                                       ${node.num }
                                    </td>
                                    <td class="text-center">${node.name }</td>
                                    <td class="text-center">
                                    	<input type="hidden" id="${node.id }_nodeName" value="${node.name }"/>
                                        <input name="nodeId"  value="${node.id }" type="radio" <c:if test="${node.name eq project.projectNode }">checked</c:if>/>
                                    </td>
                                    <td class="text-center"> <input class="Wdate search_text_form" id="${node.id }_nodeTimeStr" value="${fn:substring(node.nodeTimeStr, 0, 10)}"  onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/></td>

                                    <td class="text-center"><input class="form-control input-sm" id="${node.id }_description" name="description"  value="${node.description }" tabindex="1" type="text"/></td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <div title="项目指标信息" class="subWrap">
            <table id="proIndicators" style="width: 100%">
            </table>
            <div id="tit4">
                <c:if test="${(param.editFlag eq 'y' and user.name eq project.creator) or (param.editFlag eq 'y' and fn:contains(project.personLiableId, user.name)) or dataUpdate eq true }">
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog('proIndicators');">添加文件夹</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog('proIndicators');">修改文件夹名称</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile('proIndicators');">删除</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile('proIndicators');">上传附件</a>
                    <input type="button" name="reset" value="新增项目指标导入模版下载" class="btn btn-info" onclick="downloadTemplateZb();">
                </c:if>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>
        </div>
        <div title="项目资料" class="subWrap">
            <table id="projectMaterial" style="width: 100%">
            </table>
            <div id="tit3" >
             <c:if test="${(param.editFlag eq 'y' and user.name eq project.creator) or (param.editFlag eq 'y' and fn:contains(project.personLiableId, user.name)) or dataUpdate eq true }">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="getDialog(xmzl);">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="getEditDialog(xmzl);">修改文件夹名称</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="delFile(xmzl);">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false" onclick="uploadFile(xmzl);">上传附件</a>
                </c:if>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>
        </div>
        <!--<div title="子项目" class="subWrap">-->

        <!--</div>-->
        <div title="相关合同" class="subWrap">
            <table id="relatedContracts" style="width: 100%">
            </table>
            <div id="tit5">
                <div class="row">
                <form action="">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">合同名称：</label>
                                <input type="text" id="contractName" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">合同编号：</label>
                                <input type="text" id="contractCode" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">合同类型：</label>
                                <select class="form-control input-sm" id="contractType" style="width: 100px" onchange="contractSelectSubmit();">
                                    <option value="">请选择</option>
                                    <option value="采购" >采购</option>
                                    <option value="服务" >服务</option>
                                    <option value="勘察设计" >勘察设计</option>
                                    <option value="工程监理" >工程监理</option>
                                    <option value="检测" >检测</option>
                                    <option value="施工" >施工</option>
                                    <option value="造价咨询" >造价咨询</option>
                                    <option value="招标代理" >招标代理</option>
                                    <option value="赞助" >赞助</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">合同负责人：</label>
                                <input type="text" id="personLiable4" placeholder="请输入关键字" class="form-control input-sm"/>
                            </div>
                            <div class="form-group">
                                <label for="">签订时间：</label>
                                <input id="contractSigningTimeStart" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'contractSigningTimeEnd\')}'})">
                                <label for="">至</label>
                                <input id="contractSigningTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'contractSigningTimeStart\')}'})">
                            </div>
                             <div class="form-group">
				                <label for="">合同执行部门：</label>
				                <input type="text" id="orgCombotreeSelect" style="width:200px;" value="" placeholder="请输入关键字" class="form-control input-sm">
				            </div>
				             <div class="form-group">
                                <label for="">合同乙方：</label>
                                <input type="text" id="partyB" placeholder="请输入关键字" class="form-control input-sm"/>
                            </div>
                            <div class="form-group">
                                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="contractSelectSubmit();">&nbsp;
                                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="resetMethod('relatedContracts');">&nbsp;
                                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportHt();">&nbsp;
                            </div>
                           <div class="form-group">
                                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
                            </div>

                        </div>
                    </div>
                    </form>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn1"></a>
                </div>
            </div>
        </div>
        <shiro:hasPermission name="project:WLSJ">
        <div title="往来文件" class="subWrap">
            <table id="wlfile" style="width: 100%">
            </table>
            <div id="tit6">
                <div class="row">
                <form action="">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">文号：</label>
                                <input type="text" id="symbol" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">文件标题：</label>
                                <input type="text" id="documentName" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">往来性质：</label>
                                <select class="form-control input-sm" id="comeGoFlag" style="width: 100px" onchange="documentSelectSubmit();">
                                    <option value="">请选择</option>
                                    <option value="来" >来</option>
                                    <option value="往" >往</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">是否审价：</label>
                                <select class="form-control input-sm" id="auditPriceFlag" style="width: 100px" onchange="documentSelectSubmit();">
                                    <option value="">请选择</option>
                                    <option value="是" >是</option>
                                    <option value="否" >否</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">往来单位：</label>
                                <input type="text"  id="comeGoUnit" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>

                            <div class="form-group">
                                <label for="">负责人：</label>
                                <input type="text" id="personLiable5" placeholder="请输入关键字" class="form-control input-sm"/>
                            </div>
                            <div class="form-group">
                                <label for="">文件日期：</label>
                                <input id="documentTimeStart" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                                <label for="">至</label>
                                <input id="documentTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                            </div>
                            <div class="form-group">
                                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="documentSelectSubmit();">&nbsp;
                                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="resetMethod('wlfile');">&nbsp;
                                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportWl();">&nbsp;
                            </div>
                            <div class="form-group">
                                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
                            </div>

                        </div>
                    </div>
                    </form>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn2"></a>
                </div>
            </div>
        </div>
        <div title="审价任务" class="subWrap">
            <table id="sjTask" style="width: 100%">
            </table>
            <div id="tit7">
                <div class="row">
                    <form action="">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">审价编号：</label>
                                <input type="text" id="taskCode" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">审价任务名称：</label>
                                <input type="text" id="taskName" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">审价类型：</label>
                                <select id="auditPriceType"  class="custom-select" style="width: 200px"></select>
                                <!-- <select class="form-control input-sm" id="taskType" onchange="taskSelectSubmit();">
                                    <option value="">请选择</option>
                                    <option value="估概预结" >估概预结</option>
                                    <option value="合同变更" >合同变更</option>
                                    <option value="单价审核" >单价审核</option>
                                </select> -->
                            </div>


                            <div class="form-group">
                                <label for="">审价任务负责人：</label>
                                <input type="text" id="personLiable6" placeholder="请输入关键字" class="form-control input-sm"/>
                            </div>
                            <div class="form-group">
                                <label for="">审价单位：</label>
                                <input type="text" id="taskUnit" value="" placeholder="请输入关键字" class="form-control input-sm"/>
                            </div>
                            <div class="form-group">
                                <label for="">当前状态：</label>
                                <select class="form-control input-sm" id="status" style="width: 100px" onchange="taskSelectSubmit();">
                                    <option value="">请选择</option>
				                    <option value="新建" >新建</option>
				                    <option value="办内审核中" >办内审核中</option>
				                    <option value="办内审核完" >办内审核完</option>
				                    <option value="退审" >退审</option>
				                    <option value="审结" >审结</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">定案文号：</label>
                                <input type="text" id="finalizeNumber" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="">协调：</label>
                                <select class="form-control input-sm" style="width: 100px" id="coordinateFlag" onchange="taskSelectSubmit();">
                                    <option value="">请选择</option>
                                    <option value="是" >是</option>
                                    <option value="否" >否</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">创建日期：</label>
                                <input id="createTimeStart" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                                <label for="">至</label>
                                <input id="createTimeEnd" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                            </div>
                            <div class="form-group">
                                <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="taskSelectSubmit();">&nbsp;
                                <input type="button" name="reset" value="重置" class="btn btn btn-success" onclick="resetMethod('sjTask');">&nbsp;
                                <input type="button" name="reset" value="导出" class="btn btn-danger" onclick="exportSj();">&nbsp;
                            </div>
                            <div class="form-group">
                                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
                            </div>

                        </div>
                    </div>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn3"></a>
                    </form>
                </div>
            </div>
        </div>
        </shiro:hasPermission>
    </div>
</div>
<div id="projectPersonDialog"></div>
<div id="dlg"></div>
<div id="dialog"></div>
<div id="editDialog"></div>
  <div id="project"></div>
   <div id="person"></div>
<script src="${pageContext.request.contextPath}/res/js/cost/project/projectAccounts_add.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/taskType.js"></script>
<script src="${pageContext.request.contextPath}/res/js/cost/contract/org-combotree-multiple.js"></script>
<script>
    <!-- 禁用所有Form表单 -->
    var taskTypeId="";
    var projectType = '${project.projectClassificationId}';
    var projectId = '${project.id}';
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
   function updateNode(){
	   //$.messager.progress();
	  var id= $('input[name="nodeId"]:checked').val();
	  var nodeName = $("#"+id+"_nodeName").val();
	  var nodeTimeStr = $("#"+id+"_nodeTimeStr").val();
	  var description = $("#"+id+"_description").val();
	  $("#projectNode").val(nodeName);
	  $("#nodeTimeStr").val(nodeTimeStr);
	  $.ajax({
          url: '/costProjectPeriod/updateCostProjectPeriod.do',
          type: 'POST',
          data: {'id': id,
        	  'nodeTimeStr':nodeTimeStr,
        	  'description':description
        	  },
          traditional: true,
      	success: function(result){
     		  $.messager.progress('close');
			  if(result.status==200){
			  	window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
			  }else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			  }
		  }
      });
   }
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
		       	  var xjGsJe=$('#xjGsJe').val();
		          var xjGsCost=Number(xjGsJe)*Number(10000)/Number(xjGsProjectScale);
		          $('#xjGsCost').numberbox('setValue',xjGsCost);
	          }
	      });
	      $("#kyGsProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	var kyGsProjectScale=$('#kyGsProjectScale').val();
	          if(Number(kyGsProjectScale)!=0){
		       	  var kyGsJe=$('#kyGsJe').val();
		          var kyGsCost=Number(kyGsJe)*Number(10000)/Number(kyGsProjectScale);
		          $('#kyGsCost').numberbox('setValue',kyGsCost);
	          }
	      });
	      $("#gsProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	var gsProjectScale=$('#gsProjectScale').val();
	          if(Number(gsProjectScale)!=0){
		       	  var sumGsJe=$('#sumGsJe').val();
		          var gsCost=Number(sumGsJe)/Number(gsProjectScale);
		          $('#gsCost').numberbox('setValue',gsCost);
	          }
	      });
	      $("#kzjProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	var kzjProjectScale=$('#kzjProjectScale').val();
	          if(Number(kzjProjectScale)!=0){
		       	  var sumKzjJe='${project.sumKzjJe}';
		          var kzjCost=Number(sumKzjJe)/Number(kzjProjectScale);
		          $('#kzjCost').numberbox('setValue',kzjCost);
	          }
	      });
	      $("#htjProjectScale").next("span").children().first().blur(function(){
	          //blur事件处理代码
	      	  var htjProjectScale=$('#htjProjectScale').val();
	          if(Number(htjProjectScale)!=0){
		       	  var sumHtjJe='${project.sumHtjJe}';
		          var htjCost=Number(sumHtjJe)/Number(htjProjectScale);
		          $('#htjCost').numberbox('setValue',htjCost);
	          }
	      });

	  });
	function setHeight(){
	      if(flag1==true&&flag2==true){
	          $('#projectMaterial').treegrid('resize', {
	              height: $(window).height()-96-100
	          });
	          $('#relatedContracts').datagrid('resize', {
	              height:  $(window).height()-96-100
	          });
	          $('#wlfile').datagrid('resize', {
	              height:  $(window).height()-96-100
	          });
	          $('#sjTask').datagrid('resize', {
	              height:  $(window).height()-96-100
	          });
	          $('#projectPerson').datagrid('resize', {
	              height:  $(window).height()-96-100
	          });
	          $('.con').height( $(window).height()-96-100);

	      }else{
	          $('#projectMaterial').treegrid('resize', {
	              height: 500
	          });
	          $('#relatedContracts').datagrid('resize', {
	              height: 500
	          });
	          $('#wlfile').datagrid('resize', {
	              height: 500
	          });
	          $('#sjTask').datagrid('resize', {
	              height: 500
	          });
	          $('#projectPerson').datagrid('resize', {
	              height: 500
	          });
	          $('.con').height(500);
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
<script>
	//----项建估算批复自动计算总值
    $("#xjGsGcf").numberbox({
        "onChange":function(){
            var num = $('#xjGsGcf').numberbox('getValue');
            var num2 = $('#xjGsElfy').numberbox('getValue');
            var num3 = $('#xjGsSlfy').numberbox('getValue');
            var sum=parseFloat(num)+parseFloat(num2)+parseFloat(num3);
            $('#xjGsJe').numberbox('setValue', sum.toFixed(2));
        }
    });
    $("#xjGsElfy").numberbox({
        "onChange":function(){
            var num = $('#xjGsGcf').numberbox('getValue');
            var num2 = $('#xjGsElfy').numberbox('getValue');
            var num3 = $('#xjGsSlfy').numberbox('getValue');
            var sum=parseFloat(num)+parseFloat(num2)+parseFloat(num3);
            $('#xjGsJe').numberbox('setValue', sum.toFixed(2));
        }
    });
    $("#xjGsSlfy").numberbox({
        "onChange":function(){
            var num = $('#xjGsGcf').numberbox('getValue');
            var num2 = $('#xjGsElfy').numberbox('getValue');
            var num3 = $('#xjGsSlfy').numberbox('getValue');
            var sum=parseFloat(num)+parseFloat(num2)+parseFloat(num3);
            $('#xjGsJe').numberbox('setValue', sum.toFixed(2));
        }
    });
    //----可研估算批复自动计算总值
    $("#kyGsGcf").numberbox({
        "onChange":function(){
            var num = $('#kyGsGcf').numberbox('getValue');
            var num2 = $('#kyGsElfy').numberbox('getValue');
            var num3 = $('#kyGsSlfy').numberbox('getValue');
            var sum=parseFloat(num)+parseFloat(num2)+parseFloat(num3);
            $('#kyGsJe').numberbox('setValue', sum.toFixed(2));
        }
    });
    $("#kyGsElfy").numberbox({
        "onChange":function(){
            var num = $('#kyGsGcf').numberbox('getValue');
            var num2 = $('#kyGsElfy').numberbox('getValue');
            var num3 = $('#kyGsSlfy').numberbox('getValue');
            var sum=parseFloat(num)+parseFloat(num2)+parseFloat(num3);
            $('#kyGsJe').numberbox('setValue', sum.toFixed(2));
        }
    });
    $("#kyGsSlfy").numberbox({
        "onChange":function(){
            var num = $('#kyGsGcf').numberbox('getValue');
            var num2 = $('#kyGsElfy').numberbox('getValue');
            var num3 = $('#kyGsSlfy').numberbox('getValue');
            var sum=parseFloat(num)+parseFloat(num2)+parseFloat(num3);
            $('#kyGsJe').numberbox('setValue', sum.toFixed(2));
        }
    });
    function editInfo(zjkClId) {
        // var json1 = {tabTitle: '项目指标信息修改', url: "/costProject/editZb?editFlag=y&projId="+zjkClId};
        // window.parent.refreshTab('项目指标信息修改', "/costProject/editZb?editFlag=y&projId="+zjkClId);
        var href = "/costProject/editZb?editFlag=y&projId="+zjkClId;
        var title = "项目指标信息修改";
        top.addTabGrid(title, href);
    }
	//----概算金额自动计算总值
</script>
</body>
</html>