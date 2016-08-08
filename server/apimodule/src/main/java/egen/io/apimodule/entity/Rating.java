package egen.io.apimodule.entity;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

@Entity
@Table
@NamedQueries({ 
	@NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r "),
	@NamedQuery(name = "Rating.findAvgRatingOnMovie", query = "SELECT avg(ratings) as average_rating FROM Rating r where movie_movieId=:pMovieId"),
	//@NamedQuery(name = "Rating.findRatingByUserOnMovie", query = "SELECT r  FROM Rating r where r.movieId=:pMovieId and r.user_id=:pUserId"),

})
public class Rating {
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	
	private String ratingId;
	@OneToOne
	private Movie movie;
	@OneToOne
	private User user;
	@Range(min=1,max=5)
	private int ratings;
	
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
		
	
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
