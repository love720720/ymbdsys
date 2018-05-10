var ids = "";
var i = 0;
$(function() {
	var productIds = $.trim($("#productIds").val());
	ids = productIds;
	var id = $.trim($("#id").val());
	if (id <= 0) {
		$("#productView").hide();
	}
	$("#userName_Id").select2({
		allowClear : true,
		placeholder : "客户名称",
		minimumInputLength : 2,
		ajax : {
			type : "post",
			dataType : "json",
			url : "selectUserInfo.htm",
			quietMillis : 500,
			data : function(term, page) {
				return {
					name : term,
					page_limit : 10,
					apikey : "NoUse"
				};
			},
			results : function(data, page) {
				return {
					results : data.users
				};
			}
		},
		formatResult : movieFormatResultUser,
		formatSelection : movieFormatSelectionUser
	});
	if (i < 10) {
		$("#product_Id").select2({
			allowClear : true,
			placeholder : "请输入商品名称 按ENTER",
			minimumInputLength : 2,
			ajax : {
				type : "post",
				dataType : "json",
				url : "../product/selectProduct.htm",
				quietMillis : 500,
				data : function(term, page) {
					return {
						name : term,
						page_limit : 10,
						apikey : "NoUse",
						productIds : ids
					};
				},
				results : function(data, page) {
					return {
						results : data.products
					};
				}
			},
			formatResult : movieFormatResult,
			formatSelection : movieFormatSelection
		});
		return;
	} else {
		alertDanger(null, "目前最多只能购买10种不同商品");
		return;
	}
});
function movieFormatResultUser(users) {
	var tab;
	var id = users.id;
	if (id <= 0) {
		tab = "<table><tr><td>[" + users.name + "]未定义</td></tr></table>";
		return tab;
	}
	tab = "<table><tr><td>[" + users.name + "]</td></tr>";
	tab += "<tr><td>" + users.address + "</td></tr></table>";
	return tab;
}
function movieFormatSelectionUser(users, container) {
	$("#userId").val(users.userId);
	$("#userName").val(users.name);
	$("#phone").val(users.phone);
	$("#address").val(users.address);
	return users.name;
}
function movieFormatResult(products) {
	return products.name;
}
function movieFormatSelection(products, container) {
	if (products.name.length > 0) {
		var info = "<div><div class='floatLeft width_240'>" + products.name
				+ "&nbsp;&nbsp;￥" + products.price
				+ "</div>&nbsp;&nbsp;<input type='hidden' id='product_"
				+ products.id + "' name='idByNum' value='" + products.id
				+ "_1'/>" + "<input type='button' value='↑' onclick='addNum("
				+ products.id
				+ ");' class='btn btn-mini btn-primary add'/>&nbsp;"
				+ "<input type='text' id='text_" + products.id
				+ "' value='1' onblur='confirmNum(" + products.id
				+ ");' class='width_23'/>&nbsp;"
				+ "<input type='button' value='↓' onclick='redNum("
				+ products.id + ");' class='btn btn-mini btn-primary add'/>"
				+ "</div>";
		$("#product").append(info);
		$("#productView").show();
		$("#todayProductId option[value='" + products.id + "']").remove();
		ids += products.id + ",";
		i++;
	}
	return products.name;
}
function buyTodayProduct() {
	var todayProductId = $("#todayProductId").val();
	if (todayProductId == null || todayProductId.length <= 0) {
		return;
	}
	$.ajax({
		type : "post",
		dataType : "json",
		url : "../product/getProduct.htm",
		data : {
			"id" : todayProductId
		},
		success : function(data) {
			if (data.isTrue) {
				var info = "<div><div class='floatLeft width_240'>" + data.name
						+ "&nbsp;&nbsp;￥" + data.price
						+ "</div>&nbsp;&nbsp;<input type='hidden' id='product_"
						+ data.id + "' name='idByNum' value='" + data.id
						+ "_1'/>"
						+ "<input type='button' value='↑' onclick='addNum("
						+ data.id
						+ ");' class='btn btn-mini btn-primary add'/>&nbsp;"
						+ "<input type='text' id='text_" + data.id
						+ "' value='1' onblur='confirmNum(" + data.id
						+ ");' class='width_23'/>&nbsp;"
						+ "<input type='button' value='↓' onclick='redNum("
						+ data.id
						+ ");' class='btn btn-mini btn-primary add'/>"
						+ "</div>";
				$("#product").append(info);
				$("#productView").show();
				ids += data.id + ",";
				i++;
				$("#todayProductId option[value='" + data.id + "']").remove();
				return;
			} else {
				alertDanger(null, null);
				return;
			}
		},
		error : function(a, b, c) {
			alert(b);
		}
	});
}
function createOrder() {
	var userName = $.trim($("#userName").val());
	var phone = $.trim($("#phone").val());
	if (phone == null || phone.length <= 0) {
		alertDanger(null, "请输入号码");
		return;
	}
	var address = $.trim($("#address").val());
	if (address == null || address.length <= 0) {
		alertDanger(null, "请输入送餐地址");
		return;
	}
	var sendTime = $.trim($("#sendTime").val());
	var idByNum = "";
	$("input[name='idByNum']").each(function() {
		var i = $(this).val();
		var id_byNum = i.split("_");
		var id = id_byNum[0];
		var byNum = id_byNum[1];
		if (id > 0 && byNum > 0) {
			idByNum += $(this).val() + ",";
		}
	});
	if (idByNum.length <= 0) {
		alertDanger(null, "请选择需要购买的商品");
		return;
	}
	idByNum = idByNum.substring(0, idByNum.length - 1);
	var id = $("#id").val();
	if (id == null || id.length <= 0) {
		id = 0;
	}
	$("body").alert({
		type : "danger",
		title : "生成订单",
		content : "是否确认提交",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click",
		buttons : [ {
			id : "yes",
			name : "确定",
			callback : function() {
				$.ajax({
					type : "post",
					dataType : "json",
					url : "orderManually.htm",
					data : {
						"userId" : $("#userId").val(),
						"userName" : userName,
						"phone" : phone,
						"address" : address,
						"sendTime" : sendTime,
						"idByNum" : idByNum,
						"taste" : $("#taste").val(),
						"remark" : $("#remark").val(),
						"id" : id
					},
					success : function(data) {
						if (data) {
							$("body").alert({
								type : "success",
								title : "提示",
								content : "操作成功",
								modal : true,
								draggabled : false,
								even : "click",
								buttons : [ {
									id : "today",
									name : "返回今日订单",
									callback : function() {
										location.href = "orderToday.htm";
										return;
									}
								}, {
									id : "back",
									name : "返回列表",
									callback : function() {
										location.href = "orderList.htm";
										return;
									}
								}, {
									id : "again",
									name : "继续添加",
									callback : function() {
										location.href = "manually.htm";
										return;
									}
								} ]
							});
							return;
						} else {
							alertDanger(null, null);
							return;
						}
					},
					error : function(a, b, c) {
						alert(b);
					}
				});
			}
		}, {
			id : "no",
			name : "取消"
		} ]
	});
}
function alertDanger(title, content) {
	$("body").alert({
		type : "danger",
		title : title || "提示",
		content : content || "参数出错 请返回重试",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click"
	});
	return;
}
function confirmNum(inputId) {
	var inputVal = $("#product_" + inputId).val();
	var id_num = inputVal.split("_");
	var id = id_num[0];
	var num = $("#text_" + id).val().replace(/[^\d]/g, "");
	if (num >= 500) {
		num = 500;
	}
	$("#product_" + inputId).val(id + "_" + num);
	$("#text_" + id).val(num);
}
function addNum(inputId) {
	var inputVal = $("#product_" + inputId).val();
	var id_num = inputVal.split("_");
	var id = id_num[0];
	var num = id_num[1];
	if (num == 500) {
		return;
	}
	num++;
	$("#product_" + inputId).val(id + "_" + num);
	$("#text_" + id).val(num);
}
function redNum(inputId) {
	var inputVal = $("#product_" + inputId).val();
	var id_num = inputVal.split("_");
	var id = id_num[0];
	var num = id_num[1];
	if (num == 0) {
		return;
	}
	num--;
	$("#product_" + inputId).val(id + "_" + num);
	$("#text_" + id).val(num);
}