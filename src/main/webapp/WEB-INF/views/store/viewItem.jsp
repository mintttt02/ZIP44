<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="//code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js"></script>


<link rel="stylesheet" href="/resources/css/viewItem.css">

<div class="viewItem_contatiner">
	<div class="viewItem_furniture_view">
		<div class="viewItem_wrapper">
			<div class="viewItem_img">
				<div class="viewItem_img_wrapper">
					<c:forEach items="${image}" var="image">
					<img src="/zipImg/${image.pr_img }">
					</c:forEach>
				</div>
			</div>
		</div>
		
		<div class="viewItem_furniture_info">
			<c:set var="price" value="${pd.pr_price - (pd.pr_price * (pd.pr_sale * 0.01))}"></c:set>
			<div class="viewItem_brand">${pd.pr_brand}</div>
			<div class="viewItem_name">${pd.pr_title}</div>
			<div class="viewItem_cost">
				<span class="viewItem_original"><fmf:formatNumber value="${pd.pr_price}" type="number" />원</span><br>
				<span class="viewItem_dc"><fmf:formatNumber value="${price}" type="number" />원</span>
				<span class="viewItem_rate">${pd.pr_sale}%</span>
			</div>
			
			<div class="viewItem_delivery">
				<!-- 배송정보 -->
				<div class="viewItem_info">
					<div class="viewItem_text">배송비</div>
					<div class="viewItem_type"><fmf:formatNumber value="${pd.pr_delivery}" type="number" />원 ${pd.pr_deli_type}</div>
					<input type="hidden" name="delivery_payment">
				</div>
				<!-- 구매후기 -->
				<div class="viewItem_info">
					<div class="viewItem_text">구매후기</div>
					<div class="viewItem_type"></div>
				</div>
			</div>
			
			<!-- 상품옵션 -->
			<c:if test="${!opt.isEmpty()}">
			<div class="viewItem_option">
				<div class="viewItem_opt_title">옵션</div>
				<div class="viewItem_opt_group" style="margin-top: 5px;">
					<div class="viewItem_opt_select">
						<select id="opt_price" onchange="opt_price();" style="">
							<option>선택</option>
							<c:forEach items="${opt}" var="opt">
							<option value="${opt.option_num}" data-price="${opt.option_price}" data-cnt="${opt.option_cnt}">
								${opt.option1_title} - ${opt.option1}, 
								<c:if test="${opt.option2_title != ''}">
								${opt.option2_title} - ${opt.option2}, 
									<c:if test="${opt.option3_title != ''}">
									${opt.option3_title} - ${opt.option3}, 
									</c:if>
								</c:if>
							추가금액 (+${opt.option_price}원), 재고 : ${opt.option_cnt}개
							</option>	
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			</c:if>
			<div class="option_value"></div>
			<!-- 상품수량 -->
			<div class="viewItem_cnt">
				<div class="viewItem_title">수량</div>
				<div class="viewItem_itemQuantity">
					<div class="left">▼</div>
					<div><span class="order_cnt">1</span></div>
					<div class="right">▲</div>
				</div>
			</div>
			<!-- 합계 -->
			<div class="viewItem_total">
				<div>
					<c:set var="total" value="${price + pd.pr_delivery}"></c:set>
					합계 : <span class="price" id="price"><fmf:formatNumber value="${total}" type="number" /></span> 원
				</div>
			</div>
			<form id="frm" method="post" action="/store/delivery/${email}">
			<div class="viewItem_btn">
				<c:if test="${email ne null}">
				<div class="viewItem_cart_btn" data-user="${email}" onclick="submitCheck(1);">장바구니</div>
				</c:if>
				<c:if test="${email eq null}">
				<div class="viewItem_cart_btn" data-user="${email}" onclick="alert('로그인해주세요')">장바구니</div>
				</c:if>
				<div class="viewItem_buy_btn" onclick="submitCheck(2);">바로구매</div>
			</div>
				<input type="hidden" name="USER_EMAIL" value="${email}">
				<input type="hidden" name="pr_num" value="${pd.pr_num}">
				<input type="hidden" name="opt" class="opt" value="">
				<input type="hidden" name="order_cnt" class="order_cnt" value="1">
				<input type="hidden" name="total" class="price" value="<fmf:formatNumber value="${total}" type="number" />">
				<c:if test="${!opt.isEmpty()}">
				<input type="hidden" name="opt_text" class="opt_text">
				<input type="hidden" name="opt_no" class="opt_no" value="">
				</c:if>
			</form>
		</div>
		
	</div>
	
	<!-- 상품 상세정보 -->
	<div class="viewItem_furniture_data">
	</div>
	
	<div class="viewItem_contatiner">
		<div class="viewItem_detail">
			<h2>제품 설명</h2>
		</div>
		<div class="viewItem_view_body">
			<div class="viewItem_detailInfo">
				<h3>상품상세정보</h3>
				<c:forEach items="${pdi}" var="pdi">
				<center>
					<img alt="" src="/zipImg/${pdi.pr_detail_img}">
				</center>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="viewItem_qna">
		<div class="viewItem_commerce_title">
			<h2 style="margin-bottom: 0px;">Q&A</h2>
			<div class="viewItem_qna_wrapper">
				<div onclick="location.href='/store/write_qna/${pd.pr_num}'">Q&A 작성하기</div>
			</div>
		</div>
		
		<c:if test="${!qna.isEmpty()}">
		<c:forEach items="${qna}" var="qna">
		<div class="viewItem_qna_toggle_item" id="toggle">
			<div class="viewItem_row qna-item fix-width">
				<div class="viewItem_col2">
					<span class="question-label">질문</span>
					<div class="question-title">${qna.qq_title}</div>
					<p class="author">${qna.USER_NAME}</p>
					<p class="question-time">${qna.qq_date}</p>
				</div>
				<div class="viewItem_col3 status">답변상태</div>
			</div>
			<div class="viewItem_qna-answer fix-width">
				<div class="viewItem_q_description" style="display: none;">
					<span class="answer_label" style="margin-top: -15px;">질문</span>
					<br>
					${qna.qq_content}
					
				</div>
				<c:if test="${!qna.aa_content eq null}">
				<div class="viewItem_answer" style="display: none;">
					<span class="answer_label">답변</span>
				</div>
				</c:if>
			</div>
		</div>
		</c:forEach>
		</c:if>
		
		<div class="viewItem_row"></div>
		
		<c:if test="${qna.isEmpty()}">
		<div class="viewItem_row">
			<div class="viewItem_col1">작성된 Q&A가 없습니다.</div>
		</div>
		</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
<script src="/resources/script/viewItem.js"></script>
<script>
var price = 0;
var o_price = ${price};
var cnt = 1;	// 상품 수량 변수 1로 초기화 
var quantity = 0;
var total = 0;
var i = 1;	// 상품 옵션 수량 체크 변수

// 상품 수량 감소
$(".left").click(function(e){
    if(cnt <= 1) {	
    	return;
    }
    cnt = parseInt($(".order_cnt").html());
    cnt --;
    i--;
 
    calculatePrice();
});

// 상품 수량 증가
$(".right").click(function(e){
	
    cnt = parseInt($(".order_cnt").html());
    cnt ++;
    i++;

    calculatePrice();
});

function opt_price() {
	price = parseInt($("#opt_price option:selected").attr("data-price"));
    var price_val = $("#opt_price option:selected").text();
   	var option_no = $("#opt_price option:selected").val();
    
    $(".opt_text").val(price_val);
	$(".opt_no").val(option_no);
	calculatePrice();	// 옵션 선택할 때 마다 새로 계산을 해줌
}

function calculatePrice() {
	
	$(".order_cnt").html(cnt);	//
	$(".order_cnt").val(cnt);
    quantity = parseInt($(".order_cnt").html());	
	// 합계 = ${pd.pr_price - (pd.pr_price * (pd.pr_sale * 0.01))} * 수량  + 배송비 + (옵션가 * 옵션 수량)
    total = o_price * quantity + ${pd.pr_delivery} + (price * i);
    order = o_price * quantity + (price * i);
    total = setComma(total);
   
    if(isNaN(price)) {	// 옵션 선택이 되지 않았을 때 옵션가를 제외한 원가 표시
    	$(".order_cnt").html(cnt);
        quantity = parseInt($(".order_cnt").html());
        total = o_price * quantity + ${pd.pr_delivery};
        total = setComma(total);
	}
  
    $(".price").html(total);
    parseInt($(".price").val(total));
    parseInt($(".opt").val(price*i));
}

</script>