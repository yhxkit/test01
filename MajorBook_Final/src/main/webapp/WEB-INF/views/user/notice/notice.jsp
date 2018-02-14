<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			  <h1>공지사항</h1>
			</div>
			
			<table class="table table-hover">
			  <tr>
			  	<th  class="col-md-1">번호</th>
			  	<th  class="col-md-6">제목</th>
			  	<th  class="col-md-2">글쓴이</th>
			  	<th  class="col-md-3">작성일</th>
			  </tr>
			  <c:forEach items="${list }" var="bean">
			  <tr>
			  	<td>${bean.noticeNo }</td>
			  	<td><a href="${bean.noticeNo }">${bean.noticeTitle }</a></td>
			  	<td>${bean.noticeNick }(${bean.noticeId })</td>
			  	<td>${bean.noticeDay }</td>
			  </tr>
			  </c:forEach>
			</table>
			<div id="noticewritebtn">

			</div>
			<div class="text-center">
				  <ul class="pagination">
				 <c:choose>
				 	<c:when test="${currentpage eq 1 }">
				
               		 </c:when>
                	<c:otherwise>
                	 <li><a href="page${currentpage-1} " aria-label="Previous">
                    	<span aria-hidden="true">&laquo;</span>
                  	</a>
                	<li>
                	</c:otherwise>
                </c:choose>
					
				<!--게시글 10개 페이지 5개씩 동적 처리 -->
			
						<c:forEach var="paging" begin="${startpage }" end="${endpage}" step="1"  >
							<c:choose>
							<c:when test="${currentpage eq paging }">
								<li class="active"> <a href="<c:out value='page${ paging }' />"> <c:out value="${ paging }" />	</a></li>
							</c:when>
							<c:otherwise>
								<li> <a href="<c:out value='page${ paging }' />"> <c:out value="${ paging }" />	</a></li>
							</c:otherwise>
						</c:choose>
						</c:forEach>
				
				 <c:choose>
				 	<c:when test="${currentpage eq totalpage }">
               		 </c:when>
               		 
                	<c:otherwise>
				    <li>
				      <a href="page${currentpage+1} " aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				    </c:otherwise>
				    </c:choose>
				  </ul>
				</div>
		<jsp:include page="../../menu/footer.jsp"></jsp:include>