<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/orderStatus.css">

<script>
	function cancelOrder(num) {
		if(confirm("정말 취소하시겠습니까?")) {
			location.href='/member/orderDelete/?od_num=' + num;
		}
	}
</script>

<div class="oS_container">
	<div class="oS_title">전체 주문 내역</div>
	<c:if test="${!my.isEmpty()}">
	<c:forEach items="${my}" var="my">
	<div class="oS_item mt10">
		<div class="oS_row">
			<div class="os_order_info">
				<div class="oS_order_date">주문날짜 : ${my.od_date}</div>
				<div class="oS_order_number">주문번호 : ${my.od_num}</div>
				<div class="oS_order_more">
					<a href="/member/orderDetail/${my.od_num}">자세히 보기</a>
				</div>
			</div>
			
			<c:if test="${my.pay_complete eq 0}">
			<div class="os_delivery_status">결제대기중</div>
			</c:if>
			<c:if test="${my.pay_complete eq 1}">
			<div class="os_delivery_status">결제완료</div>
			</c:if>
		</div>
	</div>
	
	<div class="oS_item mt10">
		<div class="oS_row">
			<div class="oS_underline"></div>
		</div>
		
		<div class="oS_row item_content">
			<div class="oS_img_wrapper">
				<img src="/zipImg/${my.pr_img}">
			</div>
			<div class="oS_item_info">
				<div class="oS_item_title">${my.pr_name}</div>
				<div class="oS_option">옵션 : ${my.opt_value} </div>
				<div class="oS_option">
					<span>수량 : ${my.od_cnt}</span>
				</div>
				<c:if test="${my.od_method eq 0}">
				<div class="oS_option">결제수단 : 카드결제</div>
				</c:if>
				<c:if test="${my.od_method eq 1}">
				<div class="oS_option">결제수단 : 무통장입금(입금자명 : ${my.od_deposit_name}, 은행 : ${my.od_deposit_bank})</div>
				</c:if>
				<div class="oS_row">
					<div class="oS_item_price">
						<span><fmf:formatNumber value="${my.od_price}" type="number" /></span> 원
					</div>
				</div>
			</div>
		</div>
		
		<div class="oS_row">
			<div class="oS_btn_area">
				<div class="oS_cancel_btn" onclick="cancelOrder(${my.od_num});">주문취소</div>
			</div>
		</div>
		
		<div class="oS_underline"></div>
	</div>
	</c:forEach>
	</c:if>
	<c:if test="${my.isEmpty()}">
		<div class="outer" style="display: table; width: 100%; margin-top: 120px;">
			<div class="inner">
				<div style="text-align: center;margin:50px 0 50px 0;color:gray;">내역이 없습니다.</div>
			</div>
		</div>
	</c:if>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>