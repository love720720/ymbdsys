<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一米便当 权限列表</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath }/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/ui-all/tree.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/global/chur.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/global/chur-alert.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/privilege-list.js"></script>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>权限管理<b class="tip"></b>权限列表</div>
<div class="container-fluid">
 <div class="row-fluid">
  <div class="span2">
   <ul class="easyui-tree">
    <li>
     <span>一米便当&nbsp;&nbsp;<a href="javascript:edit();" title="一米便当">添加</a></span>
     <ul>
      <c:forEach items="${privilegeList }" var="privilegeList1">
      <c:if test="${privilegeList1.parentId == 0 }">
      <li>
       <span>
        ${privilegeList1.name }
        <c:if test="${!privilegeList1.display }">★</c:if>
        &nbsp;&nbsp;
        <a href="javascript:detail('${privilegeList1.id }');" title="${privilegeList1.name }">明细</a>&nbsp;&nbsp;
        <a href="javascript:edit('${privilegeList1.id }');" title="${privilegeList1.name }">添加</a>&nbsp;&nbsp;
        <a href="javascript:edit('-1','${privilegeList1.id }');" title="${privilegeList1.name }">修改</a>&nbsp;&nbsp;
        <a href="javascript:del('${privilegeList1.id }');" title="${privilegeList1.name }">删除</a>
       </span>
       <ul>
       <c:forEach items="${privilegeList }" var="privilegeList2">
       <c:if test="${privilegeList1.id == privilegeList2.parentId }">
        <li>
         <span>
          ${privilegeList2.name }
          <c:if test="${!privilegeList2.display }">★</c:if>
          &nbsp;&nbsp;
          <a href="javascript:detail('${privilegeList2.id }');" title="${privilegeList2.name }">明细</a>&nbsp;&nbsp;
          <a href="javascript:edit('${privilegeList2.id }');" title="${privilegeList2.name }">添加</a>&nbsp;&nbsp;
          <a href="javascript:edit('-1','${privilegeList2.id }');" title="${privilegeList2.name }">修改</a>&nbsp;&nbsp;
          <a href="javascript:del('${privilegeList2.id }');" title="${privilegeList2.name }">删除</a>
         </span>
         <ul>
          <c:forEach items="${privilegeList }" var="privilegeList3">
           <c:if test="${privilegeList2.id == privilegeList3.parentId }">
            <li>
             <span>
	          ${privilegeList3.name }
	          <c:if test="${!privilegeList3.display }">★</c:if>
	          &nbsp;&nbsp;
	          <a href="javascript:detail('${privilegeList3.id }');" title="${privilegeList3.name }">明细</a>&nbsp;&nbsp;
	          <a href="javascript:edit('-1','${privilegeList3.id }');" title="${privilegeList3.name }">修改</a>&nbsp;&nbsp;
	          <a href="javascript:del('${privilegeList3.id }');" title="${privilegeList3.name }">删除</a>
	         </span>
            </li>
           </c:if>
          </c:forEach>
         </ul>
        </li>
       </c:if>
       </c:forEach>
       </ul>
      </li>
      </c:if>
      </c:forEach>
     </ul>
    </li>
   </ul>
  </div>
 </div>
</div>
</body>
</html>