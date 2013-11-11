<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
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

<title>My JSP 'treetable01.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<link href="js/tt/jquery.treeTable.css" rel="stylesheet" type="text/css" />
<script src="js/tt/jquery.treeTable.min.js"></script>
<script type="text/javascript"  >
$(document).ready(function()  {
	  $("#your_table_id").treeTable({expandable:false});
	});
</script>
</head>

<body>
	<table id="your_table_id">
		<tr id="node-1">
			<td>Parent</td>
		</tr>
		<tr id="node-2" class="child-of-node-1">
			<td>Child</td>
		</tr>
		<tr id="node-3" class="child-of-node-2">
			<td>Child</td>
		</tr>
	</table>

</body>
</html>
