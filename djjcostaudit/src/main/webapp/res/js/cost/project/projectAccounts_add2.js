var scrollHeight = 0;
var str = '';
var projId = $("#projId").val();
$(function () {
    $(window).resize(function () {
        $("#tt").tabs({
            tabHeight: 32,
            plain: false,
            height: 'auto',
            tools: '.tab-tools',
            onSelect: function (title) {
                if (title != str) {
                    $('.wrap_center').scrollTop(scrollHeight);
                    str = title;
                }
            }
        });
    }).resize();
    $(".tabs").on("click", "li", function () {
        scrollHeight = $('.wrap_center').scrollTop();
    });
    $('.con').height(500);
    unitProjectAllList();
    contentTable();

    $("#save").click(function () {
        var description = $("#description").val();
        var name = $("#name").val();
        var code = $("#code").val();
        var projectOwner = $("#projectOwner").val();
        var area = $("#area").val();
        var xjGsJe = Number($("#xjGsJe").val());
        var xjGsGcf = Number($("#xjGsGcf").val());
        var xjGsElfy = Number($("#xjGsElfy").val());
        var xjGsSlfy = Number($("#xjGsSlfy").val());
        var kyGsJe = Number($("#kyGsJe").val());
        var kyGsGcf = Number($("#kyGsGcf").val());
        var kyGsElfy = Number($("#kyGsElfy").val());
        var kyGsSlfy = Number($("#kyGsSlfy").val());
        var sumGsJe = Number($("#sumGsJe").val());
        var gsGcf = Number($("#gsGcf").val());
        var gsElfy = Number($("#gsElfy").val());
        var gsSlfy = Number($("#gsSlfy").val());
        var unit = $("#unit option:selected").val();
        var area = $("#area option:selected").val();
        if (name == "") {
            $.messager.alert({title: '温馨提示', msg: '请填写项目名称 ！', icon: 'error', top: 100});
            return;
        }
        if (code == "") {
            $.messager.alert({title: '温馨提示', msg: '请填写项目代号！', icon: 'error', top: 100});
            return;
        }
        if (projectOwner == "") {
            $.messager.alert({title: '温馨提示', msg: '请填写项目业主 ！', icon: 'error', top: 100});
            return;
        }
        if (xjGsJe > 0 && (xjGsGcf > 0 || xjGsElfy > 0 || xjGsSlfy > 0)) {
            if ((xjGsGcf + xjGsElfy + xjGsSlfy).toFixed(2) != xjGsJe) {
                $.messager.alert({
                    title: '温馨提示',
                    msg: '项建估算金额与项建估算工程费、项建估算二类费用、项建估算三类费用之和不相等！',
                    icon: 'error',
                    top: 100
                });
                return;
            }
        }
        if (kyGsJe > 0 && (kyGsGcf > 0 || kyGsElfy > 0 || kyGsSlfy > 0)) {
            if ((kyGsGcf + kyGsElfy + kyGsSlfy).toFixed(2) != kyGsJe) {
                $.messager.alert({
                    title: '温馨提示',
                    msg: '可研估算金额与可研估算工程费、可研估算二类费用、可研估算三类费用之和不相等！',
                    icon: 'error',
                    top: 100
                });
                return;
            }
        }
        if (sumGsJe > 0 && (gsGcf > 0 || gsElfy > 0 || gsSlfy > 0)) {
            if ((gsGcf + gsElfy + gsSlfy).toFixed(2) != sumGsJe) {
                $.messager.alert({title: '温馨提示', msg: '概算金额与概算工程费、概算二类费用、概算三类费用之和不相等！', icon: 'error', top: 100});
                return;
            }
        }
        if (unit == "") {
            $.messager.alert({title: '温馨提示', msg: '请选择单位 ！', icon: 'error', top: 100});
            return;
        }
        if (area == "") {
            $.messager.alert({title: '温馨提示', msg: '请选择工程所在地！', icon: 'error', top: 100});
            return;
        }
        $.ajax({
            url: "/costProject/updateProject",
            dataType: "json",
            type: "post",
            async: false,
            data: $("#formId").serialize(),
            success: function (result) {
                if (result.status == 200) {
                    var json1 = {tabTitle: '项目台账', url: '/forward_project_list'};
                    window.parent.refreshTab(json1);
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                } else if (result.status == 300) {
                    $.messager.alert({title: '温馨提示', msg: '已存在该有效项目代号,请不要重复添加！', icon: 'error', top: 100});
                    return;
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }
        });
    });
});

function unitProjectAllList() {
    $('#unitProjectAllList').datagrid({
        url: 'costContentTemplate/getUnitByProjectId?projId='+projId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: false,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        rowStyler:function(rowIndex,rowData) {
            if (rowData.name=="合计") {
                return 'background:#ffe48d';
            }
        },
        columns: [
            [{
                field: 'number',
                title: '序号',
                width: 50,
                align: 'center',
                sortable: false
            }, {
                field: 'name',
                title: '项目名称',
                width: 220,
                align: 'center',
                sortable: false,
            }, {
                field: 'subProjectCost',
                title: '分部分项工程费<br>（万元）',
                width: 150,
                align: 'center',
                sortable: false
            }, {
                field: 'stepItemCost',
                title: '措施项目费<br>（万元）',
                width: 130,
                align: 'center',
                sortable: false
            }, {
                field: 'otherProjectFee',
                title: '其他项目费<br>（含5%暂列金）（万元）',
                width: 200,
                align: 'center',
                sortable: false
            }, {
                field: 'feesTaxes',
                title: '规费及税金<br>（万元）',
                width: 130,
                align: 'center',
                sortable: false
            }, {
                field: 'count',
                title: '合计（万元）',
                width: 130,
                align: 'center',
                sortable: false
            }, {
                field: 'unitProjectPercen',
                title: '单位工程占<br>总投资比例',
                width: 130,
                align: 'center',
                sortable: false
            }, {
                field: 'coveredArea',
                title: '建筑面积<br>（m2）',
                width: 130,
                align: 'center',
                sortable: false
            }, {
                field: 'unilateralIndicators',
                title: '单方指标<br>（元/m2）',
                width: 130,
                align: 'center',
                sortable: false
            }, {
                field: 'remark',
                title: '备注',
                width: 130,
                align: 'center',
                sortable: false
            }
            ]
        ]
    });
    $(window).resize(function () {
        $('#unitProjectAllList').datagrid('resize', {
            height: 500
        });
    });
}

function contentTable() {
    $('#contentTable').datagrid({
        url: "costContentTemplate/getContentByProjectId?projId="+projId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: false,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
            [{
                field: 'number',
                title: '序号',
                width: 50,
                align: 'center',
                sortable: false
            }, {
                field: 'name',
                title: '名称',
                width: 200,
                align: 'center',
                sortable: false,
                // formatter:function(value,rowData,rowIndex){
                //     return "<a href='javascript:void()' style='color:#549de3;padding-left:2px;display: inline-block;' onclick='addTab()'>广州市胸科医院整体改造总包合同</a>";
                // }
            }, {
                field: 'content',
                title: '含量',
                width: 120,
                align: 'center',
                sortable: false
            },
                {
                    field: 'unit',
                    title: '单位',
                    width: 120,
                    align: 'center',
                    sortable: false
                }, {
                field: 'remark',
                title: '备注',
                width: 180,
                align: 'center',
                sortable: false
            }
            ]
        ]


    });
    $(window).resize(function () {
        $('#contentTable').datagrid('resize', {
            height: 500
        });
    });
}

function addTab() {

}

function getDialog() {
    $('#dlg').dialog({
        title: '添加项目干系人',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top: 100,
        href: 'projectdialog.html',
        modal: true
    });
}

//  ajax请求后台数据添加输入框提示功能
function assignTask(objectId) {
    $("#nameCon").empty(); //清空
    $.ajax({
        url: "${basePath}monitor/getUserName?objectId=" + objectId,
        type: "Get",
        error: function () {
        },
        success: function (data) {
            var objectname = data.name;
            var modelList = data.list;
            if (modelList != null && modelList.length != 0) {
                for (var i = 0; i < modelList.length; i++) {
                    var option = "<option>" + modelList[i].name + "</option>";
                    $("#nameCon").append(option);
                }
            }
        }
    });
}

function saveFileName() {
    var isRole = $("#roleId").validatebox('isValid');
    var isFunctions = $("#functions").validatebox('isValid');
    var isProName = $("#proName").validatebox('isValid');
    var isLinkTel = $("#linkTel").validatebox('isValid');
    if (!isRole) {
        // $.messager.alert('提示信息', '请选择角色！', 'error');
        return false;
    }
    if (!isFunctions) {
        $.messager.alert('提示信息', '请填写职能！', 'error');
        return false;
    }
    if (!isProName) {
        $.messager.alert('提示信息', '请选择名称！', 'error');
        return false;
    }
    if (!isLinkTel) {
        $.messager.alert('提示信息', '请填写电话号码！', 'error');
        return false;
    }
    $('#dlg').dialog('close');

}
