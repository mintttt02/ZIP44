$(".header_list:nth-child(2)").attr("class", "header_list selected");


$(document).ready(function(){
	drag();
	
})

//태그를 달 이미지 선택 
$(document).on("click", ".all_con img", function(){
	
	//이미지 식별을 위해 선택한 이미지에는 노란색 border와 class="borderYES"를 줌
	//클릭 실행과 동식에 모든 이미지에 border와 class 삭제후 borderNO가 생성됨 
	//이때 생성된 class명과 border는 페이지 종료와 함께 삭제됨. 
		$(".all_con img").css({"border" : "none"});
		$(".all_con img").removeClass("borderYES");
		$(".all_con img").addClass("borderNO");
		
		var imgs = $(this);
		imgs.css({"border" : "5px solid yellow"});
		imgs.removeClass("borderNO");
		imgs.addClass("borderYES");
		
});


// 버튼생성기 
$(document).on("click", ".plus_tag", function(){
	//선택된 이미지 
	var that = $(".all_con .borderYES");

	if( that.length > 0){
		//선택된 이미지 바로 위의 tagdiv
		var tagdiv = that.parent().find(".tag_div");
		
		if(tagdiv.length == 0){
			//console.log("no");
			var html = '<div class="tag_div" ></div>';
			that.before(html);
		}
		
		 
		var pr_num = $(this).parent().parent().find("#pr_num").html();
		var pr_title = $(this).parent().parent().find("#pr_title").html();
		//console.log("pr_num="+pr_num);
		
		if(pr_num != null){
			var str = 
				'<div class="draggable" style="text-align : left">'
			+		'<a href="/store/view/'+pr_num+'"  target="_blank">'
			+		'<img class="tag_img" alt="tag" src="/resources/images/201612102055414nW8VYgEH0.png">'
			+		'</a>'
			+		'<div class="hover title_hover">'+pr_title+'</div>'
			+		'<div class="hover remove_btn">삭제</div>'
			+	'</div>';
			
			that.prev(".tag_div").append(str);
			
			drag();
			
		
		}else{
			alert("상품을 선택해주세요.");
		}
	}else{
		alert("이미지를 선택해주세요.");
	}
})

//태그 달 상품 검색
$(document).on("click", ".search_btn", function(){ 
	$("#search_table tr").remove(".search_tr");
	var pr_category = $("#pr_category").val();
	
	if(pr_category == "소분류"){
		alert("카테고리를 선택해주세요");
		
	}else{
	
	var data ={};
	data["bi_category"] = $("#bi_category").val(); //대분류
	data["pr_category"] = $("#pr_category").val(); //소분류
	data["search_value"] = $(".search_value").val(); 
	
	$.ajax({
		type: 'post',
		 url : '/magazine/form2_search',
		 headers:{
				"Content-Type" : "application/json",
				"X-HTTP-Method_Override" : "POST" },
		dataType : 'json',
		data : JSON.stringify(data),
		error : function(request,status,error){
	    	 console.log("code:"+request.status);
	    	 console.log("message:"+request.responseText);
	    	 console.log("error:"+error);

	    },success : function(data){
			var str = "";
			//console.log("data="+data.length);
			if(data.length !=0){
			$(data).each(function(){
				str += 
			'<tr class="search_tr">'
	+			'<td>'+this.pr_brand+'</td>'
	+			'<td><a href="/store/view/'+this.pr_num+'"  target="_blank" ><div id="pr_title">'+this.pr_title+'</div></a></td>'
	+			'<td><span id="pr_num" >'+this.pr_num+'</span></td>'
	+			'<td><button class="plus_tag">태그추가</button></td>'
	+		'</tr>'
			}); //each end
			
			$("#search_table tr:nth-child(3)").after(str);
		    }else{
		    	alert("데이터가 없습니다.");
		    }
	 
	    }
	});//ajax end
	
	}
});



//등록버튼 실행 
$(document).on("click", "#sub", function(){ 
	$(".all_con img").css({"border" : "none"});
	$(".all_con img").removeClass("borderNO");
	$(".all_con img").removeClass("borderYES");
	var con = confirm("등록하시겠습니까?");
	if(con){
		var all_com = $(".all_con").html()
		//console.log(all_com);
		$(".mag_con").val(all_com);
		$('#form').submit();
		
	}
});

//태그 드래그 
function drag(){
	$(".draggable").draggable({
        cursor:"move", 
        stack:".draggable", 
        opacity:0.7,
        create : function(event, ui) { //생성시 발생하는 이벤트
           // console.log("dragcreat event!");    
           // console.log("index:"+$(this).index());            
        }
    });
}

//태그삭제버튼
$(document).on("click",".remove_btn", function(){
	$(this).parent().remove();
})

//테이블 접기 버튼 
$(document).on("click", ".fold_btn", function(){
	$("#search_table .search_tr").toggleClass('hieght_fix');
	
});


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
	
	if(value == 'b1a') {
		for(i=0; i<furniture.length; i++) {
			option = new Option(furniture[i]); // 소분류의 <option>을 생성하기 위해 furniture[] 배열의 값을 참고해서  변수 option을 생성함
			document.all.pr_category.options[i+1] = option;	// 참고한 option을 실제 <option>에 적용
			pr_category.options[i+1].value = furniture[i];	// 소분류 <option>의 value 값 = furniture[i] 값
			pr_category.options[i+1].text = furniture_txt[i]; // 소분류 <option>의 text 값 = furniture_txt[i] 값
		}
	} else if (value == 'b2b') {
		for(i=0; i<gajeon.length; i++) {
			option = new Option(gajeon[i]);
			document.all.pr_category.options[i+1] = option;
			pr_category.options[i+1].value = gajeon[i];
			pr_category.options[i+1].text = gajeon_txt[i];
		}
	} else if (value == 'b3c') {
		for(i=0; i<fabric.length; i++) {
			option = new Option(fabric[i]);
			document.all.pr_category.options[i+1] = option;
			pr_category.options[i+1].value = fabric[i];
			pr_category.options[i+1].text = fabric_txt[i];
		}
	} else if (value == 'b4d') {
		for(i=0; i<kitchen.length; i++) {
			option = new Option(kitchen[i]);
			document.all.pr_category.options[i+1] = option;
			pr_category.options[i+1].value = kitchen[i];
			pr_category.options[i+1].text = kitchen_txt[i];
		}
	} else if (value == 'b5e') {
		for(i=0; i<storage.length; i++) {
			option = new Option(storage[i]);
			document.all.pr_category.options[i+1] = option;
			pr_category.options[i+1].value = storage[i];
			pr_category.options[i+1].text = storage_txt[i];
		}
	} else if (value == 'b6f') {
		for(i=0; i<deco.length; i++) {
			option = new Option(deco[i]);
			document.all.pr_category.options[i+1] = option;
			pr_category.options[i+1].value = deco[i];
			pr_category.options[i+1].text = deco_txt[i];
		}
	} else if (value == 'b7g') {
		for(i=0; i<animalplants.length; i++) {
			option = new Option(animalplants[i]);
			document.all.pr_category.options[i+1] = option;
			pr_category.options[i+1].value = animalplants[i];
			pr_category.options[i+1].text = animalplants_txt[i];
		}
	}
}
