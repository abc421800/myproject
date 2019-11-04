var scrollHeight=0;
var str='';
function showMessager(title, msg, timeout, showType) {
    $.messager.show({
        title : title,
        msg : msg,
        timeout : timeout,
        showType : showType
    });
}

$(function () {
    //showMessager("温馨提示", "<b>你好，你提交的请假申请已经审批了，单号：<a href='javascript:void(0)' onclick='addDetailTab()'>19-05-001</a></b><div style='text-align: right'>请点击查看</div>", 5000, "slide");
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
    leaveRequestCreat();

});
function leaveRequestCreat() {
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#leaveRequestCreat').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#leaveRequestCreat').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#leaveRequestCreat').datagrid({
        url: '/workProcessApply/list?flag=apply',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: false,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        frozenColumns:[[
            {
                field: 'id',
                title : '单选框',
                checkbox: true
            },{
                field: 'status',
                title: '状态',
                align: 'center',
                width: 100,
                sortable: false,
                formatter: function (value, rowData, index) {
                    if(value){
                        if(value=="重启审批通过"){
                            value="重启结束"
                        }else if(value=="审批通过"){
                            value="结束"
                        }
                        if(value=='结束' || value=='重启结束'|| value=='销假'){
                            return "<a href='javascript:void(0)' class='btn btn-outline-secondary' onclick='addDetailTab(\"" + rowData.id + "\",\"" + rowData.status + "\",\"" + rowData.proposer + "\",\"" + rowData.nextperson + "\",\"" + rowData.relatedId + "\")'>"+value+"</a>";
                        }else if(value=='驳回'||'重启驳回'==value){
                            return "<a href='javascript:void(0)' class='btn btn-outline-danger' onclick='addDetailTab(\"" + rowData.id + "\",\"" + rowData.status + "\",\"" + rowData.proposer + "\",\"" + rowData.nextperson + "\",\"" + rowData.relatedId + "\")'>"+value+"</a>";
                        }else if(value=='审批中' || value=='重启审批中' || value=='销假审批中' || value=='变更审批中'){
                            if(currentUser==rowData.nextperson){
                                return "<a href='javascript:void(0)' class='btn btn-outline-info' onclick='addDetailTab(\"" + rowData.id + "\",\"" + rowData.status + "\",\"" + rowData.proposer + "\",\"" + rowData.nextperson + "\",\"" + rowData.relatedId + "\")'>待办</a>";
                            }else{
                                return "<a href='javascript:void(0)' class='btn btn-outline-warning' onclick='addDetailTab(\"" + rowData.id + "\",\"" + rowData.status + "\",\"" + rowData.proposer + "\",\"" + rowData.nextperson + "\",\"" + rowData.relatedId + "\")'>"+value+"</a>";
                            }
                        }
                    }else{
                        return ""
                    }
                }

            },{
                field:'code',
                title:'请假单编号',
                width:150,
                align:'center',
                sortable:false,
                formatter: function (value, rowData, index) {
                    if(value){
                        return "<a href='javascript:void(0)' title='"+value+"' style='color:#549de3;padding-left:2px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;max-width: 100%;vertical-align: middle;' onclick='addDetailTab(\"" + rowData.id + "\",\"" + rowData.status + "\",\"" + rowData.proposer + "\",\"" + rowData.nextperson + "\",\"" + rowData.relatedId + "\")'>"+value+"</a>";
                    }else{
                        return ""
                    }
                }
            },{
                field:'proposerName',
                title:'申请人',
                width:100,
                align:'center',
                sortable:false
            }
        ]],
        columns: [
            [
                {
                    field: 'enterpriseName',
                    title: '驻场企业名称',
                    width: 180,
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
                field: 'proPhone',
                title: '联系电话',
                width: 150,
                align: 'center',
                sortable: false

            }, {
                field: 'kxnj',
                title: '可休年假（天）',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if(value!=null){
                        var annualLeaveSure=rowData.annualLeaveSure;
                        if(rowData.status=='结束'){
                            return annualLeaveSure;
                        }else{
                            return annualLeaveSure;
                        }
                    }else{
                        return ""
                    }
                }

            }, {
                field: 'type',
                title: '请假类型',
                align: 'center',
                width: 100,
                sortable: false,

            }, {
                field: 'leaveStartTimeStr',
                title: '请假开始时间',
                align: 'center',
                width: 150,
                sortable: false,

            }, {
                field: 'leaveEndTimeStr',
                title: '请假结束时间',
                align: 'center',
                width: 150,
                sortable: false,

            }, {
                field: 'leaveDays',
                title: '请假时间（天）',
                align: 'center',
                width: 120,
                sortable: false

            }, {
                field: 'createTimeStr',
                title: '创建时间',
                align: 'center',
                width: 150,
                sortable: false,

            }, {
                field: 'nextpersonName',
                title: '审批人',
                align: 'center',
                width: 120,
                sortable: false,

            },{
                field: 'relatedName',
                title: '传阅对象',
                align: 'center',
                width: 120,
                sortable: false,

            }
            ]
        ],
        toolbar: "#tit1",
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#leaveRequestCreat').datagrid('loadData', {total: 1, rows: [{enterpriseName: "未找到相关信息！"}]});
            }
        },

    });
    $(window).resize(function () {
        if(bool){
            $('#leaveRequestCreat').datagrid('resize', {
                height: $(window).height() - 40
            });
        }else{
            $('#leaveRequestCreat').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
}
function addDetailTab(id,status,proposer,nextperson,relatedId) {
    if('驳回'==status||'重启驳回'==status){
        if(currentUser==proposer){
            window.parent.addTabGrid('请假申请详情', '/workProcessApply/toTaskDetailsRelevant?id='+id)
        }else{
            window.parent.addTabGrid('请假申请详情', '/workProcessApply/toTaskDetailsShow?id='+id)
        }
    }else if('审批中'==status || '重启审批中'==status ||'变更审批中'==status||'销假审批中'==status||'销假'==status||'重启结束'==status){
        if(currentUser==nextperson){
            window.parent.addTabGrid('请假申请审批', '/workProcessApply/toTaskDetails?id='+id)
        }else{
            window.parent.addTabGrid('请假申请详情', '/workProcessApply/toTaskDetailsShow?id='+id)
        }
    }else{
        window.parent.addTabGrid('请假申请详情', '/workProcessApply/toTaskDetailsShow?id='+id)
    }
}

function edit() {
    var rows = $('#leaveRequestCreat').datagrid('getSelections');
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
    var href = "page/attendanceManager/leaveRequestCreatDetail.html";
    var title = "驻场人员修改";
    top.addTabGrid(title, href);
}
function deleteHandler() {
    var selections = $('#leaveRequestCreat').datagrid('getSelections');
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
                    $('#leaveRequestCreat').datagrid('reload');
                } else if (data.code == 4) {
                    $.messager.alert('提示信息', '不能删除已发布记录！', 'warn');
                }
            }
        });
    });
}
function getDays(strDateStart,strDateEnd){
    var strSeparator = "-"; //日期分隔符
    var oDate1;
    var oDate2;
    var iDays;
    oDate1= strDateStart.split(strSeparator);
    oDate2= strDateEnd.split(strSeparator);
    var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
    var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
    iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数
    return iDays+1 ;
}
function editInfo(id) {
    var href = "/workEnterprise/toEdit?id="+id;
    var title = "驻场企业修改";
    top.addTabGrid(title, href);
}
//查询
function searchPro(){
    var proposerName = $("#proposerName").val().trim();
    var createStartTime = $("#createStartTime").val().trim();
    var createEndTime = $("#createEndTime").val().trim();
    var status=$("#status").val();
    var type=$("#type").val();
    $('#leaveRequestCreat').datagrid({
        queryParams: {
            proposerName:proposerName,
            startTime:createStartTime,
            endTime:createEndTime,
            type:type,
            status:status
        }
    });
}
//重置
function refresh() {
    var json1={tabTitle:'请假申请',url:'/forward_processApply_list'};
    window.parent.refreshTab(json1);
}