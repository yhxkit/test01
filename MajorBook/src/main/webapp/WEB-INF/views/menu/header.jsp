<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href=" ${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
	.navbar{
		background-color:#FFE3EE;
		
	}
	
	.navbar-default .navbar-nav>li>a {
    	color: #EB7EA7;
    	font-weight: bold;
    	
	}
	.navbar-default .navbar-brand {
    	color: #CD4275;
    	font-weight: bold;
	}
	 .navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:focus, .navbar-default .navbar-nav>.active>a:hover{
		color: #505050;
    	background-color: #D7567F;
	}
	#logotext h1{
		margin-top: 30px;
		
	}
	
	.navbar .navbar-collapse {
		  text-align: center;
		}
		
	
	@media (max-width : 767px){
		.container .header {
			display : none;
			visibility: hidden;
		}
	}
	
	table tr th,td{
		text-align:center;
	}
	table tr th{
		color: #505050;
		background-color:#FFE3EE;
	}
	table a:link { color: black; text-decoration: none;}
	table a:visited { color: grey; text-decoration: none;}
	table a:hover { color: #D7567F; text-decoration: underline;}
	
	
</style>

    <script src=" ${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>
    <script src=" ${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src=" ${pageContext.request.contextPath}/resources/js/kakao.min.js"></script>
</head>
<body>
	<div class="container header">
		<div class="row header">
			<div class="col-md-2 pull-left">
			<a href="/major" ><img alt="logo" src=" ${pageContext.request.contextPath}/resources/img/book.png"></a>
			</div>
			<div class="col-md-10 text-center" id="logotext">
			<h1><strong>전공책을 찾아서</strong></h1>
			</div>
		</div><!-- row header end -->
	</div>
	<!-- 상단 배너 메뉴 -->
	<nav class="navbar navbar-default">
	
	<div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/major">전공책을 찾아서</a>
        </div>
        
        
        <div id="navbar" class="navbar-collapse collapse">
          