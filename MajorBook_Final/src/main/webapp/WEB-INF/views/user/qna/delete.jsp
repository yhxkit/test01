<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
	Kakao.Auth.getStatus(function(statusObj){
		

		if(statusObj.status=="connected"){ 
		tempid = statusObj.user.id;
		tempnick = statusObj.user.properties.nickname;
		
    	var id=document.createElement("input");
		id.setAttribute("type","hidden");
		id.setAttribute("name","qnaId");
		id.setAttribute("value",tempid);
					 
		var nick=document.createElement("input");
		nick.setAttribute("type","hidden");
		nick.setAttribute("name","qnaNick");
		nick.setAttribute("value",tempnick);
		
		var f = document.getElementById("delform");
		
		f.appendChild(id);	
		f.appendChild(nick);
		
		}});
});
		

</script>
	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>삭제확인</h1>
			</div>
			  <h2>정말로 문의사항을 삭제하시겠습니까?</h2>
			  <form  action="delete/${bean.qnaNo}" method="post" id="delform">
			  
			  		<input name="_method" value="DELETE" type="hidden">
					<button class="btn btn-danger">삭제</button>
			 </form>
			
			

<jsp:include page="../../menu/footer.jsp"></jsp:include>