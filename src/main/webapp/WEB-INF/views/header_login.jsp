<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 작성자 : 조현호 -->

<!-- 로그인 후 회원프로필 사진 및 이메일 -->
<a href="#" style="padding-right: 6px;">
	<div class="header_profile_img_wrapper">
		<img class="profile_img" src="/zipImg/${img }">
	</div> ${nickname}
</a>
<!-- 로그인 후 회원프로필 사진 및 이메일 -->

<!-- 드롭박스 내용 -->
<div class="header_subnavi" id="header_subnavi">
	<ul>
		<li class="header_mem_hover_menu">
			<div class="header_profile_img_wrapper">
				<a href="/member/setting?USER_EMAIL=${email }"><img class="profile_img" src="/zipImg/${img }"></a>
			</div>
		</li>
		<li>
			<div class="header_mem_info">
				<p>
					<a href="/member/setting?USER_EMAIL=${email }">${nickname}</a>
				</p>
				<p>1층 이웃</p>
				<p>적립금</p>
				<p>쿠폰</p>
			</div>
		</li>
		<li><a href="">알림</a>
			<div class="header_notice_cnt">0</div></li>
		<li><a href="">보관함</a>
			<div class="header_notice_cnt">0</div></li>
		<li><a href="/member/my_Order/${email}">주문정보</a>
			<div class="header_notice_cnt">0</div></li>
		<li><a href="">고객센터</a></li>
		<c:if test="${type eq '0' }">
		<li><a href="/admin/admin">관리자 페이지</a>
		</c:if>
		<c:if test="${type eq '0' || type eq '2' }">
		<li><a href="/seller/">판매자 페이지</a>
		</c:if>
		<c:if test="${type eq '0' || type eq '3'}">
		<li><a href="/magazine/editer_page">에디터 페이지</a>
		</c:if>
		<li><a href="/member/logout">로그아웃</a></li>
	</ul>
</div>
<!-- 드롭박스 내용 -->