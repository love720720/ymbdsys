<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 反馈建议</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/suggest-feedback.js"></script>
<style type="text/css">
.width_600{
	width: 600px;
}
#lh{
	line-height: 210px;
}
textarea{
	width: 500px;
	height: 200px;
	resize:none;
}
th[name="textCenter"],td[name="textCenter"]{
	text-align: center;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>反馈建议</div>
<center>
 <table class="width_600 table table-striped table-bordered table-condensed">
  <tr>
   <th colspan="2" name="textCenter">请填写需要提交的反馈建议信息</th>
  </tr>
  <tr>
   <td id="lh">邮件内容</td>
   <td>
    <textarea id="content" name="content"></textarea>
   </td>
  </tr>
  <tr>
   <td colspan="2" name="textCenter">
    <input type="button" value="提交" id="sub" class="btn btn-mini btn-primary add"/>&nbsp;&nbsp;
    <input type="reset" value="重置" class="btn btn-mini btn-primary add"/>
   </td>
  </tr>
 </table>
</center>
</body>
</html>