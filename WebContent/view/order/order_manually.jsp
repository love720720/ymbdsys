<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 手动订单</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select2/select2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2/select2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/order-manually.js"></script>
<style type="text/css">
.width_23{
	width: 23px;
}
.width_220{
	width: 220px;
}
.width_240{
	width: 240px;
}
.width_450{
	width: 450px;
}
td[name="textCenter"]{
	text-align: center;
}
.displayNone{
	display: none;
}
textarea{
	width: 260px;
	height: 100px;
	resize:none;
}
td[name="lh_100"]{
	line-height: 100px
}
.div_productInfo{
	width:236px;
	float: left;
}
.lab{
	display: inline-block;
}
.floatLeft{
	float: left;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>订单管理<b class="tip"></b><c:if test="${empty order }">手动添加订单</c:if><c:if test="${! empty order }">手动修改订单</c:if></div>
<center>
<form action="manuallyOrder.htm" method="post">
 <input type="hidden" id="id" name="id" value="${order.id }"/>
 <input type="text" id="userId" name="userId" value="${user.id }"/>
 <input type="hidden" id="productIds" name="productIds" value="${productIds }"/>
 <table class="width_450 table table-striped table-bordered table-condensed list">
  <tr>
   <td>客户名称</td>
   <td>
   <c:if test="${! empty order }">
    <input type="text" id="userName" name="userName" maxlength="20" value="${order.userName }"/>
   </c:if>
   <c:if test="${empty order }">
	<input type="hidden" id="userName_Id" class="width_220"/>
    <input type="hidden" id="userName" name="userName" maxlength="20"/>
   </c:if>
   </td>
  </tr>
   <td>客户号码</td>
   <td><input type="text" id="phone" name="phone" maxlength="14" value="${order.phone }"/></td>
  </tr>
  <tr>
   <td name="lh_100">送餐地址</td>
   <td>
    <textarea id="address" name="address" maxlength="200" class="width_260 height_50">${order.address }</textarea>
   </td>
  </tr>
  <tr>
   <td>送餐时间</td>
   <td>
    <select id="sendTime" name="sendTime">
     <option value="正常">正常[默认]&nbsp;&nbsp;</option>
      <c:forEach items="${sendTimes }" var="send">
      <option value="${send }"
       <c:if test="${send == order.sendTime }">selected</c:if>
      >${send }</option>
      </c:forEach>
    </select>
   </td>
  </tr>
  <tr>
   <td>风味选择</td>
   <td>
    <select id="taste" name="taste">
     <option value="正常">正常[默认]</option>
      <c:forEach items="${tasteList }" var="taste">
      <option value="${taste.name }"
       <c:if test="${taste.name == order.taste }">selected</c:if>
      >${taste.name }</option>
      </c:forEach>
    </select>
   </td>
  </tr>
  <c:if test="${! empty todayProductList && todayProductList.size() > 0 }">
  <tr>
   <td>今日商品</td>
   <td>
    <select id="todayProductId">
     <c:forEach items="${todayProductList }" var="product">
      <option value="${product.id }">${product.name }</option>
     </c:forEach>
    </select>
    <input type="button" value="购买" onclick="buyTodayProduct();"/>
   </td>
  </tr>
  </c:if>
  <tr>
   <td>选择购买商品</td>
   <td><input type="hidden" id="product_Id" class="width_220"/></td>
  </tr>
  <tr id="productView">
   <td id="product" colspan="2">
    <c:forEach items="${productList }" var="product">
    <div>
     <div class="floatLeft width_240">
     ${product.name }&nbsp;&nbsp;￥${product.priceNow }</div>&nbsp;
	 <input type="hidden" id="product_${product.id }" name="idByNum" value="${product.id }_${product.byNum }"/><input type="button" value="↑" onclick="addNum('${product.id }');" class="btn btn-mini btn-primary add"/>&nbsp;<input type="text" id="text_${product.id }" value="${product.byNum }" onblur="confirmNum('${product.id }');" class="width_23"/>&nbsp;<input type="button" value="↓" onclick="redNum('${product.id }');" class="btn btn-mini btn-primary add"/>
	</div>
    </c:forEach>
   </td>
  </tr>
  <tr>
   <td name="lh_100">备注</td>
   <td>
    <textarea id="remark" name="remark" maxlength="100">${order.remark }</textarea>
   </td>
  </tr>
  <tr>
   <td colspan="2" name="textCenter">
    <input type="button" value="<c:if test="${empty order }">生成订单</c:if><c:if test="${! empty order }">修改订单</c:if>" onclick="createOrder();" class="btn btn-mini btn-primary add"/>&nbsp;
    <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
   </td>
  </tr>
 </table>
 </form>
</center>
</body>
</html>