package egen.io.apimodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.Comment;
import egen.io.apimodule.service.CommentService;


@RestController
@RequestMapping(path = "comments")
public class CommentController {

	@Autowired
	CommentService service;

	//get all
	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comment> findAll() {
		return service.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "movie/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comment> findAllCommentsOnMovie(@PathVariable("id") String movieId) {
		return service.findAllCommentsOnMovie(movieId);
	}
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comment findOne(@PathVariable("id") String commentId) {
		return service.findOne(commentId);
	}
	
	//create
	@RequestMapping(method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comment create(@RequestBody Comment comment) {
		return service.create(comment);
	}
	
	//update
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comment update(@PathVariable("id") String commentId, @RequestBody Comment comment) {
		return service.update(commentId, comment);
	}
 //delete
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String commentId) {
		service.delete(commentId);
	}
}
