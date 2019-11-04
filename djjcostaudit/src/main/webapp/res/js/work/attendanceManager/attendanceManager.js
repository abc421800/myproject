var myview = $.extend({}, $.fn.datagrid.defaults.view, {

    renderFooter: function (target, container, frozen) {

        var opts = $.data(target, 'datagrid').options;

        var rows = $.data(target, 'datagrid').footer || [];

        var fields = $(target).datagrid('getColumnFields', frozen);

        var table = ['<table class="datagrid-ftable" cellspacing="0" cellpadding="0" border="0"><tbody>'];


        for (var i = 0; i < rows.length; i++) {

            var styleValue = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : '';

            var style = styleValue ? 'style="' + styleValue + '"' : '';

            table.push('<tr class="datagrid-row" datagrid-row-index="' + i + '"' + style + '>');

            table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));

            table.push('</tr>');

        }
        table.push('</tbody></table>');

        $(container).html(table.join(''));

    }

});

$(function () {
    personAccount();
});

function personAccount() {

    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#personAccount').datagrid('resize', {
                height: $(window).height() - 60
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#personAccount').datagrid('resize', {
                height: tableHeight()
            });
        }
    });

    
    $.fn.datagrid.defaults.onHeaderContextMenu =null;
    $('#personAccount').datagrid({
        url: '/workAttendancePerson/listPerson',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: false,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 50,
        singleSelect: false,
        showFooter: true,
        queryParams:{
            regYear:currentYear,
        },
        frozenColumns: [[
            {
                field: 'id',
                title: '单选框',
                checkbox: true
            }, {
                field: 'name',
                title: '姓名',
                width: 100,
                align: 'center',
                formatter: function (value, rowData, index) {
                    if (value != "汇总") {
                        if (value == undefined) {
                            return "";
                        }
                        if(rowData.status=="无效"){
                        	return "<a href='javascript:void()' title='" + value + "' style='color:darkgray;padding-left:2px;display: inline-block;' onclick='addPersonTab(\"" + rowData.id + "\")'>" + value + "</a>";
                        }
                        return "<a href='javascript:void()' title='" + value + "' style='color:#549de3;padding-left:2px;display: inline-block;' onclick='addPersonTab(\"" + rowData.id + "\")'>" + value + "</a>";
                    } else {
                        return "汇总";
                    }
                },
            },
            {
                field: 'enterpriseName',
                title: '驻场企业名称',
                width: 180,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if(value!=null){
                        return "<a href=\"javascript:void()\" title="+value+" style=\"color:#549de3;padding-left:2px;" +
                            "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                            "max-width: 100%;vertical-align: middle;\" onclick=\"editInfo('"+rowData.enterpriseId+"')\">"+value+"</a>";
                    }else{
                        return ""
                    }
                }
            }
        ]],
        columns: [
            [
                {
                    field: 'annualTotal',
                    title: '总年假天数',
                    width: 120,
                    align: 'center',
                    sortable: false,
                    rowspan: 2
                },
                {
                    title: '年度统计',
                    align: 'center',
                    width: 300,
                    sortable: false,
                    colspan: 5

                },
                {
                    title: '1月',
                    align: 'center',
                    width: 240,
                    sortable: false,
                    colspan: 4

                }, {
                title: '2月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '3月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '4月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '5月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '6月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '7月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '8月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '9月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '10月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '11月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            }, {
                title: '12月',
                align: 'center',
                width: 240,
                sortable: false,
                colspan: 4

            },
                {
                    field: 'raking',
                    title: '名次',
                    width: 120,
                    align: 'center',
                    sortable: false,
                    rowspan: 2
                },
                {
                    field: 'status',
                    title: '状态',
                    width: 120,
                    align: 'center',
                    sortable: false,
                    rowspan: 2,
                    formatter: function (value, rowData, index) {
                        if(value=="无效"){
                            return "<span style='color:red'>"+value+"</span>";
                        }else{
                            return value
                        }
                    }
                }


            ], [
                //年度统计
                {
                    field: 'yearOfWork',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if(rowData.name=="汇总"){
                            return "<span style='color:red\n'>" + value + "</span>";
                        }else{
                            return "<span style='color:#246aad\n'>" + value + "</span>";
                        }
                    }
                },
                {
                    field: 'yearOfRest',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if(rowData.name=="汇总"){
                            return "<span style='color:red\n'>" + value + "</span>";
                        }else{
                            return "<span style='color:#246aad\n'>" + value + "</span>";
                        }
                    }
                },
                {
                    field: 'yearOfLeave',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if(rowData.name=="汇总"){
                            return "<span style='color:red\n'>" + value + "</span>";
                        }else{
                            return "<span style='color:#246aad\n'>" + value + "</span>";
                        }
                    }

                },
                {
                    field: 'yearOvertime',
                    title: '年休假',
                    width: 60,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if(rowData.name=="汇总"){
                            return "<span style='color:red\n'>" + value + "</span>";
                        }else{
                            return "<span style='color:#246aad\n'>" + value + "</span>";
                        }
                    }
                },
                {
                    field: 'attendanceRate',
                    title: '出勤率',
                    width: 60,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if(rowData.name=="汇总"){
                            return "<span style='color:red\n'>" + value + "</span>";
                        }else{
                            return "<span style='color:#246aad\n'>" + value + "</span>";
                        }
                    }
                },
                //1月
                {
                    field: 'monthOfWorkday1',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday1',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave1',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime1',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //2月
                {
                    field: 'monthOfWorkday2',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday2',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave2',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime2',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //3月
                {
                    field: 'monthOfWorkday3',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday3',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave3',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime3',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //4月
                {
                    field: 'monthOfWorkday4',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday4',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave4',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime4',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //5月
                {
                    field: 'monthOfWorkday5',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday5',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave5',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime5',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //6月
                {
                    field: 'monthOfWorkday6',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday6',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave6',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime6',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //7月
                {
                    field: 'monthOfWorkday7',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday7',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave7',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime7',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //8月
                {
                    field: 'monthOfWorkday8',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday8',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave8',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime8',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //9月
                {
                    field: 'monthOfWorkday9',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday9',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave9',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime9',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //10月
                {
                    field: 'monthOfWorkday10',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday10',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave10',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime10',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //11月
                {
                    field: 'monthOfWorkday11',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday11',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave11',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime11',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                //12月
                {
                    field: 'monthOfWorkday12',
                    title: '上班',
                    width: 60,
                    align: 'center',
                    sortable: false,
                },
                {
                    field: 'monthOfRestday12',
                    title: '休息',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
                {
                    field: 'monthOfLeave12',
                    title: '请假',
                    width: 60,
                    align: 'center',
                    sortable: false

                },
                {
                    field: 'monthOfOvertime12',
                    title: '加班',
                    width: 60,
                    align: 'center',
                    sortable: false
                },
            ]
        ],
        toolbar: '#tit1',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#personAccount').datagrid('loadData', {total: 1, rows: [{annual: "未找到相关信息！"}]});
            }
        },
    });
    $(window).resize(function () {
        if (bool) {
            $('#personAccount').datagrid('resize', {
                height: $(window).height() - 40
            });
        } else {
            $('#personAccount').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
}

//查询
function search() {
    var name = $("#name").val().trim();
    var enterpriseName = $("#enterpriseName").val().trim();
    var morningOrAfternoon = $("#morningOrAfternoon").val().trim();
    var regYear = $("#regYear").val();
    var status = $("#status").val();
    var attStarttime = $("#attStarttime").val();
    var attEndtime = $("#attEndtime").val();
    $('#personAccount').datagrid({
        queryParams: {
            name: name,
            enterpriseName: enterpriseName,
            morningOrAfternoon: morningOrAfternoon,
            regYear: regYear,
            status: status,
            attStarttime: attStarttime,
            attEndtime: attEndtime
        }
    });
}

//导出excel表格
function exportData() {
    var regYear=$("#regYear").val();
    var selections = $('#personAccount').datagrid('getSelections');
    var personIds = "";
    if (selections.length != 0) {
        for(var i = 0;i<selections.length;i++){
            personIds+=selections[i].personId+",";
        }
        personIds = personIds.substring(0,personIds.length-1);
    }
    window.location.href = "/workAttendancePerson/exportData.do?regYear="+regYear+"&personIds="+personIds;
}

function editInfo(id) {
    var href = "/workEnterprise/toEdit?id="+id;
    var title = "驻场企业修改";
    top.addTabGrid(title, href);
}
//重置
function refresh() {
    var json1 = {tabTitle: '考勤台账', url: '/forward_attendanceManager_attendanceManager'};
    window.parent.refreshTab(json1);
}

function addPersonTab(id) {
    top.addTabGrid('驻场人员修改', '/workAttendancePerson/toPersonEdit?id=' + id);
}