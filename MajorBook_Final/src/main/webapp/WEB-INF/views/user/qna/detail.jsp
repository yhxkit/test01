<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>

<script>
$(document).ready(function(){
	
	
   	var tempid ='<%= request.getSession().getAttribute("userid")%>';
   	var tempnick ='<%= request.getSession().getAttribute("usernick")%>';
 	var writer = '${bean.qnaId}';

	$(document).on('click', 'button[id="delbtn"]', function(){
  		alert("삭제 클리익");
  		
  		
  		var f = document.createElement("form");
  	  	f.setAttribute("method", "post");
      	f.setAttribute("action", "${bean.qnaNo}");
      	
      	var confirmmethod=document.createElement("input");
      	confirmmethod.setAttribute("type","hidden");
      	confirmmethod.setAttribute("name","_method");
      	confirmmethod.setAttribute("value", "delete");

    	var id=document.createElement("input");
		id.setAttribute("type","hidden");
		id.setAttribute("name","qnaId");
		id.setAttribute("value",tempid);
					 
		var nick=document.createElement("input");
		nick.setAttribute("type","hidden");
		nick.setAttribute("name","qnaNick");
		nick.setAttribute("value",tempnick);
      	
		f.appendChild(id);	
		f.appendChild(nick);
		
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
			  <h1>문의사항 보기 </h1>
			</div>
			
			<form action="${bean.qnaNo }" method="post">
			<input type="hidden" name="_method" value="put">
				<div class="col-md-2"> 
					<div class="form-group"> 
						<label for="status">상태</label> 
<!-- 						<input type="text" class="form-control" name="qnaStatus" id="qnaStatus" value="상담중" readonly="readonly"/> -->
						<input type="text" class="form-control" name="qnaStatus" id="qnaStatus" value="${bean.qnaStatus }" readonly="readonly"/>
					</div> 
				</div>
				<div class="col-md-10">
					<div class="form-group"> 
						<label for="qnaTitle">제목</label> 
						<input type="text" class="form-control" name="qnaTitle" id="qnaTitle" value="${bean.qnaTitle }" readonly="readonly"/>  
					</div> 
				</div>
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="qnaContent">내용</label> 
						<textarea class="form-control" rows="20" name="qnaContent" id="qnaContent" readonly="readonly">${bean.qnaContent }</textarea>  
					</div> 
				</div>
				<!-- <div class="col-md-6">
					<div class="form-group"> 
						<label for="file">사진첨부</label> 
						<input type="file" id="file"> 
					</div> 
				</div> -->
						<input type="hidden" name="qnaDay" id="qnaDay"> 
				
				
					<div class="col-md-6 pull-right" id="btnplace">
				
					</div>
 				<div class="col-md-12 text-center">
						<a class="btn btn-primary" href="/major/myqna">목록으로</a> 	
				</div> 
			</form>
			
			<div class="col-md-12">
				<jsp:include page="comment.jsp"></jsp:include>
			</div>
				
	<jsp:include page="../../menu/footer.jsp"></jsp:include>