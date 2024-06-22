$(".header_list:nth-child(4)").attr("class", "header_list selected");

//무한스크롤
$(document).ready(function () {
	var lableOrderby = $(".HiddenlableOrderby").val();
	var lable_house = $(".Hiddenlable_house").val();
	var lable_size = $(".Hiddenlable_size").val();
	var lable_style = $(".Hiddenlable_style").val();
	

	if(lableOrderby != ""){ //필터로 걸러진 매거진이라면 라벨바꿈
		$("#maga_se1").val(lableOrderby);
		ch_lable1();
	}
	if(lable_house != ""){
		$("#maga_se2").val(lable_house);
		ch_lable2();
	}
	if(lable_size != ""){
		$("#maga_se3").val(lable_size);
		ch_lable3();
	}
	if(lable_style != ""){
		$("#maga_se4").val(lable_style);
		ch_lable4();
	}
	
	$masonry = $(".item_list").masonry({
		itemSelector : ".each_item",
		columnWidth : 300,
		gutter : 10,
		fitWidth : true,
		transitionDuration : '0.8s',
	});

	if(lableOrderby != "" || lable_house != "" || 
	  lable_size != "" || lable_style != ""  ) {
		  	filter_getPhoto();
		 	}else{
		 		getPhoto();
		 	}
	
	
	$(document).scroll(function() {
 		  var maxHeight = $(document).height(); //전체문서의 길이
 		  var currentScroll = $(window).scrollTop() + $(window).height(); //스크롤위치 + 현재 브라우저의 길이 
 					/*console.log("documentHeight:" + $(document).height() + " | scrollTop:" +
 							$(window).scrollTop() + " | windowHeight: " + $(window).height() );*/
 		  if (maxHeight <= currentScroll) {
 			  	if(lableOrderby != "" || lable_house != "" || 
 					lable_size != "" || lable_style != ""  ) {
 			  		
 			  		filter_getPhoto();
 			 	}else{
 			 		getPhoto();
 			 	}
 			 
 		    }
 		  })
 		});




//이미지 불러오기 
function getPhoto(){
	var last_mag_num = $(".item_list .each_item:last").attr("data-mag_num");
	var last_mag_img_num = $(".item_list .each_item:last img").attr("data-mag_img_num");
	
	//console.log("last_mag_img_num="+last_mag_img_num);
	//console.log("last_mag_num="+last_mag_num);
	
	if(last_mag_img_num == 1 || last_mag_num == 1){
		
		alert("더이상 데이터가 없습니다.")
		
	}
	else{
		if(last_mag_num == null){
			last_mag_num = 0;
		}
		
		$.getJSON("/etc/getPhoto/"+last_mag_num, function(data){
			
			var str = "";
			$(data).each(function(){
				str += 
					 '<div class="each_item" data-mag_num = "'+this.mag_num+'">'
					+ '<a href="/magazine/magazine_detail?mag_num='+this.mag_num+'">'
					+ '<img  class="item_img" data-mag_img_num="'+this.mag_img_num+'"'
					+ 'src="/zipImg'+this.mag_img+'" onerror="this.src=/resources/images/main1.jpg">'
					+ '</a></div>';
			}); //each end
			
			$(".item_list").append(str).masonry('appended', str);
			
			$(document).imagesLoaded('.item_list', function(){
				
				$masonry.masonry( 'reloadItems' );
				$masonry.masonry('layout');
			});
		});
	}
		
		
		
}




//라벨 필터로 다시 불러오는 이미지들 
function filter_getPhoto(){
	var last_mag_num = $(".item_list .each_item:last").attr("data-mag_num");
	var last_mag_img_num = $(".item_list .each_item:last img").attr("data-mag_img_num");
	
	//console.log("last_mag_img_num="+last_mag_img_num);
	//console.log("last_mag_num="+last_mag_num);
	
	if(last_mag_img_num != 1){// 마지막 이미지넘버가 1이아니면 
		if(last_mag_num == null){
			last_mag_num = 0; //가져올 마지막 매거진 넘버가 없으면  0으로 가져감 
		}
	
	
	 var data = {};
	 data["lableOrderby"] = $(".HiddenlableOrderby").val();
	 data["lable_house"] = $(".Hiddenlable_house").val();
	 data["lable_size"] = $(".Hiddenlable_size").val();
	 data["lable_style"] = $(".Hiddenlable_style").val();
	 
	 $.ajax({
		 type: 'post',
		 url : '/etc/getPhoto_filter/'+last_mag_num,
		 headers:{
				"Content-Type" : "application/json",
				"X-HTTP-Method_Override" : "POST" },
		dataType : 'json',
		data : JSON.stringify(data),
		error : function(request,status,error){
	    	 console.log("code:"+request.status);
	    	 console.log("message:"+request.responseText);
	    	 console.log("error:"+error);

	    },
		success : function(data){
			var str = "";
			$(data).each(function(){
				str += 
					 '<div class="each_item" data-mag_num = "'+this.mag_num+'">'
					+ '<a href="/magazine/magazine_detail?mag_num='+this.mag_num+'">'
					+ '<img  class="item_img" data-mag_img_num="'+this.mag_img_num+'"'
					+ 'src="/zipImg/'+this.mag_img+'" onerror="this.src=/resources/images/main1.jpg">'
					+ '</a></div>';
			}); //each end
			$(".item_list").append(str).masonry('appended', str);
			
			$(document).imagesLoaded('.item_list', function(){
				
				$masonry.masonry( 'reloadItems' );
				$masonry.masonry('layout');
			});
		}
		 
	 });//ajax end 
	 
	}else{
		alert("더 불러올 데이터가 없습니다.");
	}
}

function ch_lable1() {
	var opt_text = $("#maga_se1 option:selected").text();
	$("#maga_label1").html(opt_text);
}
function ch_lable2() {
	var opt_text = $("#maga_se2 option:selected").text();
	$("#maga_label2").html(opt_text);
}
function ch_lable3() {
	var opt_text = $("#maga_se3 option:selected").text();
	$("#maga_label3").html(opt_text);
}

function ch_lable4() {
	var opt_text = $("#maga_se4 option:selected").text();
	$("#maga_label4").html(opt_text);
}

