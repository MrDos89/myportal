package himedia.myportal.interceptors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutInterceptor implements HandlerInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(LogoutInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("LogoutInterceptor.preHandle ");
		
		// 세션 무효화
		request.getSession().invalidate();
		// 홈페이지로 리다이렉트
		response.sendRedirect(request.getServletContext());
		return false;
	}
}
