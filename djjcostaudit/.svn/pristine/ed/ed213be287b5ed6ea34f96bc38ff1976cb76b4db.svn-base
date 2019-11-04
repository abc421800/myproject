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
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#projectAccounts').datagrid('resize', {
                width: $(window).width() - 30,
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#projectAccounts').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#projectAccounts').datagrid({
        view: myview,
        url: '/costProject/getProjectlist.do',
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: false,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        pageList: [10, 100, 200, 500],
        singleSelect: false,
        showFooter: true,
        idField:"id",
        rowStyler: function (index, row) {
            if (row.code == '汇总') {
                return 'background-color:#ffffcc;color:red;font-weight:bold;';
            }
        },
        frozenColumns: [[
            {
                field: 'id',
                title: '单选框',
                checkbox: true

            }, {
                field: 'code',
                title: '项目编号',
                width: 130,
                align: 'center',
                sortable: false,

            }, {
                field: 'name',
                title: '项目名称',
                width: 300,
                halign: 'left',
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<a href=\"javascript:void(0)\" style=\"color:#549de3;\" title=\"" + value + "\" onclick=\"editInfo('" + rowData.id + "')\">" + value + "</a>";
                    }
                    else {
                        return "";
                    }
                }
            }
        ]],
        columns: [
            [
                {
                    field: 'projectOwner',
                    title: '项目业主',
                    width: 130,
                    halign: 'left',
                    align: 'left',
                    sortable: false,
                    rowspan: 2,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                        } else {
                            return ""
                        }
                    }
                }, {
                field: 'showFlag2',
                title: '结算台账是否显示',
                width: 140,
                halign: 'center',
                align: 'center',
                sortable: false,
                rowspan: 2
            }
                , {
                field: 'projectManagementAgreement',
                title: '建设管理协议',
                width: 80,
                align: 'center',
                rowspan: 2,
                sortable: false,
            }, {
                field: 'settlementAgreement',
                title: '结算方式约定',
                width: 150,
                halign: 'left',
                align: 'left',
                rowspan: 2,
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'settlementReivewMethod',
                title: '结算评审方式',
                halign: 'left',
                align: 'left',
                width: 150,
                rowspan: 2,
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'creator',
                title: '创建人',
                width: 60,
                align: 'center',
                rowspan: 2,
                sortable: false,

            }, {
                field: 'personLiableId',
                title: '项目负责人',
                align: 'center',
                width: 100,
                rowspan: 2,
                sortable: false,

            }, {
                title: '项建估算（万元）',
                width: 480,
                halign: 'center',
                align: 'right',
                sortable: false,
                colspan: 4
            }, {
                title: '可研估算（万元）',
                width: 480,
                halign: 'center',
                align: 'right',
                sortable: false,
                colspan: 4
            }, {
                field: 'projectClassification',
                title: '项目分类',
                width: 150,
                align: 'left',
                rowspan: 2,
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                    } else {
                        return ""
                    }
                }
            },
                {
                    field: 'projectNode',
                    title: '当前节点',
                    width: 100,
                    align: 'center',
                    rowspan: 2,
                    sortable: false,
                }, {
                field: 'nodeTime',
                title: '节点控制日期',
                width: 100,
                align: 'center',
                sortable: false,
                rowspan: 2,
                formatter: formatterdate,
            }
                , {
                title: '概算金额（元）',
                width: 480,
                halign: 'center',
                align: 'right',
                sortable: false,
                colspan: 4
            },
                {
                    field: 'sumYsJe',
                    title: '预算金额<br>（元）',
                    width: 150,
                    halign: 'center',
                    align: 'right',
                    rowspan: 2,
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                }, {
                field: 'sumKzjJe',
                title: '控制价<br>（元）',
                width: 150,
                halign: 'center',
                align: 'right',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'sumHtjJe',
                title: '合同价<br>（元）',
                width: 150,
                halign: 'center',
                align: 'right',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'sumTzBgJe',
                title: '设计变更费（会签）<br>（元）',
                width: 150,
                halign: 'center',
                align: 'right',
                sortable: false,
                rowspan: 2,
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
            }, {
                field: 'sumBgjJe',
                title: '变更金额<br>（元）',
                width: 150,
                halign: 'center',
                align: 'right',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'sumJyJe',
                title: '结余资金<br>（元）',
                width: 150,
                halign: 'center',
                align: 'right',
                rowspan: 2,
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
            }, {
                field: 'sumJsjJe',
                title: '结算价<br>（元）',
                width: 150,
                halign: 'center',
                align: 'right',
                rowspan: 2,
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }],
            [
                {
                    field: 'xjGsJe',
                    title: '项建估算合计',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                }, {
                field: 'xjGsGcf',
                title: '工程费',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            },
                {
                    field: 'xjGsElfy',
                    title: '二类费用',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'xjGsSlfy',
                    title: '三类费用',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                }, {
                field: 'kyGsJe',
                title: '可研估算合计',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            },
                {
                    field: 'kyGsGcf',
                    title: '工程费',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'kyGsElfy',
                    title: '二类费用',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'kyGsSlfy',
                    title: '三类费用',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                }, {
                field: 'sumGsJe',
                title: '概算金额合计',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            }, {
                field: 'gsGcf',
                title: '工程费',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return addQianFenFu(String(value))
                    } else {
                        return ""
                    }
                }
            },
                {
                    field: 'gsElfy',
                    title: '二类费用',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'gsSlfy',
                    title: '三类费用',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return addQianFenFu(String(value))
                        } else {
                            return ""
                        }
                    }
                }
            ],
        ],
        toolbar: '#tit1',
        dragSelection: false,
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#projectAccounts').datagrid('loadData', {total: 1, rows: [{code: "未找到相关信息！"}]});
            }
        }
    });
    $(window).resize(function () {
        if (bool) {
            $('#projectAccounts').datagrid('resize', {
                height: $(window).height() - 40
            });
        } else {
            $('#projectAccounts').datagrid('resize', {
                width: $(window).width() - 30,
                height: tableHeight()
            });
        }
    });
});


function showInfo(zjkClId) {
    var href = "/costProject/editProject?editFlag=n&projId=" + zjkClId;
    var title = "项目详情";
    top.addTabGrid(title, href);
}

function edit() {
    var rows = $('#projectAccounts').datagrid('getSelections');
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
    var href = "/costProject/editProject?editFlag=y&projId=" + zjkClId;
    var title = "项目修改";
    top.addTabGrid(title, href);
}

function deleteHandler() {
    var selections = $('#projectAccounts').datagrid('getSelections');
    if (selections.length == 0) {
        $.messager.alert('提示信息', '请至少选择一条记录', 'warn');
        return;
    }
    $.messager.confirm('提示信息', '确认要删除这' + selections.length + '条记录吗？', function (isOk) {
        if (!isOk) {
            return;
        }
        var ids = '';
        for (var i in selections) {
            ids = ids + ',' + selections[i].id;
            if (currentUser != selections[i].creator && dataUpdate == "false") {
                $.messager.alert('提示信息', '抱歉！您没权限删除该条数据：' + selections[i].name, 'warn');
                return;
            }
        }
        $.ajax({
            url: '/costProject/deleteProject.do',
            type: 'POST',
            data: {'ids': ids},
            traditional: true,
            success: function (result) {
                $.messager.progress('close');
                if (result.status == 200) {
                    window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                    $('#projectAccounts').datagrid('reload');
                } else {
                    $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                }
            }
        });
    });
}

function updateShowFlag() {
    var selections = $('#projectAccounts').datagrid('getSelections');
    var showFlag2 = $("input[name='currentNode2']:checked").val();
    var ids = '';
    for (var i in selections) {
        ids = ids + ',' + selections[i].id;
        if (currentUser != selections[i].creator && dataUpdate == "false") {
            $.messager.alert('提示信息', '抱歉！您没权限修改该条数据：' + selections[i].name, 'warn');
            return;
        }
    }
    $.ajax({
        url: '/costProject/updateShowFlag.do',
        type: 'POST',
        data: {
            'ids': ids,
            'showFlag2': showFlag2
        },
        traditional: true,
        success: function (result) {
            $.messager.progress('close');
            if (result.status == 200) {
                window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                $('#projectAccounts').datagrid('reload');
                $("#dlg").dialog('close');
            } else {
                $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
            }
        }
    });
}

//修改项目分类
function updateClassification() {
    var selections = $('#projectAccounts').datagrid('getSelections');
    var projectClassificationArray = $("#projectClassificationId2").combotree("getValues");
    var projectClassificationId = "";
    for (var i = 0; i < projectClassificationArray.length; i++) {
        projectClassificationId = projectClassificationId + projectClassificationArray[i] + ",";
    }
    projectClassificationId = projectClassificationId.substring(0, projectClassificationId.length - 1);
    if (projectClassificationId == -1) {
        projectClassificationId = null;
    }
    console.log(projectClassificationId);
    var ids = '';
    for (var i in selections) {
        ids = ids + ',' + selections[i].id;
        if (currentUser != selections[i].creator && dataUpdate == "false") {
            $.messager.alert('提示信息', '抱歉！您没权限修改该条数据：' + selections[i].name, 'warn');
            return;
        }
    }
    $.ajax({
        url: '/costProject/updateClassification.do',
        type: 'POST',
        data: {
            'ids': ids,
            'projectClassificationId': projectClassificationId,
        },
        traditional: true,
        success: function (result) {
            $.messager.progress('close');
            if (result.status == 200) {
                window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                $('#projectAccounts').datagrid('reload');
                $("#dlg2").dialog('close');
            } else {
                $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
            }
        }
    });
}

//提交查询条件
function selectSubmit() {
    var tableId = 'projectAccounts';
    var code = $("#code").val();
    var name = $("#name").val();
    var projectManagementAgreement = $("#projectManagementAgreement option:selected").val();
    var personLiableId = $("#personLiableId").val();
    var projectCategoryId = $('#projectCategoryId option:selected').val();
    var projectClassificationArray = $("#projectClassificationId").combotree("getValues");
    var projectClassificationId = "";
    for (var i = 0; i < projectClassificationArray.length; i++) {
        projectClassificationId = projectClassificationId + projectClassificationArray[i] + ",";
    }
    projectClassificationId = projectClassificationId.substring(0, projectClassificationId.length - 1);
    if (projectClassificationId == -1) {
        projectClassificationId = null;
    }
    var projectLocation = $("#projectLocation").val();
    var projectNode = $("#projectNode option:selected").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var showFlag = $("#showFlag").val();
    var showFlag2 = $("#showFlag2").val();
    $('#' + tableId).datagrid('options').queryParams = {
        'code': code,
        'name': name,
        'projectManagementAgreement': projectManagementAgreement,
        'personLiableId': personLiableId,
        'projectCategoryId': projectCategoryId,
        'projectClassificationId': projectClassificationId,
        'projectLocation': projectLocation,
        'projectNode': projectNode,
        'startTime': startTime,
        'endTime': endTime,
        'showFlag': showFlag,
        'showFlag2': showFlag2
    };
    $('#' + tableId).datagrid("reload");
}

//自动刷新
function reset1() {
    var json1 = {tabTitle: '项目台账', url: '/forward_project_list'};
    window.parent.refreshTab(json1);
}


