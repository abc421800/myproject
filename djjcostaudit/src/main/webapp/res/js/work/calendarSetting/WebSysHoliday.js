﻿$(function () {
    initCMBYear();
    initPage(); //初始化页面
    initWorkDay();
});
//初始化页面
function initPage() {
    $.ajax({
		  url: "/workCalendar/list",
		  dataType: "json",
		  type:"post",
		  async: false,
		  //data: {"id":1},
		  success: function(data){
			  loadCMBYearData(data);
		  }
	});
}

//#region "年份"
var cuurdate=new Date;
var cyear=cuurdate.getFullYear(); 
var cmbYearDefualtValue=cyear;
function initCMBYear() {
    $('#Year').combobox({
        value: (cmbYearDefualtValue == undefined ? '' : cmbYearDefualtValue),
        onLoadSuccess: function (data) {
            if (cmbYearDefualtValue == undefined && data.length > 0) {
                $(this).combobox("setValue", data[0].id);
                setWorkDay(data[0].weekDays);
                setHoliday(data[0].excepDays);
            } else if (cmbYearDefualtValue != undefined && data.length > 0) {
                $(this).combobox("select", cmbYearDefualtValue);
            }
        },
        onSelect: function (record) {
            setWorkDay(record.weekDays);
            setHoliday(record.excepDays);
        }
    })
}
function loadCMBYearData(data) {
    $('#Year').combobox('loadData', data);
}
function msgPrompt(title, msg, callBack) {
    $.messager.prompt(title, msg, function (r) {
        if (r) {
            callBack(r);
        }
    });
}
function addYear() {
    msgPrompt("添加例外年份", "请输入年份", function (r) {
        if (!isNaN(r)) {
            var date = new Date(r);
            var year = date.getFullYear();
            console.log(year);
            var AllYear = $('#Year').combobox('getData');
            //var nowYear = $('#Year').combobox('getText');
            for (var i = 0; i < AllYear.length; i++) {
                if (AllYear[i].year == year) {
                    $('#Year').combobox('select', AllYear[i].id);
                    return;
                }
            }
            var arrExcepDays0=getAllWeekDayOfWholeYear(year,0);
            var arrExcepDays6=getAllWeekDayOfWholeYear(year,6);
            var arrStr0=arrExcepDays0.join(",");
            var arrStr6=arrExcepDays6.join(",");
            var allStr=arrStr0+","+arrStr6;
            $.post("/workCalendar/save", {
                year: year,
                weekDays:"1:0:0:0:0:0:1",
                excepDays:allStr
            }, function (data) {
                if (data.status==200) {
                	$('#Year').combobox("setValue", year);
                	cmbYearDefualtValue = $('#Year').combobox('getValue');
   		         	initPage();
                } else {
                    msgAlert("提示", "添加年份失败！", "info");
                }
            });
        } else {
            msgAlert("提示", "请输入年份！", "info");
        }
    });
}

function remove(){
	var id = $('#Year').combobox('getValue');
	 $.messager.confirm('提示信息', '确定要删除 ' + id + ' 年份数据吗？', function (isOk) {
		 if (!isOk) {
            return;
         }
		 $.post("/workCalendar/del", {
	         year: id
	     }, function (data) {
	         if (data.status==200) {
	         		$('#Year').combobox("setValue", '2019');
	         		cmbYearDefualtValue = $('#Year').combobox('getValue');
		         	initPage();
	         } else {
	             msgAlert("提示", "删除年份失败！", "info");
	         }
	     });
	 });
}
//#region  "获取传入年的所有固定星期值"
function getAllDay(_year, _day) {
    var _leap = _year % 4 === 0 && _year % 100 !== 0;
    var _all = [];
    var _end;
    for (var _month = 1; _month <= 12; ++_month) {
        switch (_month) {

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                _end = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                _end = 30;
                break;
            case 2:
                _end = _leap ? 29 : 28;
        }
        for (var _date = 1; _date <= _end; ++_date) {
            _all.push(
				_year + '-' + (_month > 9 ? _month : '0' + _month) + '-' + (_date > 9 ? _date : '0' + _date));
        }
    }
    var _begin = new Date;
    _begin.setFullYear(_year);
    _begin.setMonth(0);
    _begin.setDate(_day);
    _begin = [0, 1, 2, 3, 4, 5, 6][_begin.getDay()]; //1日2一3二4三5四6五7六
    //_begin = [0, 6, 5, 4, 3, 2, 1][_begin.getDay()];1日2六3五4四5三6二7一
    _end = _leap ? 366 : 365;
    var retDay = [];
    while (_begin < _end) {
        retDay.push(_all[_begin]);
        _begin += 7;
    }
    return retDay;
    //document.write(retDay.join('<br>'));
}
/*
*   获取全年对应星期日期集合
*   year：要获取的年份
*   dayIndex：星期序号（周日-周六：0-6）
*   返回对应日期集合 
*/
function getAllWeekDayOfWholeYear(year, dayIndex) {
    var arSundays = []; //返回日期集合
    //第一部分：
    //  var date = new Date(year, 0, 1),    //定义变量为当前年第一月第一天
    //  day = date.getDay(),    //获取日期的星期序号
    //  _ = day != dayIndex && date.setDate(date.getDate() + (dayIndex == 0 ? 7 : dayIndex) - day);  //获取第一个月第一个所要获取的星期
    //第二部分：date.getFullYear() === year; //条件
    //第三部分：date.setDate(date.getDate() + 7) //设置日期
    for (var date = new Date(year, 0, 1), day = date.getDay(), _ = day != dayIndex && date.setDate(date.getDate() + (dayIndex == 0 ? 7 : dayIndex) - day); date.getFullYear() == year; date.setDate(date.getDate() + 7)) {
        arSundays.push(date.Format("yyyy-MM-dd"));
    }
    return arSundays;
}
//#endregion
//#endregion
//#region "工作日"
function initWorkDay() {
    $('#Main input[type="checkbox"]').change(function () {
        //console.log("checkbox change");
        //复选框与日历显示联动
        var _year = $('#Year').combobox('getText');
        var pp = $(this).parent().parent();
        if (pp) {
            var cc = $(pp).index();
            var excepDays = yearKalendae.getSelectedAsText(); //当前选中的日期
            //var days = getAllDay(_year, cc + 1);  //星期对应的全年日期
            var days = getAllWeekDayOfWholeYear(_year, cc);
            if (!$(this).is(':checked')) {  //星期未选中：设置为非工作日（值为：1）
                //原来数据+ 星期对应全年日期
                var moment = yearKalendae.getMoment();
                for (var i = 0; i < days.length; i++) {
                    date = moment(days[i], yearKalendae.settings.format).hours(12); //转换为对象
                    var arr = $.grep(yearKalendae._sel, function (value) {
                        return value._i == date._i;
                    })  //找到与date 相同项
                    if (arr.length == 0) {  //没有找到，则添加
                        yearKalendae._sel.push(date);
                    }
                }
                yearKalendae.draw();
            }
            else {  //选中：设置为工作日（值为：0）
                //原来数据 - 星期对应全年日期
                yearKalendae._sel = $.grep(yearKalendae._sel, function (value) {
                    return days.indexOf(value._i) == -1;
                });
                yearKalendae.draw();
            }
        }
        saveChangeData();
    });
}
function setWorkDay(WeekDays) {
    if (WeekDays.length > 0) {
        var weekArry = WeekDays.split(":");
        $('#Main input[type="checkbox"]').each(function (index) {
            $(this).prop("checked", weekArry[index] == 0);
        })
    }
}
//#endregion
//#region "节假日"
function setHoliday(ExcepDays) {
    if (yearKalendae) {
        yearKalendae.setSelected(ExcepDays);
        //        if (!$.isArray(ExcepDays)) {
        //            ExcepDays = ExcepDays.split(",");
        //        }
        //        var moment = yearKalendae.getMoment();
        //        for (var i = 0; i < ExcepDays.length; i++) {
        //            var date = moment(ExcepDays[i], yearKalendae.settings.format).hours(12);
        //            yearKalendae._sel.push(date);
        //            //yearKalendae.publish('change', yearKalendae, [date]);
        //        }
        //        yearKalendae._sel.sort(function (a, b) { return a.startOf('day').yearDay() - b.startOf('day').yearDay(); });
        //        yearKalendae.draw();
    }
}
//#endregion

//#region "保存修改后的数据"
function saveChangeData() {
    var id = $('#Year').combobox('getValue');
    var weekday = [];
    $('#Main input[type="checkbox"]').each(function (index) {  //判断工作日复选框是否选择
        if ($(this).is(':checked')) {
            weekday.push(0);
        } else {
            weekday.push(1);
        }
        // $(this).is(':checked') ? weekday.push(1) : weekday.push(0);
    })
    //console.log(weekday);
    var excepDays = yearKalendae.getSelected();
    console.log(excepDays);
    var jsonStr = { WeekDays: weekday.join(":"), ExcepDays: excepDays };
    var id = $('#Year').combobox('getValue');
    $.ajax({
		  url: "/workCalendar/upd",
		  dataType: "json",
		  type:"post",
		  async: false,
		  data: {"id":id,"weekDays":weekday.join(":"),"excepDays":excepDays},
		  success: function(result){
			 if(result.status==200){
				 cmbYearDefualtValue = $('#Year').combobox('getValue');
		         initPage();
			 }else{
				$.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
			 }
		  }
	});
}
//#endregion
function msgAlert(title, msg, icon, callBack) {
    $.messager.alert(title, msg, icon, callBack);
}
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}