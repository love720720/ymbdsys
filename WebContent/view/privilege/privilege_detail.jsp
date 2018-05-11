<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 权限明细</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/privilege-detail.js"></script>
<style type="text/css">
th[name="textCenter"]{
	text-align: center;
	color: red;
}
.width_600{
	width: 600px;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>权限管理<b class="tip"></b>权限明细</div>
<center>
<table class="width_600 table table-striped table-bordered table-condensed">
 <tr>
  <th>权限名称</th>
  <td>${privilege.name }</td>
  <th>排序</th>
  <td>${privilege.sort }</td>
  <th>display</th>
  <td>
   <c:if test="${privilege.display }">显示</c:if>
   <c:if test="${!privilege.display }">隐藏</c:if>
  </td>
 </tr>
 <c:if test="${privilege.isTree }">
 <tr>
  <td colspan="6">
        左侧树权限
   <c:if test="${! empty privilege.url && privilege.url.length() > 0 }">&nbsp;&nbsp;&nbsp;权限链接:<a href="${pageContext.request.contextPath }/${privilege.url }" target="_blank">${privilege.url }</a>&nbsp;&nbsp;&nbsp;打开方式:${privilege.target }</c:if>
  </td>
 </tr>
 </c:if>
 <c:if test="${privilege.isButton }">
 <tr>
  <td colspan="6">按钮权限&nbsp;&nbsp;&nbsp;code:${privilege.code }<c:if test="${! empty privilege.url && privilege.url.length() > 0 }">&nbsp;&nbsp;&nbsp;权限链接:<a href="${pageContext.request.contextPath }/${privilege.url }" target="_blank">${privilege.url }</a>&nbsp;&nbsp;&nbsp;打开方式:${privilege.target }</c:if></td>
 </tr>
 </c:if>
 <tr>
  <th colspan="6" name="textCenter">子权限信息</th>
 </tr>
 <c:if test="${empty privilege.privilegeList || privilege.privilegeList.size() <= 0 }">
 <tr>
  <th colspan="6">当前为最低权限 无子权限信息</th>
 </tr>
 </c:if>
 <c:if test="${! empty privilege.privilegeList && privilege.privilegeList.size() > 0 }">
 <c:forEach items="${privilege.privilegeList }" var="p">
  <tr>
   <th>权限名称</th>
   <td><a href="javascript:detail('${p.id }');">${p.name }</a></td>
   <th>排序</th>
   <td>${p.sort }</td>
   <th>display</th>
   <td>
    <c:if test="${privilege.display }">显示</c:if>
    <c:if test="${!privilege.display }">隐藏</c:if>
   </td>
  </tr>
  <c:if test="${p.isTree }">
  <tr>
   <td colspan="6">
          左侧树权限
    <c:if test="${! empty p.url && p.url.length() > 0 }">&nbsp;&nbsp;&nbsp;权限链接:<a href="${pageContext.request.contextPath }/${p.url }" target="_blank">${p.url }</a>&nbsp;&nbsp;&nbsp;打开方式:${p.target }</c:if>
   </td>
  </tr>
  </c:if>
  <c:if test="${p.isButton }">
  <tr>
   <td colspan="6">按钮权限&nbsp;&nbsp;&nbsp;code:${p.code }<c:if test="${! empty privilege.url && privilege.url.length() > 0 }">&nbsp;&nbsp;&nbsp;权限链接:<a href="${pageContext.request.contextPath }/${privilege.url }" target="_blank">${privilege.url }</a>&nbsp;&nbsp;&nbsp;打开方式:${privilege.target }</c:if></td>
  </tr>
  </c:if>
 </c:forEach>
 </c:if>
</table>
</center>
</body>
</html>