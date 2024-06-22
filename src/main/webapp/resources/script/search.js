/*작성자 : 최나현 */

function enterKey() {
	var search_val = $(".search_val").val();
	if (window.event.keyCode == 13) { //엔터키를 눌렀을때 form 실행됨.
	    	$('#search_from').submit();
	}
}

$(".list_item").on("click", function(){
	var val = $(this).html();
	$(".search_val").val(val); //해당 검색어를 가지고 검색실행함.
	$('#search_from').submit();
	
})


