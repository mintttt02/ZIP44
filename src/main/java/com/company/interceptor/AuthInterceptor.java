package com.company.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 특정 경로에 접근하는 경우 현재 사용자가 로그인 한 상태의 사용자인지를 체크하는 역할
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	// 사용자가 원하는 페이지의 정보를 HttpSessiondp 'dest'라는 이름으로 저장하는 역할
	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if (query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		if (req.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		// 사용자가 로그인하지 않은 상태라면 로그인 페이지를 보여줌
		if(session.getAttribute("login") == null) {
			logger.info("current user is not logined");
			
			saveDest(request);
			
			response.sendRedirect("/member/login");
			return false;
		}
		return true;
	}

}
