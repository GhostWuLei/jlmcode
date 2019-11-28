<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%@include file="/WEB-INF/jsp/commons/jstl_message_tld.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><fmt:message key="app.name" bundle="${commonBundle}"/></title>
    <link href="favicon.ico" rel="SHORTCUT ICON" />
    <script type="text/javascript">
        appWebRoot='<%=basePath%>';//网站地址
    </script>
    <%@ include file="/WEB-INF/jsp/commons/include.head.jsp"%>
</head>
<body >
<div id="sy_layout" class="easyui-layout" fit="true">

    <!-- 顶部-->
    <div  region="north" border="false"  style="height: 38px; padding: 1px; overflow: hidden;" >
        <div style="float: left;">
            <img src="resource/images/logo/logo.png"   style="margin-left: 7px;"/>
        </div>

        <div style="padding-top:2px;background-color: #F9F9F9;height: 100%">
            <div style="float: right;">
                <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="ui-icon-lock" onclick="MUI.openDialog('系统已锁定','sy_login/lock.do?rel=jm_look','jm_look',{width:500,height:200,collapsible:false,minimizable:false,maximizable:false,closable:false,modal:true,resizable:false,draggable:false})" title="锁定系统界面"></a>
                <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_gryhMenu" iconCls="ui-icon-cog">当前用户：<my:outLoginInfo type="trueName"/></a>
            </div>

            <%-- 隐藏菜单 --%>
            <div id="layout_north_gryhMenu"	style="width:150px;display: none;">

                <div iconCls="icon-back" onclick="goOutSystem()">安全退出</div>
                <div class="menu-sep"></div>
                <div>
                    <span>个人中心</span>
                    <div style="width:150px;">
                        <div onclick="MUI.openDialog('个人信息','user/my/info.do?rel=grbg_grse_myinfo','grbg_grse_myinfo',{width:1000,height:450})">个人信息</div>
                        <div onclick="MUI.openDialog('登陆日志','log/login/my.do?rel=grbg_grse_log','grbg_grse_log',{width:1000,height:450})">登陆日志</div>
                        <div onclick="MUI.openDialog('登陆管理','user/my/loginInfo.do?rel=main_login_info','main_login_info',{width:1000,height:450})">登陆管理</div>
                        <div onclick="MUI.openDialog('修改密码','user/updateMyPwPage.do?rel=grsz_xgmm','grsz_xgmm',{width:750,height:350})">修改密码</div>
                    </div>
                </div>
                <div>
                    <span>更换主题</span>
                    <div style="width:150px;">
                        <div onclick="changeEasyUITheme('default');">default</div>
                        <div onclick="changeEasyUITheme('gray');">gray</div>
                        <div onclick="changeEasyUITheme('bootstrap');">bootstrap</div>
                        <div onclick="changeEasyUITheme('black');">black</div>
                        <div class="menu-sep"></div>
                        <div onclick="changeEasyUITheme('metro');">metro</div>
                        <div onclick="changeEasyUITheme('metro-blue');">metro-blue</div>
                        <div onclick="changeEasyUITheme('metro-gray');">metro-gray</div>
                        <div onclick="changeEasyUITheme('metro-green');">metro-green</div>
                        <div onclick="changeEasyUITheme('metro-orange');">metro-orange</div>
                        <div onclick="changeEasyUITheme('metro-red');">metro-red</div>
                    </div>
                </div>
                <div iconCls="icon-help">
                    <a	href="#" target="_blank" >在线帮助</a>
                </div>
            </div>


            <div id="div_top_2" >

                <div style="width: 200px;"><span id="clock" ></span></div>

            </div>
        </div>
    </div>

    <!-- 中间工作区-->
    <div id="mainPanle" region="center" style="overflow: hidden;padding:1px">

        <div  class="easyui-tab" id="mainWorkTab" fit="true" border="false" tabHeight="35" >
            <!-- 首页 -->
            <div   title="<img src='resource/images/menu/1_close.png'/> 首页"  >

                <div id="sy_layout" class="easyui-layout" fit="true">
                    <!-- 左侧-->
                    <div region="west" split="true"  title="导航菜单" style="width:230px; padding: 1px;">
                        <div id="nav" class="easyui-accordion " fit="true" >
                            <div title="快捷菜单" iconCls="ui-icon-computer" tools="#left_kjmenu_tools" >
                                <ul class="ul-menu" style="margin-left: 2px;margin-right: 2px;margin-top: 2px">
                                    <%--<c:forEach  var="m"  items="${requestScope.mymenus }" >
                                        <li>
                                            <c:choose>
                                            <c:when test="${empty m.url }">
                                            <a style="color: black;">
                                                </c:when>
                                                <c:otherwise>
                                                <a  href="${m.url }" target="${m.target }" external="${m.external }" fresh="false" rel="my_${m.rel }" title="<c:out value="${m.name }"/>">
                                                    </c:otherwise>
                                                    </c:choose>
                                                    &nbsp;&nbsp;<img src="${m.icon }" />&nbsp;&nbsp;<c:out value="${m.name }"/></a>
                                        </li>
                                    </c:forEach>--%>
                                    <li>
                                        <a style="color: black;">XXXXXXXXXXXX<img src=""></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <%--<%@ include file="/WEB-INF/jsp/main/left.jsp"%>--%>
                    </div>
                    <!-- 中间-->
                    <div  region="center" style="overflow: hidden;border-top: none" >
                        <div  class="maintabs"  fit="true" border="false" >
                            <%--<div  id="tab-mainPage" title="首页"  href="main/home.do"	tools="#tab-mainPage_tools"></div>--%>
                        </div>
                    </div>
                </div>
            </div>

            <div   title="<img src='resource/images/menu/1_close.png'/> 首页"  >

                <div id="sy_layout" class="easyui-layout" fit="true">
                    <!-- 左侧-->
                    <div region="west" split="true"  title="导航菜单1" style="width:230px; padding: 1px;">
                        <%@ include file="/WEB-INF/jsp/main/left.jsp"%>
                    </div>
                    <!-- 中间-->
                    <div  region="center" style="overflow: hidden;border-top: none" >
                        <div  class="maintabs"  fit="true" border="false" >
                            <%--<div  id="tab-mainPage" title="首页"  href="main/home.do"	tools="#tab-mainPage_tools"></div>--%>
                        </div>
                    </div>
                </div>
            </div>

            <c:forEach var="m" items="${requestScope.menus }">
                <c:if test="${m.id!='0' }">
                    <div title="<img src='${m.menuIcon }'> ${m.menuName }" href="menu/mymenus/by.do?id=${m.id }"></div>
                </c:if>
            </c:forEach>

        </div>
    </div>

    <!-- 底部 -->
    <div region="south" border="false"  style="height: 30px; overflow: hidden;">
        <div id="div_footer" >
            Copyright &copy; 2019
        </div>
    </div>

    <script type="text/javascript">
        //获取一些基本信息
        <%--
        var msgWarnTime=<%=request.getAttribute("msgWarnTime") %>;
        loginUserId='<%=(String)request.getAttribute("userId") %>';
        loginName='<%=(String)request.getAttribute("trueName") %>';
        loginDeptId='<%=(String)request.getAttribute("deptId") %>';
        loginDeptName='<%=(String)request.getAttribute("deptName") %>';
        $(function(){
            var isLock=<%=session.getAttribute("lock") %>;
            if(isLock){
                //锁定界面
                MUI.openDialog('系统已锁定','sy_login/lock.do?rel=jm_look','jm_look',{width:500,height:200,collapsible:false,minimizable:false,maximizable:false,closable:false,modal:true,resizable:false,draggable:false});
            }
        });
        --%>

        var data = [
            {
                "id": 1,
                "parentId": 0,
                "url": "javascript:void(0)",
                "text": "系统设置",
                "checked": false,
                "state": "open",
                "children": [
                    {
                        "id": 2,
                        "parentId": 1,
                        "url": "sys/menu/index",
                        "text": "菜单管理",
                        "checked": false,
                        "state": null,
                        "children": []
                    },
                    {
                        "id": 3,
                        "parentId": 1,
                        "url": "sys/role/index",
                        "text": "角色管理",
                        "checked": false,
                        "state": null,
                        "children": []
                    },
                    {
                        "id": 4,
                        "parentId": 1,
                        "url": "sys/user/index",
                        "text": "用户管理",
                        "checked": false,
                        "state": null,
                        "children": []
                    }
                ]
            },
            {
                "id": 8,
                "parentId": 0,
                "url": "javascript:void(0)",
                "text": "基础设置",
                "checked": false,
                "state": "open",
                "children": []
            }
        ];


        $(function() {
            var basePath = $('#basePath').val();

            // 初始化
            $('#menuAccordion').accordion({
                fillSpace: true,
                fit: true,
                border: false,
                animate: false
            });

            //初始化左侧导航栏
            init(data);

          /*  $.post(basePath + 'sys/menu/tree', {type: 1}, function(data) {
                if(data) {
                    $.each(data, function(index, item) {
                        var selected = false;
                        if (index == 0) {
                            selected = true;
                        }
                        // Accordion 折叠面板
                        $('#menuAccordion').accordion('add', {
                            title: item.text,
                            content: "<ul id='menu_tree_" + item.id + "'></ul>",
                            selected: selected
                        });
                        // 树形菜单
                        $('#menu_tree_' + item.id).tree({
                            data: item.children,
                            onClick: function(node) {
                                if (node.children.length != 0) {
                                    return;
                                }
                                // 添加选项卡
                                addTab('tabs', node.text, node.url);
                            }
                        });
                    });
                }
            }, 'json');*/



        });


        function init(data) {
            $.each(data, function(index, item) {
                var selected = false;
                if (index == 0) {
                    selected = true;
                }
                // Accordion 折叠面板
                $('#menuAccordion').accordion('add', {
                    title: item.text,
                    content: "<ul id='menu_tree_" + item.id + "'></ul>",
                    selected: selected
                });
                // 树形菜单
                $('#menu_tree_' + item.id).tree({
                    data: item.children,
                    onClick: function(node) {
                        if (node.children.length != 0) {
                            return;
                        }
                        // 添加选项卡
                        addTab('tabs', node.text, node.url);
                    }
                });
            });
        }

        /**
         * 添加标签页面板
         * @param tabsId 标签页 ID
         * @param title 标签页面板的标题文字
         * @param url 加载远程内容来填充标签页面板的 URL
         */
        function addTab(tabsId, title, url) {

            var $tabs = $('#' + tabsId);
            console.log(tabsId);
            console.log(title);
            console.log(url);
            console.log($tabs);
            console.log($tabs.tabs('close', title));
            alert($tabs.tabs('exists', title));
            if($tabs.tabs('exists', title)) {
                $tabs.tabs('close', title);
            }
            $tabs.tabs('add', {
                title: title,
                href: url,
                closable: true
            })
        }

    </script>

    <%@ include file="/WEB-INF/jsp/main/commons-menu.jsp"%>

</div>
</body>
</html>