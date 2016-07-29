package egen.io.apimodule.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.Rating;
import egen.io.apimodule.exception.RatingNotFoundException;
import egen.io.apimodule.repository.RatingRepository;
import egen.io.apimodule.repository.RatingRepository;
import egen.io.apimodule.service.RatingService;

@Service
public class RatingServiceImp implements RatingService {
	@Autowired
	RatingRepository repository;
	@Override
	public List<Rating> findAll() {
		return repository.findAll();
	}
	@Override
	public Rating findOne(String id) {
		Rating existing = repository.findOne(id);
		if (existing == null) {
			throw new RatingNotFoundException("Rating with id:" + id + " not found");
		}
	
		return existing;
	}
	public double findAvgRatingsOnMovie(String movieId){
		List<Double> avgRating=repository.findAvgRatingsOnMovie(movieId);
		System.out.println(avgRating);
		if(avgRating!=null && avgRating.size()==1){
			return avgRating.get(0);
		}
		else{
			throw new RatingNotFoundException("Rating average not found");	
		}
	}
	@Override
	@Transactional
	public Rating create(Rating rating) {
			return repository.create(rating);
	}

	@Override
	@Transactional
	public Rating update(String id, Rating rating) {
		Rating existing = repository.findOne(id);
		if (existing == null) {
			throw new RatingNotFoundException("Rating with id:" + id + " not found");
		}
		rating.setRatingId(existing.getRatingId());
		return repository.update(rating);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Rating existing = repository.findOne(id);
		if (existing == null) {
			throw new RatingNotFoundException("Rating with id:" + id + " not found");
		}
		repository.delete(existing);
	}
}
