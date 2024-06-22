<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="<c:url value="/resources/css/search.css" />">

<!-- 작성자 : 조현호,최나현 -->

<div class="search_wrapper" style="height:1022px;">
	<div class="search_title">검색</div>
	<div class="search_form">
		<div class="search_icon">
			<img alt="검색 아이콘" src="/resources/images/search_icon.svg">
		</div>
		<form action="/etc/search_result" method="post" id="search_from">
		<input type="text" name="search_val" class="search_val"onkeyup="enterKey()" style="width:100%;height:40px;" placeholder="검색어를 입력해주세요.">
		</form>
	</div>
	<div class="search_keyword">
		<span class="pipe">|</span>
		<span class="popular selected">인기 검색어</span>
	</div>
	<div class="search_keyword_list">
		<div class="list_item">화장대</div>
		<div class="list_item">침대</div>
		<div class="list_item">행거</div>
		<div class="list_item">원룸</div>
		<div class="list_item">테이블</div>
		<div class="list_item">선반</div>
		<div class="list_item">거울</div>
	</div>
</div>

<script src="/resources/script/search.js"></script>