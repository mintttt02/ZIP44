<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<link rel="stylesheet" href="<c:url value="/resources/css/signup.css"/>">

<div class="signup_container">
	<div class="signup_title_logo">
		<img src="/resources/images/title_logo.png">
	</div>
	<div class="signup_title">
		집꾸미기 회원가입
	</div>
	<div class="signup_wrapper">
		<form method="post" id="form" enctype="multipart/form-data">
			<div class="signup_input_wrapper">
				<input type="hidden" name="USER_IMG">
				<input type="email" class="signup_input" name="USER_EMAIL" id="USER_EMAIL" placeholder="이메일 주소" required>
				<img src="/resources/images/email_icon.svg">
			</div>
			<div class="signup_input_wrapper">
				<input type="text" class="signup_input" name="USER_NICKNAME" id="USER_NICKNAME" placeholder="닉네임" required>
				<img src="/resources/images/user_icon.svg">
			</div>
			<div class="signup_input_wrapper">
				<input type="password" class="signup_input" name="USER_PASS" id="USER_PASS" placeholder="비밀번호(8자리 이상)" required>
				<img src="/resources/images/lock_icon.svg">
			</div>
			<div class="signup_input_wrapper">
				<input type="password" class="signup_input" id="USER_PASSCHK" placeholder="비밀번호 확인" required>
				<img src="/resources/images/lock_icon.svg">
			</div>
			<div style="margin-top:20px;">
				<input type="checkbox" name="USER_SERVICE" value="1">
				<label>서비스 이용약관 동의</label>
				(<span>보기</span>)
			</div>
			<div style="margin-bottom:20px;">
				<input type="checkbox" name="USER_INFO" value="1">
				<label>개인정보취급방침 동의</label>
				(<span>보기</span>)
			</div>
			<button type="submit" class="signup_signup_btn" onclick="signup_checkForm(); return false;">회원가입</button>
			<button class="signup_signup_btn">로그인 하기</button>
		</form>
	</div>
</div>

<script src="/resources/script/signup.js"></script>