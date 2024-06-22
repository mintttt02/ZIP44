
$(".header_list:nth-child(1)").attr("class", "header_list selected");

	$(document).ready(function() {
		$('.index_slider').bxSlider({
			mode : 'fade',
			auto : true, //자동으로 슬라이드 
			controls : true, //좌우 화살표	
			autoControls : false, //stop,play 
			pager : true, //페이징 
			pause : 1800,
			autoDelay : 0,
			slideWidth : 500,
			speed : 500,
			stopAutoOnclick : true
		});
		
		$('.i_m_for').bxSlider({
			auto : 'auto',
			autoControls : false,
			autoDelay : 3000,
			speed : 3000,
			pager : false,
			pause : 1800,
			minSlides : 3,
			maxSlides : 3,
			slideWidth: 500,
			slideMargin: 10,
			moveSlides : 1
		});
	});
	

	$(document).ready(function () {
	var mag_house = $(".mag_house").html();
	var mag_size = $(".mag_size").html();
	var mag_style = $(".mag_style").html();

	if(mag_house == 0){
		$(".mag_house").html("#아파트");
	}else if (mag_house == 1) {
		$(".mag_house").html("#주택");
	} else if (mag_house == 2) {
		$(".mag_house").html("#빌라");
	} else if (mag_house == 3) {
		$(".mag_house").html("#원룸");
	} else if (mag_house == 4) {
		$(".mag_house").html("#이색공간");
	} else if (mag_house == 5) {
		$(".mag_house").html("#오피스텔");
	} else if (mag_house == 6) {
		$(".mag_house").html("#etc");
	}

	if (mag_size == 0) {
		$(".mag_size").html("#10평미만");
	} else if (mag_size == 1) {
		$(".mag_size").html("#10평대");
	} else if (mag_size == 2) {
		$(".mag_size").html("#20평대");
	} else if (mag_size == 3) {
		$(".mag_size").html("#30평대");
	} else if (mag_size == 4) {
		$(".mag_size").html("#40평대");
	} else if (mag_size == 5) {
		$(".mag_size").html("#40평대 이상");
	} else if (mag_size == 6) {
		$(".mag_size").html("#해당없음");
	}

	if (mag_style == 0) {
		$(".mag_style").html("#러블리");
	} else if (mag_style == 1) {
		$(".mag_style").html("#유니크");
	} else if (mag_style == 2) {
		$(".mag_style").html("#네츄럴");
	} else if (mag_style == 3) {
		$(".mag_style").html("#앤틱");
	} else if (mag_style == 4) {
		$(".mag_style").html("#DIY");
	} else if (mag_style == 5) {
		$(".mag_style").html("#북유럽");
	} else if (mag_style == 6) {
		$(".mag_style").html("#빈티지");
	} else if (mag_style == 7) {
		$(".mag_style").html("#모던");
	} else if (mag_style == 8) {
		$(".mag_style").html("#럭셔리");
	} else if (mag_style == 9) {
		$(".mag_style").html("#프렌치");
	} else if (mag_style == 10) {
		$(".mag_style").html("#인더스트리얼");
	} else if (mag_style == 11) {
		$(".mag_style").html("#미니멀라이즈");
	} else if (mag_style == 12) {
		$(".mag_style").html("#기타");
	}

	});
