
function detail(id){if(id<=0){alertDanger();return;}
location.href="../product/detailProduct.htm?id="+id;return;}
function alertDanger(title){$("body").alert({type:"danger",title:title||"提示",content:"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"});return;}