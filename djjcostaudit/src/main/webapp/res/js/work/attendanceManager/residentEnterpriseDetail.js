var scrollHeight=0;
var str='';
$(function () {
    $(window).resize(function () {
        $("#tt").tabs({
            tabHeight: 32,
            plain: true,
            height: 'auto',
            onSelect:function (title) {
                if(title!=str){
                    $('.wrap_center').scrollTop(scrollHeight);
                    str=title;
                }
            }
        });
    }).resize();
    $(".tabs").on("click","li",function(){
        scrollHeight=$('.wrap_center').scrollTop();
    });
    $('.con').height(500);
    residentPerson();

});
function residentPerson() {
    var bool=false;
    $("#switchBtn1").on('click',function () {
        $("#t0 .filter").toggle();
        if(!bool){
            $(this).addClass("active");
            $('#residentPerson').datagrid('resize', {
                height: 500
            });
            bool=true;
        }else{
            $(this).removeClass("active");
            bool=false;
            $('#residentPerson').datagrid('resize', {
                height: 500
            });
        }
    });
    $('#residentPerson').datagrid({
        url: 'residentPerson.json',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height:  500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        columns: [
            [
                {
                    field: 'id',
                    title : '单选框',
                    checkbox: true
                },{
                field:'name',
                title:'姓名',
                width:100,
                align:'center',
                sortable:false,
                formatter: function (value, rowData, index) {
                        if(value){
                            return "<a href='javascript:void(0)' title='孔祥升' style='color:#549de3;padding-left:2px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;max-width: 100%;vertical-align: middle;' onclick='addPersonTab()'>孔祥升</a>";
                        }else{
                            return ""
                        }
                    }
                },{
                    field:'gender',
                    title:'性别',
                    width:80,
                    align:'center',
                    sortable:false
                },{
                    field: 'post',
                    title: '岗位',
                    width: 80,
                    align: 'center',
                    sortable: false

                }, {
                    field: 'linkTel',
                    title: '联系电话',
                    width: 120,
                    align: 'center',
                    sortable: false

                }, {
                    field: 'mail',
                    title: '邮箱',
                    width: 120,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if(value!=null){
                            return "<span title="+value+" style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">"+value+"</span>";
                        }else{
                            return ""
                        }
                    }
                }, {
                    field: 'weChatNum',
                    title: '微信号',
                    align: 'center',
                    width: 120,
                    sortable: false,

                }, {
                    field: 'QQNum',
                    title: 'QQ号',
                    align: 'center',
                    width: 120,
                    sortable: false,

                }, {
                    field: 'planStartTime',
                    title: '计划驻场开始时间',
                    align: 'center',
                    width: 140,
                    sortable: true,

                }, {
                    field: 'planFinishTime',
                    title: '计划驻场结束时间',
                    align: 'center',
                    width: 140,
                    sortable: true,

                }, {
                    field: 'realStartTime',
                    title: '实际驻场开始时间',
                    align: 'center',
                    width: 140,
                    sortable: true,

                }, {
                    field: 'realFinishTime',
                    title: '实际驻场结束时间',
                    align: 'center',
                    width: 140,
                    sortable: true,

                }, {
                    field: 'residentDays',
                    title: '累计驻场天数',
                    align: 'center',
                    width: 110,
                    sortable: true,

                }, {
                    field: 'isEffective',
                    title: '是否有效',
                    align: 'center',
                    width: 80,
                    sortable: false,

                }
            ]
        ],
        toolbar: "#tit1",
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#residentPerson').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        },

    });
    $(window).resize(function () {
        $('#residentPerson').datagrid('resize', {
            height: 500
        });
    });
}
function addTab() {
    window.parent.addTabGrid('驻场人员添加', 'page/attendanceManager/residentPersonDetail.jsp')
}

function edit() {
    var rows = $('#residentPerson').datagrid('getSelections');
    if (rows == undefined || rows == null || rows.length == 0) {
        $.messager.alert('提示信息', '请选择一条记录', 'warn');
        return;
    }
    if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一条记录', 'warn');
        return;
    }
    editInfo(rows[0].id);
}
function editInfo(zjkClId) {
    var href = "page/attendanceManager/residentPersonDetail.jsp";
    var title = "驻场人员修改";
    top.addTabGrid(title, href);
}
function deleteHandler() {
    var selections = $('#residentPerson').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '确认要删除这' + selections.length + '条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        $.messager.progress();
        var ids = [];
        for (var i in selections) {
            ids[i] = selections[i].id;
        }

        $.ajax({
            url: '/ajax/sat/zhzb/remove.do',
            type: 'POST',
            data: {'satGeneralZbkIds': ids},
            traditional: true,
            success: function (data) {
                $.messager.progress('close');
                if (data.success) {
                    $('#residentPerson').datagrid('reload');
                } else if (data.code == 4) {
                    $.messager.alert('提示信息', '不能删除已发布记录！', 'warn');
                }
            }
        });
    });
}