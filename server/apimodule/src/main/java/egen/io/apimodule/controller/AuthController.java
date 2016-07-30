package egen.io.apimodule.controller;

import java.security.SignatureException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.User;
import egen.io.apimodule.service.UserService;
import egen.io.apimodule.tokenhandler.TokenHandler;


@RestController

public class AuthController {
	@Autowired
	UserService service;
	@Autowired
	TokenHandler tokenHandler;
	
	
	// login
		@RequestMapping(method = RequestMethod.POST, path = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public String authenticate(@RequestBody User user) {
			boolean isValidUser = service.authenticate(user);
		
			if(isValidUser){
				User existing=service.findByEmail(user.getEmail());
				String email=user.getEmail();
				String role=existing.getRole().getName();
				System.out.println(role);
				String jwt=tokenHandler.creatToken(email,role);
				System.out.println(jwt);
				return jwt;
			
			}
			return "0";
		}
	
		// signup(registration)
		@RequestMapping(method = RequestMethod.POST, path = "signup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public User create(@RequestBody User user) {
			return service.create(user);
		}
}
