$(function () {
	//部门树
	combotreeData(orgId);
})

function combotreeData(id){
	$.post("/sysOrg/orgTree",function(data){		
		if(typeof(id)=="undefined"){
    	    var d=[{id:-1,text:'请选择',children:data}];
    	    initCombotree12(d); 						  
       	}else if(id==""){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	 initCombotree12(d); 	 
       	}else if(id==-1){
       	    var d=[{id:-1,text:'请选择',children:data}];
       	 initCombotree12(d); 	 
       	}else{		       		
       		initCombotree12(data); 		       		
       	}
	       			       			
	}, "json");
}

function initCombotree12(data) {
	var parents=[];
    $('#orgCombotreeSelect').combotree({
    	data:data,
    	multiple: true,
        checkbox: true,
        height:30,
        onlyLeafCheck: true,
        onBeforeSelect:function(node,checked){
        	if (!$("#orgCombotreeSelect").combotree("tree").tree('isLeaf', node.target)) {
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
        		$("#orgCombotreeSelect").combotree('setValue',-1);
	       	}else if(orgId==""){
	       		$("#orgCombotreeSelect").combotree('setValue',-1);
	       	}else{	
	       		$("#orgCombotreeSelect").combotree('setValues',orgId);	       		
	       	}
        }
    });
}