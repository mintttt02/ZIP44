package com.company.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

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
import com.company.service.StoryService;
import com.company.util.UploadFileUtils;

@Controller
@RequestMapping("/story/*")
public class StoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoryController.class);
	
	@Inject
	private StoryService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	//스토리 목록불러오기 
	@RequestMapping(value = "/story", method = RequestMethod.GET)
	public ModelAndView story(CommandMap map) throws Exception{
		ModelAndView mv = new ModelAndView("story");
		
		List<Map<String,Object>> storylist = service.listStory(map.getMap());
		List<Map<String,Object>> storyImgList = service.storyImgList(map.getMap());
		mv.addObject("storylist",storylist);
		mv.addObject("storyImgList",storyImgList);
		
		return mv;
	}
	
	//스토리 글쓰기 
	//이미지 업로드
	@ResponseBody
    @RequestMapping(value="/story", method=RequestMethod.POST)
    public int storyPOST(@RequestParam("imgFiles") List<MultipartFile> imgFiles, CommandMap map)throws Exception  {
		
		//스토리 내용 저장 
		service.registStory(map.getMap());
		
		int st_num = service.getStoryNum(); //가장 마지막에 생성된 스토리넘버 가져옴
		map.put("st_num", st_num);
		//System.out.println("st_num="+map.get("st_num"));
		
		//이미지 저장 
		for(MultipartFile image : imgFiles) {
            logger.info(image.getOriginalFilename());
    		logger.info("size : " + image.getSize());
    		logger.info(image.getContentType());
    		
    		if(image.getSize() != 0) {
			String savedName = UploadFileUtils.uploadFile(uploadPath, image.getOriginalFilename(), image.getBytes());
            logger.info("savedName = "+savedName);
            map.put("st_img", savedName);
            service.saveStoryImg(map.getMap());
    		}
        }
        
        return 3;
    }
	
	//스토리 스크롤다운시 스토리 리스트 불러옴 
	@RequestMapping(value="/scrolldown/{st_num}", method=RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> listAny(@PathVariable("st_num") Integer st_num){
	    ResponseEntity<List<Map<String, Object>>> entity = null;
	    
	    
	    try{
	    	List<Map<String, Object>> storyScrolldown = service.storyScrolldown(st_num);
	    	
	    	
	        entity = new ResponseEntity<>(storyScrolldown, HttpStatus.OK);
	    } catch(Exception e){
	        e.printStackTrace();
	        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
	    }
	 
	    return entity;
	}
	//스토리 스크롤다운시 이미지 리스트 불러옴 
		@RequestMapping(value="/scrolldownImg/{st_num}", method=RequestMethod.GET)
		public ResponseEntity<List<Map<String, Object>>> scrolldownImg(@PathVariable("st_num") Integer st_num){
		    ResponseEntity<List<Map<String, Object>>> entity = null;
		    
		    
		    try{
		    	List<Map<String, Object>> storyScrolldownImgList = service.storyScrolldownImgList(st_num);
		    	
		    	
		        entity = new ResponseEntity<>(storyScrolldownImgList, HttpStatus.OK);
		    } catch(Exception e){
		        e.printStackTrace();
		        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
		    }
		 
		    return entity;
		}
	//스토리 삭제
	@RequestMapping(value = "/storyDelete", method=RequestMethod.GET)
	public ModelAndView deleteMag(@RequestParam("st_num") int st_num) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/story/story");
		
		//스토리 삭제 전 해당 스토리의 이미지들을 찾아와 삭제함 
		List<Map<String, Object>> delStoryImgList = service.delStoryImgList(st_num); 
		for(int i=0; i< delStoryImgList.size(); i++) {
			Map<String, Object> delImgList = delStoryImgList.get(i);
			String delImg = (String) delImgList.get("st_img");
			logger.info("delImg : " + delImg);
			new File(uploadPath + delImg.replace('/', File.separatorChar)).delete();
		}
		//스토리삭제 
		service.storyDelete(st_num);
		
		return mv;
	}
	
		//스토리 신고
		@ResponseBody //int로 리턴하려면 이거 필요함.
		@RequestMapping(value = "/stReport", method=RequestMethod.POST)
		public int stReport(@RequestBody Map<String, Object> map) throws Exception{
			
			int result = 3;
			
			result = service.searchReportUser(map);
			//result 0이면 신고안함, 1이면 신고한 스토리
			
			if(result == 0) {
				service.stReport(map);
				result = 2; //신고완료 
			}
			
			return result;
		}
	//좋아요
	@ResponseBody //int로 리턴하려면 이거 필요함.
	@RequestMapping(value = "/updateLike", method=RequestMethod.POST)
	public int updateLike(@RequestParam("st_num") Integer st_num,
			@RequestParam("user_email") String user_email) throws Exception{
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("st_num", st_num);
		 map.put("user_email", user_email);
			
	int result = service.updateLike(map);
	//result = 0 좋아요 안함, 1 좋아요 이미 함
			
	return result;
	}
	
}
