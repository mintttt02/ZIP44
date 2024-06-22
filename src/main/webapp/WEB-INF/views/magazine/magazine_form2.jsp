<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 작성자 : 최나현  -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/magazine_form.css"/>">
<script src="/resources/script/jquery-ui.min.js"></script>

</head>
<body>

<div class="mag_form2_wrap">

<!-- 검색 테이블 -->
<div id="table_div">
	<table id="search_table">
	<tr>
		<td>
			<!-- 대분류 카테고리 -->
			<select name="bi_category" id="bi_category" onChange="changeSelect(value)">
				<option>대분류</option>
				<option value="b1a">1 - 가구</option>
				<option value="b2b">2 - 가전</option>
				<option value="b3c">3 - 패브릭</option>
				<option value="b4d">4 - 주방</option>
				<option value="b5e">5 - 생활수납</option>
				<option value="b6f">6 - 홈데코</option>
				<option value="b7g">7 - 반려동식물</option>
			</select>
		</td>
		<td>
			<!-- 소분류 카테고리 -->
			<select name="pr_category" id="pr_category">
				<option>소분류</option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan = 2><input type="text" class="search_value" style=width:300px;></td>
		<td><button class="search_btn">검색</button></td>
		<td><button class="fold_btn">접기</button></td>
	</tr>
	<tr>
		<td style=width:140px;>브랜드명</td>
		<td style=width:200px;>제품타이틀</td>
		<td style=width:70px;>제품번호</td>
	</tr>
	
	</table>
</div>


<h3>제품타이틀을 누르면 해당 제품페이지로 이동합니다.</h3>
<h3>태그를 더블클릭하면 사라집니다.</h3>


<form method="post" id="form">
	<input type="text" name="mag_number"  class="mag_num" value="${magDetail.mag_num}">
	<!-- 컨트롤러에서 redirect 하는 과정 중 requestParam 변수명인 mag_num 으로 인한 
	혼동 발생으로 이 input value를 제대로 못 읽어게됨. 변수명 바꿔줌.   -->
	<div>
	타이틀 : 
	<span class="mag_title">${magDetail.mag_title}</span>
	</div>
	내용 : 
	<div class="all_con" > 
		${magDetail.mag_content}
	</div>
	<textarea class="mag_con" name="mag_content"></textarea>
	
	
	<p>대표사진</p>
	<div class="mag_photo">
	<img alt="대표사진" src="/zipImg${magDetail.mag_photo }">
	</div>
	
	<input type="button" value="등록" id="sub">
</form>	
</div>


<script src="/resources/script/magazine_form2.js"></script>
</body>
</html>