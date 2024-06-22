package com.company.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.company.common.CommandMap;
import com.company.service.MagazineService;
import com.company.service.MemberService;
import com.company.service.StoreService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private MagazineService magservice;
	
	@Inject
	private StoreService storeService;
	
	@Inject
	private MemberService memberservice;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(CommandMap map) throws Exception{
		ModelAndView mv = new ModelAndView("index");
		
		// ip주소 insert
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		
		HttpSession session = req.getSession();
		String user_email = (String)session.getAttribute("email");
		
		if(ip == null) {
			ip = req.getRemoteAddr();
			
		}
		
		map.put("login_email", user_email);
		map.put("ip_addr", ip);
		//System.out.println("ip_addr = " + map.get("ip_addr"));
		
		memberservice.registIpAddr(map.getMap());
		
		mv.addObject("clientIP", ip);
		
		
		
		map.put("lableOrderby", "mag_num");
		map.put("lable_house", 99);
		map.put("lable_size", 99);
		map.put("lable_style", 99);
		
		List<Map<String,Object>> listMag = magservice.listOpMag(map.getMap());
		mv.addObject("listMag",listMag);
		List<Map<String, Object>> listStore = storeService.ListProduct(map.getMap());
		mv.addObject("listSt", listStore);
		
		return mv;
	}
	@RequestMapping(value = "/nonMember", method = RequestMethod.GET)
	public String nonMemberOrder() throws Exception {
		
		return "nonMemberOrder";
	}
}
