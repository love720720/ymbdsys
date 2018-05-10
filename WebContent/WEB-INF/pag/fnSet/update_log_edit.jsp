<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 系统更新设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/update-log-edit.js"></script>
<style type="text/css">
.width_340{
	width: 340px;
}
textarea{
	width: 240px;
	height: 100px;
	resize:none;
}
td[name="textCentent"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>设置<b class="tip"></b>更新日志添加</div>
<center>
<form action="editUpLog.htm" method="post" id="form_edit">
<table class="width_340 table table-striped table-bordered table-condensed list">
 <tr>
  <td>更新类型</td>
  <td>
   <select id="stat" name="stat">
    <option value="">--请选择更新类型--</option>
    <c:forEach items="${updateLogStat }" var="stat" varStatus="index">
     <option value="${index.index }">${stat }</option>
    </c:forEach>
   </select>
  </td>
 </tr>
 <tr>
  <td>更新摘要</td>
  <td><textarea id="content" name="content" maxlength="200"></textarea></td>
 </tr>
 <tr>
  <td colspan="2" name="textCentent">
   <input type="button" value="提交" id="sub" class="btn btn-mini btn-primary add"/>&nbsp;
   <input type="reset" class="btn btn-mini btn-primary add"/>
  </td>
 </tr>
</table>
</form>
</center>
</body>
</html>