<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../menu/header.jsp"></jsp:include>
<jsp:include page="../menu/menutest.jsp"></jsp:include>
<script src=" ${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>

<script type="text/javascript">

</script>

	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>회원탈퇴</h1>
			</div>
			  <p>개인정보가 해당서비스에서 삭제됩니다.<br/><br/>
			  	회원탈퇴 버튼을 누르시면 카카오 앱과 연결이 끊깁니다.<br/><br/>
			   	정말로 탈퇴하시겠습니까?
			  </p>
			  <br/><br/>
			 <form  action="/major/userdelete" method="post" id="accountdeleteform">
			  		<input name="_method" value="DELETE" type="hidden">
					<button class="btn btn-danger" id="accountdeletebtn">회원탈퇴</button>
			</form>
			
<jsp:include page="../menu/footer.jsp"></jsp:include>