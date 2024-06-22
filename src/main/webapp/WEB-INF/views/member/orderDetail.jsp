<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/orderDetail.css">

<script>
	function cancelOrder() {
		if(confirm("정말 취소하시겠습니까?")) {
			location.href='/member/orderDelete/?od_num=${oD.od_num}';
		}
	}
</script>

<div class="oD_container">
	<div class="oD_top">
		<div class="oD_row">
			<div class="oD_date">${oD.od_date}</div>
			<div class="oD_number">주문번호 : ${oD.od_num}</div>
		</div>
	</div>
	
	<div class="oD_order_info">
		<div class="oD_row">
			<div class="oD_col">
				<div class="oD_title">주문자 정보</div>
			</div>
		</div>
		<div class="oD_row">
			<div class="oD_col">
				<div class="oD_underline_space"></div>
			</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">받는 사람</div>
			<div class="oD_right">${oD.od_name}</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">휴대전화</div>
			<div class="oD_right">${oD.od_phone}</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">배송지</div>
			<div class="oD_right">${oD.od_add}</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">배송메세지</div>
			<div class="oD_right">${oD.od_msg}</div>
		</div>
		<div class="oD_row">
			<div class="oD_col">
				<div class="btn_modify" onclick="location.href='/member/address/modify?add_num=${oD.od_addr_num}';">주문자 정보 수정</div>
			</div>
		</div>
	</div>
	
	<div class="oD_underline" style="clear: left;"></div>
	
	<div class="oD_order_info">
		<div class="oD_row">
			<div class="oD_col-2 oD_title">
				<div>총 결제 금액</div>
			</div>
			<div class="oD_col-2 total_price">
				<span><fmf:formatNumber value="${oD.od_all_price}" type="number" /></span> 원
			</div>
		</div>
		<div class="oD_row">
			<div class="oD_col">
				<div class="oD_underline_space"></div>
			</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">상품 금액</div>
			<div class="oD_right"><fmf:formatNumber value="${oD.od_price * oD.od_cnt}" type="number" /> 원</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">배송비</div>
			<div class="oD_right"><fmf:formatNumber value="${oD.od_d_price}" type="number" /> 원</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">할인 금액</div>
			<div class="oD_right"><fmf:formatNumber value="${oD.od_discount}" type="number" /> 원</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">포인트 사용</div>
			<div class="oD_right">0 원</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">쿠폰 사용</div>
			<div class="oD_right">0 원</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">결제수단</div>
			
			<div class="oD_right">
				<c:if test="${oD.od_method eq 0}">
					카드결제
				</c:if>
				<c:if test="${oD.od_method eq 1}">
				무통장입금(입금자명 : ${oD.od_deposit_name})<br>
				${oD.od_deposit_bank}
				</c:if>
			</div>
		</div>
		<div class="oD_row list_item">
			<div class="oD_left">결제상태</div>
			<div class="oD_right payment">
				<c:if test="${oD.pay_complete eq 0}">
					결제대기중
				</c:if>
				<c:if test="${oD.pay_complete eq 1}">
					결제완료
				</c:if>
			</div>
		</div>
	</div>
	
	<div class="oD_underline_space"></div>
	
	<div class="oD_order_info" style="display: inline-block;">
		<div class="oD_row">
			<div class="oD_col">
				<div class="oD_title">주문 상품</div>
			</div>
		</div>
		<div class="oD_item">
			<div class="oD_row item_content">
				<div class="oD_col3 img_wrapper">
					<img alt="" src="/zipImg/${oD.pr_img}">
				</div>
				<div class="oD_col4">
					<div class="oD_item_title">${oD.od_pr_name}</div>
					<div class="oD_item_option">${oD.opt_value}</div>
					<div class="oD_item_option">
						<span>수량 : ${oD.od_cnt}</span>
					</div>
					<div class="oD_item_option red"></div>
					<div class="oD_row">
						<div class="oD_item_price">
							<span><fmf:formatNumber value="${oD.od_all_price}" type="number" /></span> 원
						</div>
					</div>
				</div>
			</div>
			<div class="oD_row">
				<div class="oD_col">
					<div class="btn_cancel" onclick="cancelOrder();">주문취소</div>
				</div>
			</div>
			<div class="oD_row">
				<div class="oD_col">
					<div class="oD_underline"></div>
				</div>
			</div>
		</div>
		<div class="oD_item">
			<div class="oD_row item_price_area">
				<div class="oD_item_sum_price">
					상품금액
					<span class="price_value"><fmf:formatNumber value="${oD.od_all_price}" type="number" /></span>
					원 + 배송비
					<span class="delivery_price"><fmf:formatNumber value="${oD.od_d_price}" type="number" /></span> 원
				</div>
				<div class="oD_item_total_price">
					<span><fmf:formatNumber value="${oD.od_all_price + oD.od_d_price}" type="number" /></span> 원
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>