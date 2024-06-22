<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>    

<link rel="stylesheet" href="/resources/css/setting.css">
<script>
	function MemberLeave(email) {
		if(confirm('정말 탈퇴 하시겠습니까?')) {
			alert('정상적으로 탈퇴되었습니다.');
			location.href = "/member/leavePost?USER_EMAIL=" + email;
		}
	}
</script>

<div class="setting_wrapper">
	<div class="setting_container">
		<!-- 좌측(메뉴) -->
		<div class="setting_menu">
			<a href="/member/setting?USER_EMAIL=${email}">
				<div class="setting_menu_item">프로필 수정</div>
			</a>
			<a href="/member/password?USER_EMAIL=${email}">
				<div class="setting_menu_item">비밀번호 변경</div>
			</a>
			<a href="/member/leave">
				<div class="setting_menu_item">회원 탈퇴</div>
			</a>
			<a href="/member/address?USER_EMAIL=${email}">
				<div class="setting_menu_item">주소지 수정</div>
			</a>
		</div>
		<!-- 좌측(메뉴) -->
		
		<!-- 우측(내용) -->
		<div class="setting_content" style="padding-left:200px; padding-right:200px; padding-top:40px; text-align:center;">
			<img src="/resources/images/leave.png" ">
			<div style="font-size:15px; text-align:center; line-height:1.5;">
				<br>
				정말 탈퇴 하시겠습니까?
				<br>
				탈퇴한 데이터는 복구가 되지 않습니다.
				<br>
				<br>
				탈퇴를 원하시면 아래
				<b>"회원 탈퇴"</b>
				버튼을 눌러주세요.
				<br>
			</div>
			<button class="setting_saveBtn" onclick="MemberLeave('${email}');" style="margin-top:50px; text-align:center;">회원 탈퇴</button>
		</div>
		<!-- 우측(내용) -->
	</div>
</div>