var projectType = "";
var orgId = "";
$(function () {
    var queryParams = JSON.parse(localStorage.getItem("queryParams"));
    if (queryParams) {
        $("#auditPriceType").val(queryParams.auditPriceType);
        $("#projectName").val(queryParams.projectName);
        if (queryParams.projectClassificationId != null) {
            projectType = queryParams.projectClassificationId;
        } else {
            projectType = "";
        }
        if (queryParams.orgId != null) {
            orgId = queryParams.orgId;
        } else {
            orgId = "";
        }

    }
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#contractStatistics').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#contractStatistics').datagrid('resize', {
                height: tableHeight()
            });
        }
    });

    //new
    $('#contractStatistics').datagrid({
        view: detailview,//注意1
        fitColumns: false,
        singleSelect: false,
        nowrap: false,
        striped: false,
        collapsilble: true,
        dataType: 'json',
        method: 'post',
        queryParams: queryParams,
        scrollbarSize: 0,
        pagination: true,
        height: tableHeight(),
        pageSize: 200,
        pageList: [10, 100, 200, 500],
        idField: "projectId",
        loadMsg: '数据加载中,请稍候...',
        showFooter: true,
        url: '/costProject/getContractJsList.do',
        toolbar: '#tit1',
        columns: [
            [{
                field: 'id',
                title: '单选框',
                checkbox: true,
                rowspan: 2
            }, {
                field: 'priority',
                title: '序号',
                width: 50,
                align: 'center',
                sortable: false,
                rowspan: 2
            }, {
                field: 'projectName',
                title: '项目名称',
                width: 230,
                align: 'left',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "<a href=\"javascript:void(0)\" style=\"color:#549de3;padding-left:2px;" +
                            "overflow:hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;" +
                            "max-width: 100%;vertical-align: middle;\"  title=\"" + value + "\" onclick=\"showInfo('" + rowData.projectId + "')\">" + value + "</a>";
                    }
                    else {
                        return "";
                    }
                }
            }, {
                field: 'executiveDepartment',
                title: '合同执行部门',
                width: 130,
                align: 'center',
                sortable: false,
                rowspan: 2
            },
                {
                    title: '合同签订情况',
                    align: 'center',
                    width: 280,
                    sortable: false,
                    colspan: 2

                }, {
                title: '送审情况',
                align: 'center',
                width: 480,
                sortable: false,
                colspan: 6

            }, {
                title: '定审情况',
                align: 'center',
                width: 480,
                sortable: false,
                colspan: 6

            }, {
                field: 'ssJePercent',
                title: '送审金额定审率',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + "%" + '</span>';
                    } else {
                        return '<span style="font-weight:bold;color:#dd4d57;">' + "0.00%" + '</span>';
                    }
                }
            }, {
                field: 'ssCountPercent',
                title: '送审合同定审率',
                width: 120,
                halign: 'center',
                align: 'right',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + "%" + '</span>';
                    } else {
                        return '<span style="font-weight:bold;color:#dd4d57;">' + "0.00%" + '</span>';
                    }
                }
            },
                {
                    field: 'htJsPercent',
                    title: '合同结算完成率',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    rowspan: 2,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + "%" + '</span>';
                        } else {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + "0.00%" + '</span>';
                        }
                    }
                }, {
                field: 'memo',
                title: '备注',
                width: 180,
                align: 'center',
                sortable: false,
                rowspan: 2,
                formatter: function (value, rowData, index) {
                    if (value != null) {
                        return "";
                        // return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                    } else {
                        return "";
                    }
                }
            }], [
                //合同签订情况
                {
                    field: 'contractCount',
                    title: '合同数量（个）',
                    width: 140,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'contractAmount',
                    title: '合同金额（万元）',
                    width: 140,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                },
                //送审情况
                {
                    field: 'ssContractCount',
                    title: '合同数量（个）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'ssContractAmount',
                    title: '金额（万元）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                },
                {
                    field: 'ssWsContractCount',
                    title: '外审合同数量（个）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'ssWsContractAmount',
                    title: '外审金额（万元）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                }, {
                    field: 'ssZsContractCount',
                    title: '自审合同数量（个）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'ssZsContractAmount',
                    title: '自审金额（万元）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                },
                //定审情况
                {
                    field: 'dsContractCount',
                    title: '合同数量（个）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'dsContractAmount',
                    title: '金额（万元）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                },
                {
                    field: 'dsWsContractCount',
                    title: '外审合同数量（个）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'dsWsContractAmount',
                    title: '外审金额（万元）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                }, {
                    field: 'dsZsContractCount',
                    title: '自审合同数量（个）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + value + '</span>';

                        } else {
                            return ""
                        }
                    }
                },
                {
                    field: 'dsZsContractAmount',
                    title: '自审金额（万元）',
                    width: 120,
                    halign: 'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                        if (value != null) {
                            return '<span style="font-weight:bold;color:#dd4d57;">' + addQianFenFu(String(value)) + '</span>';
                        } else {
                            return "";
                        }
                    }
                }
            ]
        ],
        detailFormatter: function (index, row) {//注意2
            return '<div style="padding:2px 0"><table id="ddv-' + index + '"></table></div>';
        },
        onLoadSuccess: function (data) {
            localStorage.clear();
            if (data.total == 0) {
                $('#contractStatistics').datagrid('loadData', {total: 1, rows: [{executiveDepartment: "未找到相关信息！"}]});
            }
        },
        onExpandRow: function (index, row) {//注意3
            $('#ddv-' + index).datagrid({
                url: '/costProject/getContractJsGroup.do?projectId=' + row.projectId + '&index=' + row.priority,
                fitColumns: true,
                singleSelect: true,
                nowrap: true,
                striped: false,
                collapsilble: true,
                loadMsg: '数据加载中,请稍候...',
                height: 'auto',
                showHeader: false,
                scrollbarSize: 0,
                columns: [
                    [{
                        field: 'id',
                        title: '单选框',
                        checkbox: true,
                        rowspan: 2
                    }, {
                        field: 'priority',
                        title: '序号',
                        width: 50,
                        align: 'center',
                        sortable: false,
                        rowspan: 2
                    }, {
                        field: 'projectName',
                        title: '项目名称',
                        width: 230,
                        align: 'center',
                        sortable: false,
                        rowspan: 2,
                        formatter: function (value, rowData, index) {
                            return "";
                        }

                    }, {
                        field: 'executiveDepartment',
                        title: '合同执行部门',
                        width: 130,
                        align: 'center',
                        sortable: false,
                        rowspan: 2
                    }, {
                        title: '合同签订情况',
                        align: 'center',
                        width: 280,
                        sortable: false,
                        colspan: 2

                    }, {
                        title: '送审情况',
                        align: 'center',
                        width: 480,
                        sortable: false,
                        colspan: 6

                    }, {
                        title: '定审情况',
                        align: 'center',
                        width: 480,
                        sortable: false,
                        colspan: 6
                    }, {
                        field: 'ssJePercent',
                        title: '送审金额定审率',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false,
                        rowspan: 2,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return addQianFenFu(String(value)) + "%";
                            } else {
                                return '<span>' + "0.00%" + '</span>';
                            }
                        }
                    }, {
                        field: 'ssCountPercent',
                        title: '送审合同定审率',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false,
                        rowspan: 2,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                return addQianFenFu(String(value)) + "%";
                            } else {
                                return '<span>' + "0.00%" + '</span>';
                            }
                        }
                    },
                        {
                            field: 'htJsPercent',
                            title: '合同结算完成率',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            rowspan: 2,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value)) + "%";
                                } else {
                                    return '<span>' + "0.00%" + '</span>';
                                }
                            }
                        }, {
                        field: 'memo',
                        title: '备注',
                        width: 180,
                        align: 'center',
                        sortable: false,
                        rowspan: 2,
                        formatter: function (value, rowData, index) {
                            if (value != null) {
                                // return "<span title=" + value + " style=\"overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;max-width:100%;vertical-align: middle;\">" + value + "</span>";
                                return "";
                            } else {
                                return ""
                            }

                        }
                    }], [
                        //合同签订情况
                        {
                            field: 'contractCount',
                            title: '合同数量（个）',
                            width: 140,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'contractAmount',
                            title: '合同金额（万元）',
                            width: 140,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        },
                        //送审情况
                        {
                            field: 'ssContractCount',
                            title: '合同数量（个）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'ssContractAmount',
                            title: '金额（万元）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        },
                        {
                            field: 'ssWsContractCount',
                            title: '外审合同数量（个）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'ssWsContractAmount',
                            title: '外审金额（万元）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        }, {
                            field: 'ssZsContractCount',
                            title: '自审合同数量（个）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'ssZsContractAmount',
                            title: '自审金额（万元）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        },
                        //定审情况
                        {
                            field: 'dsContractCount',
                            title: '合同数量（个）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'dsContractAmount',
                            title: '金额（万元）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        },
                        {
                            field: 'dsWsContractCount',
                            title: '外审合同数量（个）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'dsWsContractAmount',
                            title: '外审金额（万元）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        }, {
                            field: 'dsZsContractCount',
                            title: '自审合同数量（个）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            // sorter:function(a,b){
                            //     return(a>b?1:-1)
                            // }
                        },
                        {
                            field: 'dsZsContractAmount',
                            title: '自审金额（万元）',
                            width: 120,
                            halign: 'center',
                            align: 'right',
                            sortable: false,
                            formatter: function (value, rowData, index) {
                                if (value != null) {
                                    return addQianFenFu(String(value));
                                } else {
                                    return "";
                                }
                            }
                        }
                    ]
                ],
                onResize: function () {
                    $('#contractStatistics').datagrid('fixDetailRowHeight', index);
                },
                onLoadSuccess: function (data) {
                    setTimeout(function () {
                        $('#contractStatistics').datagrid('fixDetailRowHeight', index);
                    }, 0);
                }
            });
            $('#contractStatistics').datagrid('fixDetailRowHeight', index);
        }

    });
    //new End
    $(window).resize(function () {
        if (bool) {
            $('#contractStatistics').datagrid('resize', {
                height: $(window).height() - 40
            });
        } else {
            $('#contractStatistics').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
});

function showInfo(id) {
    window.parent.addTabGrid('项目详情', "/costProject/editProject?editFlag=n&projId=" + id)
}

//提交查询条件
function selectSubmit() {
    var auditPriceType = $("#auditPriceType").val();
    var tableId = 'contractStatistics';
    var projectName = $("#projectName").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var orgId = $("#orgCombotreeSelect").combotree("getValues");
    var dep = orgId.join(",");
    var type = "结算审核";
    if (dep == -1) {
        dep = "";
    }
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
    $('#' + tableId).datagrid('options').queryParams = {
        'projectName': projectName,
        'orgId': dep,
        'startTime': startTime,
        'endTime': endTime,
        'auditPriceType': auditPriceType,
        'type': type,
        'projectClassificationId': projectClassificationId
    };
    $('#' + tableId).datagrid("reload");
}

//修改项目分类
function updateClassification() {
    var selections = $('#contractStatistics').datagrid('getSelections');
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

//自动刷新
function Refresh() {
    var json1 = {tabTitle: '合同台账统计表', url: '/forward_project_contractJsCount'};
    window.parent.refreshTab(json1);
}


