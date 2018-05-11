<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 订单明细</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/order-detail.js"></script>
<style type="text/css">
.colorRed{
	color: red;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单管理<b class="tip"></b>订单明细</div>
 <table class="table table-striped table-bordered table-condensed">
  <tr>
   <td width="15%">订单编号</td>
   <td>${order.no }</td>
   <td>下单时间</td>
   <td>${fn:substring(order.createTimeInfo,0,19) }</td>
  </tr>
  <tr>
   <td>客户名称</td>
   <td>${order.userName }</td>
   <td>客户号码</td>
   <td>${order.phone }</td>
  </tr>
  <tr>
   <td>客户地址</td>
   <td colspan="3">${order.address }</td>
  </tr>
  <tr>
   <td>订单原价</td>
   <td>￥${order.priceOlds }</td>
   <td>订单现价</td>
   <td>￥${order.priceNows }</td>
  </tr>
  <tr>
   <td>订单折扣</td>
   <td>${order.discount }</td>
   <td>订单风味</td>
   <td>${order.taste }</td>
  </tr>
  <tr>
   <td>订单状态</td>
   <td>${order.statInfo }</td>
   <td>生成方式</td>
   <td>${order.flagInfo }</td>
  </tr>
  <tr>
   <td>订单备注</td>
   <td colspan="3">${order.remark }</td>
  </tr>
  <tr>
  <td colspan="4">
    <c:choose>
     <c:when test="${order.createTime == order.updateTime }">暂未修改</c:when>
     <c:otherwise>上次修改时间&nbsp;&nbsp;&nbsp;${fn:substring(order.updateTimeInfo,0,19) }</c:otherwise>
    </c:choose>
   </td>
  </tr>
  <c:if test="${order.createTime != order.updateTime && ! empty order && order.record.length() > 0 }">
   <tr>
    <td colspan="4">${order.record }</td>
   </tr>
  </c:if>
 </table>
 <table class="table table-striped table-bordered table-condensed">
  <tr>
   <td colspan="6"><b class="colorRed">以下是该订单中购买商品信息[商品明细请点击商品名称]</b></td>
  </tr>
  <c:if test="${! empty productList && productList.size() > 0}">
  <c:forEach items="${productList }" var="product" varStatus="index">
  <tr>
   <td>${index.index + 1 }.&nbsp;</td>
   <td>商品名称：<a href="javascript:detail('${product.id }');">${product.name }</a>&nbsp;&nbsp;</td>
   <td>商品原价：￥${product.priceOld }&nbsp;&nbsp;</td>
   <td>商品现价：￥${product.priceNow }&nbsp;&nbsp;</td>
   <td>购买件数：${product.byNum }&nbsp;&nbsp;</td>
   <td>总金额：￥${product.priceNows }&nbsp;&nbsp;</td>
  </tr>
  </c:forEach>
  </c:if>
  <c:if test="${empty productList || productList.size() <= 0}">
  <tr>
   <td colspan="6">未查找到数据 原因：1.商品已经被删除&nbsp;&nbsp;2.生成订单出错</td>
  </tr>
  <tr>
   <td colspan="6">提示：若订单还未生效&nbsp;商品依然存在&nbsp;建议删除该订单重新生成</td>
  </tr>
  </c:if>
 </table>
</body>
</html>