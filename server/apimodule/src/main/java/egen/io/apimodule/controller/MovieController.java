package egen.io.apimodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.io.apimodule.entity.Movie;
import egen.io.apimodule.entity.User;
import egen.io.apimodule.service.MovieService;
import egen.io.apimodule.service.UserService;

@RestController
@RequestMapping(path = "movies")
public class MovieController {
	
	@Autowired
	MovieService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAll() {
		return service.findAll();
       
	}
	//search movie
	@RequestMapping(method = RequestMethod.GET, path="{searchtext}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findSearch(@PathVariable("id") String searchText) {
		//return service.findAll();
       return null;
	}
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findOne(@PathVariable("id") String movieId) {
		return service.findOne(movieId);
	}
	
	    // create movie title
		@RequestMapping(method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Movie create(@RequestBody Movie movie) {
			return service.create(movie);
		}

		// update
		@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Movie update(@PathVariable("id") String movieId, @RequestBody Movie movie) {
			return service.update(movieId, movie);
		}

		// delete
		@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
		public void delete(@PathVariable("id") String movieId) {
			service.delete(movieId);
		}
}
