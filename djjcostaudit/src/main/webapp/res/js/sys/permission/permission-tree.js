/*权限树*/
var perIds=$('.hidden-perId').val();
if(perIds){
	var arr_perIds=perIds.split(",");
}
permissionTree.tree({
	url:'/sysPermission/treeList', 
	animate:true ,
	cascadeCheck : false ,
	checkbox:true,
	lines:"true",  
	dnd: false,
	onClick:function(node){
		console.log(node);
		if(node._checked){
            permissionTree.tree('uncheck',node.target);
		}else{
			permissionTree.tree('check',node.target);
		}
	},
	onCheck: function (node, checked){
		//alert(checked)
		if(on_off){
			return;
		}
        var childNode = permissionTree.tree('getChildren', node.target);
            if (checked) {
            	var root =permissionTree.tree('getParent',node.target);
            	if (!root && typeof(root)!="undefined" && root!=0){
            		   //点击根节点走这里
					   permissionTree.tree('check', node.target);
					   var lab='<span id="'+node.id+'" class="permissionName">'+node.text+'</span>';
					    $("#permissionName").before(lab);
					if (childNode.length > 0) {
                        for (var i = 0; i < childNode.length; i++) {
	                       permissionTree.tree('check', childNode[i].target);
	                    }
                    }
				}else{
					//alert(childNode.length)
					if (childNode.length > 0) {
					    permissionTree.tree('check', node.target);
					    var lab='<span id="'+node.id+'" class="permissionName">'+node.text+'</span>';
					    $("#permissionName").before(lab);
                        for (var i = 0; i < childNode.length; i++) {
                        	on_off=true;
                            permissionTree.tree('check', childNode[i].target);
                            var lab='<span id="'+childNode[i].id+'" class="permissionName">'+childNode[i].text+'</span>';
    					    $("#permissionName").before(lab);
                        }
                	}else{
                		
                		 permissionTree.tree('check', node.target);
                		 var lab='<span id="'+node.id+'" class="permissionName">'+node.text+'</span>';
 		        		$("#permissionName").before(lab);
                	}
				}
            }else{
                if (childNode.length > 0) {
                    for (var i = 0; i < childNode.length; i++) {
                    	$("#"+childNode.id).remove();
                        permissionTree.tree('uncheck', childNode[i].target);
                    }
                }
                $("#"+node.id).remove();
            }
            on_off=false;
     },
     onBeforeCheck:function(node,checked){
    	 return true;
     },
	onLoadSuccess:function(node, data){  //初始化显示
		var rootNode = permissionTree.tree('getRoot');
	    //选中根节点
		permissionTree.tree('select',rootNode.target);
	    //
	    var treeOptions = permissionTree.tree('getChildren');//获取所以
	    if(treeOptions){
	       $(treeOptions).each(function(index,d){
	        	for(i in arr_perIds){
	        		if(d.id==arr_perIds[i]){
		        		on_off=true;
		        		permissionTree.tree('check', d.target);
		        		var node = permissionTree.tree('find', d.id);
		        		var lab='<span id="'+node.id+'" class="permissionName">'+node.text+'</span>';
		        		$("#permissionName").before(lab)
	        		}
	        	}
	        });
	    }
	    on_off=false;
	    
	}
});
function getChecked(){
	var nodes = permissionTree.tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
		s += nodes[i].id;
	}
	return s;
}

function getCheckedName(){
	var nodes = permissionTree.tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
		s += nodes[i].text;
		var lab='<span id="'+nodes[i].id+'" class="permissionName">'+nodes[i].text+'</span>';
 		$("#permissionName").before(lab);
	}
	return s;
}