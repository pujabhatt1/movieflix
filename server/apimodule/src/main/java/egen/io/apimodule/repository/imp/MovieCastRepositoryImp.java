package egen.io.apimodule.repository.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.apimodule.entity.MovieCast;
import egen.io.apimodule.repository.MovieCastRepository;

@Repository
public class MovieCastRepositoryImp implements MovieCastRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MovieCast> findAll() {
		TypedQuery<MovieCast> query = em.createNamedQuery("MovieCast.findAll", MovieCast.class);
		return query.getResultList();
	}

	@Override
	public MovieCast findOne(String id) {
		return em.find(MovieCast.class, id);
	}

	@Override
	
	public MovieCast create(MovieCast movieCast) {
		em.persist(movieCast);
		return movieCast;
	}

	@Override
	public MovieCast update(MovieCast movieCast) {
		return em.merge(movieCast);
	}

	@Override
	public void delete(MovieCast movieCast) {
		em.remove(movieCast);
	}
}
