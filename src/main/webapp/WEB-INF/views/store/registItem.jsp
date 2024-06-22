<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="currTime" class="java.util.Date" />

<link rel="stylesheet" href="/resources/css/registItem.css">

<div class="registItem_container">
	<h1>상품 등록</h1>
	<div class="registItem_view">
		<div class="registItem_view_wrap">
			<div class="registItem_item_info">
				<form method="post" enctype="multipart/form-data" id="uploadForm">
				<div class="registItem_info">
				작성자 : ${email } <br> <input type="hidden" name="USER_EMAIL" id="USER_EMAIL" value="${email}">
				날짜 : <fmf:formatDate value="${currTime}" pattern="yyyy-MM-dd"/><br>
				</div>
				
				<div class="registItem_item_category">
				카테고리 선택<br>
				<!-- 대분류 카테고리 -->
				<select name="bi_category" id="bi_category" onChange="changeSelect(this.value)">
					<option>대분류</option>
					<option value="b1a">1 - 가구</option>
				<option value="b2b">2 - 가전</option>
				<option value="b3c">3 - 패브릭</option>
				<option value="b4d">4 - 주방</option>
				<option value="b5e">5 - 생활수납</option>
				<option value="b6f">6 - 홈데코</option>
				<option value="b7g">7 - 반려동식물</option>
				</select>
				<!-- 소분류 카테고리 -->
				<select name="pr_category" id="pr_category">
					<option>소분류</option>
				</select>
				</div>
				
				<div class="registItem_item_brand">
					브랜드명<br>
					<input type="text" name="pr_brand" id="pr_brand">
				</div>
				
				<div class="registItem_item_name">
					타이틀<br>
					<input type="text" name="pr_title" id="pr_title">
				</div>
				
				<div class="registItem_item_cost">
					원가(숫자만 입력하세요)<br>
					<input type="text" name="pr_price" id="pr_price" class="registItem_origin" placeholder="숫자만 입력하세요." onkeyup="remove_space();"><br>
					할인률(숫자만 입력하세요)<br>
					<input type="text" name="pr_sale" id="pr_sale" class="registItem_rate" placeholder="숫자만 입력하세요." onkeyup="remove_space();"><br>
				</div>
				
				<div class="registItem_delivery">
					배송비<br>
					<input type="text" name="pr_delivery" id="deliPay" class="registItem_deli_cost">
					<input type="radio" name="pr_deli_type" id="deli1" value="무료">무료
					<input type="radio" name="pr_deli_type" id="deli1" value="착불">착불
					<input type="radio" name="pr_deli_type" id="deli1" value="선결제" checked>선결제
					<input type="radio" name="pr_deli_type" id="deli1" value="조건부 무료">조건부 무료
				</div>
				
				<div class="registItem_item_option">
					옵션 <label style="color:#afafaf; font-size:11px;">※ 옵션이 없는 경우, 옵션가와 재고를 0으로 해주시기 바랍니다.</label>
					<table id="opt_table">
						<tr>
							<th style="width: 40px"><input type="checkbox" id="checkAll"></th>
							<th style="width: 95px">옵션1</th>
							<th style="width: 95px">옵션값</th>
							<th style="width: 95px">옵션2</th>
							<th style="width: 95px">옵션값</th>
							<th style="width: 95px">옵션3</th>
							<th style="width: 95px">옵션값</th>
							<th style="width: 95px">옵션가격</th>
							<th style="width: 95px">재고</th>
						</tr>
						<tr>
							<td><input type=checkbox name="chkObj" class="select"></td>
							<td><input type="text" class="option1_title" name="option1_title" onkeyup="remove_space();"></td>
							<td><input type="text" class="option1" name="option1" onkeyup="remove_space();"></td>
							<td><input type="text" class="option2_title" name="option2_title" onkeyup="remove_space();"></td>
							<td><input type="text" class="option2" name="option2" onkeyup="remove_space();"></td>
							<td><input type="text" class="option3_title" name="option3_title" onkeyup="remove_space();"></td>
							<td><input type="text" class="option3" name="option3" onkeyup="remove_space();"></td>
							<td><input type="number" class="option_price" id="opt_price" name="option_price" value="0" onkeyup="remove_space();"></td>
							<td><input type="number" class="option_cnt" id="opt_cnt" name="option_cnt" value="0" onkeyup="remove_space();"></td>
						</tr>
					</table>
					<input type="button" id="add_opt" value="옵션추가">
					<input type="button" onclick="remove_opt();" value="옵션 삭제">	
				</div>
				
				<div id="registItem_item_opt"></div>
				
				<div class="registItem_item_quantity">
					재고수량<br>
					<input type="text" name="pr_cnt" id="pr_cnt">
				</div>
				
				<div class="registItem_item_AS">
					A/S, 교환/환불 문의처<br>
					<input type="text" name="pr_phone">
				</div>
				
				<div class="registItem_item_refund">
					반품형태<br>
					<input type="text" name="pr_back"> 
				</div>
				
				<div class="registItem_item_img">	
					<!-- 파일1번 -->
					<input type="file" name="file" id="photo_btn" multiple accept="image/*">
				</div>
				<!-- 파일1번 의 프리뷰-->
				<div class="previewImg_wrapper" id="preview_img"></div>
				
				<div class="registItem_item_detail">
					제품상세<br>
					<!-- 파일2번 -->
					<input type="file" name="pr_content" id="pr_content" multiple accept="image/*">
					<!-- 파일2번 의 프리뷰-->
					<div class="preview_content" id="preview_content"></div>
				</div>
				
				<div class="registItem_item_regist">
				<input type="button" id="reigst_btn" value="등록">
				</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="/resources/script/registItem.js"></script>

