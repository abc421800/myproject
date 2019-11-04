/**
 * Elektron - An Admin Toolkit
 * @version 0.3.1
 * @license MIT
 * @link https://github.com/onokumus/elektron#readme
 */
// 'use strict';

/* eslint-disable no-undef */
$(function () {
	if ($(window).width() < 992) {
		$('#app-side').onoffcanvas('hide');
	} else {
		$('#app-side').onoffcanvas('show');
	}

	// $('.side-nav .unifyMenu').unifyMenu({ toggle: true });
    $('.side-nav .unifyMenu').unifyMenu({ activeClass:'selected'});
    $('.side-nav .unifyMenu ul li a').on('click',function () {
        $(this).addClass("current-page");
        $(this).parent().siblings().find('a').removeClass('current-page');
    });
	$('#app-side-hoverable-toggler').on('click', function () {
		$('.app-side').toggleClass('is-hoverable');
		$(undefined).children('i.fa').toggleClass('fa-angle-right fa-angle-left');
	});

	$('#app-side-mini-toggler').on('click', function () {
		$('.app-side').toggleClass('is-mini');
		$("#app-side-mini-toggler i").toggleClass('icon-sort icon-menu');
        $("#hualian_layout_center_tab_container").css("width", "auto");
        $("#hualian_layout_center_tab_container div[class^='tabs'],[class^='panel']").css("width", "auto");
        $("#hualian_layout_center_tab_container div[class='tabs-scroller-left']").css("width", "18px");
        $("#hualian_layout_center_tab_container div[class='tabs-scroller-right']").css("width", "18px");

    });

	$('#onoffcanvas-nav').on('click', function () {
		$('.app-side').toggleClass('left-toggle');
		$('.app-main').toggleClass('left-toggle');
		$("#onoffcanvas-nav i").toggleClass('icon-sort icon-menu');
	});
	
	$('.onoffcanvas-toggler').on('click', function () {
		$(".onoffcanvas-toggler i").toggleClass('icon-chevron-left icon-cheveron-right');
        $("#hualian_layout_center_tab_container").css("width", "auto");
        $("#hualian_layout_center_tab_container div[class^='tabs'],[class^='panel']").css("width", "auto");
        $("#hualian_layout_center_tab_container div[class='tabs-scroller-left']").css("width", "18px");
        $("#hualian_layout_center_tab_container div[class='tabs-scroller-right']").css("width", "18px");
	});
});


// $(function() {
// 	$('#unifyMenu')
// 	.unifyMenu()
// 	.on('shown.unifyMenu', function(event) {
// 			$('body, html').animate({
// 					scrollTop: $(event.target).parent('li').position().top
// 			}, 600);
// 	});
// });


// Todays Date
$(function() {
	var interval = setInterval(function() {
		var momentNow = moment();
		$('#today-date').html(momentNow.format('MMMM . DD') + ' '
		+ momentNow.format('. dddd').substring(0,5).toUpperCase());
	}, 100);
});


// Task list
$('.task-list').on('click', 'li.list', function() {
	$(this).toggleClass('completed');
});
// Loading
window.onload=function(){
    console.log("loading end");
    $("#loading-wrapper").fadeOut(500);
};
// quit system
function logout(url) {
    $.extend($.messager.defaults, {
        ok: "确定",
        cancel: "取消",
    });
    $.messager.confirm('退出提示 ',"确认退出当前系统？", function(r) {
        if (r) {
            location.href = url;
        }
    });
}
$(function(){
    resizeFun();
    $('#kefu').on('click',function () {
        $('.kefu').show();
        $('.layerBox').show();
    });
    $(".layerBox .close").on('click',function () {
        $('.kefu').hide();
        $('.layerBox').hide();
    });
    $(".kefu").on('click',function () {
        $('.kefu').hide();
        $('.layerBox').hide();
    });
    $(".layerBox .content li:first-child a").on('mouseover',function () {
        $(".tip_wx").show();
    });
    $(".layerBox .content li:first-child a").on('mouseout',function () {
        $(".tip_wx").hide();
    });
    $(".layerBox .content li:last-child a").on('mouseover',function () {
        $(".tip_tel").show();
    });
    $(".layerBox .content li:last-child a").on('mouseout',function () {
        $(".tip_tel").hide();
    });
    $(".layerBox .content li:nth-of-type(2) a").on('click',function () {
        $('.kefu').hide();
        $('.layerBox').hide();
        addTabGrid('服务支持', 'page/service_support/support.html');
    })

});
function resizeFun() {
    window.onresize=function(){
        $("#pf-page").height($(window).height()-63);
        $(".page-iframe").height($(window).height()-63-49);
        $('#hualian_layout_center_tab_container').height($(window).height()-63);
        $('#hualian_layout_center_tab_container .tabs-panels').height($(window).height()-63);
        $('#hualian_layout_center_tab_container .panel-body').height($(window).height()-63);
        $("#hualian_layout_center_tab_container").css("width", "auto");
        $("#hualian_layout_center_tab_container div[class^='tabs'],[class^='panel']").css("width", "auto");
        $("#hualian_layout_center_tab_container div[class='tabs-scroller-left']").css("width", "18px");
        $("#hualian_layout_center_tab_container div[class='tabs-scroller-right']").css("width", "18px");
    };

    $('.easyui-tabs1').tabs({
        tabHeight: 40,
        onSelect:function(title,index){
            var currentTab = $('.easyui-tabs1').tabs("getSelected");
            currentTab.find("iframe").resize();
                           /* if(currentTab.find("iframe") && currentTab.find("iframe").size() && title == "项目台账"){
                              currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
                            }*/
        }
    });
    $(window).resize(function() {
        $('.easyui-tabs1').tabs("resize");
    }).resize();
    $(window).resize(function(){
       // $('#hualian_layout_center_tab_container .tabs-panels').height($("#pf-page").height()-46);
       // $('#hualian_layout_center_tab_container .panel-body').height($("#pf-page").height());
    }).resize();
}
function parentSay(str,href){
    addTabGrid(str,href);
}
function closeTabs(curTabTitle) {
    $("#hualian_layout_center_tab_container").tabs("close", curTabTitle);
}
