package com.company.controller;


import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.common.CommandMap;
import com.company.dto.LoginDTO;
import com.company.service.MemberService;
import com.company.service.StoreService;
import com.company.util.MediaUtil;
import com.company.util.UploadFileUtils;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;
	
	@Inject
	private StoreService storeService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	/*
	 * 회원가입을 위한 입력 페이지를 보는 경우(GET 방식으로 처리)
	 * 실제로 데이터를 처리하는 부분(POST 방식으로 처리)
	 */
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void signupGET() throws Exception {
		
	}
	
	// 회원가입
	@RequestMapping(value = "/signup")	// 해쉬맵
	public ModelAndView signupPOST(CommandMap map, RedirectAttributes rttr) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/member/login");
		logger.info("회원가입 성공");
		
		if(map.get("USER_SERVICE") == null) {
			map.put("USER_SERVICE", 0);
		}
		if(map.get("USER_INFO") == null) {
			map.put("USER_INFO", 0);
		}
		
		service.signup(map.getMap());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return mv;
	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)	
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) throws Exception{
		
	}
	
	// 로그인 후
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)	 
	public void loginPOST(CommandMap map, Model model) throws Exception {
		
		
		Map<String, Object> login = service.LoginService(map.getMap());
		
		if (login == null) {
			return;
		}
		
		model.addAttribute("Login", login);
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)	
	public String logout(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		session.removeAttribute("LOGIN");
		session.invalidate();
		
		return "member/logout";
	}
	
	// 개인정보 페이지 이동
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public ModelAndView settingProfile(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/setting");
		
		Map<String, Object> setting = service.MemberDetail(map.getMap());
		mv.addObject("setting", setting);
		
		return mv;
	}
	
	// 개인정보 수정
	@RequestMapping(value = "/setting", method = RequestMethod.POST)
	public ModelAndView settingProfileUpdate(CommandMap map, HttpServletRequest request, 
		MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView("member/settingPost");
		HttpSession session = request.getSession();
		
		logger.info(file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info(file.getContentType());
		
		//원래 파일명 가져옴 
		String original = (String) map.get("original");
		
		String savedName = null;
		if(file.getOriginalFilename() == "") { //프사를 안바꾸면 원래 파일명으로 그대로감 
			savedName = original;
		}else {//프사를 바꾸면
			savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			
			//기존프사파일 삭제 메소드
			logger.info("delete file : "+ original);
			new File(uploadPath + original.replace('/', File.separatorChar)).delete();
			
		}
		
		//지정된 파일명을 user_img에 넣음
		map.put("USER_IMG", savedName);
		service.SettingProfile(map.getMap());
		
	//	System.out.println("upadte 실행 후 : " + map.get("USER_IMG"));
		
		session.setAttribute("nickname", map.get("USER_NICKNAME"));
		session.setAttribute("img", savedName);
		
		return mv;
	}
	
	
	// 이메일 중복체크
	@RequestMapping(value = "/emailChk", method = RequestMethod.POST)
	@ResponseBody
	public int emailCheck(CommandMap map) throws Exception {
		
		int result = service.CheckEmail(map.getMap());
		
		return result;
	}
	
	// 닉네임 중복체크
	@RequestMapping(value = "/nickChk", method = RequestMethod.POST)
	@ResponseBody
	public int nickNameCheck(CommandMap map) throws Exception {
		
		int result = service.CheckNickname(map.getMap());
		
		return result;
	}
	
	// 비밀번호 변경 페이지 이동
	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public ModelAndView modifyPasswordGET(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/password");
		
		return mv;
	}
	
	// 비밀번호 변경
	@RequestMapping(value = "/password")
	public ModelAndView modifyPasswordPOST(CommandMap map, RedirectAttributes rttr) throws Exception {
		ModelAndView mv = new ModelAndView("member/passwordPost");
		
		service.ModifyPassword(map.getMap());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return mv;
	}
	
	// 회원탈퇴 페이지 이동
	@RequestMapping(value = "/leave", method = RequestMethod.GET)
	public ModelAndView leaveGET(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/leave");
		
		return mv;
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/leavePost")
	public ModelAndView memberLeave(CommandMap map, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/");
		
		service.DeleteMember(map.getMap());
		session.removeAttribute("LOGIN");
		session.invalidate();
		
		return mv;
	}
	
	// 주소지 변경 페이지 이동
	@RequestMapping(value = "/address")
	public ModelAndView memberAddress(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/addrPage");
		
		List<Map<String, Object>> addr = service.ListAddr(map.getMap());
		mv.addObject("addr", addr);
		
		return mv;
	}
	
	// 새 배송지 만들기 페이지 이동
	@RequestMapping(value = "/address/new", method = RequestMethod.GET)
	public ModelAndView newAddress(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/newAddress");
		
		Map<String, Object> addr = service.MemberDetail(map.getMap());
		mv.addObject("addr", addr);
		
		return mv;
	}
	
	// 새 배송지 만들기
	@RequestMapping(value = "/address/newPost")
	public ModelAndView newAddressPOST (CommandMap map, RedirectAttributes rttr) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/member/address");
		
		service.NewAddr(map.getMap());
		mv.addObject("USER_EMAIL", map.get("USER_EMAIL"));
		
		rttr.addFlashAttribute("msg", "SUECCESS");
		
		return mv;
	}
	
	@RequestMapping(value = "/address/modify", method = RequestMethod.GET)
	public ModelAndView modifyAddressGET(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/modifyAddress");
		
		Map<String, Object> addr = service.DetailAddr(map.getMap());
		mv.addObject("detail", addr);
		
		return mv;
	}
	
	// 배송지 수정
	@RequestMapping(value = "/address/modify")
	public ModelAndView modifyAddress(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/member/address");
		
		service.ModifyAddr(map.getMap());
		
		mv.addObject("USER_EMAIL", map.get("USER_EMAIL"));
		
		return mv;
	}
	
	// 배송지 삭제
	@RequestMapping(value = "/address/remove")
	public ModelAndView removeAddress(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("member/newAddressPost");
		
		service.RemoveAddr(map.getMap());
		
		return mv;
	}
	
	// 주문정보 페이지 이동
	@RequestMapping(value = "/my_Order/{USER_EMAIL:.+}")
	public ModelAndView myOrder(CommandMap map, @PathVariable("USER_EMAIL") String user_email) throws Exception {
		ModelAndView mv = new ModelAndView("member/myOrder");
		
		map.put("USER_EMAIL", user_email);
		
		int result = service.CountOrder(map.getMap());
		int result_waitingPay = service.CountWaitngPay(map.getMap());
		
		mv.addObject("result", result);
		mv.addObject("r_wp", result_waitingPay);
		
		
		return mv;
	}
	
	// 주문상태 정보 보기
	@RequestMapping(value = "/orderStatus/{order}/{USER_EMAIL:.+}")
	public ModelAndView orderStatus(CommandMap map, @PathVariable("USER_EMAIL") String user_email,
			@PathVariable("order") int order) throws Exception {
		ModelAndView mv = new ModelAndView("member/orderStatus");
		
		map.put("USER_EMAIL", user_email);
		
		List<Map<String, Object>> my_orderList = service.MyOrderList(map.getMap());
		mv.addObject("my", my_orderList);
		
		return mv;
	}
	
	// 주문 상세내역
	@RequestMapping(value = "/orderDetail/{od_num}")
	public ModelAndView orderDetail(CommandMap map, @PathVariable("od_num") int od_num) throws Exception {
		ModelAndView mv = new ModelAndView("member/orderDetail");
		
		map.put("od_num", od_num);
		
		Map<String, Object> orderDetail = service.MyOrderDetail(map.getMap());
		mv.addObject("oD", orderDetail);
		
		return mv;
	}
	
	// 주문 취소
	@RequestMapping(value = "/orderDelete/")
	public ModelAndView orderDelete(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/");
		
		service.RemoveMyOrder(map.getMap());
		
		return mv;
		
	}
	
	// 작성자 : 최나현 
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	//http://offbyone.tistory.com/167
	// 비밀번호찾기
		@RequestMapping(value = "/findPass")	
		public ModelAndView findPass() throws Exception{
			ModelAndView mv = new ModelAndView("member/findPass");
			
			return mv;
		}
		//메일보내기
		@RequestMapping(value = "/sendMail")
		public String sendMail(CommandMap map,HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			final String findPassEmail = (String) map.get("findPassEmail"); //사용자가 입력한 email주소 

			StringBuffer createPassBuffer = new StringBuffer(); //임시 비밀번호 위한 랜덤 문자열 생성.
			Random rnd = new Random();
			for (int i = 0; i < 8; i++) {
			    int rIndex = rnd.nextInt(3);
			    switch (rIndex) {
			    case 0:
			        // a-z
			    	createPassBuffer.append((char) ((int) (rnd.nextInt(26)) + 97));
			        break;
			    case 1:
			        // A-Z
			    	createPassBuffer.append((char) ((int) (rnd.nextInt(26)) + 65));
			        break;
			    case 2:
			        // 0-9
			    	createPassBuffer.append((rnd.nextInt(10)));
			        break;
			    }
			}
			//StringBuffer를 sql문에서 쓰기위해 String타입으로 형변환.
			final String createPass = createPassBuffer.toString();
			//System.out.println("임시비밀번호생성 createPass="+createPass);
			map.put("createPass", createPass);
			Integer chkEmail = service.tmpPsw(map.getMap()); //해당 email주소의 비밀번호를 임시비번으로 업데이트
			
			if(chkEmail == 1) { //해당 email이 있음 
				final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			    	
			        @Override
			        public void prepare(MimeMessage mimeMessage) throws Exception {
			            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			            helper.setFrom("집꾸미기<GreenMaison12@gmail.com>");
			            helper.setTo(findPassEmail);
			            helper.setSubject("[집꾸미기] 임시 비밀번호가 도착하였습니다!");
			            helper.setText("<h3>"+findPassEmail+"님의 비밀번호는<h3><br><h3>"
			            	+createPass+"입니다.</h3><br>"
			            	+ "<h4>집꾸미기 홈페이지에서 임시 비밀번호를바꿔주세요! </h4>", true);
			        }
			    };
			    mailSender.send(preparator); //메일보내기 
			 //   System.out.println("sendMail="+findPassEmail);
			    out.println("<script>alert('입력하신 주소로 메일발송이 완료되었습니다.');</script>");
				out.println("<script>location.href='/member/findPass';</script>");
				out.flush();
				return null;
				
			}else { //해당 email이 없음
				out.println("<script>alert('등록되지 않은 이메일입니다.');</script>");
				out.println("<script>location.href='/member/findPass';</script>");
				out.flush();
				return null;
			}
		}
	
	// 장바구니 페이지 이동 및 리스트 출력
	@RequestMapping(value = "/cart/{USER_EMAIL:.+}")
	public ModelAndView cartPage(CommandMap map, @PathVariable("USER_EMAIL") String user_email) throws Exception {
		ModelAndView mv = new ModelAndView("member/cart");
		
		map.put("USER_EMAIL", user_email);
		
		List<Map<String, Object>> cart = service.ListCart(map.getMap());
		
		mv.addObject("cart", cart);
		
		return mv;
	}
	
	// 장바구니 상품 추가
	@RequestMapping(value = "/cart/add")
	public ModelAndView cartAdd(CommandMap map, HttpSession session) throws Exception {
		String email = (String)session.getAttribute("email");
		
		ModelAndView mv = new ModelAndView("redirect:/member/cart/" + email);
		
		service.addCart(map.getMap());
		
		return mv;
	}
	
	// 장바구니 목록 주문 
	@RequestMapping(value = "/cart/order")
	public ModelAndView cartOrder(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("/store/store");
		
		return mv;
	}
	
	// 장바구니 삭제
	@RequestMapping(value = "/cart/remove")
	public ModelAndView cartRemove(CommandMap map, HttpSession session) throws Exception {
		String email = (String)session.getAttribute("email");
		
		ModelAndView mv = new ModelAndView("redirect:/member/cart/" + email);
		
		service.removeCart(map.getMap());
		
		return mv;
	}
}
