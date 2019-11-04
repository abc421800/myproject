<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>驻场人员修改</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/home/base.css">
    <link rel="stylesheet" href="../../fonts/icomoon/style.css"/>
    <link rel="stylesheet" href="../../vendor/My97DatePicker/4.8/skin/WdatePicker.css">
    <link rel="stylesheet" href="../../css/hetong_add/hetong_add.css">
    <link rel="stylesheet" href="../../css/button.css">
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/allstyle.css">
    <link rel="stylesheet" href="../../vendor/jquery-easyui-1.5.4.2/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../vendor/jquery-easyui-1.5.4.2/themes/bootstrap/datagrid.css">
    <link rel="stylesheet" type="text/css" href="../../vendor/jquery-easyui-1.5.4.2/themes/bootstrap/linkbutton.css">
    <link rel="stylesheet" href="../../vendor/jquery-easyui-1.5.4.2/themes/icon.css">
    <link rel="stylesheet" href="../../css/index.css">

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
        <table class="table_edit" id="table" cellspacing="0" cellpadding="0" >
            <tbody>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场企业名称：</td>
                <td>
                    <input type="text"  value="" placeholder="请输入驻场企业名称" style="width:80%;margin-top:5px;">
                    <a href="javascript:void(0)" class="fr" style="margin-top:5px;"><img src="../../img/project.png" alt="" width="19" height="19"></a>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>负责人：</td>
                <td>
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>企业类型：</td>
                <td >
                    <select name="" class="custom-select">
                        <option value="">请选择</option>
                        <option value="造价咨询">造价咨询</option>
                        <option value="招标代理">招标代理</option>
                        <option value="工程咨询">工程咨询</option>
                        <option value="工程监理">工程监理</option>
                        <option value="勘察设计">勘察设计</option>
                        <option value="建材供货商">建材供货商</option>
                        <option value="检测机构">检测机构</option>
                        <option value="其他">其他</option>
                    </select>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>手机：</td>
                <td>
                    <input type="text"  value="13800138000" placeholder="请输入联系电话" style="width:100%;" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" onblur="this.v();">
                </td>
            </tr>
            <tr>

                <td class="bgcolor"><strong>&nbsp;</strong>联系地址：</td>
                <td>
                    <input type="text"  value="" placeholder="请输入联系地址" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>*</strong>固话：</td>
                <td>
                    <input type="text"  value="" placeholder="请输入固话" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>服务开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>传真：</td>
                <td>
                    <input type="text"  value="" placeholder="请输入传真" style="width:100%;">
                </td>
            </tr>

            <tr>
                <td class="bgcolor"><strong>*</strong>服务结束时间：</td>
                <td style="text-align: left">
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>邮箱：</td>
                <td style="text-align: left">
                    <div class="parentCls">
                        <input type="text" name="email" value="10086@qq.com" placeholder="请输入邮箱"  style="width:100%;" id="email" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class="inputElem">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>是否有效：</td>
                <td style="text-align: left">

                    <input type="radio"  value="" id="effective" checked  name="effect">
                    <label for="effective" style="font-weight: normal">有效</label>
                    <input type="radio"  value="" id="ineffective" name="effect">
                    <label for="ineffective" style="font-weight: normal">无效</label>
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>创建人：</td>
                <td style="text-align: left">
                    <input type="text"  value="" placeholder="请输入创建人" style="width:100%;">
                </td>

            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>累计派出驻场人员：</td>
                <td style="text-align: left">
                    <input type="text"  value="" placeholder="请输入累计派出人员汇总数" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>当前有效派出人员：</td>
                <td style="text-align: left">
                    <input type="text"  value="" placeholder="请输入当前有效派出人员汇总数" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>备注：</td>
                <td colspan="3">
                    <textarea name="" cols="3" rows="4"></textarea>
                </td>
            </tr>
            <tr style="height:40px">
                <td colspan="4" style="text-align: center">
                    <input type="button" class="btn btn-primary"  value="保存">&nbsp;&nbsp;
                    <input type="button" class="btn btn-success" onclick="top.closeWindow('')" value="取消">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div id="tt" style="width:100%;">
        <div title="驻场人员" class="subWrap">
            <div id="t0" style="margin-bottom: 5px">
                <div class="row">
                    <div class="filter" style="margin-bottom:0px">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">姓名：</label>
                                <input type="text" value="" placeholder="请输入关键字" class="form-control input-sm">
                            </div>
                            <div class="form-group">

                                <select style="font-weight: bold" id="shenTime"  class="form-control input-sm" name="queryTimeType">
                                    <option value="receiveTime">计划驻场时间</option>
                                    <option value="decideTime">实际驻场时间</option>
                                </select>
                                <input id="startTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                                <label for="">至</label>
                                <input id="endTime" class="Wdate form-control input-sm" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">


                            </div>
                            <div class="form-group">
                                <label for="">是否有效：</label>
                                <select class="form-control input-sm" style="width:100px">
                                    <option value="">请选择</option>
                                    <option value="">有效</option>
                                    <option value="">无效</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="Submit11" value="查询" class="btn btn-primary" onclick="">&nbsp;
                                <input type="reset" name="reset" value="重置" class="btn btn btn-success" onclick="">&nbsp;
                                <input type="button" name="button" value="导出" class="btn btn-danger" onclick="">&nbsp;
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" class="switchBtn" id="switchBtn1"></a>
                </div>
            </div>
            <table id="residentPerson" style="width: 100%">
            </table>

            <div id="tit1">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addTab()">添加</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit()">修改</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteHandler()">删除</a>
                <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>
            </div>

        </div>
    </div>
</div>
<script src="../../vendor/jquery-easyui-1.5.4.2/jquery.min.js"></script>
<script src="../../vendor/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
<script src="../../vendor/jquery-easyui-1.5.4.2/jquery.easyui.datagrid.column.js"></script>
<script src="../../vendor/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>
<script src="../../vendor/slimscroll/slimscroll.min.js"></script>
<script src="../../vendor/slimscroll/custom-scrollbar.js"></script>
<script src="../../vendor/My97DatePicker/4.8/WdatePicker.js"></script>
<script src="../../js/page.js"></script>
<script src="../../../res/js/work/attendanceManager/residentEnterpriseDetail.js"></script>
<script src="../../js/emailAutoComplete.js"></script>
<script>
    <!-- 禁用所有Form表单 -->
    function disableForm(formId,isDisabled) {
        var attr="disable";
        if(!isDisabled){
            attr="enable";
        }
        $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] select").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);
        $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);
    }
</script>
</body>
</html>