function addTabGrid(title, href) {
	var tabContainer = $('#hualian_layout_center_tab_container');
	addTab(tabContainer, title, href, '');

}

function addTab(tabId, title, href, icon) {
	var tt = tabId;
	if (tt.tabs('exists', title)) {
		tt.tabs('select', title);
		refreshTab({
			tabId : tabId,
			tabTitle : title,
			url : href
		});
	} else {
		if (href) {
			if(title=="预览"){
				var content = '<iframe scrolling="yes" class="page-iframe" frameborder="0"  src="'
					+ href + '" style="width:100%;height:100%;" id="'+title+'"></iframe>';
			}else{
				var content = '<iframe scrolling="no" class="page-iframe" frameborder="0"  src="'
					+ href + '" style="width:100%;height:100%;" id="'+title+'"></iframe>';
			}
        } else {
            var content ="未实现";
        }
		tt.tabs('add', {
			title : title,
			tabHeight: 40,
			iconCls:'',
			closable : true,
			content : content
		});
	}

       $("#hualian_layout_center_tab_container").tabs("getTab", title).find('iframe').height($(window).height()-63-49);

}
/**
 * 
 * @cfg example: {tabTitle:'tabTitle',url:'refreshUrl'}
 */
function refreshTab(cfg) {
	var refresh_tab = cfg.tabTitle ? $("#hualian_layout_center_tab_container").tabs('getTab', cfg.tabTitle)
			: $("#hualian_layout_center_tab_container").tabs('getSelected');
	if (refresh_tab && refresh_tab.find('iframe').length > 0) {
		var _refresh_ifram = refresh_tab.find('iframe')[0];
		var refresh_url = cfg.url ? cfg.url : _refresh_ifram.src;
		// _refresh_ifram.src = refresh_url;
		_refresh_ifram.contentWindow.location.href = refresh_url;
	}
}

function refreshTabByTitle(title) {
	alert(title);
    var targetTab = getTabContainer().tabs('getTab', title)[0];
    var targetFrame = $(targetTab).find('iframe')[0];
    targetFrame.contentWindow.location.reload();
}


/**
 */
function getDataGrid(tabTitle, dataGridId) {
	var tabContainer = getTabContainer();
	var exist_tab = tabContainer.tabs('getTab', tabTitle);
	// tabContainer.tabs('select',tabTitle);
	if (exist_tab && exist_tab.find('iframe').length > 0) {
		var exist_ifram = exist_tab.find('iframe')[0];
		var iframDoc = exist_ifram.contentWindow;
		var dataGridObj = iframDoc.$('#' + dataGridId);
		return dataGridObj;
	}
}
function getTabContainer() {
	var tabContainer = $('#hualian_layout_center_tab_container');
	return tabContainer;
}
function closeWindow(title) {
	var tabContainer = getTabContainer();
	if (tabContainer.tabs('exists', title)) {
		var existTab = tabContainer.tabs('getTab', title);
		tabContainer.tabs('close', title);
	} else {
		$("#" + title).window('destroy');
	}
	/* $("#" + windowId).window('destroy'); */
}

/**
 */
function selectTab(title) {
	var tabContainer = getTabContainer();
	if (tabContainer.tabs('exists', title)) {
		tabContainer.tabs('select', title);
	}
}
/**
 * * 
 */
function logout(url) { 
     $.extend($.messager.defaults, {
        ok: "确定",
        cancel: "取消",
    });
	$.messager.confirm('退出提示 ',"确认退出当前系统？", function(r) {
		if (r) {
			location.href = url;
		}
	});
	
}
function closeTab(menu, type) {
    var allTabs = $("#hualian_layout_center_tab_container").tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs, function (i, n) {
        var opt = $(n).panel('options');
        if (opt.closable)
            allTabtitle.push(opt.title);
    });
    var curTabTitle = $(menu).data("tabTitle");
    var curTabIndex = $("#hualian_layout_center_tab_container").tabs("getTabIndex", $("#hualian_layout_center_tab_container").tabs("getTab", curTabTitle));
    switch (type) {
        case "1":
        	$("#hualian_layout_center_tab_container").tabs("close", curTabTitle);
            return false;
            break;
        case "2":
            for (var i = 0; i < allTabtitle.length; i++) {
                $('#hualian_layout_center_tab_container').tabs('close', allTabtitle[i]);
            }
            break;
        case "3":
            for (var i = 0; i < allTabtitle.length; i++) {
                if (curTabTitle != allTabtitle[i])
                    $('#hualian_layout_center_tab_container').tabs('close', allTabtitle[i]);
            }
            $('#hualian_layout_center_tab_container').tabs('select', curTabTitle);
            break;
        case "4":
            for (var i = curTabIndex; i < allTabtitle.length; i++) {
                $('#hualian_layout_center_tab_container').tabs('close', allTabtitle[i]);
            }
            $('#hualian_layout_center_tab_container').tabs('select', curTabTitle);
            break;
        case "5": 
            for (var i = 0; i < curTabIndex - 1; i++) {
                $('#hualian_layout_center_tab_container').tabs('close', allTabtitle[i]);
            }
            $('#hualian_layout_center_tab_container').tabs('select', curTabTitle);
            break;
        case "6": 
         	var refresh_tab = $("#hualian_layout_center_tab_container").tabs("getTab",curTabTitle);
         	if (refresh_tab && refresh_tab.find('iframe').length > 0) {
        		var _refresh_ifram = refresh_tab.find('iframe')[0];
        		var refresh_url =  _refresh_ifram.src;
        		_refresh_ifram.contentWindow.location.href = refresh_url;
        	}
            break;
    }

}
$(function(){
     //监听右键事件，创建右键菜单
    $('#hualian_layout_center_tab_container').tabs({
        onContextMenu: function(e, title, index) {
            e.preventDefault();
            if (index > 0) {
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        }
    });
    //右键菜单click
    $("#mm").menu({
        onClick: function(item) {
            closeTab(this, String(item.name));
        }
    });
});
   
//弹出消息提示框
function showMessager(title, msg, timeout, showType) {
	$.messager.show({
		title : title,
		msg : msg,
		timeout : timeout,
		showType : showType
	});
}

