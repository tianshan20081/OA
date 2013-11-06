var oTable;
$(function() {
	oTable = $("#resourceList").dataTable({
		"bPaginate" : false,
		"bFilter":false,
		"bAutoWidth":false,
		"bInfo":false
		}
	});

	// 点击时候选中表行
	$("#resourceList tbody").click(function(event) {
		var ons = oTable.fnGetNodes();
		for(var i=0;i<ons.length;i++){
			$(ons[i]).removeClass("row_selected");
		}
		$(event.target.parentNode).addClass("row_selected");
	});
	oTable.css("font-size", "12px");
})
function delOper() {
	var anSelected = fnGetSelected(oTable);
	if (anSelected.length == 0) {
		alert("请选中要删除的操作");
		return;
	}
	// 获取选中人员de id
	var resourceId = anSelected[0].children[0].innerHTML;
// alert(resourceId);
	if (confirm("您确认要执行删除操作吗？(删除不可恢复！！！)")) {
		window.location = "system/resource!del.action?id=" + resourceId;
		$.get("system/resource!del.action?id=" + resourceId, function() {
			oTable.fnDeleteRow(anSelected[0]);
		});
	}
}
function addOper() {
	window.location = "system/resource!operInput.action?id=" + resourceId;
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