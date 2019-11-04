<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
    <jsp:include page="${pageContext.request.contextPath}/res/common/easyui/easyui-common.jsp"></jsp:include>
</head>
<body>
<div id="loading-wrapper">
    <div id="loader">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
        <div class="line4"></div>
        <div class="line5"></div>
        <div class="line6"></div>
    </div>
</div>
<div class="row">
    <form action="">
        <div class="filter">
            <div class="form-inline">
                <div class="form-group">
                    <label for="">角色名称：</label>
                    <input type="text" id="name" value="" placeholder="请输入关键字" class="form-control input-sm">
                </div>

                <div class="form-group">
                    <input type="button" name="Submit11" value="查询" class="btn btn-primary" onclick="searchRol()">&nbsp;
                    <input type="button" name="button" value="重置" class="btn btn btn-success" onclick="Refresh()">&nbsp;
                </div>
            </div>
        </div>
        <a href="javascript:void(0);" class="switchBtn"></a>
    </form>
</div>
<div class="row">
    <table id="role" style="width: 100%;">
    </table>
    <div id="tit1">
        <shiro:hasPermission name="role:add">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="false"
               onclick="add()">添加</a>
        </shiro:hasPermission>
        <%--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="edit()">修改</a>--%>
        <shiro:hasPermission name="role:delete">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="false"
               onclick="deleteHandler()">删除</a>
        </shiro:hasPermission>
        <span style="color:#969696;font-size: 14px">（右键表格表头设置显示隐藏列）</span>

    </div>
</div>
<%--<script src="${pageContext.request.contextPath}/res/js/sys/role/roleList.js"></script>--%>
<script type="text/javascript">
    $(function () {
        var bool = false;
        $(".switchBtn").on('click', function () {
            $(".filter").toggle();
            if (!bool) {
                $(this).addClass("active");
                $('#role').datagrid('resize', {
                    height: $(window).height() - 40
                });
                bool = true;
            } else {
                $(this).removeClass("active");
                bool = false;
                $('#role').datagrid('resize', {
                    height: tableHeight()
                });
            }
        });
        $('#role').datagrid({
            url: '/sysRole/list',
            loadMsg: '数据加载中,请稍候...',
            nowrap: false,
            rownumbers: true,
            height: tableHeight(),
            fitColumns: true,
            striped: true,
            collapsilble: true,
            pagination: true, //分页控件
            pageSize: 10,
            singleSelect: false,
            columns: [[

                {
                    field: 'id',
                    title: '单选框',
                    checkbox: true

                }, {
                    field: 'name',
                    title: '角色名称',
                    width: 150,
                    align: 'center',
                    sortable: false,

                }, {
                    field: 'description',
                    title: '描述',
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
                    field: 'status',
                    title: '是否可用',
                    width: 80,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, row, index) {
                        if (value) {
                            if ("可用" == value) {
                                return '<span style="color:green">可用</span>';
                            } else if ("禁用" == value) {
                                return '<span style="color:red">禁用</span>';
                            }
                        }
                        return "";
                    }

                }, {
                    field: 'num',
                    title: '排序号',
                    width: 80,
                    align: 'center',
                    sortable: false

                }, {
                    field: 'createTimeStr',
                    title: '创建时间',
                    align: 'center',
                    width: 100,
                    sortable: false

                }, {
                    field: 'manager', title: '管理', align: 'center', halign: 'center', width: 100,
                    formatter: function (value, row, index) {
                        var a = "";
                        <shiro:hasPermission name="role:update">
                        a = "<a href='javascript:void(0)' class='btn btn-outline-primary' onclick='editInfo(\"" + row.id + "\",\"" + row.name + "\");'>编辑</a>&nbsp;&nbsp;";
                        </shiro:hasPermission>
                        return a;
                    }

                }
            ]
            ],
            toolbar: '#tit1',
            onLoadSuccess: function (data) {
                if (data.total == 0) {
                    $('#role').datagrid('loadData', {total: 1, rows: [{name: "未找到相关信息！"}]});
                }
            },
        });
        $(window).resize(function () {
            $('#role').datagrid('resize', {
                height: tableHeight()
            });
        });
    });

    function edit() {
        var rows = $('#role').datagrid('getSelections');
        if (rows == undefined || rows == null || rows.length == 0) {
            $.messager.alert('提示信息', '请选择一条记录', 'warn');
            return;
        }
        if (rows.length > 1) {
            $.messager.alert('提示信息', '只能选择一条记录', 'warn');
            return;
        }
        parent.addTabGrid("修改角色", "/sysRole/toEdit?editFlag=y&id=" + zjkClId);
    }

    //修改角色
    var orgName = "${roleName}";

    function editInfo(zjkClId, name) {
        var href = "/sysRole/toEdit?editFlag=y&id=" + zjkClId;
        var title = "修改角色";
        if ("超级管理员" == name) {
            if ("超级管理员" == orgName) {
                parent.addTabGrid(title, href);
            } else {
                $.messager.alert('提示信息', '抱歉,只有【超级管理员】才能编辑！', 'warn');
            }
        } else {
            parent.addTabGrid(title, href);
        }
    }

    //角色详情
    function showInfo(zjkClId) {
        var href = "/sysRole/toEdit?editFlag=n&id=" + zjkClId;
        var title = "角色详情";
        top.addTabGrid(title, href);
    }

    //添加角色
    function add() {
        var href = "/sysRole/toEdit?editFlag=y";
        var title = "添加角色";
        parent.addTabGrid(title, href);
    }

    function deleteHandler() {
        var selections = $('#role').datagrid('getSelections');
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
                console.log(selections[i].id);
            }
            console.log(ids);
            $.ajax({
                url: '/sysRole/deleteRole.do',
                type: 'POST',
                data: {'ids': ids},
                traditional: true,
                success: function (result) {
                    $.messager.progress('close');
                    if (result.status == 200) {
                        window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                        $('#role').datagrid('reload');
                    } else {
                        $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                    }
                }
            });
        });
    }

    function del(id) {
        $.messager.confirm('提示信息', '确认要删除这条记录吗？', function (isOk) {
            if (!isOk) {
                return;
            }
            $.ajax({
                url: '/sysRole/deleteRole.do',
                type: 'POST',
                data: {'ids': id},
                traditional: true,
                success: function (result) {
                    $.messager.progress('close');
                    if (result.status == 200) {
                        window.parent.showMessager("温馨提示", "<b>操作成功!</b>", 5000, "slide");
                        $('#role').datagrid('reload');
                    } else {
                        $.messager.alert("温馨提示", "操作异常,请联系管理员!", "error");
                    }
                }
            });
        });
    }

    //提交角色查询条件
    function searchRol() {
        var name = $("#name").val();
        $('#role').datagrid({
            queryParams: {
                name: name
            }
        });
    }

    function addQianFenFu(s) {
        if (/[^[+-]?0-9\.]/.test(s)) return "invalid value";
        s = s.replace(/^(\d*)$/, "$1.");
        s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
        s = s.replace(".", ",");
        var re = /(\d)(\d{3},)/;
        while (re.test(s))
            s = s.replace(re, "$1,$2");
        s = s.replace(/,(\d\d)$/, ".$1");
        return s.replace(/^\./, "0.")
    }

    function Refresh() {
        var json1 = {tabTitle: '角色列表', url: '/forward_sys_roleList'};
        window.parent.refreshTab(json1);
    }

</script>

</body>
</html>