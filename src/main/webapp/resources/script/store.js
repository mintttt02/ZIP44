$(".header_list:nth-child(3)").attr("class", "header_list selected");

$(document).ready(function(){
	
      $('.slick_list').slick({
    	  slidesToShow: 1,
    	  slidesToScroll: 1,
    	  autoplay: true,
    	  autoplaySpeed: 3000,
    	  prevArrow:'<button type="button" class="slick-prev"><</button>',
    	  nextArrow:'<button type="button" class="slick-next">></button>',
    	  dots: true
      });
    });

//summernote

