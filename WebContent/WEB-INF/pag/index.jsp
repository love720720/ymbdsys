<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 后台管理系统</title>
<link rel="Shortcut Icon" href="images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="css/global/base.css" />
<link rel="stylesheet" type="text/css" href="css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/global/ui-lightness/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="js/global/index.js"></script>
</head>
<body>
<div class="warp">
 <!--头部开始-->
 <div class="top_c">
  <div class="top-menu">
   <ul class="top-menu-nav">
    <li><a href="./">首页</a></li>
    <li><a target="_blank" href="feedback/suggestFeedback.htm">反馈建议<i></i></a>
    
    <li><a href="javascript:;">暂无<i class="tip-up"></i></a>
   <ul class="kidc">
    <li><a target="conframe" href="javascript:;">暂无</a></li>
   </ul>
    </li>
    <li><a href="javascript:;">暂无<i class="tip-up"></i></a>
   <ul class="kidc">
     <li><a target="conframe" href="javascript:;">暂无</a></li>
	</ul>
     </li>
     <li><a href="javascript:;">暂无<i class="tip-up"></i></a>
	 <ul class="kidc">
      <li><a target="conframe" href="javascript:;">暂无</a></li>
	 </ul>
     </li>
     <li><a href="javascript:;">暂无<i class="tip-up"></i></a>
	 <ul class="kidc">
      <li><a target="conframe" href="javascript:;">暂无</a></li>
	 </ul>
	 </li>
	</ul>
  </div>
 <div class="top-nav">
  ${welCome }&nbsp;&nbsp;
  <a href="staff/staffCenter.htm" target="conframe" title="个人中心">${sessionScope.STAFF.name }</a>
  &nbsp;&nbsp;
  <a href="staff/password.htm" target="conframe">修改密码</a>&nbsp;|&nbsp;<a href="./logOut.htm">安全退出</a>
 </div>
 </div>
 <!--头部结束-->
 
 <!--左边菜单开始-->
 <div class="left_c left">
 <h1>一米便当</h1>
 <div class="acc">
  <c:forEach items="${treeList }" var="tree1">
  <div>
   <c:if test="${tree1.parentId == 0 }">
    <a class="one">${tree1.name }</a>
     <ul class="kid">
     <c:forEach items="${treeList }" var="tree2">
      <c:if test="${tree1.id == tree2.parentId }">
       <li><b class="tip"></b><a target="${tree2.target }" href="${tree2.url }">${tree2.name }</a></li>
      </c:if>
     </c:forEach>
     </ul>
    </c:if>
   </div>
  </c:forEach>
 <div id="datepicker"></div>
 </div>
 </div>
 <!--左边菜单结束-->
 
 <!--右边框架开始-->
 <div class="right_c">
  <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>
 </div>
 <div class="conframe" id="conframeDiv">
  <iframe name="conframe" id="conframe" frameborder="0" src="fnSet/upLogList.htm"></iframe>
 </div>
 <!--右边框架结束-->

 <!--底部开始-->
 <div class="bottom_c">Copyright &copy;2013-2014 一米便当 版权所有</div>
 <!--底部结束-->
</div>
</body>
</html>