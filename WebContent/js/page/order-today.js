
var LODOP;$(function(){var orderIdSize=$("#orderIdSize").val();if(orderIdSize>0){$(".classHidden").show();}});function del(id){if(id<=0){alertDanger();return;}
$("body").alert({type:"danger",title:"提示",content:"此操作将会删除订单以及金额",btntext:"确定",modal:true,draggabled:false,even:"click",buttons:[{id:"yes",name:"确定",callback:function(){location.href="deleteOrder.htm?form=today&id="+id;}},{id:"no",name:"取消"}]});return;}
function detail(id){if(id<=0){alertDanger();return;}
location.href="detailOrder.htm?id="+id;return;}
function alertDanger(title,content){$("body").alert({type:"danger",title:title||"提示",content:content||"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"});return;}
function openPrint(){$(".classHidden").show();}
function print(ids,notIds,content,flag,pageIndex){$("body").alert({type:"danger",title:"提示",content:content||"确认打印",btntext:"确定",modal:true,draggabled:false,even:"click",buttons:[{id:"yes",name:"确定",callback:function(){$.ajax({type:"post",dataType:"json",url:"printAll.htm",data:{"ids":ids,"notIds":notIds,"flag":flag},success:function(data){if(data){var orders=data.orders;if(orders.length<=0){alertDanger();return;}
LODOP=getLodop(document.getElementById("LODOP"),document.getElementById("LODOP_EM"));LODOP.PRINT_INIT("订单打印");LODOP.SET_PRINT_PAGESIZE(3,"55mm","50mm","");LODOP.SET_PRINT_STYLE("FontName","楷体");try{LODOP.SET_PRINTER_INDEX(-1);}catch(e){alertDanger(null,"请安装打印机并开启print spooler服务");return;}
var len=orders.length;var table="";for(var i=0;i<len;i++){table+="<table style='font-size:13px;text-align: center;' width='100%'>";table+="<tr><td colspan='3' style='font-size:20px;'>一米便当</td></tr>";table+="</table>";table+="<table style='font-size:13px;' width='100%'>";table+="<tr><td width='18%'>客户</td><td>"+orders[i].userName+"</td></tr>";table+="<tr><td colspan='2'>"+orders[i].phone+"</td></tr>";table+="<tr><td colspan='2'>"+orders[i].address+"</td></tr>";table+="</table>";table+="<table style='font-size:13px;' width='100%'>";table+="<tr><td width='60%'>套餐种类</td><td>数量</td><td>单价</td></tr>";var products=orders[i].products;for(var j=0;j<products.length;j++){table+="<tr><td>"+products[j].name+"</td><td>"+products[j].byNum+"</td><td>￥"+products[j].priceNow+"</td></tr>";}
table+="<tr><td></td><td>小计</td><td>"+orders[i].priceNows+"</td></tr>";table+="</table>";table+="<table style='font-size:13px;' width='100%'>";table+="<tr><td width='34%'>备注</td><td>"+orders[i].remark+"</td></tr>";table+="<tr><td>订餐QQ</td><td>2939424225</td></tr>";table+="<tr><td>订餐电话</td><td>0551-66011131</td></tr>";table+="</table>";if(len>1&&len!=(i+1)){table+="<hr/>";}}
LODOP.ADD_PRINT_HTML("-4mm","-3mm","99%","1mm",table);LODOP.PREVIEW();$.ajax({type:"post",dataType:"json",url:"editPrintNum.htm",data:{"ids":ids,"flag":flag},success:function(data){if(data){$("input:checkbox").removeAttr("checked");$("#orderIdSize").val(null);$(".classHidden").hide();location.href="orderToday.htm?pageIndex="
+pageIndex;return;}},error:function(a,b,c){alert(b);}});}else{alertDanger();}},error:function(a,b,c){alert(b);}});}},{id:"no",name:"取消"}]});}
function printAll(pageIndex){var ids="";var notIds="";var check=$("input:checkbox[name='orderId']:checked");var notChecked=$("input:checkbox[name='orderId']").not("input:checked");if(check.size()>0){check.each(function(){var id=$(this).val();if(id>0){ids+=id+",";}});}
if(notChecked.size()>0){notChecked.each(function(){var id=$(this).val();if(id>0){notIds+=id+",";}});}
var len=ids.length;if(len>0){ids=ids.substring(0,len-1);}
var notLen=notIds.length;if(notLen>0){notIds=notIds.substring(0,notLen-1);}
print(ids,notIds,"确认批量打印",0,pageIndex);}
function printSingle(id,pageIndex){print(id,null,null,1,pageIndex);}
function check_all(obj,cName){var checkboxs=document.getElementsByName(cName);for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked=obj.checked;}}
function edit(id){if(id<=0){alertDanger();return;}
location.href="manually.htm?id="+id;return;}
function orderProductCountPrice(pageIndex){var ids="";var notIds="";var check=$("input:checkbox[name='orderId']:checked");var notChecked=$("input:checkbox[name='orderId']").not("input:checked");if(check.size()>0){check.each(function(){var id=$(this).val();if(id>0){ids+=id+",";}});}
if(notChecked.size()>0){notChecked.each(function(){var id=$(this).val();if(id>0){notIds+=id+",";}});}
var len=ids.length;if(len>0){ids=ids.substring(0,len-1);}
var notLen=notIds.length;if(notLen>0){notIds=notIds.substring(0,notLen-1);}
$("input:checkbox").removeAttr("checked");$("#orderIdSize").val(null);$(".classHidden").hide();window.open("orderProductCountPrice.htm?pageIndex="+pageIndex+"&ids="
+ids+"&notIds="+notIds);return;}
function pager(fromAction,beforePageIndex,pageIndex){if(fromAction==null||fromAction.length<=0){return;}
var ids="";var notIds="";var check=$("input:checkbox[name='orderId']:checked");var notChecked=$("input:checkbox[name='orderId']").not("input:checked");if(check.size()>0){check.each(function(){var id=$(this).val();if(id>0){ids+=id+",";}});}
if(notChecked.size()>0){notChecked.each(function(){var id=$(this).val();if(id>0){notIds+=id+",";}});}
var len=ids.length;if(len>=1){ids=ids.substring(0,len-1);}
var notLen=notIds.length;if(notLen>=1){notIds=notIds.substring(0,notLen-1);}
fromAction+=".htm";var form=document.createElement("form");document.body.appendChild(form);var pageIndexInput=document.createElement("input");pageIndexInput.type="hidden";pageIndexInput.value=pageIndex;pageIndexInput.name="pageIndex";form.appendChild(pageIndexInput);var idsInput=document.createElement("input");idsInput.type="hidden";idsInput.value=ids;idsInput.name="ids";form.appendChild(idsInput);var beforePageIndexInput=document.createElement("input");beforePageIndexInput.type="hidden";beforePageIndexInput.value=beforePageIndex;beforePageIndexInput.name="beforePageIndex";form.appendChild(beforePageIndexInput);var beforePageIndexInput=document.createElement("input");beforePageIndexInput.type="hidden";beforePageIndexInput.value=notIds;beforePageIndexInput.name="notIds";form.appendChild(beforePageIndexInput);form.action=fromAction;form.method="post";form.submit();return;}
function removeOrderIdCaChe(){$.ajax({type:"post",dataType:"json",url:"removeOrderIdCaChe.htm",success:function(data){if(data){$("input:checkbox").removeAttr("checked");$("#orderIdSize").val(null);$(".classHidden").hide();alertDanger(null,"缓存清空成功");return;}else{alertDanger(null,"缓存清空失败 请返回重试");}},error:function(a,b,c){alert(b);}});}