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
<title>角色授权主界面</title>
<link href="org/theme/party.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" language="javascript" src="js/jquery.dataTables.min.js"></script>
<style type="text/css" title="currentStyle">
@import "js/datatable/css/demo_page.css";

@import "js/datatable/css/demo_table.css";

@import "js/datatable/css/demo_table_jui.css";

@import "js/datatable/css/jquery.dataTables_themeroller.css";

@import "js/datatable/css/jquery.dataTables.css";

.dataTables_filter {
	width: 70%;
	float: left;
	text-align: left;
	font-size: 12px;
}

.dataTables_filter input {
	width: 60%;
	float: right;
}
</style>
<script language="javascript">
	$(function()
	{
		oTable = $("#personList").dataTable({
			"bProcessing" : true,// 加载数据时候是否显示进度条
			"bServerSide" : true,// 是否从服务加载数据
			"bPaginate" : false,//不要分页
			"bInfo" : false,//不要显示当前第几条...
			"bSort" : false,//不要排序特性
			"aoColumnDefs" : [ {
				"bVisible" : false,
				"aTargets" : [ 0 ]
			//隐藏Id 列
			} ],
			"sAjaxSource" : "system/acl!userAuthIndexTree.action",// 值返回 id he 那么
			"oLanguage" : { // 主要用于设置各种提示文本
				"sProcessing" : "正在处理...", // 设置进度条显示文本
				"sEmptyTable" : "没有找到记录",// 没有记录时显示的文本
				"sZeroRecords" : "没有找到记录",// 没有记录时显示的文本
				"sInfoEmpty" : "",// 没记录时,关于记录数的显示文本
				"sSearch" : "搜索:",// 搜索框前的文本设置
			}
		});
		// 点击时候选中表行
		$("#personList tbody").click(function(event)
		{
			var ons = oTable.fnGetNodes();
			for ( var i = 0; i < ons.length; i++) {
				$(ons[i]).removeClass("row_selected");
			}
			$(event.target.parentNode).addClass("row_selected");
			openAuth();
		});
		oTable.css("font-size", "12px");
	});
	//打开授权界面
	function openAuth()
	{
		var principalId;
		//搜索那一行被选中
		$(oTable.fnSettings().aoData).each(function()
		{
			if ($(this.nTr).hasClass("row_selected")) {
				//因为id 列是隐藏的
				principalId = this._aData[0];
			}
		});
		if (principalId) {
			$("#rigthFrame").attr("src", "system/acl!allMenuResource.action?principalId=" + principalId + "&principalType=User");
		}
	}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
}
-->
</style>
</head>
<body>
	<table width="100%" height="100%" border="0" cellpadding="0">
		<tr>
			<td width="150" valign="top"><table cellpadding="0" cellspacing="0" border="0" class="display" id="personList" width="100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>选择用户授权</th>
						</tr>

					</thead>
					<tbody>
						<tr class="odd gradeX">
							<td colspan="2">目前没有用户可操作</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<th>ID</th>
							<th>选择用户授权</th>
						</tr>
					</tfoot>
				</table></td>
			<td width="8" bgcolor="#add2da">&nbsp;</td>

			<td><iframe src="right.jsp" width="100%" height="100%" frameborder="0" id="rigthFrame" /></td>
			<td width="8" bgcolor="#add2da">&nbsp;</td>
		</tr>
	</table>
</body>
</html>

