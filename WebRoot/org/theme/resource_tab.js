var oTable;
$(function() {
	oTable = $("#resourceList").dataTable({
		"bPaginate" : false,
		"bFilter" : false,
		"bSort" : false,
		"bAutoWidth" : false,
		"bInfo" : false
	});

	// 点击时选中表行
	$("#resourceList tbody").click(function(event) {
		var ons = oTable.fnGetNodes();
		for ( var i = 0; i < ons.length; i++) {
			$(ons[i]).removeClass("row_selected");
		}
		/*
		 * $(oTable.fnSettings().aoData).each( function(){
		 * $(this.nTr).removeClass("row_selected"); } );
		 */
		$(event.target.parentNode).addClass("row_selected");
	});

	oTable.css("font-size", "12px");
});

function delOper() {
	var anSelected = fnGetSelected(oTable);
	if (anSelected.length == 0) {
		alert("请选中要删除的操作!");
		return;
	}

	if (!confirm("删除不可恢复,您是否确认要删除吗?")) {
		return;
	}

	// 获取选中的人员的ID
	var resourceId = anSelected[0].children[0].innerHTML;
	$.get("system/resource!delOper.action?id=" + resourceId, function() {
		oTable.fnDeleteRow(anSelected[0]);
	});
}
function addOper() {
	window.location = "system/resource!operInput.action?id=" + resourceId;
}
// 获得选中行集合的方法
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
