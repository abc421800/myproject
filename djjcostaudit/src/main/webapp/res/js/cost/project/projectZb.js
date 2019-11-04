$(function () {
    var bool = false;
    $(".switchBtn").on('click', function () {
        $(".filter").toggle();
        if (!bool) {
            $(this).addClass("active");
            $('#projectIndexAccounts').datagrid('resize', {
                height: $(window).height() - 40
            });
            bool = true;
        } else {
            $(this).removeClass("active");
            bool = false;
            $('#projectIndexAccounts').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
    $('#projectIndexAccounts').datagrid({
        url: '/costProject/getProjectZblist.do',
        loadMsg: '数据加载中,请稍候...',
        nowrap: true,
        rownumbers: true,
        height: tableHeight(),
        fitColumns: false,
        striped: true,
        collapsilble: true,
        pagination: true, //分页控件
        remoteSort:false,
        multiSort:true,
        pageSize: 10,
        idField:"id",
        singleSelect: false,
        frozenColumns:[[
            {
                field: 'id',
                title : '单选框',
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
                width: 230,
                align: 'left',
                sortable: false,
                formatter: function (value, rowData, index) {
                	if(value!=null){
                		return "<a href=\"javascript:void(0)\" style=\"color:#549de3;\" title=\""+value+"\" onclick=\"editInfo('"+rowData.id+"')\">"+value+"</a>";
                	}
                	else{
                		return "";
                	}
                }
            }
        ]],
        columns: [
            [{
                field: 'projectClassification',
                title: '项目分类',
                width: 140,
                align: 'center',
                sortable: false,
                rowspan:2
            },{
                field: 'area',
                title: '工程地点',
                width: 120,
                align: 'center',
                sortable: false,
                rowspan:2
            },
                 {
                field: 'personLiableId',
                title: '项目负责人',
                align: 'center',
                width: 130,
                sortable: false,
                rowspan:2

            },  {
                field: 'unit',
                title: '工程单位',
                align: 'center',
                width: 150,
                sortable: false,
                rowspan:2

            },  {
                title: '项建估算指标',
                align: 'center',
                width:600,
                sortable: false,
                colspan: 4

            },{
                title: '可研估算指标',
                align: 'center',
                width:600,
                sortable: false,
                colspan: 4

            },{
                title: '概算指标',
                align: 'center',
                width:600,
                sortable: false,
                colspan: 4

            }],[
                //项建估算指标
                {
                    field: 'xjGsCost',
                    title: '单方造价',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.costUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'xjGsArchitectural',
                    title: '建筑工程',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.architecturalUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'xjGsInstallation',
                    title: '安装工程',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.installationUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'outdoorCost',
                    title: '室外配套',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.outdoorUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                //可研估算指标
                {
                    field: 'kyGsCost',
                    title: '单方造价',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.costUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'kyGsArchitectural',
                    title: '建筑工程',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.architecturalUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'kyGsInstallation',
                    title: '安装工程',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.installationUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'outdoorKyGs',
                    title: '室外配套',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.outdoorUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                //概算指标
                {
                    field: 'gsCost',
                    title: '单方造价',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.costUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'gsArchitectural',
                    title: '建筑工程',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.architecturalUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'gsInstallation',
                    title: '安装工程',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.installationUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                {
                    field: 'outdoorGsCost',
                    title: '室外配套',
                    width: 150,
                    halign:'center',
                    align: 'right',
                    sortable: false,
                    formatter: function (value, rowData, index) {
                    	if(value!=null){
                    		return addQianFenFu(String(value))+rowData.outdoorUnit;
                    	}else{
	                		return ""
	                	}
                    }
                },
                ]
        ],
        toolbar: '#tit1',
    });
    $(window).resize(function () {
        if(bool){
            $('#projectIndexAccounts').datagrid('resize', {
                height: $(window).height() - 40
            });
        }else{
            $('#projectIndexAccounts').datagrid('resize', {
                height: tableHeight()
            });
        }
    });
});

function showInfo(zjkClId) {
    var href = "/costProject/editProject?editFlag=n&projId="+zjkClId;
    var title = "项目详情";
    top.addTabGrid(title, href);
}
function editInfo(zjkClId) {
    var href = "/costProject/editZb?editFlag=y&projId="+zjkClId;
    var title = "项目指标信息修改";
    top.addTabGrid(title, href);
}
function exportExcel() {
    var frozenColumns =  $('#projectIndexAccounts').datagrid("options").frozenColumns;  // 得到frozenColumns对象
    var columns =  $('#projectIndexAccounts').datagrid("options").columns;  // 得到Columns对象
    columns = (frozenColumns[0] || []).concat(columns[0] || []).concat(columns[1] || []);
    var rows =  $('#projectIndexAccounts').datagrid("getRows");
    var data = new Array();
    var tmp = new Array();
    var titArr=new Array();
    //列名
    $.map(columns, function (a) {
        if (!a.hidden && a.field) {
            tmp.push(a.field);
            titArr.push(a.title)

        }
    });
    console.log(JSON.stringify(data)) ;
    return JSON.stringify(data);
}
function fixGetValue(obj) {
    return obj ? obj : "";
}
//提交查询条件
function selectSubmit(){
  	var tableId='projectIndexAccounts';
  	var code=$("#code").val();
  	var name=$("#name").val();
	var personLiableId=$("#personLiableId").val();	  
  	var projectClassificationArray=$("#projectClassificationId").combotree("getValues");
  	var projectClassificationId="";
  	for(var i=0;i<projectClassificationArray.length;i++){
  		projectClassificationId = projectClassificationId+projectClassificationArray[i]+",";
  	}
   /* for(let i of projectClassificationArray){
    	projectClassificationId = projectClassificationId+i+",";
    }*/
    projectClassificationId = projectClassificationId.substring(0,projectClassificationId.length-1);
    if(projectClassificationId==-1){
    	projectClassificationId=null;
    }
    var area=$("#area").val();	 
  	var startTime =$("#startTime").val();
  	var endTime = $("#endTime").val();
 
 	$('#'+tableId).datagrid('options').queryParams = {
 		   'code': code, 
           'name': name,  
           'personLiableId':personLiableId,
            'projectClassificationId':projectClassificationId,
            'area':area,
           'startTime':startTime,
           'endTime':endTime
       };
   $('#' + tableId).datagrid("reload");
}
//自动刷新
function reset1() {
	var json1={tabTitle:'项目指标查询',url:'/forward_project_projZbList'};
	window.parent.refreshTab(json1); 
}