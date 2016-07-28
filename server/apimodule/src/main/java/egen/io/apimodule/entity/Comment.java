package egen.io.apimodule.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table
@NamedQueries({ 
	@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c "),
	@NamedQuery(name = "Comment.findAllOnMovie", query = "SELECT c FROM Comment c where movie_movieId=:pMovieId "),
	
})
public class Comment {
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String commentId;
	
	
	@ManyToOne(cascade=CascadeType.REMOVE) 
	private User user;
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	@ManyToOne(cascade=CascadeType.ALL) 
	
	private Movie movie;
	
	private String commentText;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
}
