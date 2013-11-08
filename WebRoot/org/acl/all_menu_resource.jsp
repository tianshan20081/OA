<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>所有菜单主界面</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.jstree.js"></script>
<script type="text/javascript" src="js/jquery.jstree.aclcheckbox.js"></script>
<script language="javascript">
	$(function()
	{
		//点击某个节点的菜单， 允许
		function permit(node)
		{
			this.permit_node(node);
		}
		//点击某个节点的菜单， 拒绝
		function deny(node)
		{
			this.deny_node(node);
		}
		//点击某个节点的菜单， 继承
		function extend(node)
		{
			this.extends_node(node);
		}
		//点击某个节点的菜单， 取消
		function cancel(node)
		{
			this.cancel_node(node);
		}
		//点击某个节点的菜单， 允许所有
		function permitAll(node)
		{
			this.permit_all(node);
		}
		//点击某个节点的菜单， 拒绝所有
		function denyAll(node)
		{
			this.deny_all(node);
		}
		//点击某个节点的菜单， 取消所有
		function cancelAll(node)
		{
			this.cancel_all(node);
		}
		//点击某个节点的菜单， 继承所有
		function extendAll(node)
		{
			this.extends_all(node);
		}

		var contextmenu_items = function()
		{
			return {
				"permit" : {
					"label" : "允许",
					"action" : permit,
					"separator_after":true
				},
				"deny" : {
					"label" : "拒绝",
					"action" : deny,
					"separator_after":true
				},
				"cancel" : {
					"label" : "取消",
					"action" : cancel,
					"separator_after":true
				},
				"extend" : {
					"label" : "继承",
					"action" : extend,
					"separator_after":true
				},
				"permitAll" : {
					"label" : "全部许可",
					"action" : permitAll,
					"separator_after":true
				},
				"denyAll" : {
					"label" : "全部拒绝",
					"action" : denyAll,
					"separator_after":true
				},
				"cancelAll" : {
					"label" : "全部取消",
					"action" : cancelAll,
					"separator_after":true
				},
				"extendAll" : {
					"label" : "全部继承",
					"action" : extendAll,
					"separator_after":true
				}
			};
		}
		//初始化授权表格
		var maxMenus = <s:property value="#topMenuIds.size()"/>;
		var loadedMenu = 0 ;
		function initTable()
		{
			loadedMenu ++ ;
			if(loadedMenu >= maxMenus){
				$.getJSON("system/acl!findMenuAcls?principalType=${principalType}&principalId=${principalId}", function(data)
						{
							for(var i=0;i<data.length;i++){
								var authVo = data[i] ;
								var resourceId = authVo.resourceId ;
								var permit = authVo.permit ;
								var extend = authVo.extend ;
								
								var node = $("#"+resourceId);//菜單資源的 li 標籤
								if(permit){
									node.removeClass("jstree-deny jstree-normal jstree-extend").addClass("jstree-permit");
								}else{
									node.removeClass("jstree-permit jstree-normal jstree-extend").addClass("jstree-deny");
								}
								
								if(extend){//继承状态
									node.addClass("jstree-extend");
								}
								
							}
						});
			}
			

		}
		<s:iterator value="#topMenuIds">
		//将roleContainer变成一棵树！
		$("#menuTree_<s:property />").jstree({
			"json_data" : {
				"ajax" : {
					"url" : "system/acl!allMenuResourceTree.action?topMenuId=<s:property/>"
				}
			},
			"themes" : {
				"theme" : "classic"
			},
			"contextmenu" : {
				"items" : contextmenu_items
			},
			"plugins" : [ "themes", "json_data", "ui", "aclcheckbox", "contextmenu" ]
		});
		$("#menuTree_<s:property />").bind("loaded.jstree", function(event)
		{
			$("#menuTree_<s:property />").jstree("open_all", -1);
			//初始化表格
			initTable();
		});
		$("#menuTree_<s:property />").bind("select_node.jstree", function(event, data)
		{
			var principalId = data.rslt.obj.attr("id");
			var principalType = data.rslt.obj.attr("principalType");
			$("#rigthFrame").attr("src", "system/acl!allMenuResource.action?principalId=" + principalId + "&principalType=" + principalType);
		});
		</s:iterator>
		$("#menuTree_<s:property />").css("font-size", "12px")
	});
	//给菜单授权
	function auth()
	{
		//获得所有树被授权的节点
		var nodes = getAllCheckedNodes();
		//拼装需要传输到后台的参数
		var param = "principalType=${principalType}&principalId=${principalId}";
		for ( var i = 0; i < nodes.length; i++) {
			var resourceId = nodes[i].attr("id");
			var operIndex = 0;
			var permit;
			var extend;
			if (nodes[i].hasClass("jstree-permit")) {
				permit = true;//许可
			} else if (nodes[i].hasClass("jstree-deny")) {
				permit = false;//拒绝
			}
			if (nodes[i].hasClass("jstree-extend")) {
				extend = true;
			} else {
				extend = false;
			}

			param += "&authVos[" + i + "].resourceId=" + resourceId;
			param += "&authVos[" + i + "].operIndex=" + operIndex;
			param += "&authVos[" + i + "].permit=" + permit;
			param += "&authVos[" + i + "].extend=" + extend;
		}
		// var url = "system/acl!XXX.action"+param  为 get 方法 大小限制位 1 K
		var url = "system/acl!authMenu.action";
		//	alert(param);
		$.post(url, param, function()
		{
			alert("已经授权完毕！");//回调函数
		});
		//alert(nodes.length);
	}
	function getAllCheckedNodes()
	{
		var nodes = new Array();
		<s:iterator value="#topMenuIds">
		var allChecked<s:property/> = $("#menuTree_<s:property />").jstree("get_all_auths_node");
		for ( var i = 0; i < allChecked<s:property/>.length; i++) {
			nodes.push($(allChecked<s:property />[i]));
		}
		</s:iterator>
		return nodes;
	}
function permitAll(){
	<s:iterator value="#topMenuIds">
	$("#menuTree_<s:property />").jstree("permit_all");
	</s:iterator>
}
function denyAll(){
	<s:iterator value="#topMenuIds">
	$("#menuTree_<s:property />").jstree("deny_all");
	</s:iterator>
}
function cancelAll(){
	<s:iterator value="#topMenuIds">
	$("#menuTree_<s:property />").jstree("cancel_all");
	</s:iterator>
}
function extendsAll(){
	<s:iterator value="#topMenuIds">
	$("#menuTree_<s:property />").jstree("extends_all");
	</s:iterator>
}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
}
-->
</style>
</head>
<body>
	<table width="100%" height="100%" border="0" cellpadding="0">
		<tr>
<td colspan='<s:property value="#topMenuIds.size()"/>'>
<a href="system/acl!allMenuResource.action?principalId=$(princaipalId)&principalType=$(principalType)">菜单授权</a>
 <a href="system/acl!allMenuResource.action?principalId=$(princaipalId)&principalType=$(principalType)">资源授权</a> 
 |
 <a href="javascript:auth();">保存授权</a> 
 <a href="javascript:permitAll();">全部允许</a>
  <a href="javascript:denyAll();">全部拒绝</a> 
  <a href="javascript:cancelAll();">全部取消</a>
   <a href="javascript:extendsAll();">全部继承</a>

				<hr />
			</td>
		</tr>
		<tr>
			<s:iterator value="#topMenuIds">
				<td width="140" valign="top">
					<div id="menuTree_<s:property/>"></div>
				</td>
			</s:iterator>
		</tr>
	</table>
</body>
</html>

