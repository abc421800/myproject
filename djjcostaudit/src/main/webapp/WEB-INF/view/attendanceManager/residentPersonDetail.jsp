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
                <td class="bgcolor"><strong>*</strong>计划驻场开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>驻场人员姓名：</td>
                <td >
                    <input type="text"  value="孔祥升" placeholder="请输入驻场人员姓名" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>计划驻场结束时间：</td>
                <td>
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>性别：</td>
                <td>
                    <input type="radio"  value="" id="man" checked  name="gender">
                    <label for="man" style="font-weight: normal">男</label>
                    <input type="radio"  value="" id="woman" name="gender">
                    <label for="woman" style="font-weight: normal">女</label>
                </td>
                <td class="bgcolor"><strong>*</strong>实际驻场开始时间：</td>
                <td>
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
            </tr>
            <tr>

                <td class="bgcolor"><strong>&nbsp;</strong>驻场服务岗位：</td>
                <td>
                    <input type="text"  value="预算员" placeholder="请输入驻场服务岗位" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>实际驻场结束时间：</td>
                <td>
                    <input class="Wdate search_text_form" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" style="width:80%">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>*</strong>联系电话：</td>
                <td style="text-align: left">
                    <input type="text"  value="13800138000" placeholder="请输入联系电话" style="width:100%;" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-_/]+/,'');}).call(this)" onblur="this.v();">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>累计驻场天数：</td>
                <td style="text-align: left">
                    <input type="text"  value="=上班天数" placeholder="请输入累计驻场天数" style="width:100%;">
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>邮箱：</td>
                <td style="text-align: left">
                    <div class="parentCls">
                        <input type="text" name="email" value="10086@qq.com" placeholder="请输入邮箱"  style="width:100%;" id="email" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9_+-.+a-z+A-Z+@]+/,'');}).call(this)" onblur="this.v();" class="inputElem">
                    </div>
                </td>
                <td class="bgcolor"><strong>*</strong>是否有效：</td>
                <td style="text-align: left">

                    <input type="radio"  value="" id="effective" checked  name="effect">
                    <label for="effective" style="font-weight: normal">有效</label>
                    <input type="radio"  value="" id="ineffective" name="effect">
                    <label for="ineffective" style="font-weight: normal">无效</label>
                </td>
            </tr>
            <tr>
                <td class="bgcolor"><strong>&nbsp;</strong>微信号：</td>
                <td style="text-align: left">
                    <input type="text"  value="13800138000" placeholder="请输入联系电话" style="width:100%;">
                </td>
                <td class="bgcolor"><strong>&nbsp;</strong>创建人：</td>
                <td style="text-align: left">
                    <input type="text"  value="" placeholder="请输入创建人" style="width:100%;">
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
                    <input type="button" class="btn btn-success" onclick="top.closeWindow('驻场人员详情')" value="取消">
                </td>
            </tr>
            </tbody>
        </table>
    </form>

</div>
<script src="../../vendor/jquery-easyui-1.5.4.2/jquery.min.js"></script>
<script src="../../vendor/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
<script src="../../vendor/jquery-easyui-1.5.4.2/jquery.easyui.datagrid.column.js"></script>
<script src="../../vendor/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>
<script src="../../vendor/slimscroll/slimscroll.min.js"></script>
<script src="../../vendor/slimscroll/custom-scrollbar.js"></script>
<script src="../../vendor/My97DatePicker/4.8/WdatePicker.js"></script>
<script src="../../js/page.js"></script>
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