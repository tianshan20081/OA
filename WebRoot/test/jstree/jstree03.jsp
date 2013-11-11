<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'jstree01.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="js/jquery.jstree.js"></script>
<script language="javascript">
	$(function() {
		//将menuContainer变成一棵树！
		$("#demo").jstree({
			"json_data" : {
				"ajax" : {
					"url" : "system/party!tree.action"
				}
			},
			"plugins" : [ "themes", "json_data", "ui" ]
		});

		$("#demo").bind("loaded.jstree", function(event) {
			$("#demo").jstree("open_all", -1);
		});
		$("#demo").bind("select_node.jstree", function(event, data) {
			//获取 li 标记上的属性
			
	//		alert(data.rslt.obj.attr("id"));
			// 獲取 A 標記上的属性
			alert(data.rslt.obj.context.href);
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
	<div id="demo"></div>
</body>
</html>
