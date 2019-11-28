<!DOCTYPE html>
<html>
<head>
    <base href="http://127.0.0.1:8088/oa/">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>编码标识系统</title>
    <link href="favicon.ico" rel="SHORTCUT ICON"/>
    <script type="text/javascript">
        appWebRoot = 'http://127.0.0.1:8088/oa/';//网站地址
    </script>


    <link href="resource/js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">

    <link rel="stylesheet" href="resource/css/main.css" type="text/css"/>

    <link rel="stylesheet" href="resource/js/ui/css/ui.css" type="text/css"/>
    <link rel="stylesheet" href="resource/js/ui/css/icon.css" type="text/css"/>


    <script type="text/javascript" src="resource/js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="resource/js/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="resource/js/jquery/jquery-expand.js"></script>

    <link href="resource/js/easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" media="screen"/>
    <link id="easyuiTheme" href="resource/js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"
          media="screen"/>
    <script type="text/javascript" src="resource/js/easyui-1.3.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resource/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>


    <link rel="stylesheet" href="resource/js/easyui-portal/portal.css" type="text/css"/>
    <script type="text/javascript" src="resource/js/easyui-portal/jquery.portal.js"></script>


    <!-- js 对象拓展  -->
    <script src="resource/js/jsObject.Expand.js" type="text/javascript"></script>


    <link rel="stylesheet" href="resource/js/zTree/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <script src="resource/js/zTree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>


    <script src="resource/js/jquery.filestyle.js" type="text/javascript"></script>


    <script type="text/javascript" src="resource/js/ueditor/ueditor.config.js" charset="utf-8"></script>
    <script type="text/javascript" src="resource/js/ueditor/ueditor.all.min.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="resource/js/ueditor/themes/default/css/ueditor.css"/>


    <!-- My97DatePicke -->
    <script type="text/javascript" src="resource/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

    <!-- 流程表单插件 -->
    <script src="resource/js/flowForm_plugin.js" type="text/javascript"></script>

    <script src="resource/js/security/security.js"></script>

    <script src="resource/js/json/json2.js"></script>

    <script src="resource/js/ui/ui.queryFilter.js"></script>


    <script type="text/javascript" src="resource/js/ui/ui.core.js"></script>
    <script type="text/javascript" src="resource/js/ui/ui.navTab.js"></script>
    <script type="text/javascript" src="resource/js/ui/ui.taskBar.js"></script>
    <script type="text/javascript" src="resource/js/ui/ui.ajax.js"></script>
    <script type="text/javascript" src="resource/js/ui/ui.database.js"></script>
    <script type="text/javascript" src="resource/js/ui/plugins/zTree_plugin.js"></script>

    <script type="text/javascript" src="resource/js/ui/ui.util.js"></script>

    <script type="text/javascript" src="resource/js/ui/easyui.defaults.js"></script>
    <script type="text/javascript" src="resource/js/ui/easyui.validate.js"></script>
    <script type="text/javascript" src="resource/js/ui/ui.msg.js"></script>
    <script type="text/javascript" src="resource/js/util.js"></script>
    <script type="text/javascript" src="resource/js/init.js"></script>

    <link href='resource/js/fullcalendar/fullcalendar.css' rel='stylesheet'/>
    <link href='resource/js/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print'/>


    <script src="resource/js/fullcalendar/fullcalendar.js" type="text/javascript"></script>
    <script src="resource/js/fullcalendar/fullcalendar.util.js" type="text/javascript"></script>


</head>
<body>
<div id="sy_layout" class="easyui-layout" fit="true">

    <!-- 顶部-->
    <div region="north" border="false" style="height: 38px; padding: 1px; overflow: hidden;">
        <div style="float: left;">
            <img src="resource/images/logo/logo.png" style="margin-left: 7px;"/>
        </div>

        <div style="padding-top:2px;background-color: #F9F9F9;height: 100%">
            <div style="float: right;">
                <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="ui-icon-lock"
                   onclick="MUI.openDialog('系统已锁定','sy_login/lock.do?rel=jm_look','jm_look',{width:500,height:200,collapsible:false,minimizable:false,maximizable:false,closable:false,modal:true,resizable:false,draggable:false})"
                   title="锁定系统界面"></a>
                <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_gryhMenu"
                   iconCls="ui-icon-cog">当前用户：超级管理员</a>
            </div>


            <div id="layout_north_gryhMenu" style="width:150px;display: none;">

                <div iconCls="icon-back" onclick="goOutSystem()">安全退出</div>
                <div class="menu-sep"></div>
                <div>
                    <span>个人中心</span>
                    <div style="width:150px;">
                        <div onclick="MUI.openDialog('个人信息','user/my/info.do?rel=grbg_grse_myinfo','grbg_grse_myinfo',{width:1000,height:450})">
                            个人信息
                        </div>
                        <div onclick="MUI.openDialog('登陆日志','log/login/my.do?rel=grbg_grse_log','grbg_grse_log',{width:1000,height:450})">
                            登陆日志
                        </div>
                        <div onclick="MUI.openDialog('登陆管理','user/my/loginInfo.do?rel=main_login_info','main_login_info',{width:1000,height:450})">
                            登陆管理
                        </div>
                        <div onclick="MUI.openDialog('修改密码','user/updateMyPwPage.do?rel=grsz_xgmm','grsz_xgmm',{width:750,height:350})">
                            修改密码
                        </div>
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
                    <a href="#" target="_blank">在线帮助</a>
                </div>
            </div>


            <div id="div_top_2">

                <div style="width: 200px;"><span id="clock"></span></div>

            </div>
        </div>
    </div>

    <!-- 中间工作区-->
    <div id="mainPanle" region="center" style="overflow: hidden;padding:1px">

        <div class="easyui-tab" id="mainWorkTab" fit="true" border="false" tabHeight="35">
            <!-- 首页 -->
            <div title="<img src='resource/images/menu/1_close.png'/> 首页">

                <div id="sy_layout" class="easyui-layout" fit="true">
                    <!-- 左侧-->
                    <div region="west" split="true" title="导航菜单" style="width:230px; padding: 1px;">


                        <div id="nav" class="easyui-accordion " fit="true">

                            <div title="快捷菜单" iconCls="ui-icon-computer" tools="#left_kjmenu_tools">
                                <ul class="ul-menu" style="margin-left: 2px;margin-right: 2px;margin-top: 2px">

                                    <li>


                                        <a href="notice/load.do?rel=my_xzbg_ggtzgl" target="navTab" external="false"
                                           fresh="false" rel="my_xzbg_ggtzgl" title="通知管理">


                                            &nbsp;&nbsp;<img src="resource/images/menu/xtgg.png"/>&nbsp;&nbsp;通知管理</a>
                                    </li>

                                    <li>


                                        <a href="imageSpace/load.do?rel=my_wdgl_tkkj" target="navTab" external="false"
                                           fresh="false" rel="my_wdgl_tkkj" title="图片空间">


                                            &nbsp;&nbsp;<img src="resource/images/menu/gxwd.png"/>&nbsp;&nbsp;图片空间</a>
                                    </li>

                                    <li>


                                        <a href="shareFiles/load.do?rel=my_dwgl_gxwd&wdSuperId=0" target="navTab"
                                           external="false" fresh="false" rel="my_dwgl_gxwd" title="共享文档">


                                            &nbsp;&nbsp;<img src="resource/images/menu/sjx.png"/>&nbsp;&nbsp;共享文档</a>
                                    </li>

                                    <li>


                                        <a href="msgwarn/load.do?rel=my_grbg_xxtx" target="navTab" external="false"
                                           fresh="false" rel="my_grbg_xxtx" title="消息提醒">


                                            &nbsp;&nbsp;<img src="resource/images/menu/reply.gif"/>&nbsp;&nbsp;消息提醒</a>
                                    </li>

                                    <li>


                                        <a href="vote/pervote.do?rel=my_ggpt_grtp" target="navTab" external="false"
                                           fresh="false" rel="my_ggpt_grtp" title="投票">


                                            &nbsp;&nbsp;<img src="resource/images/menu/8.png"/>&nbsp;&nbsp;投票</a>
                                    </li>

                                    <li>

                                        <a href="schedule/load.do?rel=my_grbg_rcap_box" target="navTab" external="false"
                                           fresh="false" rel="my_grbg_rcap_box" title="日程安排">
                                            &nbsp;&nbsp;<img src="resource/images/menu/mail_reply.png"/>&nbsp;&nbsp;日程安排</a>
                                    </li>

                                </ul>
                            </div>

                            <div title="在线人员" iconCls="ui-icon-group" tools="#left_online_tools" id="left_online_div">
                                <ul id="left_online_tree" class="ztree"></ul>
                            </div>
                            <div title="全部人员" iconCls="ui-icon-group" tools="#left_allUsers_tools"
                                 id="left_allUsers_div">
                                <ul id="left_allUsers_tree" class="ztree"></ul>
                            </div>

                        </div>

                        <div id="left_kjmenu_tools" style="display: none;">
                            <a href="menu/my/load.do?rel=mykjmenu" class="ui-icon-cog" title="快捷菜单管理" target="dialog"
                               width="800" height="400" style="margin-left: 5px;"></a>
                        </div>

                        <div id="left_menu_tools" style="display: none;">
                            <a href="javascript:;" class="icon-reload" title="刷新" onclick="queryLeftMenuTree()"></a>
                            <a href="javascript:;" class="icon-add" title="合并"
                               onclick="zTreeExpandAllNodes('left_menu_tree',false)" style="margin-left: 5px;"></a>
                            <a href="javascript:;" class="icon-remove" title="展开"
                               onclick="zTreeExpandAllNodes('left_menu_tree',true)" style="margin-left: 5px;"></a>
                        </div>

                        <div id="left_online_tools" style="display: none;">
                            <a href="javascript:;" class="icon-reload" title="刷新" onclick="queryLeftOnlineTree()"></a>
                            <a href="javascript:;" class="icon-add" title="合并"
                               onclick="zTreeExpandAllNodes('left_online_tree',false)" style="margin-left: 5px;"></a>
                            <a href="javascript:;" class="icon-remove" title="展开"
                               onclick="zTreeExpandAllNodes('left_online_tree',true)" style="margin-left: 5px;"></a>
                        </div>

                        <div id="left_allUsers_tools" style="display: none;">
                            <a href="javascript:;" class="icon-reload" title="刷新" onclick="queryLeftAllUsersTree()"></a>
                            <a href="javascript:;" class="icon-add" title="合并"
                               onclick="zTreeExpandAllNodes('left_allUsers_tree',false)" style="margin-left: 5px;"></a>
                            <a href="javascript:;" class="icon-remove" title="展开"
                               onclick="zTreeExpandAllNodes('left_allUsers_tree',true)" style="margin-left: 5px;"></a>
                        </div>

                    </div>
                    <!-- 中间-->
                    <div region="center" style="overflow: hidden;border-top: none">
                        <div class="maintabs" fit="true" border="false">
                            <div id="tab-mainPage" title="首页" href="main/home.do" tools="#tab-mainPage_tools"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div title="<img src='resource/images/menu/grtxl.png'> 个人办公" href="menu/mymenus/by.do?id=402881f73ea22a57013ea23438ea000e"></div>
            <div title="<img src='resource/images/menu/building_link.png'> 设备台账" href="menu/mymenus/by.do?id=402880e86e86c964016e86cdd4310001"></div>
            <div title="<img src='resource/images/menu/ggpt.png'> 公共平台" href="menu/mymenus/by.do?id=402881f73e86b4e6013e86c9d4960003"></div>
            <div title="<img src='resource/images/menu/24.png'> 协同办公" href="menu/mymenus/by.do?id=402881f73e39eba8013e39fbbf220003"></div>
            <div title="<img src='resource/images/menu/fwqxx.png'> CRM系统" href="menu/mymenus/by.do?id=40283a8142b1308e0142b13599740001"></div>
            <div title="<img src='resource/images/menu/32.png'> 行政办公" href="menu/mymenus/by.do?id=402881e63f0e7630013f0e79cd8c0002"></div>
            <div title="<img src='resource/images/menu/gzlc.png'> 工作流程" href="menu/mymenus/by.do?id=402881f73eb50cf5013eb51308d7000b"></div>
            <div title="<img src='resource/images/menu/gstxl.png'> 项目立项" href="menu/mymenus/by.do?id=40288ad14dcba70f014dcbd8290c0001"></div>
            <div title="<img src='resource/images/menu/gstxl.png'> 人事管理" href="menu/mymenus/by.do?id=402881823f1c52d4013f1c56f8780002"></div>
            <div title="<img src='resource/images/menu/wdgl.gif'> 文档管理" href="menu/mymenus/by.do?id=402881f73e39afbd013e39b7e93a0005"></div>
            <div title="<img src='resource/images/menu/1.png'> 系统管理" href="menu/mymenus/by.do?id=402881f73e39afbd013e39b48d2e0003"></div>

        </div>
    </div>

    <!-- 底部 -->
    <div region="south" border="false" style="height: 30px; overflow: hidden;">
        <div id="div_footer">
            Copyright &copy; 2019
        </div>
    </div>

    <script type="text/javascript">
        //获取一些基本信息
        var msgWarnTime = 5;
        loginUserId = '402881f73e1c4ba4013e1c4c08470001';
        loginName = '超级管理员';
        loginDeptId = '402881f73e1c5181013e1c56da3c0002';
        loginDeptName = '公司';
        $(function () {
            var isLock = null;
            if (isLock) {
                //锁定界面
                MUI.openDialog('系统已锁定', 'sy_login/lock.do?rel=jm_look', 'jm_look', {
                    width: 500,
                    height: 200,
                    collapsible: false,
                    minimizable: false,
                    maximizable: false,
                    closable: false,
                    modal: true,
                    resizable: false,
                    draggable: false
                });
            }
        });
    </script>


    <div id="tab-mainPage_tools">
        <a href="javascript:void(0)" class="icon-mini-refresh" title="初始化顺序" onclick="resetMainPortalState()"></a>
    </div>


    <div id="maintabs_menu" class="easyui-menu" style="width:150px;display: none">
        <div id="maintabs-update" iconCls="icon-reload">刷新</div>
        <div class="menu-sep"></div>
        <div id="maintabs-close" iconCls="icon-cancel">关闭</div>
        <div id="maintabs-closeall">关闭全部</div>
        <div id="maintabs-closeother">关闭其他</div>
        <div class="menu-sep"></div>
        <div id="maintabs-closeright">关闭右侧</div>
        <div id="maintabs-closeleft">关闭左侧</div>

    </div>


    <div id="taskbar" style="left:0px; display:none;">
        <div class="taskbarContent">
            <ul></ul>
        </div>
        <div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
        <div class="taskbarRight" style="display:none;">taskbarRight</div>
    </div>


    <div id="taskbar_menu" class="easyui-menu" style="width:150px;display: none">
        <div id="taskbar_menu-update" iconCls="icon-reload">刷新</div>
        <div class="menu-sep"></div>
        <div id="taskbar_menu-close" iconCls="icon-cancel">关闭</div>
        <div id="taskbar_menu-closeall">关闭全部</div>
        <div id="taskbar_menu-closeother">关闭其他</div>
        <div class="menu-sep"></div>
        <div id="taskbar_menu-closeright">关闭右侧</div>
        <div id="taskbar_menu-closeleft">关闭左侧</div>

    </div>


</div>
</body>
</html>