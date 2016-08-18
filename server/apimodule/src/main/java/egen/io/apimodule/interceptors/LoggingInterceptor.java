package egen.io.apimodule.interceptors;

import java.security.SignatureException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.InvalidTokenException;
import egen.io.apimodule.exception.TokenNotFoundException;
import egen.io.apimodule.exception.UserNotFoundException;
import egen.io.apimodule.service.TokenService;
import egen.io.apimodule.service.UserService;
import egen.io.apimodule.tokenhandler.TokenHandler;

public class LoggingInterceptor implements HandlerInterceptor {

	

	
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("---Before Method Execution---");
	 String method=request.getMethod();
	if(method.equals("OPTIONS")){
		System.out.println("mm"+method);
		 return true;
	 }
	 else{
			System.out.println("mm"+method);
		String AuthHeader = request.getHeader("Authorization");
		System.out.println("Auth Header"+AuthHeader);
		String token=null;
			if(AuthHeader!=null){
			token	= AuthHeader.substring("Bearer".length()).trim();
			}
		System.out.println("token is"+token);
		  if (token == null) {
	            throw new InvalidTokenException("Token header must be provided");
	        }
		
		  else if(!tokenService.isValidToken(token)){
			  throw new TokenNotFoundException("Invalid token");
		  }
		try {
			TokenHandler obj = new TokenHandler();
			Map<String, Object> claims = obj.verifyToken(token);
			System.out.println(claims);
			
			if (claims==null) {
				throw new UserNotFoundException(
						"User not found or expired!!please login with other valid email and password");
			} else {
				parseClaimAndValidateEmail(claims);
			}
		} catch (SignatureException e) {
			System.out.println("ther is signature exception");
			response.sendRedirect("http://www.google.com");
		}
	 }
		return true;
	}

	public void parseClaimAndValidateEmail(Map<String, Object> claims){
	Iterator it = claims.entrySet().iterator();
	while (it.hasNext()) {
		Map.Entry pair = (Map.Entry) it.next();
		System.out.println(pair.getKey() + " = " + pair.getValue());
		if (pair.getKey().equals("email")) {
			validateEmail(pair.getValue().toString());
		}
	}
	}
	public void validateEmail(String email) {
		User existing = userService.findByEmail(email);
		System.out.println("validate" + existing);
		if (existing == null) {
			throw new UserNotFoundException(
					"User not found!!please login with other valid email and password");
		}
	}
	
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	
}


