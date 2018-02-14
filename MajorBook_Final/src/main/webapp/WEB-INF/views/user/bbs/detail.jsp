<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
   .col-md-12 .text-center{
      margin-top: 20px;
   }
   
   #img {
      display: block;
      border: 1px gray solid;
      width: 60%;
      margin-left: auto;
      margin-right: auto;
   }
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<jsp:include page="../../menu/header.jsp"></jsp:include>
<jsp:include page="../../menu/menurest.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
   
   
  
   

   var tempid ='<%= request.getSession().getAttribute("userid")%>';
    var writer = '${bean.id}';
     
   //alert(currentuser);
     //alert( writer );
   

     

   $(document).on('click', 'button[id="delbtn"]', function(){
        //alert("삭제 클리익");
        
         var f = document.createElement("form");
          f.setAttribute("method", "post");
         f.setAttribute("action", "${bean.no}");
         
          var confirmmethod=document.createElement("input");
         confirmmethod.setAttribute("type","hidden");
         confirmmethod.setAttribute("name","_method");
         confirmmethod.setAttribute("value", "delete");

        f.appendChild(confirmmethod);
        
          document.body.appendChild(f);
                  
        f.submit(); 
        
        
        
     });//on end
     
     
     
   if(writer==tempid){
      //alert("니가 글슨이로구나...");
       document.getElementById("btnplace").innerHTML='<button type="button" id="delbtn" class="btn btn-danger pull-right">삭제</button><button type="submit" id="editbtn" class="btn btn-default pull-right" >수정</button>';
   }
   
    
    
 
 }); //ready end
 
window.onload = function() {
   CKEDITOR.replace('content');
} 
 </script>


   <!-- 내용 -->   
   <div class="container">
      <div class="row content">
        <div class="col-md-12">
           <div class="page-header">
           <h1>내용보기 </h1>
         </div>
         
         <form action="${bean.no }" method="post">
         <input type="hidden" name="_method" value="put">
         
            <div class="col-md-2"> 
               <div class="form-group"> 
                  <label for="status">상태</label> 
                  <select class="form-control">
                    <option id="sell">${bean.status}</option>
                  </select> 
               </div> 
            </div> 
            <div class="col-md-10">
               <div class="form-group"> 
                  <label for="title">제목</label> 
                  <input type="text" class="form-control" name="title" id="title" value="${bean.title }" readonly="readonly"/>  
               </div> 
            </div>
            <div class="col-md-4">
               <div class="form-group"> 
                  <label for="nickName">이름</label> 
                  <input type="text" class="form-control" name="nickName" id="nickName" value="${bean.nickName }(${bean.id})" readonly="readonly">  
               </div> 
            </div>
            <div class="col-md-4">
               <div class="form-group"> 
                  <label for="university">대학교</label> 
                  <input type="text" class="form-control" name="university" id="university" value="${bean.university }" readonly="readonly">  
               </div> 
            </div>
            <div class="col-md-4">
               <div class="form-group"> 
                  <label for="dept">과</label> 
                  <input type="text" class="form-control" name="dept" id="dept" value="${bean.dept }" readonly="readonly">  
               </div> 
            </div>   
            <!-- ///////////////////////////////////////////////////////////////////////////////// -->
            <div class="col-md-6">
               <div class="form-group"> 
                  <label for="bookName">책이름</label> 
                  <input type="text" class="form-control" name="bookName" id="bookName" value="${bean.bookName }" readonly="readonly"/>  
               </div> 
            </div>
            <div class="col-md-3">
               <div class="form-group"> 
                  <label for="author">저자</label> 
                  <input type="text" class="form-control" name="author" id="author" value="${bean.publisher }" readonly="readonly"/>  
               </div> 
            </div>
            <div class="col-md-3">
               <div class="form-group"> 
                  <label for="publisher">출판사</label> 
                  <input type="text" class="form-control" name="publisher" id="publisher" value="${bean.author }" readonly="readonly"/>  
               </div> 
            </div>
            <!-- ///////////////////////////////////////////////////////////////////////////////// -->
            <div class="col-md-4">
               <div class="form-group"> 
                  <label for="ISBN">고유번호</label> 
                  <input type="text" class="form-control" name="ISBN" id="ISBN" value="${bean.ISBN }" readonly="readonly"/>  
               </div> 
            </div>
            <div class="col-md-4">
               <div class="form-group"> 
                  <label for="costPrice">원가</label> 
                  <input type="text" class="form-control" name="costPrice" id="costPrice" value="${bean.costPrice }" readonly="readonly"/>  
               </div> 
            </div>
            <div class="col-md-4">
               <div class="form-group"> 
                  <label for="salePrice">판매가</label> 
                  <input type="text" class="form-control" name="salePrice" id="salePrice" value="${bean.salePrice }" readonly="readonly"/>  
               </div> 
            </div>
            <!-- ///////////////////////////////////////////////////////////////////////////////// -->
            <div class="col-md-12">
               <div class="form-group"> 
                  <label for="content">내용</label>
                  <c:if test="${bean.imgFile ne '' }">
                  <img id="img" src="${pageContext.request.contextPath}/resources/file/${bean.imgFile}"/>
                  </c:if>
                  
                  <textarea class="form-control" rows="20" name="content" id="content" readonly="readonly">${bean.content }</textarea>  
               </div> 
            </div>
               <input type="hidden" name="day" id="day"> 
               <div class="col-md-6 pull-right" id="btnplace"></div>
         </form>
         
            <div class="col-md-12">
            <jsp:include page="comment.jsp"></jsp:include>
            </div>
            
            <div class="col-md-12 text-center">
                  <a class="btn btn-primary" href="/major/board/">목록으로</a>
            </div>
            
            
   <jsp:include page="../../menu/footer.jsp"></jsp:include>