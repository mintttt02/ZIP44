<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>    

<link rel="stylesheet" href="<c:url value="/resources/css/setting.css"/>">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

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
			<form method="post" enctype="multipart/form-data">
				<table class="setting_table">
					<tr>
						<td colspan="2" class="setting_text_center">
							<div class="setting_profile_wrapper">
								<img id="profile" alt="프로필" src="/zipImg/${img }">
								<input type=hidden name="original" value="${img }">
								<div class="setting_up_picture">
									<input type="file" name="file" id="photo_btn" class="photo_btn" value="$" multiple accept="image/*">
									<img alt="사진 업로드 버튼" src="/resources/images/camera_icon.svg">
								</div>
							</div>
							<br>
							<br>
						</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>
							<input type="text" name="USER_NAME" class="setting_input" value="${setting.USER_NAME }" style="width:140px;">
						</td>
					</tr>
					<tr>
						<td>닉네임 <span>필수</span></td>
						<td>
							<input type="text" name="USER_NICKNAME" id="USER_NICKNAME" class="setting_input" value="${setting.USER_NICKNAME }">
						</td>
						<td class="setting_text_center">
							<input type="button" class="setting_saveBtn" id="nickChk_btn" value="중복검사">
						</td>
					</tr>
					<tr>
						<td>이메일 주소<span>필수</span></td>
						<td>
							<input type="text" class="setting_input" id="USER_EMAIL" readonly value="${setting.USER_EMAIL }">
						</td>
					</tr>
					<tr>
						<td>휴대폰 번호</td>
						<td>
							<input type="text" name="USER_PHONE" class="setting_input" value="${setting.USER_PHONE }">
						</td>
					</tr>
					<tr>
						<td>웹사이트/블로그</td>
						<td>
							<input type="text" name="USER_SITE" class="setting_input" value="${setting.USER_SITE }">
						</td>
					</tr>
					<tr>
						<td>상태 메시지</td>
						<td>
							<input type="text" name="USER_MSG" class="setting_input" value="${setting.USER_MSG }">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="setting_underline"></div>
						<td>
					</tr>
					<tr>
						<td colspan="2" class="setting_text_center">
							<input type="hidden" id="nickChkValue" value="0">	<!-- 닉네임 중복체크 여부 값 -->
							<button class="setting_saveBtn" onclick="save_setting(); return false;">저장하기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 우측(내용) -->
	</div>
</div>

<script src="/resources/script/setting.js"></script>
