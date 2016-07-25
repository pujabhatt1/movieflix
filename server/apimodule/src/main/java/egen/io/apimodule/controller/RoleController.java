package egen.io.apimodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.Role;
import egen.io.apimodule.service.RoleService;

@RestController
@RequestMapping(path = "roles")
public class RoleController {

	@Autowired
	RoleService service;

	//get all
	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Role> findAll() {
		return service.findAll();
		
	}
	
	//get one login
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Role findOne(@PathVariable("id") String roleId) {
		return service.findOne(roleId);
	}
	
		
	//registration: post
	@RequestMapping(method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Role create(@RequestBody Role role) {
		return service.create(role);
	}
	
	//update
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Role update(@PathVariable("id") String roleId, @RequestBody Role role) {
		return service.update(roleId, role);
	}
 //delete
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String roleId) {
		service.delete(roleId);
	}
}
