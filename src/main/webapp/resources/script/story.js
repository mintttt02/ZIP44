/*작성자 : 최나현*/

//무한스크롤
 	$(document).ready(function () {
 		
 		change_like_if_0(); //좋아요0이면 글자나옴 
 		
 		  $(document).scroll(function() {
 		    var maxHeight = $(document).height(); //전체문서의 길이
 		    var currentScroll = $(window).scrollTop() + $(window).height(); //스크롤위치 + 현재 브라우저의 길이 
 					/*console.log("documentHeight:" + $(document).height() + " | scrollTop:" +
 							$(window).scrollTop() + " | windowHeight: " + $(window).height() );*/
 		    if (maxHeight <= currentScroll) {
 		    	downList();
 		    	
 		    }
 		  })
 		});
 	
 	// 슬라이더 이거 수정시 getimgList의 슬라이더도 똑같이 수정해줄것! 
	$(document).ready(function() {
		var slider = $('.content_gallery_item').bxSlider({
			auto : false,
			autoControls : false,
			pager : false,
			minSlides : 3,
			maxSlides : 3,
			slideWidth:190,
			slideMargin: 10,
			moveSlides : 1,
			infiniteLoop : false, //무한루프
			hideControlOnEnd : true,
			controls : false
		});
	});
 	
 	//스크롤 내렸을때 불러오는 값 출력 
 	function downList(){
 		var last_st_num = $(".storyrepeat .story_item:last").attr("data-st_num"); 
	//	console.log("마지막스토리번호="+last_st_num);
		
		if(last_st_num != 1){
		$.getJSON("/story/scrolldown/"+last_st_num, function(data){
					var str="";
						$(data).each(
								function(){
									var mydate = this.st_date;
									var sysdate = new Date(mydate);
									var date = sysdate.toLocaleDateString();

									str += 
										'<div class="story_item" data-st_num = "'+this.st_num+'">'
		+							'<div class="story_writer">'
		+								'<div class="writer_profile">'
		+									'<img alt="작성자 프로필" src="/zipImg/'+this.USER_IMG+'">'
		+								'</div>'
		+								'<div class="writer_info">'
		+									'<div class="writer_name">'
		+										'<a href="">'+this.USER_NICKNAME+'</a>'
		+									'</div>'
		+									'<div class="writer_datetime">'
		+										date
		+									'</div>'
		+								'</div>'
		+								'<div class="writer_option">'
		+									'<div class="writer_report_btn" data-balloon="신고">'
		+										'<img alt="신고버튼" src="/resources/images/flag.png">'
		+									'</div>'
		+									'<div class="storyDelBtnDiv" data-stWemail="'+this.USER_EMAIL+'">'
		+								'</div>'
		+							'</div>'
		+							'<div class="story_content">'
		+								'<div class="content_description">'
		+									'<pre>'+this.st_content+'</pre>'
		+								'</div>'
		+								'<div class="content_gallery">'
		+											'<div class="content_gallery_item" data-st_num="'+this.st_num+'">'
		+												'<!-- 스토리이미지영역 -->'
		+											'</div>'
		+								'</div>'
		+							'</div>'
		+							'<div class="story_like">'
		+								'<input type="hidden" value="N" class="stroy_data">'
		+								'<img class="like_icon"  alt="좋아요" src="/resources/images/like_icon.png">'
		+								'<span class="like_cnt">'+this.st_like+'</span>'
		+							'</div>'
		+							'<!-- 댓글 영역  -->'
		+							'<div class="story_reply">'
		+									'<div class="reply_write">'
		+											'<input type="hidden" name="st_num" class="st_num" id="st_num" value="'+this.st_num+'">'	
		+										'<table>'
		+											'<tr>'
		+												'<td>'
		+													'<input type="text" name="st_com_content" id="st_com_content" class="reply_input"'
		+													'placeholder="댓글을 입력하세요!">'
		+												'</td>'
		+												'<td style="width:10px;"></td>'
		+												'<td style="width:60px;">'
		+													'<button style="opacity:1" id="reply_write_btn" class="reply_write_btn" >등록</button>'
		+												'</td>'
		+											'</tr>'
		+											'<!-- 댓글목록 출력 -->'
		+											'<tr>'
		+											'<td>'
		+											'<ul id="replies'+this.st_num+'" class="replies"></ul>'
		+											'</td>'
		+											'</tr>'
		+										'</table>'
		+									'</div>'
		+								'<div class="story_reply_wrapper" id="story_reply_wrapper">'
		+								'</div>'
		+							'</div>'
		+							'</div>'
		+						'</div>';
								});//each
						$(".story_item:last").after(str);
						//$(".storyrepeat").append(str);
						commentList(last_st_num); //출력한 스토리에따른 댓글 출력 
					}); //스토리 스크롤다운 끝
				getimgList(last_st_num); //출력한 스토리에 따른 이미지 출력
				
		}else{
			alert("더 불러올 데이터가 없습니다.");
		}
 	}
 	
 	
 	function getimgList(last_st_num){
		$.getJSON("/story/scrolldownImg/"+last_st_num, function(data){ 
			var imgStr="";
			var findSt_num="";
			var thatSt_num="";
			$(data).each(
					function(){
						findSt_num = $(".content_gallery_item[data-st_num='"+this.st_num+"']");
						thatSt_num = findSt_num.attr("data-st_num");
						if(thatSt_num == this.st_num){
							imgStr = 	
								'<div class=sliderDiv>'
							+		'<img alt="storyImg" src="/zipImg'+this.st_img+'">'
							+	'</div>';
							
							findSt_num.append(imgStr);
					
						}
					});//each
			
			$('.content_gallery_item').bxSlider({
				auto : false,
				autoControls : false,
				pager : false,
				minSlides : 3,
				maxSlides : 3,
				slideWidth:190,
				slideMargin: 10,
				moveSlides : 1,
				infiniteLoop : false, //무한루프
				hideControlOnEnd : true,
				controls : false
			});
			
		}); //이미지스크롤다운 끝
		
 	}
 	
	//story.jsp 실행될때 댓글 불러옴
 	$(document).ready(function(){
 		var number = document.getElementsByName("st_num");
		var st_num;
	
		for(var i=0;i<number.length;i++){
			//console.log("스토리번호="+number[i].value);
			st_num = number[i].value;
			getAllList(st_num);
		}
 	});
 	
 	
 	// 스크롤 내려서 불러온 글에 따른 댓글 불러옴 
 	function commentList(last_st_num){
		var number = document.getElementsByName("st_num");
			for(var i=last_st_num-1;i>last_st_num-11;i--){
				//console.log("스토리번호="+i);
				getAllList(i);
			}
 	}
 	
 	
 	//댓글 목록 처리
 	function getAllList(st_num){
 		$.getJSON("/comment/commentAll/"+st_num, function(data){
 			//console.log(data.length);
 			var str = "";
 			$(data).each(
 					function(){
 						str += 
 								"<li data-st_com_num = '" +this.st_com_num+"' class='replyLi' >"
 	 						+"<input type=hidden class='st_comm_email' value='"+this.USER_EMAIL+"' >"
 							+"<input type=hidden class='st_st_num' value='"+st_num+"' >"
 							+"<input type=hidden class='st_com_num ' value='"+this.st_com_num +"' >"
 	 						+ '<img class="comWImg" alt="댓쓴이 프로필" src="/zipImg/'+this.USER_IMG+'">'
 	 						+ this.USER_NICKNAME  +  "    :  "  
 	 						+ "<input type=text name=st_com_content class=st_com_content value='"+this.st_com_content+"'>"
 	 						+"<button class='st_com_report'>신고</button>"
 	 						+"<div class='comm_report' data-comm='"+this.USER_EMAIL+"'>" 
 	 						+		"</div>"
 	 						+"</li>"
 					});
 			$("#replies"+st_num).html(str);
 			update_btn(); //수정버튼 삽입
 		});
 		//스토리가 완전히 로딩된 후 메소드를 실행시키기 위해 여기에서 호출함.
 		story_del_btn(); //스토리 삭제버튼 삽입 
 		change_like_if_0(); //좋아요0이면 글자나옴 
 	}
 
 	
 	
 	//댓글수정버튼 삽입
 	function update_btn(){
 		var user_email = $("#user_email").val();//로그인한 이메일
 		var type = $("#type").val(); //로그인한 유저 타입 
 		var button = "<button class='re_update_open'>수정</button>";
 		
 		if(user_email != ""){//로그인하면 
 			
 			var report_div = document.getElementsByClassName("comm_report"); //댓글수정버튼이 들어갈 모든 div 배열  
 			
 			if(type == 0 ){ // 관리자 이메일이면 
 				for(var i=0;i<report_div.length;i++){ 
 					report_div[i].innerHTML = button; 
 				}
 				
 			}else{
 			
	 			for(var i=0;i<report_div.length;i++){ //모든 div의 길이만큼 비교 
	 				var data_comm_val = report_div[i].getAttribute("data-comm");  //i번째 div의 data-comm value
	 				if(data_comm_val == user_email){ //data-comm value가 현재 로그인한 이메일과 같으면 
	 					 report_div[i].innerHTML = button; 
	 				}
	 			}
 			}
 		}
 	}
 	//스토리삭제버튼 삽입
 	function story_del_btn(){
 		var user_email = $("#user_email").val();//로그인한 이메일
 		var type = $("#type").val(); //로그인한 유저 타입 
 		var del_btn = "<span class='storyDeleteBtn' ><img src='/resources/images/if_trash_bin_1370026.png'></span>";
 		
 		if(user_email != ""){
 			var del_div = document.getElementsByClassName("storyDelBtnDiv"); //스토리 삭제버튼이 들어갈 모든 div 배열  
 			if(type== 0){
 				$(".storyDelBtnDiv").html(del_btn);
 				
 			}else{
 				for(var i=0;i<del_div.length;i++){ //모든 div의 길이만큼 비교 
	 				var stWemail = del_div[i].getAttribute("data-stWemail");  //i번째 div의 data-comm value
	 				if(stWemail == user_email){ //data-comm value가 현재 로그인한 이메일과 같으면 
	 					del_div[i].innerHTML = del_btn; 
	 				}
	 			}
 				
 			}
 		}
 	}
 	
 	//스토리 신고버튼
 	$(document).on("click", ".writer_report_btn", function(){
 		var user_email = $("#user_email").val();
 		
 		if(user_email != ""){
 		
	 		var conf = confirm("스토리를 신고하시겠습니까??");
	 		if(conf == true){
	 			var st_num = $(this).parents(".story_item").attr("data-st_num");
	 		//	console.log("신고st_num="+st_num);
	 			
	 			var data ={}; // 실제로 hashmap으로 보낼 데이터들 object형태 
	 			data["USER_EMAIL"] = user_email;
	 			data["st_num"] = st_num;
	 			
	 			
	 			$.ajax({
 					type:'post',
 					url:'/story/stReport',
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
 	//댓글 신고버튼
 	$(document).on("click", ".st_com_report", function(){
 		var user_email = $("#user_email").val();
 		
 		if(user_email != ""){
 		
	 		var conf = confirm("댓글을 신고하시겠습니까?");
	 		if(conf == true){
	 			var st_com_num = $(this).parent().attr("data-st_com_num");
	 			//console.log("신고st_com_num="+st_com_num);
	 			
	 			var data ={}; // 실제로 hashmap으로 보낼 데이터들 object형태 
	 			data["USER_EMAIL"] = user_email;
	 			data["st_com_num"] = st_com_num;
	 			
	 			
	 			$.ajax({
 					type:'post',
 					url:'/comment/st_com_report',
 					headers:{
 						"Content-Type" : "application/json",
 						"X-HTTP-Method_Override" : "POST" },
 						dataType : 'text',
 						data : JSON.stringify(data),
 						success : function(result){
 							if(result == 2){
 								alert("신고를 완료하였습니다.");
 							}else if(result == 1){
 								alert("이미 신고한 스토리입니다.");
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
 	
 	// *** ajax로 불러와서 원래 코드에는 없는 내용이 출력되었을때 
 	//$(document).on("click", '.reply_write_btn', function(){ 의 형태로 이벤트를 위임해줘야 이벤트실행됨 
 	//https://brunch.co.kr/@ourlove/69 보낼때 hashmap타입으로 변환 
 	//- data로 바꿔서 JSON.stringify(data)로 보내버림 
 	
 	// 댓글 작성 메소드
 	 	$(document).on("click", '.reply_write_btn', function(){
 	 		var that = $(this);
 	 		var st_num = $("input[name=st_num]", that.closest('div')).val(); //getalllist를 위해서 선언
 			var st_com_content = $("input[name=st_com_content]", // 댓글 내용이 없을때 튕기기 위해서 선언 
 					that.closest('div')).val();
 			var user_email = $("input[name=USER_EMAIL]").val();
 			
 			if(st_com_content != "" && user_email != ""){

 			var data ={}; // 실제로 hashmap으로 보낼 데이터들 object형태 
 			data["user_email"] = user_email;
 			data["st_num"] = $("input[name=st_num]", that.closest('div')).val();
 			data["st_com_content"] = $("input[name=st_com_content]", that.closest('div')).val();
 			
 				$.ajax({
 					type:'post',
 					url:'/comment/comment',
 					headers:{
 						"Content-Type" : "application/json",
 						"X-HTTP-Method_Override" : "POST" },
 						dataType : 'json',
 						data : JSON.stringify(data),
 						success : function(result){
 							if(result != null){
 							//	console.log("댓글등록완료");
 								getAllList(st_num);
 								$("#st_com_content").val("");
 							}
 						}
 				});
 			}else if(user_email == ""){
 				alert("로그인이 필요합니다.");
 			}else if(st_com_content == ""){
 				alert("댓글 내용을 입력해주세요.");
 			}
 		}); 
 	 	
 	 //댓글 수정 팝업 처리
 	 	$(document).on("click", ".re_update_open", function() {
 			
 			var reply = $(this).closest(".replyLi"); // 버튼의 부모요소 = <li>

 			var st_st_num = reply.find(".st_st_num").val();
 			var st_com_num = reply.find(".st_com_num").val();
 			var replytext = reply.find(".st_com_content").val()
 			
 			$("#reply_num").val(st_st_num); // 글번호
 			$("#modal_num").val(st_com_num); // 댓글번호
 			$("#replytext").val(replytext); // 댓글 내용
 			
 		});
 	 	
 	//댓글 삭제 처리
 	$(document).on("click", "#replyDelBtn", function(){
 		
 		var conf = confirm("댓글을 삭제하시겠습니까?");
 		
 		if(conf == true){
	 		var st_num = $("#reply_num").val();
	 		var st_com_num = $("#modal_num").val();
	 		var st_com_content = $("#replytext").val();
	 		//console.log("st_num"+st_num);
	 		//console.log("st_com_num"+st_com_num);
	 		//console.log("st_com_num"+st_com_num);
	 		
	 		
	 		$.ajax({
	 			type : 'delete',
	 			url : '/comment/commentDelete/'+st_com_num,
	 			headers : {
	 				"Content-Type" : "application/json",
					"X-HTTP-Method_Override" : "DELETE" 
						},
				dataType : 'text',
	 			success : function(result){
	 				//console.log("result : "+ result);
	 				if(result != null){
	 					alert("삭제완료");
	 					$('#re_update').popup('hide'); //팝업숨김 
	 					getAllList(st_num);
	 				}else{
	 					alert("삭제실패");
	 				}
	 			}
	 		});
 		}
 	});
 	
 	//댓글 수정 처리
 	$(document).on("click", "#replyModBtn",function(){
 		var st_num = $("#reply_num").val();
 		var st_com_num = $("#modal_num").val();
 		var st_com_content = $("#replytext").val();
 		
 		var data = {};
 		data["st_com_content"] = st_com_content;
 		
 	//	console.log("st_com_num="+st_com_num);
 		$.ajax({
 			type : 'put',
 			url : '/comment/commnetUpdate/'+st_com_num,
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
 					getAllList(st_num);
 				}else{
 					alert("수정실패");
 				}
 			}
 		});
 		
 	});
 	//수정 팝업 띄우기
    $(document).ready(function() {
        $('#re_update').popup();
      });
 	
    //스토리 삭제버튼 
 	$(document).on("click", ".storyDeleteBtn", function(){
 		var conf = confirm("스토리를 삭제하시겠습니까?");

 		if(conf == true){
	 		var locationNum = $(this).closest('.story_item');
	 		var st_num = locationNum.attr("data-st_num");
	 //		console.log("st_num="+st_num);
	 		
	 		location.href="/story/storyDelete?st_num="+st_num;
 		}
 	})
 	
 	//좋아요
 	$(document).on("click", ".like_icon", function(){
 		var user_email = $("#user_email").val();
 		var st_num = $(this).parents(".story_item").attr("data-st_num");
 		var that = $(this);
 		if(user_email != ""){
 			that.attr('src','/resources/images/like_after.png');
 			var likeCnt = that.parent().find(".like_cnt").html();
 			
 			if(likeCnt == "제일 먼저 좋아요를 눌러주세요!"){ //like가 0이면
 				likeCnt = 0;
 			}
 			$.ajax({
 				url : '/story/updateLike?st_num='+st_num+'&&user_email='+user_email,
 				type:'post',
 				success:function(result){
 					
 					if(result == 1){
 						alert("이미 좋아요 한 스토리입니다.");
 					}else{
 						likeCnt++;
 						that.parent().find(".like_cnt").html(likeCnt);
 					}
 					
 				},error : function(request,status,error){
			    	 console.log("code:"+request.status);
			    	 console.log("message:"+request.responseText);
			    	 console.log("error:"+error);
			    }

 			});

 			
 		}else{
 			alert("로그인이 필요합니다.");
 		}
 	});
 	
 	//좋아요0이면 글자나옴 
 	function change_like_if_0(){
 		var all_like = document.getElementsByClassName("like_cnt");
 		
 		for(var i=0; i<all_like.length;i++){
 			var like_cnt = all_like[i].innerHTML;
 	 		if(like_cnt == 0){
 	 			all_like[i].innerHTML="제일 먼저 좋아요를 눌러주세요!";
 	 		}
 		}
 		
 	}
 	
 	
 	
 	//https://developer.mozilla.org/ko/docs/Web/HTML/Element/Input/file
//------------------------------------------------------------------------------------------------------
 	//이미지 파일 업로드 && 미리보기
 	
 	
//https://developer.mozilla.org/ko/docs/Web/HTML/Element/Input/file
//http://programmingsummaries.tistory.com/367
//http://extracold.tistory.com/40
//자바스크립트와 제이쿼리가 섞여있으니 수정시 조심할것! 
$(".header_list:nth-child(5)").attr("class", "header_list selected");

var photo_btn = document.querySelector('#photo_btn'); //파일선택자
var preview = document.querySelector("#preview"); //프리뷰 영역
var imgFiles = {}; //이미지 임시보관 배열 선언 
var maxUploadSize = 10 * 1024 * 1024; //10MB
var imgIndex = 0; //imgFiles의 이미지 순서
var previewIndex = 0; //이미지 삭제버튼의 data-previewIndex에 해당 배열의 imgIndex를 지정해기위한 변수

photo_btn.onchange = function () { //파일이 선택되면
	var fileList = photo_btn.files ; //한꺼번에 선택된 파일의 정보 배열
	
	for(var i=0; i < fileList.length; i++){
		
		//console.log("filename="+fileList[i].name+"||"+fileList[i].size);
		 
		 if(fileList[i].size > maxUploadSize){ //파일사이즈 거름망
			 alert("파일의 크기가 10MB가 초과하였습니다.");
			 continue;
		 }
		 
		 //선택된 파일읽기
		 var reader = new FileReader();
		 reader.readAsDataURL(fileList [i]);
		 
		 //imgFiles의 배열에 파일을 넣음. 
		 //imgIndex는 다음번 선택때 배열값이 유지되기 위해서 따로선언한 변수사용한것. 
		 imgFiles[imgIndex] = fileList[i]; 
		 
		// console.log("imgFiles["+imgIndex+"]="+"i="+i);
		 
		 imgIndex++;
		 
		    reader.onload = function(e) {
		        //썸네일 이미지 생성
		     		 
		              //이미지+삭제버튼 넣을 div생성
		              var imgDiv = document.createElement('div');
		              imgDiv.className = "previewImgDiv";
		              
		              //썸네일 이미지 태그 생성 
		              var image = document.createElement('img');
		              image.src = e.target.result;
		              
		              //preview 영역에 DIV 넣음 
		              var previewDiv = preview.appendChild(imgDiv);
		             
		              //imgDiv 영역에 image 넣음 
		              previewDiv.appendChild(image);

		              //삭제버튼 메소드 가져옴
		              makeDeleteBtn(previewDiv,previewIndex);
		              previewIndex++
		              
		              /*프리뷰에서 이미지를 삭제하면 imgFiles안의 배열값도 같이 삭제하기위해 
		              삭제버튼을 만들때 previewIndex를 사용해서 똑같은 배열넘버로 가져가게함 
		              imgIndex를 쓰지않은 이유는 function안에서는 ++된 imgIndex값이 제대로 안넘어와서임*/
		              
		      };//onload문
	}//for문
};//onchange끝


//프리뷰이미지 삭제버튼 만드는 메소드
function makeDeleteBtn(previewDiv,previewIndex) {
	var deleteBtn = "<button class=story_thum_del data-previewIndex='"+previewIndex+"' >X</button>";
	//data-* 의 형태로 여러가지 값을 넘길수있음. 
	var choicedImg =$(previewDiv).html();
	$(previewDiv).html(choicedImg+deleteBtn); 
}

//프리뷰이미지 삭제버튼 누르면 이미지 삭제됨 
$(document).on("click", ".story_thum_del", function(){
	var previewIndex = $(this).attr("data-previewIndex"); 
	//해당삭제버튼의 data-previewIndex값을 가져옴 = 해당 이미지가 들어있는 imgFiles의 배열넘버 
	//console.log("previewIndex="+previewIndex);
	
	delete imgFiles[previewIndex];
	//삭제
	
	$(this).parent("div").remove();
	//프리뷰 삭제 
	
})

//다중파일업로드
 $(document).ready(function() {
            $('#create_btn').on('click',function() {  
            	
                var formData = new FormData();
                //전송할 폼 선언후 #uploadForm을 formdata에 넣음 
                
                //전송할 폼 내용 formdata에 추가
                var USER_EMAIL = $("#user_email").val();
                var st_content = $("#st_content").val();
                
                formData.append('USER_EMAIL', USER_EMAIL);
                formData.append('st_content', st_content);
                
                var nameCnt = document.getElementsByClassName("story_thum_del");
                //story_thum_del 삭제파일의 previewIndex를 가져오기위해 선언 
            	
                //전송할 이미지 파일 formdata에 추가
                for (var j = 0; j < Object.keys(imgFiles).length; j++) {
                    //formData 공간에 files라는 이름으로 파일을 추가한다.
                    //동일명으로 계속 추가할 수 있다.
                	var index = nameCnt[j].getAttribute("data-previewIndex");
                	
                	formData.append('imgFiles', imgFiles[index]);
                	
                	/*index를 j로 하지않은 이유는 프리뷰에서 어떤 이미지를 삭제했을때 
                	배열길이는 줄어들었지만 지정된 배열넘버는 그대로이기 때문에 
                	그 이미지를 삭제한 갯수만큼 맨 뒤의 이미지값들이 넘어가지 않음.
                	그래서 삭제버튼에 지정된 배열넘버를 가져와서 넣어줌.*/
                }
                
                $.ajax({
                    type : 'POST',
                    enctype : 'multipart/form-data',
                    processData : false,
                    contentType : false,
                    cache : false,
                    url : '/story/story',
                    dataType : 'JSON',
                    data : formData,
                    success : function(result) {
                        if (result === -1) {
                            //alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
                            // 이후 동작 ...
                        } else if (result === -2) {
                           // alert('파일이 10MB를 초과하였습니다.');
                            // 이후 동작 ...
                        } else {
                            alert('스토리 등록 성공');
                            location.href="/story/story";
                        }
                    }
                });//ajax끝
            });//onclick끝
        });

 	
 
 	
 	
 	
 	
 	
 	
 	
 	
 	