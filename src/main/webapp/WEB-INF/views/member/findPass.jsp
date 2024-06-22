<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<!-- 작성자 : 최나현 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.findPass_wrap *{
box-sizing: border-box;

}
.findPass_wrap{
margin: auto;
padding-top: 95px;
padding-button: 95px;
}
.title_logo{
    margin-right: auto;
    margin-left: auto;
    width: 40px;
    margin-bottom: -25px;
}
.title_logo img{
height: 50px;
}
.findPass_wrap .title{
	    font-size: 13px;
    color: #afafaf;
    text-align: center;
    font-weight: 500;
    margin-top: 30px;
    margin-bottom: 20px;
}
.lost_wrap{
margin-left: auto;
    margin-right: auto;
    width: 300px;
}
.lost_div input{
    width: 300px;
    border: 1px solid #dfdfdf;
    background-color: #fff;
    height: 46px;
    padding-left: 5px;
    margin-bottom: 10px;
}

</style>
</head>
<body>

<div class="findPass_wrap">

	<div class="title_logo">
		<img src="/resources/images/i_title_logo.png">
	</div>
	<div class="title">집꾸미기 로그인</div>
	<div class="lost_wrap">
		<form method="post" action="/member/sendMail">
		<div class="lost_div">
			<input type="email" name="findPassEmail" id="input-lost-email" class="input-login" placeholder="이메일 주소">
			<input type="submit" value="비밀번호 찾기">
			<input type="button" value="로그인 하기" onclick="location.href='/member/login'">
		</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>