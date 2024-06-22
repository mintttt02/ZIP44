<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/myOrder.css">
<script src="/resources/script/payInfo.js"></script>

<div class="myOrder_myOrder">
	<div class="myOrder_container">
		<div class="myOrder_title">주문정보</div>
		<div class="myOrder_menu_item" onclick="location.href='/member/orderStatus/0/${email}';">
			<div class="myOrder_menu_title">전체 주문 내역</div>
			<div class="arrow hide">${result}</div>
		</div>
		<div class="myOrder_underline"></div>
		<div class="myOrder_menu_item">
			<div class="myOrder_menu_title">결제대기</div>
			<div class="arrow red">${r_wp}</div>
		</div>
		<div class="myOrder_underline"></div>
		<div class="myOrder_menu_item">
			<div class="myOrder_menu_title">결제완료</div>
			<div class="arrow red">0</div>
		</div>
		<div class="myOrder_underline"></div>
		<div class="myOrder_menu_item">
			<div class="myOrder_menu_title">배송중</div>
			<div class="arrow red">0</div>
		</div>
		<div class="myOrder_underline"></div>
		<div class="myOrder_menu_item">
			<div class="myOrder_menu_title">배송완료</div>
			<div class="arrow red">0</div>
		</div>
		<div class="myOrder_underline"></div>
		<div class="myOrder_menu_item">
			<div class="myOrder_menu_title">반품/취소/교환</div>
			<div class="arrow red">0</div>
		</div>
		<div class="myOrder_underline"></div>
	</div>
</div>