package com.company.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.util.MediaUtil;
import com.company.util.UploadFileUtils;

@Controller
@RequestMapping("/upload/*")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	/*MultipartFile 객체를 이용하면  전송된 파일의 이름, 사이즈, 타입등과 같은 정보를 추출 가능*/
	/*업로드한 이미지파일 저장 경로 설정 */
	//http://doublesprogramming.tistory.com/131 설명자세히 되어있는곳
	
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
/*
	@RequestMapping(value="/uploadForm", method=RequestMethod.POST)
	public String uploadForm(MultipartFile mag_img_file, Model model) throws Exception{
		
		logger.info("originalName : "+mag_img_file.getOriginalFilename());
		logger.info("size :"+mag_img_file.getSize());
		logger.info("contentType : "+mag_img_file.getContentType());
		
		String savedName = uploadFile(mag_img_file.getOriginalFilename(),mag_img_file.getBytes());
		
		model.addAttribute("savedName",savedName);
		
		return "redirect:/magazine/magazine";
	}
	
	//원본 파일의 이름과 파일데이터를 byte[]로 변환한 정보를 파라미터로 처리해서 실제로 파일을 업로드함
	private String uploadFile(String originalName, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID();
		//UUID : 중복되지 않는 고유한 키 값을 설정할 떄 사용 
		// * 동일폴더에 동일한 이름의 파일을 업로드하는 경우를 피하기 위함 
		
		String savedName = uid.toString() + "_" + originalName;
		
		//실제 파일업로드 결과 : 파일이름 = UUID값 + _ + 파일원래이름
		
		File target = new File(uploadPath, savedName);
		
		
		FileCopyUtils.copy(fileData, target);
		//FileCopyUtils : 실제 파일 처리 위한 스프링에서 제공하는 클래스 
		// * 파일데이터를 파일로 처리하거나 복사하는 등의 작업에 사용 p.529
		return savedName;
	}*/

/*ajax 파일처리 */	
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, 
					produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("originalName : "+file.getOriginalFilename());
		
		return
				new ResponseEntity<>(
						UploadFileUtils.uploadFile(uploadPath, 
												file.getOriginalFilename(), 
												file.getBytes()),
						HttpStatus.CREATED 
						);
	}
	

/*전송된 파일을 화면에 표시하기 */
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		/*파라미터로 전송받기를 원하는 파일의 이름을 받음 STring fileName */
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FINE NAME : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			/*파일이름에서 확장자 추출
			 * lastIndexOf 메서드는 String 개체 안에서 부분 문자열의 시작점을 나타내는 정수 값을 반환합니다. 
			 * 마지막 "."에서 바로 다음부터가 확장자이기 때문에 +1 */
			MediaType mType = MediaUtil.getMediaType(formatName);
			/*파일에서 확장자를 추출하고 이미지타입의 파일인 경우, 적절한 mime타입을 지정함.*/
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				/*파일명이 지정될때 UUID+_+파일이름 이기떄문에 _다음의 파일이름만 추출*/
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				/*이미지가 아닌경우 MIME타입을 다운로드용으로 사용되는 APPLICATION_OCTET_STREAM 으로 지정
				 * 브라우저는 이 MIME타입을 보고 사용자에게 자동으로 다운로드 창을 열어줌  */
				headers.add("Content-Disposition", "attachment; filename=\""+
						new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			/*다운로드할때 사용자에게 보이는 파일이름. 한글처리함. */
			}
			
			entity =  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), /*실제로데이터를 읽어내는부분*/
					headers,
					HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
		/*리턴타입 ResponseEntity<byte[]> : 실제 파일의 데이터가 결과 
		 *@ResponseBody byte[]데이터가 그대로 전송될것을 명시함.  */
	}
	/*첨부파일 삭제하는 메소드 */
	@ResponseBody
	@RequestMapping(value="/deleteFile",  method=RequestMethod.POST )
	public ResponseEntity<String> deleteFile(String fileName){
		logger.info("delete file : "+ fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mtype = MediaUtil.getMediaType(formatName);
		
		if(mtype != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/',File.separatorChar)).delete();
		}
		
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
				
	}
	
}
