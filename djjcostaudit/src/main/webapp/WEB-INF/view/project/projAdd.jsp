<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目添加</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/res/js/cost/project/category-combotree.js"></script>
    <!-- 审批新建页面样式 -->

    <style>
        .combo-panel {
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
            right: 80px;
        }

        .switchBtn1, .switchBtn2 {
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
            right: 20px;
            top: -1px;
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
    <form id="formId" method="post" action="/costProject/saveProject.do">
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0" style="margin-bottom:20px">
            <tbody>
            <tr style="height: 48px">
                <td colspan="6" style="text-align: left;position: relative">
                    <span class="title">项目基本情况</span>
                    <a href="javascript:void(0);" class="switchBtn1"></a>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>*</strong>项目名称：</td>
                <td colspan="5">
                    <input type="text" id="name" name="name" value="" placeholder="请输入文件标题" style="width:100%;"/>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor" style="width:18%;"><strong>*</strong>项目编号：</td>
                <td style="width:16%;">
                    <input type="text" id="code" name="code" value="" placeholder="请输入文号" style="width:100%;"/>
                </td>
                <td class="bgcolor" style="width:16%;"><strong>*</strong>项目业主：</td>
                <td style="text-align: center;">
                    <input type="text" id="projectOwner" name="projectOwner" value="" placeholder="请输入项目业主"
                           style="width:100%;"/>
                </td>
                <td class="bgcolor" style="width:16%;"><strong>&nbsp;</strong>项目业主联系方式：</td>
                <td style="width:17%;">
                    <input type="text" name="projectOwnerPhone" value="" placeholder="示例：“李某：13699886655”"
                           style="width:100%;">
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>&nbsp;</strong>立项批复文号：</td>
                <td>
                    <input type="text" name="lxpfNumber" value="" placeholder="请输入立项批复文号" style="width:100%;"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>计划竣工日期：</td>
                <td>
                    <input class="Wdate search_text_form" name="planOverTimeStr" value="" type="text"
                           onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                </td>
                <td class="bgcolor"><strong>*</strong>项目负责人：</td>
                <td>
                    <input type="text" id="personLiableId" name="personLiableId" style="margin-top:5px;width:80%;"
                           value="" style="width:80%;" readonly/>
                    <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="person();">
                        <img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt=""
                             width="19" height="19">
                    </a>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>&nbsp;</strong>当前节点：</td>
                <td>
                    <input type="text" id="projectNode" name="projectNode" value="" readonly style="width:100%;"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>节点控制日期：</td>
                <td>
                    <input class="Wdate search_text_form" id="nodeTimeStr" name="nodeTimeStr" type="text" value=""
                           readonly onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>项建估算批复(万元)：</td>
                <td>
                    <input type="text" id="xjGsJe" name="xjGsJe" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入项建估算金额"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
            </tr>
            <tr class="sonlist1">

                <td class="bgcolor"><strong>&nbsp;</strong>项建估算工程费(万元)：</td>
                <td>
                    <input type="text" id="xjGsGcf" name="xjGsGcf" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入项建估算工程费"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>项建估算二类费用(万元)：</td>
                <td>
                    <input type="text" id="xjGsElfy" name="xjGsElfy" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入项建估算二类费用"
                           style="width:100%;height:30px;line-height: 30px"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>项建估算三类费用(万元)：</td>
                <td>
                    <input type="text" id="xjGsSlfy" name="xjGsSlfy" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入项建估算三类费用"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
            </tr>
            <tr class="sonlist1">

                <td class="bgcolor"><strong>&nbsp;</strong>可研估算批复(万元)：</td>
                <td>
                    <input type="text" id="kyGsJe" name="kyGsJe" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入可研估算金额"
                           style="width:100%;height:30px;line-height: 30px"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>可研估算工程费(万元)：</td>
                <td>
                    <input type="text" id="kyGsGcf" name="kyGsGcf" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入可研估算工程费"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>可研估算二类费用(万元)：</td>
                <td>
                    <input type="text" id="kyGsElfy" name="kyGsElfy" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入可研估算二类费用"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
            </tr>
            <tr class="sonlist1">

                <td class="bgcolor"><strong>&nbsp;</strong>可研估算三类费用(万元)：</td>
                <td>
                    <input type="text" id="kyGsSlfy" name="kyGsSlfy" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入可研估算三类费用"
                           style="width:100%;height:30px;line-height: 30px"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>概算金额(元)：</td>
                <td>
                    <input type="text" id="sumGsJe" name="sumGsJe" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入概算金额"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>概算工程费(元)：</td>
                <td>
                    <input type="text" id="gsGcf" name="gsGcf" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入概算工程费"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
            </tr>
            <tr class="sonlist1">

                <td class="bgcolor"><strong>&nbsp;</strong>概算二类费用(元)：</td>
                <td>
                    <input type="text" id="gsElfy" name="gsElfy" value="" placeholder="请输入概算二类费用"
                           style="width:100%;height:30px;line-height: 30px" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2"/>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>概算三类费用(元)：</td>
                <td>
                    <input type="text" id="gsSlfy" name="gsSlfy" value="" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:2" placeholder="请输入概算三类费用"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>项目管理协议书：</td>
                <td>
                    <select name="projectManagementAgreement" class="custom-select">
                        <option value="">请选择</option>
                        <c:forTokens items="未签,已签,无需签订" delims="," var="i">
                            <option value="${i}">${i}</option>
                        </c:forTokens>
                    </select>
                </td>
            </tr>
            <tr class="sonlist1">

                <td class="bgcolor"><strong>*</strong>是否属于全过程项目：</td>
                <td>
                    <select name="allProcess" class="custom-select" id="allProcess" style="width:28%"
                            onchange="isAllProcess();">
                        <option value="是">是</option>
                        <option value="否" selected>否</option>
                    </select>
                </td>
                <td class="bgcolor"><strong>*</strong>项目分类：</td>
                <td colspan="2">
                    <select id="projectClassificationId" style="width:100%;" name="projectClassificationId"
                            class="custom-select">
                    </select>
                </td>
                <td></td>
            </tr>
            <tr id="unitTr" style="display:none">
                <td class="bgcolor"><strong>*</strong>审价单位：</td>
                <td colspan="5">
                    <input type="text" id="auditPriceUnit" name="auditPriceUnit" value=""
                           style="margin-top:5px;width:80%;" readonly/>
                    <a href="javascript:void(0)" class="fr" style="margin-top:5px;" onclick="unitEnterprise()">
                        <img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" alt=""
                             width="19" height="19">
                    </a>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>*</strong>工程单位：</td>
                <td>
                    <select id="unit" name="unit" class="custom-select" onchange="changeUnit();">
                        <option value="">请选择</option>
                        <option value="m2">m2</option>
                        <option value="km">km</option>
                    </select>
                </td>
                <td class="bgcolor"><strong>*</strong>工程所在地：</td>
                <td style="text-align: center;">
                    <select id="area" name="area" class="custom-select">
                        <option value="">请选择</option>
                        <option value="广州市">广州市</option>
                        <option value="越秀区">越秀区</option>
                        <option value="海珠区">海珠区</option>
                        <option value="荔湾区">荔湾区</option>
                        <option value="天河区">天河区</option>
                        <option value="白云区">白云区</option>
                        <option value="黄埔区">黄埔区</option>
                        <option value="花都区">花都区</option>
                        <option value="番禺区">番禺区</option>
                        <option value="南沙区">南沙区</option>
                        <option value="从化区">从化区</option>
                        <option value="萝岗区">萝岗区</option>
                        <option value="增城区">增城区</option>
                    </select>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>建设地点：</td>
                <td>
                    <input type="text" name="projectLocation" value="" placeholder="请输入详细地址" style="width:100%;">
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>&nbsp;</strong>结算评审方式：</td>
                <td colspan="5">
                    <input type="text" name="settlementReivewMethod" placeholder="请输入结算评审方式" style="width:100%;"/>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>&nbsp;</strong>结算方式约定：</td>
                <td colspan="5">
                    <input type="text" name="settlementAgreement" placeholder="请输入结算方式约定" style="width:100%;"/>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>&nbsp;</strong>项目概况：</td>
                <td colspan="5">
                    <textarea name="projectIntroduction" cols="5" rows="2">${project.projectIntroduction }</textarea>
                </td>
            </tr>
            <tr class="sonlist1">
                <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                <td colspan="5">
                    <textarea name="description" cols="5" rows="2">${project.description }</textarea>
                </td>
            </tr>
            <tr style="height:40px">
                <td colspan="6" style="text-align: center">
                    <input type="button" id="save" class="btn btn-primary" value="保存"/>&nbsp;&nbsp;
                    <input type="button" class="btn btn-success" onclick="top.closeWindow('项目添加')" value="取消"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="project"></div>
<div id="person"></div>
<script>
    $(function () {
        var deliveryFlag = $("#allProcess").val();
        if (deliveryFlag == '是') {
            $("#unitTr").show();
        } else if (deliveryFlag == '否') {
            $("#unitTr").hide();
        }

        $("#save").click(function () {
            var name = $("#name").val();
            var code = $("#code").val();
            var projectOwner = $("#projectOwner").val();
            var personLiableId = $("#personLiableId").val();
            var projectClassificationArray = $("#projectClassificationId").combotree("getValues");
            var allProcess = $("#allProcess option:selected").val();
            var auditPriceUnit = $("#auditPriceUnit").val();
            var auditPriceUnit = $("#auditPriceUnit").val();
            var xjGsJe = Number($("#xjGsJe").val());
            var xjGsGcf = Number($("#xjGsGcf").val());
            var xjGsElfy = Number($("#xjGsElfy").val());
            var xjGsSlfy = Number($("#xjGsSlfy").val());
            var kyGsJe = Number($("#kyGsJe").val());
            var kyGsGcf = Number($("#kyGsGcf").val());
            var kyGsElfy = Number($("#kyGsElfy").val());
            var kyGsSlfy = Number($("#kyGsSlfy").val());
            var sumGsJe = Number($("#sumGsJe").val());
            var gsGcf = Number($("#gsGcf").val());
            var gsElfy = Number($("#gsElfy").val());
            var gsSlfy = Number($("#gsSlfy").val());
            var unit = $("#unit option:selected").val();
            var area = $("#area option:selected").val();
            if (name == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写项目名称 ！', icon: 'error', top: 100});
                return;
            }
            if (code == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写项目代号！', icon: 'error', top: 100});
                return;
            }
            if (projectOwner == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写项目业主 ！', icon: 'error', top: 100});
                return;
            }
            if (personLiableId == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写项目负责人 ！', icon: 'error', top: 100});
                return;
            }

            if (projectClassificationArray == "-1" || projectClassificationArray == null || projectClassificationArray == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择项目分类！', icon: 'error', top: 100});
                return;
            }
            if (allProcess == "是" && auditPriceUnit == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择审价单位！', icon: 'error', top: 100});
                return;
            }
            if (xjGsJe > 0 && (xjGsGcf > 0 || xjGsElfy > 0 || xjGsSlfy > 0)) {
                if (xjGsGcf + xjGsElfy + xjGsSlfy != xjGsJe) {
                    $.messager.alert({
                        title: '温馨提示',
                        msg: '项建估算金额与项建估算工程费、项建估算二类费用、项建估算三类费用之和不相等！',
                        icon: 'error',
                        top: 100
                    });
                    return;
                }
            }
            if (kyGsJe > 0 && (kyGsGcf > 0 || kyGsElfy > 0 || kyGsSlfy > 0)) {
                if (kyGsGcf + kyGsElfy + kyGsSlfy != kyGsJe) {
                    $.messager.alert({
                        title: '温馨提示',
                        msg: '可研估算金额与项建估算工程费、项建估算二类费用、项建估算三类费用之和不相等！',
                        icon: 'error',
                        top: 100
                    });
                    return;
                }
            }
            if (sumGsJe > 0 && (gsGcf > 0 || gsElfy > 0 || gsSlfy > 0)) {
                if (gsGcf + gsElfy + gsSlfy != sumGsJe) {
                    $.messager.alert({title: '温馨提示', msg: '概算金额与概算工程费、概算二类费用、概算三类费用之和不相等！', icon: 'error', top: 100});
                    return;
                }
            }
            if (unit == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择单位 ！', icon: 'error', top: 100});
                return;
            }
            if (area == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择工程所在地！', icon: 'error', top: 100});
                return;
            }
            $.ajax({
                url: "/costProject/saveProject",
                dataType: "json",
                type: "post",
                async: false,
                data: $("#formId").serialize(),
                success: function (result) {
                    if (result.status == 200) {
                        //url="/costProject/updateProject";
                        window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                        var json1 = {tabTitle: '项目台账', url: '/forward_project_projectList'};
                        window.parent.refreshTab(json1);
                        window.location.href = "/costProject/editProject?editFlag=y&projId=" + result.msg;
                    } else if (result.status == 300) {
                        $.messager.alert({title: '温馨提示', msg: '已存在该有效项目代号,请不要重复添加！', icon: 'error', top: 100});
                        return;
                    } else {
                        $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                    }
                }
            });
        });
    });

    //审价单位
    function unitEnterprise() {
        $('#project').dialog({
            title: '审价单位',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top: 50,
            content: "<iframe name=\"fileFramePro\" frameborder=\"0\" src=" + '/forward_project_containEnterprise' + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
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
            top: 50,
            content: "<iframe name=\"fileFramePro\" frameborder=\"0\" src=" + '/forward_project_person' + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }

    function isAllProcess() {
        var value = $("#allProcess option:selected").val();
        if (value == "是") {
            $("#unitTr").show();
        }
        else {
            $("#unitTr").hide();
            $("#auditPriceUnit").val("");
        }
    }

    var flag1 = false;
    var flag2 = false;
    $(function () {
        $(".wrap").height(tableHeight());

        $(".switchBtn1").click(function () {

            $(".sonlist1").toggle(500);

            if (!flag1) {
                flag1 = true;
                $(this).addClass("active");
                $("#unitTr").hide();
            } else {
                flag1 = false;
                $(this).removeClass("active");
                isAllProcess();

            }

        })
        $(".switchBtn2").click(function () {
            $(".sonlist2").toggle(500);
            if (!flag2) {
                flag2 = true;
                $(this).addClass("active");
            } else {
                flag2 = false;
                $(this).removeClass("active");
            }
        });
        $("#xjGsProjectScale").next("span").children().first().blur(function () {
            //blur事件处理代码
            var xjGsProjectScale = $('#xjGsProjectScale').val();
            if (Number(xjGsProjectScale) != 0) {
                var xjGsJe = $('#xjGsJe').val();
                var xjGsCost = Number(xjGsJe) / Number(xjGsProjectScale);
                $('#xjGsCost').numberbox('setValue', xjGsCost);
            }
        });
        $("#kyGsProjectScale").next("span").children().first().blur(function () {
            //blur事件处理代码
            var kyGsProjectScale = $('#kyGsProjectScale').val();
            if (Number(kyGsProjectScale) != 0) {
                var kyGsJe = $('#kyGsJe').val();
                var kyGsCost = Number(kyGsJe) / Number(kyGsProjectScale);
                $('#kyGsCost').numberbox('setValue', kyGsCost);
            }
        });
        $("#gsProjectScale").next("span").children().first().blur(function () {
            //blur事件处理代码
            var gsProjectScale = $('#gsProjectScale').val();
            if (Number(gsProjectScale) != 0) {
                var sumGsJe = $('#sumGsJe').val();
                var gsCost = Number(sumGsJe) / Number(gsProjectScale);
                $('#gsCost').numberbox('setValue', gsCost);
            }
        });
        $("#kzjProjectScale").next("span").children().first().blur(function () {
            //blur事件处理代码
            var kzjProjectScale = $('#kzjProjectScale').val();
            if (Number(kzjProjectScale) != 0) {
                var sumKzjJe = '${project.sumKzjJe}';
                var kzjCost = Number(sumKzjJe) / Number(kzjProjectScale);
                $('#kzjCost').numberbox('setValue', kzjCost);
            }
        });
        $("#htjProjectScale").next("span").children().first().blur(function () {
            //blur事件处理代码
            var htjProjectScale = $('#htjProjectScale').val();
            if (Number(htjProjectScale) != 0) {
                var sumHtjJe = '${project.sumHtjJe}';
                var htjCost = Number(sumHtjJe) / Number(htjProjectScale);
                $('#htjCost').numberbox('setValue', htjCost);
            }
        });

    });

    function changeUnit() {
        var unit = $("#unit option:selected").val();
        if (unit != '') {
            $("#cost .unitStyle").val("元/" + unit);
            $("#architectrural .unitStyle").val("元/" + unit);
            $("#installation .unitStyle").val("元/" + unit);
            $("#rebar .unitStyle").val("t/" + unit);
            $("#concrete .unitStyle").val("m3/" + unit);
            $("#template .unitStyle").val("m2/" + unit);
            $("#scaffolding .unitStyle").val("m2/" + unit);
        }
    }

</script>
</body>
</html>