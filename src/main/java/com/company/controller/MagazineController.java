package com.company.controller;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.company.common.CommandMap;
import com.company.service.MagazineService;
import com.company.util.UploadFileUtils;

@Controller
@RequestMapping("/magazine/*")
public class MagazineController {
	private static final Logger logger = LoggerFactory.getLogger(MagazineController.class);
	
	@Inject
	private MagazineService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/editer_page", method = RequestMethod.GET)
	public ModelAndView editer() throws Exception{
		ModelAndView mv = new ModelAndView("/magazine/editer_page");
		
		return mv;
	}

	
	@RequestMapping(value = "/magazine_form", method = RequestMethod.GET)
	public ModelAndView magazineGET(CommandMap map) throws Exception{
		ModelAndView mv = new ModelAndView("/magazine/magazine_form");
		
		return mv;
	}
	//매거진 작성
	@RequestMapping(value = "/magazine_form", method = RequestMethod.POST)
	public ModelAndView magazinePOST(CommandMap map, MultipartFile file,
			@RequestParam("allImgList") List<String> allImgList ) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/magazine/magazine_form2?mag_num=");
		
		logger.info(file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info(file.getContentType());
		
		//매거진 대표이미지 서버에 저장
		if(file.getSize() != 0) {
		String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		map.put("mag_photo", savedName);
		}
		//매거진 insert
		service.createMag(map.getMap());
		
		//매거진 이미지 insert 
			for(String imgSrc : allImgList) {
			
			String originalFileName = imgSrc.substring(imgSrc.indexOf("/zipImg")+7);
		//	System.out.println("매거진 이미지 등록 "+ originalFileName);
			service.magAllImg(originalFileName);
			
		}
		
			
		return mv;
	}
		//매거진 작성2 
			@RequestMapping(value = "/magazine_form2", method = RequestMethod.POST)
			public ModelAndView magazine_form2_update(CommandMap map) throws Exception{
				ModelAndView mv = new ModelAndView("redirect:/magazine/magazine");
				
			//	System.out.println("form2 update = "+ map.get("mag_number"));
				service.update_from(map.getMap());
				
				
				return mv;
			}
		//매거진 작성2 이동
		@RequestMapping(value = "/magazine_form2", method = RequestMethod.GET)
		public ModelAndView magazine_form2(@RequestParam("mag_num") Integer mag_num) throws Exception{
			ModelAndView mv = new ModelAndView("/magazine/magazine_form2");
			//System.out.println("mag_num="+mag_num);
			
			if(mag_num == null) {
				Map<String, Object> magDetail = service.magForm2();
				mv.addObject("magDetail",magDetail);
			}else {
				Map<String, Object> magDetail = service.readMag(mag_num);
				mv.addObject("magDetail",magDetail);
			}
			
			return mv;
		}	
	//매거진 리스트
	@RequestMapping(value = "/magazine", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView magazine(CommandMap map,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("/magazine/magazine");
		
		List<Map<String,Object>> listMag = new ArrayList<>();
		
		Object lableOrderby = map.get("lableOrderby");
		Object lable_house = map.get("lable_house");
		Object lable_size = map.get("lable_size");
		Object lable_style = map.get("lable_style");
		
		if(lableOrderby!=null || lable_house!=null || 
			lable_size!=null || lable_style!=null  ) {
			
			listMag = service.listOpMag(map.getMap());
			
			mv.addObject("lableOrderby", lableOrderby);
			mv.addObject("lable_house", lable_house);
			mv.addObject("lable_size", lable_size);
			mv.addObject("lable_style", lable_style);
			
			if(listMag.size() < 1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
			//	System.out.println("nodata");
				out.println("<script>alert('데이터가 없습니다.');</script>");
				out.println("<script>location.href='/magazine/magazine';</script>");
				out.flush();
			}
			
		}else {
			map.put("lableOrderby", "mag_num");
			map.put("lable_house", 99);
			map.put("lable_size", 99);
			map.put("lable_style", 99);
			
			listMag = service.listOpMag(map.getMap());
		}
		
		mv.addObject("listMag",listMag);
		return mv;
	}
	
	//매거진 스크롤 다운 
		@RequestMapping(value = "/scroll", method = RequestMethod.POST)
		public ResponseEntity<List<Map<String, Object>>> scroll(
				@RequestBody Map<String, Object> map) throws Exception{
				ResponseEntity<List<Map<String, Object>>> entity = null;
				
			Object lableOrderby = map.get("lableOrderby");
			Object lable_house = map.get("lable_house");
			Object lable_size = map.get("lable_size");
			Object lable_style = map.get("lable_style");
			
			
			try{

				if(lableOrderby!="" || lable_house!="" || 
					lable_size!="" || lable_style!=""  ) {
					
					List<Map<String, Object>> listSize = service.listOpMag(map);
					entity = new ResponseEntity<>(listSize, HttpStatus.OK);
					
				}else {
					map.put("lableOrderby", "mag_date");
					map.put("lable_house", 99);
					map.put("lable_size", 99);
					map.put("lable_style", 99);
					
					List<Map<String, Object>> listSize = service.listOpMag(map);
					entity = new ResponseEntity<>(listSize, HttpStatus.OK);
				}
				
		    } catch(Exception e){
		        e.printStackTrace();
		        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
		    }

			return entity;
		}

	
	//매거진상세
	@RequestMapping(value = "/magazine_detail", method=RequestMethod.GET)
	public ModelAndView readMag(@RequestParam("mag_num") int mag_num) throws Exception{
		ModelAndView mv = new ModelAndView("/magazine/magazine_detail");
		//매거진 조회수 +1 
		service.updateViewCnt(mag_num);
		
		Map<String, Object> magDetail = service.readMag(mag_num);
		mv.addObject("magDetail",magDetail);
		return mv;
		
	}
	
	
	//매거진수정페이지 상세
	@RequestMapping(value = "/update_page", method = RequestMethod.GET)
	public ModelAndView updatePage(@RequestParam("mag_num") int mag_num) throws Exception{
		ModelAndView mv = new ModelAndView("/magazine/magazine_update");
		Map<String, Object> magDetail = service.readMag(mag_num);
		List<Map<String, Object>> magImg = service.updateDetailMagImg(mag_num);
		
		mv.addObject("magDetail",magDetail);
		mv.addObject("magImg",magImg);
		return mv;
	}
	//매거진삭제
	@ResponseBody
	@RequestMapping(value = "/magazine_delete", method=RequestMethod.POST)
	public int deleteMag(@RequestParam("allImgList") List<String> allImgList,CommandMap map) throws Exception{
		
		
		for(String imgSrc : allImgList) {
			
			String originalFileName = imgSrc.substring(imgSrc.indexOf("/zipImg")+7);
			originalFileName = originalFileName.replace('/', File.separatorChar);
			logger.info("deleted mag imgSrc="+originalFileName);
			new File(uploadPath + originalFileName).delete();
		}
		
		String mag_photo = (String) map.get("mag_photo");
		logger.info("mag_photo ="+mag_photo);
		mag_photo = mag_photo.replace('/', File.separatorChar);
		new File(uploadPath + mag_photo).delete();
		
		logger.info("deleted mag number ="+map.get("mag_num"));
		service.deleteMag(map.getMap());
		
		return 3;
		
	}
	
	//매거진 수정
	@RequestMapping(value = "/magazine_update_action")
	public ModelAndView updateMag(@RequestParam("allImgList") List<String> allImgList,
			CommandMap map,MultipartFile file) throws Exception{
		
		logger.info("원본파일이름 : " + file.getOriginalFilename());
		logger.info("파일사이즈 : " + file.getSize());
		logger.info("파일contentType : " + file.getContentType());
		
		
		//원래 파일명 가져옴 
		String mag_photo_original = (String) map.get("mag_photo_original");
		
		String savedName = null;
		if(file.getSize() == 0) { 
			savedName = mag_photo_original;
		}else {
			savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			
			//기존파일 삭제
			logger.info("delete file : "+ mag_photo_original);
			new File(uploadPath + mag_photo_original.replace('/', File.separatorChar)).delete();
			
		}
		
		//지정된 파일명을 user_img에 넣음
		map.put("mag_photo", savedName);
		
		service.updateMag(map.getMap());
		//매거진 업데이트
		//수정된 이미지들 다시 넣기 전에 기존에 있던 내용 지움.
		
		//매거진 이미지 insert 
		for(String imgSrc : allImgList) {
		
		String originalFileName = imgSrc;
		//System.out.println("매거진 이미지 등록 "+ originalFileName);
		map.put("originalFileName", originalFileName);
		service.updateMagImg(map.getMap());
		
		}
		
		Object mag_num = map.get("mag_num");
		ModelAndView mv = new ModelAndView("redirect:/magazine/magazine_form2?mag_num="+mag_num);
		return mv;
		
	}
	
	//"text/plain;charset=UTF-8 > 한국어를 정상적으로 전달하기 위함.
	//이미지를 저장하기 위해서 사용되는 메서드
	//매거진 작성, 수정시 에디터 - 이미지 액션 
	@ResponseBody	
	@RequestMapping(value="/uploadMagImg", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadForm(MultipartFile file)throws Exception{
		ResponseEntity<String> entity;
		
		logger.info("원본파일이름 : " + file.getOriginalFilename());
		logger.info("파일사이즈 : " + file.getSize());
		logger.info("파일contentType : " + file.getContentType());
		
		String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		logger.info("savedName : " + savedName);
		
		try{		
			entity = new ResponseEntity<String>("/zipImg"+savedName,HttpStatus.CREATED);
		}catch(Exception e){
			logger.info("파일업로드 도중 에러발생: " + e.getMessage());
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return  entity;
	}
	
	//이미지 삭제
	@RequestMapping(value="/deleteMagImg", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName){
		ResponseEntity<String> entity;
		
		String originalFileName = fileName.substring(fileName.indexOf("/zipImg")+7);
		logger.info("del mag img : " + originalFileName);
		
		originalFileName = originalFileName.replace('/', File.separatorChar);
		new File(uploadPath + originalFileName).delete();
		
		try{
			entity = new ResponseEntity<String>("deleted",HttpStatus.OK);
		}catch(Exception e){
			logger.info("파일 삭제도중 에러발생 : " + e.getMessage());
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//매거진 댓글 등록
	@RequestMapping(value = "/magazine_comment_write", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> magazine_comment_write(@RequestBody Map<String, Object> map) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			service.addComment(map);
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
//  댓글조회 
	@RequestMapping(value="/magazine_comment_list/{mag_num}", method=RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> listAny(@PathVariable("mag_num") Integer mag_num){
	 
	    ResponseEntity<List<Map<String, Object>>> entity = null;
	    try{
	        entity = new ResponseEntity<>(service.listComment(mag_num), HttpStatus.OK);
	    } catch(Exception e){
	        e.printStackTrace();
	        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
	    }
	 
	    return entity;
	}
	
	
	// 댓글 삭제
		@RequestMapping(value = "/commentDelete/{mag_com_num}", method = RequestMethod.DELETE)
		public ResponseEntity<Map<String, Object>> remove(@PathVariable("mag_com_num") Integer mag_com_num) {
			
			ResponseEntity<Map<String, Object>> entity = null;
			Map<String, Object> resultMap = new HashMap<String, Object>();
			try {
				service.removeComment(mag_com_num);
				entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
		// 댓글 수정
		@RequestMapping(value = "/commnetUpdate/{mag_com_num}", method = {RequestMethod.PUT, RequestMethod.PATCH})
		public ResponseEntity<Map<String, Object>> update(
				@PathVariable("mag_com_num") Integer mag_com_num, 
				@RequestBody Map<String, Object> map) {
			
			ResponseEntity<Map<String, Object>> entity = null;
			try {
				map.put("mag_com_num", mag_com_num); //map에 st_com_num 넣어줌
				service.updateComment(map);
				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}	
		//에디터 취소시 에디터에 있던 이미지 파일들 서버에서 삭제
		@ResponseBody
		@RequestMapping(value = "/editorcancel", method=RequestMethod.POST)
		public int editorcancel(@RequestParam("allImgList") List<String> allImgList) throws Exception{
			for(String imgSrc : allImgList) {
				
				String originalFileName = imgSrc.substring(imgSrc.indexOf("/zipImg")+7);
				originalFileName = originalFileName.replace('/', File.separatorChar);
				logger.info("editorcancel="+originalFileName);
				new File(uploadPath + originalFileName).delete();
			}
			return 3;
			
		}
		

		//매거진폼2 검색 
			@RequestMapping(value = "/form2_search", method = RequestMethod.POST)
			public ResponseEntity<List<Map<String, Object>>> form2_search(
					@RequestBody Map<String, Object> map) throws Exception{
					ResponseEntity<List<Map<String, Object>>> entity = null;
				
					
				try{
						List<Map<String, Object>> form2_search = service.form2_search(map);
						entity = new ResponseEntity<>(form2_search, HttpStatus.OK);
					
					
			    } catch(Exception e){
			        e.printStackTrace();
			        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
			    }

				return entity;
			}
			//매거진 댓글 신고
			@ResponseBody //int로 리턴하려면 이거 필요함.
			@RequestMapping(value = "/magCommReport", method=RequestMethod.POST)
			public int stReport(@RequestBody Map<String, Object> map) throws Exception{
				
				int result = 3;
				
				result = service.searchReportUser(map);
				//result 0이면 신고안함, 1이면 신고한 스토리
				
				if(result == 0) {
					service.magCommReport(map);
					result = 2; //신고완료 
				}
				
				return result;
			}
		
}


