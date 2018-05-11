<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 商品搜索</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select2/select2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2/select2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/product-search.js"></script>
<style type="text/css">
td[name="textCenter"]{
	text-align: center;
}
.width_300{
	width: 300px;
}
</style>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>商品管理<b class="tip"></b>商品搜索</div>
<input type="hidden" id="select_Id" class="width_300"/>
<br/>
<br/>
<div id="pruductInfo"></div>
</body>
</html>