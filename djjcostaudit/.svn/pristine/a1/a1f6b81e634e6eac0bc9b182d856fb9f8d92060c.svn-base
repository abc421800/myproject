$(function () {
	
	$.post("/costProjectType/treeList",function(data){	
		if(typeof(projectType)=="undefined"){
    	    var d=[{id:-1,text:'请选择',children:data}];
    	    initCombotreeProj2(d);
       	}else if(projectType==""){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	    initCombotreeProj2(d);
       	}else{		       		
       		initCombotreeProj2(data);
       	}
	}, "json");
});

//初始化组合树
function initCombotreeProj2(data) {
	var parents=[];
    $('#projectClassificationId2').combotree({
    	data:data,
        multiple: true,
        checkbox: true,
        height:30,
        onlyLeafCheck: true,
        options: [{

        }],   
      
        onBeforeSelect:function(node,checked){
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
        onLoadSuccess: function (node, data) {
        	if(typeof(projectType)=="undefined"){
        		$("#projectClassificationId2").combotree('setValue',-1);
	       	}else if(projectType==""){
	       		$("#projectClassificationId2").combotree('setValue',-1);
	       	}else{	
	       		$("#projectClassificationId2").combotree('setValues',projectType);
	       	}
        }
    });
}


