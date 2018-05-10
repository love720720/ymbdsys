<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 系统更新日志</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>设置<b class="tip"></b>站点信息</div>
 <table  class="table table-striped table-bordered table-condensed">
 <tr>
  <td><a href="siteInformationEdit.htm">编辑</a></td>
 </tr>
 <tr>
  <td>紧急公告</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.urgencyNotice }</td>
 </tr>
 <tr>
  <td>公告</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.notice }</td>
 </tr>
 <tr>
  <td>送餐说明</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.dispatchExplain }</td>
 </tr>
 <tr>
  <td>订餐时间</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.timeExplain }</td>
 </tr>
 <tr>
  <td>电话</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.phone }</td>
 </tr>
 <tr>
  <td>QQ</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.qq }</td>
 </tr>
 <tr>
  <td>地址</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.address }</td>
 </tr>
 <tr>
  <td>邮编</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.postcode }</td>
 </tr>
 <tr>
  <td>底部说明</td>
 </tr>
 <tr>
  <td>&nbsp;&nbsp;--${siteInformation.bottomExplain }</td>
 </tr>
 </table>
</body>
</html>