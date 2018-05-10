
function sub(){var name=$.trim($("#name").val());if(name.length<=0){alertDanger(null,"请输入名称");return;}
var sort=$.trim($("#sort").val());if(isNaN(sort)){alertDanger(null,"排序参数错误");return;}
$("#form_edit").submit();return;}
function alertDanger(title,content){$("body").alert({type:"danger",title:title||"提示",content:content||"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"})
return;}