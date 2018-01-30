<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/960.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet"
	href="./css/administration_student_attendance.css">
<link rel="stylesheet" href="./css/footer.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	
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
				<b>출석체크</b>
			</p>
			<div id="select">
				<form action="student_attendance.lms" name="searchQuery"
					method="get">
					<select id="column" name="selectColumn">
						<option value="">선택</option>
						<option value="mnum">번호</option>
						<option value="mname">이름</option>
						<option value="roomNumber">강의장</option>
						<option value="lectureName">강의명</option>
					</select> <input type="text" id="searchText" name="searchText">
					<button id="searchButton" type="submit">검색</button>
				</form>
			</div>
			<div>
				<form action="student_attendance_insert.lms" method="get">
					<table class="list">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>강의장</th>
							<th>강의명</th>
							<th>출석</th>
							<th>지각</th>
							<th>조퇴</th>
							<th>결석</th>
							<!-- 비고에는 선택안함 경고창 validation -->
						</tr>
						<c:forEach items="${list }" var="bean">
							<tr>
								<td>${bean.mnum}</td>
								<td>${bean.mname}</td>
								<td>${bean.roomNumber}</td>
								<td>${bean.lectureName}</td>
								<td><input type='radio' value='presentDays'
									name='${bean.mnum}' /></td>
								<td><input type='radio' value='lateTimes'
									name='${bean.mnum}' /></td>
								<td><input type='radio' value='earlyHome'
									name='${bean.mnum}' /></td>
								<td><input type='radio' value='absentDays'
									name='${bean.mnum}' checked='checked' /></td>
							</tr>
						</c:forEach>
					</table>
					<div>
						<button type="submit" id="btn">출석체크</button>
					</div>
				</form>
			</div>
		</div>


	</div>
	<!-- content END  -->
	<div class="clear"></div>
	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>