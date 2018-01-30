<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menutest.jsp"></jsp:include>

<script>
$(document).ready(function(){
	
		//alert("1");
		
	 Kakao.Auth.getStatus(function(statusObj){ //현재 로그인 상태를 확인
		//alert("2");
   	  console.log("디텔"+statusObj.user.id);
  
   	var currentuser= statusObj.user.properties.nickname;
 	var writer = '${bean.qnaNick}';
  	
	//alert(currentuser);
  	//alert( writer );
	

  	

	$(document).on('click', 'button[id="delbtn"]', function(){
  		//alert("삭제 클리익");
  		
  			Kakao.Auth.getStatus(function(statusObj){
					

		if(statusObj.status=="connected"){ 
		tempid = statusObj.user.id;
		tempnick = statusObj.user.properties.nickname;
		
		alert("지울때"+tempid);
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
  		
  	
	
	
  			}});
	
	});
	
  	
  	
  	
	if(writer==currentuser){
		//alert("니가 글슨이로구나...");
		 document.getElementById("btnplace").innerHTML='<button type="button" id="delbtn" class="btn btn-danger pull-right">삭제</button><button type="submit" id="editbtn" class="btn btn-default pull-right" >수정</button>';
	}
	
	 });
	 
 
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
<!-- 				<div class="col-md-12 text-center"> -->
<!-- 						<a class="btn btn-primary" href="/major/myqna">목록으로</a> -->
						
<!-- 				</div> -->
			</form>
			
			<div class="col-md-12">
				<jsp:include page="comment.jsp"></jsp:include>
			</div>
			
			
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