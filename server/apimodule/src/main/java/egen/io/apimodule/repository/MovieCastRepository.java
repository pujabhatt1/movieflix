package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.MovieCast;

public interface MovieCastRepository {

	public List<MovieCast> findAll();
	public MovieCast findOne(String id);
	public MovieCast create(MovieCast movieCast);
	public MovieCast update(MovieCast movieCast);
	public void delete(MovieCast movieCast);
}
