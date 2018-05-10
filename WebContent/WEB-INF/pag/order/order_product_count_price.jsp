<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 所选订单明细</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
.colorRed{
	color: red;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单管理<b class="tip"></b>所选订单明细</div>
<table class="table table-striped table-bordered table-condensed">
 <c:if test="${! empty orderStatisticsList && orderStatisticsList.size() > 0 }">
  <tr>
   <th>以下以商品信息为准</th>
  </tr>
  <c:forEach items="${orderStatisticsList }" var="orderStatistics">
  <tr class="even">
   <td>
	    商品编号&nbsp;&nbsp;${orderStatistics.productNo }&nbsp;&nbsp;&nbsp;&nbsp;
	    商品名称&nbsp;&nbsp;${orderStatistics.productName }&nbsp;&nbsp;&nbsp;&nbsp;
	    出售商品件数&nbsp;&nbsp;${orderStatistics.productCount }&nbsp;&nbsp;&nbsp;&nbsp;
	    出售金额 &nbsp;&nbsp;￥${orderStatistics.priceNows }
    </td>
  </tr>
  </c:forEach>
  <tr>
   <th class="colorRed">
   	商品总数&nbsp;&nbsp;${orderStatisticsList.size() }&nbsp;&nbsp;&nbsp;&nbsp;
   	出售总数&nbsp;&nbsp;${byNums }&nbsp;&nbsp;&nbsp;&nbsp;
   	总金额 &nbsp;&nbsp;￥${priceNows }
   </th>
  </tr>
 </c:if>
 <c:if test="${empty orderStatisticsList || orderStatisticsList.size() <= 0 }">
  <tr>
   <td colspan="8" name="textCenter">若看到此信息 数据缓存已经清空 并且没有选中任何订单 若有问题 请首页 点击反馈建议</td>
  </tr>
 </c:if>
</table>
<table class="table table-striped table-bordered table-condensed">
 <c:if test="${! empty orderList && orderList.size() > 0 }">
  <tr>
   <th>以下以订单信息为准</th>
  </tr>
  <c:forEach items="${orderList }" var="order">
   <tr class="even">
    <td colspan="2">
	    订单编号&nbsp;&nbsp;${order.no }&nbsp;&nbsp;&nbsp;&nbsp;
	    订单包含商品件数&nbsp;&nbsp;${order.productCount }&nbsp;&nbsp;&nbsp;&nbsp;
	    出售商品件数&nbsp;&nbsp;${order.byNums }&nbsp;&nbsp;&nbsp;&nbsp;
	    订单金额 &nbsp;&nbsp;￥${order.priceNows }
    </td>
   </tr>
   <tr class="colorRed">
    <td width="10%">已购买商品</td>
    <td>
     <c:forEach items="${order.productList }" var="product" varStatus="index">
      ${product.name }&nbsp;-&nbsp;${product.byNum }份&nbsp;&nbsp;￥${product.priceNows }
      <c:if test="${order.productList.size() > 1 && index.index != order.productList.size() - 1}">&nbsp;|&nbsp;</c:if>
     </c:forEach>
    </td>
   </tr>
  </c:forEach>
  <tr>
   <th colspan="2" class="colorRed">
   	总订单总数&nbsp;&nbsp;${orderList.size() }&nbsp;&nbsp;&nbsp;&nbsp;
   	订单商品总数&nbsp;&nbsp;${productCount }&nbsp;&nbsp;&nbsp;&nbsp;
   	订单非重商品总数&nbsp;&nbsp;${noRepeatProductCount }&nbsp;&nbsp;&nbsp;&nbsp;
   	出售商品总数&nbsp;&nbsp;${byNums }&nbsp;&nbsp;&nbsp;&nbsp;
   	订单总商品金额 &nbsp;&nbsp;￥${priceNows }
   </th>
  </tr>
 </c:if>
</table>
</body>
</html>