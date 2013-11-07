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
<title>更新资源信息</title>
<link href="org/theme/resource.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<style type="text/css" title="currentStyle">
@import "js/datatable/css/demo_page.css";
@import "js/datatable/css/demo_table.css";
</style>

<script type="text/javascript">
var resourceId = <s:property value='id'/>;
</script>
<script type="text/javascript" src="org/theme/resource_tab.js" ></script>
</head>
<body>

	<div id="formwrapper">
		<h3>更新资源信息</h3>
		<form action="system/resource.action" method="post">
			<input type="hidden" name="method:update">
			 <input type="hidden" name="id" value="<s:property value="id"/>"> 
			 <input type="hidden" name="parent.id" value="<s:property value="parent.id"/>">
			<fieldset>
				<legend>
					资源信息 <input type="button" value="添加下级资源" onclick="window.location='system/resource!addInput.action?parent.id=${id}'"> <input type="button" value="删除本资源" onclick="window.location='system/resource!del.action?id=${id}'">
				</legend>
				<div>
					<label for="name">资源名称</label><input type="text" name="name" id="name" value="${name }" size="60" /> <br />
				</div>
				<div>
					<label for="sn">唯一标识</label><input type="text" name="sn" id="sn" value="${sn }" size="60" /> <br />
				</div>

				<div>
					<label for="className">类名</label><input type="text" name="className" id="className" value="${className }" size="60" /> <br />
				</div>
				<div>
					<label for="orderNumber">排序号</label><input type="text" name="orderNumber" id="orderNumber" value="${orderNumber }" size="60" /> <br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="保存资源信息" /> <input name="reset" type="reset" class="buttom" value="重置" />
				</div>
			</fieldset>
		</form>
	</div>
	<fieldset>
		<legend>
			资源对应的操作列表 <input type="button" value="添加操作" onclick="addOper()" /> 
			<input type="button" value="删除操作" onclick="delOper()" />
		</legend>

		<table cellpadding="0" cellspacing="0" border="0" class="display" id="resourceList" width="100%">
			<thead>
				<tr>
					<th>标识</th>
					<th>操作名称</th>
					<th>方法</th>
					<th>操作索引</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="opers">
					<tr>
						<td><s:property value="value.operSn" /></td>
						<td><s:property value="value.operName" /></td>
						<td><s:property value="value.methodName" /></td>
						<td><s:property value="value.operIndex" /></td>
					</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<th>标识</th>
					<th>操作名称</th>
					<th>方法</th>
					<th>操作索引</th>
				</tr>
			</tfoot>
		</table>
	</fieldset>
</body>
</html>

