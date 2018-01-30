<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=request.getParameter("mnum")%>의 상세페이지</title>
<link rel="stylesheet" href="./css/960.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet"
	href="./css/administration_student_list_detail.css">
<link rel="stylesheet" href="./css/footer.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$('#pointUpdate').click(function() {
			if ((
			Number($('#javaPoint').val())
			+ Number($('#webPoint').val()) 
			+ Number($('#dbPoint').val())) / 3 
			>= 0&& (Number($('#javaPoint').val())
			+ Number($('#webPoint').val()) 
			+ Number($('#dbPoint').val())) / 3 
			<= 100&& ((Number($('#javaPoint').val())) 
			>= 0 && (Number($('#javaPoint').val())) <= 100)
			&& ((Number($('#webPoint').val())) 
			>= 0 && (Number($('#webPoint').val())) 
			<= 100)&& ((Number($('#dbPoint').val())) 
			>= 0 && (Number($('#dbPoint').val())) <= 100)) {
			$('#pointAvr').val(
				Math.floor((Number($('#javaPoint').val())
				+ Number($('#webPoint').val()) 
				+ Number($('#dbPoint').val())) / 3 * 100) / 100);
			} else {
				$('#pointAvr').val(
				'잘못된 값이 입력되었습니다');
			}
		});
		$('#pointSubmit').click(function() {
			alert('성적 입력 버튼 클릭(임시)');
		});
		$('#presentUpdate').click(function() {
			if ((
				Number($('#presentDays').val())
				+ Number($('#absentDays').val()) 
				+ Number($('#lateEarly').val())) / 3 
				>= 0&& (Number($('#presentDays').val())
				+ Number($('#absentDays').val()) 
				+ Number($('#lateEarly').val())) / 3 
				<= 100&& ((Number($('#presentDays').val())) 
				>= 0 && (Number($('#presentDays').val())) <= 100)
				&& ((Number($('#absentDays').val())) 
				>= 0 && (Number($('#absentDays').val())) 
				<= 100)&& ((Number($('#lateEarly').val())) 
				>= 0 && (Number($('#lateEarly').val())) <= 100)) {
			$('#presentRatio').val(
				Math.floor(Number($('#presentDays').val()) / (Number($('#presentDays').val()) + Number($('#absentDays').val()))*1000)/10+'%(지각 제외)')
				;
				//location.href = 'gradeUpdate.jsp?';
			} else {
				$('#presentRatio').val(
				'잘못된 값이 입력되었습니다');
			}
		});
		$('#presentSubmit').click(function() {
			alert('출석 입력 버튼 클릭(임시)');
		});
	})
</script>
<style>
</style>
</head>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	<jsp:include page="../template/loginjoin.jsp"></jsp:include>
	<jsp:include page="../template/menu.jsp"></jsp:include>
	<jsp:include page="../template/aside_menu_administration.jsp"></jsp:include>

	<div class="content grid_10">
			<img class="imgs" id="topimg" alt="" src="./imgs/menu_topimg1.jpg">
		<div class="layout">
			<div>
				<img alt="" src="">
			</div>
			<p>
				<b>수강생정보</b>
			</p>
	
	<c:forEach items="${list }" var="bean">
	<div id="stuinfo">
		<table>
			<tr>
				<th>이름</th>
				<td>${bean.mname}</td>
				<th>성별</th>
				<td>${bean.mzenText}</td>
				<th>연락처</th>
				<td>0${bean.mphone}</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>${bean.mbirth}</td>
				<th>e-mail</th>
				<td>${bean.mmail}</td>
				<th>주소</th>
				<td>${bean.maddress}/${bean.maddnum}</td>
			</tr>
		</table>
	</div>
			<p>
				<b>수강강의</b>
			</p>
	<div>
		<table id="lecture">
			<tr>
				<th>강의명</th>
				<td>${bean.lectureName}</td>
				<th>강사</th>
				<td>${bean.teacherName}</td>
				<th>기간</th>
				<td>${bean.lectureStart}~${bean.lectureEnd}</td>
				<th>수강인원</th>
				<td>${bean.studentTotal}</td>
			</tr>
		</table>
	</div>
			<p>
				<b>성적</b>
			</p>
			<div>
<table id="grade">
			<tr>
				<th>JAVA</th>
				<th>WEB</th>
				<th>DB</th>
				<th>평균</th>
				<!--  <th>총합</th> -->
			</tr>
			<tr>
				<td>${bean.javaPoint}</td>
				<td>${bean.webPoint}</td>
				<td>${bean.dbPoint}</td>
				<td>${bean.pointAvr}</td>
				<!-- 합계점수 <td>${bean.pointSum}</td> -->
			</tr>
			<tr>
				<td><input type="text" id="javaPoint" /></td>
				<td><input type="text" id="webPoint" /></td>
				<td><input type="text" id="dbPoint" /></td>
				<td><input type="text" readonly="readonly" id="pointAvr" /></td>
				<td><input type="button" value="수정" id="pointUpdate" /></td>
				<td><input type="button" value="입력" id="pointSubmit" /></td>
			</tr>
		</table>
		</div>
			<p>
				<b>출석</b>
			</p>
		<c:if test="${bean.presentRatio < 60}">
			출석률 60% 미만, 중도탈락 대상자입니다
		</c:if>
			<div>
				<table id="attendance">
			<tr>
				<th>출석</th>
				<th>결석</th>
				<th>지각/조퇴</th>
				<th>출석률</th>
			</tr>
			<tr>
				<td>${bean.presentDays}</td>
				<td>${bean.absentDays}</td>
				<td>${bean.lateEarly}</td>
				<td>${bean.presentRatio}%</td>
			</tr>
			<tr>
				<td><input type="text" id="presentDays" /></td>
				<td><input type="text" id="absentDays" /></td>
				<td><input type="text" id="lateEarly" /></td>
				<td><input type="text" id="presentRatio" readonly="readonly" /></td>
				<td><input type="button" value="수정" id="presentUpdate" /></td>
				<td><input type="button" value="입력" id="presentSubmit" /></td>
			</tr>
		</table>
		</div>
	</c:forEach>
	</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>