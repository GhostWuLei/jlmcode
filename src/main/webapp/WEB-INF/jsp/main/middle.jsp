<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	中间区域
--%>

	<%--菜单--%>
<%--	<ul class="ul-menu">
	  <li><a href="javascript:alert('xxx');"></a></li>
	  <li><a href="javascript:;">菜单二</a></li>		
	  <li>
	  		<a href="javascript:;">二级菜单</a>
	  		<ul class="ul-submenu">
	  			<li >
	  				<a href="javascript:;">子菜单</a>
	  			</li>
	  			<li >
	  				<a href="javascript:;">三级菜单</a> 
	  				<ul class="ul-submenu">
	  					<li ><a>xx</a></li>
	  				</ul>
	  			</li>
	  		</ul>
	  </li>
    </ul>--%>

<div  class="easyui-layout" fit="true" border="false">
	<!-- 左侧-->
	<div region="west" split="true" title="系统管理" style="width:220px; padding: 1px; ">

		<%--<ul class="ul-menu">
             ${requestScope.menus }
        </ul>--%>
		<ul class="ul-menu">
			<li>
				<a href="javascript:void(0);">组织机构</a>
				<ul class="ul-submenu">
					<li>
						<a href="dept/load.do?rel=stgl_jg_bmgl" target="navTab" title="部门管理">部门管理</a>
					</li>
					<li>
						<a href="user/load.do?rel=stgl_jg_yhgl" target="navTab" title="用户管理">用户管理</a>
					</li>
					<li>
						<a href="role/load.do?rel=stgl_jg_jsgl" target="navTab" title="角色管理">角色管理</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="javascript:void(0);">系统日志</a>
				<ul class="ul-submenu">
					<li>
						<a href="javascript:alert('登录日志');">登录日志</a>
					</li>
					<li>
						<a href="javascript:alert('操作日志');">操作日志</a>
					</li>
					<li>
						<a href="javascript:alert('登录日志设置');">登录日志设置</a>
					</li>
				</ul>
			</li>
			<li><a href="javascript:alert('字典管理');">字典管理</a></li>
			<li><a href="javascript:alert('菜单管理');">菜单管理</a></li>
			<li><a href="javascript:alert('用户导入');">用户导入</a></li>
			<li><a href="javascript:alert('地区管理');">地区管理</a></li>
			<li><a href="javascript:alert('打印导出自定义管理');">打印导出自定义管理</a></li>
			<li><a href="javascript:alert('在线管理');">在线管理</a></li>
			<li><a href="javascript:alert('服务器信息');">服务器信息</a></li>
			<li><a href="javascript:alert('系统设置');">系统设置</a></li>
			<li><a href="javascript:alert('数据库管理');">数据库管理</a></li>
			<li><a href="javascript:alert('数据权限管理');">数据权限管理</a></li>
		</ul>

	</div>


		<!-- 中间-->
		<div  region="center" style="overflow: hidden;border-top: none" >
			<div  class="maintabs"  fit="true" border="false" >
			</div>
		</div>

</div>

