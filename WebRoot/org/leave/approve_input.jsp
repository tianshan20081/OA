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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>审核请假单</title>
<link href="org/theme/party.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div id="formwrapper">
		<h3>审核请假单</h3>
		<form action="personal/leave.action" method="post">
			<input type="hidden" name="method:approve">
			<!-- 请假单ID -->
			<input type="hidden" name="id" value="<s:property value='id'/>">
			<fieldset>
				<legend>请假审核信息 </legend>
				<div>
					<label for="name">审核意见</label> <input type="text" name="info" id="info" size="60" /> <br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="保存审核意见" /> <input name="reset" type="reset" class="buttom" value="重置" />
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>

