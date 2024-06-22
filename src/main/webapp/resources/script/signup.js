function signup_checkForm() {
	var user_email = document.getElementById("USER_EMAIL").value;
	var user_nickname = document.getElementById("USER_NICKNAME").value;
	var user_pass = document.getElementById("USER_PASS").value;
	var user_passchk = document.getElementById("USER_PASSCHK").value;
	var emailValue = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if (user_email.length < 1) {		// 이메일 입력하지 않은 경우
		alert('이메일을 입력해주세요.');
		return;
	} else if (!emailValue.test(user_email)) {	// 위에 정의한 문자형식이 아닐 경우
		alert('올바른 이메일 주소를 입력해주세요.');
		return true;
	} else if (user_nickname.length < 1) {	// 닉네임 입력하지 않은 경우
		alert('닉네임을 입력해주세요.');
		return;
	} else if (user_pass.length < 1 ) {		// 비밀번호 입력하지 않은 경우
		alert('비밀번호를 입력해주세요.');
		return;
	} else if (user_pass.length < 8) {
		alert('비밀번호를 8자리 이상 입력해주세요.');	// 비밀번호 8자리 미만인 경우
		return;
	} else if (user_pass != user_passchk) {		// 비밀번호와 비밀번호 확인란이 일치하지 않을 경우
		alert('비밀번호를 다시 한 번 확인해주세요.')
		return;
	} else if (user_pass == user_passchk) {		// 정상적으로 다 입력하면 이메일과 닉네임 중복체크를 실행함
		var userEmail = $("#USER_EMAIL").val();
		var userData = {"USER_EMAIL" : userEmail}
		
		$.ajax({
			type : "POST",
			url : "/member/emailChk",
			data : userData,
			dataType : "json",
			error : function(error) {
				alert('서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.')
			},
			success : function(result) {
			
				if(result == 0) {
					var userNickname = $("#USER_NICKNAME").val();
					var userData = {"USER_NICKNAME" : userNickname}
					
					$.ajax({
						type : "POST",
						url : "/member/nickChk",
						data : userData,
						dataType : "json",
						error : function(error) {
							alert('서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.')
						},
						success : function(result) {
						
							if(result == 0) {
								form.submit();	// id = "form"을 submit시킴
							} else if (result >= 1) {
								alert('이미 존재하는 닉네임입니다. \n다른 닉네임을 사용해주세요.');
								return;
							} else {
								alert('에러가 발생하였습니다.');
								return;
							}
						}
					});
				
				} else if (result >= 1) {
					alert('이미 존재하는 이메일입니다. \n다른 이메일을 사용해주세요.');
					return;
				} else {
					alert('에러가 발생하였습니다.');
					return;
				}
			}
		});
		
	}
	
}
	
	
		
	
