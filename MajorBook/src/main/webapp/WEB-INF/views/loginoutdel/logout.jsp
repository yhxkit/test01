<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>

$(document).ready(function(){
//웹은 그냥 두고 앱은 폰이 개인용 기기니까 토큰 잔여 유효시간받아내서 스스로 로갓안하면 계속 로그인 되어있게 처리할까?
	

//Kakao.init('920ab8edbd5f0f27b6f8c7df2c8d5532');
Kakao.init('fe2b9e6e9dc19c730ad3d547e0772625'); //전공책앱

Kakao.Auth.getStatus(function(statusObj){
		
		console.log(statusObj.status);
		console.log(statusObj.user);
	
	});
	
	
  $('#outbtn').on('click', function(){
	
		Kakao.Auth.logout(
				function(){
					
				alert('로그아웃됨');

					Kakao.Auth.getStatus(function(statusObj){
							
							console.log("로갓 상태"+statusObj.status);
							console.log("로갓 아이디"+statusObj.user);
						
						});
					
					location.replace("/major");//로그아웃 시 홈페이지로 돌아감
					
				});
	
});  



});

</script>
</head>
<body>

<h1>after login</h1>


<button id="outbtn">로그아웃하기</button>


<form action="../unlinkapp" method="post">
	<button type="submit">연결해제 페이지로 가는 버튼</button>
<p>근데 이걸...폼을 써야 하나....전송할 데이터도 없는데 포스트로 보내려고...? 여튼 이거 위치 생각해볼것</p>
</form>



</body>
</html>