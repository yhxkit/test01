<%@page import="com.beat.Lecture.model.MyLectureDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/960.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/lecture_mylecture.css">
<link rel="stylesheet" href="./css/footer.css">

<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	<jsp:include page="../template/loginjoin.jsp"></jsp:include>
	<jsp:include page="../template/menu.jsp"></jsp:include>
	<jsp:include page="../template/aside_menu_lecture.jsp"></jsp:include>



	<!-- content -->
	<div class="content grid_10">
		<img class="imgs" id="topimg" alt="" src="./imgs/menu_topimg1.jpg">
		<div class="layout">
			<div>
				<img alt="" src="">
			</div>
			<div>
				<p id="firstP"><b>�������� ����</b></p>
				<table id="mylecture">
<% ArrayList<MyLectureDto> list = (ArrayList<MyLectureDto>)request.getAttribute("list"); 
for(MyLectureDto bean : list){
%>	
					<tr>
						<th>���Ǹ�</th>
						<th>���ǽ�</th>
						<th>��&nbsp;��</th>
						<th>��&nbsp;��</th>
						<th>&nbsp;��&nbsp;��&nbsp;</th>
						<th>&nbsp;������&nbsp;</th>
					</tr>
					<tr>
						<td><%= bean.getLecname() %></td>
						<td><%= bean.getLecroom()%></td>
						<td><%= bean.getLecteacher() %></td>
						<td><%= bean.getRedate() %></td>
						<td>20</td>
						<td><%=bean.getStucnt() %></td>
					</tr>
				</table>
			</div>
			<div>
			  <P><b>����</b></P>
				<table id="grade">
					<tr>
						<th>JAVA</th>
						<th>WEB</th>
						<th>DB</th>
						<th>���</th>
					</tr>
					<tr>
						<td><%=bean.getJavascr() %></td>
						<td><%=bean.getWebscr() %></td>
						<td><%=bean.getDbscr() %></td>
						<td><%=bean.getAvr() %></td>
					</tr>
				</table>
			</div>
			<div>
			   <P><b>�⼮</b></P><div id="warning">�⼮�� 60%�̸� �ߵ�Ż�� ����� �Դϴ�.</div>
				<table id="attendance">
					<tr>
						<th>�⼮</th>
						<th>�Ἦ</th>
						<th>����</th>
						<th>����</th>
						<th>�⼮��</th>
					</tr>
					<tr>
						<td><%=bean.getPrst() %></td>
						<td><%=bean.getAbst() %></td>
						<td><%=bean.getLate() %></td>
						<td><%=bean.getEarleav() %></td>
						<td><%=bean.getAttendancy() %></td>
					</tr>
	<%} %>
				</table>
			</div>

		</div>


	</div>
	<!-- content END  -->
	<div class="clear"></div>


	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>