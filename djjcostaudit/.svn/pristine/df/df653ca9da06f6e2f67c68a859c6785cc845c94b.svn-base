function SetCookie(name, value) {
    var argv = SetCookie.arguments;
    var argc = SetCookie.arguments.length;
    var expires = (2 < argc) ? argv[2] : null;
    var path = (3 < argc) ? argv[3] : null;
    var domain = (4 < argc) ? argv[4] : null;
    var secure = (5 < argc) ? argv[5] : false;
    document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
}

function GetCookie(Name) {
    var search = Name + "=";
    var returnvalue = "";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            returnvalue = unescape(document.cookie.substring(offset, end));
        }
    }
    return returnvalue;
}
var thisskin;
thisskin = GetCookie("nowskin2018");
if (thisskin != "") {
    skin.href = thisskin;
   if(skin.href.slice(-5,-4)=='0'){
       $('.skin1').addClass("active");
       $('.skin2').removeClass("active");
   }else{
       $('.skin1').removeClass("active");
       $('.skin2').addClass("active");
   }
} else {
    skin.href = "res/css/home/main0.css";
}

function changecss(url) {

    if (url != "") {
        skin.href = url;
        var expdate = new Date();
        expdate.setTime(expdate.getTime() + (24 * 60 * 60 * 1000 * 30*120));
        //expdate=null;
        //以下设置COOKIES时间为1年,自己随便设置该时间..
        SetCookie("nowskin2018", url, expdate, "/", null, false);
    }
}
$('#skin-box span').on('click', function() {
    $(this).addClass('active');
    $(this).siblings().removeClass('active');
    if($(this).index()==0){
        changecss('res/css/home/main0.css')
    }else if($(this).index()==1){
        changecss('res/css/home/main1.css')
    }
});