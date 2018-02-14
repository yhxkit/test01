<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
window.onload = function() {
	CKEDITOR.replace('content');
}
</script>
	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>게시글 수정</h1>
			</div>
			
			<form action="completeedit/${bean.no}" method="post">
	<input type="hidden" name="_method" value="put">
				<div class="col-md-2"> 
					<div class="form-group"> 
						<label for="status">상태</label> 
						<select class="form-control" name="status" id="status">
						  <option>팔래요</option>
						 <option>살래요</option>
						 <option>거래완료</option>
						</select> 
					</div> 
				</div> 
				<div class="col-md-10">
					<div class="form-group"> 
						<label for="title">제목</label> 
						<input type="text" class="form-control" name="title" id="title" value="${bean.title }"/>  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="nickName">이름</label> 
						<input type="text" class="form-control" name="nickName" id="nickName" value="${bean.nickName }" readonly="readonly"/>  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="university">대학교</label> 
						<input type="text" class="form-control" name="university" id="university" value="${bean.university }" />
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="dept">과</label> 
						<input type="text" class="form-control" name="dept" id="dept" value="${bean.dept }"/>  
					</div> 
				</div>	
				<!-- ///////////////////////////////////////////////////////////////////////////////// -->
				<div class="col-md-6">
					<div class="form-group"> 
						<label for="bookName">책이름</label> 
						<input type="text" class="form-control" name="bookName" id="bookName" value="${bean.bookName }" />  
					</div> 
				</div>
				<div class="col-md-3">
					<div class="form-group"> 
						<label for="author">저자</label> 
						<input type="text" class="form-control" name="author" id="author" value="${bean.author }" />  
					</div> 
				</div>
				<div class="col-md-3">
					<div class="form-group"> 
						<label for="publisher">출판사</label> 
						<input type="text" class="form-control" name="publisher" id="publisher" value="${bean.publisher }" />  
					</div> 
				</div>
				<!-- ///////////////////////////////////////////////////////////////////////////////// -->
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="ISBN">고유번호</label> 
						<input type="text" class="form-control" name="ISBN" id="ISBN" value="${bean.ISBN }" />  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="costPrice">원가</label> 
						<input type="text" class="form-control" name="costPrice" id="costPrice" value="${bean.costPrice }" />  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="salePrice">판매가</label> 
						<input type="text" class="form-control" name="salePrice" id="salePrice" value="${bean.salePrice }" />  
					</div> 
				</div>
				
				<input type="hidden" name="day" value="${bean.day}" disabled="disabled">
				<input type="hidden" name="view" value="${bean.view }">
				<!-- ///////////////////////////////////////////////////////////////////////////////// -->
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="content">내용</label> 
						<%-- <c:if test="${bean.imgFile ne '' }">
						<img src="${pageContext.request.contextPath}/resources/file/${bean.imgFile}"/>
						</c:if> --%>
						<textarea class="form-control" rows="20" name="content" id="content" >${bean.content }</textarea>  
					</div> 
				</div>
				
						<input type="hidden" name="no" id="no" value="${bean.no }">
					
				<div class="col-md-12 text-center">
						<button class="btn btn-primary" type="submit">수정완료</button>
						<a class="btn btn-default" href="/major/board/">목록</a>
				</div>
			</form>
				
		<jsp:include page="../../menu/footer.jsp"></jsp:include>