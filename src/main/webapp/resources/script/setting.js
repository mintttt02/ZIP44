	$(document).ready(function() {
		$("#nickChk_btn").on("click",function(e) {
			e.preventDefault();
			fn_userNickCheck();
		});
	});
	
	function fn_userNickCheck() {
		var userNickname = $("#USER_NICKNAME").val();
		var user_email = $("#user_email").val();
		var userData = {"USER_NICKNAME" : userNickname, "USER_EMAIL":user_email}
		
		if(userNickname.length < 1) {
			alert('아이디를 입력해주시기 바랍니다.');
		} else {
			$.ajax({
				type : "POST",
				url : "/member/nickChk",
				data : userData,
				dataType : "json",
				error : function(error) {
					alert('서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.')
				},
				success : function(result) {
					if(result == 0)
						{
						$("#USER_NICKNAME").attr("readonly", true);
						alert('사용이 가능한 닉네임입니다.');
						document.getElementById("nickChkValue").value = 1;	// 중복 값이 없을 때 중복여부 값 1 대입
						}
					else if (result == 1) {
						alert('이미 존재하는 닉네임입니다. \n다른 닉네임을 사용해주세요.');
					}
					else {
						alert('에러가 발생하였습니다.');
					}
				}
			});
		}
	}
	
	function save_setting() {		// 중복체크 여부확인 function
		var nickChkvalue = document.getElementById("nickChkValue").value; // 중복여부 값
		
		if(nickChkvalue == 0) {		// 중복체크를 안 한 경우
			alert('닉네임 중복체크를 해주세요');
		} else if (nickChkvalue == 1) {	// 중복체크를 한 경우 
			submit();
		}
	}
	
	//프사 썸네일생성
		var file = document.querySelector('#photo_btn'); 
		//input type=file을 가져옴 
		var profile = document.querySelector("#profile");

		file.onchange = function () { //파일이 선택되면
			var fileList = file.files ;
		   
		  //  console.log("filename="+fileList[0].name+"||"+fileList[0].size);
		    
		    // 읽기
		    var reader = new FileReader();
		    reader.readAsDataURL(fileList [0]);

		    //로드 한 후
		    reader.onload = function  () {
		      //썸네일 이미지 생성
		            profile.src = reader.result; 
		    };
		  }
	
