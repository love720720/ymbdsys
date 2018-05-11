<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 订单数据统计</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/order-statistics-detail.js"></script>
<style type="text/css">
.input-append {
	float: left;
}
.input-append input[type='text']{
	width: 80px;
}
table caption{
	color:red;
	font-weight: bold;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单数据<b class="tip"></b>订单商品统计</div>
<form action="orderStatisticsDetail.htm" method="post" id="searchForm">
 <input type="hidden" id="param" name="param" value="-1"/>
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
     <c:if test="${button.code == 1005 }">
      <input type="button" value="查询" class="btn btn-mini btn-primary add" id="search"/>
     </c:if>
     <c:if test="${button.code == 1006 }">
      <input type="button" value="导出excel" class="btn btn-mini btn-primary add" id="excel"/>
     </c:if>
    </c:forEach>
   </td>
  </tr>
 </table>
</form>
<c:choose>
 <c:when test="${! empty orderStatisticsList && orderStatisticsList.size() > 0 }">
 <table class="table table-striped table-bordered table-condensed">
  <caption>${searchStartTime }&nbsp;至&nbsp;${searchEndTime }</caption>
  <tr>
   <th colspan="4">
    <input type="button" value="今日订单" class="btn btn-mini btn-primary add" id="today"/>
    <input type="button" value="昨日订单" class="btn btn-mini btn-primary add" id="yesterday"/>
    <input type="button" value="一周订单" class="btn btn-mini btn-primary add" id="weekday"/>
    <input type="button" value="一月订单" class="btn btn-mini btn-primary add" id="month"/>
   </th>
  </tr>
  <tr>
   <th colspan="4">
    <b>订单商品总数&nbsp;&nbsp;${productCounts }&nbsp;&nbsp;订单商品总金额&nbsp;&nbsp;￥${priceNows }</b>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   </th>
  </tr>
  <tr>
   <td>商品编号</td>
   <td>商品名称</td>
   <td>订单商品个数</td>
   <td>订单商品金额</td>
  </tr>
  <c:forEach items="${orderStatisticsList }" var="orderStatistics">
  <tr>
   <td>${orderStatistics.productNo }</td>
   <td>${orderStatistics.productName }</td>
   <td>${orderStatistics.productCount }</td>
   <td>￥${orderStatistics.priceNows }</td>
  </tr>
  </c:forEach>
 </table>
 </c:when>
 <c:otherwise>
  <table class="table table-striped table-bordered table-condensed">
   <caption>${searchStartTime }&nbsp;至&nbsp;${searchEndTime }[无数据]</caption>
  </table>
 </c:otherwise>
</c:choose>
</body>
</html>