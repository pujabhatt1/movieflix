package egen.io.apimodule.repository;

import java.util.List;

import egen.io.apimodule.entity.Comment;

public interface CommentRepository {
	public List<Comment> findAll();
	public Comment findOne(String id);
	public List<Comment> findAllCommentsOnMovie(String movieId);
	public Comment create(Comment comment);
	public Comment update(Comment comment);
	public void delete(Comment comment);
}
