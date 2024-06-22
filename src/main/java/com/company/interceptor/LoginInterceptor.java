package com.company.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap = mv.getModelMap();
		Map<String, Object> loginMap = (Map<String, Object>)modelMap.get("Login");
		System.out.println(loginMap);
		
		
		if(loginMap != null) {
			if((int)loginMap.get("USER_DENIED") == 0) {	// 차단 회원이 아닐 경우

				session.setAttribute(LOGIN, loginMap);
				session.setAttribute("email", loginMap.get("USER_EMAIL"));
				session.setAttribute("nickname", loginMap.get("USER_NICKNAME"));
				session.setAttribute("type", loginMap.get("USER_TYPE"));
				session.setAttribute("img", loginMap.get("USER_IMG"));
				session.setAttribute("name", loginMap.get("USER_NAME"));
			//	response.sendRedirect("/");
				
				//로그인 성공 후에 response.sendRedirect()작업에 'dest' 정보를 사용하도록 하는 코드
				Object dest = session.getAttribute("dest");
				
				response.sendRedirect(dest != null ? (String)dest:"/");
			
			} else if ((int)loginMap.get("USER_DENIED") == 1) {	// 차단 회원일 경우
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('차단된 회원입니다.'); history.go(-1);</script>");
				out.flush();
			}
		} else {	// 이메일이나 비밀번호가 잘못되었을 경우 
			System.out.println("로그인 실패");
			//response.sendRedirect("/member/login");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이메일이나 비밀번호가 잘못되었습니다.'); history.go(-1);</script>");
			out.flush();
			
		}
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		// 기존에 HttpSession에 남아있는 정보가 있는 경우에는 정보를 삭제
		if(session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}
				
		return true;
	}

}
