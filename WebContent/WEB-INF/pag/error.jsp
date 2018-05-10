<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>一米便当 对不起 您当前访问的页面不存在</title>
<meta http-equiv=content-type content="text/html; charset=UTF-8">
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<style type=text/css>
html {
	background:url(data:;base64,R0lGODlhKAAoALMAAO7s7fv5+vr4+e/t7vPx8vLw8fDu7/Xz9Pj29/n3+PTy8/b09ff19vHv8AAAAAAAACH5BAAAAAAALAAAAAAoACgAAAT/sCh0ThpqAJbKTciCJESyMM1iEEPSGEvBGQVSAE1DCLlHMIXFYsCANQ5CA4XQUBhgJEPnMEAwQgrCAaFQFBsMgEBAQGgPjBKy0DAlDMUCerE7oqAVGcJQmTStRC1oA3BlCm0HhwkHAlc5VTI8eWlNL3IMAz+JBAAKbGwLVAoWIkChBw0DbBMJb1wVW181IEw8GQUGAHQdCi48rQsZQyY9XaGaiQsXNToEyocHBgJCYsFHbQ0IOhYwGdFDXaodNqqpHRco2ldCHEyZYE90NI9zOCrYDNM1WiB62UNVsnhisKVJNC8sQhxJMGqAnWxFXLzBpGRIAxmtCGTBUaBRjSoh/xCILPAjGJoLPwqQ5HBoT7ByxJQ461JG2ZFeTFLAycZFGQAXXVZwYKgL2Co3BzomVSDgQpMT0bRpQ2JFAok9zZQlIFihSRAgWToYSEHkHxoEYvgQGMuBAhFgOWoSWxSAC4NRW5yIoBNqyAoJDGKIPaCRASY2HO6KzKfFiQ6PLkPQcgIIRYcyInWMSrJWyUJMPVusCIZJUTQkTpQMwODN1YJsJACOGItE1YCkjTJVuAIDQNITezSCSCNChp4yN60wFElnD5FXNVAoCKbNiQwnhN5MYOmJNkkwQKIFtjsdiEMhYU8QYyMlRjYPAaa0DGygCzwTcDzY0HJkD8kqBHigQP8ncGgDhUo6qANOEKPgtEl6o1AQAhqpUKHEd6AY8ZqEbGQjxQZOFEGQBytUwZIUN20FxhGJHBIKQ0yNxpU2R1wXgxAFZCKAN6/h4AlJa4ExghMoacMEB1F1U4USh7yXhkYUuJAIMShMyccoO4aSIx+GWTCdC0Bw4EEKFjAWwgBCgPFGHmY0MZYMFqGyUVI08LFAJ8qwgKJhLKnwRBsD4XJbCS45o2V4XrTHBx8moAdDETswsagpDFVwQg6WDEXFAk3dEU5SQUwTzDFCMFRTGyR5EuB1oCQAgF1R3PXCIlIgmI4FfwSmg2YvdfFkNESgsGOOMYQh4RMWvgYpGKoksRiKmpI1QMZroYrj4ggwZhGgLuK9UAEBa6kiDSpA1OfDdAKMNZYLQ7hnGI2vbXGCDA4ZEgIdTUxQpg7iakOCRp1QsUc+q8mAIiEqGNxlU2OCK1UOw7hagwygoXCbnVzmhwlDRcABAJppRBcNGOI1wkdIUJLUVDP1/YSSEm22woi2aORUhgHPeRFYPxEAADs=)
		repeat top left fixed
}
.mod-notfound {
	border-right: #e1e1e1 1px solid;
	border-top: #e1e1e1 1px solid;
	margin-top: 10px;
	background: #fff;
	border-left: #e1e1e1 1px solid;
	border-bottom: #e1e1e1 1px solid;
	height: 585px;
	text-align: center;
	border-radius: 10px
}
.mod-page-body {
	height: auto;
	_height: 100%;
	min-height: 100%;
	zoom: 1;
	color: #454545;
}
.mod-page-body .mod-page-main .x-page-container {
	padding: 0;
	margin: 0;
	zoom: 1
}
.mod-page-body .mod-page-main {
	padding-top: 50px;
	padding-bottom: 85px;
}
.wordwrap {
	word-break: break-all;
	word-wrap: break-word
}
.clearfix {
	zoom: 1
}
body{
font-size: 12px;
font-family: Tahoma, Helvetica, "Microsoft Yahei", "微软雅黑", Arial, STHeiti;
}
.grid-98 {
	width: 980px;
	overflow: hidden;
	margin: 0 auto;
}
.p1{
	font-size: 24px;
	line-height: 70px;
}
.p2{
	margin-bottom: 30px;
}
.p3{
	font-size: 14px;
	line-height: 20px;
}
</style>
</head>
<body>
 <section class="mod-page-body">
 <div class="mod-page-main wordwrap clearfix">
  <div class="x-page-container">
   <div class="mod-notfound grid-98">
    <img src="${pageContext.request.contextPath }/images/404-page.gif">
    <p class="p1">啊~哦~ 您要查看的页面不存在或已删除！</p>
    <p class="p2">请检查您输入的网址是否正确，或者点击链接继续浏览空间</p>
    <p class="p3">您可以
     <a href="javascript:history.back();" title="点击返回上一页">返回上一页</a>&nbsp;或到
     <a href="javascript:window.top.location.href='${pageContext.request.contextPath }/index.htm'" title="点击返回首页">回到网站首页</a>
	</p>
   </div>
  </div>
 </div>
 </section>
</body>
</html>