package egen.io.apimodule.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.MovieCast;
import egen.io.apimodule.entity.Movie;
import egen.io.apimodule.entity.User;
import egen.io.apimodule.exception.MovieNotFoundException;
import egen.io.apimodule.exception.UserAlreadyExistsException;
import egen.io.apimodule.exception.UserNotFoundException;
import egen.io.apimodule.repository.MovieCastRepository;
import egen.io.apimodule.repository.MovieRepository;
import egen.io.apimodule.service.MovieService;

@Service
public class MovieServiceImp implements MovieService {
	@Autowired
	MovieRepository repository;
	@Autowired
	MovieCastRepository castRepository;
	@Override
	public List<Movie> findAll() {
	
		return repository.findAll();
	}

	@Override
	public Movie findOne(String id) {
		Movie existing = repository.findOne(id);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + id + " not found");
		}
	
		return existing;
	}

	@Override
	@Transactional
	public Movie create(Movie movie) {
		
		MovieCast movieCast=castRepository.create(movie.getMovieCast());
		
		movie.setMovieCast(movieCast);
		return repository.create(movie);
	}

	@Override
	@Transactional
	public Movie update(String id, Movie movie) {
		Movie existing = repository.findOne(id);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + id + " not found");
		}
		movie.setMovieId(existing.getMovieId());
		return repository.update(movie);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Movie existing = repository.findOne(id);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + id + " not found");
		}
		repository.delete(existing);
	}


}
