<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/header.jsp"%>

<div style="width: 500px; margin: 70px auto 0 auto;">
	
	<h2 style="text-align: center;">주문 상세정보</h2>
	<table border="1">
		<tr>
			<td style="width: 203px;">상품명</td>
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
			<td>${od.pay_complete}</td>
		</tr>
		<tr>
			<td>주문처리</td>
			<td>${od.od_complete}</td>
		</tr>
		<tr>
			<td>환불/반품/교환</td>
			<td>${od.od_back}</td>
		</tr>

	</table>
	<a href="/seller/orderUpdate/${od.od_num}">수정</a>
	<a href="/seller/orderList"
		style="float: right; color: #000000; text-decoration: none;"><h3><<
			돌아가기</h3></a>
</div>