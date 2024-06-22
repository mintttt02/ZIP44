package com.company.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.company.common.CommandMap;
import com.company.domain.Criteria;
import com.company.domain.PageMaker;
import com.company.service.MemberService;
import com.company.service.StoryService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	private MemberService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public void admin() throws Exception {
		
	}
	///한글테스트
	@RequestMapping(value = "/list")
	public ModelAndView memberList(Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("admin/list");
		logger.info(cri.toString());
		List<Map<String, Object>> list = service.listPaging(cri);
		mv.addObject("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		mv.addObject("pageMaker", pageMaker);
		System.out.println("list="+list);
		
		return mv;
	}
	
	@RequestMapping(value = "/detail")
	public ModelAndView memberDetail(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("admin/detail");
		
		Map<String,Object> detail = service.MemberDetail(map.getMap());
		mv.addObject("detail", detail);
		
		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGET(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("admin/update");
		
		Map<String, Object> update = service.MemberDetail(map.getMap());
		mv.addObject("update", update);
		
		return mv;
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView memberUpdate(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/detail");
		
		service.UpdateMember(map.getMap());
		
		mv.addObject("USER_EMAIL", map.get("USER_EMAIL"));
		
		return mv;
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView memberDelete(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/list");
		
		service.DeleteMember(map.getMap());
		
		return mv;
	}
	
	//신고리스트 
	
	//신고 리스트 
	@RequestMapping(value = "/ReportList")
	public ModelAndView Report(Criteria cri, @RequestParam ("column") Integer column) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(column == 1) { //매거진댓글
			 mv = new ModelAndView("admin/magCommReport");
			 List<Map<String, Object>> magtitle = service.magtitle(cri);
			 mv.addObject("magtitle", magtitle);
			 cri.setReport_category("mag_com_num"); 
		}else if(column == 2) {//스토리
			 mv = new ModelAndView("admin/storyReport");
			 cri.setReport_category("st_num"); 
		}else if(column ==3) {//스토리댓글
			 mv = new ModelAndView("admin/storyCommReport");
			 List<Map<String, Object>> storyNum = service.storyNum(cri);
			 mv.addObject("storyNum", storyNum);
			 cri.setReport_category("st_com_num"); 
		}else if(column == 4) {//후기
			 mv = new ModelAndView("admin/reviewReport");
			 cri.setReport_category("rv_num"); 
		}
		
		logger.info(cri.toString());
		List<Map<String, Object>> list = service.reportList(cri);
		mv.addObject("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.reportListCnt(cri));
		
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
	}
	//스토리 신고 자세히 
	@RequestMapping(value="/storyReportDetail")
	public ModelAndView storyReportDetail(@RequestParam("st_num") Integer st_num) throws Exception{
		ModelAndView mv = new ModelAndView("admin/storyReportDetail");
		
		Map<String, Object> storyDetail = service.storyReportDetail(st_num);
		List<Map<String,Object>> storyImgList = service.storyReportImgList(st_num);
		List<Map<String,Object>> storyCommList = service.storyCommList(st_num);
		
		mv.addObject("st", storyDetail);
		mv.addObject("storyImgList", storyImgList);
		mv.addObject("storyCommList", storyCommList);
		
		return mv;
		
	}
	
	@Inject
	private StoryService Storyservice;
	//신고된 스토리 삭제 
	@RequestMapping(value="/reportDel")
	public ModelAndView reportDel(
			@RequestParam ("column") Integer column,
			@RequestParam ("number") Integer number) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/ReportList?column="+column);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("number", number); //글번호
		
		if(column == 1) { //매거진댓글
			map.put("tableName", "magazine_comment");
			map.put("columnName", "mag_com_num");
			service.reportDel(map);
		}
		
		else if(column == 2) {//스토리
			Integer st_num = number;
			//스토리 삭제 전 해당 스토리의 이미지들을 찾아와 삭제함 
			List<Map<String, Object>> delStoryImgList = Storyservice.delStoryImgList(st_num); 
			for(int i=0; i< delStoryImgList.size(); i++) {
				Map<String, Object> delImgList = delStoryImgList.get(i);
				String delImg = (String) delImgList.get("st_img");
				logger.info("delImg : " + delImg);
				new File(uploadPath + delImg.replace('/', File.separatorChar)).delete();
			}
			//스토리삭제 
			Storyservice.storyDelete(st_num);
			
		}
		
		else if(column ==3) {//스토리댓글
			map.put("tableName", "st_comment");
			map.put("columnName", "st_com_num");
			service.reportDel(map);
		}
		
		else if(column == 4) {//후기
			map.put("tableName", "review");
			map.put("columnName", "rv_num");
			service.reportDel(map);
		}
		
		
		
		return mv;
		
	}
	
	//신고취소
	@RequestMapping(value="/reportCancel")
	public ModelAndView reportCancel(
			@RequestParam("cancel_num") Integer cancel_num,
			@RequestParam ("column") Integer column) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/ReportList?column="+column);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cancel_num", cancel_num);
		
		if(column == 1) { //매거진댓글
			map.put("delColumn", "mag_com_num");
			service.reportCancel(map);
		}
		
		else if(column == 2) {//스토리
			map.put("delColumn", "st_num");
			service.reportCancel(map);
		}
		
		else if(column ==3) {//스토리댓글
			map.put("delColumn", "st_com_num");
			service.reportCancel(map);
		}
		
		else if(column == 4) {//후기
			map.put("delColumn", "rv_num");
			service.reportCancel(map);
		}		
		
		
		return mv;
		
	}
	
	@RequestMapping(value = "/iplog")
	public ModelAndView iplogCheck(Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("admin/ipLog");
		
		List<Map<String, Object>> ip_addr = service.listIpAddr(cri);
		
		mv.addObject("ip", ip_addr);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countIpAddrPaging(cri));
		
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
		
	}

}
