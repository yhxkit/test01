<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!doctype HTML>

<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>전공책을 찾아서</title>
    <style type="text/css">
    	#newDiv{
    		margin-top : 10px;
    	}
    </style>
    <script src=" ${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    
<script>
/* var tempid;
var tempnick; */
 $(document).ready(function(){
    	   
Kakao.Auth.getStatus(function(statusObj){ //현재 로그인 상태를 확인

    	   		
    	  console.log(statusObj.status);
    	  console.log(statusObj.user);
    	 // var newDiv=document.createElement("div");
    	 var newDiv=document.getElementById("newDiv");
    	 
    	   		
if(statusObj.status=="not_connected"){
	

    newDiv.innerHTML='<a id="kakao-login-btn"></a>';
    	 
    	   
    	     // 카카오 로그인 버튼을 생성합니다.
    	     Kakao.Auth.createLoginButton({
    	       container: '#kakao-login-btn',
    	        size: 'small',
    	       success: function(authObj) {
    	        	
    	       // 로그인 성공시 API를 호출합니다.
    	       Kakao.API.request({
    	        	  
    	          url: '/v1/user/me',
    	          
    	          success: function(res) {
    	            var sData = JSON.stringify(res);
    	            //alert(sData);
    	            
    	            sData = JSON.parse(sData);
    	            
    	            console.log(JSON.stringify(sData));
    	            
    	            var route = "kakao"
    	            var kakao_root_id = sData.id+"@"+route; 
    	            var id = sData.id;     
    	            var nickname = sData.properties.nickname;
    	            tempid=id;
    	        	tempnick=nickname;
    	            var thumbnail_image = sData.properties.thumbnail_image;
    	            var profile_image = sData.properties.profile_image;
    	           

    	            var f = document.createElement("form");
    	          /*   f.setAttribute("method","get");
    	            f.setAttribute("action","afterlogin/"+i_id);
    	            f.setAttribute("target","_self");
    	            document.body.appendChild(f); */
    	            
    	            var i_id=document.createElement("input");
    	            i_id.setAttribute("type","hidden");
    	            i_id.setAttribute("name","kakao_id");
    	            i_id.setAttribute("value",id);
    	           // f.appendChild(i_id);
    	            
    	            var i_nick=document.createElement("input");
    	            i_nick.setAttribute("type","hidden");
    	            i_nick.setAttribute("name","kakao_nick");
    	            i_nick.setAttribute("value",nickname);
    	           // f.appendChild(i_nick);
    	            
    	            var i_thum=document.createElement("input");
    	            i_thum.setAttribute("type","hidden");
    	            i_thum.setAttribute("name","kakao_thumbnail_image");
    	            i_thum.setAttribute("value",thumbnail_image);
    	           // f.appendChild(i_thum);
    	            
    	            var i_profile=document.createElement("input");
    	            i_profile.setAttribute("type","hidden");
    	            i_profile.setAttribute("name","kakao_profile_image");
    	            i_profile.setAttribute("value",profile_image);
    	           // f.appendChild(i_profile);
    	            
    	            var i_accessToken=document.createElement("input");
    	            i_accessToken.setAttribute("type","hidden");
    	            i_accessToken.setAttribute("name","accessToken");
    	            i_accessToken.setAttribute("value",accessToken);
    	            //f.appendChild(i_accessToken);
    	            
    	            var i_refreshToken=document.createElement("input");
    	            i_refreshToken.setAttribute("type","hidden");
    	            i_refreshToken.setAttribute("name","refreshToken");
    	            i_refreshToken.setAttribute("value",refreshToken);
    	           // f.appendChild(i_refreshToken);
    	            
    	            
    	            f.setAttribute("method", "post");
    	            f.setAttribute("action", "/major/");
    	           // f.setAttribute("target","_self");
    	            document.body.appendChild(f);
    	            
    	            f.appendChild(i_id);
    	            f.appendChild(i_nick);
    	            f.appendChild(i_thum);
    	            f.appendChild(i_profile);
    	            f.appendChild(i_accessToken);
    	            f.appendChild(i_refreshToken);
    	            
    	            f.submit();
    	            
    	       
    	            
    	          },	
    	          fail : function(error){
    	      		console.log('api req fail'+JSON.stringify(error));
    	      	  }	
    	          
    	      }); //Kakao.API.request end
    	          
    	          
    	      var accessToken = Kakao.Auth.getAccessToken();
    	      var refreshToken = Kakao.Auth.getRefreshToken();
    	   
    	          //document.writeln("액세스 토큰 : "+accessToken);
    	          //document.writeln("<br>");
    	          //document.writeln("리프레시 토큰 : "+refreshToken);
    	          //document.writeln("<br>");
    	          
    	        }, // success: function(authObj) {
    	        
    	        fail: function(err) {
    	      	   console.log('oauth fail 뜸'+JSON.stringify(err));
    	        },
    	        
    	        always : function(authObj, errorObj){
    	        	
    	        		console.log(+statusObj.status+"상태");
    	        		console.log(statusObj.user.id+"유저");
    	        
    	        }
    	        
    	      });//  Kakao.Auth.createLoginButton end
    	    
    		
	}else{//접속상태면
    		newDiv.innerHTML= statusObj.user.id+"님 WELCOME! <button id='outbtn' class='btn btn-default'>로그아웃</button>";
    		tempid = statusObj.user.id;
    		tempnick = statusObj.user.properties.nickname;
   			
    		$('#outbtn').on('click', function(){
    			
    			Kakao.Auth.logout(function(){
    						
    					alert('로그아웃됨');
    						Kakao.Auth.getStatus(function(statusObj){
    								console.log("로갓 상태"+statusObj.status);
    								console.log("로갓 아이디"+statusObj.user);
    							});
    						location.replace("/major");//로그아웃 시 홈페이지로 돌아감
    						
    					});
    		});  

    		
    		
    	}
    	   	
});//Kakao.Auth.getStatus(function(statusObj) end
    	   
    	   			

	
    		
});   
    </script>
    
    
  </head>
  <body>
<div id="newDiv" class="pull-right">

</div>
    
  </body>
</html>