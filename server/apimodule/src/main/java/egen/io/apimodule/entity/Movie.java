package egen.io.apimodule.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table
@NamedQueries({ 
	@NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
	@NamedQuery(name = "Movie.findMovieByImdbId", query = "SELECT m FROM Movie m where imdbId=:pImdbId"),
	@NamedQuery(name = "Movie.findMovieByText", query = "Select m from Movie m join m.movieCast where title like :pTitle OR actor like :pActor OR writer like :pWriter OR Director like :pDirector OR language like :pLanguage OR awards like :pAwards OR plot like :pPlot ORDER BY m.movieId"),
	@NamedQuery(name = "Movie.findTopRatedMovies", query = "SELECT m FROM Movie m where m.type=:pType ORDER BY m.imdbRating DESC"),
	@NamedQuery(name = "Movie.findMoviesByType", query = "SELECT m FROM Movie m where m.type like :pSearchText  ORDER BY m.movieId"),
	@NamedQuery(name = "Movie.findMoviesByYear", query = "SELECT m FROM Movie m where m.year like :pSearchText ORDER BY m.movieId"),
	@NamedQuery(name = "Movie.findMoviesByGenre", query = "SELECT m FROM Movie m where m.genre like :pSearchText ORDER BY m.movieId"),
	@NamedQuery(name = "Movie.sortMoviesByImdbRating", query = "Select m from Movie m join m.movieCast where title like :pTitle OR actor like :pActor OR writer like :pWriter OR Director like :pDirector OR language like :pLanguage OR awards like :pAwards OR plot like :pPlot  ORDER BY m.imdbRating DESC"),
	@NamedQuery(name = "Movie.sortMoviesByImdbVotes", query = "Select m from Movie m join m.movieCast where title like :pTitle OR actor like :pActor OR writer like :pWriter OR Director like :pDirector OR language like :pLanguage OR awards like :pAwards OR plot like :pPlot  ORDER BY m.imdbVotes DESC"),
	@NamedQuery(name = "Movie.sortMoviesByImdbYear", query = "Select m from Movie m join m.movieCast where title like :pTitle OR actor like :pActor OR writer like :pWriter OR Director like :pDirector OR language like :pLanguage OR awards like :pAwards OR plot like :pPlot  ORDER BY m.year DESC"),

})
public class Movie {
	
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String movieId;
	private String title;
	private String year;
	private String rated;
	private  String released;
	private  String runtime;
	private String genre;
	@OneToOne(cascade=CascadeType.ALL) 
	private MovieCast movieCast;
	private String plot;
	private String language;
	private String country;
	private String poster;
	private String metascore;
	private String imdbRating;
	private String imdbVotes;
	private String imdbId;
	private String type;
	private String awards;	
	
	
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getMetascore() {
		return metascore;
	}
	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getImdbVotes() {
		return imdbVotes;
	}
	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	public MovieCast getMovieCast() {
		return movieCast;
	}
	public void setMovieCast(MovieCast movieCast) {
		this.movieCast = movieCast;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	


}
