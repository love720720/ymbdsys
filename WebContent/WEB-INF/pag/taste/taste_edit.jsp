<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 风味设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/taste-edit.js"></script>
<style type="text/css">
.width_300{
	width: 300px;
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>风味管理<b class="tip"></b><c:if test="${empty taste }">风味添加</c:if><c:if test="${! empty taste && taste.id > 0}">风味修改</c:if></div>
<center>
 <form method="post" action="editTaste.htm" id="form_edit">
  <input type="hidden" name="id" value="${taste.id }"/>
  <table class="width_300 table table-striped table-bordered table-condensed">
   <tr>
    <th width="22%">名称</th>
	<td><input type="text" id="name" name="name" value="${taste.name }"/></td>
   </tr>
	<tr>
	<th>排序</th>
	<td><input type="text" id="sort" name="sort" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" value="${taste.sort }"/></td>
   </tr>
   <tr>
	<td colspan="2" name="textCenter">
	 <input type="button" value="提交" onclick="sub();" class="btn btn-mini btn-primary add" />&nbsp;
	 <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
	</td>
   </tr>
  </table>
 </form>
</center>
</body>
</html>