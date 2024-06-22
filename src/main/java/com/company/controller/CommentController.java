package com.company.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
	
	@Inject
	private CommentService service;
	
	// 댓글 등록
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> map) {
		
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
	@RequestMapping(value="/commentAll/{st_num}", method=RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> listAny(@PathVariable("st_num") Integer st_num){
	 
	    ResponseEntity<List<Map<String, Object>>> entity = null;
	    try{
	        entity = new ResponseEntity<>(service.listComment(st_num), HttpStatus.OK);
	    } catch(Exception e){
	        e.printStackTrace();
	        entity = new ResponseEntity<>( HttpStatus.BAD_REQUEST );
	    }
	 
	    return entity;
	}
	
	// 댓글 수정
	@RequestMapping(value = "/commnetUpdate/{st_com_num}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<Map<String, Object>> update(
			@PathVariable("st_com_num") Integer st_com_num, 
			@RequestBody Map<String, Object> map) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			map.put("st_com_num", st_com_num); //map에 st_com_num 넣어줌
			service.modifyComment(map);
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}	
	
	// 댓글 삭제
	@RequestMapping(value = "/commentDelete/{st_com_num}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("st_com_num") Integer st_com_num) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			service.removeComment(st_com_num);
			entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	//댓글 신고
	
			@ResponseBody //int로 리턴하려면 이거 필요함.
			@RequestMapping(value = "/st_com_report", method=RequestMethod.POST)
			public int stReport(@RequestBody Map<String, Object> map) throws Exception{
				
				int result = 3;
				
				result = service.searchReportUser(map);
				//result 0이면 신고안함, 1이면 신고한 스토리
				
				if(result == 0) {
					service.st_com_report(map);
					result = 2; //신고완료 
				}
				
				return result;
			}

}
