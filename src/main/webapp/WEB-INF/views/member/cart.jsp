<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/cart.css">
<script src="/resources/script/cart.js"></script>

<div class="cart_container">
	<div class="cart_page_title">장바구니</div>
	
	<c:if test="${!cart.isEmpty()}">
	<c:forEach items="${cart}" var="cart">
	<!-- 할인률 적용한 가격  -->
	<c:set var="price" value="${cart.pr_price - (cart.pr_price * (cart.pr_sale * 0.01))}"></c:set>
	<div>
		<div class="cart_item item mt10">
			<div class="cart_row item_content">
				<div class="cart_img_wrapper">
					<div class="cart_checkbox_area">
						<input type="checkbox" id="total" name="total" data-cart-num="${cart.cart_num}"
						data-price="<fmf:parseNumber value="${price}" integerOnly="true"/>"
						data-deli-price="${cart.pr_delivery}" data-cart-opt-num="${cart.cart_opt_num}">
					</div>
					<div class="img" style="background-image: url('/zipImg/${cart.pr_img}');"></div>
				</div>
				<div class="cart_info">
					<div class="cart_item_title">[${cart.pr_brand}] ${cart.pr_title}</div>
					<div class="cart_item_option">옵션 : ${cart.cart_opt_value}</div>
					<div class="cart_item_option">
						<span>수량 : ${cart.cart_cnt}</span>
					</div>
					<div class="cart_row">
						<div class="cart_item_price">
							<span><fmf:formatNumber value="${price}" type="number" /></span>원
						</div>
					</div>
				</div>
			</div>
			<div class="cart_row" style="clear: left;">
				<div class="cart_col1">
					<div class="cart_underline" style="padding-top: 10px;"></div>
				</div>
			</div>
			<div class="cart_btn_closed" onclick="removeCart(${cart.cart_num})">
				X
			</div>
		</div>
	</div>
	
	<div class="cart_item_total">
		<div class="cart_row item_price_area">
			<div class="cart_item_sum_price">
				상품금액
				<span><fmf:formatNumber value="${price}" type="number" /></span>
				원 + 배송비
				<span><fmf:formatNumber value="${cart.pr_delivery}" type="number" /></span>
				원
			</div>
			<div class="cart_item_total_price">
				<span><fmf:formatNumber value="${price + cart.pr_delivery}" type="number" /></span>
				원
			</div>
		</div>
		<div class="cart_underline"></div>
	</div>
	</c:forEach>
	
	<div class="cart_summary">
		<div class="cart_container price_area">
			<div class="cart_row total_item_price">
				<div class="cart_col2">총 상품 금액</div>
				<div class="cart_col2">
					<span class="price">0</span>
					<span>원</span>
				</div>
			</div>
			<div class="cart_row total_delivery_price">
				<div class="cart_col2">총 배송비</div>
				<div class="cart_col2">
					<span class="delivery">0</span>
					<span>원</span>
				</div>
			</div>
			<div class="cart_row total_payment">
				<div class="cart_col2">결제금액</div>
				<div class="cart_col2">
					<span class="cart_total_item_price-value">0</span>
					<span>원</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="cart_underline mb20"></div>
	<form id="frm" method="post" action="/store/delivery_fromCart/${email}">
	<div class="cart_btn_order" onclick="order();">주문하기</div>
			<input type="hidden" name="total" class="total">
			<input type="hidden" name="delivery" class="deli">
	</form>
	</c:if>
	
	<c:if test="${cart.isEmpty()}">
		<div class="cart_row">
			<div style="margin-top: 90px; margin-bottom: 50px; color: #afafaf; text-align: center;">
				장바구니에 상품이 없습니다.
			</div>
		</div>
		
		<div class="cart_row">
			<div style="margin-top: 10px; text-decoration: underline; color: #afafaf; 
			 cursor: pointer; color: #615f5f; text-align: center;" onclick="location.href='/store/store';">
				쇼핑하러 가기
			</div>
		</div>
	</c:if>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>