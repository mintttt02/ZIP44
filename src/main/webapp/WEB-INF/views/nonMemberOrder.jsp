<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!-- 작성자 : 조현호 -->
<link rel="stylesheet" href="/resources/css/nonMemberOrder.css">

<div class="nm_wrapper">
	<div class="nm_desc">주문자명과 주문번호를 입력해주세요.</div>
	<div class="nm_container">
		<div class="nm_input_warpper">
			<input type="text" placeholder="주문자명">
		</div>
		<div class="nm_input_warpper">
			<input type="text" placeholder="주문번호">
		</div>
		<div class="nm_input_warpper">
			<input type="text" placeholder="비밀번호">
		</div>
		<div class="nm_btn_wrapper">
			<div class="nm_btn">조회하기</div>
		</div>
	</div>
</div>
<div class="nm_underline"></div>