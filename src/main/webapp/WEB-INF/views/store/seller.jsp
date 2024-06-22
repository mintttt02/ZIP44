<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>

<link rel="stylesheet" href="<c:url value="/resources/css/seller.css"/>">
<!-- 작성자 : 조현호 -->

<div class="seller_wrapper">
	<div class="seller_title">판매자 페이지</div>
	<div class="seller_list">
		<div class="seller_list_item" onclick="location.href='/seller/listItem'">
			<div class="seller_menu">상품 리스트</div>
			<div class="arrow">></div>	
		</div>
		<div class="seller_underline"></div>
		
		<div class="seller_list_item" onclick="location.href='/seller/orderList'">
			<div class="seller_menu">주문 리스트</div>
			<div class="arrow">></div>	
		</div>
		<div class="seller_underline"></div>
		
		<div class="seller_list_item" onclick="location.href='/seller/registItem'">
			<div class="seller_menu">상품등록</div>
			<div class="arrow">></div>	
		</div>
		<div class="seller_underline"></div>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>