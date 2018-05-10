function sub(){
	var name = $.trim($("#name").val());
	if(name == null || name.length <= 0){
		alertDanger(null,"请输入权限名称");
		return;
	}
	var type = $("input[name='type']:checked").val();
	if(type == null || type.length <= 0){
		alertDanger(null,"请选择权限类型");
		return;
	}
	if(type == "button"){
		var code = $.trim($("#code").val());
		if(code == null || code.length <= 0){
			alertDanger(null,"按钮权限code不可为空");
			return;
		}
	}
	$("#form_edit").submit();
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
	});
	return;
}