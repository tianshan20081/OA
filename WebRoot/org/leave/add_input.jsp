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
<title>添加请假单</title>
<link href="org/theme/party.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="tools/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

	<div id="formwrapper">
		<h3>请设置公司的基本信息</h3>
		<form action="personal/leave.action" method="post">
			<input type="hidden" name="method:add"> <input type="hidden" name="leaver.id" value="${login.id }">
			<fieldset>
				<legend>请假单基本信息 </legend>
				<div>
					<label for="name">请假者</label> <input type="text" name="leaver.person.name" id="leaver.person.name" value="${login.name }" size="60" /> <br />
				</div>
				<div>
					<label for="tel">请假事项</label> <input type="text" name="content" id="content" value="${content }" size="30" /> <br />
				</div>
				<div>
					<label for="tel">请假天数</label> <input type="text" name="days" id="days" value="${days }" size="30" /> <br />
				</div>
				<div>
					<label for="fax">开始时间</label> <input type="text" name="beginTime" id="beginTime" value="${beginTime }" size="20" class="Wdate" onClick="WdatePicker()" /> <br />
				</div>
				<div>
					<label for="address">结束时间</label> <input type="text" name="endTime" id="endTime" value="${endTime }" size="20" class="Wdate" onClick="WdatePicker()" /> <br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="保存请假单" /> <input name="reset" type="reset" class="buttom" value="重置" />
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>

