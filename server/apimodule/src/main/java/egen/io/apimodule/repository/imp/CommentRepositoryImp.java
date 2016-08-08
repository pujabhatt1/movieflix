package egen.io.apimodule.repository.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.apimodule.entity.Comment;
import egen.io.apimodule.repository.CommentRepository;
@Repository
public class CommentRepositoryImp implements CommentRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Comment> findAll() {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll", Comment.class);
		return query.getResultList();
	}

	@Override
	public Comment findOne(String id) {
		return em.find(Comment.class, id);
	}
	public List<Comment> findAllCommentsOnMovie(String movieId){
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findAllOnMovie", Comment.class);
		query.setParameter("pMovieId",movieId);
		return query.getResultList();
	}
	
	@Override
	public Comment create(Comment comment) {
		em.persist(comment);
		return comment;
	}

	@Override
	public Comment update(Comment Comment) {
		return em.merge(Comment);
	}

	@Override
	public void delete(Comment comment) {
		em.remove(comment);
	}
}
