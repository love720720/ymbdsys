
$(function(){$("#sub").click(function(){var stat=$.trim($("#stat").val());if(stat==null||stat.length<=0){alertDanger(null,"请选择更新类型");return;}
var content=$.trim($("#content").val());if(content==null||content.length<=0){alertDanger(null,"请输入更新摘要");return;}
$("#form_edit").submit();});var alertDanger=function(title,content){$("body").alert({type:"danger",title:title||"提示",content:content||"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"})
return;}});