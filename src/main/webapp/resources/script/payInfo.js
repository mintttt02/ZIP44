
// 체크박스를 라디오버튼처럼 동작하게 하는 function
$(document).on("click", 'input[type="checkbox"][name="payWay"]', function() {
	var order_method = $(this).val();
	$(".od_method").val(order_method);
	
	if($(this).prop("checked")) {
		$('input[type="checkbox"][name="payWay"]').prop('checked', false);
		$(this).prop('checked', true);
	} else if($(this).prop("checked", false)) {	// 이미 체크한 값을 해제 못하게 함
		$(this).prop('checked', true);
	}
});
$(document).on('click','#deposit', function() {
	$(".deposit.hide").attr("class", "deposit");
});
$(document).on('click','#card', function() {
	$(".deposit").attr("class", "deposit hide");
});

function deposit_name() {
	var a = $('#deposit_name').val().replace(/ /g, '');
	$('.deposit_name').val(a);
	var deposit_bank = $("#deposit_bank option:selected").val();
	$(".deposit_bank").val(deposit_bank);
}



function depositChk() {
	var email = $(".email").val();
	//console.log("email = " + email);
	if(email != "") {
		if($(".od_method").val() == 1) {
			if($(".deposit_name").val() == "") {
				alert("입금자명을 입력해주세요.");
				return;
			} else {
				alert("주문완료!");
				document.getElementById('frm').submit();
			}
			
		} else {
			alert("주문완료!");
			document.getElementById('frm').submit();
		}
	} else {
		alert("로그인 해주세요.");
	}
}