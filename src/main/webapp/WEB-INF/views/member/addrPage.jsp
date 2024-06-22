<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>    

<link rel="stylesheet" href="<c:url value="/resources/css/setting.css"/>">
<script>
var result = '${msg}';

if(result == 'SUCCESS') {
	alert("배송지가 추가되었습니다.");
}

function removeAddr(num) {
	if(confirm('정말 삭제 하시겠습니까?')) {
		alert('정상적으로 삭제되었습니다.');
		location.href='/member/address/remove?add_num=' + num;
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
		<div class="setting_content" style="padding-top:40px;">
			<!-- 기존 배송지 있을 때 출력 -->
			<div class="setting_delivery_addr">
				<c:forEach items="${addr}" var="addr">
				<div class="setting_deli_container">
					<div class="setting_deli_content">
						<div class="setting_deli_info">
							<div>
								<div>${addr.add_name}</div>
								<div>${addr.address }</div>
								<div>${addr.add_phone }</div>
							</div>
						</div>
						<div class="setting_deli_btn">
							<div class="btn_modify" onclick="location.href='/member/address/modify?add_num=${addr.add_num}'">수정</div>
							<div class="btn_remove" onclick="removeAddr(${addr.add_num});">삭제</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<!-- 새 배송지 만들기 -->
			<div class="setting_row">
				<div class="setting_new_add_btn" onclick="location.href='/member/address/new?USER_EMAIL=${email}';">새 배송지 만들기</div>
			</div>
		</div>
		<!-- 우측(내용) -->
	</div>
</div>