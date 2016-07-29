package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.Rating;

public interface RatingRepository {
		public List<Rating> findAll();
		public Rating findOne(String id);
		public List<Double> findAvgRatingsOnMovie(String movieId);
		public Rating create(Rating rating);
		public Rating update(Rating rating);
		public void delete(Rating rating);
}
