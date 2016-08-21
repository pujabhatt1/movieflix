package egen.io.apimodule.controller;

import java.security.SignatureException;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.Token;
import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.UserNotFoundException;
import egen.io.apimodule.helper.AuthResult;
import egen.io.apimodule.service.TokenService;
import egen.io.apimodule.service.UserService;
import egen.io.apimodule.tokenhandler.TokenHandler;


@RestController

public class AuthController {
	@Autowired
	UserService service;
	@Autowired
	TokenHandler tokenHandler;
	@Autowired
	TokenService tokenService;
	@Autowired
	AuthResult authResult;
	
	// login
		@RequestMapping(method = RequestMethod.POST, path = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public AuthResult authenticate(@RequestBody User user) {
			boolean isValidUser = service.authenticate(user);
			if(isValidUser){
				User existing=service.findByEmail(user.getEmail());
				String email=user.getEmail();
				String role=existing.getRole().getName();
				String userId=existing.getId();
				System.out.println(role);
				String token=tokenHandler.creatToken(email,role);
				System.out.println(token);
				Token tokenObj=new Token();
				tokenObj.setToken(token);
				tokenService.create(tokenObj);
				authResult.setResult("success");
				authResult.setToken(token);
				authResult.setUserId(userId);
				authResult.setRole(role);
			   return authResult;
		}
			else{
				 throw new UserNotFoundException(
						"User not found!!please login with other valid email and password");
			}
		       
		           
			}
	
		// signup(registration)
		@RequestMapping(method = RequestMethod.POST, path = "signup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public User create(@RequestBody User user) {
			return service.create(user);
		}
}
