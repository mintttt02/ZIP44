$(".header_list:nth-child(2)").attr("class", "header_list selected");

var mag_img_btn = document.querySelector('#mag_img');

mag_img_btn.onchange = function(){
	var mag_img = mag_img_btn.files;
	//console.log("filename="+mag_img[0].name+"||"+mag_img[0].size);
	
	 var reader = new FileReader();
	 reader.readAsDataURL(mag_img[0]);
	 
	 reader.onload = function() {
		 
		 var image = document.createElement('img');
		 image.src = reader.result;
		 $("#mag_Preivew_div").html(image);
		 
	 }
}


//summernote 에디터
$(document).ready(function() {
    $('#summernote').summernote({
    	placeholder: 'Hello summernote',
        height: 500,
        callbacks : {
			onImageUpload : function(targetFile) {
				sendFile(targetFile[0]);
			},
			onMediaDelete : function(targetFile){
				//console.log(targetFile[0].src);
				deleteFile(targetFile[0].src);
			}
		}
    });
});

$("#subBtn").on("click", function(){
	if($(".allImgList").val() == null){
		alert("이미지를 하나 이상 등록해 주세요");
	}else{
		$("#formId").submit();
	}
})

//이미지 등록시 
 function sendFile(targetFile) {
	 
	var formdata = new FormData();
	formdata.append("file", targetFile);
	
	$.ajax({
		type : 'POST',
		url : '/magazine/uploadMagImg',
		enctype : 'multipart/form-data',
        processData : false,
        contentType : false,
        cache : false,
		data : formdata,
		dataType:'text',
		success : function(data){
			//console.log("이미지업로드 성공"+data);
			//수정시 새 이미지가 업로드 되었을때 태그 위치가 꼬이는것을 방지하기위해 
			//이미지를 div로 감싸줌. 
			var divCode = document.createElement('div');
			divCode.className = "summdiv";
			var imgdiv = $('#summernote').summernote('insertNode', divCode);
			var imgsrc = $('#summernote' ).summernote('insertImage', data);
			imgsrc.className = "summerImg";
			var str = '<input type="hidden" name="allImgList" class="allImgList" value="'+data+'">'
			$(".allInput").append(str);
		}
	});
}
 
 //이미지 삭제시 
 function deleteFile(targetFile) {
	 //console.log("targetFile="+targetFile);
	//한글파일일때 깨짐방지 디코딩
	 var currentFileName = decodeURIComponent(targetFile);
	 $.ajax({
		type:'POST',
		url : '/magazine/deleteMagImg',
		data : {fileName :currentFileName },
		dataType:'text',
		success : function(data){
			currentFileName = currentFileName.substring(currentFileName.indexOf("_") + 1);
			//console.log("이미지삭제 성공"+currentFileName);
		}
	 });
	
}
 
//이미지 등록해놓고 그냥 취소누를때 에디터에 올라온 사진 서버에서 삭제 
function editorcancel(){
	var allImgList = document.getElementsByClassName("allImgList");
	
	var formdata = new FormData();
	
	for(var i = 0; i < allImgList.length;i++){
		formdata.append("allImgList", allImgList[i].value);
	}
	
	$.ajax({
		type : 'POST',
	    enctype : 'multipart/form-data',
	    processData : false,
	    contentType : false,
	    cache : false,
	    url : '/magazine/editorcancel',
	    dataType : 'json',
	    data : formdata,
	    success : function(result) {
	    	//console.log("result="+result);
	    	if(result==3){
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
 

 
 
 
 
 
 
 
 
 