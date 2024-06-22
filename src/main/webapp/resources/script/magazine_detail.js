$(".header_list:nth-child(2)").attr("class", "header_list selected");

//댓글 작성
$(document).on("click", ".mag_comm_submit", function(){
	
	var mag_num = $(".mag_num").val();
	var mag_com_content = $("#mag_com_content").val();
	var user_email=$("#user_email").val();
	
	if(mag_com_content != "" && user_email != ""){
		var data = {};
		data["user_email"] = user_email;
		data["mag_num"] = mag_num;
		data["mag_com_content"] = mag_com_content;
		
			$.ajax({
				type:'post',
				url:'/magazine/magazine_comment_write',
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method_Override" : "POST" },
					dataType : 'json',
					data : JSON.stringify(data),
					success : function(result){
						if(result != null){
							//console.log("댓글등록완료");
							getCommnetList(mag_num);
						}
					},
					error : function(request,status,error){
				    	 console.log("code:"+request.status);
				    	 console.log("message:"+request.responseText);
				    	 console.log("error:"+error);

				    }
			});
		
	}else if(user_email == ""){
			alert("로그인이 필요합니다.");
		}else if(mag_com_content == ""){
			alert("댓글 내용을 입력해주세요.");
		}
	
});

//jsp 실행될때 댓글 불러옴
	$(document).ready(function(){
		var mag_num = $(".mag_num").val();
		getCommnetList(mag_num);
	});

//매거진 댓글 목록 출력
function getCommnetList(mag_num){
	
	$.getJSON("/magazine/magazine_comment_list/"+mag_num, function(data){
			//console.log(data.length);
			var str = "";
			$(data).each(
					function(){
						var mydate = this.mag_com_date;
						var sysdate = new Date(mydate);
						var date = sysdate.toLocaleDateString();
						
						str += 
							'<div class="mag_comm_item">'
	+					'<input type=hidden class="mag_comm_email" value='+this.USER_EMAIL+'>'
	+					'<input type=hidden class="mag_com_num" value='+this.mag_com_num+'>'
	+					'<table>'
	+						'<tr>'
	+							'<td><!-- 댓쓴이 프로필사진 -->'
	+								'<div class="comm_member_profile">'
	+									'<img src="/zipImg/'+this.USER_IMG+'" alt="댓쓴이프사" onerror="this.src=/resources/images/profile.png ">'
	+								'</div>'
	+							'</td>'
	+							'<td><!-- 댓쓴이 닉네임, 댓글날짜 -->'
	+								'<div class="comm_member_nickname">'
	+									'<a href="">'+this.USER_NICKNAME+'</a>'
	+									'<span class="comm_date">'+date +'</span>'
	+								'</div>'
	+								'<div class="comm_content">'+this.mag_com_content+'</div>'
	+							'</td>'
	+							'<td>'
	+								'<div class="comm_report"  data-comm="'+this.USER_EMAIL+'">'
	+									'<img class="report_img" src="/resources/images/flag.png" alt="신고">'
	+								'</div>'
	+							'</td>'
	+						'</tr>'
	+					'</table>'
	+				'</div>'
					});
			$(".mag_comm_list").html(str);
			update_btn();
			$("#mag_com_content").val("");
		});
}

//매거진 댓글 신고버튼
	$(document).on("click", ".report_img", function(){
		var user_email = $("#user_email").val();
		
		if(user_email != ""){
		
 		var conf = confirm("댓글을 신고하시겠습니까?");
 		if(conf == true){
 			var mag_com_num = $(this).parents(".mag_comm_item").find(".mag_com_num").val();
 			//console.log("신고mag_com_num="+mag_com_num);
 			
 			var data ={}; // 실제로 hashmap으로 보낼 데이터들 object형태 
 			data["USER_EMAIL"] = user_email;
 			data["mag_com_num"] = mag_com_num;
 			
 			
 			$.ajax({
					type:'post',
					url:'/magazine/magCommReport',
					headers:{
						"Content-Type" : "application/json",
						"X-HTTP-Method_Override" : "POST" },
						dataType : 'text',
						data : JSON.stringify(data),
						success : function(result){
							if(result == 2){
								alert("신고를 완료하였습니다.");
							}else if(result == 1){
								alert("이미 신고한 댓글입니다.");
							}else if(result == 3){
								alert("신고에 실패했습니다.");
							}
							
						}
				});
 			
 		}
		}else{
			alert("로그인이 필요합니다.");
		}
		
	});

//댓글수정버튼 삽입
function update_btn(){
	var user_email = $("#user_email").val();//로그인한 이메일
	var type = $("#type").val(); //로그인한 유저 타입 
	var button = "<button class='re_update_open'>수정</button>";
	
	if(user_email != ""){//로그인하면 
		if(type == 0 ){ // 관리자 이메일이면 
			$(".comm_report").append(button); //모든 댓글에 수정버튼 생성 
		}else{
		var report_div = document.getElementsByClassName("comm_report"); //수정버튼이 들어갈 모든 div 배열  
		
		for(var i=0;i<report_div.length;i++){ //모든 div의 길이만큼 비교 
			var data_comm_val = report_div[i].getAttribute("data-comm");  //i번째 div의 data-comm value
			if(data_comm_val == user_email){ //data-comm value가 현재 로그인한 이메일과 같으면 
				var inner = report_div[i].innerHTML; //기존에 div에 있던 코드 
				 report_div[i].innerHTML = inner+button; //기존코드 + 버튼 
			}
		}
	}
}
}

//댓글 수정 팝업 띄우기
$(document).ready(function() {
    $('#re_update').popup();
  });


//댓글 수정 팝업 처리
	$(document).on("click", ".re_update_open", function() {
		
		var reply = $(this).closest(".mag_comm_item"); // 버튼의 부모요소 = <div>

		var mag_num = $(".mag_num").val();
		var mag_com_num = reply.find(".mag_com_num").val();
		var replytext = reply.find(".comm_content").html()
		
		$("#reply_num").val(mag_num); // 글번호
		$("#modal_num").val(mag_com_num); // 댓글번호
		$("#replytext").val(replytext); // 댓글 내용
		
	});
	
	//댓글 삭제 처리
 	$(document).on("click", "#replyDelBtn", function(){
 		
 		var conf = confirm("댓글을 삭제하시겠습니까?");
 		
 		if(conf == true){
 			var mag_num = $("#reply_num").val();
 			var mag_com_num = $("#modal_num").val();
 			var replytext = $("#replytext").val();
	 		
	 		$.ajax({
	 			type : 'delete',
	 			url : '/magazine/commentDelete/'+mag_com_num,
	 			headers : {
	 				"Content-Type" : "application/json",
					"X-HTTP-Method_Override" : "DELETE" 
						},
				dataType : 'text',
	 			success : function(result){
	 			//	console.log("result : "+ result);
	 				if(result != null){
	 					alert("삭제완료");
	 					$('#re_update').popup('hide'); //팝업숨김 
	 					 getCommnetList(mag_num)
	 				}else{
	 					alert("삭제실패");
	 				}
	 			}
	 		});
 		}
 	});
 	//댓글 수정 처리
 	$(document).on("click", "#replyModBtn",function(){
 		var mag_num = $("#reply_num").val();
		var mag_com_num = $("#modal_num").val();
		var mag_com_content = $("#replytext").val();
 		
 		var data = {};
 		data["mag_com_content"] = mag_com_content;
 		
 		$.ajax({
 			type : 'put',
 			url : '/magazine/commnetUpdate/'+mag_com_num,
 			headers : {
 				"Content-Type" : "application/json",
				"X-HTTP-Method_Override" : "PUT" 
					},
			dataType : 'json',
			data : JSON.stringify(data),
 			success : function(result){
 				if(result !=null){
 					alert("수정완료");
 					$('#re_update').popup('hide'); //팝업숨김 
 					 getCommnetList(mag_num)
 				}else{
 					alert("수정실패");
 				}
 			}
 		});
 		
 	});

//매거진 삭제 
function delMag(){

var conf = confirm("매거진을 삭제하시겠습니까?");

	if(conf == true){
		var mag_photo = $(".mag_photo").val();
		var mag_num = $(".mag_num").val();
		//console.log("mag_num="+mag_num);
		
		
		var formData = new FormData();
		formData.append('mag_num', mag_num);
		formData.append('mag_photo', mag_photo);
		
		var allImg = $(".mag_content img");
		
		if(allImg.length > 0){
			for(var i=0; i < allImg.length; i++){
				 var currentFileName = decodeURIComponent(allImg[i].src)
				 //한글파일일때 깨짐방지 디코딩
				 
				formData.append('allImgList', currentFileName);
			}
		}else{
			formData.append('allImgList',"");
			//컨트롤러에서 List<String>으로 받으니까 
			//이미지가 없을때 삭제하면 오류남 그래서 공백이라도 보내줌
			//스토리컨트롤러에서는 List<MultipartFile>으로 받으니까 이미지 없어도 오류안남 
		}
		
		$.ajax({
			type : 'POST',
		    enctype : 'multipart/form-data',
		    processData : false,
		    contentType : false,
		    cache : false,
		    url : '/magazine/magazine_delete',
		    dataType : 'json',
		    data : formData,
		    success : function(result) {
		    	//console.log("result="+result);
		    	if(result==3){
		    		alert("매거진 삭제 성공");
		    		location.href="/magazine/magazine";
		    	}
		    },
		    error : function(request,status,error){
		    	 console.log("code:"+request.status);
		    	 console.log("message:"+request.responseText);
		    	 console.log("error:"+error);
		
		    }
		});

	}
}
