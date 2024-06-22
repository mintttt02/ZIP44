<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 작성자 : 최나현  -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/search_result.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/magazine.css"/>">
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
		<div class="search_tap selected" ><a href="/etc/search_mag?search_val=${search_val}">매거진</a></div>
		<div class="search_tap"><a href="/etc/search_store?search_val=${search_val}">스토어</a></div>
	</div>
	<div class="search_result_area">
		<div class="section">
			<div class="section_title">매거진
			</div>
			<div class=maga_list>
			<!-- 매거진리스트 1개 for문돌릴곳 -->
			<c:forEach items="${listMag}" var="ma">
			<div class=maga_maga data-lastMagNum = "${ma.mag_num}">
				<div class=maga_img  onclick="location.href='/magazine/magazine_detail?mag_num=${ma.mag_num}'"
					style="background-image: url('/zipImg/${ma.mag_photo}')">
					</div>
				<div class=maga_info>
					<div class=maga_i_title>${ma.mag_title}</div>
					<div class=maga_i_tag>
						<a href="#"><span class="mag_house">#${ma.mag_house }</span></a>
						<a href="#"><span class="mag_size">#${ma.mag_size }</span></a>
						<a href="#"><span class="mag_style">#${ma.mag_style }</span></a>
						<a href="#"><span class="mag_tag">#${ma.mag_tag }</span></a>
					</div>
				</div>
			</div>
			</c:forEach>
			</div>
		</div>
	</div>

</div>

<script src="/resources/script/search.js"></script>
</body>
</html>