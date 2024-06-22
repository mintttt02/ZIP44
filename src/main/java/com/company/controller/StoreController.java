package com.company.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.common.CommandMap;
import com.company.domain.Criteria;
import com.company.domain.PageMaker;
import com.company.service.MemberService;
import com.company.service.StoreService;
import com.company.util.UploadFileUtils;

@Controller
@RequestMapping("/store/*")
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@Inject
	private StoreService service;
	
	@Inject
	private MemberService memberService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 스토어 페이지 이동
	@RequestMapping(value = "")
	public ModelAndView store(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("store/store");
		
		List<Map<String, Object>> itemList = service.ListProduct(map.getMap());
		List<Map<String, Object>> bestItem = service.ListBest(map.getMap());
		
		mv.addObject("item", itemList);
		mv.addObject("best", bestItem);
		
		return mv;
	}
	
	@RequestMapping(value = "/view/{pr_num}")
	public ModelAndView viewItem(CommandMap map, @PathVariable("pr_num") Integer pr_num,
			HttpServletResponse response) throws Exception {
		map.put("pr_num", pr_num);
		// 상품 정보
		Map<String, Object> productDetail = service.DetailProduct(map.getMap());
		List<Map<String, Object>> QnA = service.ListQnA(map.getMap());
		
		if(productDetail != null) {
			ModelAndView mv = new ModelAndView("/store/viewItem");
		// 옵션 타이틀 
		List<Map<String, Object>> opt = service.ListOpt(map.getMap());
//		List<Map<String, Object>> opt2_title = service.Opt2_title(map.getMap());
//		List<Map<String, Object>> opt3_title = service.Opt3_title(map.getMap());
//		
//		// 상품 옵션
//		List<Map<String, Object>> opt1 = service.ListOpt1(map.getMap());
//		List<Map<String, Object>> opt2 = service.ListOpt2(map.getMap());
//		List<Map<String, Object>> opt3 = service.ListOpt3(map.getMap());
		
		// 상품 대표이미지(slick-slider 안에 들어가는 이미지)
		List<Map<String, Object>> productImg = service.ListProductImg(map.getMap());
		
		// 상품 세부정보 이미지
		List<Map<String, Object>> productDetailImg = service.ListProductDetailImg(map.getMap());
		
		// 상품 view_cnt +1
		service.ModifyViewCnt(map.getMap());
	
		mv.addObject("pd", productDetail);
		mv.addObject("opt", opt);
		
		mv.addObject("image", productImg);
		mv.addObject("pdi", productDetailImg);
		mv.addObject("qna", QnA);
		
		return mv;
		
		}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
			//	System.out.println("nodata");
				out.println("<script>alert('상품이 없습니다.');</script>");
				out.println("<script>location.href='/store/store';</script>");
				out.flush();
				return null;
		}
		
	}
	
	// 스토어 더 보기(전체상품)
	@RequestMapping(value="/more")
	public ModelAndView storeMore(Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("store/more");
		cri.setPerPageNum(30);
		List<Map<String, Object>> itemList = service.ListAllProduct(cri);
		mv.addObject("item", itemList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countProductPaging(cri));
		
		mv.addObject("pageMaker", pageMaker);
	
		return mv;
	}
	
	@RequestMapping(value="/more/{bi_category}")
	public ModelAndView storeCategory(@PathVariable("bi_category") String bi_category, Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("store/more");
		
		// 페이징 처리를 위해서 cri가 필요함
		// 카테고리 값을 넘기기 위해서 map.put("bi_category", bi_category)를 사용하여야 함
		// 하지만 DAO에서 selectList메서드의 매개변수가 1개 뿐이라 map과 cri의 값을 동시에 보낼 수 없음 
		// Criteria 클래스에 bi_category 변수를 추가하여 getter/setter를 만들어주고 set으로 bi_category 값을 넘겨줌
		cri.setBi_category(bi_category); 
		cri.setPerPageNum(30);
		List<Map<String, Object>> itemList = service.ListCategoryProduct(cri);
		mv.addObject("item", itemList);
	
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countCategoryProduct_Paging(cri));
		
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
	}
	
	@RequestMapping(value = "/more/best")
	public ModelAndView storeBest(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("store/more");
		
		List<Map<String, Object>> best = service.ListBest(map.getMap());
		mv.addObject("item", best);
		
		return mv;
	}
	// 배송지 선택
	@RequestMapping(value="/delivery/{USER_EMAIL:.+}" , method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delivery(CommandMap map, @PathVariable("USER_EMAIL")String USER_EMAIL) throws Exception {
		ModelAndView mv = new ModelAndView("store/delivery");
		
	//	System.out.println(map.getMap());
	//	System.out.println("opt_no=" + map.get("opt_no"));
		
		map.put("USER_EMAIL", USER_EMAIL);
		List<Map<String, Object>> addr = memberService.ListAddr(map.getMap());
		mv.addObject("addr", addr);
		mv.addObject("order", map.getMap());
		
		return mv;
	}
	

	
	// 결제 
	@RequestMapping(value="/payment")
	public ModelAndView payment(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("store/payInfo");
		
		Map<String, Object> productDetail = service.DetailProduct(map.getMap());
		Map<String, Object> addr = memberService.DetailAddr(map.getMap());
		
	
		mv.addObject("order", map.getMap());
		mv.addObject("pd", productDetail);
		mv.addObject("addr", addr);
		
		return mv;
		
	}
	// 주문완료 페이지
	@RequestMapping(value="/complete", method = RequestMethod.POST)
	public ModelAndView paymentComplete(CommandMap map, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("store/orderComplete");
			
		//payInfo.jsp에 taker_name이 없음.
		if(map.get("taker_name") == null) {		// 받는 사람 이름이 없을 경우 주문자 이름과 동일하게 함
			map.put("taker_name", map.get("od_name"));
		}
		
		int product_cnt = service.CheckProductCnt(map.getMap()); 
		int opt_cnt=1;
		
		if(map.get("opt_value") != "") {
			opt_cnt = service.CheckOptCnt(map.getMap()); 
		}
		if(product_cnt > 0 &&  opt_cnt > 0) {	// 재고가 있을 경우
			service.ModifyProductCnt(map.getMap());
			if(map.get("opt_value") != "") {	// 옵션이 있을 경우 실행
				service.ModifyOptionCnt(map.getMap());
			}
			
			service.RegistOrder(map.getMap()); //주문등록
			
		} else {	// 재고가 없을 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('재고가 없습니다.'); history.go(-3);</script>");
			out.flush();
			return null; //재고가없으면 끝냄 
		}
		
		
		Map<String, Object> productDetail = service.DetailProduct(map.getMap());
		Map<String, Object> orderImg = service.OrderImg(map.getMap());
		Map<String, Object> order_list = service.OrderDetail_user(map.getMap());
		
		
		mv.addObject("pd", productDetail);
		mv.addObject("oI", orderImg);
		mv.addObject("oL", order_list);
		
		return mv;
	}
	//카트 -> 배송지선택 페이지 
	@RequestMapping(value="/delivery_fromCart/{USER_EMAIL:.+}" , method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delivery_fromCart(CommandMap map, 
			@PathVariable("USER_EMAIL")String USER_EMAIL,
			@RequestParam("cartNumList") List<Integer> cartNumList) throws Exception {
		ModelAndView mv = new ModelAndView("store/delivery");
		
		map.put("USER_EMAIL", USER_EMAIL);
		service.update_cartType_default(map.getMap());
		//이전에 cart_type을 1로 바꿨는데 결제창까지 안간 상품이 있을 경우를 대비해
		//전부 default로 바꿈.
		for(Integer cart_num : cartNumList) {
			map.put("cart_num", cart_num);
			service.update_cartType(map.getMap());
		}
		
		List<Map<String, Object>> addr = memberService.ListAddr(map.getMap());
		mv.addObject("addr", addr);
		
		return mv;
	}
	// 카트 -> 결제 
	@RequestMapping(value="/payment_fromCart")
	public ModelAndView payment_fromCart(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("store/payInfo2");
		
		List<Map<String, Object>> info = service.product_info(map.getMap());
		List<Map<String, Object>> pr_delivery = service.pr_delivery(map.getMap());
		//같은상품의 배송비 중복을 막기위해 따로 select 해서 배송비 가져옴.
		
		int total = 0; // (원가*개수)+옵션가 - 할인가 
		int delivery = 0;//배송료
		int discount = 0;//할인가격 (원가 * 할인률) * 개수

		for(int i = 0 ; i < info.size(); i++) {
			float totalF = Float.parseFloat( info.get(i).get("price_noDeli").toString());  //총가격
			//object -> string -> float -> int 형변환 
			float discountF = Float.parseFloat( info.get(i).get("discount").toString()); //할인가
			
			total =  total+(int)totalF;
			discount += (int)discountF;
		}
		for(int i = 0 ; i < pr_delivery.size(); i++) {
			total += (int)pr_delivery.get(i).get("pr_delivery");
			//total + 배송비 
			delivery += (int)pr_delivery.get(i).get("pr_delivery");
		}
		
		
		mv.addObject("total", total);
		mv.addObject("discount", discount);
		mv.addObject("delivery", delivery);
		mv.addObject("add_num", map.get("add_num"));
		
		return mv;
		
	}
	// 주문완료 페이지2 from cart 
		@RequestMapping(value="/complete2", method = RequestMethod.POST)
		public ModelAndView paymentComplete2(CommandMap map, HttpServletResponse response) throws Exception {
			ModelAndView mv = new ModelAndView("store/orderComplete2");
			
			map.put("add_num", map.get("od_addr_num"));
			//주문등록할때 name이 od_addr_num라서 jsp의 name을 따로 바꾸지 않음.
			Map<String, Object> addr = memberService.DetailAddr(map.getMap());
			//선택한 배송지 정보 
			List<Map<String, Object>> info = service.product_info(map.getMap());
			
			
			String add = addr.get("address1")+","+addr.get("address2");
			String od_n_phone = addr.get("add_n_phone1")+"-"+addr.get("add_n_phone2")+"-"+addr.get("add_n_phone3");
			String od_phone = addr.get("add_phone1")+"-"+addr.get("add_phone2")+"-"+addr.get("add_phone3");
			
			map.put("od_add", add);
			map.put("od_postcode", addr.get("post_code"));
			map.put("od_n_phone", od_n_phone);
			map.put("od_phone", od_phone);
			map.put("od_msg", addr.get("add_msg"));
			map.put("od_name", addr.get("add_name"));
			
			if(map.get("taker_name") == null) {		// 받는 사람 이름이 없을 경우 주문자 이름과 동일하게 함
				map.put("taker_name",addr.get("add_name"));
			}
			
			for(int i=0; i<info.size(); i++	) {
				map.put("bi_category", info.get(i).get("bi_category"));
				map.put("pr_num", info.get(i).get("pr_num"));
				map.put("od_cnt", info.get(i).get("cart_cnt"));
				map.put("od_pr_name", info.get(i).get("pr_title"));

				map.put("opt_no", info.get(i).get("cart_opt_num"));
				map.put("opt_value", info.get(i).get("cart_opt_value"));
				map.put("pr_deli_type", info.get(i).get("pr_deli_type"));
				map.put("od_price", info.get(i).get("pr_price"));
				map.put("od_d_price", info.get(i).get("pr_delivery"));
				map.put("od_discount", info.get(i).get("discount"));
				
				//재고확인 
				int product_cnt = service.CheckProductCnt(map.getMap()); 
				int opt_cnt=1;
				
				if(map.get("opt_no") != null) {
					opt_cnt = service.CheckOptCnt(map.getMap()); 
				}
				if(product_cnt > 0 &&  opt_cnt > 0) {	// 재고가 있을 경우
					service.ModifyProductCnt(map.getMap());
					if(map.get("opt_no") != null) {	// 옵션이 있을 경우 실행
						service.ModifyOptionCnt(map.getMap());
					}
					
					service.RegistOrder(map.getMap());//주문등록
					
				} else {	// 재고가 없을 경우
					String msg = info.get(i).get("pr_title")+"의 재고가 없으므로 주문이 취소되었습니다.";
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('"+msg+"'); history.go(-3);</script>");
					out.flush();
					return null; //재고가없으면 끝냄 
				}
				
			}
			
			List<Map<String, Object>> productDetailImg = service.payAfterDetailImg(map.getMap());
			
			mv.addObject("pd", info);
			mv.addObject("prImg", productDetailImg);
			mv.addObject("addr", addr);
			mv.addObject("total", map.get("od_all_price"));
			
			
			service.deleteCart(map.getMap());//결제완료된 상품을 카트에서 지움
			return mv;
		}
				
	@RequestMapping(value = "/write_qna/{pr_num}")
	public ModelAndView write_qna(CommandMap map, @PathVariable("pr_num") int pr_num, RedirectAttributes rttr) throws Exception {
		ModelAndView mv = new ModelAndView("/store/write_qna");
		rttr.addAttribute("pr_num", pr_num);
		map.put("pr_num", pr_num);
		
		Map<String, Object> pd = service.DetailProduct(map.getMap());
		mv.addObject("pd", pd);
		
		return mv;
	}
	
	@RequestMapping(value = "/write_qna/add", method = RequestMethod.POST)
	public ModelAndView addQnA(CommandMap map, @RequestParam("pr_num") int pr_num) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/store/view/" + pr_num);
		
	//	System.out.println(pr_num);
		service.AddQnA(map.getMap());
		
		return mv;
	}
}
