<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 작성자 : 최나현  -->
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/search_result.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/magazine.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/store.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="search_result_wrap">
	<div class="search_form">
		<div class="search_icon">
			<img alt="검색 아이콘" src="/resources/images/search_icon.svg">
		</div>
		<form action="/etc/search_result" method="post" id="search_from">
			<input type="text" name="search_val" value="${search_val}" class="search_val"
			onkeyup="enterKey()" style="width:100%;height:40px;" placeholder="검색어를 입력해주세요.">
		</form>
	</div>
	<div class="search_filter">
		<div class="search_tap" onclick="enterKey()">통합검색</div>
		<div class="search_tap"><a href="/etc/search_mag?search_val=${search_val}">매거진</a></div>
		<div class="search_tap selected"><a href="/etc/search_store?search_val=${search_val}">스토어</a></div>
	</div>
	<div class="search_result_area">
		
		<div class="section">
			<div class="section_title">스토어
			</div>
			
			<div class="store_furniture_group_1">
				<c:forEach items="${item}" var="it" varStatus="status">
				<c:set var="price" value="${it.pr_price - (it.pr_price * (it.pr_sale * 0.01))}"></c:set>
			
				<div class="store_item">
					<a href="/store/view/${it.pr_num }">
						<div class="store_item_img" style="background-image: url('/zipImg/${itemImg[status.index].pr_img}')">
						
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
		</div>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>

<script src="/resources/script/search.js"></script>
</body>
</html>