<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 작성자 : 최나현  -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/magazine_form.css"/>">

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

	<jsp:useBean id="m_form_date" class="java.util.Date" />
	
	<div class=m_form>
		<form method=post enctype="multipart/form-data" id="formId">
			<div>
				작성자 : <input type=text name="user_email" value="${email}">
			</div>
			<div>
				닉네임 : <input type=text name="nickname"value="${nickname}">
			</div>
			<div>
				작성일 :
				<fmt:formatDate value="${m_form_date}" type="date" />
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
				<input type=text name="mag_tag" placeholder="태그">
			</div>
			<div>
				<input type=text name="mag_title" placeholder="타이틀">
			</div>
		
			<!-- summernote 에디터  -->		
			<div class=m_f_content> 
				<textarea id="summernote" name="mag_content"></textarea>
			</div>
			<div class="form-group">
 				 <ul class="list-group">
				</ul>
			</div>
		
			<div>
				대표사진등록
				<input type=file name="file" id="mag_img" class="mag_img" accept="image/*">
				<div class="mag_Preivew_div" id="mag_Preivew_div"></div>
			</div>
			<div class="allInput">
				<input type=button value="다음" id="subBtn" >
				
				<input type=button value="취소" onclick="editorcancel()">
			</div>
		</form>


	</div>
	
	<script src="/resources/script/magazine_form.js"></script>

</body>
</html>