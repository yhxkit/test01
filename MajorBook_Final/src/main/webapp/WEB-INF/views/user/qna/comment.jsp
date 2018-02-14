<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  <script>
  
 	var tempnick ='<%= request.getSession().getAttribute("usernick")%>';
 	var tempid ='<%= request.getSession().getAttribute("userid")%>';

	var putcmtinf = function(){
	
	 var cmtnick=document.createElement("input");
	 cmtnick.setAttribute("type","hidden");
	 cmtnick.setAttribute("name","qnaCommNick");
	 cmtnick.setAttribute("value",tempnick);
	 
	 var cmtid=document.createElement("input");
	 cmtid.setAttribute("type","hidden");
	 cmtid.setAttribute("name","qnaCommId");
	 cmtid.setAttribute("value",tempid);
	 
	 var cform = document.getElementById('qnaCommentform');
		cform.appendChild(cmtnick);
		cform.appendChild(cmtid);
	};
 </script>


<c:forEach items="${cmtlist }" var="cmtbean">
	<div class="list-group">
	    <h4 class="list-group-item-heading">${cmtbean.qnaCommNo } :        ${cmtbean.qnaCommNick }(${cmtbean.qnaCommId })             ____${cmtbean.qnaCommDay}</h4>
	    <p class="list-group-item-text">${cmtbean.qnaCommContent }</p>
	</div>
 </c:forEach>
 

	<form action="writecomment" method="post" id="qnaCommentform" onsubmit="putcmtinf()">
		<input type="hidden" name="qnaListNo" value="${bean.qnaNo }">
		
		<div class="col-md-12">
			<div class="form-group"> 
			
			<label for="qnaCommContent">코멘트 내용</label> 
			<textarea class="form-control" rows="4" name="qnaCommContent" id="qnaCommContent"></textarea>  
			<button class="btn btn-default col-md-12" type="submit">코멘트 등록</button> 
			</div>
		</div>
		
	</form>
	
</html>