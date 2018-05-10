
$(function(){$(".datepicker").datepicker({dateFormat:"yy-mm-dd"});$("#sub").click(function(){var name=$("#name").val();if(name==null||$.trim(name).length<=0){alertDanger(null,"请输入商品名称");return;}
var classify=$("#classifyId").val();if(classify==null||classify.length<=0){alertDanger(null,"请选择分类");return;}
var stat=$("#stat").val();if(stat==null||stat.length<=0){alertDanger(null,"请选择状态");return;}
var priceNow=$("#priceNow").val();if(priceNow==null||$.trim(priceNow).length<=0){alertDanger(null,"请输入商品现价");return;}
$("#form_edit").submit();});var alertDanger=function(title,content){$("body").alert({type:"danger",title:title||"提示",content:content||"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"})
return;}})