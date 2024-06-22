<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/store/store_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- slick slider -->
<div class="store_promotion" style="min-width:1280px;">
		<div class="slick_list">
			<a href="">
				<img src="/resources/images/test1.jpg">
			</a>
			<a href="">
				<img src="/resources/images/test2.jpg">
			</a>
			<a href="">
				<img src="/resources/images/test3.jpg">
			</a>
			<a href="">
				<img src="/resources/images/test4.jpg">
			</a>
			<a href="">
				<img src="/resources/images/test5.jpg">
			</a>
			<a href="">
				<img src="/resources/images/test6.jpg">
			</a>
			<a href="">
				<img src="/resources/images/test7.jpg">
			</a>
		</div>
</div>


<!-- 상품 목록 -->
<div style="min-width: 1280px;">	
<div class="store_container">
	<div class="store_title">
		<h2>신상품</h2>
	</div>
	<div class="store_furniture_group_1">
		<!-- 상품 목록 6개까지만 출력 -->
		<c:forEach items="${item }" begin="0" end="5" var="it">
		<!-- 할인률 적용한 가격  -->
		<c:set var="price" value="${it.pr_price - (it.pr_price * (it.pr_sale * 0.01))}"></c:set>
		
		<div class="store_item">
			<a href="/store/view/${it.pr_num }">
				<div class="store_item_img" style="background-image: url('/zipImg/${it.pr_img}')">
				</div>
			</a>
			<div class="store_item_description">
				<div class="store_item_name">[${it.pr_brand}] ${it.pr_title}</div>
				<div class="store_item_price">
					<s><fmf:formatNumber value="${it.pr_price }" type="number" /> 원</s>
				</div>
				<div class="store_price_dc">
					<span><fmf:formatNumber value="${price}" type="number" /></span>
					<span class="unit">원</span>
					<span>(${it.pr_sale }%)</span>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	<div class="store_title">
		<h2>
			실시간 베스트
			<a href="">전체보기</a>
		</h2>
	</div>
	<div class="store_furniture_group_2">
		<c:forEach items="${best }" begin="0" end="7" var="bt" varStatus="vs">
		<div class="store_item_2">
			<a href="/store/view/${bt.pr_num }">
				<div class="number" >${vs.count}</div>
				<img src="/zipImg/${bt.pr_img}">
			</a>
			<div class="store_item_description">
				<p>[${bt.pr_brand}] ${bt.pr_title}</p>
			</div>
		</div>
		</c:forEach>
	</div>
	<div class="store_title">
		<h2>전체 상품</h2>
	</div>
	<div class="store_furniture_group_1">
		<c:forEach items="${item}" begin="0" end="20" var="it">
		<c:set var="price" value="${it.pr_price - (it.pr_price * (it.pr_sale * 0.01))}"></c:set>
		
		<div class="store_item">
			<a href="/store/view/${it.pr_num }">
				<div class="store_item_img" style="background-image: url('/zipImg/${it.pr_img}')">
				</div>
			</a>
			<div class="store_item_description">
				<div class="store_item_name">[${it.pr_brand}] ${it.pr_title}</div>
				<div class="store_item_price">
					<s><fmf:formatNumber value="${it.pr_price }" type="number" /> 원</s>
				</div>
				<div class="store_price_dc">
					<span><fmf:formatNumber value="${price}" type="number" /></span>
					<span class="unit">원</span>
					<span>(${it.pr_sale }%)</span>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	<a href="/store/more">
		<div class="store_more">더보기</div>
	</a>
</div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>

<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js"></script>
<script type="text/javascript" src="/resources/script/store.js"></script>

