<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 今日订单列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/order-today.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lodop/LodopFuncs.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
.classHidden {
	display: none;
}
.pager th{
	padding:8px 10px;
}
.pager a{
	border:1px solid #CCC;
	margin:2px 3px;
	padding:3px 6px;
	cursor:pointer;
	background:#efefef;
	color:#333;
	text-decoration:none;
}
.pager a.current{
	cursor:default;
	background:#06C;
	color:#FFF;
}
.pager a:hover{
	background:#06C;
	color:#FFF;
}
.mt{
	margin-top: 0px;
}
</style>
<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width="0" height="0"> 
 <embed id="LODOP_EM" type="application/x-print-lodop" width="0" height="0" pluginspage="install_lodop.exe"></embed>
</object>
</head>
<body>
<div class="alert alert-info">当前位置
 <b class="tip"></b>订单管理
 <b class="tip"></b>今日订单
 <b class="tip"></b>
 <c:forEach items="${buttonList }" var="button">
 <c:if test="${button.code == 1007 }">
  <a href="javascript:openPrint();">打印&统计</a>&nbsp;&nbsp;
 </c:if>
 </c:forEach>
 <a href="javascript:printAll('${pageBean.pageIndex }');" title="批量打印" class="classHidden">预览打印</a>&nbsp;&nbsp;
 <a href="javascript:orderProductCountPrice('${pageBean.pageIndex }');" title="统计" class="classHidden">统计</a>&nbsp;&nbsp;
 <a href="javascript:removeOrderIdCaChe();" title="清空选择缓存 不手动清理 记录将会保持30分钟">清空缓存</a>
</div>
<input type="hidden" id="orderIdSize" value="${orderIdList.size() }"/>
<table class="table table-striped table-bordered table-condensed">
 <tr>
   <th class="classHidden" width="24px;"><input type="checkbox" class="mt" name="orderIdAll" value="${order.id }" onchange="check_all(this,'orderId');"/></th>
   <th>订单编号</th>
   <th>客户名称</th>
   <th>客户号码</th>
   <th>送餐时间</th>
   <th>订单价格</th>
   <th>订单状态</th>
   <th>是否打印</th>
   <th>操作</th>
 </tr>
 <c:if test="${! empty list && list.size() > 0 }">
  <c:forEach items="${list }" var="order">
   <tr class="even">
    <td class="classHidden">
     <input type="checkbox" name="orderId" value="${order.id }" style="margin-top: 0px;"
      <c:forEach items="${orderIdList }" var="orderId">
       <c:if test="${orderId == order.id }">checked</c:if>
      </c:forEach>
     />
    </td>
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
    <td width="18%">
    <c:forEach items="${buttonList }" var="button">
    <c:if test="${button.code == 1008 }">
     <a href="javascript:edit('${order.id }');" title="点击修改" class="btn btn-mini btn-danger">修改</a>&nbsp;
     </c:if>
     <c:if test="${button.code == 1009 }">
      <a href="javascript:del('${order.id }');" title="点击删除" class="btn btn-mini btn-danger">删除</a>&nbsp;
     </c:if>
     <c:if test="${button.code == 1010 }">
      <a href="javascript:detail('${order.id }');" title="点击查看明细" class="btn btn-mini btn-danger">明细</a>&nbsp;
     </c:if>
     <c:if test="${button.code == 1011 }">
      <a href="javascript:printSingle('${order.id }','${pageBean.pageIndex }');" title="预览打印" class="btn btn-mini btn-danger">打印</a>
     </c:if>
     </c:forEach>
    </td>
   </tr>
   <tr class="even" style="color: #FF0033;">
    <td class="classHidden"></td>
    <td>已购买商品</td>
    <td colspan="7">
     <c:forEach items="${order.productList }" var="product" varStatus="index">
      ${product.name }&nbsp;-&nbsp;${product.byNum }份
      <c:if test="${order.productList.size() > 1 && index.index != order.productList.size() - 1}">&nbsp;|&nbsp;</c:if>
     </c:forEach>
    </td>
   </tr>
  </c:forEach>
  <tr class="pager">
  <th colspan="100">
  <b>当前第${pageBean.pageIndex }页/共${pageBean.totalPageCount }页&nbsp;&nbsp;共${pageBean.totalCount }条记录</b>
  &nbsp;&nbsp;
   <a 
    <c:if test="${pageBean.pageIndex > 1}">href="javascript:pager('${pageBean.fromAction}',${pageBean.pageIndex },1);"</c:if>
   >首页</a>&nbsp;
   <a
    <c:if test="${pageBean.pageIndex > 1}">href="javascript:pager('${pageBean.fromAction}',${pageBean.pageIndex },${pageBean.pageIndex - 1 });"</c:if>
   >上一页</a>&nbsp;
   <c:forEach items="${pageBean.pageNums }" var="pageNum">
    <a 
     <c:if test="${pageNum == pageBean.pageIndex }">class="current"</c:if>
     <c:if test="${pageNum != pageBean.pageIndex }">href="javascript:pager('${pageBean.fromAction}',${pageBean.pageIndex },${pageNum});"</c:if>
    >${pageNum }</a>&nbsp;
   </c:forEach>
   <a
    <c:if test="${pageBean.pageIndex < pageBean.totalPageCount}">href="javascript:pager('${pageBean.fromAction}',${pageBean.pageIndex },${pageBean.pageIndex + 1 });"</c:if>
   >下一页</a>&nbsp;
   <a
    <c:if test="${pageBean.pageIndex < pageBean.totalPageCount}">href="javascript:pager('${pageBean.fromAction}',${pageBean.pageIndex },${pageBean.totalPageCount});"</c:if>
   >尾页</a>
  </th>
 </tr>
 </c:if>
 <c:if test="${empty list || list.size() <= 0 }">
  <tr>
   <td colspan="8" name="textCenter">暂无信息</td>
  </tr>
 </c:if>
</table>
</body>
</html>