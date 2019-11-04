$(function () {
	
	$.post("/costTaskType/treeList",function(data){	
		if(typeof(taskType)=="undefined"){
    	    var d=[{id:-1,text:'请选择',children:data}];
    	    initCombotree(d);
       	}else if(taskType==""){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	    initCombotree(d); 
       	}else{		       		
       		initCombotree(data); 		       		
       	}
	}, "json");
	
	
		
});

//初始化组合树
function initCombotree(data) {
    $('#valuationTask').combotree({
    	data:data,
        multiple: false,
        onlyLeafCheck: true,
        options: [{

        }],   
        onBeforeSelect:function(node){
        	 $("#valuationTaskParent").val(node.parentId);
        	if(node.children==undefined){
                return true;
            }else if(node.children.length>0){
           	 	return false;
            }else{
           	 	return true;
            }
        },
        onLoadSuccess: function (node, data) {
        	if(typeof(taskType)=="undefined"){
        		$("#valuationTask").combotree('setValue',-1);	
	       	}else if(taskType==""){
	       		$("#valuationTask").combotree('setValue',-1);
	       	}else{		       		
	       		$("#valuationTask").combotree('setText',taskType);	       		
	       	}
        }
    });
}
