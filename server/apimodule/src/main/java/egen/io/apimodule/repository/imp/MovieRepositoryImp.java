package egen.io.apimodule.repository.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.apimodule.entity.Movie;
import egen.io.apimodule.repository.MovieRepository;

@Repository
public class MovieRepositoryImp implements MovieRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Movie> findAll() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findMovieByText(String searchText){
		//TypedQuery<Movie> query = em.createQuery("Select m from Movie m join m.movieCast where title like :pTitle OR actor like :pActor OR writer like :pWriter OR Director like :pDirector OR language like :pLanguage OR awards like :pAwards OR plot like :pPlot",Movie.class);
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findMovieByText", Movie.class);
		query.setParameter("pTitle",	'%'+searchText+'%');
		query.setParameter("pActor",	'%'+searchText+'%');
		query.setParameter("pWriter",	'%'+searchText+'%');
		query.setParameter("pDirector",	'%'+searchText+'%');
		query.setParameter("pLanguage",	'%'+searchText+'%');
		query.setParameter("pAwards",	'%'+searchText+'%');
		query.setParameter("pPlot",	'%'+searchText+'%');
		return query.getResultList();
	}
	
	@Override
	public List<Movie>findMovieByField(String field,String searchText){
		TypedQuery<Movie> query ;
	
		if(field.equals("type")){
			 query = em.createNamedQuery("Movie.findMoviesByType", Movie.class);
		}
		else if(field.equals("year")){
			query = em.createNamedQuery("Movie.findMoviesByYear", Movie.class);
		}
		else if (field.equals("genre")){
			query = em.createNamedQuery("Movie.findMoviesByGenre", Movie.class);
		}
		else{
			return findAll();
		}
		query.setParameter("pSearchText", '%'+searchText+'%');
		return query.getResultList();
	}
	
	@Override
	public List<Movie> sortMovieByField(String searchText,String field){
		TypedQuery<Movie> query ;
		
		if(field.equals("imdbRating")){
			 query = em.createNamedQuery("Movie.sortMoviesByImdbRating", Movie.class);
		}
		else if(field.equals("imdbVotes")){
			query = em.createNamedQuery("Movie.sortMoviesByImdbVotes", Movie.class);
		}
		else if (field.equals("year")){
			query = em.createNamedQuery("Movie.sortMoviesByImdbYear", Movie.class);
		}
		else{
			return findMovieByText(searchText);
		}
		query.setParameter("pTitle",	'%'+searchText+'%');
		query.setParameter("pActor",	'%'+searchText+'%');
		query.setParameter("pWriter",	'%'+searchText+'%');
		query.setParameter("pDirector",	'%'+searchText+'%');
		query.setParameter("pLanguage",	'%'+searchText+'%');
		query.setParameter("pAwards",	'%'+searchText+'%');
		query.setParameter("pPlot",	'%'+searchText+'%');
		return query.getResultList();
	}
	@Override
	public List<Movie> findMovieByTopRating(String type) {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findTopRatedMovies", Movie.class);
		query.setParameter("pType", type);
		return query.getResultList();
	}
	
	@Override
	public Movie findOne(String id) {
		return em.find(Movie.class, id);
	}
	@Override
	public List<Movie> findMovieByImdbId(String imdbId){
		TypedQuery<Movie> query =em.createNamedQuery("Movie.findMovieByImdbId", Movie.class);
		query.setParameter("pImdbId", imdbId);
		return query.getResultList();
	}

	
	@Override
	
	public Movie create(Movie movie) {
		em.persist(movie);
		return movie;
	}

	@Override
	public Movie update(Movie movie) {
		return em.merge(movie);
	}

	@Override
	public void delete(Movie movie) {
		em.remove(movie);
	}
}
