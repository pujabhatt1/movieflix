package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.Movie;

public interface MovieRepository {
	
	public List<Movie> findAll();
	public List<Movie> findMovieByText(String searchText);
	public List<Movie>findMovieByField(String field,String searchText);
	public List<Movie> sortMovieByField(String searchText,String field);
	public List<Movie> findMovieByTopRating(String type);
	public List<Movie> findMovieByImdbId(String imdbId);
	public Movie findOne(String id);
	public Movie create(Movie movie);
	public Movie update(Movie movie);
	public void delete(Movie movie);
}
