boxCode = {
	group : function() {
		var codeValues = $("#boxCodeValues").val();
		if(codeValues == ''){
			$("#boxCodeValues").addClass("error");
			$("#boxCodeValuesVerify").show();
			return;
		}
		var data = {boxCodeValues: codeValues};
		$.ajax({
			type : 'get',
			url : basePath+'/box-code/box-group',
			data: data,
			dataType : 'json',
			success : function(data) {
				if (data) {
					var con = new Array();
					for (var i = 0; i < data.length; i++) {
						con.push("<tr>");
						con.push("	<td>");
						con.push(		data[i].product.productNo);
						con.push("	</td>");
						con.push("	<td>");
						con.push(		data[i].product.name);
						con.push("	</td>");
						con.push("	<td>");
						con.push(		data[i].boxCount);
						con.push("	</td>");
						con.push("	<td>");
						con.push(		data[i].totalCount);
						con.push("	</td>");
						con.push("	<td>");
						for(var j = 0; j < data[i].boxCodes.length; j++){
							con.push("<div>");
							con.push("<input type='hidden' class='boxCodes' name='boxCodes' value='"+data[i].boxCodes[j].boxCode+"'/>");	
							con.push("["+data[i].boxCodes[j].boxCode+"] ");	
							con.push("["+data[i].boxCodes[j].boxSpec.specName+"] ");	
							con.push("["+data[i].boxCodes[j].boxSpec.capacity+"] ");	
							con.push("<span style='cursor: pointer;' onclick='boxCode.refreshGroup("+data[i].boxCodes[j].boxCode+");'>[删除]</span>");	
							con.push("</div>");
						}
						con.push("	</td>");
						con.push("</tr>");
					}
					$("#detailContent").html(con.join(""));
				}
			}
		});	
	},
	refreshGroup: function(boxCode){
		var bcValues = $("#boxCodeValues").val().replace(boxCode, "");
		bcValues = bcValues.replace("\n", "");
		$("#boxCodeValues").val(bcValues);
		if(bcValues == ''){
			$("#detailContent").html("");
		}else{
			this.group();
		}
	}
};
