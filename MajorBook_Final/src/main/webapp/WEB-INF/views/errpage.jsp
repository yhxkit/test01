<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>전공책을 찾아서</title>
    <link href=" ${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src=" ${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>
    <script src=" ${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src=" ${pageContext.request.contextPath}/resources/js/kakao.min.js"></script>
</head>
<body>
<div class="container text-center">
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
     <h1><u>페이지 오류 안내: error code ( ${requestScope['javax.servlet.error.status_code']} )</u></h1>
            <br/>
            <br/>
            <br/>
            <br/>
            <h3>
            홈페이지 이용에 불편을 드려 죄송합니다.
            <br/>
            <br/>
           
            뒤로가기를 누르시거나, 잠시 후에 이용해 주십시요
            </h3>
            <p>
            <br/>
            <br/>
            <br/>
              <a href="/major" class="btn btn-lg btn-default"><strong>홈페이지로 이동</strong></a>
            </p>

            <div >
              <p>company2018</p>
            </div>
        </div>
  </body>
</html>