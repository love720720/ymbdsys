<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 后台用户角色管理</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<style type="text/css">
.width_300{
	width: 300px;
}
td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>后台用户<b class="tip"></b>用户角色管理</div>
<c:choose>
 <c:when test="${empty staffInfo || staffInfo.id <= 0 }">
  <table class="table table-striped table-bordered table-condensed">
   <tr>
    <td name="textCenter">参数错误 请重试</td>
   </tr>
  </table>
 </c:when>
 <c:otherwise>
 <center>
  <form action="editStaffRole.htm" method="post">
  <input type="hidden" name="id" value="${staffInfo.id }"/>
  <table class="width_300 table table-striped table-bordered table-condensed">
   <tr>
    <th>更新角色信息</th>
   </tr>
   <tr>
    <td>${staffInfo.name }</td>
   </tr>
   <tr>
    <td>
     <select name="roleId">
      <c:forEach items="${roleList }" var="role">
       <option value="${role.id }"
        <c:if test="${role.id == staffInfo.roleId }">selected</c:if>
       >${role.name }</option>
      </c:forEach>
     </select>
    </td>
   </tr>
   <tr>
    <td name="textCenter">
     <input type="submit" value="提交" class="btn btn-mini btn-danger"/>&nbsp;&nbsp;
     <input type="reset" value="重置" class="btn btn-mini btn-danger"/>
    </td>
   </tr>
  </table>
  </form>
 </center>
 </c:otherwise>
</c:choose>
</body>
</html>