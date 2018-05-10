<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 ${sessionScope.STAFF.name }</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/staff-password.js"></script>
<style type="text/css">
.width_320{
	width: 320px;
}
.colorRed{
	color: red;
}
.lab{
	display: inline-block;
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>个人中心<b class="tip"></b>密码修改</div>
<center id="tableCenter">
<table class="width_320 table table-striped table-bordered table-condensed" id="passwordTable">
 <tr>
  <th class="colorRed" colspan="2">注:请勿输入特殊字符[密码长度6-16]</th>
 </tr>
 <tr>
   <th>原始密码</th>
   <td><input type="password" id="oldPassword" name="oldPassword" maxlength="16"/></td>
 </tr>
 <tr>
   <th>新密码</th>
   <td><input type="password" id="newPassword" name="newPassword" maxlength="16"/></td>
 </tr>
 <tr>
   <th>确认密码</th>
   <td><input type="password" id="againPassword" name="againPassword" maxlength="16"/></td>
 </tr>
 <tr>
  <td colspan="2">
   <input type="checkbox" id="checkboxPassword" onclick="showPassword();"/><label class="lab" for="checkboxPassword">显示密码</label>
  </td>
 </tr>
 <tr>
  <td colspan="2" name="textCenter">
   <input type="button" value="提交" onclick="sub();" class="btn btn-mini btn-primary add"/>&nbsp;
   <input type="reset" class="btn btn-mini btn-primary add"/>
  </td>
 </tr>
</table>
</center>
</body>
</html>