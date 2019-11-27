
//页面加载完成之后执行  
$(function() {
	
	if($.browser.msie<8){
		Msg.alert('提示','抱歉，您使用浏览器版本太低，本系统不支持IE8(内核)以下浏览器。','error');
	}
	queryLeftMenuTree();//左侧菜单
	//定时查询在线人员信息
    //queryLeftOnlineTree();
	//设置定时查询
	//intervalProcess=setInterval("queryLeftOnlineTree();",msgWarnTime*60000);
	new Clock().display(document.getElementById("clock"));//设置时钟
	//queryLeftAllUsersTree();//左侧全部人员查询
	
});

//查询左侧操作菜单
function queryLeftMenuTree(){
	var $menu=$("#left_menu_tree");
	if($menu.length!=1)return ;
	$.ajax({
		url:"menu/mymenus.do",
		cache: false,
		dataType:"json",
		success:function(json){
			MUI.ajaxDone(json);
			if(json.statusCode != MUI.statusCode.ok)return false;	
			var setting = {
					view: {
						dblClickExpand: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						onClick: zTreeClick
					}
			};
			var zNodes = new Array();
			$.each(json.menus, function(i, m) {
				zNodes.push({id : m.id,name : m.name,
					pId : m.pid,icon:m.icon,
					href:m.url,dwzTarget:m.target,
					rel:m.rel,external:m.external,fresh:m.fresh,
					open:m.open,clickOpen:true});
				
			});

			$.fn.zTree.init($menu, setting, zNodes);
			//一些基本用户信息
			loginUserId=json.userId;
			loginName=json.userName;
			loginDeptId=json.deptId;
			loginDeptName=json.deptName;
		}
	});
		
}
//查询在线人员列表
function queryLeftOnlineTree(){
	$.ajax({
		url:"main/online.do",
		cache: false,
		dataType:"json",
		success:function(data){
			MUI.ajaxDone(data);
			if(data.statusCode != MUI.statusCode.ok)return false;	
			$("#left_online_div").panel("setTitle","在线人员&nbsp;&nbsp;[<span style='color:red'>"+data.onlineNum+"</span>]");
			var onlineSetting = {
					view: {
						dblClickExpand: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						onClick: zTreeClick
					}
			};
			var onlineZNodes = new Array();
			
			$.each(data.depts, function(i, m) {
				onlineZNodes.push({id : m.id,name : m.deptName,pId : m.superId,
					iconSkin:"mypoint",clickOpen:true,open:false});
			});
			
			$.each(data.users, function(i, m) {
				
				if(m.sex==0){
					onlineZNodes.push({id : m.id,name : m.trueName,pId : m.deptId,
						icon:"resource/images/user/U11.png",href:"instantMessage/addPage.do?userId="+m.id+"&rel=xttx_jsgt_add",dwzTarget:"dialog",
						rel:"xttx_jsgt_add",dialogParam:{width:1000,height:450}
					});
				}else{
					onlineZNodes.push({id : m.id,name : m.trueName,pId : m.deptId,
						icon:"resource/images/user/U01.png",href:"instantMessage/addPage.do?userId="+m.id+"&rel=xttx_jsgt_add",dwzTarget:"dialog",
						rel:"xttx_jsgt_add",dialogParam:{width:1000,height:450}
					});
				}
				
			});
			var zTobj=$.fn.zTree.init($("#left_online_tree"), onlineSetting, onlineZNodes);
			//获取所有部门节点
			var rootNodes = zTobj.getNodes();
			var removeNodes=[];//需要删除的节点
			addRemoveDeptNode(rootNodes);//递归添加需要删除的节点
			for (var i = 0,k=removeNodes.length; i <k ; i++) {//判断删除节点
				zTobj.removeNode(removeNodes[i]);
			}
			zTobj.expandNode(rootNodes[0], true, false, true);
			//判断节点下是否有用户，如果部门下没有用户则放在待删除的集合里面
			function addRemoveDeptNode(nodes){
				var hasUser=false;
				for (var i = 0,k=nodes.length; i <k ; i++) {
					var node=nodes[i];
					if(node["clickOpen"]){
						var cNodes = node.children;
						if(cNodes&&cNodes.length>0){
							
							if(addRemoveDeptNode(cNodes)){
								hasUser=true;
							}else{
								removeNodes.push(node);
							}
						
						}else{
							removeNodes.push(node);
						}
					}else{
						hasUser=true;
					}
					
				}
				return hasUser;
			}
			
		}
	});
	
}
//查询全部人员列表
function queryLeftAllUsersTree(){
	$.ajax({
		url:"user/allUsers.do",
		cache: false,
		dataType:"json",
		success:function(data){
			MUI.ajaxDone(data);
			if(data.statusCode != MUI.statusCode.ok)return false;
			$("#left_allUsers_div").panel("setTitle","全部人员&nbsp;&nbsp;[<span style='color:red'>"+data.users.length+"</span>]");
			var onlineSetting = {
					view: {
						dblClickExpand: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						onClick: zTreeClick
					}
			};
			var onlineZNodes = new Array();
			
			$.each(data.depts, function(i, m) {
				onlineZNodes.push({id : m.id,name : m.deptName,pId : m.superId,
					iconSkin:"mypoint",clickOpen:true,open:false});
			});
			
			$.each(data.users, function(i, m) {
				if(m.sex==0){
					onlineZNodes.push({id : m.id,name : m.trueName,pId : m.deptId,
						icon:"resource/images/user/U11.png",href:"instantMessage/addPage.do?userId="+m.id+"&rel=xttx_jsgt_add",dwzTarget:"dialog",
						rel:"xttx_jsgt_add",dialogParam:{width:1000,height:450}
					});
				}else{
					onlineZNodes.push({id : m.id,name : m.trueName,pId : m.deptId,
						icon:"resource/images/user/U01.png",href:"instantMessage/addPage.do?userId="+m.id+"&rel=xttx_jsgt_add",dwzTarget:"dialog",
						rel:"xttx_jsgt_add",dialogParam:{width:1000,height:450}
					});
				}
				
			});
			var zTobj=$.fn.zTree.init($("#left_allUsers_tree"), onlineSetting, onlineZNodes);
			var nodes = zTobj.getNodes();
			zTobj.expandNode(nodes[0], true, false, true);
			
		}
	});
	
}


//退出系统
function goOutSystem(){
	
	$.messager.confirm('提示',"确定退出系统吗?", function(r){
           if (r){
				window.location.href="sy_login/out.do";
		   }
     });
	
}

