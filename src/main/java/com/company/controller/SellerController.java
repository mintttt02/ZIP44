package com.company.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.company.domain.Criteria;
import com.company.domain.PageMaker;
import com.company.service.StoreService;
import com.company.util.UploadFileUtils;

@Controller
@RequestMapping("/seller/*")
public class SellerController {
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Inject
	private StoreService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 판매자 페이지 이동
	@RequestMapping(value = "")
	public ModelAndView sellerPage(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("store/seller");
			
		return mv;
	}
	
	// 상품등록 리스트 페이지 이동
	@RequestMapping(value = "/listItem", method = RequestMethod.GET)
	public ModelAndView listItem(Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("store/listItem");
		
		List<Map<String, Object>> itemList = service.ListPaging(cri);
		mv.addObject("item", itemList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.ListCountCriteria(cri));
		
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
	}
	
	// 상품등록 페이지 이동
	@RequestMapping(value = "/registItem", method = RequestMethod.GET)
	public ModelAndView registItemGET(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("store/registItem");
		
		return mv;
	}
	
	// 상품등록
	@ResponseBody
	@RequestMapping(value = "/seller/registItem", method = RequestMethod.POST)
	public int registItem(CommandMap map, 
			@RequestParam("imgFiles") List<MultipartFile> file,
			@RequestParam("imgFiles_content") List<MultipartFile> pr_content,
			@RequestParam("option1_title") List<String> opt1_title,
			@RequestParam("option1") List<String> opt1,
			@RequestParam("option2_title") List<String> opt2_title,
			@RequestParam("option2") List<String> opt2,
			@RequestParam("option3_title") List<String> opt3_title,
			@RequestParam("option3") List<String> opt3,
			@RequestParam("option_price") List<String> opt_price,
			@RequestParam("option_cnt") List<String> opt_cnt
			) throws Exception {
		
		service.RegistProduct(map.getMap());
	
		// 대표이미지
		for(MultipartFile image : file) {
		logger.info(image.getOriginalFilename());
		logger.info("size : " + image.getSize());
		logger.info(image.getContentType());
	
		String savedName = UploadFileUtils.uploadFile(uploadPath, image.getOriginalFilename(), image.getBytes());
		map.put("pr_img", savedName);
		logger.info("savedName = " + savedName);
		
		service.RegistProductImg(map.getMap());
		}
		
		if(opt1_title != null) {
		for(int j = 0 ; j < opt1.size() ; j++) {
		
			map.put("option1_title", opt1_title.get(j));
			map.put("option1", opt1.get(j));
			map.put("option2_title", opt2_title.get(j));
			map.put("option2", opt2.get(j));
			map.put("option3_title", opt3_title.get(j));
			map.put("option3", opt3.get(j));
			map.put("option_price", opt_price.get(j));
			map.put("option_cnt", opt_cnt.get(j));
			
			service.RegistOpt(map.getMap());
		}
		}
		// 제품상세 이미지
		for(MultipartFile img_content : pr_content) {
			logger.info(img_content.getOriginalFilename());
			logger.info("size : " + img_content.getSize());
			logger.info(img_content.getContentType());
			
			String savedName = UploadFileUtils.uploadFile(uploadPath, img_content.getOriginalFilename(), img_content.getBytes());
			map.put("pr_detail_img", savedName);
			logger.info("savedName = " + savedName);
			
			service.RegistProductDetailImg(map.getMap());
		}
		
		
		return 1;
	}
	
	// 상품 삭제
	@RequestMapping(value = "/delete")
	public ModelAndView deleteProduct(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/seller/listItem");
		
		service.removeProduct(map.getMap());
		
		return mv;
	}
	
	// 주문리스트
	@RequestMapping(value = "/orderList")
	public ModelAndView orderList(Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("/store/orderList");
		
		List<Map<String, Object>> orderList = service.ListOrder(cri);
		mv.addObject("order", orderList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countOrderPaging(cri));
		
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
	}
	
	@RequestMapping(value = "/orderDetail/{od_num}")
	public ModelAndView orderDetail(CommandMap map, @PathVariable("od_num") int od_num) throws Exception {
		ModelAndView mv = new ModelAndView("/store/orderDetail");
		
		map.put("od_num", od_num);
		
		Map<String, Object> orderDetail = service.OrderDetail(map.getMap());
		mv.addObject("od", orderDetail);
		
		return mv;
	}
	
	@RequestMapping(value = "/orderUpdate/{od_num}", method = RequestMethod.GET)
	public ModelAndView orderUpdateGET(CommandMap map, @PathVariable("od_num") int od_num) throws Exception {
		ModelAndView mv = new ModelAndView("/store/orderUpdate");
		
		map.put("od_num", od_num);
		
		Map<String, Object> orderDetail = service.OrderDetail(map.getMap());
		mv.addObject("od", orderDetail);
		
		return mv;
	}
	
	@RequestMapping(value = "/orderUpdate/{od_num}", method = RequestMethod.POST)
	public ModelAndView orderUpdate(CommandMap map, @PathVariable("od_num") int od_num) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/seller/orderDetail/{od_num}");
		
		map.put("od_num", od_num);
		
		service.ModifyOrder(map.getMap());
		
		return mv;
	}
	
}
