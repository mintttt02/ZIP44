
// select option에 담을 변수
var furniture = new Array('a1', 'a2', 'a3', 'a4', 'a5', 'a6');
var furniture_txt = new Array("a1 - 침실가구", "a2 - 거실가구", "a3 - 주방가구", "a4 - 홈오피스가구", "a5 - 테이블", "a6 - 체어");
var gajeon = new Array('b1', 'b2', 'b3');
var gajeon_txt = new Array("b1 - 생활가전", "b2 - 주방가전", "b3 - 시즌가전");
var fabric = new Array('c1', 'c2', 'c3', 'c4');
var fabric_txt = new Array("c1 - 침구", "c2 - 커튼블라인드", "c3 - 카페트러그", "c4 - 패브릭소품");
var kitchen = new Array('d1', 'd2', 'd3');
var kitchen_txt = new Array("d1 - 주방용품", "d2 - 주방수납", "d3 - 주방소품");
var storage = new Array('e1', 'e2', 'e3', 'e4');
var storage_txt = new Array("e1 - 수납정리", "e2 - 홈케어", "e3 - 욕실용품", "e4 - 생활용품");
var deco = new Array('f1', 'f2', 'f3', 'f4', 'f5', 'f6');
var deco_txt = new Array("f1 - 조명", "f2 - 시계", "f3 - 홈갤러리액자", "f4 - 캔들디퓨저", "f5 - 셀프인테리어(DIY)", "f6 - 라이프데코소품");
var animalplants = new Array('g1', 'g2');
var animalplants_txt = new Array("g1 - 펫", "g2 - 가드닝");

function changeSelect(value) {	// 대분류 select의 value 값을 매개변수로 받음
	document.all.pr_category.length = 1; 
	//console.log(value);
	if(value == 'b1a') {
		for(i=0; i<furniture.length; i++) {
			option = new Option(furniture[i]); // 소분류의 <option>을 생성하기 위해 furniture[] 배열의 값을 참고해서  변수 option을 생성함
			document.all.pr_category.options[i+1] = option;	// 참고한 option을 실제 <option>에 적용
			document.all.pr_category.options[i+1].value = furniture[i];	// 소분류 <option>의 value 값 = furniture[i] 값
			document.all.pr_category.options[i+1].text = furniture_txt[i]; // 소분류 <option>의 text 값 = furniture_txt[i] 값
		}
	} else if (value == 'b2b') {
		for(i=0; i<gajeon.length; i++) {
			option = new Option(gajeon[i]);
			document.all.pr_category.options[i+1] = option;
			document.all.pr_category.options[i+1].value = gajeon[i];
			document.all.pr_category.options[i+1].text = gajeon_txt[i];
		}
	} else if (value == 'b3c') {
		for(i=0; i<fabric.length; i++) {
			option = new Option(fabric[i]);
			document.all.pr_category.options[i+1] = option;
			document.all.pr_category.options[i+1].value = fabric[i];
			document.all.pr_category.options[i+1].text = fabric_txt[i];
		}
	} else if (value == 'b4d') {
		for(i=0; i<kitchen.length; i++) {
			option = new Option(kitchen[i]);
			document.all.pr_category.options[i+1] = option;
			document.all.pr_category.options[i+1].value = kitchen[i];
			document.all.pr_category.options[i+1].text = kitchen_txt[i];
		}
	} else if (value == 'b5e') {
		for(i=0; i<storage.length; i++) {
			option = new Option(storage[i]);
			document.all.pr_category.options[i+1] = option;
			document.all.pr_category.options[i+1].value = storage[i];
			document.all.pr_category.options[i+1].text = storage_txt[i];
		}
	} else if (value == 'b6f') {
		for(i=0; i<deco.length; i++) {
			option = new Option(deco[i]);
			document.all.pr_category.options[i+1] = option;
			document.all.pr_category.options[i+1].value = deco[i];
			document.all.pr_category.options[i+1].text = deco_txt[i];
		}
	} else if (value == 'b7g') {
		for(i=0; i<animalplants.length; i++) {
			option = new Option(animalplants[i]);
			document.all.pr_category.options[i+1] = option;
			document.all.pr_category.options[i+1].value = animalplants[i];
			document.all.pr_category.options[i+1].text = animalplants_txt[i];
		}
	}
}

//재고수량 관리
$("#pr_cnt").on("keyup", function(){
	if($(".option1_title:first").val() != ""){
		var allcnt=0;
		$($(".option_cnt")).each(function(index, item){
			if(item.value != ""){
				allcnt += parseInt(item.value);
			}else{
				alert("옵션 재고를 입력해주세요");
			}
			
		})
		$("#pr_cnt").val(allcnt);
	}
})


	// 옵션 테이블 추가
	var row = $('<tr></tr>');
	var opt_check = $('<td><input type=checkbox></td>')
	var opt1_title = $('<td><input type="text" name="option1_title"></td>');
	var opt1 = $('<td><input type="text" name="option1"></td>');
	var opt2_title = $('<td><input type="text" name="option2_title"></td>');
	var opt2 = $('<td><input type="text" name="option2"></td>');
	var opt3_title = $('<td><input type="text" name="option3_title"></td>');
	var opt3 = $('<td><input type="text" name="option3"></td>');
	var opt_price = $('<td><input type="text" name="option_price"></td>');
	var opt_cnt = $('<td><input type="text" name="option_cnt"></td>');
$("#add_opt").on("click",function(){ 
	 $("#opt_table").append('<tr><td><input type=checkbox name="chkObj" class="select"></td>'
			 				+ '<td><input type="text" class="option1_title" name="option1_title" onkeyup="remove_space();"></td>'
			 				+ '<td><input type="text" class="option1" name="option1" onkeyup="remove_space();"></td><td>'
			 				+ '<input type="text" class="option2_title" name="option2_title" onkeyup="remove_space();"></td>'
			 				+ '<td><input type="text" class="option2" name="option2" onkeyup="remove_space();"></td>'
			 				+ '<td><input type="text" class="option3_title" name="option3_title" onkeyup="remove_space();"></td>'
			 				+ '<td><input type="text" class="option3" name="option3" onkeyup="remove_space();"></td>'
			 				+ '<td><input type="number" class="option_price" id="opt_price" name="option_price" onkeyup="remove_space();"></td>'
			 				+ '<td><input type="number" class="option_cnt" id="opt_cnt" name="option_cnt" onkeyup="remove_space();"></td></tr>');
// 	 $("#opt_table").append('<div id="x_btn">X</div>');
// 		$("#opt_table").append(row);
// 		row.append(opt_check);
// 		row.append(opt1_title);
// 		row.append(opt1);
// 		row.append(opt2_title);
// 		row.append(opt2);
// 		row.append(opt3_title);
// 		row.append(opt3);
// 		row.append(opt_price);
// 		row.append(opt_cnt);
	});

// 체크박스 전체 선택 및 해제
$(document).on("click", "#checkAll", function(){
	if($('#checkAll').is(':checked')) {
		$('.select').prop('checked', true);
	} else {
		$('.select').prop('checked', false);
	}
});
// 개별선택시 최상위 체크박스 해제
$(document).on("click", ".select", function(){
	$('#checkAll').attr('checked', false);
});
// 선택된 옵션 열 삭제
function remove_opt() {
	if($("input[name=chkObj ]").is(":checked")) {
		if(confirm("삭제하시겠습니까?")) {
			for(var i=$("[name='chkObj']:checked").length-1; i>-1; i--) {
				$("[name='chkObj']:checked").eq(i).closest("tr").remove();
			}
		}
	} else {
		alert("선택된 데이터가 없습니다.");
	}
}
// 공백 제거
function remove_space() {
	var pr_price = $('#pr_price').val().replace(/ /g, '');
	$('#pr_price').val(pr_price);
	var pr_sale = $('#pr_sale').val().replace(/ /g, '');
	$('#pr_sale').val(pr_sale);
	var a = $('.option1_title').val().replace(/ /g, '');
	$('.option1_title').val(a);
	var c = $('.option2_title').val().replace(/ /g, '');
	$('.option2_title').val(c);
	var e = $('.option3_title').val().replace(/ /g, '');
	$('.option3_title').val(e);
}
// $("#opt2").on("click",function(){ 
// 	 $(".registItem_item_opt2").append('<input type="text" name="option2">');
// 	});
	
// $("#opt3").on("click",function(){ 
// 	 $(".registItem_item_opt3").append('<input type="text" name="option3">');
// 	});
	
	
	var photo_btn = document.querySelector('#photo_btn'); //파일선택자
	var preview = document.querySelector("#preview_img"); //프리뷰 영역
	var imgFiles = {}; //이미지 임시보관 배열 선언 
	var maxUploadSize = 10 * 1024 * 1024; //10MB
	var imgIndex = 0; //imgFiles의 이미지 순서
	var previewIndex = 0; //이미지 삭제버튼의 data-previewIndex에 해당 배열의 imgIndex를 지정해기위한 변수

	photo_btn.onchange = function () { //파일이 선택되면
		var fileList = photo_btn.files ; //한꺼번에 선택된 파일의 정보 배열
		
		for(var i=0; i < fileList.length; i++){
			
		//	console.log("filename="+fileList[i].name+"||"+fileList[i].size);
			 
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
			              imgDiv.className = "previewImg";
			              
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


	//삭제버튼 만드는 메소드
	function makeDeleteBtn(previewDiv,previewIndex) {
		var deleteBtn = "<button class=registItem_thumb_del data-previewIndex='"+previewIndex+"' >X</button>";
		//data-* 의 형태로 여러가지 값을 넘길수있음. 
		var choicedImg =$(previewDiv).html();
		$(previewDiv).html(choicedImg+deleteBtn); 
	}

	//삭제버튼 누르면 이미지 삭제됨 
	$(document).on("click", ".registItem_thumb_del", function(){
		var previewIndex = $(this).attr("data-previewIndex"); 
		//해당삭제버튼의 data-previewIndex값을 가져옴 = 해당 이미지가 들어있는 imgFiles의 배열넘버 
		//console.log("previewIndex="+previewIndex);
		
		delete imgFiles[previewIndex];
		//삭제
		
		$(this).parent("div").remove();
		//프리뷰 삭제 
		
	})


	var pr_content = document.querySelector('#pr_content'); //파일선택자
	var preview_content = document.querySelector("#preview_content"); //프리뷰 영역
	var imgFiles_content = {}; //이미지 임시보관 배열 선언 
	var maxUploadSize = 10 * 1024 * 1024; //10MB
	var imgIndex_content = 0; //imgFiles의 이미지 순서
	var previewIndex_content = 0; //이미지 삭제버튼의 data-previewIndex에 해당 배열의 imgIndex를 지정해기위한 변수

	pr_content.onchange = function () { //파일이 선택되면
		var fileList = pr_content.files ; //한꺼번에 선택된 파일의 정보 배열
		
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
			 imgFiles_content[imgIndex_content] = fileList[i]; 
			 
			// console.log("imgFiles["+imgIndex_content+"]="+"i="+i);
			 
			 imgIndex_content++;
			 
			    reader.onload = function(e) {
			        //썸네일 이미지 생성
			     		 
			              //이미지+삭제버튼 넣을 div생성
			              var imgDiv = document.createElement('div');
			              imgDiv.className = "previewImg2";
			              
			              //썸네일 이미지 태그 생성 
			              var image = document.createElement('img');
			              image.src = e.target.result;
			              
			              //preview 영역에 DIV 넣음 
			              var previewDiv = preview_content.appendChild(imgDiv);
			             
			              //imgDiv 영역에 image 넣음 
			              previewDiv.appendChild(image);

			              //삭제버튼 메소드 가져옴
			              makeDeleteBtn2(previewDiv,previewIndex_content);
			              previewIndex_content++
			              
			              /*프리뷰에서 이미지를 삭제하면 imgFiles안의 배열값도 같이 삭제하기위해 
			              삭제버튼을 만들때 previewIndex를 사용해서 똑같은 배열넘버로 가져가게함 
			              imgIndex를 쓰지않은 이유는 function안에서는 ++된 imgIndex값이 제대로 안넘어와서임*/
			              
			      };//onload문
		}//for문
	};//onchange끝


	// 제품상세 삭제버튼 만드는 메소드
	function makeDeleteBtn2(previewDiv,previewIndex_content) {
		var deleteBtn = "<button class=registItem_thumb_del2 data-previewIndex='"+previewIndex_content+"' >X</button>";
		//data-* 의 형태로 여러가지 값을 넘길수있음. 
		var choicedImg =$(previewDiv).html();
		$(previewDiv).html(choicedImg+deleteBtn); 
	}

	//삭제버튼 누르면 이미지 삭제됨 
	$(document).on("click", ".registItem_thumb_del2", function(){
		var previewIndex_content = $(this).attr("data-previewIndex"); 
		//해당삭제버튼의 data-previewIndex값을 가져옴 = 해당 이미지가 들어있는 imgFiles의 배열넘버 
	//	console.log("previewIndex="+previewIndex_content);
		
		delete imgFiles_content[previewIndex_content];
		//삭제
		
		$(this).parent("div").remove();
		//프리뷰 삭제 
		
	})
	
		$('#reigst_btn').on('click',function() {
					var user_email = $("#USER_EMAIL").val();
	                var bi_category = $("#bi_category").val();
	                var pr_category = $("#pr_category").val();
	                var pr_brand = $("#pr_brand").val();
	                var pr_title = $("#pr_title").val();
	                var pr_price = $("#pr_price").val();
	                var pr_sale = $("#pr_sale").val();
	                var deliPay = $("#deliPay").val();
	                var opt_price = $(".option_price").val();
	                var opt_cnt = $("#opt_cnt").val();
	                var regNumber = /^[0-9]*$/;
	                var pr_cnt = $("#pr_cnt").val();	        
	                
	                if(user_email == "admin@naver.com" || user_email == "seller@naver.com") {
						if(bi_category == "대분류") {
		                	alert("대분류를 선택해주세요.");
		                	return;
		                } else if (pr_category == "소분류") {
		                	alert("소분류를 선택해주세요.");
		                	return;
		                } else if (pr_brand == "") {
		                	alert("브랜드명을 입력해주세요.");
		                	return;
		                } else if (pr_title == "") {
		                	alert("상품명을 입력해주세요.");
		                	return;
		                } else if (pr_price == "") {
		                	alert("가격을 입력해주세요.");
		                	return;
		                } else if (!regNumber.test(pr_price)) {
		                	alert("가격은 숫자만 입력해주세요.");
		                	return;
		                } else if (pr_sale == "") {
		                	alert("할인률을 입력해주세요.");
		                	return;
		                } else if (!regNumber.test(pr_sale)) {
		                	alert("할인률은 숫자만 입력해주세요.");
		                	return;
		                } else if (deliPay == "") {
		                	alert("배송비를 입력해주세요.\n"
		                			+ "무료일 경우, 입력 값 : 0");
		                	return;
		                } else if (opt_price == "") {
		                	alert("옵션가를 입력해주세요.\n"
		                			+ "무료일 경우, 입력 값 : 0");
		                	return;
		                } else if (pr_cnt == "") {
		                	alert("재고수량을 입력해주세요.");
		                	return;
		                } else if (imgFiles == 0) {
		                	alert("대표 이미지를 첨부해주세요.");
		                	return;
		                } else if (imgIndex_content == 0) {
		                	alert("제품상세 이미지를 첨부해주세요.");
		                	return;
		                }
	                } else {
	                	alert("관리자나 판매자로 로그인 해주세요.");
	                	return;
	                }
			
					var form = $('#uploadForm')[0];
	                var formData = new FormData(form);
	                //전송할 폼 선언후 #uploadForm을 formdata에 넣음 	               
	                      
	                var nameCnt = document.getElementsByClassName("registItem_thumb_del");
	                var nameCnt2 = document.getElementsByClassName("registItem_thumb_del2");
	                //story_thum_del 삭제파일의 previewIndex를 가져오기위해 선언 
	            	
	                for (var j = 0; j < Object.keys(imgFiles).length; j++) {
	                	var index = nameCnt[j].getAttribute("data-previewIndex");
	                	formData.append('imgFiles', imgFiles[index]);
	                }
	                
	                
	                //전송할 이미지 파일 formdata에 추가
	                for (var j = 0; j < Object.keys(imgFiles_content).length; j++) {
	                	var index = nameCnt2[j].getAttribute("data-previewIndex");
	                	
	                	formData.append('imgFiles_content', imgFiles_content[index]);
	                }
	                
	                
	                $.ajax({
	                    type : 'POST',
	                    enctype : 'multipart/form-data',
	                    processData : false,
	                    contentType : false,
	                    cache : false,
	                    url : '/seller/registItem',
	                    dataType : 'JSON',
	                    data : formData,
	                    success : function(result) {
	                 //   	console.log(result);
	                        if (result === -1) {
	                            //alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
	                            // 이후 동작 ...
	                        } else if (result === -2) {
	                           // alert('파일이 10MB를 초과하였습니다.');
	                            // 이후 동작 ...
	                        } else {
	                            alert('상품 등록 성공');
	                            location.href="/store/";
	                        }
	                    },
	                    error:function(request,status,error){
	                   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

	                    }
	                });
	                
	});
