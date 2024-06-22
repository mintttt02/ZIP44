package com.company.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.company.common.CommandMap;
import com.company.service.EtcService;

@Controller
@RequestMapping("/etc/*")
public class EtcController {
	private static final Logger logger = LoggerFactory.getLogger(EtcController.class);
	
	@Inject
	private EtcService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/photo", method = RequestMethod.GET)
	public ModelAndView photo(CommandMap map) throws Exception{
		ModelAndView mv = new ModelAndView("photo");
		
		Object lableOrderby = map.get("lableOrderby");
		Object lable_house = map.get("lable_house");
		Object lable_size = map.get("lable_size");
		Object lable_style = map.get("lable_style");
		

		if(lableOrderby!=null || lable_house!=null || 
			lable_size!=null || lable_style!=null  ) {
			
			mv.addObject("lableOrderby", lableOrderby);
			mv.addObject("lable_house", lable_house);
			mv.addObject("lable_size", lable_size);
			mv.addObject("lable_style", lable_style);
		}
		
		return mv;
	}
	//매거진포토 전부가져옴 
	@RequestMapping(value = "/getPhoto/{mag_num}", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getPhoto(
			@PathVariable("mag_num") Integer mag_num) throws Exception{
		ResponseEntity<List<Map<String, Object>>> entity = null;
		
		if(mag_num == 0) {
			mag_num = service.getMaxmag_num();
		}
		
		try{
	        entity = new ResponseEntity<>(service.getPhoto(mag_num), HttpStatus.OK);
	    } catch(Exception e){
	        e.printStackTrace();
	        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
	    }

		
		return entity;
	}
	
	//매거진포토 필터링된 
	@RequestMapping(value = "/getPhoto_filter/{mag_num}", method = RequestMethod.POST)
	public ResponseEntity<List<Map<String, Object>>> getPhoto_filter(
			@PathVariable("mag_num") Integer mag_num, @RequestBody Map<String, Object> map) throws Exception{
			ResponseEntity<List<Map<String, Object>>> entity = null;

			if(mag_num == 0) {
			mag_num = service.getMaxmag_num();
			map.put("mag_num", mag_num);
		}else {
			mag_num = mag_num-1;
			map.put("mag_num", mag_num);
		}
		
		try{
	        entity = new ResponseEntity<>(service.getPhoto_filter(map), HttpStatus.OK);
	    } catch(Exception e){
	        e.printStackTrace();
	        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
	    }

		
		return entity;
	}
	
	//검색페이지이동
	@RequestMapping(value = "/search")
	public ModelAndView searchGET(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("search");
			
	
		return mv;
	}
	
	//검색-결과
	@RequestMapping(value = "/search_result")
	public ModelAndView search_result(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("search_result");
			
		Object search_val = map.get("search_val");
		if(search_val != null) {
			List<Map<String, Object>> listMag = service.getListMag(map.getMap());
			List<Map<String, Object>> item = service.getListProduct(map.getMap());
			List<Map<String, Object>> itemImg = service.getListitemImg(map.getMap());
			
			mv.addObject("listMag", listMag);
			mv.addObject("item", item);
			mv.addObject("itemImg", itemImg);
			mv.addObject("search_val", search_val);
		}
		return mv;
	}
	
	//검색-매거진-더보기
	@RequestMapping(value = "/search_mag")
	public ModelAndView search_mag(@RequestParam("search_val") String search_val) throws Exception {
		ModelAndView mv = new ModelAndView("search_mag");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search_val", search_val);
		List<Map<String, Object>> listMag = service.getListMag(map);
		mv.addObject("listMag", listMag);
		mv.addObject("search_val", search_val);
		
		return mv;
	}
	//검색-스토어-더보기
		@RequestMapping(value = "/search_store")
		public ModelAndView search_store(@RequestParam("search_val") String search_val) throws Exception {
			ModelAndView mv = new ModelAndView("search_store");
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("search_val", search_val);
			List<Map<String, Object>> item = service.getListProduct(map);
			List<Map<String, Object>> itemImg = service.getListitemImg(map);
			mv.addObject("item", item);
			mv.addObject("itemImg", itemImg);
			mv.addObject("search_val", search_val);
			
			return mv;
		}
}
