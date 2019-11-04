$(function () {
    if ($(window).width() < 767) {
        $('#main-layout').layout('collapse', 'west');
    }
    $('body').layout('panel','west').panel('panel').resizable('disable');
    setLayoutHeight();

});
function setLayoutHeight() {
    var height = $(window).height() - 40;
    $(window).resize(function(){
        height=$(window).height() - 40;
        $("#main-layout").attr("style", "width:100%;height:" + height + "px");
        $("#main-layout").layout("resize", {
            width: "100%",
            height: height + "px"
        });

    }).resize();
}
$(function(){
    $(".left-menu dl dd").on("click",function () {
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
        if($(this).index()==0){
            $("#con0").show();
            $("#con1").hide();
        }else if($(this).index()==1){
            $("#con0").hide();
            $("#con1").show();
        }
    })
})