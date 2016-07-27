package egen.io.apimodule.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egen.io.apimodule.entity.Comment;
import egen.io.apimodule.exception.CommentNotFoundException;
import egen.io.apimodule.repository.CommentRepository;
import egen.io.apimodule.service.CommentService;


@Service
public class CommentServiceImp implements CommentService {
	@Autowired
	CommentRepository repository;

	@Override
	public List<Comment> findAll() {
		return repository.findAll();
	}
	@Override
	public Comment findOne(String id) {
		Comment existing = repository.findOne(id);
		if (existing == null) {
			throw new CommentNotFoundException("Comment with id:" + id + " not found");
		}
	
		return existing;
	}

	@Override
	@Transactional
	public Comment create(Comment comment) {
			return repository.create(comment);
	}

	@Override
	@Transactional
	public Comment update(String id, Comment comment) {
		Comment existing = repository.findOne(id);
		if (existing == null) {
			throw new CommentNotFoundException("Comment with id:" + id + " not found");
		}
		comment.setCommentId(existing.getCommentId());
		return repository.update(comment);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Comment existing = repository.findOne(id);
		if (existing == null) {
			throw new CommentNotFoundException("Comment with id:" + id + " not found");
		}
		repository.delete(existing);
	}
}
