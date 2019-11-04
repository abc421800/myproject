<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日历设置</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/pagecommon/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/pagecommon/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/themes/bootstrap/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/themes/icon.css">
    <!-- 弹出框内容样-->
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/index.css">--%>
	<script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/bootstrap.min.js"></script>
    <!-- Slimscroll JS -->
    <script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/layout.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/kalendae/kalendae.css">
	<style type="text/css">
        *
        {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        body
        {
            overflow-x: hidden;
            font-family: '微软雅黑';
            font-size: 12px;
            margin: 0px;
            padding: 0px;
            color: #000000;
        }
        .Main
        {
            border: 1px solid #ADD4ED;
            margin: 0px 10px 10px 10px;
            /*padding-bottom:8%;
            padding-bottom: 100px;
            margin-bottom:100px;*/
        }
        .worksCSS
        {
            margin-left: 10px;
            width: 480px;
            height: calc(100% - 32px);
        }
        .worksCSS h3
        {
            height: 30px;
            font-size: 16px; /*border-bottom: 1px dotted #ccc;*/
            margin-top: 5px;
            margin-bottom: 2px;
            padding-bottom: 1px;
        }
        .worksCSS2
        {
            margin-left: 10px; /*width: 480px;*/
            height: calc(100% - 32px);
        }
        .worksCSS2 h3
        {
            height: 30px;
            font-size: 16px; /*border-bottom: 1px dotted #ccc;*/
            margin-top: 5px;
            margin-bottom: 2px;
            padding-bottom: 1px;
        }
        .Legend
        {
            font-family: '微软雅黑';
            font-size: 13px;
            color: #00A3E9;
            float: left;
            margin-left: 10px;
            margin-top: 4px; /*border: 2px solid #ADD4ED;
            margin-right: 15px;*/
        }
    </style>
    <style type="text/css">
        .dline
        {
            display: inline;
            margin-right: 10px;
        }
    </style>
    <style type="text/css">
        .Top
        {
            margin-top: 10px;
            margin-left: 25px;
            margin-bottom: 12px;
        }
        .checkbox{
            border:none
        }
    </style>
</head>
<body class="easyui-layout" style="overflow-y: auto">
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
<div class="Top">
    <h2>
        年份:
        <input id="Year" class="easyui-combobox" name="year" style="width: 115px;" data-options="
					valueField:'id',
					textField:'year',
					panelHeight:'auto',
					icons:[{
						iconCls:'icon-add',
                         handler: function () {
                            addYear();
                        }
					},{
						iconCls:'icon-remove',
                         handler: function () {
                            remove();
                        }
					}]
			">
    </h2>
</div>
<div class="Main" id="Main">
    <div class="worksCSS">
        <h3>工作日</h3>
        <div style="border: 1px solid #ADD4ED; padding: 10px 8px; margin-left: 10px;width:96%;">
            <div class="checkbox controlled-setting-with-label dline">
                <label>
                    <input type="checkbox" style="vertical-align: middle;" />
                    <span><span style="color: #FF0000">星期天</span> </span>
                </label>
            </div>
            <div class="checkbox controlled-setting-with-label dline" >
                <label>
                    <input type="checkbox" checked="checked" style="vertical-align: middle;" />
                    <span><span>星期一</span> </span>
                </label>
            </div>
            <div class="checkbox controlled-setting-with-label dline">
                <label>
                    <input type="checkbox" checked="checked" style="vertical-align: middle;" />
                    <span><span>星期二</span> </span>
                </label>
            </div>
            <div class="checkbox controlled-setting-with-label dline">
                <label>
                    <input type="checkbox" checked="checked" style="vertical-align: middle;" />
                    <span><span>星期三</span> </span>
                </label>
            </div>
            <div class="checkbox controlled-setting-with-label dline">
                <label>
                    <input type="checkbox" checked="checked" style="vertical-align: middle;" />
                    <span><span>星期四</span> </span>
                </label>
            </div>
            <div class="checkbox controlled-setting-with-label dline">
                <label>
                    <input type="checkbox" checked="checked" style="vertical-align: middle;" />
                    <span><span>星期五</span> </span>
                </label>
            </div>
            <div class="checkbox controlled-setting-with-label dline">
                <label>
                    <input type="checkbox" style="vertical-align: middle;" />
                    <span><span style="color: #0000FF">星期六</span> </span>
                </label>
            </div>
        </div>
    </div>
    <div class="worksCSS2">
        <h3>
            节假日
            <div class="Ledend" style="width: 200px; height: 30px; background-color: #F8F036;
                    float: right; display: inline; margin-right: 15px; border: 1px solid #00A3E9">
                <h4 class="Legend">
                    图例:</h4>
                <div style="width: 18px; height: 18px; background-color: #FFFFFF; float: left; margin-top: 4px;
                        margin-left: 7px; margin-right: -6px; border: 1px solid #A79D84;">
                </div>
                <h4 class="Legend">
                    工作日</h4>
                <div style="width: 18px; height: 18px; background-color: #00A3E9; float: left; margin-top: 4px;
                        margin-left: 7px; margin-right: -6px; border: 1px solid #FFFFFF;">
                </div>
                <h4 class="Legend">
                    休息日</h4>
            </div>
        </h3>
        <h4 style="padding: 0px 4px; color: #3E48D0;">
            说明:在以下日历中选择本年度的节假日</h4>
    </div>
    <div id="Calen" style="padding: 0px 8px;/*padding-bottom:105px;*/">
    </div>
</div>


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
<script language="javascript">
    var ss;
    window.onload = function () {
        var w = document.documentElement.clientWidth; //可见区域宽度
        var h = document.documentElement.clientHeight; //可见区域高度
        ss = document.getElementById('Main');
        ss.style.paddingBottom = (h-555)+ "px";
        //ss.style.height = h + "px";
    }
</script>
<script type="text/javascript">
    var yearKalendae;
    $(function () {
        yearKalendae = new Kalendae(document.getElementById("Calen"), {
            //attachTo: documen.getElementById("ddd"),
            months: 12,
            mode: 'multiple',
            viewStartDate: '2019-01-01',
            //selected: [Kalendae.moment().subtract({ M: 1 }), Kalendae.moment().add({ M: 1 })]  //默认选中日期
            subscribe: {
                'date-clicked': function (date) {
                    //console.log('yearKalendae change');
                    //延迟500毫秒，记点击事件完成，才能获取到正确的选中数据
                    setTimeout(function () {
                            saveChangeData();
                    }, 500);
                }
            }
        });
    });
</script>
<script src="${pageContext.request.contextPath}/res/js/pagecommon/page.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/kalendae/kalendae.standalone.js"></script>
<script src="${pageContext.request.contextPath}/res/js/work/calendarSetting/WebSysHoliday.js"></script>
</body>
</html>