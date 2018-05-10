<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 对不起 无权浏览</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript">
$(function(){
	var isTrue = ${isTrue};
	isTrue = isTrue || false;
	if(isTrue){
		$("body").alert({
			type : "danger",
			title : "提示",
			content : "对不起 您没有权限浏览",
			btntext : "返回首页",
			modal : true,
			draggabled : false,
			even : "click",
			buttons : [ {
				id : "yes",
				name : "返回首页",
				callback : function() {
					location.href = "index.htm";
				}
			}]
		});
		return;
	}
	location.href = "index.htm";
	return;
});
</script>
</head>
<body>
</body>
</html>