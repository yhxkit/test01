<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

 <jsp:include page="../../menu/header.jsp" ></jsp:include>
 <jsp:include page="../../menu/menurest.jsp"></jsp:include>
 <style type="text/css">

 </style>
   <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
   <script src=" ${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
  <script type="text/javascript" >

  $(document).ready(function(){
		//  history.replaceState({}, null, location.pathname)


	 $(document).on('submit', 'form',function(e){
		//alert("이거 실행안해?");
		e.preventDefault();
						

		var tempid ='<%= request.getSession().getAttribute("userid")%>';
		var tempnick ='<%= request.getSession().getAttribute("usernick")%>';

		
			var id=document.createElement("input");
			id.setAttribute("type","hidden");
			id.setAttribute("name","qnaId");
			id.setAttribute("value",tempid);
						 
			var nick=document.createElement("input");
			nick.setAttribute("type","hidden");
			nick.setAttribute("name","qnaNick");
			nick.setAttribute("value",tempnick);
						 
		/* 	var master=document.createElement("input");
			master.setAttribute("type","hidden");
			master.setAttribute("name","master");
			master.setAttribute("value",checkmaster);
			f.appendChild(master);  
		*/
			var wform = document.getElementById('qnaform');
			wform.appendChild(id);	
			wform.appendChild(nick);
			wform.submit();
	 
							 
 	});  

});
		$("#writeform").validate();
	
	
 </script>
 </head>
 <body>
	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>문의사항 작성</h1>
			</div>
			
			
			<form action="/major/myqna/write/writecom" method="post" id="qnaform" >
				<div class="col-md-2"> 
					<div class="form-group"> 
						<label for="status">상태</label> 
						<input type="text" class="form-control" name="qnaStatus" id="qnaStatus" value="상담중"  readonly="readonly"/>
					</div> 
				</div> 
				<div class="col-md-10">
					<div class="form-group"> 
						<label for="qnaTitle" id="qnaTitle">제목</label> 
						<input type="text" class="form-control" name="qnaTitle" id="qnaTitle" placeholder="제목을 작성하세요" minlength="2" maxlength="100" required="required">  
					</div> 
				</div>
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="qnaContent">내용</label> 
						<textarea class="form-control" rows="20" name="qnaContent" id="qnaContent"></textarea>  
					</div> 
				</div>
				<!-- <div class="col-md-6">
					<div class="form-group"> 
						<label for="filename">사진첨부</label> 
						<input type="file" name="filename" id="filename"> 
					</div> 
				</div> -->
				<div class="col-md-12 text-center">
				<button class="btn btn-primary" type="submit" id="qnawritebtn">완료</button>
				<button class="btn btn-default" type="reset">취소</button>
				</div>
			</form>
			
			<table class="table table-hover">
           <tr>
              <th  class="col-md-1">번호</th>
              <th  class="col-md-2">상태</th>
              <th  class="col-md-5">제목</th>
              <th  class="col-md-2">글쓴이</th>
              <th  class="col-md-2">작성일</th>
           </tr>
           <c:forEach items="${mylist }" var="bean">
           <tr>
              <td>${bean.qnaNo }</td>
              <td>${bean.qnaStatus }</td>
              <td><a href="/major/myqna/${bean.qnaNo }">${bean.qnaTitle }</a></td>
              <td>${bean.qnaNick }</td>
              <td>${bean.qnaDay }</td>
           </tr>
           </c:forEach>
         </table>
			
			
			
			

		<jsp:include page="../../menu/footer.jsp"></jsp:include>
