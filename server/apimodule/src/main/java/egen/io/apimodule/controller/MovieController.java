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

	// filter movie by text
	@RequestMapping(method = RequestMethod.GET, path = "find/{searchtext}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findMovieByText(
			@PathVariable("searchtext") String searchText) {
			return service.findMovieByText(searchText);
	}

	// filter movie based on type,year and genre
	@RequestMapping(method = RequestMethod.GET, path = "find/criteria/{field}/{searchtext}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findMovieByField(@PathVariable("field") String field,
			@PathVariable("searchtext") String searchText) {
			return service.findMovieByField(field, searchText);

	}
	//find top rated movie or tv series type is movie or series
	@RequestMapping(method = RequestMethod.GET, path = "find/toprated/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findMovieByTopRating(@PathVariable("type") String type) {
			return service.findMovieByTopRating(type);

	}
	// sort movie catalog based on imdbRating,Year,imdbVotes
	@RequestMapping(method = RequestMethod.GET, path = "sort/{searchtext}/{field}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> sortMovieByField(@PathVariable("searchtext") String searchText,@PathVariable("field") String field)
			 {
		 	  return service.sortMovieByField(searchText, field);

	}
	
 //get movie detail
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findOne(@PathVariable("id") String movieId) {
		return service.findOne(movieId);
	}
	//detail using imdbId
		@RequestMapping(method = RequestMethod.GET, path = "detail/{imdbid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Movie findMovieByImdbId(@PathVariable("imdbid") String imdbId) {
			return service.findMovieByImdbId(imdbId);
		}
	// create movie title
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie create(@RequestBody Movie movie) {
		return service.create(movie);
	}

	// update
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie update(@PathVariable("id") String movieId,
			@RequestBody Movie movie) {
		return service.update(movieId, movie);
	}

	// delete
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String movieId) {
		service.delete(movieId);
	}
}
