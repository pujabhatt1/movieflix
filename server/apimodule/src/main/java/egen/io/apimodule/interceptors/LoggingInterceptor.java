package egen.io.apimodule.interceptors;

import java.security.SignatureException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.UserNotFoundException;
import egen.io.apimodule.service.UserService;
import egen.io.apimodule.tokenhandler.TokenHandler;

public class LoggingInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("---Before Method Execution---");
		String jwt = getJwtFromHeader(request);
		System.out.println(jwt);
		try {
			TokenHandler obj = new TokenHandler();
			Map<String, Object> claims = obj.verifyToken(jwt);
			System.out.println(claims);
			System.out.println("size" + claims.size());
			if (claims.size() == 0) {
				throw new UserNotFoundException(
						"User not found or expired!!please login with other valid email and password");
			} else {
				Iterator it = claims.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					System.out.println(pair.getKey() + " = " + pair.getValue());
					if (pair.getKey().equals("email")) {
						validateEmail(pair.getValue().toString());
					}
				}
			}
		} catch (SignatureException e) {
			System.out.println("ther is signature exception");
			response.sendRedirect("http://www.google.com");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("---method executed---");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("---Request Completed---");
	}

	private String getJwtFromHeader(HttpServletRequest request) {
		return request.getHeader("jwt");
	}

	public void validateEmail(String email) {
		System.out.println("came at validation" + email);
		User existing = userService.findByEmail(email);
		System.out.println("validate" + existing);
		if (existing == null) {
			throw new UserNotFoundException(
					"User not found!!please login with other valid email and password");
		}
	}
}
