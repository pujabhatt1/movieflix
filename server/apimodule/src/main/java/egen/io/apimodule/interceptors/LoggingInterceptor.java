package egen.io.apimodule.interceptors;

import java.security.SignatureException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import egen.io.apimodule.tokenhandler.TokenHandler;
public class LoggingInterceptor implements HandlerInterceptor  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		System.out.println("---Before Method Execution---");
		String jwt=getJwtFromHeader(request);
		System.out.println(jwt);
		try{
			TokenHandler obj=new TokenHandler();
			obj.verifyToken(jwt);
			
			}
			catch(SignatureException e){
				System.out.println("ther is signature exception");
				response.sendRedirect("http://www.google.com");
			}
		return true;
	}
	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		//System.out.println("---method executed---");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		//System.out.println("---Request Completed---");
	}
		 
	 private String getJwtFromHeader(HttpServletRequest request) {
			return request.getHeader("jwt");
		  }
} 
