
function del(id){if(id<=0){alertDanger();return;}
$("body").alert({type:"danger",title:"提示",content:"此操作将会删除订单以及金额",btntext:"确定",modal:true,draggabled:false,even:"click",buttons:[{id:"yes",name:"确定",callback:function(){location.href="deleteOrder.htm?form=list&id="+id;}},{id:"no",name:"取消"}]});return;}
function detail(id){if(id<=0){alertDanger();return;}
location.href="detailOrder.htm?id="+id;return;}
function alertDanger(title){$("body").alert({type:"danger",title:title||"提示",content:"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"})
return;}