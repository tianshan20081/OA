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
<title>添加下级员工信息</title>

<link href="org/theme/party.css" type="text/css" rel="stylesheet">
</head>
<body>

	<div id="formwrapper">
		<h3>添加下级员工信息</h3>
		<form action="system/person.action" method="post">
			<input type="hidden" name="method:update"> <input type="hidden" name="id" value="<s:property value="id"/>"> <input type="hidden" name="parent.id" value="<s:property value="parent.id"/>">
			<fieldset>
				<legend>员工基本信息 </legend>
				<div>
					<label for="name">姓名</label><input type="text" name="name" id="name" value="${name }" size="60" /> <br />
				</div>
				<div>
					<label for="snumber">员工编号</label><input type="text" name="snumber" id="snumber" value="${snumber }" size="60" /> <br />
				</div>
				<div>
					<label for="address">地址</label><input type="text" name="address" id="address" value="${address }" size="60" /> <br />
				</div>
				<div>
					<label for="qq">QQ</label><input type="text" name="qq" id="qq" value="${qq }" size="60" /> <br />
				</div>
				<div>
					<label for="msn">MSN</label><input type="text" name="msn" id="msn" value="${msn }" size="60" /> <br />
				</div>

				<div>
					<label for="duty">职责</label><input type="text" name="duty" id="duty" value="${duty }" size="60" /> <br />
				</div>
				<div>
					<label for="phone">手机</label><input type="text" name="phone" id="phone" value="${phone }" size="60" /> <br />
				</div>
				<div>
					<label for="sex">性别</label><input type="text" name="sex" id="sex" value="${sex }" size="60" /> <br />
				</div>
				<div>
					<label for="email">Email</label><input type="text" name="email" id="email" value="${email }" size="60" /> <br />
				</div>
				<div>
					<label for="description">备注</label><input type="text" name="description" id="description" value="${description }" size="60" /> <br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="提交" /> <input name="reset" type="reset" class="buttom" value="重置" />
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>

