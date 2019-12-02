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
                                    <li>
                                        <a style="color: black;">部门管理<img src=""></a>
                                    </li>
                                    <li>
                                        <a style="color: black;">用户管理<img src=""></a>
                                    </li>
                                    <li>
                                        <a style="color: black;">角色管理<img src=""></a>
                                    </li>

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
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- 中间-->
                    <div  region="center" style="overflow: hidden;border-top: none" >
                        <div  class="maintabs"  fit="true" border="false" >
                            <%--<div  id="tab-mainPage" title="首页"  href="main/home.do"	tools="#tab-mainPage_tools"></div>--%>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 顶部导航栏 -->
            <div   title="<img src='resource/images/menu/1.png'/> 系统管理"  >
                <%@ include file="/WEB-INF/jsp/main/middle.jsp"%>
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

    </script>

    <%@ include file="/WEB-INF/jsp/main/commons-menu.jsp"%>

</div>
</body>
</html>