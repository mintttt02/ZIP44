<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 작성자 : 최나현  -->
<link rel="stylesheet" href="<c:url value="/resources/css/photo.css"/>">
	<script type="text/javascript" src="http://cdn.dontorz.com/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="https://npmcdn.com/masonry-layout@4.0/dist/masonry.pkgd.min.js"></script>


<div class=pohoto_wrap>
	<!-- 필터 식별위한 식별값  -->
	<input type="hidden" value="${lableOrderby}" class="HiddenlableOrderby" id="HiddenlableOrderby">
	<input type="hidden" value="${lable_house}" class="Hiddenlable_house" id="Hiddenlable_house">
	<input type="hidden" value="${lable_size}" class="Hiddenlable_size" id="Hiddenlable_size">
	<input type="hidden" value="${lable_style}" class="Hiddenlable_style" id="Hiddenlable_style">
	
	<div class="page_title">사진 모아보기</div>
		<!-- 필터영역 -->
			<div class=maga_filter>
			<form method="get">
				<div class=maga_f_container>
					<div class=maga_f_s>
						<label class=maga_label id=maga_label1>등록순</label> 
						<select name="lableOrderby" class="maga_selector" onchange="ch_lable1()" id="maga_se1" >
							<option value="mag_num">등록순</option>
							<option value="mag_viewcnt">인기순</option>
						</select>
					</div>
					<div class=maga_f_s>
						<label class=maga_label id=maga_label2>주거형태</label> 
						<select name="lable_house" class="maga_selector" onchange="ch_lable2()" id="maga_se2" >
							<option value="99">전체선택</option>
							<option value="h0">아파트</option>
							<option value="h1">주택</option>
							<option value="h2">빌라</option>
							<option value="h3">원룸</option>
							<option value="h4">이색공간</option>
							<option value="h5">오피스텔</option>
							<option value="h6">etc</option>
						</select>
					</div>
					<div class=maga_f_s>
						<label class=maga_label id=maga_label3>평수</label> 
						<select   name="lable_size" class="maga_selector" onchange="ch_lable3()" id="maga_se3">
							<option value="99">전체선택</option>
							<option value="z0">10평미만</option>
							<option value="z1">10평대</option>
							<option value="z2">20평대</option>
							<option value="z3">30평대</option>
							<option value="z4">40평대</option>
							<option value="z5">40평대 이상</option>
							<option value="z6">해당없음</option>
						</select>
					</div>
					<div class=maga_f_s>
						<label class=maga_label id=maga_label4>스타일</label> 
						<select name="lable_style" class="maga_selector" onchange="ch_lable4()" id="maga_se4" >
							<option value="99">전체선택</option>
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
					<div class=maga_f_s id=maga_f_submit>
						<input type=submit value="적용">
					</div>
				</div>
			</form>
		</div>
		<!-- 필터영역 끝-->
		<div class="item_list"></div>
		
		
</div>

<script src="/resources/script/imagesloaded.pkgd.min.js"></script>
<script src="/resources/script/photo.js"></script>
