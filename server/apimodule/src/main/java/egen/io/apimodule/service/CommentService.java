package egen.io.apimodule.service;

import java.util.List;

import egen.io.apimodule.entity.Comment;


public interface CommentService {
	public List<Comment> findAll();
	public Comment findOne(String id);
	public Comment create(Comment comment);
	public Comment update(String id, Comment comment);
	public void delete(String id);
}
