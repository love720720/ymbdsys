<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 后台用户个人中心</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>个人中心</div>
<table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>姓名</th>
   <td>${staffInfo.name }</td>
   <th>登陆账号</th>
   <td>${staffInfo.userName }</td>
 </tr>
 <tr>
   <th>性别</th>
   <td>${staffInfo.genderInfo }</td>
   <th>登陆密码</th>
   <td><a href="password.htm" target="conframe">*********</a></td>
 </tr>
 <tr>
   <th>电话</th>
   <td>${staffInfo.phone }</td>
   <th>注册时间</th>
   <td>${fn:substring(staffInfo.registerTimeStr,0,19) }</td>
 </tr>
 <tr>
   <th>登陆次数</th>
   <td>${staffInfo.loginNum }</td>
   <th>上次登陆时间</th>
   <td>${fn:substring(staffInfo.loginTimeStr,0,19) }</td>
 </tr>
 <tr>
   <td colspan="2"></td>
   <th>本次登陆时间</th>
   <td>${fn:substring(staffInfo.lastLoginTimeStr,0,19) }</td>
 </tr>
</table>
</body>
</html>