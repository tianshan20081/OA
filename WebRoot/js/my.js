(
	function($){
		
		$.fn.myTable = function(settings){
			var mydefault = {"data":[["","",""]]};
		
			if(settings != null){
				$.extend(mydefault,settings);
			}
			var args = mydefault.data;
			
			var tbody = this.children("tbody");
			
			var tab = "";
			for(var i = 0 ; i < args.length ; i++){
				tab += "<tr>";
				for(var j = 0 ; j < args[i].length ; j++){
					tab += "<td>";
					tab += args[i][j];
					tab += "</td>";
				}
				tab += "</tr>";
			}
			
			tbody.html(tab);
		};
	}
)(jQuery);