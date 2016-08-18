package egen.io.apimodule.interceptors;

import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egen.io.apimodule.tokenhandler.TokenHandler;



public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		System.out.println("admin is called before this method");
		String AuthHeader = request.getHeader("Authorization");
		System.out.println("Auth Header"+AuthHeader);
		String token=null;
			if(AuthHeader!=null){
			token	= AuthHeader.substring("Bearer".length()).trim();
			}
		
		try{
			TokenHandler obj=new TokenHandler();
			obj.verifyTokenAndAdmin(token);
			}
			catch(SignatureException e){
				System.out.println("ther is signature exception");
				response.sendRedirect("http://www.google.com");
			}
		//System.out.println("Got request to save data : name:"+request.getParameter("name"));
		return true;
	}
	
}