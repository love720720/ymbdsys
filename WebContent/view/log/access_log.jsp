<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 accessLog</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>log<b class="tip"></b>accessLog<b class="tip"></b><a href="clearAccessLog.htm">清空 accessLog</a></div>
<table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>时间</th>
   <th>url</th>
   <th>ip</th>
   <th>操作者</th>
 </tr>
 <c:if test="${! empty list && list.size() > 0 }">
  <c:forEach items="${list }" var="accessLog">
   <tr class="even">
    <td>${fn:substring(accessLog.timeInfo,0,19) }</td>
    <td>${accessLog.url }</td>
    <td>${accessLog.ip }</td>
    <td>
     <c:if test="${accessLog.userId > 0 }">
      <a href="">${accessLog.userName }</a>
     </c:if>
     <c:if test="${accessLog.userId <= 0 }">${accessLog.userName }</c:if>
    </td>
   </tr>
  </c:forEach>
   <jsp:include page="../page.jsp"></jsp:include>
 </c:if>
 <c:if test="${empty list || list.size() <= 0 }">
  <tr>
   <td colspan="8" name="textCenter">暂无信息</td>
  </tr>
 </c:if>
</table>
</body>
</html>