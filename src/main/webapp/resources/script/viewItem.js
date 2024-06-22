//
//
$(document).ready(function(){
    $('.viewItem_img_wrapper').slick({
  	  slidesToShow: 1,
  	  slidesToScroll: 1,
  	  autoplay: true,
  	  autoplaySpeed: 3000,
  	  prevArrow:'<button type="button" class="slick-prev"><</button>',
	  nextArrow:'<button type="button" class="slick-next">></button>',
  	  arrows: true
    });
  });

function cart() {	// 장바구니
	var email = $(".viewItem_cart_btn").attr("data-user");
	document.getElementById('frm').action = '/member/cart/' + email;
	
	document.getElementById('frm').submit();
}

function submitCheck(index) {	// 옵션 선택 체크
	if(index == 1) { //장바구니 추가
		var email = $(".viewItem_cart_btn").attr("data-user");
		document.getElementById('frm').action = '/member/cart/add';
		
		if($(".viewItem_option").val() != null) {	
			if($(".opt").val() == "" || isNaN($(".opt").val())) { // hidden에서 클래스가 opt인 값이 없거나 NaN일 때
			alert("옵션을 선택해주세요");
			return;
			} else {
				var cnt = $("#opt_price option:selected").attr("data-cnt");
				if(cnt <= 0	){
					alert("재고가 없습니다.");
				}else{
					alert("장바구니에 추가되었습니다.");
					document.getElementById('frm').submit();
				}
				
			}
		} else {
			alert("장바구니에 추가되었습니다.");
			document.getElementById('frm').submit();
		}
	} else if (index == 2) {//바로구매 
		if($(".viewItem_option").val() != null) {	
			if($(".opt").val() == "" || isNaN($(".opt").val())) { // hidden에서 클래스가 opt인 값이 없거나 NaN일 때
			alert("옵션을 선택해주세요");
			return;
			} else {
				var cnt = $("#opt_price option:selected").attr("data-cnt");
				if(cnt<= 0	){
					alert("재고가 없습니다.");
				}else{
					document.getElementById('frm').submit();
				}
				
			}
		} else {
			document.getElementById('frm').submit();
		}
	}
}

function setComma (number) {
    // 정규표현식 : (+- 존재하거나 존재 안함, 숫자가 1개 이상), (숫자가 3개씩 반복)
    var reg = /(^[+-]?\d+)(\d{3})/;

    // 스트링변환
    number += '';
    while (reg.test(number)) {
        // replace 정규표현식으로 3자리씩 콤마 처리
        number = number.replace(reg,'$1'+','+'$2');
    }

    return number;
}

// Q&A 질문 답변 자세히 보기 
$(document).on("click", "#toggle", function() {
	$(this).find(".viewItem_q_description").toggle();
	$(this).find(".viewItem_answer").toggle();

});

