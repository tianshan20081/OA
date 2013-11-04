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

<title>My JSP 'ajax01.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<script type="text/javascript">
		var xmlHttp = false;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (!xmlHttp) {
			alert("当前浏览器无法创建XMLHttpRequest 对象");
		} else {
			//初始化XMLHTTPRequest 对象
			xmlHttp.open("GET", "/system/company.action?method:ajax", true);
			alert("hello3");
			//定义一个回调函数
			xmlHttp.onreadystatechange() = function() {
											alert(xmlHttp.readyState);
											if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
												var str = xmlHttp.responseText();
												alert(str)
											}
										}
			//发送
			xmlHttp.send();
		}
		
	</script>
</body>
</html>
