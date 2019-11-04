$(function () {
	$.post("/costTaskType/treeList",function(data){	
		if(typeof(taskTypeId)=="undefined"){
    	    var d=[{id:-1,text:'请选择',children:data}];
    	    initCombotree2(d);
       	}else if(taskTypeId==""){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	    initCombotree2(d); 
       	}else{	
       		initCombotree2(data); 		       		
       	}
	}, "json");
	
})

//初始化组合树-合同分类
function initCombotree2(data) {
	var parents=[];
    $('#auditPriceType').combotree({
    	data:data,
    	multiple: true,
    	checkbox: true,
        onlyLeafCheck: true,
        height:30,
        options: [{

        }],   
        onBeforeCheck:function(node,checked){
        	/*
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
        	*/
        },
        onBeforeSelect:function(node){
        	if(node.id==-1){
            	return false;
            }else{
            	 if(node.children.length>0){
                	 return false;
                 }else{
                	 return true;
                 }
            }
        },
        onLoadSuccess: function (node, data) {
        	if(typeof(taskTypeId)=="undefined"){
        		$("#auditPriceType").combotree('setValue',-1);
	       	}else if(taskTypeId==""){
	       		$("#auditPriceType").combotree('setValue',-1);
	       	}else{	
	       		$("#auditPriceType").combotree('setValue',-1);    		
	       	}
        }
    });
}
