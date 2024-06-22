<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/resources/css/payInfo.css">

<div class="pI_container">
	<div class="pI_common_fix">
		<div class="pI_img">
			<img alt="" src="/resources/images/img_order_step_3.png">
		</div>
	</div>
	
	<div class="pI_info">
		<div class="pI_row">
			<div class="pI_left">주문정보</div>
		</div>
	
		<div class="pI_underline"></div>
		
		<div class="pI_row mb10">
			<div class="pI_left mb10">상품명</div>
			<div class="oC_row">
				<img alt="" src="/zipImg/${oI.pr_img}" style="width: 200px;height: 200px; float: left;">
				<div style="display: inline-block;height: 200px;line-height: 200px;padding-left: 20px;">
				<span style="font-weight: bold;">[${pd.pr_brand}]</span>
				${pd.pr_title}
				</div>
			</div>
			<div class="pI_left mb10" style="margin-top: 10px; display: block;">옵션</div>
			<div class="oC_row" style="display: inline-block;">
				${oL.opt_value}
			</div>
			<div class="pI_right">${oL.od_cnt}개</div>
		</div>
		
		<div class="pI_underline"></div>
		
		<div class="pI_row mb10">
			<div class="pI_left mb10">배송지</div>
			<div class="oC_row">
				<div>${oL.od_name}</div>
				<div>${oL.od_add}</div>
				<div>${oL.od_phone}</div>
			</div>
		</div>
		
		<div class="pI_underline"></div>
		
		<div class="pI_row mb10" style="margin-bottom: 65px;">
			<div class="pI_left">결제금액</div>
			<div class="pI_right">
				<span class="base_color bold-text">
					<fmf:formatNumber value="${oL.od_all_price}" type="number" />
				</span>원
			</div>
		</div>
	</div>
	
	<div class="pI_btn" onclick="location.href='/store/'">확인</div>
</div>