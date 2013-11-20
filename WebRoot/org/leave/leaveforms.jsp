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
<title>请假单管理</title>
<link href="org/theme/party.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" language="javascript" src="js/jquery.dataTables.min.js"></script>
<style type="text/css" title="currentStyle">
@import "js/datatable/css/demo_page.css";

@import "js/datatable/css/demo_table.css";

@import "js/datatable/css/demo_table_jui.css";

@import "js/datatable/css/jquery.dataTables_themeroller.css";

@import "js/datatable/css/jquery.dataTables.css";
</style>
<script type="text/javascript">
	
</script>
</head>
<body>

	<table cellpadding="0" cellspacing="0" border="0" class="display" id="personList" width="100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>请假者</th>
				<th>请假事项</th>
				<th>请假时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>

		</thead>
		<tbody>
			<s:iterator value="#leaveForms">

				<tr >
					<td><s:property value="id" />
					</td>
					<td><s:property value="leaver.person.name" />
					</td>
					<td><s:property value="content" />
					</td>
					<td><s:date name="createTime" format="yyyy-MM-dd" />
					</td>
					<td><s:property value="status" />
					</td>
					<td><a href="personal/leave!submit.action?id=<s:property value='id'/>">提交</a></td>

				</tr>
			</s:iterator>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>请假者</th>
				<th>请假事项</th>
				<th>请假时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</tfoot>
	</table>
</body>
</html>

