<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>综合考核单修改</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-master/dist/css/layui.css">
    <script src="${pageContext.request.contextPath}/res/plugin/layui-master/dist/layui.js"></script>
    <script>
        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate;
            return currentdate;
        }

        var currentDateRange = getNowFormatDate() + ' - ' + getNowFormatDate();
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            //时间范围
            laydate.render({
                elem: '#leaveTime'
                , range: true
                , isInitValue: false,
                value: currentDateRange
            });
        });
    </script>
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
    <form action="" id="formId">
        <c:if test="${!empty obj}">
            <input type="hidden" value="${obj.id}" id="typeId">
        </c:if>
        <input type="hidden" value="${obj.costEnterpriseId}" id="costEnterpriseId">
        <table class="table_edit" id="table_edit" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>考核年份：</td>
                <td style="width:30%;">
                    <input id="year" value="${obj.year}" readonly/>
                </td>
                <td class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>年度中签项目（个）：</td>
                <td style="text-align: left;width: 15%">
                    <input id="winbidNum" value="${obj.winbidNum}" readonly>
                </td>
                <td class="bgcolor" style="width:14%;"><strong>&nbsp;</strong>登记人：</td>
                <td style="width:14%">
                    <input id="creater" value="${obj.creater}" readonly/>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>入库企业名称：</td>
                <td>
                    <input type="text" id="auditPriceUnit" value="${obj.enterpriseName}" placeholder="请选择入库企业"
                           style="width:70%;margin-top:5px;" readonly>
                    <a href="javascript:void(0)" onclick="queryAllEnterprise()" class="fr" style="margin-top:5px;">
                        <img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" width="19"
                             height="19">&nbsp;&nbsp;
                    </a>
                    <div style="clear: both"></div>
                </td>
                <td class="bgcolor"><strong>*</strong>是否参加综考：</td>
                <td>
                    <select id="joinAssessFlag" class="custom-select">
                        <c:forTokens items="是,否" delims="," var="i">
                            <option
                                    <c:if test="${i eq obj.joinAssessFlag}">selected="selected"</c:if> value="${i}">${i}
                            </option>
                        </c:forTokens>
                    </select>
                </td>
                <td class="bgcolor"><strong>*</strong>评分人：</td>
                <td>
                    <input type="text" id="personLiableId" value="${obj.rater}" placeholder="请选择评分人"
                           style="width:70%;margin-top:5px;" readonly>
                    <a href="javascript:void(0)" onclick="queryAllRater()" class="fr" style="margin-top:5px;">
                        <img src="${pageContext.request.contextPath}/res/images/pagecommon/project.png" width="19"
                             height="19">&nbsp;&nbsp;
                    </a>
                    <div style="clear: both"></div>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>综合考核评分：</td>
                <td>
                    <input type="text" value="${obj.assessScore}" id="assessScore" class="easyui-numberbox"
                           data-options="groupSeparator:',',precision:0"
                           style="width:100%;height:30px;line-height: 30px">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>综合考评得分排名：</td>
                <td>
                    <input type="text" value="${obj.scoreRank}" id="scoreRank" name="scoreRank"
                           placeholder="请选择综合考评得分排名" style="width:100%;" readonly>
                </td>
                <td class="bgcolor"><strong>*</strong>综合考核评定结果：</td>
                <td>
                    <select class="custom-select" id="assessResult">
                        <option value="">未评定</option>
                        <c:forTokens items="第一档,第二档,不合格" delims="," var="i">
                            <option
                                    <c:if test="${i eq obj.assessResult}">selected="selected"</c:if> value="${i}">${i}
                            </option>
                        </c:forTokens>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>下一年摇号档次：</td>
                <td>
                    <select class="custom-select" id="yaohaoGrade">
                        <option value="">请选择</option>
                        <c:forTokens items="第一档,第二档" delims="," var="i">
                            <option
                                    <c:if test="${i eq obj.yaohaoGrade}">selected="selected"</c:if> value="${i}">${i}
                            </option>
                        </c:forTokens>
                    </select>
                </td>
                <td class="bgcolor"><strong>*</strong>评定入库状态：</td>
                <td>
                    <select class="custom-select" id="rukuStatus">
                        <option value="">请选择</option>
                        <c:forTokens items="在库,出库,暂停" delims="," var="i">
                            <option
                                    <c:if test="${i eq obj.rukuStatus}">selected="selected"</c:if> value="${i}">${i}
                            </option>
                        </c:forTokens>
                    </select>
                </td>
                <td class="bgcolor"></td>
                <td>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                <td colspan="5">
                    <textarea cols="5" id="remark" rows="4">${obj.remark}</textarea>
                </td>
            </tr>
            <tr style="height:40px">
                <td colspan="6" style="text-align: center">
                    <input type="button" class="btn btn-primary" id="addAssessBtn" value="保存">&nbsp;&nbsp;
                    <input type="button" class="btn btn-success" onclick="top.closeWindow('综合考核单修改')" value="取消">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div id="tt" style="width:100%;">
        <div title="相关资料" class="subWrap">
            <table id="material" style="width: 100%">
            </table>
            <div id="tit2">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false"
                   onclick="getDialog('material')">添加文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false"
                   onclick="getEditDialog('material')">修改文件夹</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false"
                   onclick="delFile('material')">删除</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-upload" plain="false"
                   onclick="uploadFile('material')">
                    <上传附件></上传附件>
                </a>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>
        </div>
    </div>
</div>
<div id="dlg"></div>
<div id="dialog"></div>
<div id="person"></div>
<div id="unitEnterprise"></div>
<div id="editDialog"></div>
<div id="document"></div>
<script src="${pageContext.request.contextPath}/res/js/yaohao/assess/comprehensiveAssessment_add.js"></script>
<script>
    var url = "${url}";
    var rukuStatus = $('#rukuStatus').val();
    if (rukuStatus == '暂停') {
        $("#rukuStatus").attr("disabled", "disabled");
    }
    $(function () {
        $("#addAssessBtn").on("click", function (e) {
            var id = $("#typeId").val();
            var costEnterpriseId = $("#costEnterpriseId").val();
            var year = $("#year").val();
            var winbidNum = $("#winbidNum").val();
            var creater = $("#creater").val();
            var enterpriseName = $("#auditPriceUnit").val();
            var joinAssessFlag = $('#joinAssessFlag').val();
            var rater = $('#personLiableId').val();
            var assessScore = $('#assessScore').val();
            var scoreRank = $('#scoreRank').val();
            var assessResult = $('#assessResult').val();
            var yaohaoGrade = $('#yaohaoGrade').val();
            var rukuStatus = $('#rukuStatus').val();
            var remark = $('#remark').val();
            if (year == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写考核年份！', icon: 'error', top: 100});
                return;
            }
            if (year.length > 4) {
                $.messager.alert({title: '温馨提示', msg: '请输入正确的年份！', icon: 'error', top: 100});
                return;
            }
            if (creater == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写登记人！', icon: 'error', top: 100});
                return;
            }
            if (enterpriseName == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择驻场企业 ！', icon: 'error', top: 100});
                return;
            }
            if (joinAssessFlag == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择是否选择综考 ！', icon: 'error', top: 100});
                return;
            }
            if (rater == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择评分人 ！', icon: 'error', top: 100});
                return;
            }
            if (assessScore == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写综合考核评分 ！', icon: 'error', top: 100});
                return;
            }
            if (scoreRank == "") {
                $.messager.alert({title: '温馨提示', msg: '请填写综合考评得分排名 ！', icon: 'error', top: 100});
                return;
            }
            if (assessResult == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择综合考核评定结果 ！', icon: 'error', top: 100});
                return;
            }
            if (yaohaoGrade == "") {
                if (rukuStatus == "出库") {

                } else {
                    $.messager.alert({title: '温馨提示', msg: '请选择下一年摇号档次 ！', icon: 'error', top: 100});
                    return;
                }
            }
            if (rukuStatus == "") {
                $.messager.alert({title: '温馨提示', msg: '请选择评定入库状态 ！', icon: 'error', top: 100});
                return;
            }
            if (assessScore > 100 || assessScore < 0) {
                if (assessScore <= 65) {
                    if (rukuStatus != '出库') {
                        $.messager.alert({title: '温馨提示', msg: '评定入库状态不符合规则，请重新填写！', icon: 'error', top: 100});
                        return;
                    }
                }
                if (rukuStatus == '暂停') {
                    if (assessScore > 0) {
                        $.messager.alert({title: '温馨提示', msg: '请输入评分数值，暂停状态的企业输入0！', icon: 'error', top: 100});
                        return;
                    } else if (assessScore < 0) {
                        $.messager.alert({title: '温馨提示', msg: '请输入评分数值，暂停状态的企业输入0！', icon: 'error', top: 100});
                        return;
                    }
                } else {
                    $.messager.alert({title: '温馨提示', msg: '综合考核分数不符合规则，请重新填写！', icon: 'error', top: 100});
                    return;
                }
            }
            var assessParam = {
                "id": id,
                "costEnterpriseId": costEnterpriseId,
                "year": year,
                "winbidNum": winbidNum,
                "creater": creater,
                "enterpriseName": enterpriseName,
                "joinAssessFlag": joinAssessFlag,
                "rater": rater,
                "assessScore": assessScore,
                "scoreRank": scoreRank,
                "assessResult": assessResult,
                "yaohaoGrade": yaohaoGrade,
                "rukuStatus": rukuStatus,
                "remark": remark
            };
            $.ajax({
                url: url,
                dataType: "json",
                type: "post",
                async: false,
                data: assessParam,
                success: function (result) {
                    if (result.status == 200) {
                        window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                        top.addTabGrid('综合考核', '/assess/toList');
                    } else {
                        $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                    }
                }
            });
        });
    });

    //查询当前考核年份，落在入库有效起止时间段内所有的企业
    function queryAllEnterprise() {
        $('#unitEnterprise').dialog({
            title: '入库企业名称',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top: 50,
            content: "<iframe name=\"fileFramePro\" frameborder=\"0\" src=" + '/forward_yaohao_assess_containEnterprise' + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true,
            onLoadSuccess: function (data) {
                if (data.total == 0) {
                    $('#unitEnterprise').datagrid('loadData', {total: 1, rows: [{account: "未找到相关信息！"}]});
                }
            },
        });
    }

    //查询所有的造价审核部的有效人员
    function queryAllRater() {
        $('#person').dialog({
            title: '评分人',
            width: 805,
            height: 520,
            closed: false,
            cache: false,
            top: 50,
            content: "<iframe name=\"fileFramePro\" frameborder=\"0\" src=" + '/forward_yaohao_assess_person' + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
            modal: true
        });
    }
</script>
</body>
</html>