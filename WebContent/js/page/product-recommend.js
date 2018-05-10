function recommend(id,isTrue,obj) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	$.ajax({
		type : "post",
		dataType : "json",
		url : "recommend.htm",
		data : {
			"id" : id,
			"isTrue" : isTrue
		},
		success : function(data) {
			if (data.isTrue) {
				$(obj).attr("disabled",true);
				alertDanger(null,data.tips);
				return;
			} else {
				alertDanger(null,data.tips);
				return;
			}
		},
		error : function(a, b, c) {
			alert(b);
		}
	});
}
function alertDanger(title,content) {
	$("body").alert({
		type : "danger",
		title : title || "提示",
		content : content || "参数出错 请返回重试",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click"
	})
	return;
}