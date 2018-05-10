
function detail(id){if(id<=0){alertDanger();return;}
location.href="detailTree.htm?id="+id;}
function display(id){if(id<=0){alertDanger();return;}
location.href="displayTree.htm?id="+id;}
function edit(id){id=id||0;location.href="editTree.htm?id="+id;}
function del(id){if(id<=0){alertDanger();return;}
$("body").alert({type:"danger",title:"提示",content:"此操作会删除该权限以及子权限",btntext:"确定",modal:true,draggabled:false,even:"click",buttons:[{id:"yes",name:"确定",callback:function(){location.href="deleteTree.htm?&id="+id;}},{id:"no",name:"取消"}]});return;}
function alertDanger(title){$("body").alert({type:"danger",title:title||"提示",content:"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"});return;}