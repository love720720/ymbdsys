<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 商品信息设置</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript">
$(function(){
	var isTrue = ${isTrue};
	var tips = "${tips}";
	if(isTrue){
		$("body").alert({
			type : "success",
			title : "提示",
			content : tips,
			btntext : "确定",
			modal : true,
			draggabled : false,
			even : "click",
			buttons : [
			           {
				id : "today",
				name : "今日商品列表",
				callback : function() {
					location.href = "productToday.htm";
				}
			}, {
				id : "back",
				name : "商品列表",
				callback : function() {
					location.href = "productList.htm";
				}
			} , {
				id : "again",
				name : "继续添加",
				callback : function() {
					location.href = "editProduct.htm";
				}
			} 
			]
		});
		return;
	}
	$("body").alert({
		type : "danger",
		title : "提示",
		content : tips,
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click",
		buttons : [ {
			id : "yes",
			name : "确定",
			callback : function() {
				location.href = "javascript:history.back(-1);";
			}
		}]
	})
});
</script>
</head>
<body>
</body>
</html>