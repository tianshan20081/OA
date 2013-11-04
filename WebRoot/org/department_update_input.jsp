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
<title>更新部门信息</title>
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
	var parentId = <s:property value='id'/>;
</script>
<script type="text/javascript" src="org/theme/person_tab.js"></script>
</head>
<body>

	<div id="formwrapper">
		<h3>更新部门基本信息</h3>
		<form action="system/department.action" method="post">
			<input type="hidden" name="method:update"><input type="hidden" name="id" value="<s:property value="id"/>">
			<fieldset>
				<legend>
					部门基本信息 
					<input type="button" value="添加下级部门" onclick="window.location = 'system/department!addInput.action?parent.id=${id}'"> 
					<input type="button" value="添加岗位" onclick="window.location = 'system/position!addInput.action?parent.id=${id}'">
					<input type="button" value="添加人员" onclick="window.location = 'system/person!addInput.action?parent.id=${id}'"> 
					<input type="button" value="删除本部门" onclick="window.location = 'system/department!del.action?id=${id}'">
				</legend>
				<div>
					<label for="name">名称</label> <input type="text" name="name" id="name" value="${name }" size="60" /> <br />
				</div>
				<div>
					<label for="snumber">部门编号</label> <input type="text" name="snumber" id="snumber" value="${snumber }" size="30" /> <br />
				</div>
				<div>
					<label for="tel">电话</label> <input type="text" name="tel" id="tel" value="${tel }" size="30" /> <br />
				</div>
				
				<div>
					<label for="description">描述</label> <input type="text" name="description" id="description" value="${description }" size="60" /> <br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="保存部门基本信息" /> <input name="reset" type="reset" class="buttom" value="重置" />
				</div>
			</fieldset>
		</form>
	</div>
	<table cellpadding="0" cellspacing="0" border="0" class="display" id="personList" width="100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>电话</th>
			</tr>

		</thead>
		<tbody>
			<tr class="odd gradeX">
				<td colspan="4">没有更多内容</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>电话</th>
			</tr>
		</tfoot>
	</table>
</body>
</html>

