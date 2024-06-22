$(document).on("click", "#total", function() {
	var sum = 0;
	var count = $("input:checkbox[name=total]:checked").length;
	var price_sum = 0;
	var delivery_sum = 0;
		
		$("input:checkbox[name=total]:checked").each(function() {
			if($(this).is(":checked")) {
				var item_price = parseInt($(this).attr("data-price"));
				var item_delivery = parseInt($(this).attr("data-deli-price"));
				console.log(item_price);
				
				price_sum += item_price;
				delivery_sum += item_delivery;
				sum += item_price + item_delivery;
				
			}
		});
		price_sum = setComma(price_sum);
		delivery_sum = setComma(delivery_sum);
		sum = setComma(sum);
		
		$(".price").html(price_sum);
		$(".delivery").html(delivery_sum);
		$(".deli").val(delivery_sum);
		$(".cart_total_item_price-value").html(sum);
		$(".total").val(sum);
	});
	
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
	
	function removeCart(cartNum) {
		if(confirm("정말 삭제 하시겠습니까?")) {
			location.href = "/member/cart/remove?cart_num=" + cartNum;
		}
	}
	
	function order() {
		if($("input:checkbox[name=total]:checked").length == 0) {
			alert('상품을 선택해주세요.');
			return;
		}
		$("input:checkbox[name=total]:checked").each(function() {
				var cart_number = $(this).attr("data-cart-num");
				var cart_num = '<input type="hidden" name="cartNumList" class="cart_num" value="'+cart_number+'">';
				
				$("#frm").append(cart_num);
				
		});
		
		document.getElementById('frm').submit();
	}
	
	