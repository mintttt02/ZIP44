$(".header_list:nth-child(2)").attr("class", "header_list selected");


//summernote

$(document).ready(function() {
    $('#summernote').summernote({
    	placeholder: 'Hello summernote',
        height: 800,
        //lang : 'ko-KR',
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
			//이미지를 div로 감싸줌. 태그도 이 div안으로 들어감. 
			//이유는 모르겠으나 알아서 div안으로 img가 들어감.
			var divCode = document.createElement('div');
			divCode.className = "summdiv";
			var imgdiv = $('#summernote').summernote('insertNode', divCode);
			var imgsrc = $('#summernote' ).summernote('insertImage', data);
			imgsrc.className = "summerImg";
			
			var magImg = data.substring(data.indexOf("/zipImg")+7);
			var str = '<input type="hidden" name="allImgList" class="allImgList" value="'+magImg+'">'
			
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
			currentFileName = currentFileName.substring(currentFileName.indexOf("/zipImg")+7);
			//console.log("이미지삭제 성공"+currentFileName);
			var delImg = $("input:hidden[name ='allImgList']:input[value='"+currentFileName+"']");
			delImg.remove();

		}
	 });
	
}
 
 //대표사진 미리보기
 var mag_photo_btn = document.querySelector('#mag_photo_btn');
var mag_photo = document.querySelector(".mag_photo");
 var mag_photo_original = $(".mag_photo_original").val();
 
mag_photo_btn.onchange = function(){
 	var mag_img = mag_photo_btn.files;
 	//console.log("filename="+mag_img[0].name+"||"+mag_img[0].size);
 	
 	 var reader = new FileReader();
 	 reader.readAsDataURL(mag_img[0]);
 	 
 	 reader.onload = function() {
 		 mag_photo.src = reader.result;
 		 
 	 }
 }

