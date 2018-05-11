<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 商品列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/product-recommend.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>商品管理<b class="tip"></b>推荐商品列表</div>
<form>
<table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>商品编号</th>
   <th>商品名称</th>
   <th>所属分类</th>
   <th>商品状态</th>
   <th>原价</th>
   <th>现价</th>
   <th>库存</th>
   <th width="15%">操作</th>
 </tr>
 <c:if test="${! empty recommendProductList && recommendProductList.size() > 0 }">
  <c:forEach items="${recommendProductList }" var="product">
   <tr class="even">
    <td width="10%">${product.no }</td>
    <td title="${product.name }">${fn:substring(product.name,0,8) }</td>
    <td title="${product.classifyName }">${fn:substring(product.classifyName,0,8) }</td>
    <td>${product.statInfo }</td>
    <td>￥${product.priceOld }</td>
    <td>￥${product.priceNow }</td>
    <td>${product.inventoryInfo }</td>
    <td>
    <%-- <c:forEach items="${buttonList }" var="button">
     <c:if test="${button.code == 1014 }">
      <a href="javascript:recommend('${product.id }',false);" title="取消推荐" class="btn btn-mini btn-danger">取消推荐</a>
     </c:if>
     </c:forEach> --%>
      <input type="button" value="取消推荐" onclick="recommend('${product.id }','false',this);" class="btn btn-mini btn-danger"/>
    </td>
   </tr>
  </c:forEach>
 </c:if>
 <c:if test="${empty recommendProductList || recommendProductList.size() <= 0 }">
  <tr>
   <td colspan="8" name="textCenter">暂无信息</td>
  </tr>
 </c:if>
</table>
</form>
</body>
</html>