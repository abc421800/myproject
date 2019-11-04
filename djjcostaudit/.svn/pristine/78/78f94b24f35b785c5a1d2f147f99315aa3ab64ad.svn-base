$(function () {
	
	$.post("/costTaskType/treeListByPid?id=2",function(data){	
		if(typeof(auditPriceType)=="undefined"){
    	    var d=[{id:-1,text:'请选择',children:data}];
    	    initCombotree(d);
       	}else if(auditPriceType==""){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	    initCombotree(d); 
       	}else{		       		
       		initCombotree(data); 		       		
       	}
	}, "json");
	
})

//初始化组合树-合同分类
function initCombotree(data) {
	var parents=[];
    $('#auditPriceType').combotree({
    	data:data,
    	multiple: false,
    	checkbox: true,
        onlyLeafCheck: true,
        height:30,
        options: [{

        }],   
        onClick:function(node){
        	var parentName=$("#auditPriceType").combotree('tree').tree('find', node.parentId).text;
        	if('设计图纸变更'== parentName){
        		$("#auditPriceTypecn").val("设计图纸变更");
        		$("#progressDescription").attr("placeholder","填入变更内容等信息");
        		$("#whereabouts").show();
        		$("#whereaboutsQx").show();
        	}else{
        		$("#auditPriceTypecn").val("合同变更");
        		$("#progressDescription").attr("placeholder","填入审价任务情况说明");
        		$("#whereabouts").hide();
        		$("#whereaboutsQx").hide();
        	}
        },
        onBeforeCheck:function(node,checked){
        	if(checked){
        		var exist=$.inArray(node.parentId,parents);
            	if(exist==-1){
            		parents.push(node.parentId);
            		return true;
            	}else{
            		return false;
            	}
        	}else{
        		parents=$.grep(parents,function(n,i){
        			return (n!=node.parentId);
        		});
        	}
        },
        onBeforeSelect:function(node){
        	if(node.id==-1){
            	return false;
            }else{
            	 if(node.children.length>0){
                	 return false;
                 }else{
                	 $("#auditPriceTypeName").val(node.text);
                	 return true;
                 }
            }
        },
        onLoadSuccess: function (node, data) {
        	if(typeof(auditPriceType)=="undefined"){
        		$("#auditPriceType").combotree('setValue',-1);
	       	}else if(auditPriceType==""){
	       		$("#auditPriceType").combotree('setValue',-1);
	       	}else{	
	       		$("#auditPriceType").combotree('setValues',auditPriceType);	       		
	       	}
        }
    });
}
