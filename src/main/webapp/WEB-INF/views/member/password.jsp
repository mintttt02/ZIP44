<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>    

<link rel="stylesheet" href="<c:url value="/resources/css/setting.css"/>">
<script>
	var result = '${msg}';
	
	if(result == 'SUCCESS') {
		alert("비밀번호가 변경되었습니다.");
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
		<div class="setting_content" style="padding-left:180px;padding-top:40px;">
			<form method="post">
				<table class="setting_table">
					<tr>
						<td colspan="2" class="setting_text_center">비밀번호 변경</td>
					</tr>
					<tr>
						<td style="width:145px;">신규 비밀번호 <span>필수</span></td>
						<td>
							<input type="password" name="USER_PASS" class="setting_input" style="width:100%;">
						</td>
					</tr>
					<tr>
						<td style="width:145px;">비밀번호 확인 <span>필수</span></td>
						<td>
							<input type="password" class="setting_input" style="width:100%;">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="setting_underline"></div>
						<td>
					</tr>
					<tr>
						<td colspan="2" class="setting_text_center">
							<input type="submit" class="setting_saveBtn" value="변경하기">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 우측(내용) -->
	</div>
</div>