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
<link href="js/tt/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
<link href="js/tt/stylesheets/master.css" rel="stylesheet" type="text/css" />
<script src="js/tt/javascripts/jquery.treeTable.min.js"></script>
<script type="text/javascript">
	$(function()
	{
		$(".treeTable").treeTable({
			initialState : "expanded"
		});
		$(".treeTable").css("font-size", "14px");

		$("a.oper,ins").toggle(function()
		{//允许
			if ($(this).is("ins")) {
				//$(this).parent().removeClass("jstree-extend");
				$(this).removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-permit");
			} else {
				//$(this).removeClass("jstree-extend");
				$(this).children[0].removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-permit");
			}
		}, function()
		{//拒绝
			if ($(this).is("ins")) {
				//$(this).parent().removeClass("jstree-extend");
				$(this).removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-deny");
			} else {
				//$(this).removeClass("jstree-extend");
				$(this).children[0].removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-deny");
			}
		}, function()
		{//取消
			if ($(this).is("ins")) {
				$(this).parent().removeClass("jstree-extend");
				$(this).removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-normal");
			} else {
				$(this).removeClass("jstree-extend");
				$(this).children[0].removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-normal");
			}
		}, function()
		{//继承
			if ($(this).is("ins")) {
				$(this).parent().addClass("jstree-extend");
				//$(this).removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-permit");
			} else {
				$(this).addClass("jstree-extend");
				//$(this).children[0].removeClass("jstree-noraml jstree-permit jstree-deny").addClass("jstree-permit");
			}
		});

	});
	//给菜单授权
	function auth()
	{
		//首先要获得所有树表当中被授权的节点
		//查找应用了permit,deny,normal样式ins标签
		//或者查找应用extend样式的a标签下的ins标签
		var nodes = $("ins.jstree-permit,ins.jstree-deny,a.jstree-extend ins");
		//拼装需要传输到后台的参数
		var param = "principalType=${principalType}&principalId=${principalId}";
		for ( var i = 0; i < nodes.length; i++) {
			var node = $(nodes[i]);
			var resourceId = node.attr("resourceId");
			var operIndex = node.attr("operIndex");
			var permit;
			var extend;

			if (node.hasClass("jstree-permit")) {
				permit = true;//许可
			} else if (node.hasClass("jstree-deny")) {
				permit = false;//拒绝
			}

			if (node.parent().hasClass("jstree-extend")) {
				extend = true;
			} else {
				extend = false;
			}

			param += "&authVos[" + i + "].resourceId=" + resourceId;
			param += "&authVos[" + i + "].operIndex=" + operIndex;
			param += "&authVos[" + i + "].permit=" + permit;
			param += "&authVos[" + i + "].extend=" + extend;
		}
		//	alert(param);
		// var url = "system/acl!XXX.action"+param  为 get 方法 大小限制位 1 K
		var url = "system/acl!authActionResource.action";
		$.post(url, param, function()
		{
			alert("已经授权完毕！");//回调函数
		});
		//alert(nodes.length);
	}
	function permitAll()
	{
		$("a.oper").removeClass("jstree-extend");
		$("ins").removeClass("jstree-normal jstree-deny jstree-permit").addClass("jstree-permit");
	}
	function denyAll()
	{
		$("a.oper").removeClass("jstree-extend");
		$("ins").removeClass("jstree-normal jstree-permit jstree-deny").addClass("jstree-deny");
	}
	function cancelAll()
	{
		$("a.oper").removeClass("jstree-extend");
		$("ins").removeClass("jstree-extend jstree-deny jstree-permit").addClass("jstree-normal");
	}
	function extendsAll()
	{
		$("a.oper").addClass("jstree-extend");
	}
	//初始化 授权表
	$(function()
	{
		$.getJSON("system/acl!findAllActionResourceAcls.action?principalType=${principalType}&principalId=${principalId}", function(data)
		{
			for ( var i = 0; i < data.length; i++) {
				var authVo = data[i];
				var resourceId = authVo.resourceId;
				var operIndex = authVo.operIndex;
				var permit = authVo.permit;
				var extend = authVo.extend;
				//操作资源的ins标签
				alert(permit);
				var node = $("ins[resourceId=" + resourceId + "][operIndex=" + operIndex + "]");
				if (permit) {//允许
					node.removeClass("jstree-deny jstree-normal jstree-extend").addClass("jstree-permit");
				} else {//拒绝
					node.removeClass("jstree-permit jstree-normal jstree-extend").addClass("jstree-deny");
				}

				if (ext) {//继承
					node.parent().addClass("jstree-extend");
				}

			}
		});
	});
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
			<td colspan='<s:property value="#topMenuIds.size()"/>'><a href="system/acl!allMenuResource.action?principalId=${principalId}&principalType=${principalType}">菜单授权</a> <a href="system/acl!allActionResource.action?principalId=${principalId}&principalType=${principalType }">资源授权</a> | <a href="javascript:auth();">保存授权</a> <a href="javascript:permitAll();">全部允许</a> <a href="javascript:denyAll();">全部拒绝</a> <a href="javascript:cancelAll();">全部取消</a> <a href="javascript:extendsAll();">全部继承</a>
				<hr />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table class="treeTable">
					<thead>
						<tr>
							<th>操作资源名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#ress" var="res">
							<tr id="res-<s:property value="#res.id"/>" class="<s:if test='#res.parent != null'>child-of-res-<s:property value='#res.parent.id'/></s:if>">
								<td><span class='<s:if test="#res.children.size() == 0">file</s:if><s:else>folder</s:else>'><s:property value="#res.name" /> </span></td>
								<td><s:iterator value="opers">
										<a class="oper"> <ins class="jstree-normal" resourceId="<s:property value="#res.id"/>" operIndex="<s:property value="value.operIndex"/>"> &nbsp;&nbsp;&nbsp;&nbsp;</ins> <s:property value="value.operName" /> </a>
									</s:iterator>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					</td>
					</tr>
				</table>
</body>
</html>

