<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 角色列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/role-list.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>角色管理<b class="tip"></b>角色列表</div>
 <table class="table table-striped table-bordered table-condensed">
  <tr>
   <th>名称</th>
   <th>等级</th>
   <th>排序</th>
   <th>操作</th>
  </tr>
  <c:if test="${! empty list && list.size() > 0 }">
  <c:forEach items="${list }" var="role">
  <tr>
   <td>${role.name }</td>
   <td>${role.levelInfo }</td>
   <td>第${role.sort }位</td>
   <td>
    <c:forEach items="${buttonList }" var="button">
    <c:if test="${button.code == 1018 }">
     <a href="javascript:edit('${role.id }');" title="点击修改" class="btn btn-mini btn-danger">修改</a>&nbsp;
    </c:if>
    <c:if test="${button.code == 1019 }">
     <a href="javascript:del('${role.id }');" title="点击删除" class="btn btn-mini btn-danger">删除</a>&nbsp;
    </c:if>
    <c:if test="${button.code == 1020 }">
     <a href="javascript:manageRole('${role.id }');" title="点击管理权限" class="btn btn-mini btn-danger">管理</a>
    </c:if>
   </c:forEach>
   </td>
  </tr>
  </c:forEach>
  <jsp:include page="../page.jsp"></jsp:include>
  </c:if>
  <c:if test="${empty list || list.size() <= 0 }">
  <tr><td colspan="4" name="textCenter">暂无信息</td></tr>
  </c:if>
 </table>
</body>
</html>