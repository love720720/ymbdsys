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
function sub() {
	var oldPassword = $.trim($("#oldPassword").val());
	if (oldPassword == null || oldPassword.length <= 0) {
		alertDanger("请输入原始密码");
		return;
	}
	var newPassword = $.trim($("#newPassword").val());
	if (newPassword == null || newPassword.length <= 0) {
		alertDanger("请输入新密码");
		return;
	}
	if (newPassword.length < 6 || newPassword.length > 16) {
		alertDanger("密码长度6-16");
		return;
	}
	var againPassword = $.trim($("#againPassword").val());
	if (againPassword == null || againPassword.length <= 0) {
		alertDanger("请再次输入新密码");
		return;
	}
	if (newPassword != againPassword) {
		alertDanger("两次密码不一直 请重新输入");
		return;
	}
	$.ajax({
		type : "post",
		dataType : "json",
		url : "upPassword.htm",
		data : {
			"oldPassword" : oldPassword,
			"newPassword" : newPassword
		},
		success : function(data) {
			if (data.isTrue) {
				$("body").alert({
					type : "danger",
					title : "提示",
					content : data.param,
					btntext : "确定",
					modal : true,
					draggabled : false,
					even : "click",
					buttons : [ {
						id : "yes",
						name : "确定",
						callback : function() {
							location.href = "../index.htm";
						}
					} ]
				});
			} else {
				alertDanger(data.param);
			}
		},
		error : function(a, b, c) {
			alert(b);
		}
	});
	return;
}
function showPassword() {
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var againPassword = $("#againPassword").val();
	var table = "<table class='width_320 table table-striped table-bordered table-condensed' id='passwordTable'>"
			+ "<tr>"
			+ "<th class='colorRed' colspan='2'>注:请勿输入特殊字符[密码长度6-16]</th>"
			+ "</tr>"
	var checked = $("#checkboxPassword").attr("checked");
	if (checked) {
		$("#passwordTable").remove();
		table += "<tr>"
				+ "<th>原始密码</th>"
				+ "<td><input type='text' id='oldPassword' name='oldPassword' maxlength='16' value='"
				+ oldPassword
				+ "' /></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th>新密码</th>"
				+ "<td><input type='text' id='newPassword' name='newPassword' maxlength='16' value='"
				+ newPassword
				+ "' /></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th>确认密码</th>"
				+ "<td><input type='text' id='againPassword' name='againPassword' maxlength='16' value='"
				+ againPassword
				+ "' /></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td colspan='2'>"
				+ "<input type='checkbox' id='checkboxPassword' checked onclick='showPassword();'/><label class='lab' for='checkboxPassword'>显示密码</label>"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td colspan='2' name='textCenter'>"
				+ "<input type='button' value='提交' onclick='sub();' class='btn btn-mini btn-primary add'/>&nbsp;&nbsp;"
				+ "<input type='reset' class='btn btn-mini btn-primary add'/>"
				+ "</td>" + "</tr>" + "</table>";
		$("#tableCenter").append(table);
		return;
	}
	$("#passwordTable").remove();
	table += "<tr>"
			+ "<th>原始密码</th>"
			+ "<td><input type='password' id='oldPassword' name='oldPassword' maxlength='16' value='"
			+ oldPassword
			+ "' /></td>"
			+ "</tr>"
			+ "<tr>"
			+ "<th>新密码</th>"
			+ "<td><input type='password' id='newPassword' name='newPassword' maxlength='16' value='"
			+ newPassword
			+ "' /></td>"
			+ "</tr>"
			+ "<tr>"
			+ "<th>确认密码</th>"
			+ "<td><input type='password' id='againPassword' name='againPassword' maxlength='16' value='"
			+ againPassword
			+ "' /></td>"
			+ "</tr>"
			+ "<tr>"
			+ "<td colspan='2'>"
			+ "<input type='checkbox' id='checkboxPassword' onclick='showPassword();'/><label class='lab' for='checkboxPassword'>显示密码</label>"
			+ "</td>"
			+ "</tr>"
			+ "<tr>"
			+ "<td colspan='2' name='textCenter'>"
			+ "<input type='button' value='提交' onclick='sub();' class='btn btn-mini btn-primary add'/>&nbsp;&nbsp;"
			+ "<input type='reset' class='btn btn-mini btn-primary add'/>"
			+ "</td>" + "</tr>" + "</table>";
	$("#tableCenter").append(table);
	return;
}