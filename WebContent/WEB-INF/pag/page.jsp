<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
 function pager(pageIndex){
	 var fromAction = "${pageBean.fromAction}";
	 if(fromAction == null || fromAction.length <= 0){
		 return;
	 }
	 fromAction += ".htm";
	 var form = document.createElement("form");
	 document.body.appendChild(form);
	 var input = document.createElement("input");
	 input.type = "hidden";
	 input.value = pageIndex;
	 input.name = "pageIndex";
	 form.appendChild(input);
	 form.action = fromAction;
	 form.method="post";
	 form.submit();
	 return;
 }
</script>
<style type="text/css">
.pager th{
	padding:8px 10px;
}
.pager a{
	border:1px solid #CCC;
	margin:2px 3px;
	padding:3px 6px;
	cursor:pointer;
	background:#efefef;
	color:#333;
	text-decoration:none;
}
.pager a.current{
	cursor:default;
	background:#06C;
	color:#FFF;
}
.pager a:hover{
	background:#06C;
	color:#FFF;
}
</style>
 <tr class="pager">
  <th colspan="100">
  <b>当前第${pageBean.pageIndex }页/共${pageBean.totalPageCount }页&nbsp;&nbsp;共${pageBean.totalCount }条记录</b>
  &nbsp;&nbsp;
   <a 
    <c:if test="${pageBean.pageIndex > 1}">href="javascript:pager(1);"</c:if>
   >首页</a>&nbsp;
   <a
    <c:if test="${pageBean.pageIndex > 1}">href="javascript:pager(${pageBean.pageIndex - 1 });"</c:if>
   >上一页</a>&nbsp;
   <c:forEach items="${pageBean.pageNums }" var="pageNum">
    <a 
     <c:if test="${pageNum == pageBean.pageIndex }">class="current"</c:if>
     <c:if test="${pageNum != pageBean.pageIndex }">href="javascript:pager(${pageNum});"</c:if>
    >${pageNum }</a>&nbsp;
   </c:forEach>
   <a
    <c:if test="${pageBean.pageIndex < pageBean.totalPageCount}">href="javascript:pager(${pageBean.pageIndex + 1 });"</c:if>
   >下一页</a>&nbsp;
   <a
    <c:if test="${pageBean.pageIndex < pageBean.totalPageCount}">href="javascript:pager(${pageBean.totalPageCount});"</c:if>
   >尾页</a>
  </th>
 </tr>