<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/960.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/administration_student_grade_add.css">
<link rel="stylesheet" href="./css/footer.css">
<script src="./js/jquery-1.12.4.js"></script>
<script>
	$(document).ready(function() {
		$("#btns").click(function() {
			var form = document.createElement('form');
			var objs;
			objs = document.createElement('input');
			objs.setAttribute('type', 'hidden');
			objs.setAttribute('name', 'name');
			objs.setAttribute('value', value);
			form.appendChild(objs);
			form.setAttribute('method', 'post');
			form.setAttribute('action', "/grade_insert.lms");
			document.body.appendChild(form);
			form.submit();
		});
	});
</script>


</head>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	<jsp:include page="../template/loginjoin.jsp"></jsp:include>
	<jsp:include page="../template/menu.jsp"></jsp:include>
	<jsp:include page="../template/aside_menu_administration.jsp"></jsp:include>


	<!-- content -->
	<div class="content grid_10">
		<img class="imgs" id="topimg" alt="" src="./imgs/menu_topimg1.jpg">
		<div class="layout">
			<div>
				<img alt="" src="">
			</div>
			<p>

				<b> 성적입력 <span><c:out value="${param.mname }" /></span>
				</b> 
				<c:out value="${param.mnum}" />
				
			</p>
			<form action="grade_insert.lms" method="post">
				<div id="inside">

					<div id="mnum">
						<label>회원번호</label><input type="text" name="mnum"
							value="${param.mnum}" readonly="readonly" />
					</div>
					<div id="java">
						<label>JAVA</label><input type="text" name="javaPoint" />
					</div>
					<div id="web">
						<label>WEB</label><input type="text" name="webPoint" />
					</div>
					<div id="db">
						<label>DB</label><input type="text" name="DBPoint" />
					</div>					
						<button id="btn">입력완료</button>
					

				</div>
			</form>
		</div>


	</div>
	<!-- content END  -->
	<div class="clear"></div>


	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>