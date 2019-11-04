var scrollHeight = 0;
var str = '';
var projId = $("#projId").val();
$(function () {
    $(window).resize(function () {
        $("#tt").tabs({
            tabHeight: 32,
            plain: true,
            height: 'auto',
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
    projectPerson();
    projectMaterial();
    projectIndicators(projectId);
    relatedContracts();
    wlfile();
    sjTask();
    $("#save").click(function () {
        var name = $("#name").val();
        var code = $("#code").val();
        var projectOwner = $("#projectOwner").val();
        var personLiableId = $("#personLiableId").val();
        var projectClassificationArray = $("#projectClassificationId").combotree("getValues");
        var allProcess = $("#allProcess option:selected").val();
        var auditPriceUnit = $("#auditPriceUnit").val();
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
        if (personLiableId == "") {
            $.messager.alert({title: '温馨提示', msg: '请填写项目负责人 ！', icon: 'error', top: 100});
            return;
        }
        if (projectClassificationArray == "-1" || projectClassificationArray == null || projectClassificationArray == "") {
            $.messager.alert({title: '温馨提示', msg: '请选择项目分类 ！', icon: 'error', top: 100});
            return;
        }
        if (allProcess == "是" && auditPriceUnit == "") {
            $.messager.alert({title: '温馨提示', msg: '请选择审价单位！', icon: 'error', top: 100});
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
                    // var json1 = {tabTitle: '项目台账', url: '/forward_project_list'};
                    // window.parent.refreshTab(json1);
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

function projectPerson() {
    $('#projectPerson').datagrid({
        url: '/stakelholder/getStakeholderList?projId=' + projId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        toolbar: '#tit1',
        columns: [
            [{field: 'ck', title: '单选框', checkbox: true},
                {
                    field: 'roleName',
                    title: '角色',
                    width: 130,
                    align: 'center',
                    sortable: false
                }, {
                field: 'function',
                title: '职能',
                width: 140,
                align: 'center',
                sortable: false,
            }, {
                field: 'name',
                title: '名称',
                width: 200,
                align: 'center',
                sortable: false,
            }, {
                field: 'phone',
                title: '联系方式',
                width: 130,
                align: 'center',
                sortable: false
            }
            ]
        ]
    });
    $(window).resize(function () {
        $('#projectPerson').datagrid('resize', {
            height: 500
        });
    });
}

function getProjectPersonDialog() {
    $('#projectPersonDialog').dialog({
        title: '添加项目干系人',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top: 100,
        href: '/stakelholder/addStakeholder?projId=' + projId,
        modal: true
    });
}

//  ajax请求后台数据添加输入框提示功能
function assignTask() {
    var userName = $("#userName").val();
    $("#nameCon").empty(); //清空
    $.ajax({
        url: "/stakelholder/getUserName?userName=" + userName,
        type: "Get",
        error: function () {
        },
        success: function (data) {
            console.log(data);
            //var userList = data.userList;
            if (data != null && data.length != 0) {
                for (var i = 0; i < data.length; i++) {
                    var option = "<option>" + data[i].name + "</option>";
                    $("#nameCon").append(option);
                }
            }
        }
    });

}

function projectMaterial() {
    $("#projectMaterial").treegrid({
        url: "/costAttachment/list?typeId=" + projId,
        height: 500,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        lines: true,//下面三个是树属性
        animate: true,
        rownumbers: false,// 行号
        idField: 'id',
        parentField: "_parentId",
        treeField: 'name',
        singleSelect: false,
        toolbar: '#tit3',
        columns: [[
            {field: 'ck', title: '单选框', checkbox: true},
            {
                field: 'name',
                title: '文档标题',
                width: 320,
                halign: 'center',
                formatter: function (value, rowData, index) {
                    return "<span title=" + value + ">" + value + "</span>";
                }
            }, {
                field: 'suffix',
                title: '类型',
                halign: 'center',
                align: 'center',
                width: 100
            }, {
                field: 'size',
                title: '大小',
                halign: 'center',
                align: 'center',
                width: 100,
            }, {
                field: 'creater',
                title: '发布人',
                halign: 'center',
                align: 'center',
                width: 100,
            }, {
                field: 'createrTime',
                title: '发布时间',
                halign: 'center',
                align: 'center',
                width: 100,
                formatter: formatterdate,
            }, {
                field: 'opt',
                title: '操作',
                halign: 'center',
                align: 'center',
                width: 120,
                formatter: function (value, rowData, rowIndex) {
                    if (rowData.isLeaf == "Y") {
                        var a1 = '<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach(' + "'" + rowData.id + "'" + ')">预览</a>&nbsp;&nbsp;';
                        var b = '<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach(' + "'" + rowData.id + "'" + ')">下载</a>&nbsp;&nbsp;';
                        //var c='<a href="javascript:void(0)" class="btn btn-outline-danger" onclick="delFileById('+"'"+rowData.id+"'"+')">删除</a>&nbsp;&nbsp;';
                        return a1 + b;
                    }
                }
            }]],
        onBeforeCheck: function (rows, data) {
            if (rows == null) {
                return true;
            }
            if (rows.isLink == "false") {
                return false;
            }
        },
        onDrop: function (targetRow, sourceRow, point) {
            //alert("原来行名称："+sourceRow.name);
            //alert("原来行名称："+targetRow.name);
            $.ajax({
                type: 'post',
                url: '/costAttachment/findDndTree',
                data: {
                    targetId: targetRow.id,
                    sourceId: sourceRow.id,
                    point: point
                },
                dataType: 'json',
                cache: false,
                success: function (data) {
                    if (data.status == 200) {
                        parent.window.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    } else {
                        $.messager.alert("温馨提示", "删除异常,请联系管理员", "error");
                    }
                }
            });
        },
        onLoadSuccess: function (row) {
            $('#projectMaterial').treegrid('enableDnd', row ? row.id : null);
        }

    });
    $(window).resize(function () {
        $('#projectMaterial').treegrid('resize', {
            height: 500
        });
    });
}

function projectIndicators(projectId) {
    $("#proIndicators").treegrid({
        url: "/costAttachment/list?dataType=proIndicators&typeId=" + projectId,
        height: 500,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        lines: true,//下面三个是树属性
        animate: true,
        rownumbers: false,// 行号
        idField: 'id',
        parentField: "_parentId",
        treeField: 'name',
        singleSelect: false,
        toolbar: '#tit4',
        columns: [[
            {field: 'ck', title: '单选框', checkbox: true},
            {
                field: 'name',
                title: '文档标题',
                width: 320,
                halign: 'center',
                formatter: function (value, rowData, index) {
                    return "<span title=" + value + ">" + value + "</span>";
                }
            }, {
                field: 'suffix',
                title: '类型',
                halign: 'center',
                align: 'center',
                width: 100
            }, {
                field: 'size',
                title: '大小',
                halign: 'center',
                align: 'center',
                width: 100,
            }, {
                field: 'creater',
                title: '发布人',
                halign: 'center',
                align: 'center',
                width: 100,
            }, {
                field: 'createrTime',
                title: '发布时间',
                halign: 'center',
                align: 'center',
                width: 100,
                formatter: formatterdate,
            }, {
                field: 'category',
                title: '操作',
                halign: 'center',
                align: 'center',
                width: 120,
                formatter: function (value, rowData, rowIndex) {
                    if (rowData.isLeaf == "Y") {
                        if(value=="已发布"){
                            var a1 = '<a href="javascript:void(0)" class="btn btn-outline-info" >'+value+'</a>&nbsp;&nbsp;';
                        }else{
                            var a1 = '<a href="javascript:void(0)" class="btn btn-outline-info" onclick="pulish( '+"'"+rowData.id+"','"+projectId+"'"+')">发布</a>&nbsp;&nbsp;';
                        }
                        var b = '<a href="javascript:void(0)" class="btn btn-outline-primary" onclick="previewAttach(' + "'" + rowData.id + "'" + ')">预览</a>&nbsp;&nbsp;';
                        var c = '<a href="javascript:void(0)" class="btn btn-outline-success" onclick="downloadAttach(' + "'" + rowData.id + "'" + ')">下载</a>&nbsp;&nbsp;';
                        return a1 + b + c;
                    }
                }
            }]],
        onBeforeCheck: function (rows, data) {
            if (rows == null) {
                return true;
            }
            if (rows.isLink == "false") {
                return false;
            }
        },
        onDrop: function (targetRow, sourceRow, point) {
            $.ajax({
                type: 'post',
                url: '/costAttachment/findDndTree',
                data: {
                    targetId: targetRow.id,
                    sourceId: sourceRow.id,
                    point: point
                },
                dataType: 'json',
                cache: false,
                success: function (data) {
                    if (data.status == 200) {
                        parent.window.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    } else {
                        $.messager.alert("温馨提示", "删除异常,请联系管理员", "error");
                    }
                }
            });
        },
        onLoadSuccess: function (row,data) {
            $('#projectIndicators').treegrid('enableDnd', row ? row.id : null);
            // var rows1=$('#projectIndicators').treegrid("getRows");
            var n=data.rows;
            if (n.length==0) {
                var projName = "新增项目指标信息";
                var rows = $("#projectMaterial").treegrid("getRows");
                var json1 = {};
                if (rows.length == 0) {
                    json1.name = projName;
                    json1.typeId = projectId;
                    json1.dataType = "proIndicators";
                } else {
                    json1.name = projName;
                    json1.typeId = projectId;
                    json1.pid = rows[0].id;
                }
                //插入附件表
                $.ajax({
                    url: "/costAttachment/save",
                    dataType: "json",
                    type: "post",
                    async: false,
                    data: json1,
                    success: function (result) {
                        if (result.status == 200) {
                            projectIndicators(projectId)
                        }
                    }
                });
            }
        }
    });

    $(window).resize(function () {
        $('#projectIndicators').treegrid('resize', {
            height: 500
        });
    });
}
//添加文件夹名称
function getDialog() {
    $('#dlg').dialog({
        title: '添加文件夹',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top: 100,
        href: '/forward_document_dialog',
        modal: true
    });
}

//项目指标信息添加文件夹
function getDialog(flag) {
    $('#dlg').dialog({
        title: '添加文件夹',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top: 100,
        href: '/forward_document_dialogZb?flag=' + flag,
        modal: true
    });
}

//修改文件夹名称
function getEditDialog() {
    var rows = $("#projectMaterial").treegrid("getSelections");
    if (rows.length == 1 && rows[0].suffix == "文件夹") {
        $('#editDialog').dialog({
            title: '修改文件夹',
            width: 600,
            height: "auto",
            closed: false,
            cache: false,
            top: 100,
            href: "/costAttachment/toEdit?id=" + rows[0].id,
            modal: true
        });
    } else {
        $.messager.alert("温馨提示", "请选中一个文件夹!", "error");
        return;
    }
}

//项目指标信息修改文件夹
function getEditDialog(flag) {
    var rows = $("#proIndicators").treegrid("getSelections");
    if (rows.length == 1 && rows[0].suffix == "文件夹") {
        $('#editDialog').dialog({
            title: '修改文件夹',
            width: 600,
            height: "auto",
            closed: false,
            cache: false,
            top: 100,
            href: "/costAttachment/toEdit?flag=" + flag + "&id=" + rows[0].id,
            modal: true
        });
    } else {
        $.messager.alert("温馨提示", "请选中一个文件夹!", "error");
        return;
    }
}

function saveFileName() {
    var projName = $("#projName").val();
    var rows = $("#projectMaterial").treegrid("getSelections");
    var json1 = {};
    if (rows.length == 0) {
        json1.name = projName;
        json1.typeId = projId;

    } else {
        json1.name = projName;
        json1.typeId = projId;
        json1.pid = rows[0].id;
    }
    if (projName == "") {
        $.messager.alert("温馨提示", "请输入文件夹名称!", "error");
        return;
    }
    //插入附件表
    $.ajax({
        url: "/costAttachment/save",
        dataType: "json",
        type: "post",
        async: false,
        data: json1,
        success: function (result) {
            if (result.status == 200) {
                //更新treegrid数据
                window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                $('#dlg').dialog('close');
                $("#projectMaterial").treegrid('reload');
            } else {
                $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
            }
        }
    });
}

//项目指标插入附件表
function saveFileName(flag) {
    var projName = $("#name").val();
    var rows = $("#" + flag).treegrid("getSelections");
    var json1 = {};
    if (rows.length == 0) {
        json1.name = projName;
        json1.typeId = projectId;
    } else {
        json1.name = projName;
        json1.typeId = projectId;
        json1.pid = rows[0].id;
    }
    json1.dataType = flag;

    if (projName == "") {
        $.messager.alert("温馨提示", "请输入文件夹名称!", "error");
        return;
    }
    //插入附件表
    $.ajax({
        url: "/costAttachment/save",
        dataType: "json",
        type: "post",
        async: false,
        data: json1,
        success: function (result) {
            if (result.status == 200) {
                //更新treegrid数据
                window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                $('#dlg').dialog('close');
                $("#" + flag).treegrid('reload');
            } else {
                $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
            }
        }
    });
}

function updateFileName() {
    var projName = $("#fileName").val();
    if (projName == "") {
        $.messager.alert("温馨提示", "请输入文件夹名称!", "error");
        return;
    }
    var rows = $("#projectMaterial").treegrid("getSelections");
    var json1 = {};
    json1.name = projName;
    json1.typeId = projId;
    json1.id = rows[0].id;
    //插入附件表
    $.ajax({
        url: "/costAttachment/update",
        dataType: "json",
        type: "post",
        async: false,
        data: json1,
        success: function (result) {
            if (result.status == 200) {
                //更新treegrid数据
                window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                $('#editDialog').dialog('close');
                $("#projectMaterial").treegrid('reload');
            } else {
                $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
            }
        }
    });
}

function updateFileName(flag) {
    var projName = $("#fileName").val();
    if (projName == "") {
        $.messager.alert("温馨提示", "请输入文件夹名称!", "error");
        return;
    }
    var rows = $("#" + flag).treegrid("getSelections");
    var json1 = {};
    json1.name = projName;
    json1.typeId = projectId;
    json1.id = rows[0].id;
    //插入附件表
    $.ajax({
        url: "/costAttachment/update",
        dataType: "json",
        type: "post",
        async: false,
        data: json1,
        success: function (result) {
            if (result.status == 200) {
                //更新treegrid数据
                window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                $('#editDialog').dialog('close');
                $("#" + flag).treegrid('reload');
            } else {
                $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
            }
        }
    });
}

//删除附件
function delFile() {
    var rows = $("#projectMaterial").treegrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("温馨提示", "请选择要删除的数据！", "error");
        return;
    }
    $.messager.confirm('温馨提示', '确定删除吗？', function (r) {
        if (r) {
            var ids = "";
            for (var i = 0; i < rows.length; i++) {
                ids += rows[i].id + ",";
            }
            ids = ids.substring(0, ids.length - 1);
            $.post("/costAttachment/del", {"ids": ids}, function (result) {
                if (result.status == 200) {
                    //更新treegrid数据
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    $("#projectMaterial").treegrid('reload');
                    $('#projectMaterial').treegrid('clearSelections');
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }, "json");
        }
    });
}

//项目指标信息删除文件
function delFile(flag) {
    var rows = $("#" + flag).treegrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("温馨提示", "请选择要删除的数据！", "error");
        return;
    }
    if ("proIndicators" == flag) {
        if ("主材定价" == auditPriceType || "综合单价" == auditPriceType) {
            if (rows[0].name == "新增项目指标") {
                $.messager.alert("温馨提示", "抱歉该文件夹不能删除!", "error");
                return;
            }
        }
    }
    $.messager.confirm('温馨提示', '确定删除吗？', function (r) {
        if (r) {
            var ids = "";
            for (var i = 0; i < rows.length; i++) {
                ids += rows[i].id + ",";
            }
            ids = ids.substring(0, ids.length - 1);
            $.post("/costAttachment/del", {"ids": ids}, function (result) {
                if (result.status == 200) {
                    //更新treegrid数据
                    $("#" + flag).treegrid('reload');
                    $("#" + flag).treegrid("clearSelections");
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }, "json");
        }
    });
}

function delFileById(id) {
    $.messager.confirm('温馨提示', '确定删除吗？', function (r) {
        if (r) {
            $.post("/costAttachment/del", {"ids": id}, function (result) {
                if (result.status == 200) {
                    //更新treegrid数据
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    $("#projectMaterial").treegrid('reload');
                    $('#projectMaterial').treegrid('clearSelections');
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }, "json");
        }
    });
}

function uploadFile() {
    var rows = $("#projectMaterial").treegrid("getSelections");
    if (rows.length == 0 || rows[0].isLeaf == 'Y') {
        $.messager.alert("温馨提示", "请选择文件夹！", "error");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一个文件夹', 'warn');
        return;
    }
    var folderId = rows[0].id;
    var url = "/forward_document_fileinput?folderId=" + folderId + "&typeId=" + projId;
    $("#dialog").dialog({
        title: "选择文件",
        minimizable: false,
        content: "<iframe name=\"fileFrame\" frameborder=\"0\" src=" + url + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        width: 820,
        height: 480,
        iconCls: 'icon-info',
        modal: true,
        buttons: [{
            text: '关闭',
            iconCls: 'icon-close',
            handler: function (data) {
                $("#projectMaterial").treegrid('reload');
                $("#dialog").dialog("close");
            }
        }]
    });
}

//项目指标上传文件
function uploadFile(flag) {
    var rows = $("#" + flag).treegrid("getSelections");
    if (rows.length == 0 || rows[0].isLeaf == 'Y') {
        $.messager.alert("温馨提示", "请选择文件夹！", "error");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert('提示信息', '只能选择一个文件夹', 'warn');
        return;
    }
    var folderId = rows[0].id;
    var url = "/forward_document_fileinput?folderId=" + folderId + "&typeId=" + projectId + "&dataType=" + flag;
    $("#dialog").dialog({
        title: "选择文件",
        minimizable: false,
        content: "<iframe name=\"fileFrame\" frameborder=\"0\" src=" + url + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        width: 820,
        height: 480,
        iconCls: 'icon-info',
        modal: true,
        buttons: [{
            text: '关闭',
            iconCls: 'icon-close',
            handler: function (data) {
                $("#" + flag).treegrid('reload');
                $("#dialog").dialog("close");
            }
        }]
    });
}

//附件下载
function downloadAttach(id) {
    window.location.href = '/costAttachment/downLoadAttach.do?id=' + id + '&timestamp=' + new Date().getTime();
}

/* 预览  */
function previewAttach(id) {
    top.addTabGrid('预览', '/costAttachment/preview?id=' + id);
}

function relatedContracts() {
    var bool = false;
    $("#switchBtn1").on('click', function () {
        $("#tit5 .filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#relatedContracts').datagrid('resize', {
                height: 500
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#relatedContracts').datagrid('resize', {
                height: 500
            });
        }
        if (flag1 == true && flag2 == true) {
            $('#relatedContracts').datagrid('resize', {
                height: $(window).height() - 96 - 100
            });
        }
    });
    $('#relatedContracts').datagrid({
        url: "/costContract/list?projectId=" + projId,
        view: detailview,//注意1
        queryParams: {
            mainFlag: "主合同",
        },
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        toolbar: '#tit5',
        columns: [
            [{
                field: 'code',
                title: '合同编号',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<a href=\"javascript:void()\" title=" + value + " style=\"color:#549de3;padding-left:2px;" +
                            "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                            "max-width: 100%;vertical-align: middle;\" onclick=\"showInfo('" + rowData.id + "')\">" + value + "</a>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'name',
                title: '合同名称',
                width: 180,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'contractType',
                title: '合同类型',
                width: 100,
                align: 'center',
                sortable: false
            },
                {
                    field: 'contractAmount',
                    title: '合同金额（元）',
                    width: 120,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }

                }, {
                field: 'changeAmountSum',
                title: '变更金额（元）',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'settlementAmount',
                title: '结算金额（元）',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'partyB',
                title: '合同乙方',
                width: 130,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'personLiable',
                title: '合同负责人',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'department',
                title: '合同执行部门',
                width: 100,
                align: 'center',
                sortable: false
            }, {
                field: 'signingTime',
                title: '签订时间',
                width: 120,
                align: 'center',
                sortable: false,
                formatter: formatterdate
            }
            ]
        ],
        detailFormatter: function (index, row) {//注意2
            return '<div style="padding:2px 0"><table id="ddv-' + index + '"></table></div>';
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#relatedContracts').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        },
        onExpandRow: function (index, row) {//注意3
            $('#ddv-' + index).datagrid({
                url: '/costContract/listContractCong?parentId=' + row.id,
                fitColumns: true,
                singleSelect: true,
                nowrap: false,
                striped: false,
                collapsilble: true,
                loadMsg: '数据加载中,请稍候...',
                height: 'auto',
                showHeader: false,
                scrollbarSize: 0,
                queryParams: {
                    mainFlag: "从合同",
                },
                columns: [
                    [{
                        field: 'code',
                        title: '合同编号',
                        width: 150,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return "<a href=\"javascript:void()\" title=" + value + " style=\"color:#549de3;padding-left:2px;" +
                                    "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                                    "max-width: 100%;vertical-align: middle;\" onclick=\"showInfo('" + rowData.id + "')\">" + value + "</a>";
                            } else {
                                return ""
                            }
                        }
                    }, {
                        field: 'name',
                        title: '合同名称',
                        width: 180,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                            } else {
                                return ""
                            }
                        }
                    }, {
                        field: 'contractType',
                        title: '合同类型',
                        width: 100,
                        align: 'center',
                        sortable: false
                    },
                        {
                            field: 'contractAmount',
                            title: '合同金额（元）',
                            width: 120,
                            align: 'center',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value))
                                } else {
                                    return ""
                                }
                            }
                        }, {
                        field: 'changeAmountSum',
                        title: '变更金额（元）',
                        width: 120,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return addQianFenFu(String(value))
                            } else {
                                return ""
                            }
                        }
                    }, {
                        field: 'settlementAmount',
                        title: '结算金额（元）',
                        width: 120,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return addQianFenFu(String(value))
                            } else {
                                return ""
                            }
                        }
                    }, {
                        field: 'partyB',
                        title: '合同乙方',
                        width: 130,
                        align: 'center',
                        sortable: false,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                            } else {
                                return ""
                            }
                        }
                    }, {
                        field: 'personLiable',
                        title: '合同负责人',
                        width: 100,
                        align: 'center',
                        sortable: false
                    }, {
                        field: 'department',
                        title: '合同执行部门',
                        width: 100,
                        align: 'center',
                        sortable: false
                    }, {
                        field: 'signingTime',
                        title: '签订时间',
                        width: 120,
                        align: 'center',
                        sortable: false,
                        formatter: formatterdate
                    }
                    ]
                ],
                onResize: function () {
                    $('#relatedContracts').datagrid('fixDetailRowHeight', index);
                },
                onLoadSuccess: function (data) {
                    setTimeout(function () {
                        $('#relatedContracts').datagrid('fixDetailRowHeight', index);
                    }, 0);
                    if (data.total == 0) {
                        $("#ddv-" + index).datagrid('loadData', {total: 1, rows: [{code: "未找到相关信息！"}]});
                    }
                }
            });
            $('#relatedContracts').datagrid('fixDetailRowHeight', index);
        }
    });
}

function wlfile() {
    var bool = false;
    $("#switchBtn2").on('click', function () {
        $("#tit6 .filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#wlfile').datagrid('resize', {
                height: 500
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#wlfile').datagrid('resize', {
                height: 500
            });
        }
        if (flag1 == true && flag2 == true) {
            $('#wlfile').datagrid('resize', {
                height: $(window).height() - 96 - 100
            });
        }
    });
    $('#wlfile').datagrid({
        url: '/costProject/getDocumentList?projId=' + projId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
            [{
                field: 'symbol',
                title: '文号',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<a href=\"javascript:void(0)\" title=" + value + " style=\"color:#549de3;padding-left:2px;" +
                            "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                            "max-width: 100%;vertical-align: middle;\" onclick=\"documentShowInfo('" + rowData.id + "')\">" + value + "</a>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'name',
                title: '文件标题',
                width: 230,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'comeGoFlag',
                title: '往/来',
                width: 80,
                align: 'center',
                sortable: false

            }, {
                field: 'auditPriceFlag',
                title: '是否审价',
                width: 80,
                align: 'center',
                sortable: false

            }, {
                field: 'comeGoUnit',
                title: '往来单位',
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'documentTime',
                title: '文件日期',
                align: 'center',
                width: 120,
                sortable: false,
                formatter: formatterdate

            }, {
                field: 'number',
                title: '份数',
                width: 100,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }
                /*{
                    field: 'personLiable',
                    title: '负责人',
                    width: 100,
                    align: 'center',
                    sortable: false
                }*/
            ]
        ],
        toolbar: '#tit6',
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#wlfile').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        },

    });
    $(window).resize(function () {
        $('#wlfile').datagrid('resize', {
            height: 500
        });
    });
}

function sjTask() {
    var bool = false;
    $("#switchBtn3").on('click', function () {
        $("#tit7 .filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#sjTask').datagrid('resize', {
                height: 500
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#sjTask').datagrid('resize', {
                height: 500
            });
        }
        if (flag1 == true && flag2 == true) {
            $('#sjTask').datagrid('resize', {
                height: $(window).height() - 96 - 100
            });
        }
    });
    $('#sjTask').datagrid({
        url: '/costTask/list?projectId=' + projId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: 500,
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: true,
        columns: [
            [{
                field: 'code',
                title: '审价编号',
                width: 150,
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<a href=\"javascript:void()\" title=" + value + " style=\"color:#549de3;padding-left:2px;" +
                            "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                            "max-width: 100%;vertical-align: middle;\" onclick=\"taskShowInfo('" + rowData.id + "')\">" + value + "</a>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'name',
                title: '审价任务名称',
                width: 200,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'auditPriceType',
                title: '审价类型',
                width: 120,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }

            }, {
                field: 'createTime',
                title: '创建日期',
                width: 80,
                align: 'center',
                sortable: false,
                formatter: formatterdate

            }, {
                field: 'personLiable',
                title: '审价任务负责人',
                align: 'center',
                width: 80,
                sortable: false,

            }, {
                field: 'auditPriceUnit',
                title: '审价单位',
                align: 'left',
                halign: 'center',
                width: 150,
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"padding-left:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:block;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }

            }, {
                field: 'status',
                title: '当前状态',
                align: 'center',
                width: 80,
                sortable: false

            }
                /*, {
                    field: 'finalizeNumber',
                    title: '定案文号',
                    width: 100,
                    align: 'center',
                    sortable: false,

                }*/
                , {
                field: 'decideAmount',
                title: '定审金额（元）',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        if (value >= 0) {
                            return addQianFenFu(String(value));
                        } else {
                            return "<span style='color:green;'>" + addQianFenFu(String(value.toFixed(2))) + "</span>";
                        }
                    } else {
                        return ""
                    }
                }

            }
                , {
                field: 'coordinateFlag',
                title: '协调',
                width: 50,
                align: 'center',
                sortable: false
            }
            ]
        ],
        toolbar: "#tit7",
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#sjTask').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
            }
        },
    });
    $(window).resize(function () {
        $('#sjTask').datagrid('resize', {
            height: 500
        });
    });
}

function saveStakeholder() {
    var isRole = $("#roleId").val();
    var isFunctions = $("#functions").val();
    var isProName = $("#userName").val();
    var isPhone = $("#phone").val();
    if (!isRole) {
        $.messager.alert('提示信息', '请选择角色！', 'error');
        return false;
    }
    if (!isFunctions) {
        $.messager.alert('提示信息', '请填写职能！', 'error');
        return false;
    }
    if (!isProName) {
        $.messager.alert('提示信息', '请填写名称！', 'error');
        return false;
    }
    if (!isPhone) {
        $.messager.alert('提示信息', '请填写电话号码！', 'error');
        return false;
    }

    if (isRole && isProName && isFunctions && isPhone) {
        $.ajax({
            url: "/stakelholder/saveStakeholder",
            dataType: "json",
            type: "post",
            async: false,
            data: $("#stakeholderForm").serialize(),
            success: function (result) {
                if (result.status == 200) {
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    $('#projectPersonDialog').dialog('close');
                    $('#projectPerson').datagrid('reload');
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }
        });
    }
}

function updateStakeholder() {
    var rows = $("#projectPerson").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("温馨提示", "请至少选择一项！", "error");
        return;
    }
    else if (rows.length > 1) {
        $.messager.alert("温馨提示", "只能选择一项！", "error");
        return;
    }
    $('#projectPersonDialog').dialog({
        title: '修改项目干系人',
        width: 600,
        height: "auto",
        closed: false,
        cache: false,
        top: 100,
        href: '/stakelholder/addStakeholder?projId=' + projId + '&id=' + rows[0].id,
        modal: true
    });
}

//删除附件
function delStakeholder() {
    var rows = $("#projectPerson").treegrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("温馨提示", "请选择要删除的数据！", "error");
        return;
    }
    $.messager.confirm('温馨提示', '确定删除吗？', function (r) {
        if (r) {
            var ids = "";
            for (var i = 0; i < rows.length; i++) {
                ids += rows[i].id + ",";
            }
            ids = ids.substring(0, ids.length - 1);
            $.post("/stakelholder/deleteStakeholder", {"ids": ids}, function (result) {
                if (result.status == 200) {
                    //更新treegrid数据
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    $('#projectPerson').datagrid('reload');
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }, "json");
        }
    });
}

//提交合同查询条件
function contractSelectSubmit() {
    var tableId = 'relatedContracts';
    var name = $("#contractName").val();
    var code = $("#contractCode").val();
    var contractType = $("#contractType option:selected").val();
    var orgId = $("#orgCombotreeSelect").combotree("getValues");
    var dep = orgId.join(",");
    if (dep == -1) {
        dep = "";
    }
    var personLiable = $("#personLiable4").val();
    var contractSigningTimeStart = $('#contractSigningTimeStart').val();
    var contractSigningTimeEnd = $("#contractSigningTimeEnd").val();
    var partyB = $("#partyB").val();
    $('#' + tableId).datagrid('options').queryParams = {
        'code': code,
        'name': name,
        'personLiable': personLiable,
        'contractType': contractType,
        'orgId': dep,
        'signingTimeSartDate': contractSigningTimeStart,
        'signingTimeEndDate': contractSigningTimeEnd,
        'partyB': partyB,
        'mainFlag': "主合同"
    };
    $('#' + tableId).datagrid("reload");
}

//提交往来文件查询条件
function documentSelectSubmit() {
    var tableId = 'wlfile';
    var name = $("#documentName").val();
    var symbol = $("#symbol").val();
    var comeGoFlag = $("#comeGoFlag option:selected").val();
    var auditPriceFlag = $("#auditPriceFlag option:selected").val();
    var comeGoUnit = $("#comeGoUnit").val();
    var personLiable = $("#personLiable5").val();
    var documentTimeStart = $('#documentTimeStart').val();
    var documentTimeEnd = $("#documentTimeEnd").val();

    $('#' + tableId).datagrid('options').queryParams = {
        'symbol': symbol,
        'name': name,
        'personLiable': personLiable,
        'comeGoFlag': comeGoFlag,
        'auditPriceFlag': auditPriceFlag,
        'comeGoUnit': comeGoUnit,
        'startTime': documentTimeStart,
        'endTime': documentTimeStart
    };
    $('#' + tableId).datagrid("reload");
}

//提交审价任务查询条件
function taskSelectSubmit() {
    var tableId = 'sjTask';
    var code = $("#taskCode").val();
    var name = $("#taskName").val();
    var personLiable = $("#personLiable6").val();
    var auditPriceUnit = $("#taskUnit").val();
    var status = $("#status option:selected").val();
    var finalizeNumber = $("#finalizeNumber").val();
    var coordinateFlag = $("#coordinateFlag option:selected").val();
    var createTimeStart = $('#createTimeStart').val();
    var createTimeEnd = $("#createTimeEnd").val();
    var coordinateFlag = $("#coordinateFlag").val();
    var auditPriceType = $("#auditPriceType").combotree('getText');
    if (auditPriceType == "请选择") {
        auditPriceType = "";
    }
    $('#' + tableId).datagrid('options').queryParams = {
        'code': code,
        'name': name,
        'personLiable': personLiable,
        'auditPriceType': auditPriceType,
        'auditPriceUnit': auditPriceUnit,
        'status': status,
        'finalizeNumber': finalizeNumber,
        'coordinateFlag': coordinateFlag,
        'startTime': createTimeStart,
        'endTime': createTimeEnd,
        'coordinateFlag': coordinateFlag,
        'shenTime': "createTime"
    };
    $('#' + tableId).datagrid("reload");
}

//合同详情
function showInfo(zjkClId) {
    var href = "/costContract/toEdit?editFlag=y&id=" + zjkClId;
    var title = "合同修改";
    top.addTabGrid(title, href);
}

//往来文件详情
function documentShowInfo(zjkClId) {
    var href = "/costDocument/toEdit?editFlag=y&id=" + zjkClId;
    var title = "往来文件修改";
    top.addTabGrid(title, href);
}

//审价任务详情
function taskShowInfo(zjkClId) {
    var href = "/costTask/toEdit?editFlag=y&id=" + zjkClId;
    var title = "审价任务修改";
    top.addTabGrid(title, href);
}

//项目干系人
function person1() {
    $('#person1').dialog({
        title: '系统用户',
        width: 805,
        height: 520,
        closed: false,
        cache: false,
        top: 50,
        content: "<iframe name=\"fileFramePro\" frameborder=\"0\" src=" + '/forward_project_person1' + " scrolling=\"yes\" style=\"width:100%;height:100%;\"></iframe>",
        modal: true
    });
}

//项目指标模板下载
function downloadTemplateZb() {
    window.location.href = "/costDocument/downloadTemplateZb";
}

//模版发布
function pulish(id,projectId) {
    $.post('/costProject/costContentTemplate/importContentTemplate?id='+id+'&&projectId='+projectId,function(result){
        if(result.status==200){
            //更新treegrid数据
            window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
            $("#proIndicators").treegrid('reload');
        }else{
            $.messager.alert("温馨提示","操作异常,请联系管理员!", "error");
        }
    }, "json");
}