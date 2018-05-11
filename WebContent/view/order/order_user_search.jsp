<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 用户订单查询</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select2/select2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2/select2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/order-user-search.js"></script>
<style type="text/css">
.width_300{
	width: 300px;
}
.colorRed {
	color: #FF0033;
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单查询<b class="tip"></b>客户订单查询</div>
<input type="hidden" id="select_Id" class="width_300"/>
<br/>
<br/>
<c:if test="${! empty list && list.size() > 0 }">
 <table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>订单编号</th>
   <th>客户名称</th>
   <th>客户号码</th>
   <th>送餐时间</th>
   <th>订单价格</th>
   <th>订单状态</th>
   <th>是否打印</th>
   <th>操作</th>
 </tr>
  <c:forEach items="${list }" var="order">
   <tr class="even">
    <td width="10%">${order.no }</td>
    <td title="${order.userName }">${fn:substring(order.userName,0,8) }</td>
    <td>${order.phone }</td>
    <td>${order.sendTime }</td>
    <td>￥${order.priceNows }</td>
    <td>${order.statInfo }</td>
    <td>
     <c:choose>
      <c:when test="${order.printNum <= 0 }"><font color="#FF0033">未打印</font></c:when>
      <c:otherwise>print[<font color="#FF0033">${order.printNum }</font>]</c:otherwise>
     </c:choose>
    </td>
    <td width="6%">
     <a href="javascript:detail('${order.id }');" title="点击查看明细" class="btn btn-mini btn-danger">明细</a>&nbsp;
    </td>
   </tr>
   <tr class="colorRed even">
    <td>已购买商品</td>
    <td colspan="8">
     <c:forEach items="${order.productList }" var="product" varStatus="index">
      ${product.name }&nbsp;-&nbsp;${product.byNum }份
      <c:if test="${order.productList.size() > 1 && index.index != order.productList.size() - 1}">&nbsp;|&nbsp;</c:if>
     </c:forEach>
    </td>
   </tr>
  </c:forEach>
</table>
</c:if>
<c:if test="${! empty message }">
 <table class="table table-striped table-bordered table-condensed">
 <tr>
   <td class="textCenter">${message }</td>
 </tr>
</table>
</c:if>
</body>
</html>