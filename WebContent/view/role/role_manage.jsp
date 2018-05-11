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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/page/privilege-manage.js"></script>
</head>
<body>
<div class="alert alert-info">当前位置<b class="tip"></b>角色管理<b class="tip"></b>角色权限管理</div>
<c:choose>
 <c:when test="${! empty role && role.id > 0}">
  ${role.name }&nbsp;&nbsp;
  <input type="button" value="更新权限" onclick="editRolePrivilege('${role.id }');" class="btn btn-mini btn-primary add"/>
<div class="container-fluid">
 <div class="row-fluid">
  <div class="span2">
   <ul class="easyui-tree">
    <li>
     <span>
  	  <input type="checkbox" onclick="checkAll(this,'rp');"
  	   <c:if test="${allTrue }">checked</c:if>
  	  />   
                一米便当
     </span>
     <ul>
      <c:forEach items="${privilegeList }" var="privilegeList1">
      <c:if test="${privilegeList1.parentId == 0 }">
      <li>
       <span>
       <input type="checkbox" name="rp" value="${privilegeList1.id }"
        <c:if test="${privilegeList1.isTrue }">checked</c:if>
       />
        ${privilegeList1.name }
       </span>
       <ul>
       <c:forEach items="${privilegeList }" var="privilegeList2">
       <c:if test="${privilegeList1.id == privilegeList2.parentId }">
        <li>
         <span>
         <input type="checkbox" name="rp" value="${privilegeList2.id }"
          <c:if test="${privilegeList2.isTrue }">checked</c:if>
         />
          ${privilegeList2.name }
         </span>
         <ul>
          <c:forEach items="${privilegeList }" var="privilegeList3">
           <c:if test="${privilegeList2.id == privilegeList3.parentId }">
            <li>
             <span>
             <input type="checkbox" name="rp" value="${privilegeList3.id }"
              <c:if test="${privilegeList3.isTrue }">checked</c:if>
             />
	          ${privilegeList3.name }
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
 </c:when>
 <c:otherwise>未获取到数据</c:otherwise>
</c:choose>
</body>
</html>