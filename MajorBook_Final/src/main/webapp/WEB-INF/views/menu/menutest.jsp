<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전공책을 찾아서</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
var tempid;
var tempnick;
var checkmaster=false;
//어느 문서에서든 menu.jsp는 login.jsp보다 먼저 include 되어야함
$(document).ready(function(){

      var f = document.createElement("form");
       f.setAttribute("method","get");   
         //f.setAttribute("action","/major/question/page1");
      
   
Kakao.init('fe2b9e6e9dc19c730ad3d547e0772625'); //전공책앱
    
Kakao.Auth.getStatus(function(statusObj){

   //console.log("메뉴"+statusObj.status);
   //console.log("메뉴"+statusObj.user);
   
    if(statusObj.status=="connected"){ //접속상태가 로그인일때 //비동기로 관리자값을 확인하고 로그인상태를 true로 변경
      //ajax로 값 넘김 "id값"

      tempid = statusObj.user.id;
       tempnick = statusObj.user.properties.nickname;
      
        var req = new XMLHttpRequest();
          function checklogin(){//encodeURIComponent() 해줘야하나 id
              
              //굳이 폼을 만들어서 시리얼라이즈할 필요없으니 uri로 넘기기로..
            req.open("POST", "/major/checkmasteraccount?checkid="+encodeURIComponent(statusObj.user.id), true);
             req.onreadystatechange = checkProcess;
             req.send(null);
             
          }
           
          function checkProcess(){
             if(req.readyState==4 && req.status ==200){
                var obj = eval('('+ req.responseText +')');
                var result = obj.result;
                //alert(result.value);
                
                if(result.value=='true'){
                  
                   f.setAttribute("action","/major/question/page1");
                   
                   
                  checkmaster = true; 
                  //통신성공해서 받아온 관리자확인 값이 트루면 이렇게 경로를 바꿈.
                   document.getElementById('checkright').setAttribute("href", "/major/question/page1");
                  
                
                   //notice 페이지에서 관리자일 경우에만 글쓰기 버튼을 보여줌
                   document.getElementById('noticewritebtn').innerHTML='<button class="btn btn-default pull-right" onclick="towritepage();" id="write" >글쓰기</button>';

                   
                   
                }else{
                   f.setAttribute("action","/major/myqna");
                   document.getElementById('qnasearchform').innerHTML='';

                }
                
             }
          }
          checklogin();
          
       
       }else{ //비로그인일때는 모든 메뉴를 전부 메인으로 돌려버림
       
          
          var fakemenu =document.getElementById("showmenu");
          fakemenu.innerHTML='<ul class="nav navbar-nav"><li class="active menus"><a href="/major">HOME<span class="sr-only">(current)</span></a></li><li class="menus"><a href="/major">ABOUT US</a></li><li class="menus"><a href="/major">공지사항</a></li><li class="menus"><a href="/major">게시판</a></li><li class="menus"><a href="/major">문의사항</a></li></ul>';
      
          ////비로그인으로 주소표시줄에서 주소 찍고 들어올때... 여기 미비점임 ㅠㅠ 일단 그 게시판에 들어갔다가 리로드 되기 때문에 퍼포먼스가 지저분하고 각 게시판 컨트롤러에서도 찍힘  
          var currenturl = location.href;
          var suburl = currenturl.split("/major/")[1];
      
          if(suburl != ""){ //main아닐때는 main으로
           //  location.replace("/major");
          }
       }    
   });


   //문의 사항을 클릭했을 때에만 //주소표시줄로 들어올때는 처리되지 않음ㅠ
    $(document).on('click', 'a[id="checkright"]', function(e){
      e.preventDefault();
      //alert(tempid + tempnick+checkmaster);
       Kakao.Auth.getStatus(function(statusObj){
               

      if(statusObj.status=="connected"){ 
      tempid = statusObj.user.id;
      tempnick = statusObj.user.properties.nickname;
      
     /*    var f = document.createElement("form");
        f.setAttribute("method","get");   
        f.setAttribute("action","/major/question/page1");
        */ document.body.appendChild(f);
        
        var userid=document.createElement("input");
        userid.setAttribute("type","hidden");
        userid.setAttribute("name","userid");
        userid.setAttribute("value",tempid);
          f.appendChild(userid);
       
           var master=document.createElement("input");
          master.setAttribute("type","hidden");
          master.setAttribute("name","master");
          master.setAttribute("value",checkmaster);
          f.appendChild(master); 
          
          f.submit();
      }});
});
   
    
   
   });
   </script>
   </head>
   <body>
   
   <div id="showmenu">

    <ul class="nav navbar-nav">
    
            <c:choose>
                  <c:when test="${currentmenu eq 'home' }">
            <li class="active menus"><a href="/major">HOME<span class="sr-only">(current)</span></a></li>
            <li class="menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/" id="checkright">문의사항</a></li>
                  </c:when>
                  <c:when test="${currentmenu eq 'aboutus' }">
            <li class="menus"><a href="/major">HOME <span class="sr-only">(current)</span></a></li>
            <li class="active menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/"  id="checkright">문의사항</a></li>
                  </c:when>
                  <c:when test="${fn:startsWith(currentmenu,'notice') }">
            <li class="menus"><a href="/major">HOME <span class="sr-only">(current)</span></a></li>
            <li class="menus"><a href="/major/aboutus/">ABOUT US</a></li>
            <li class="active menus"><a href="/major/notice/page1">공지사항</a></li>
            <li class="menus"><a href="/major/board/page1">게시판</a></li>
            <li class="menus"><a href="/major/myqna/"  id="checkright">문의사항</a></li>
            
                     
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
            <li class="menus"><a href="/major/myqna/"  id="checkright">문의사항</a></li>
            
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
            <li class="active menus"><a href="/major/myqna/"  id="checkright">문의사항</a></li>
            
                     
         <form class="navbar-form navbar-left" id="qnasearchform" method="get" action="/major/question/qnasearch" >
              
                   <input type="text" class="form-control" placeholder="search" name="qnasearchkeyword" id="qnasearchkeyword">
                   <button type="submit"class="btn btn-default" >검색</button>
                   
          </form>
                  </c:when>
               </c:choose>

            
      </div>    
            
            <jsp:include page="../loginoutdel/login.jsp"></jsp:include>
            
          </ul>
          
          
          
          
         </div>
        </div>
      </nav>      


   </body>
   </html>