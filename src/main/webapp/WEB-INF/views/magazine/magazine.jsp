<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<!-- 작성자 : 최나현  -->
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/magazine.css"/>">

<script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

	<!-- 매거진 전체 div -->
	<div class=magazine>
	<!-- 필터 식별위한 식별값  -->
	<input type="hidden" value="${lableOrderby}"  id="HiddenlableOrderby" class="HiddenlableOrderby">
	<input type="hidden" value="${lable_house}" id="Hiddenlable_house" class="Hiddenlable_house">
	<input type="hidden" value="${lable_size}" id="Hiddenlable_size" class="Hiddenlable_size">
	<input type="hidden" value="${lable_style}" id="Hiddenlable_style" class="Hiddenlable_style">
		<div class=maga_top>
			<div class=maga_slider>
				<div class=maga_s_img
					style="background-image: url('/resources/images/m11.jpg')">
					<div class=maga_s_cover>
						<table class=maga_s_title>
							<tr>
								<td><span>디자이너가 꾸민 단정한 집</span></td>
							</tr>
						</table>
					</div>
				</div>
				<div class=maga_s_img
					style="background-image: url('/resources/images/m2.jpg')">
					<div class=maga_s_cover>
						<table class=maga_s_title>
							<tr>
								<td><span>배란다 확장 후 더 넓어진 우리 집</span></td>
							</tr>
						</table>
					</div>
				</div>
				<div class=maga_s_img
					style="background-image: url('/resources/images/m3.jpg')">
					<div class=maga_s_cover>
						<table class=maga_s_title>
							<tr>
								<td><span>인생에서 '특별한 경험'칸을 채우기</span></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>

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
		
		<div class=maga_list>
			<input type="hidden" value="more" id="nomore">
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
	<script src="/resources/script/magazine.js"></script>

