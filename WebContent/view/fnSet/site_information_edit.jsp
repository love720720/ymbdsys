<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 系统更新日志</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
textarea{
	width: 400px;
	height: 60px;
}
.table{
	width: 600px;
}
</style>
<script type="text/javascript">
function changeSiteInformation(){
	$("body").alert({
		type : "danger",
		title : "提示",
		content : "确认提交更新操作",
		modal : true,
		draggabled : false,
		even : "click",
		buttons : [
		    {
				id : "yes",
				name : "更新",
				callback : function() {
					$("#form_edit").submit();
					return;
				}
			},
				{
				id : "no",
				name : "取消",
				callback : function() {
					return;
				}
			}
		]
	});
}
</script>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>设置<b class="tip"></b>站点信息</div>
<center>
<form action="siteInformationEdit.htm" method="post" id="form_edit">
 <table  class="table table-striped table-bordered table-condensed">
 <tr>
  <td colspan="99">tips:请勿查过250个字节 英文和数字一个占1一个字节 汉字占2个字节[汉字最大长度为127]</td>
 </tr>
 <tr>
  <td>紧急公告</td>
  <td><input type="text" name="urgencyNotice" value="${siteInformation.urgencyNotice }" maxlength="127"/></td>
 </tr>
 <tr>
  <td>公告</td>
  <td><textarea name="notice" maxlength="127">${siteInformation.notice }</textarea></td>
 </tr>
 <tr>
  <td>送餐说明</td>
  <td><textarea name="dispatchExplain" maxlength="127">${siteInformation.dispatchExplain }</textarea></td>
 </tr>
 <tr>
  <td>订餐时间</td>
  <td><textarea name="timeExplain" maxlength="127">${siteInformation.timeExplain }</textarea></td>
 </tr>
 <tr>
  <td>电话</td>
  <td><input type="text" name="phone" value="${siteInformation.phone }" maxlength="127"/></td>
 </tr>
 <tr>
  <td>QQ</td>
  <td><input type="text" name="qq" value="${siteInformation.qq }" maxlength="127"/></td>
 </tr>
 <tr>
  <td>地址</td>
  <td><textarea name="address" maxlength="127">${siteInformation.address }</textarea></td>
 </tr>
 <tr>
  <td>邮编</td>
  <td><input type="text" name="postcode" value="${siteInformation.postcode }" maxlength="127"/></td>
 </tr>
 <tr>
  <td>底部说明</td>
  <td><textarea name="bottomExplain" maxlength="127">${siteInformation.bottomExplain }</textarea></td>
 </tr>
 <tr>
  <td colspan="99" name="textCenter">
   <input type="button" value="更新" class="btn btn-mini btn-primary add" onclick="changeSiteInformation();"/>
  </td>
 </tr>
 </table>
</form>
</center>
</body>
</html>