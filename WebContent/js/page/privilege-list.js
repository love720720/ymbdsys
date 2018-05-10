function detail(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "detailPrivilege.htm?id=" + id;
	return;
}
function edit(pid,id) {
	id = id || pid;
	location.href = "editPrivilege.htm?pid=" + pid + "&id=" + id;
	return;
}
function del(id) {
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
				$.ajax({
					type : "post",
					dataType : "json",
					url : "deletePrivilege.htm",
					data : {
						"id" : id
					},
					success : function(data) {
						if (data.isTrue) {
							$("body").alert({
								type : "success",
								title : "提示",
								content : data.tips,
								btntext : "确定",
								modal : true,
								draggabled : false,
								even : "click",
								buttons : [ {
									id : "yes",
									name : "立即刷新",
									callback : function() {
										location.href="privilegeList.htm";
									}
								}, {
									id : "no",
									name : "稍等刷新"
								} ]
							});
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
		}, {
			id : "no",
			name : "取消"
		} ]
	});
	return;
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