<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 后台用户列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/page/staff.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/staff-list.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
td input{
	width: 26px;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>成员管理<b class="tip"></b>后台用户<b class="tip"></b><a href="javascript:edit();">添加</a></div>
<table class="table table-striped table-bordered table-condensed">
 <tr>
   <th>姓名</th>
   <th>性别</th>
   <th>电话</th>
   <th>最后登陆时间</th>
   <th>操作</th>
 </tr>
 <c:if test="${! empty list && list.size() > 0 }">
  <c:forEach items="${list }" var="staffInfo">
   <tr class="even">
    <td>${staffInfo.name }</td>
    <td>${staffInfo.genderInfo }</td>
    <td>${staffInfo.phone }</td>
    <td>${fn:substring(staffInfo.lastLoginTimeStr,0,19) }</td>
    <td>
    <c:forEach items="${buttonList }" var="button">
     <c:if test="${button.code == 1023 && staffInfo.stat == 0 }">
      <input type="button" onclick="activateDestroy(this,'${staffInfo.id }');" title="点击注销该用户" value="注销" class="btn btn-mini btn-danger"/>
     </c:if>
     <c:if test="${button.code == 1023 && staffInfo.stat == 1 }">
      <input type="button" onclick="activateDestroy(this,'${staffInfo.id }');" title="点击激活用户" value="激活" class="btn btn-mini btn-primary add"/>
     </c:if>
     <c:if test="${button.code == 1022 }">
      <input type="button" onclick="manageStaff('${staffInfo.id }');" title="点击管理角色" value="管理" class="btn btn-mini btn-danger"/>
     </c:if>
    </c:forEach>
    </td>
   </tr>
  </c:forEach>
   <jsp:include page="../page.jsp"></jsp:include>
 </c:if>
 <c:if test="${empty list || list.size() <= 0 }">
  <tr>
   <td colspan="8" name="textCenter">暂无信息</td>
  </tr>
 </c:if>
</table>
</body>
</html>