<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 商品明细</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/potbig/cloud-zoom.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/page/product.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/potbig/cloud-zoom.1.0.2.min.js"></script>
<style type="text/css">
.float {
 	float: left;
 	border: 2px solid black;
 }
 .float img {
 	width: 300px;
 	width: 300px;
 }
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>商品管理<b class="tip"></b>商品明细</div>
 <c:if test="${product.imageFlag == IMAGE1 }">
  <div class="zoom-small-image float">
   <a href="showImage.htm?fileName=${product.imageFileName }" class="cloud-zoom" id="zoom1" rel="adjustX:10, adjustY:-4"><img src="showImage.htm?fileName=${product.imageFileName }" title="${product.imageName }" /></a></div>
  <div class="zoom-desc">
 </c:if>
 <table class="table table-striped table-bordered table-condensed">
  <tr>
   <td width="15%">商品编号</td>
   <td>${product.no }</td>
   <td width="15%">商品名称</td>
   <td>${product.name }</td>
  </tr>
  <tr>
   <td>创建时间</td>
   <td>${fn:substring(product.createTimeInfo,0,19) }</td>
   <td>上架时间</td>
   <td>${fn:substring(product.shelvesTimeInfo,0,10) }</td>
  </tr>
  <tr>
   <td>商品分类</td>
   <td>${product.classifyName }</td>
   <td>商品状态</td>
   <td>${product.statInfo }</td>
  </tr>
  <tr>
   <td>商品原价</td>
   <td>￥${product.priceOld }</td>
   <td>商品现价</td>
   <td>￥${product.priceNow }</td>
  </tr>
  <tr>
   <td>商品库存</td>
   <td colspan="1">${product.inventoryInfo }</td>
   <td colspan="2">
    <c:choose>
     <c:when test="${product.createTime == product.updateTime }">暂未修改</c:when>
     <c:otherwise>上次修改时间&nbsp;&nbsp;&nbsp;${fn:substring(product.updateTimeInfo,0,19) }</c:otherwise>
    </c:choose>
   </td>
  </tr>
  <c:if test="${product.createTime != product.updateTime && ! empty product && product.record.length() > 0 }">
   <tr>
    <td colspan="4">${product.record }</td>
   </tr>
  </c:if>
 </table>
</body>
</html>