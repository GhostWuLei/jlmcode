<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--组织机构--用户管理
--%>

<div class="easyui-layout" fit="true" >
	<!-- 左侧-->
	<div region="west" title="部门列表" split="true" 	style="width:250px;" >
		<ul id="${param.rel }_leftTree" class="ztree"></ul>
	</div>
	<%--中间--%>
	<div  region="center" border="false">
		<%@ include file="/WEB-INF/jsp/system/organize/user/query.jsp"%>
	</div>

</div>

<script type="text/javascript">
    $(function(){
        $.ajax({
            url:"dept/load/all.do",
            cache: false,
            dataType:"json",
            success:function(json){
                var setting = {
                    view: {
                        showIcon: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onClick: updateUserDeptId
                    }
                };
                var rel='<%= request.getParameter("rel") %>_datagrid';
                var zNodes = new Array();
                zNodes.push({id : 0,name : "全部部门",open:true,datagrid : rel,param : {"deptId":""},updateTitle:"用户查询--全部部门"});
                $.each(json, function(i, d) {

                    zNodes.push({
						id : d.id,
						name : d.deptName,
						pId : d.superId,
						open:true,
                        datagrid : rel,
						param : {"deptId":d.id},
						updateTitle:"用户查询--"+d.deptName});

                });
                $.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);

            }
        });

    });

    function updateUserDeptId(event,treeId, treeNodeJSON){
        alert("XXXXX");
        $('#<%= request.getParameter("rel") %>_deptId').val(treeNodeJSON.id);
        refreshDatagrid(event,treeId, treeNodeJSON);
    }

    <%--


    function refreshDatagrid(event,treeId, treeNodeJSON){
	//点击打开
	if(treeNodeJSON.clickOpen){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNodeJSON);//展开或折叠指定的节点
	}
	if(!treeNodeJSON.datagrid)return ;
	var $dg=$("#"+treeNodeJSON.datagrid);
	var $form=$dg.closest(".datagrid").find($dg.attr("toolbar")+">form");
	var o = {};
	$.each($form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	var queryParams = $dg.datagrid('options').queryParams;//获取原查询条件
	$.extend(queryParams,o,treeNodeJSON.param);//将现在的查询条件合并到原条件中
	$dg.datagrid('load', queryParams);
	if(treeNodeJSON.updateTitle){//修改表格title
		$dg.datagrid('getPanel').panel("setTitle",treeNodeJSON.updateTitle);
	}
}



    --%>

</script>