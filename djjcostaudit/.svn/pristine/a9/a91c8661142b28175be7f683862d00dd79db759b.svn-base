$.extend($.fn.validatebox.defaults.rules, {
    //验证汉字
    chinese: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    //手机号码验证
    mobile: {
        validator: function (value) {
            var reg = /^1[3|4|5|7|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确'
    },
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字'
    },
    //用户账号验证(只能包括 _ 数字 字母) 
    account: {
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        },
        
        message: ''
    },
    //只能输入数字、字母、下划线
    charAndNum:{
    	validator: function (value) {
    		var reg = /^[\w]+$/;
    		return reg.test(value);
        },
        message: '请输入数字、字母、下划线'
    },
   //只能输入数字或带有两位小数点的数字
    number:{
    	validator: function (value) {
    		if(value.indexOf(".") > -1){
    			if(!/^[0-9]+[\.]{1}[0-9]{1,2}$/.test(value)){
    				 $.fn.validatebox.defaults.rules.number.message = '小数位数为1-2位';
    				 return false;
    			}else
    				return true;
    		}else{
    			if(!/^[0-9]+$/.test(value)){
    				 $.fn.validatebox.defaults.rules.number.message = '请输入数字';
    				 return false;
    			}else
    				return true;
    		}
        },
        message: ''
    },
    //只能输入字母
    letter:{
    	validator: function (value) {
    		var reg = /^[a-z|A-Z]+$/;
    		return reg.test(value);
        },
        message: '请输入字母'
    },
  //网址url
    url:{
    	validator: function (value) {
    		var reg = /^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?$/;
    		return reg.test(value);
        },
        message: '请输入正确网址'
    },
  //数子
    int:{
    	validator: function (value) {
    		var reg = /^[0-9]+$/;
    		return reg.test(value);
        },
        message: '请输入数字'
    },
    //邮箱
    email:{
    	validator: function (value) {
    		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    		return reg.test(value);
        },
        message: '请输入正确邮箱'
    },
    //日期(yyyy-MM-dd)
    date:{
    	validator: function (value) {
    		if(value){
    			var reg = /^\d{4}-\d{1,2}-\d{1,2}$/;
        		if(reg.test(value)){
        			var date = value.split("-");
        			if(date){
        				var year = date[0];
            			var month = date[1];
            			var day = date[2];
            			if(year >= 1970 && year <= 2099){
            				if(month > 0 && month <= 12){
            					if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            						if(day > 31 || day < 1){
            							$.fn.validatebox.defaults.rules.date.message = '请输入1-31之间的天数';
            	            			return false;
            						}else{
            							return true;
            						}
            					}else if(month == 2){
            						if(year%4 == 0){
            							if(day > 29 || day < 1){
                							$.fn.validatebox.defaults.rules.date.message = '请输入1-29之间的天数';
                	            			return false;
                						}else{
                							return true;
                						}
            	        			}else{
            	        				if(day > 28 || day < 1){
                							$.fn.validatebox.defaults.rules.date.message = '请输入1-28之间的天数';
                	            			return false;
                						}else{
                							return true;
                						}
            	        			}
            					}else if(month == 4 || month == 6 || month == 9){
            						if(day > 30 || day < 1){
            							$.fn.validatebox.defaults.rules.date.message = '请输入1-30之间的天数';
            	            			return false;
            						}else{
            							return true;
            						}
            					}
            				}else{
            					$.fn.validatebox.defaults.rules.date.message = '请输入1-12之间的月份';
                    			return false;
            				}
            			}else{
            				$.fn.validatebox.defaults.rules.date.message = '请输入1970-2099之间的年份';
                			return false;
            			}
        			}
        		}else{
        			$.fn.validatebox.defaults.rules.date.message = '请输入正确日期格式(yyyy-MM-dd)';
        			return false;
        		}
    		}else{
    			$.fn.validatebox.defaults.rules.date.message = '请输入日期';
    			return false;
    		}
        },
        message: ''
    }
});

//返回是否通过验证标示
function validate(){
	var flag = true;
	var ele = null;
	$("input[type='checkbox'][class*='easyui-validatebox'],input[type='radio'][class*='easyui-validatebox'],input[type='text'][class*='easyui-validatebox'],select[class*='easyui-validatebox'],textarea[class*='easyui-validatebox']").each(function(){
		 if($(this).attr('required') || $(this).attr('validType')) {
		    if (!$(this).validatebox('isValid')){
		    	ele = $(this);
		    	$.messager.alert("温馨提示","请填写正确表单信息", "error",function(){
					ele.focus();
				});
		    	flag = false;
		        return false;
		    }
		 }
	 });
	return flag;
}