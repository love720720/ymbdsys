<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 权限设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/privilege-edit.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
.width_400{
	width: 400px;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>权限管理<b class="tip"></b>权限设置</div>
<center>
<form action="editPrivilege.htm" method="post" id="form_edit">
<input type="hidden" name="id" value="${privilege.id }"/>
<input type="hidden" name="pid" value="${pid }"/>
<table class="width_400 table table-striped table-bordered table-condensed">
 <tr>
  <td>权限名称</td>
  <td><input type="text" id="name" name="name" value="${privilege.name }" maxlength="20"/></td>
 </tr>
 <tr>
  <td>权限类型</td>
  <td>
   <input type="radio" name="type" value="tree"
    <c:if test="${privilege.isTree }">checked</c:if>
   />树权限&nbsp;&nbsp;
   <input type="radio" name="type" value="button"
    <c:if test="${privilege.isButton }">checked</c:if>
   />按钮权限
  </td>
 </tr>
 <tr>
  <td>url</td>
  <td><input type="text" name="url" value="${privilege.url }" maxlength="200"/></td>
 </tr>
 <tr>
  <td>打开方式</td>
  <td>
   <input type="radio" name="target" value="conframe" checked="checked"
    <c:if test="${privilege.target == 'conframe' }">checked</c:if>
   />当前窗口打开&nbsp;&nbsp;
   <input type="radio" name="target" value="_blank"
    <c:if test="${privilege.target == '_blank' }">checked</c:if>
   />新窗口打开
  </td>
 </tr>
 <tr>
  <td>隐藏${privilege.display }</td>
  <td>
   <input type="radio" name="display" value="true" checked="checked"
    <c:if test="${! empty privilege && privilege.display}">checked</c:if>
   />显示&nbsp;&nbsp;
   <input type="radio" name="display" value="false"
    <c:if test="${! empty privilege && !privilege.display }">checked</c:if>
   />隐藏
  </td>
 </tr>
 <tr>
  <td>code</td>
  <td><input type="text" id="code" name="code" value="${privilege.code }"/></td>
 </tr>
 <tr>
  <td>排序</td>
  <td><input type="text" name="sort" value="${privilege.sort }" maxlength="3"/></td>
 </tr>
 <tr>
  <td name="textCenter" colspan="2">
   <input type="button" value="提交" onclick="sub();" class="btn btn-mini btn-primary add"/>&nbsp;&nbsp;
   <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
  </td>
 </tr>
</table>
</form>
</center>
</body>
</html>