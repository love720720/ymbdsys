<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 风味列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="|${pageContext.request.contextPath }/js/page/taste-list.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>风味管理<b class="tip"></b>风味列表</div>
 <table class="table table-striped table-bordered table-condensed">
  <tr>
   <th>名称</th>
   <th>排序</th>
   <th>操作</th>
  </tr>
  <c:if test="${! empty list && list.size() > 0 }">
  <c:forEach items="${list }" var="taste">
  <tr>
   <td>${taste.name }</td>
   <td>第${taste.sort }位</td>
   <td>
   <c:forEach items="${buttonList }" var="button">
    <c:if test="${button.code == 1023 }">
     <a href="javascript:edit('${taste.id }');" title="点击修改" class="btn btn-mini btn-danger">修改</a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${button.code == 1024 }">
     <a href="javascript:del('${taste.id }');" title="点击删除" class="btn btn-mini btn-danger">删除</a>
    </c:if>
   </c:forEach>
   </td>
  </tr>
  </c:forEach>
  </c:if>
  <c:if test="${empty list || list.size() <= 0 }">
  <tr><td colspan="3" name="textCenter">暂无信息</td></tr>
  </c:if>
 </table>
</body>
</html>