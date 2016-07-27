package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.Movie;

public interface MovieRepository {
	
	public List<Movie> findAll();
	public Movie findOne(String id);
	public Movie create(Movie movie);

	public Movie update(Movie movie);

	public void delete(Movie movie);
}
