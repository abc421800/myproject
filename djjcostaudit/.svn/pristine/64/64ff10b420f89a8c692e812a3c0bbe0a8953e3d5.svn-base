$(function(){
	
	$('#processRecord').datagrid({
        url: '/workProcessApply/recodList?id='+processRecordId,
        loadMsg: '数据加载中,请稍候...',
        nowrap: false,
        rownumbers: true,
        height: "300",
        fitColumns: true,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        pageSize: 10,
        singleSelect: false,
        columns: [
            [{
                field: 'operatorName',
                title: '操作人',
                width: 150,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                      return value;
                }
            }, {
                field: 'createTimeStr',
                title: '创建时间',
                width: 120,
                align: 'left',
                sortable: false,
                formatter: function(value, rowData, index) {
                    return value;
                }
            }, {
                field: 'operatorTimeStr',
                title: '处理时间',
                width: 100,
                align: 'center',
                sortable: false,
                formatter: function(value, rowData, index) {
                    return value;
                }

            }, {
                field: 'consumeTime',
                title: '耗时',
                align: 'center',
                width: 120,
                sortable: false,
                formatter: function(value, rowData, index) {
                    return value;
                }

            },{
                field: 'operatorNode',
                title: '状态',
                align: 'center',
                halign:'center',
                width: 100,
                sortable: false,
                formatter: function (value, rowData, index) {
                	return value;
                }
            }, {
                field: 'opinion',
                title: '意见',
                align: 'left',
                width: 300,
                sortable: false

            }
            ]
        ],
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $('#processRecord').datagrid('loadData', {total: 1, rows: [{operatorName: "未找到相关信息！"}]});
            }
        }
    });
	
	
	
	
});