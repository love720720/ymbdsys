<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 商品信息设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/product-edit.js"></script>
<style type="text/css">
.width_400{
	width: 400px;
}
.width_184{
	width: 184px;
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>商品管理<b class="tip"></b>商品<c:if test="${empty product }">添加</c:if><c:if test="${! empty product && product.id > 0}">修改</c:if></div>
<center>
 <form action="editProduct.htm" method="post" id="form_edit" enctype="multipart/form-data">
 <input type="hidden" id="id" name="id" value="${product.id }"/>
 <table class="width_400 table table-striped table-bordered table-condensed list" >
  <tr>
   <td>商品名称</td>
   <td><input type="text" id="name" name="name" maxlength="20" value="${product.name }"/></td>
  </tr>
  <tr>
   <td>商品分类</td>
   <td>
    <select id="classifyId" name="classifyId">
     <option value="">--请选择分类--&nbsp;&nbsp;</option>
      <c:forEach items="${classifyList }" var="classify">
      <option value="${classify.id }"
       <c:if test="${product.classifyId == classify.id }">selected</c:if>
      >${classify.name }</option>
      </c:forEach>
    </select>
   </td>
  </tr>
  <tr>
   <td>商品状态</td>
   <td>
    <select id="stat" name="stat">
     <option value="">--请选择状态--&nbsp;&nbsp;</option>
      <c:forEach items="${stats }" var="stat" varStatus="index">
      <option value="${index.index }"
       <c:if test="${product.stat == index.index }">selected</c:if>
      >${stat }</option>
      </c:forEach>
    </select>
   </td>
  </tr>
  <tr>
   <td>上架时间</td>
   <td>
    <div class="input-append">
     <input class="width_184 ipt datepicker" type="text" id="shelvesTime" name="shelvesTime" value="${fn:substring(product.shelvesTimeInfo,0,10) }" readonly="readonly"/><span class="add-on"><i class="icon-calendar"></i></span>
    </div>
   </td>
  </tr>
  <tr>
   <td>商品原价</td>
   <td><input type="text" id="priceOld" name="priceOld" maxlength="8" value="${product.priceOld }" onkeyup="this.value=this.value.replace(/[^\.\d]/g,'')"/></td>
  </tr>
  <tr>
   <td>商品现价</td>
   <td><input type="text" id="priceNow" name="priceNow" maxlength="8" value="${product.priceNow }" onkeyup="this.value=this.value.replace(/[^\.\d]/g,'')"/></td>
  </tr>
  <tr>
   <td>商品库存</td>
   <td><input type="text" id="inventory" name="inventory" maxlength="8" value="${product.inventory }"/></td>
  </tr>
  <tr>
   <td>推荐</td>
   <td>
    <input type="radio" name="recommend" value="0"
     <c:if test="${empty product || product.recommend == 0 }">checked</c:if>
    />不推荐&nbsp;&nbsp;
    <input type="radio" name="recommend" value="1"
     <c:if test="${product.recommend == 1 }">checked</c:if>
    />推荐
   </td>
  </tr>
  <tr>
   <th colspan="2">暂时不支持上传图片[未知的服务器路径]</th>
  </tr>
  <tr>
   <td>商品配图</td>
   <td><input type="file" name="file" disabled="disabled"/></td>
  </tr>
  <tr>
   <td colspan="2" name="textCenter">
    <input type="button" value="提交" id="sub" class="btn btn-mini btn-primary add"/>&nbsp;
    <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
   </td>
  </tr>
 </table>
 </form>
</center>
</body>
</html>