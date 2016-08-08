package egen.io.apimodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.Rating;
import egen.io.apimodule.service.RatingService;
@RestController
@RequestMapping(path = "ratings")
public class RatingController {
	@Autowired
	RatingService service;

	//get all
	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Rating> findAll() {
		return service.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "avg/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public double findAvgRatingsOnMovie(@PathVariable("id") String movieId) {
		return service.findAvgRatingsOnMovie(movieId);
	}
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Rating findOne(@PathVariable("id") String ratingId) {
		return service.findOne(ratingId);
	}
	
	//create
	@RequestMapping(method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Rating create(@RequestBody Rating rating) {
		return service.create(rating);
		
	}
	
	//update
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Rating update(@PathVariable("id") String ratingId, @RequestBody Rating rating) {
		return service.update(ratingId, rating);
	}
 //delete
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String ratingId) {
		service.delete(ratingId);
	}
}
