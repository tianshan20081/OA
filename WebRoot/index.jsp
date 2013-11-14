<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>XX办公自动化(OA)系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.textfield {
	margin: 0px;
	height: 21px;
	width: 106px;
	border: 1px solid #000;
}

#form {
	position: absolute;
	margin: 389px 430px;
}

#password {
	position: relative;
	margin: -22px 170px;
}

#submit {
	position: relative;
	margin: -22px 340px;
}

.word {
	font-size: 12px;
	font-family: "宋体";
}
</style>
</head>
<body background="images/index.jpg" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<div id="form" >
		<form method="post" action="system/login">
			<div id="username" class="word">
				用户：<input style="border:solid #000000 1px; width:120px; height:20" name="username" type="text" />
			</div>
			<div id="password" class="word">
				密码：<input style="border:solid #000000 1px; width:120px; height:20" name="password" type="password" />
			</div>
			<div id="submit">
				<input type="image" src="images/submit.jpg" />
			</div>
		</form>
	</div>
</body>
</html>