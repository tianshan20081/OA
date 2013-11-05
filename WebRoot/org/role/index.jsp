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
<title>列出所有角色信息</title>
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
	var oTable;
	$(function() {
		oTable = $("#roleList")
				.dataTable(
						{
							"bProcessing" : true,// 加载数据时候是否显示进度条
							"bServerSide" : true,// 是否从服务加载数据
							"sAjaxSource" : "system/role!list.action",// 如果从服务器端加载数据,这个属性用于指定加载的路径
							"sPaginationType" : "full_numbers",
							"oLanguage" : { // 主要用于设置各种提示文本
								"sProcessing" : "正在处理...", // 设置进度条显示文本
								"sLengthMenu" : "每页_MENU_行 <input type='button' value='添加角色' onclick='addRole();' /> <input type='button' value='删除角色' onclick='delRole();' /> <input type='button' value='更新角色' onclick='updateRole();' />",// 显示每页多少条记录
								"sEmptyTable" : "没有找到记录",// 没有记录时显示的文本
								"sZeroRecords" : "没有找到记录",// 没有记录时显示的文本
								"sInfo" : "总记录数 _TOTAL_ 当前显示从 _START_ 至 _END_",
								"sInfoEmpty" : "",// 没记录时,关于记录数的显示文本
								"sSearch" : "搜索:",// 搜索框前的文本设置
								"oPaginate" : {
									"sFirst" : "首页",
									"sLast" : "未页",
									"sNext" : "下页",
									"sPrevious" : "上页"
								}
							}
						});

		// 点击时候选中表行
		$("#roleList tbody").click(function(event) {
			var ons = oTable.fnGetNodes();
			for ( var i = 0; i < ons.length; i++) {
				$(ons[i]).removeClass("row_selected");
			}
			//		$(oTable.fnSettings().aoData).each(function() {
			//			$(this.nTr).removeClass("row_selected");
			//		});
			$(event.target.parentNode).addClass("row_selected");
		});
		oTable.css("font-size", "12px");
	})
	function delRole() {
		var anSelected = fnGetSelected(oTable);
		if (anSelected.length == 0) {
			alert("请选中要更新的人员");
			return;
		}
		// 获取选中人员de id
		var personId = anSelected[0].children[0].innerHTML;
		//	alert(personId);
		if (confirm("您确认要执行删除操作吗？(删除不可恢复！！！)")) {
			//window.location = "system/user!del.action?id=" + personId;
			$.get("system/role!del.action?id=" + personId, function() {
				oTable.fnDeleteRow(anSelected[0]);
			});
		}

	}
	function updateRole() {
		var anSelected = fnGetSelected(oTable);
		if (anSelected.length == 0) {
			alert("请选中要更新的角色");
			return;
		}
		// 获取选中人员de id
		var personId = anSelected[0].children[0].innerHTML;
		// alert(personId);
		window.location = "system/role!updateInput.action?id=" + personId;
		// $.get("system/person!updateInput.action?id="+personId);
	}
	/**
	 * 获得选中行的集合的方法
	 * 
	 * @param oTab
	 * @returns {Array}
	 */
	function fnGetSelected(oTab) {
		var aReturn = new Array();
		var aTrs = oTab.fnGetNodes();
		for ( var i = 0; i < aTrs.length; i++) {
			if ($(aTrs[i]).hasClass("row_selected")) {
				aReturn.push(aTrs[i]);
			}
		}
		return aReturn;
	}
	function addRole() {
		window.location = "system/role!addInput.action";
	}
</script>
</head>
<body>

	<table cellpadding="0" cellspacing="0" border="0" class="display" id="roleList" width="100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>角色名</th>
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
				<th>角色名</th>
			</tr>
		</tfoot>
	</table>
</body>
</html>

