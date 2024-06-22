<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
<script>
	var result = '${msg}';
	
	if(result == 'SUCCESS') {
		alert("회원가입을 축하드립니다.");
	}
	
	function loginchk() {
		var user_email = document.getElementById("USER_EMAIL").value;
		var user_pass = document.getElementById("USER_PASS").value;
		
		if (user_email.length < 1) {
			alert('이메일을 입력해주세요.');
			return;
		} else if (user_pass.length < 1) {
			alert('비밀번호를 입력해주세요.');
			return;
		} else {
			submit();
		}
	}
</script>

<div class="login_container">
	<div class="login_title_logo">
		<img src="/resources/images/title_logo.png">
	</div>
	<div class="login_title">
	집꾸미기 로그인	
	</div>
	<div class="login_wrapper">
		<form action="/member/loginPost" method="post">
			<div class="login_input_wrapper">
				<input type="email" class="login_input" name="USER_EMAIL" id="USER_EMAIL" placeholder="E-MAIL" >
				<img src="/resources/images/email_icon.svg">
			</div>
			<div class="login_input_wrapper">
				<input type="password" class="login_input" name="USER_PASS" id="USER_PASS" placeholder="PASSWORD">
				<img src="/resources/images/lock_icon.svg">
			</div>
			<button type="submit" class="login_login_btn" onclick="loginchk(); return false;">LOGIN</button>
			
		</form>
		<div class="login_bottom">
			<span><a href="/member/signup">회원가입</a></span>
			|
			<span><a href="/member/findPass">비밀번호 찾기</a></span>
		</div>
	</div>
</div>