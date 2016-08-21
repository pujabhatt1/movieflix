package egen.io.apimodule.repository.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.apimodule.entity.Rating;
import egen.io.apimodule.repository.RatingRepository;

@Repository
public class RatingRepositoryImp implements RatingRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Rating> findAll() {
		TypedQuery<Rating> query = em.createNamedQuery("Rating.findAll", Rating.class);
		return query.getResultList();
	}

	@Override
	public Rating findOne(String id) {
		return em.find(Rating.class, id);
	}
	public List<Double> findAvgRatingsOnMovie(String movieId){
		Query  query = em.createQuery("SELECT avg(ratings) as average_rating FROM Rating r where movie_movieId=:pMovieId");
		query.setParameter("pMovieId",movieId);
		return query.getResultList();
		
	}
	
	@Override
	public Rating create(Rating rating) {
		System.out.println(rating);
	//	Query  query = em.createQuery("DELETE FROM Rating r where movie_movieId=:pMovieId and user_id=:pUserId");
		//query.setParameter("pMovieId",rating.getMovie().getMovieId());
		//query.setParameter("pUserId",rating.getUser().getId());

		em.persist(rating);
		
		return rating;
	}

	@Override
	public Rating update(Rating rating) {
		return em.merge(rating);
	}

	@Override
	public void delete(Rating rating) {
		em.remove(rating);
	}
}
