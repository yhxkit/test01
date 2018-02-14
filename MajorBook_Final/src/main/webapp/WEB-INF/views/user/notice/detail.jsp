<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>

<script>
$(document).ready(function(){
	

   	var tempid ='<%= request.getSession().getAttribute("userid")%>';
 	var writer = '${bean.noticeId}';
  	
	//alert(tempid);
  	//alert(writer);
	

  	

	$(document).on('click', 'button[id="delbtn"]', function(){
  		//alert("삭제 클리익");
  		
  		 var f = document.createElement("form");
  	  	f.setAttribute("method", "post");
      	f.setAttribute("action", "${bean.noticeNo}");
      	
      	 var confirmmethod=document.createElement("input");
      	confirmmethod.setAttribute("type","hidden");
      	confirmmethod.setAttribute("name","_method");
      	confirmmethod.setAttribute("value", "delete");

    	 f.appendChild(confirmmethod);
    	 
      	 document.body.appendChild(f);
    	           
    	 f.submit(); 
  		
  		
  		
  	});
  	
  	
  	
	if(writer==tempid){
		//alert("니가 글슨이로구나...");
		 document.getElementById("btnplace").innerHTML='<button type="button" id="delbtn" class="btn btn-danger pull-right">삭제</button><button type="submit" id="editbtn" class="btn btn-default pull-right" >수정</button>';
	}
	

	 
 
 }); 
 </script>


	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>공지사항 보기 </h1>
			</div>
			
			<form action="${bean.noticeNo }" method="post">
			<input type="hidden" name="_method" value="put">
			
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="noticeTitle">제목</label> 
						<input type="text" class="form-control" name="noticeTitle" id="noticeTitle" value="${bean.noticeTitle }" readonly="readonly"/>  
					</div> 
				</div>
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="noticeContent">내용</label> 
						<textarea class="form-control" rows="20" name="noticeContent" id="noticeContent" readonly="readonly">${bean.noticeContent }</textarea>  
					</div> 
				</div>
				<!-- <div class="col-md-6">
					<div class="form-group"> 
						<label for="file">사진첨부</label> 
						<input type="file" id="file"> 
					</div> 
				</div> -->
						<input type="hidden" name="noticeDay" id="noticeDay"> 
				
				
					<div class="col-md-6 pull-right" id="btnplace">
				
					</div>
				<div class="col-md-12 text-center">
						<a class="btn btn-primary" href="/major/notice/">목록으로</a>
						
				</div>
			</form>
				
	<jsp:include page="../../menu/footer.jsp"></jsp:include>