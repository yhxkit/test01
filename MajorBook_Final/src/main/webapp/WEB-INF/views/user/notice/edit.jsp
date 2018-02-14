<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>

	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>공지사항 수정</h1>
			</div>
			
			<form action="completeedit/${bean.noticeNo}" method="post">
				<input type="hidden" name="_method" value="put">
	
				<div class="col-md-10">
					<div class="form-group"> 
						<label for="noticeTitle">제목</label> 
						<input type="text" class="form-control" name="noticeTitle" id="noticeTitle" value="${bean.noticeTitle }"/>  
					</div> 
				</div>
				<input type="hidden" name="noticeDay" value="${bean.noticeDay}" disabled="disabled">
				<!-- ///////////////////////////////////////////////////////////////////////////////// -->
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="noticeContent">내용</label> 
						<textarea class="form-control" rows="20" name="noticeContent" id="noticeContent" >${bean.noticeContent }</textarea>  
					</div> 
				</div>
				<!-- <div class="col-md-6">
					<div class="form-group"> 
						<label for="file">사진첨부</label> 
						<input type="file" id="file"> 
					</div> 
				</div> -->
						<input type="hidden" name="noticeNo" id="noticeNo" value="${bean.noticeNo }">
					
				<div class="col-md-12 text-center">
						<button class="btn btn-primary" type="submit">수정완료</button>
						<a class="btn btn-default" href="/major/notice/">목록</a>
				</div>
			</form>
				
		<jsp:include page="../../menu/footer.jsp"></jsp:include>