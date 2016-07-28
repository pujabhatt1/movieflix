package egen.io.apimodule.service;

import java.util.List;

import egen.io.apimodule.entity.Movie;

public interface MovieService {
	public List<Movie> findAll();
	public List<Movie> findMovieByText(String searchText);
	public List<Movie> findMovieByField(String field,String searchText);
	public List<Movie> sortMovieByField(String searchText,String field);
	public List<Movie> findMovieByTopRating(String type);
	public Movie findMovieByImdbId(String imdbId);
	public Movie findOne(String id);
	public Movie create(Movie movie);
	public Movie update(String id, Movie movie);
	public void delete(String id);
}
