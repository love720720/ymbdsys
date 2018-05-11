<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 每日订单查询 导出</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/order-search-excel.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
.input-append {
	float: left;
}
.input-append input[type='text']{
	width: 80px;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单数据<b class="tip"></b>订单查询导出</div>
<form action="searchOrder.htm" method="post" id="searchForm">
 <table class="table table-striped table-bordered table-condensed">
  <tr>
   <td>
    <div class="input-append">
     <input type="text" readonly="readonly" id="searchStartTime" name="searchStartTime" value="${searchStartTime }"/><span class="add-on"><i class="icon-calendar"></i></span>-
    </div>
    <div class="input-append">
     <input type="text" readonly="readonly" id="searchEndTime" name="searchEndTime" value="${searchEndTime }"/><span class="add-on"><i class="icon-calendar"></i></span>
    </div>&nbsp;
    <c:forEach items="${buttonList }" var="button">
     <c:if test="${button.code == 1003 }">
      <input type="button" value="查询" class="btn btn-mini btn-primary add" id="search"/>
     </c:if>
     <c:if test="${button.code == 1004 }">
      <input type="button" value="导出excel" class="btn btn-mini btn-primary add" id="excel"/>
     </c:if>
    </c:forEach>
   </td>
  </tr>
 </table>
</form>
<c:if test="${! empty list && list.size() > 0 }">
 <b>订单数量:${list.size() }</b>
 <table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>订单编号</th>
   <th>客户名称</th>
   <th>客户号码</th>
   <th>送餐时间</th>
   <th>订单价格</th>
 </tr>
  <c:forEach items="${list }" var="order">
   <tr class="even">
    <td width="12%">${order.no }</td>
    <td title="${order.userName }">${fn:substring(order.userName,0,15) }</td>
    <td>${order.phone }</td>
    <td>${order.sendTime }</td>
    <td>￥${order.priceNows }</td>
   </tr>
  </c:forEach>
 </table>
</c:if>
<c:if test="${! empty message }">
 <table class="table table-striped table-bordered table-condensed">
 <tr>
   <td name="textCenter">${message }</td>
 </tr>
</table>
</c:if>
</body>
</html>