function checkAll(obj,cName){
    var checkboxs = document.getElementsByName(cName);
    for(var i=0;i<checkboxs.length;i++){
	    checkboxs[i].checked = obj.checked;
    }
}
function editRolePrivilege(id){
	if(id <= 0){
		alertDanger();
		return;
	}
	var ids = "";
	$("input:checkbox[name='rp']:checked").each(function(){
		ids += $(this).val() + ",";
	});
	var len = ids.length;
	if(len <= 0){
		ids = ids.substring(0,len - 1);
	}
	location.href="editRolePrivilege.htm?id=" + id + "&ids=" + ids;
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
	});
	return;
}