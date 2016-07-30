package egen.io.apimodule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.User;
import egen.io.apimodule.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	UserService service;

	// get all
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<User> findAll() {
		return service.findAll();

	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findOne(@PathVariable("id") String userId) {
		return service.findOne(userId);
	}

	

	// logout
	@RequestMapping(method = RequestMethod.GET, path = "logout")
	public void logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user.getEmail().length() > 0) {
			session.invalidate();
		}
		// return "redirect:/login.html";
	}

	// update
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User update(@PathVariable("id") String userId, @RequestBody User user) {
		return service.update(userId, user);
	}

	// delete
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String userId) {
		service.delete(userId);
	}
}
