<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 角色设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/role-edit.js"></script>
<style type="text/css">
.width_300{
	width: 300px
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>角色管理<b class="tip"></b><c:if test="${empty role }">角色添加</c:if><c:if test="${! empty role && role.id > 0}">角色修改</c:if></div>
<center>
 <form method="post" action="editRole.htm" id="form_edit">
  <input type="hidden" id="id" name="id" value="${role.id }"/>
  <table class="width_300 table table-striped table-bordered table-condensed">
   <tr>
    <th width="22%">名称</th>
	<td><input type="text" id="name" name="name" maxlength="20" value="${role.name }"/></td>
   </tr>
   <tr>
    <th>等级</th>
	<td>
	 <select id="level" name="level">
	  <option value="">--请指定角色等级--&nbsp;&nbsp;</option>
	  <c:forEach items="${roleLevels }" var="level" varStatus="index">
	   <option value="${index.index }"
	    <c:if test="${role.level == index.index }">selected</c:if>
	   >${level }</option>
	  </c:forEach>
	 </select>
	</td>
   </tr>
   <tr>
	<th>排序</th>
	<td><input type="text" id="sort" name="sort" maxlength="3" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" value="${role.sort }"/></td>
   </tr>
   <tr>
    <td colspan="2" name="textCenter">
	 <input type="button" value="提交" onclick="sub();" class="btn btn-mini btn-primary add"/>&nbsp;
	 <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
	</td>
   </tr>
  </table>
 </form>
</center>
</body>
</html>