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
<link href="js/tt/stylesheets/jquery.treeTable.css" rel="stylesheet" type="text/css" />
<link href="js/tt/stylesheets/master.css" rel="stylesheet" type="text/css" />
<script src="js/tt/javascripts/jquery.treeTable.min.js"></script>
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#example3").treeTable();
	});
	$(document).ready(function()
	{
		$("#example").treeTable();
	});
</script>
</head>

<body>
	<table id="example3">
  <caption>Example 2: A tree that is not collapsable.</caption>
  <thead>
    <tr>
      <th>Tree column</th>
      <th>Column 2</th>
    </tr>
  </thead>
  <tbody>
    <tr id="ex3-node-1">
      <td>Node 1</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-1-1" class="child-of-ex3-node-1">
      <td>Node 1.1</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-1-2" class="child-of-ex3-node-1">
      <td>Node 1.2</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-1-3" class="child-of-ex3-node-1">
      <td>Node 1.3</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-2">
      <td>Node 2</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-2-1" class="child-of-ex3-node-2">
      <td>Node 2.1</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-2-1-1" class="child-of-ex3-node-2-1">
      <td>Node 2.1.1</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-2-2" class="child-of-ex3-node-2">
      <td>Node 2.2</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-2-2-1" class="child-of-ex3-node-2-2">
      <td>Node 2.2.1</td>
      <td>Second column</td>
    </tr>
    <tr id="ex3-node-2-2-2" class="child-of-ex3-node-2-2">
      <td>Node 2.2.2</td>
      <td>Second column</td>
    </tr>
  </tbody>
  
</table>
<table class="example">
  <caption>Example 1: A complex tree.</caption>
  <tr id="ex2-node-1">
    <td>Node 1</td>
  </tr>
  <tr id="ex2-node-1-1" class="child-of-ex2-node-1">
    <td>Node 1.1</td>
  </tr>
  <tr id="ex2-node-1-2" class="child-of-ex2-node-1">
    <td>Node 1.2</td>
  </tr>
  <tr id="ex2-node-1-3" class="child-of-ex2-node-1">
    <td>Node 1.3</td>
  </tr>
  <tr id="ex2-node-2">
    <td>Node 2</td>
  </tr>
  <tr id="ex2-node-2-1" class="child-of-ex2-node-2">
    <td>Node 2.1</td>
  </tr>
  <tr id="ex2-node-2-1-1" class="child-of-ex2-node-2-1">
    <td>Node 2.1.1</td>
  </tr>
  <tr id="ex2-node-2-2" class="child-of-ex2-node-2">
    <td>Node 2.2</td>
  </tr>
  <tr id="ex2-node-2-2-1" class="child-of-ex2-node-2-2">
    <td>Node 2.2.1</td>
  </tr>
  <tr id="ex2-node-2-2-1-1" class="child-of-ex2-node-2-2-1">
    <td>Node 2.2.1.1</td>
  </tr>
  <tr id="ex2-node-2-2-2" class="child-of-ex2-node-2-2">
    <td>Node 2.2.2</td>
  </tr>
</table>
</body>
</html>
