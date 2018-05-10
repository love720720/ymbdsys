function edit(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "editProduct.htm?id=" + id;
}
function del(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	$("body").alert({
		type : "danger",
		title : "提示",
		content : "确认删除",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click",
		buttons : [ {
			id : "yes",
			name : "确定",
			callback : function() {
				location.href = "deleteProduct.htm?id=" + id;
			}
		}, {
			id : "no",
			name : "取消"
		} ]
	})
	return;
}
function detail(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "detailProduct.htm?id=" + id;
	return;
}
function alertDanger(title) {
	$("body").alert({
		type : "danger",
		title : title || "提示",
		content : "参数出错 请返回重试",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click"
	})
	return;
}