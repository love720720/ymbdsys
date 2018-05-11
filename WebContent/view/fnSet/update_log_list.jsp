<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 系统更新日志</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<style type="text/css">
.width_888{
	width: 888px;
}
.load{
	text-align: center;
	width: 888px;
}
</style>
<script type="text/javascript">
var pageIndex = 1;
var text = "正在加载...";
var error = "暂无数据";
var again = true;
function load(obj){
	if(!again){
		return;
	}
	var a = $(obj);
	var aText = a.text();
	a.text(text);
	$.ajax({
		type : "post",
		dataType : "json",
		url : "loadLog.htm",
		data : {
			"pageIndex" : pageIndex
		},
		success : function(data) {
			var len = data.logs.length;
			if(len <= 0){
				a.text(error);
				again = false;
				return;
			}
			a.text(aText);
			pageIndex++;
			var tab = "";
			for(var i = 0;i < data.logs.length; i++){
				var log = data.logs[i];
				var content = log.content;
				var createTimeInfo = log.createTimeInfo;
				createTimeInfo = createTimeInfo.substring(0,16);
				var src = "${pageContext.request.contextPath }/images/cawe.gif";
				if(log.isNew){
					src = "${pageContext.request.contextPath }/images/new.gif";
				}
				tab += "<tr>"
					  +"<td>"
					  +"<img src='"+src+"'/>"
					  +"</td>"
					  +"<td>"
					  +"<a href='javascript:;' title="+content+">"+content+"</a>"
					  +"</td>"
					  +"<td>"+log.statName+"</td>"
					  +"<td>"+log.staffInfoName+"</td>"
					  +"<td>"+createTimeInfo+"</td>"
				  +"</tr>";
			}
			$("#logTab").css("opacity","0");//这句加在append前
			$("#logTab").animate({opacity:1},1000);//这句的作用是使div缓慢显示
			$("#logTab").append(tab);
		},
		error : function(a, b, c) {
			alert(b);
		}
	});
}
</script>
</head>
<body>
<c:if test="${! empty updateLogList && updateLogList.size() > 0 }">
 <table class="width_888 table-striped  table-condensed" cellspacing="0" cellpadding="0" id="logTab">
 <c:forEach items="${updateLogList }" var="updateLog">
 <tr>
  <td>
   <c:choose>
    <c:when test="${updateLog.isNew }"><img src="${pageContext.request.contextPath }/images/new.gif"/></c:when>
    <c:otherwise><img src="${pageContext.request.contextPath }/images/cawe.gif"/></c:otherwise>
   </c:choose>
  </td>
  <td width="50%">
   <a href="javascript:;" title="${updateLog.content }">${fn:substring(updateLog.content,0,40) }</a>
  </td>
  <td>${updateLog.statName }</td>
  <td>${fn:substring(updateLog.staffInfoName,0,5) }</td>
  <td>${fn:substring(updateLog.createTimeInfo,0,16) }</td>
 </tr>
 </c:forEach>
 </table>
 <div class="load">
  <a href="javascript:;" onclick="load(this)">加载更多</a>
 </div>
</c:if>
</body>
</html>