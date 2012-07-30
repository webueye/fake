common = {
	get : function(data, url, callback) {
		$.ajax({
			type : 'get',
			url : basePath + url,
			data : data,
			dataType : 'json',
			success : callback
		});
	},
	post : function(data, url, callback) {
		$.ajax({
			type : 'post',
			url : basePath + url,
			data : data,
			dataType : 'json',
			success : callback
		});
	},
	sort : function(orderBy, defaultOrder) {
		if ($("#orderBy").val() == orderBy) {
			if ($("#order").val() == "") {
				$("#order").val(defaultOrder);
				$("#className").val("sorting-asc");
			} else if ($("#order").val() == "desc") {
				$("#order").val("asc");
				$("#className").val("sorting-asc");
			} else if ($("#order").val() == "asc") {
				$("#order").val("desc");
				$("#className").val("sorting-desc");
			}
		} else {
			$("#orderBy").val(orderBy);
			$("#order").val(defaultOrder);
		}
		$("#validateForm").submit();
	},
	selectAll : function(className) {
		$(className).each(function(i) {
			$(this).attr("checked", "true");
		});
	},
	reverseSelect : function(elem, className) {
		if ($(elem).attr("checked")) {
			$(className).each(function(i) {
				$(this).attr("checked", "true");
			});
		} else {
			$(className).each(function(i) {
				$(this).removeAttr("checked");
			});
		}
	}
};

boxCode = {
	group : function() {
		var codeValues = $("#boxCodeValues").val();
		if (codeValues == '') {
			$("#boxCodeValues").addClass("error");
			$("#boxCodeValuesVerify").show();
			return;
		}
		var data = {
			boxCodeValues : codeValues
		};

		common
				.post(
						data,
						"/box-code/box-group",
						function(data) {
							if (data) {
								var con = new Array();
								for ( var i = 0; i < data.length; i++) {
									con.push("<tr>");
									con.push("	<td>");
									con.push(data[i].product.productNo);
									con.push("	</td>");
									con.push("	<td>");
									con.push(data[i].product.name);
									con.push("	</td>");
									con.push("	<td>");
									con.push(data[i].boxCount);
									con.push("	</td>");
									con.push("	<td>");
									con.push(data[i].totalCount);
									con.push("	</td>");
									con.push("	<td>");
									for ( var j = 0; j < data[i].boxCodes.length; j++) {
										con.push("<div>");
										con
												.push("<input type='hidden' class='boxCodes' name='boxCodes' value='"
														+ data[i].boxCodes[j].boxCode
														+ "'/>");
										con.push("["
												+ data[i].boxCodes[j].boxCode
												+ "] ");
										con
												.push("["
														+ data[i].boxCodes[j].boxSpec.specName
														+ "] ");
										con
												.push("["
														+ data[i].boxCodes[j].boxSpec.capacity
														+ "] ");
										con
												.push("<span style='cursor: pointer;' onclick='boxCode.refreshGroup("
														+ data[i].boxCodes[j].boxCode
														+ ");'>[删除]</span>");
										con.push("</div>");
									}
									con.push("	</td>");
									con.push("</tr>");
								}
								$("#detailContent").html(con.join(""));
							}
						});
	},
	refreshGroup : function(boxCode) {
		var bcValues = $("#boxCodeValues").val().replace(boxCode, "");
		bcValues = bcValues.replace("\n", "");
		$("#boxCodeValues").val(bcValues);
		if (bcValues == '') {
			$("#detailContent").html("");
		} else {
			this.group();
		}
	}
};

menu = {
	cascadeCheck : function(up, selfObj, down) {
		var menuId = $(selfObj).attr("id").split("_");
		if (menuId.length == 2) {
			if (selfObj.checked == true) {
				menu.upCheck(menuId[0]);
			}
			if (down) {
				menu.downCheck(menuId[1], selfObj);
			}
		}

	},
	upCheck : function(parentId) {
		var menuId = $("input[id$=_" + parentId + "]");
		if (menuId.length > 0) {
			menuId[0].checked = true;
			menu.cascadeCheck(true, menuId[0], false);
		}

	},
	downCheck : function(selfId, selfObj) {
		var menuId = $("input[id^=" + selfId + "_]");
		for ( var i = 0; i < menuId.length; i++) {
			if (selfObj.checked == true) {
				menuId[i].checked = true;
			} else {
				menuId[i].checked = false;
			}
			menu.cascadeCheck(false, menuId[i], true);
		}
	}
};
