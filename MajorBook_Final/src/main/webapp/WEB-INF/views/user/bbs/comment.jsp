<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  <script>
   

   var tempid ='<%= request.getSession().getAttribute("userid")%>';
   var tempnick ='<%= request.getSession().getAttribute("usernick")%>';

   
   var putcmtinf = function(){
   
    var cmtnick=document.createElement("input");
    cmtnick.setAttribute("type","hidden");
    cmtnick.setAttribute("name","commNick");
    cmtnick.setAttribute("value",tempnick);
    
    var cmtid=document.createElement("input");
    cmtid.setAttribute("type","hidden");
    cmtid.setAttribute("name","commId");
    cmtid.setAttribute("value",tempid);
    
    var cform = document.getElementById('commentform');
      cform.appendChild(cmtnick);
      cform.appendChild(cmtid);
   };
 </script>


<c:forEach items="${cmtlist }" var="cmtbean">
   <div class="list-group">
       <h4 class="list-group-item-heading">${cmtbean.commNo } :        ${cmtbean.commNick }(${cmtbean.commId })             ____${cmtbean.commDay}</h4>
       <p class="list-group-item-text">${cmtbean.commContent }</p>
   </div>
 </c:forEach>
 



   <form action="/major/board/writecomment" method="post" id="commentform" onsubmit="putcmtinf()">
      <input type="hidden" name="bbsNo" value="${bean.no }">
      
      <div class="col-md-12">
         <div class="form-group"> 
         
         <label for="commContent">코멘트 내용</label> 
         <textarea class="form-control" rows="4" name="commContent" id="commContent"></textarea>  
         <button class="btn btn-default col-md-12" type="submit">코멘트 등록</button> 
         </div>
      </div>
      
   </form>
   
</html>