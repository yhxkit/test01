<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>

<script>
$(document).ready(function(){
var checkmaster = '<%= session.getAttribute("mastercheck")%>';

if(checkmaster=="true"){
	  document.getElementById('noticewritebtn').innerHTML='<button class="btn btn-default pull-right" onclick="towritepage();" id="write" >글쓰기</button>';
}
});
	
	var towritepage=function(){
		var write=document.createElement("form");
		write.setAttribute("method","post");
		write.setAttribute("action", "write");
		document.body.appendChild(write);
		write.submit();
		
		//alert("클릭");
	}
</script>
   <!-- 내용 -->   
   <div class="container">
      <div class="row content">
        <div class="col-md-12">
           <div class="page-header">
           <h1>팔래요 게시판</h1>
          
         </div>
         
         <table class="table table-hover">
           <tr>
              <th  class="col-md-1">번호</th>
              <th  class="col-md-2">상태</th>
              <th  class="col-md-4">제목</th>
              <th  class="col-md-2">글쓴이</th>
              <th  class="col-md-2">작성일</th>
           </tr>
      <c:forEach items="${list }" var="bean">
           <tr>
              <td>${bean.no }</td>
              <td>${bean.status }</td>
              <td><a href="${bean.no }">${bean.title }</a></td>
              <td>${bean.nickName }(${bean.id })</td>
              <td>${bean.day }</td>
           </tr>
           </c:forEach>
         </table>
         <button class="btn btn-default pull-right" onclick="towritepage();" id="write" >글쓰기</button>
   ${totalpage }
         
      <jsp:include page="../../menu/footer.jsp"></jsp:include>