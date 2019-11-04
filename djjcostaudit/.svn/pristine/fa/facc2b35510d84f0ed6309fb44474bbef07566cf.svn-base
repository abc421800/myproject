function resizeFrame(){
  var windowl = $(window).height()-40;
  // $(".tree-aru").css({height:windowl});
  // $(".embed-responsive").css({height:windowl});
     $(".tree-aru").height(windowl+4);
     $(".embed-responsive").height(windowl+6);
     window.onresize=function(){
         $(".tree-aru").height($(window).height()-36);
         $(".embed-responsive").height($(window).height()-34);
     }
}
function removeCheck(){
  $('.datagrid-header-check').html("");
}
function tableHeight() {
    var windowH = $(window).height();
    var tabsWrap = $('.tabs-wrap').outerHeight() ? $('.tabs-wrap').outerHeight() : 0;
    var filterH = $('.filter').outerHeight(false) ? $('.filter').outerHeight(false) : 0;
    var none = 60;
    var con = windowH  -tabsWrap- filterH - none;
    console.log("2018.5.14windowH:::::"+windowH);
    console.log("2018.5.14tabsWrap:::::"+tabsWrap);
    console.log("2018.5.14filterH:::::"+filterH);
    console.log("2018.5.14con:::::"+con);
    return con;
}

function formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = month < 10 ? "0" + month : month;
    var dayOfMonth = date.getDate();
    dayOfMonth = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth;
    var hour = date.getHours();
    hour = hour < 10 ? "0" + hour : hour;
    var minute = date.getMinutes();
    minute = minute < 10 ? "0" + minute : minute;
    var second = date.getSeconds();
    second = second < 10 ? "0" + second : second;
    return year + "-" + month + "-" + dayOfMonth + " " + hour + ":" + minute + ":" + second;
}
function formatterdate(val, row) {
    if (val != null) {
    var date = new Date(val);
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
    + date.getDate();
    }
}
//添加千分符
function addQianFenFu(s){
        if(/[^[+-]?0-9\.]/.test(s)) return "invalid value";
        s=s.replace(/^(\d*)$/,"$1.");
        s=(s+"00").replace(/(\d*\.\d\d)\d*/,"$1");
        s=s.replace(".",",");
        var re=/(\d)(\d{3},)/;
        while(re.test(s))
                s=s.replace(re,"$1,$2");
        s=s.replace(/,(\d\d)$/,".$1");
        return s.replace(/^\./,"0.")
}
function format(val) {
    if(val!=""){
        var n = parseFloat(val).toFixed(2);
        var re = /(\d{1,3})(?=(\d{3})+(?:\.))/g;
        return n.replace(re, "$1,");
    }
}
$(function(){
    // tableHeight();
    resizeFrame();
    $(window).resize(function() {
        // tableHeight();
        // resizeFrame();
    });
    window.onload=function(){
        console.log("loading end");
        $("#loading-wrapper").fadeOut(500);
        numberInputPlaceholder();
    };

});
function numberInputPlaceholder(){
    $(".easyui-numberbox").each(function(i){
        var span = $(this).siblings("span")[0];
        var targetInput = $(span).find("input:first");
        if(targetInput){
            $(targetInput).attr("placeholder", $(this).attr("placeholder"));
        }

    });
}