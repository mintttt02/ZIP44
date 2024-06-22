<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/resources/css/payInfo.css">


<div class="pI_container">
<form id="frm" method="post" action="/store/complete2">
	<div class="pI_common_fix">
		<div class="pI_img">
			<img alt="" src="/resources/images/img_order_step_2.png">
		</div>
	</div>
	
	<div class="pI_info">
		<div class="pI_row">
			<div class="pI_left">결제정보 선택</div>
			<div class="pI_right"></div>
		</div>
		
		<div class="pI_underline"></div>
		
		
		<div class="pI_row mb10">
			<div class="pI_left">결제금액</div>
			<div class="pI_right">
				<span class="pI_bold-text">${total}</span>원
			</div>
		</div>
		
		
		<div class="pI_row mb10" id="depositDiv">
			<div class="pI_left">결제방식</div>
			<div class="pI_right">
				<div>
					<input type="checkbox" name="od_method" id="card" checked value="0">
					<label>카드결제</label>
					<input type="checkbox" name="od_method" id="deposit" value="1">
					<label>무통장입금</label>
				</div>
			</div>
		</div>
		
		<div class="deposit hide">
			<div class="pI_row mb10 deposit_name">
				<div class="pI_deposit_row">입금자명</div>
				<div class="pI_input">
					<input type="text" name="od_deposit_name" id="deposit_name" onkeyup="deposit_name();">
				</div>
			</div>
			<div class="pI_row deposit_bank">
				<div class="pI_deposit_row">입금은행</div>
				<div class="pI_input">
					<select name="od_deposit_bank" id="deposit_bank">
						<option value="국민:67470204-115491 조현호">국민:67470204-115491 조현호</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="pI_underline"></div>
		
		<div class="pI_description">
			최소 결제 가능 금액은 총 결제금액에서 배송비를 죄외한 금액입니다.<br>
			소액 결제의 경우 PG사 정책에 따라 결제 금액 제한이 있을 수 있습니다.
		</div>
		
		<div class="pI_underline"></div>
		
		<div class="pI_point_area">
			<div class="pI_point">
				<div class="pI_left">
					포인트 사용
					<img alt="" src="/resources/images/question.png" style="width: 13px; margin-left: 5px;">
				</div>
				<div class="pI_right">
					<span class="pI_use_point">0 P</span>
				</div>
				<div class="pI_underline"></div>
				<div class="pI_zip_point">
					<div class="pI_left">집꾸미기 포인트</div>
					<div class="pI_right">
						<span class="pI_total_point">0 P</span>
					</div>
				</div>
				<div class="pI_description mt10">
					집꾸미기 포인트는 최소 1,000p 이상 보유시 1p 단위로 사용하실 수 있습니다.
				</div>
				<div class="pI_underline"></div>
			</div>
		</div>
		
		<div class="pI_coupon_area">
			<div class="pI_point">
				<div class="pI_left">
					할인 쿠폰
					<img alt="" src="/resources/images/question.png" style="width: 13px; margin-left: 5px;">
				</div>
				<div class="pI_right">
					사용가능 쿠폰 
					<span>0</span>개
				</div>
			</div>
			<div class="pI_description mt10">
				상품금액이 할인 쿠폰 금액 이상일 경우 사용하실 수 있습니다.
			</div>
			<div class="pI_underline"></div>
		</div>
		
		<div class="pI_row">
			<div class="pI_left bold-text">총 결제예정금액</div>
			<div class="pI_right">
				<span class="base_color bold-text"><fmf:formatNumber value="${total}" type="number" /></span>원
			</div>
		</div>
		
		<div class="pI_underline"></div>
		
		<div class="pI_row mb10">
			<div class="pI_left">주문금액</div>
			<div class="pI_right">
				<span class="bold-text-sm"><fmf:formatNumber value="${total-delivery+discount}" type="number" /></span>원
			</div>
		</div>
		
		<div class="pI_row mb10">
			<div class="pI_left">배송금액</div>
			<div class="pI_right">
				<span class="bold-text-sm"><fmf:formatNumber value="+${delivery}" type="number" /></span>원
			</div>
		</div>
		
		<div class="pI_row mb10">
			<div class="pI_left">할인금액</div>
			<div class="pI_right">
			
				<span class="bold-text-sm"><fmf:formatNumber value="-${discount}" type="number" /></span>원
			</div>
		</div>
		
		<div class="pI_row mb10">
			<div class="pI_left">포인트 사용금액</div>
			<div class="pI_right">
				<span class="bold-text-sm">0</span>원
			</div>
		</div>
		
		<div class="pI_row" style="margin-bottom: 65px;">
			<div class="pI_left">할인쿠폰 사용금액</div>
			<div class="pI_right">
				<span class="bold-text-sm">0</span>원
			</div>
		</div>
	</div>
	
	
	<div class="pI_btn" onclick="depositChk();">결제하기</div>
		<input type="hidden" name="USER_EMAIL" class="email" value="${email}">
		<input type="hidden" name="od_addr_num" class="add_num" value="${add_num}">
		<input type="hidden" name="od_all_price" class="paytotal" value="${total}">
	</form>
</div>

<script src="/resources/script/payInfo.js"></script>



