<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加人员账号</title>

<link href="org/theme/party.css" type="text/css" rel="stylesheet">
</head>
<body>

	<div id="formwrapper">
		<h3>添加操作的基本信息</h3>
		<form action="system/resource.action" method="post">
			<input type="hidden" name="method:addOper"> 
			<!-- 资源的id -->
			<input type="hidden" name="id" value='<s:property value="id"/>' /> 
			<fieldset>
				<legend>添加操作的基本信息</legend>
				<div>
					<label for="operName">操作名称</label><input type="text" name="operName" id="operName"  size="60" /> <br />
				</div>
				<div>
					<label for="operSn">操作的标识</label><input type="text" name="operSn" id="operSn"  size="60" /> <br />
				</div>
				<div>
					<label for="methodName">方法名</label><input type="text" name="methodName" id="methodName" size="60" /> <br />
				</div>
				<div>
					<label for="operIndex">操作索引</label><input type="text" name="operIndex" id="operIndex"  size="60" /> <br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="保存操作信息" /> <input name="reset" type="reset" class="buttom" value="重置" />
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>

