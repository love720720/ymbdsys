function activateDestroy(obj, id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	$.ajax({
		type : "post",
		dataType : "json",
		url : "activateDestroy.htm",
		data : {
			"id" : id
		},
		success : function(data) {
			if (data) {
				$(obj).attr("disabled", true);
				alertDanger("操作成功");
				return;
			} else {
				alertDanger("操作失败 请返回重试");
				return;
			}
		},
		error : function(a, b, c) {
			alert(b);
		}
	});
	return;
}
function manageStaff(id){
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "manageStaff.htm?id=" + id;
	return;
}
function edit() {
	location.href = "editStaff.htm";
	return;
}
function alertDanger(content) {
	$("body").alert({
		type : "danger",
		title : "提示",
		content : content || "参数出错 请返回重试",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click"
	});
	return;
}