$(function () {
	//部门树
	combotreeData1(orgId);
})

function combotreeData1(id){
	$.post("/sysOrg/orgTree",function(data){		
		if(typeof(id)=="undefined"){
    	    var d=[{id:-1,text:'请选择',children:data}];
    	    initCombotree1(d); 						  
       	}else if(id==""){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	 initCombotree1(d); 	 
       	}else if(id==-1){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	 initCombotree1(d); 	 
       	}else{		       		
       		initCombotree1(data); 		       		
       	}
	       			       			
	}, "json");
}

function initCombotree1(data) {
	var parents=[];
    $('#orgCombotree').combotree({
    	data:data,
        onBeforeSelect:function(node,checked){
        	if (!$("#orgCombotree").combotree("tree").tree('isLeaf', node.target)) {
                return false;
            }
        	/*if(checked){
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
        	}*/
        },
        onLoadSuccess: function (node, data) {
        	if(typeof(orgId)=="undefined"){
        		$("#orgCombotree").combotree('setValue',-1);
	       	}else if(orgId==""){
	       		$("#orgCombotree").combotree('setValue',-1);
	       	}else{	
	       		$("#orgCombotree").combotree('setValues',orgId);	       		
	       	}
        }
    });
}