<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/header.jsp"%>

<div style="width: 500px; margin: 70px auto 0 auto;">
	
	<h2 style="text-align: center;">주문 상세정보</h2>
	<form method="post">
	<table border="1">
		<tr>
			<td style="width: 199px;">상품명</td>
			<td style="width: 400px;">${od.od_pr_name}</td>
		</tr>
		<tr>
			<td>옵션</td>
			<td>${od.opt_value}</td>
		</tr>
		<tr>
			<td>주문자명</td>
			<td>${od.od_name}</td>
		</tr>
		<tr>
			<td>배송지</td>
			<td>${od.od_add_num} ${od.od_add}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${od.od_phone}</td>
		</tr>
		<tr>
			<td>배송메시지</td>
			<td>${od.od_msg}</td>
		</tr>
		<tr>
			<td>배송비</td>
			<td><fmf:formatNumber value="${od.od_d_price}" type="number" /> 원 (${od.pr_deli_type})</td>
		</tr>
		<tr>
			<td>입금자명(무통장입금)</td>
			<td>${od.od_deposit_name}</td>
		</tr>
		<tr>
			<td>총 금액</td>
			<td><fmf:formatNumber value="${od.od_all_price}" type="number" /> 원</td>
		</tr>
		<tr>
			<td>주문날짜</td>
			<td>${od.od_date}</td>
		</tr>
		<tr>
			<td>결제완료여부</td>
			<td>
				<select name="pay_complete">
					<option value="${od.pay_complete}">${od.pay_complete}</option>
					<option value="0">0 - 결제대기중</option>
					<option value="1">1 - 결제완료</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>주문처리</td>
			<td>
				<select name="od_complete">
					<option value="${od.od_complete}">${od.od_complete}</option>
					<option value="0">0 - 배송준비중</option>
					<option value="1">1 - 배송중</option>
					<option value="2">2 - 배송완료</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>환불/반품/교환</td>
			<td>
				<select name="od_back">
					<option value="${od.od_back}">${od.od_back}</option>
					<option value="0">0 - 반품/환불/교환X</option>
					<option value="1">1 - 반품신청</option>
					<option value="2">2 - 환불신청</option>
					<option value="3">3 - 교환신청</option>
					<option value="4">4 - 반품완료</option>
					<option value="5">5 - 환불완료</option>
					<option value="6">6 - 교환완료</option>
				</select>
			</td>
		</tr>

	</table>
	<button type="submit">수정완료</button>
	</form>
	<a href="/seller/orderDetail/${od.od_num}"
		style="float: right; color: #000000; text-decoration: none;"><h3><<
			돌아가기</h3></a>
</div>