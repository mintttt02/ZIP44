<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<style>
	ul li {
		display: inline-block;
	}
</style>

<div style="width:1000px;margin:70px auto 0px auto;">
 <h2 style="text-align:center;">주문리스트</h2>

<table border="1" style="margin: 0 auto;">
	<tr>
		<th style="width:65px;">주문번호</th>
		<th style="width:300px;">상품명</th>
		<th style="width:100px;">주문자명</th>
		<th style="width:100px;">결제금액</th>
		<th style="width:100px;">결제완료여부</th>
		<th style="width:100px">주문처리</th>
		<th style="width:155px">환불/반품/교환</th>	
	</tr>
	<c:forEach items="${order}" var="od">
	<tr>
		<td style="text-align:center;">${od.od_num}</td>
		<td><a href="/seller/orderDetail/${od.od_num}">${od.od_pr_name}</a></td>
		<td style="text-align:center;">${od.od_name}</td>
		<td style="text-align:center;"><fmf:formatNumber value="${od.od_all_price}" type="number" />원</td>
		<td style="text-align:center;">
			<c:choose>
				<c:when test="${od.pay_complete eq 0}">
					결제대기중
				</c:when>
				<c:when test="${od.pay_complete eq 1}">
					결제완료
				</c:when>
			</c:choose>
		</td>
		<td style="text-align:center;">
			<c:choose>
				<c:when test="${od.od_complete eq 0}">
					배송준비중
				</c:when>
				<c:when test="${od.od_complete eq 1}">
					배송중
				</c:when>
				<c:when test="${od.od_complete eq 2}">
					배송완료
				</c:when>
			</c:choose>
		</td>
		<td style="text-align:center;">
			<c:choose>
				<c:when test="${od.od_back eq 0}">
					반품/환불/교환X
				</c:when>
				<c:when test="${od.od_back eq 1}">
					반품신청
				</c:when>
				<c:when test="${od.od_back eq 2}">
					환불신청
				</c:when>
				<c:when test="${od.od_back eq 3}">
					교환신청
				</c:when>
				<c:when test="${od.od_back eq 4}">
					반품완료
				</c:when>
				<c:when test="${od.od_back eq 5}">
					환불완료
				</c:when>
				<c:when test="${od.od_back eq 6}">
					교환완료
				</c:when>
			</c:choose>
		</td>
	</tr>
</c:forEach>	
</table>
<ul style="list-style:none; text-align:center;">
	<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
	<c:if test="${pageMaker.prev }">	
		<li>
			<a href="orderList?page=${pageMaker.startPage - 1 }"></a>
		</li>
	</c:if>
	<!-- 각 페이지의 번호 출력 -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<li
			<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="orderList?page=${idx }">${idx }</a>
		</li>
	</c:forEach>
</ul>
	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
		<li><a href="orderList?page=${pageMaker.endPage + 1 }"></a>
	</c:if>
	<div style="text-align:right;">

	</div>
</div>