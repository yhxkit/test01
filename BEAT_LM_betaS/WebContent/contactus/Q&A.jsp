<%@page import="com.beat.Counsel.Model.QnaDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/960.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/contactus_Q&A.css">
<link rel="stylesheet" href="./css/footer.css">

<style type="text/css">

</style>
<script src="./js/jquery-1.12.4.js"></script>
<script src="./js/jquery.paging.js"></script>
<script>
function mouseOver(obj){
	 obj.style.cursor='pointer';
	 obj.style.backgroundColor="#dddddd";
}

function mouseOut(obj){
	 obj.style.backgroundColor="";
}

</script>

<script>
 <%
ArrayList<QnaDto> list=(ArrayList<QnaDto>)request.getAttribute("list");
ArrayList<QnaDto> listpaging=(ArrayList<QnaDto>)request.getAttribute("listpaging");
int pages=Integer.parseInt((String)request.getParameter("page"));
int totalPage=(int)(Math.ceil((list.size()+1)/10));

%>

 
$(function(){
	$('#paging').paging({
		'current':<%=pages%>,
		'max':'<%=totalPage%>'	
		
		});
	
	
	for(var i=0;i<<%=totalPage%>;i++){
	 $('#paging').children(0).eq(i).on('click',function(e){
		 e.preventDefault();
		location.href="QnaTotal.lms?page="+i;		
	 });
	 }
	
});


</script>

</head>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	<jsp:include page="../template/loginjoin.jsp"></jsp:include>
	<jsp:include page="../template/menu.jsp"></jsp:include>
	<jsp:include page="../template/aside_menu_contact.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content grid_10">
		<img class="imgs" id="topimg" alt="" src="./imgs/menu_topimg1.jpg">
		<div class="layout">
			<div>
				<img alt="" src="">
			</div>
			<p><b>질문사항</b></p>				
				
			<div id="listlayout">
			
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>비고</th>
					</tr>
					<c:forEach items="${ listpaging}" var="listpaging">
					<tr onmouseover="mouseOver(this);" onmouseout="mouseOut(this);" onclick='location.href="qnadetail.lms?lmsblog=${listpaging.lmsblog}"'>
						<td>${listpaging.qnaLog}</td>
						<td>${listpaging.qnaTitle }</td>
						<td>${listpaging.mid }</td>
						<td>${listpaging.qnaDate}</td>
						<td></td>
					</tr>
					</c:forEach>							
				
				</table>	
				
				<div id="addbtn">									
				  <a href="qnaAdd.lms">글쓰기</a>
				</div>
			<div id="paging">
	
			</div>
			</div>
		</div>


	</div>
	<!-- content END  -->
	<div class="clear"></div>


	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>