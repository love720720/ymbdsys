<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 后台用户设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/staff-edit.js"></script>
<style type="text/css">
.width_320{
	width: 320px;
}
.colorRed{
	color: red;
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>成员管理<b class="tip"></b>后台用户<b class="tip"></b>添加用户</div>
<center>
<table class="width_320 table table-striped table-bordered table-condensed">
 <tr>
  <th class="colorRed" colspan="2">注:请输入真实姓名[姓名只能为汉字和数字的组合]</th>
 </tr>
 <tr>
   <th>姓名</th>
   <td><input type="text" id="name" name="name" maxlength="10" onchange="changeName();"/></td>
 </tr>
 <tr>
   <th>登陆账号</th>
   <td><input type="text" id="userName" name="userName" disabled="disabled"/></td>
 </tr>
 <tr>
   <th>性别</th>
   <td>
    <select id="gender" name="gender">
     <c:forEach items="${genders }" var="gender" varStatus="index">
      <option value="${index.index }">${gender }</option>
     </c:forEach>
    </select>
   </td>
 </tr>
 <tr>
   <th>电话</th>
   <td><input type="text" id="phone" name="phone" maxlength="15"/></td>
 </tr>
 <tr>
  <th class="colorRed" colspan="2">注:若填写邮箱 随机密码将发送到该邮箱</th>
 </tr>
 <tr>
   <th>联系邮箱</th>
   <td><input type="text" id="email" name="email" maxlength="100"/></td>
 </tr>
 <tr>
  <td colspan="2" name="textCenter">
   <input type="button" value="提交" onclick="sub();" class="btn btn-mini btn-primary add"/>&nbsp;&nbsp;
   <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
  </td>
 </tr>
</table>
</center>
</body>
</html>