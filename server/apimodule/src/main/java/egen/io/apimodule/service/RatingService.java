package egen.io.apimodule.service;

import java.util.List;

import egen.io.apimodule.entity.Rating;

public interface RatingService {
	public List<Rating> findAll();
	public Rating findOne(String id);
	public double findAvgRatingsOnMovie(String movieId);
	public Rating create(Rating rating);
	public Rating update(String id, Rating rating);
	public void delete(String id);
}
