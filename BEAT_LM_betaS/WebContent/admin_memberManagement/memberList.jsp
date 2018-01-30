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
<link rel="stylesheet" href="./css/administration_student_list.css">
<link rel="stylesheet" href="./css/footer.css">

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
			<p>
				<b>수강생목록</b>
			</p>
			<div id="select">
				<form action="memberList.lms" name="searchQuery" method="get">
					<select id="column" name="selectColumn">
						<option value="lectSeriNum">강의번호</option>
						<option value="lectureName">강의명</option>
						<option value="mnum">회원번호</option>
						<option value="mname">회원명</option>
						<option value="mid">아이디</option>
					</select> <input type="text" id="searchText" name="searchText" /> <input
						type="submit" value="검색" />
				</form>
			</div>
			<div>
				<table class="list">
					<tr>
						<th>강의번호</th>
						<th>강의명</th>
						<th>회원번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>출석률</th>
						<th>비고</th>
					</tr>

					<c:forEach items="${list }" var="bean">
						<tr>
							<td>${bean.lectSeriNum}</td>
							<td>${bean.lectureName}</td>
							<td>${bean.mnum}</td>
							<td>${bean.mid}</td>
							<td><a href="memberDetail.lms?mnum=${bean.mnum}">${bean.mname}</a></td>
							<td>${bean.presentRatio}%</td>
							<td><c:if test="${bean.presentRatio < 60}">
								위험
							</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- content END  -->
	<div class="clear"></div>
	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>