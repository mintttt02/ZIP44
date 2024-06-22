$(document).on("click", "#addr_num", function() { // id가 addr_num인 체크박스를 클릭하면 발생하는 이벤트
	var addr_num = 0;
	
	if($(this).prop("checked")) {	// 체크박스가 여러개 중에 하나가 선택되면 
		$('input[type="checkbox"][name="addr_num"]').prop('checked', false); // 선택되지 않은 다른 체크박스의 체크는 해제 됨
		$(this).prop('checked', true);	// 선택한 체크박스에는 체크를 해줌
		addr_num = $(this).attr("data-idx");	// data-idx에는 배송지 고유 번호가 들어가있음
		$(".addr_num").val(addr_num);			// addr_num의 값을 hidden value에 넣어줌
	} else if ($(this).prop("checked", false)) {
		addr_num = null;
		$(".addr_num").val(addr_num);
	}
});

function delichk() {	// 배송지 선택 체크
	if($(".addr_num").val() == "") {
		alert("배송지를 선택해주세요.");
		return;
	} else {
		document.getElementById('frm').submit();
	}
}

$(document).ready(function(){
	var inputval = $('input[name="pr_num"]').val();
	
	if(inputval == ""){
		$('input[name="pr_num"]').remove();
		$('input[name="order_cnt"]').remove();
		$('input[name="total"]').remove();
		$('input[name="opt_text"]').remove();
		$('input[name="opt"]').remove();
		$('input[name="opt_no"]').remove();
		document.getElementById('frm').action = '/store/payment_fromCart';
	}
	
	
})
