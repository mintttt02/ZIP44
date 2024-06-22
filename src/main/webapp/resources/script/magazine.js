	

	$(".header_list:nth-child(2)").attr("class", "header_list selected");

	//무한스크롤
	$(document).ready(function () {
		$(document).scroll(function() {
	 		  var maxHeight = $(document).height(); //전체문서의 길이
	 		  var currentScroll = $(window).scrollTop() + $(window).height(); //스크롤위치 + 현재 브라우저의 길이 
	 					/*console.log("documentHeight:" + $(document).height() + " | scrollTop:" +
	 							$(window).scrollTop() + " | windowHeight: " + $(window).height() );*/
	 		  if (maxHeight <= currentScroll) {
	 			  var nomore = $("#nomore").val();
	 			  if(nomore == "nomore"){
	 				 alert("더이상 데이터가 없습니다.");
	 			  }else{
	 				 getMag();
	 			  }
	 			 
	 		    }
	 		  })
	 		});


	function getMag(){
		last_mag_num = $(".maga_maga:last").attr("data-lastMagNum");
		//console.log("last_mag_num="+last_mag_num);
		
		var data = {};
		 data["lableOrderby"] = $(".HiddenlableOrderby").val();
		 data["lable_house"] = $(".Hiddenlable_house").val();
		 data["lable_size"] = $(".Hiddenlable_size").val();
		 data["lable_style"] = $(".Hiddenlable_style").val();
		 data["mag_num"] = last_mag_num;
		
			
			$.ajax({
				type: 'post',
				 url : '/magazine/scroll',
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
					
					$(data).each(function(){
						str += 
						'<div class=maga_maga data-lastMagNum = "'+this.mag_num+'">'
			+				'<div class=maga_img  onclick="lolo('+this.mag_num+')"'
			+						'style="background-image: url(/zipImg/'+this.mag_photo+')">'
			+				'</div>'
			+				'<div class=maga_info>'
			+					'<div class=maga_i_title>'+this.mag_title+'</div>'
			+					'<div class=maga_i_tag>'
			+						'<a href="#"><span class="mag_house">#'+this.mag_house+'</span></a>'
			+						'<a href="#"><span class="mag_size">#'+this.mag_size+'</span></a>'
			+						'<a href="#"><span class="mag_style">#'+this.mag_style+'</span></a>'
			+					'</div>'
			+				'</div>'
			+			'</div> '
					}); //each end
					
				if(data.length < 10){
					$(".maga_maga:last").after(str);
					$("#nomore").val("nomore");
					
				}else{
					$(".maga_maga:last").after(str);
				}
					
			    }
			});//ajax end
				
	}
	
	function lolo(number){
		location.href= "/magazine/magazine_detail?mag_num="+number;
	}
	
	//슬라이더 
	$(document).ready(function() {
		$('.maga_slider').bxSlider({
			mode : 'fade',
			auto : true, //자동으로 슬라이드 
			controls : true, //좌우 화살표	
			autoControls : false, //stop,play 
			pager : false,
			pause : 1800,
			autoDelay : 0,
			slideWidth : 1000,
			speed : 500,
			stopAutoOnclick : true,
			adaptiveHeight : false, // 슬라이더 높이 고정 

		});
	});

//필터링 후 돌아왔을때 라벨 바꿈 
$(document).ready(function () {
	var lableOrderby = $(".HiddenlableOrderby").val();
	var lable_house = $(".Hiddenlable_house").val();
	var lable_size = $(".Hiddenlable_size").val();
	var lable_style = $(".Hiddenlable_style").val();
	
	if(lableOrderby != ""){ //필터로 걸러진 매거진이라면 
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
})

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