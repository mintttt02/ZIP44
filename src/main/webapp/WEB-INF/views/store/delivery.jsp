<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/delivery.css">
<script src="/resources/script/delivery.js"></script>

<div class="delivery_container">
	<div class="delivery_common_fix">
		<div class="delivery_img">
			<img alt="" src="/resources/images/img_order_step_1.png">
		</div>
	</div>
	
	<div class="delivery_info">
		<table>
		<c:forEach items="${addr}" var="addr">
			<tr>
			<td rowspan='3' style="width:30px;"><input type="checkbox" name="addr_num" id="addr_num" data-idx="${addr.add_num}"></td>
			<td><div class="addD">${addr.add_name}</div></td>
			</tr>
			<tr>
			
			<td><div class="addD">${addr.address}</div></td>
			</tr>
			<tr>
			<td><div class="addD">${addr.add_phone}</div></td>
			</tr>
			</c:forEach>
		</table>
	</div>
				
	<div class="delivery_new">
		<div class="delivery_btn">
			<div class="delivery_newAddr" onclick="window.open('/member/address/new?USER_EMAIL=${email}')">새 배송지 만들기</div>
			<button class="reload" onClick="window.location.reload()">새로고침</button> 
		</div>
	</div>
	<form id="frm" method="post" action="/store/payment">
	<div class="delivery_next_btn" onclick="delichk();">다음</div>
		<input type="hidden" name="add_num" class="addr_num" value="">
		<input type="hidden" name="USER_EMAIL" value="${email}">
		<input type="hidden" name="pr_num" value="${order.pr_num}">
		<input type="hidden" name="order_cnt" value="${order.order_cnt}">
		<input type="hidden" name="total" value="${order.total}">
		<input type="hidden" name="opt_text" value="${order.opt_text}">
		<input type="hidden" name="opt" value="${order.opt}">
		<input type="hidden" name="opt_no" value="${order.opt_no}">
	</form>
</div>

<div class="delivery_underline"></div>