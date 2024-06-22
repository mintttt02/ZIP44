<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/magazine_update.css"/>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>

</head>
<body>
<form method=post action="/magazine/magazine_update_action" enctype="multipart/form-data"> 
<div class=mag_update>

	<input type=text name="mag_num" value="${magDetail.mag_num}">
	<input type=text name="mag_viewcnt" value="${magDetail.mag_viewcnt}">

<div>
작성일 :${magDetail.mag_date}
</div>
<div>
타이틀 : 
<input type=text name="mag_title" value="${magDetail.mag_title}">
태그:
<input type=text name="mag_tag" value="${magDetail.mag_tag}">
</div>

<div>
				카테고리 선택 :

				<div>
					<div class=>
						<label class=>주거형태</label> 
						<select class="" name="mag_house">
							<option value="h0">아파트</option>
							<option value="h1">주택</option>
							<option value="h2">빌라</option>
							<option value="h3">원룸</option>
							<option value="h4">이색공간</option>
							<option value="h5">오피스텔</option>
							<option value="h6">etc</option>
						</select>
					</div>
					<div class=>
						<label class=>평수</label> 
						<select class="" name="mag_size">
							<option value="z0">10평미만</option>
							<option value="z1">10평대</option>
							<option value="z2">20평대</option>
							<option value="z3">30평대</option>
							<option value="z4">40평대</option>
							<option value="z5">40평대 이상</option>
							<option value="z6">해당없음</option>
						</select>
					</div>
					<div class=>
						<label class=>스타일</label> 
						<select class="" name="mag_style">
							<option value="t0">러블리</option>
							<option value="t1">유니크</option>
							<option value="t2">네츄럴</option>
							<option value="t3">앤틱</option>
							<option value="t4">DIY</option>
							<option value="t5">북유럽</option>
							<option value="t6">빈티지</option>
							<option value="t7">모던</option>
							<option value="t8">럭셔리</option>
							<option value="t9">프렌치</option>
							<option value="t10">인더스트리얼</option>
							<option value="t11">미니멀라이즈</option>
							<option value="t12">기타</option>
						</select>
					</div>

				</div>

</div>
<div>
<span>보관함 ${magDetail.mag_savecnt}</span>
<span>댓글 0</span>
</div>
<!-- <h3>제품 태그는 절대로 지우거나 수정하지 마세요! </h3>
<h3>혹시 지우거나 수정했다면 다음 페이지에서 다시 수정해주세요!</h3> -->
<div>
<textarea id="summernote" name="mag_content" >${magDetail.mag_content}</textarea>
</div>
<div>
	대표사진등록
	<input type=file name="file" id="mag_photo_btn" class="mag_photo_btn" accept="image/*">
	<div class="mag_photo_div">
		<img class="mag_photo" alt="대표사진" src="/zipImg${magDetail.mag_photo }">
		<input type=hidden name="mag_photo_original" class="mag_photo_original" value="${magDetail.mag_photo}">
	</div>
</div>

<input type=submit value="다음">
<input type=button value="취소" onclick="location.href='/magazine/magazine'">
</div>
<div class="allInput">
<c:forEach items="${magImg}" var="magImg">
<input type="hidden" name="allImgList" class="allImgList" value="${magImg.mag_img}">
</c:forEach>
</div>
</form>

<script src="/resources/script/magazine_update.js"></script>
</body>
</html>