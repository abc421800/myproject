<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>广州市代建局造价审核管理系统</title>
   <!-- Common CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/fonts/icomoon/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/main1.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/main0.css" id="skin" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/themes/bootstrap/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/themes/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/index.css">
    <link rel="shortcut icon " type="images/x-icon" href="${pageContext.request.contextPath}/res/images/home/favicon.ico">

    <!-- 弹出框内容样-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/home/blacklayer.css"> 
</head>
<body class="easyui-layout">
    <!-- Loading starts -->
    <div id="loading-wrapper">
        <div id="loader">
            <div class="line1"></div>
            <div class="line2"></div>
            <div class="line3"></div>
            <div class="line4"></div>
            <div class="line5"></div>
            <div class="line6"></div>
        </div>
    </div>
    <!-- Loading ends -->
    <!-- BEGIN .app-wrap -->
    <div class="app-wrap">
        <!-- BEGIN .app-heading -->
        <header class="app-header">
            <div class="container-fluid">
                <div class="row gutters">
                   <div class="col-xl-4 col-lg-4 col-md-3 col-sm-3 col-4">
                        <a class="mini-nav-btn" href="Javascript:void(0)" id="app-side-mini-toggler">
                            <i class="icon-menu"></i>
                        </a>
                        <a href="Javascript:void(0);" data-toggle="onoffcanvas" class="onoffcanvas-toggler" aria-expanded="true">
                            <i class="icon-chevron-left"></i>
                        </a>
                    </div>
                     <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-4">
                        <a href="Javascript:void(0);" onclick="location.reload();" class="logo logo_title">
                            <img src="${pageContext.request.contextPath}/res/images/login/logo_login.png" alt="Admin"/>广州市代建局造价审核管理系统
                        </a>
                    </div>
                    <div class="col-xl-4 col-lg-4 col-md-3 col-sm-3 col-4">
                        <ul class="header-actions">
                        	<%--
                            <li class="dropdown">
                                <a href="Javascript:void(0)" id="notifications" data-toggle="dropdown" aria-haspopup="true" onclick="addTabGrid('消息', '')">
                                    <i class="icon-message green"></i>
                                    <span class="count-label"></span>
                                    <span class="info-name green">消息</span>
                                </a>
                            </li>
                            <li>
                                <a href="Javascript:void(0)" id="todos1" data-toggle="dropdown" aria-haspopup="true" onclick="addTabGrid('用户手册', '')">
                                    <i class="icon-shouce yellow"></i>
                                    <span class="count-label"></span>
                                    <span class="info-name yellow">手册</span>
                                </a>
                            </li>
                            <li>
                                <a href="Javascript:void(0)" id="kefu" data-toggle="dropdown" aria-haspopup="true">
                                    <i class="icon-kefu blue"></i>
                                    <span class="count-label"></span>
                                    <span class="info-name blue">客服</span>
                                </a>
                            </li>--%>
                            <li class="dropdown">
                                <a href="Javascript:void(0)" id="userSettings" class="user-settings" data-toggle="dropdown" aria-haspopup="true">
                                    <c:choose>
								    	<c:when test="${user.headImage eq null or user.headImage eq '' }">
								    	 	<img id="headImg1" src="${pageContext.request.contextPath}/res/images/home/user.jpg" class="avatar" width="40" height="40"  alt="headImage" >
								    	</c:when>
								    	<c:otherwise>
								    		<img id="headImg1" src="${pageContext.request.contextPath}/file/${user.headImage}" class="avatar" width="40" height="40"  alt="headImage">
								    	</c:otherwise>
								    </c:choose>
                                    <span class="user-name">${roleName}</span>
                                    <i class="icon-arrow_drop_down"></i>
                                </a>
                                <div class="dropdown-menu lg dropdown-menu-right dropdown_new" aria-labelledby="userSettings">
                                    <div class="account-info">
                                        <div>
                                        	 <c:choose>
										    	<c:when test="${user.headImage eq null or user.headImage eq '' }">
										    	 <img id="headImg2" src="${pageContext.request.contextPath}/res/images/home/user.jpg"  class="img-fluid rounded-circle mr-2" width="40" height="40" alt="">
										    	</c:when>
										    	<c:otherwise><img id="headImg2" src="${pageContext.request.contextPath}/file/${user.headImage}"  class="img-fluid rounded-circle mr-2" width="40" height="40" alt=""></c:otherwise>
										    </c:choose>
                                        </div>
                                        <div class="lh-12">
                                            <strong>您好：${user.name}</strong>
                                            <div class="text-light">欢迎使用 广州市代建局造价审核管理系统</div>
                                        </div>
                                    </div>
                                    <ul class="stats-widget">
                                        <li>
                                            <a href="Javascript:void(0)" class="dropdown-item" onclick="addTabGrid('个人中心', '/forward_sys_setting');">
                                                <i class="icon icon-setting"></i>
                                                <span class="middle">个人中心</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="Javascript:void(0)" class="dropdown-item">
                                                <i class="icon icon-skin"></i>
                                                <span class="middle">皮肤设置</span>
                                                <div class="skin-box" id="skin-box">
                                                    <span class="com skin1 active" onclick="">
                                                        <i class="icon2 icon-check"></i>
                                                    </span>
                                                    <span class="com skin2" onclick="">
                                                        <i class="icon2 icon-check"></i>
                                                    </span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="Javascript:void(0)" class="dropdown-item" onclick="logout('/logout')">
                                                <i class="icon icon-quit"></i>
                                                <span class="middle">退出系统</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <!-- END: .app-heading -->
        <!-- BEGIN .app-container -->
        <div class="app-container">
            <!-- BEGIN .app-side -->
            <aside class="app-side" id="app-side">
                <!-- BEGIN .side-content -->
                <div class="side-content ">
                    <!-- BEGIN .user-profile -->
                    <div class="user-profile">
                    <c:choose>
				    	<c:when test="${user.headImage eq null or user.headImage eq '' }">
				    	 <img id="headImg3" src="${pageContext.request.contextPath}/res/images/home/user.jpg" class="profile-thumb" alt="User Thumb" >
				    	</c:when>
				    	<c:otherwise><img id="headImg3" src="${pageContext.request.contextPath}/file/${user.headImage}" class="profile-thumb" alt="User Thumb"></c:otherwise>
				    </c:choose>
                        <h6 class="profile-name">欢迎 ${user.name}</h6>
                        <span class="date">${orgName}</span><br>
                        <span class="date">${currentDate}</span>
                    </div>
                    <!-- END .user-profile -->
                    <!-- BEGIN .side-nav -->
                    <nav class="side-nav">
                        <!-- BEGIN: side-nav-content -->
                        <ul class="unifyMenu" id="unifyMenu">
                            <!--<li class="selected active">-->
                            <shiro:hasPermission name="project:list">
                            <li>
                                <a href="Javascript:void(0)"  aria-expanded="false" onclick="addTabGrid('项目台账', '/forward_project_list');">
                                    <span class="has-icon">
                                        <i class="icon-proTaizhang"></i>
                                    </span>
                                    <span class="nav-title">项目台账</span>
                                </a>
                                <ul aria-expanded="false"  style="padding-bottom: 0;">
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="contract:list">
                            <li>
                                <a href="Javascript:void(0)"  aria-expanded="false" onclick="addTabGrid('合同台账', '/forward_contract_list');">
                                    <span class="has-icon">
                                        <i class="icon-hetong"></i>
                                    </span>
                                    <span class="nav-title">合同台账</span>
                                </a>
                                <ul aria-expanded="false"  style="padding-bottom: 0;">
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="document:list">
                            <li>
                                <a href="Javascript:void(0)"  aria-expanded="false" onclick="addTabGrid('往来文件', '/forward_document_list');">
                                    <span class="has-icon">
                                        <i class="icon-tishi"></i>
                                    </span>
                                    <span class="nav-title">往来文件</span>
                                </a>
                                <ul aria-expanded="false"  style="padding-bottom: 0;">
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="task:list">
                          	<li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                        <i class="icon-total"></i>
                                    </span>
                                    <span class="nav-title">审价任务</span>
                                </a>
                                <ul aria-expanded="false">
                                	<shiro:hasPermission name="task:ggyj:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('估概预结', '/forward_task_estimatePrefix?oneLoad=1');">估概预结</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="task:htbg:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('合同变更', '/forward_task_contractChange?oneLoad=1');">合同变更</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="task:djsh:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('单价审核', '/forward_task_priceCheck?oneLoad=1');">单价审核</a>
                                    </li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="priceLibrary:list">
                            <li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                       <i class="icon-guiji"></i>
                                    </span>
                                    <span class="nav-title">单价库</span>
                                </a>
                                <ul aria-expanded="false">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('综合单价库', '/forward_priceLibrary_comprehensivePriceBase');">综合单价库</a>
                                    </li>
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('主材单价库', '/forward_priceLibrary_mainPriceBase');">主材单价库</a>
                                    </li>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="statistics:list">
                            <li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                         <i class="icon-search1"></i> 
                                    </span>
                                    <span class="nav-title">统计查询</span>
                                </a>
                                <ul aria-expanded="false">

                                    <shiro:hasPermission name="js:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('结算台账', '/forward_project_projectJsCount');">结算台账</a>
                                    </li>
                                    </shiro:hasPermission>
                                   <%--<li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('合同结算统计表', '/forward_project_contractJsCount2');">合同结算统计表</a>
                                    </li>--%>
                                    <shiro:hasPermission name="ht:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('结算台账统计表', '/forward_project_contractJsCount');">结算台账统计表</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="xmzb:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('项目指标查询', '/forward_project_projZbList');">项目指标查询</a>
                                    </li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="yaohaoMng">
                            <li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                         <i class="icon-rock-number"></i> 
                                    </span>
                                    <span class="nav-title">摇号管理</span>
                                </a>
                                <ul aria-expanded="false">
                                    <shiro:hasPermission name="candidate:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('摇号名单', '/yaohaoCandidate/toNameList');">摇号名单</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="order:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('摇号台账', '/yaohaoOrder/toOrderList');">摇号台账</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="winbid:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('中签记录', '/forward_yaohao_yaohaoMng_winList');">中签记录</a>
                                    </li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="consult">
                            <li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                        <i class="icon-form-manager"></i>
                                    </span>
                                    <span class="nav-title">造价咨询管理</span>
                                </a>
                                <ul aria-expanded="false">
                                	<shiro:hasPermission name="enterprise:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('入库企业', '/costEnterprise/toEnterpriseList');">入库企业</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="assess:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('综合考核', '/assess/toList');">综合考核</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="publish:list">
                                    <li>
                                        <a href="Javascript:void(0)" onclick="addTabGrid('奖惩记录', '/yaohaoPunish/toPunishList');">奖惩记录</a>
                                    </li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            
                            <shiro:hasPermission name="attendanceMng"> 
                             <li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                        <i class="icon-attendance"></i>
                                    </span>
                                    <span class="nav-title">考勤管理</span>
                                </a>
                                <ul aria-expanded="false">
                                	<shiro:hasPermission name="register:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('考勤登记', '/workRegister/tolist');">考勤登记</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="attendanceLedger:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('考勤台账', '/workAttendancePerson/toAttendancePerson');">考勤台账</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="workEnterprise:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('驻场企业', '/forward_workEnterprise_list');">驻场企业</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="register:leave">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('请假申请', '/forward_processApply_list');">请假申请</a>
                                    </li>
                                    </shiro:hasPermission>
                                	</ul>
                            </li>
                            </shiro:hasPermission>
                            
                            <shiro:hasPermission name="dictionary">
                            <li>
                                <a href="Javascript:void(0)" class="has-arrow" aria-expanded="false">
                                    <span class="has-icon">
                                        <i class="icon-data-dic"></i>
                                    </span>
                                    <span class="nav-title">数据字典</span>
                                </a>
                                <ul aria-expanded="false">
                                	<shiro:hasPermission name="projectType:list">	
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('项目分类', '/forward_projectType_projectTypeIndex');">项目分类</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="projectNode:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('项目节点', '/forward_projectNode_list');">项目节点</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="projectGXR:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('项目干系人', 'forward_projectStakeholderrole_list');">项目干系人</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="taskType:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('审价类型', 'forward_taskType_taskTypeIndex');">审价类型</a>
                                    </li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="contractType:list">
                                    <li>
                                        <a href='Javascript:void(0)' onclick="addTabGrid('合同类型', '/forward_contractType_list');">合同类型</a>
                                    </li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                        <!-- END: side-nav-content -->
                    </nav>
                    <!-- END: .side-nav -->
                </div>
                <!-- END: .side-content -->
            </aside>
            <!-- END: .app-side -->

            <!-- BEGIN .app-main -->
            <div class="app-main clearfix">
                <div id="pf-page" class="ui-layout-center"  data-options="region:'center',fit:true" style="height: 100%;">
                    <div id="hualian_layout_center_tab_container" class="easyui-tabs1"  data-options=" fit:true,enableJumpTabMenu: true,scrollIncrement:200" >
                        <div id="homePanel" data-options="title: '项目台账', iconCls: ''">
                            <iframe class="page-iframe" name="iframe" src="/forward_project_list" frameborder="no" border="no" height="200" width="100%" scrolling="auto">
                            	</iframe>
                        	
                        	
                        </div>
                    </div>
                </div>
            </div>
            <!-- END: .app-main -->
        </div>
        <!-- END: .app-container -->
    </div>
    <!-- END: .app-wrap -->
    <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose" data-options="name:1,iconCls:'icon-standard-application-form-delete'">关闭</div>
        <div id="mm-tabcloseall" data-options="name:2,iconCls:'icon-standard-cross'">全部关闭</div>
        <div id="mm-tabcloseother" data-options="name:3,iconCls:'icon-standard-cancel'">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabclosrefresh" data-options="name:6,iconCls:'icon-standard-table-refresh'">刷新</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright" data-options="name:4,iconCls:'icon-standard-tab-close-right'">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft" data-options="name:5,iconCls:'icon-standard-tab-close-left'">当前页左侧全部关闭</div>
    </div>
    <div class="layerBox">
        <div class="head_title clearfix">
            <span class="tit">客服</span>
            <a href="Javascript:void(0)" class="close"></a>
        </div>
        <div class="content">
            <ul class="clearfix">
                <li>
                    <a href="Javascript:void(0)">微信</a>
                    <div class="tip_wx">
                        <div class="wrap">
                            <div class="jt"></div>
                            <div class="title">广东华联</div>
                            <img src="${pageContext.request.contextPath}/res/images/home/hl_qcode.png" alt="">
                        </div>
                    </div>
                </li>
                <li><a href="Javascript:void(0)">服务支持</a></li>
                <li>
                    <a href="Javascript:void(0)">客服电话</a>
                    <div class="tip_tel">
                        <div class="wrap">
                            <div class="jt"></div>
                            <ul class="stats-widget">
                                <li>
                                    <div class="title">客服电话</div>
                                    <span class="tel">020-37063313</span>
                                </li>
                                <li>
                                    <div class="title">行政前台</div>
                                    <span class="tel">020-37063333</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="kefu">
    </div>
    <!-- jQuery first, then Tether, then other JS. -->
    <!--<script src="js/jquery.js"></script>-->
    <script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/tether.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/unifyMenu.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/onoffcanvas.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/moment.js"></script>

    <!-- Slimscroll JS -->
    <script src="${pageContext.request.contextPath}/res/js/home/slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/custom-scrollbar.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/layout.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/home/skinTheme.js"></script>

    <!-- Common JS -->
    <script src="${pageContext.request.contextPath}/res/js/home/common.js"></script>
    <script>
    	function setHeadImg(url){
    		$("#headImg1").attr("src",url);
    		$("#headImg2").attr("src",url);
    		$("#headImg3").attr("src",url);
    	}
    </script>
</body>
</html>