<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 订单列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/order-list.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单管理<b class="tip"></b>订单列表</div>
<table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>订单编号</th>
   <th>客户名称</th>
   <th>客户号码</th>
   <th>风味</th>
   <th>送餐时间</th>
   <th>订单价格</th>
   <th>订单状态</th>
   <th>操作</th>
 </tr>
 <c:if test="${! empty list && list.size() > 0 }">
  <c:forEach items="${list }" var="order">
   <tr class="even">
    <td width="10%">${order.no }</td>
    <td title="${order.userName }">${fn:substring(order.userName,0,8) }</td>
    <td>${order.phone }</td>
    <td>${order.taste }</td>
    <td>${order.sendTime }</td>
    <td>￥${order.priceNows }</td>
    <td>${order.statInfo }</td>
    <td width="10%">
     <a href="javascript:detail('${order.id }');" title="点击查看明细" class="btn btn-mini btn-danger">明细</a>
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