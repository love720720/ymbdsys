
function activateDestroy(obj,id){if(id<=0){alertDanger();return;}
$.ajax({type:"post",dataType:"json",url:"activateDestroy.htm",data:{"id":id},success:function(data){if(data){$(obj).attr("disabled",true);alertDanger("操作成功");return;}else{alertDanger("操作失败 请返回重试");return;}},error:function(a,b,c){alert(b);}});return;}
function edit(){location.href="editStaff.htm";return;}
function alertDanger(content){$("body").alert({type:"danger",title:"提示",content:content||"参数出错 请返回重试",btntext:"确定",modal:true,draggabled:false,even:"click"});return;}
function sub(){var name=$.trim($("#name").val());if(name==null||name.length<=0){alertDanger("请输入姓名");return;}
var tname=name.substring(0,1);var reg=/[\u4e00-\u9fa5]/;if(!reg.test(tname)){alertDanger("姓名请以汉字开头");return;}
reg=/^[0-9\u4e00-\u9fa5]+$/;if(!reg.test(name)){alertDanger("姓名含有非法字符");return;}
if(name.length<2){alertDanger("最少输入2个字符");return;}
if(name.length>16){alertDanger("请勿超过16个字符");return;}
var userName=$.trim($("#userName").val());if(userName==null||userName.length<=0){alertDanger("登陆账号为生成 请刷新重试");return;}
var gender=$.trim($("#gender").val());if(name==null||gender.length<=0){alertDanger("请选择性别");return;}
var phone=$.trim($("#phone").val());if(phone==null||phone.length<=0){alertDanger("请输入联系方式");return;}
var b=false;var email=$.trim($("#email").val());if(email!=null&&email.length>0){var reg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;if(!reg.test(email)){alertDanger("email格式不正确");return;}
b=true;}
var phone=$.trim($("#phone").val());$.ajax({type:"post",dataType:"json",url:"editStaff.htm",data:{"name":name,"userName":userName,"gender":gender,"email":email,"phone":phone},success:function(data){if(data){var content="默认密码admin 请及时修改";if(b){content="密码已经发送";}
$("body").alert({type:"danger",title:"提示",content:content,btntext:"确定",modal:true,draggabled:false,even:"click",buttons:[{id:"yes",name:"确定",callback:function(){location.href="staffList.htm";}}]});}else{alertDanger();}},error:function(a,b,c){alert(b);}});return;}
function changeName(){$("#userName").val(null);var name=$.trim($("#name").val());if(name==null||name.length<=0){return;}
var tname=name.substring(0,1);var reg=/[\u4e00-\u9fa5]/;if(!reg.test(tname)){return;}
reg=/^[0-9\u4e00-\u9fa5]+$/;if(!reg.test(name)){return;}
if(name.length<2||name.length>16){return;}
$.ajax({type:"post",dataType:"json",url:"pingYin.htm",data:{"name":name},success:function(data){if(data.isTrue){$("#userName").val(data.param);}else{alertDanger(data.param);}},error:function(a,b,c){alert(b);}});return;}