<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    

<!doctype HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>전공책을 찾아서</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


	<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script>
  
 <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>

<script>
$(document).ready(function(){
	
	   
	
	var idfromback = '<%=request.getSession().getAttribute("userid") %>';
	var nickfromback = '<%=request.getSession().getAttribute("usernick") %>';
	
	if(idfromback != null){
		console.log('받은 아이디'+idfromback);
	}
	if(nickfromback != null){
		console.log('받은 닉'+nickfromback);
	}
	
	
	

	
	
});
</script>	



</head>
<body>



<div id="showmenu">

    <ul class="nav navbar-nav">
    
    <% if(request.getSession().getAttribute("userid")==null){ %>
    
    <li class="active menus"><a href="/major">HOME<span class="sr-only">(current)</span></a></li>
    <li class="menus"><a href="/major">ABOUT US</a></li>
     <li class="menus"><a href="/major">공지사항</a></li>
     <li class="menus"><a href="/major">게시판</a></li>
     <li class="menus"><a href="/major">문의사항</a></li>
    
          
      </div>    
  
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=6f1f5ce92db4949ae9395f384a2366f5&redirect_uri=http://203.236.209.213:8080/major/kauth&response_type=code"><img src="${pageContext.request.contextPath}/resources/img/kakao_login_btn_small_ov.png"/></a>
	로그인 후에 이용할 수 있습니다. 이거 컨트롤러 쪽에서 전부 체크해야하나?
	
	
	<%}else{ %>

            <c:choose>
                  <c:when test="${currentmenu eq 'home' }">
            <li class="active menus"><a href="/major">HOME<span class="sr-only">(current)</span></a></li>
            <li class="menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/page1" id="checkright">문의사항</a></li>
                  </c:when>
                  <c:when test="${currentmenu eq 'aboutus' }">
            <li class="menus"><a href="/major">HOME <span class="sr-only">(current)</span></a></li>
            <li class="active menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/page1"  id="checkright">문의사항</a></li>
                  </c:when>
                  <c:when test="${fn:startsWith(currentmenu,'notice') }">
            <li class="menus"><a href="/major">HOME <span class="sr-only">(current)</span></a></li>
            <li class="menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="active menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/page1"  id="checkright">문의사항</a></li>
            
                     
         <form class="navbar-form navbar-left" id="noticesearchform" method="get" action="/major/notice/noticesearch" >
                <input type="text" class="form-control" placeholder="search" name="noticesearchkeyword" id="noticesearchkeyword">
                <button type="submit"class="btn btn-default" >검색</button>
          </form>
            
                  </c:when>
                  <c:when test="${fn:startsWith(currentmenu,'board') }">
            <li class="menus"><a href="/major">HOME <span class="sr-only">(current)</span></a></li>
            <li class="menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="active menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/page1"  id="checkright">문의사항</a></li>
            
         <form class="navbar-form navbar-left" id="bbssearchform" method="get" action="/major/board/bbssearch" >
                <input type="text" class="form-control" placeholder="search" name="bbssearchkeyword" id="bbssearchkeyword">
                <button type="submit"class="btn btn-default" >검색</button>
          </form>
                  </c:when>
                  <c:when test="${fn:startsWith(currentmenu,'question') }">
            <li class="menus"><a href="/major">HOME <span class="sr-only">(current)</span></a></li>
            <li class="menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="active menus"><a href="/major/myqna/page1"  id="checkright">문의사항</a></li>
            
        <%if((Boolean)request.getSession().getAttribute("mastercheck")){ %>  
         <form class="navbar-form navbar-left" id="qnasearchform" method="get" action="/major/question/qnasearch" >
                   <input type="text" class="form-control" placeholder="search" name="qnasearchkeyword" id="qnasearchkeyword">
                   <button type="submit"class="btn btn-default" >검색</button>
                   
          </form>
          
          <%} %>
                  </c:when>
               </c:choose>

            
      </div>    
  

	<%= request.getSession().getAttribute("userid")%> 님 환영합니다<br/><a href="/major/logout"><button>logout</button></a>
<%} %>



<br/>
세션값은 <%= request.getSession().getAttribute("userid")%>
<%= request.getSession().getAttribute("mastercheck")%>
<%=request.getSession().getMaxInactiveInterval() %>



            
          </ul>
          
          
          
          
         </div>
        </div>
      </nav>      





















</body>
</html>