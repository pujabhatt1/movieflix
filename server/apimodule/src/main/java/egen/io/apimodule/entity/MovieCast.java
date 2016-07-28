package egen.io.apimodule.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table
@NamedQueries({ 
	@NamedQuery(name = "MovieCast.findAll", query = "SELECT c FROM MovieCast c"),
})
public class MovieCast {
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String movieCastId;
	private String actor;
	private String director;
	private String writer;
	
	
	public String getMovieCastId() {
		return movieCastId;
	}
	public void setMovieCastId(String movieCastId) {
		this.movieCastId = movieCastId;
	}
	
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
}
