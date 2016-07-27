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
	private String Actor;
	private String Director;
	private String Writer;
	
	
	public String getMovieCastId() {
		return movieCastId;
	}
	public void setMovieCastId(String movieCastId) {
		this.movieCastId = movieCastId;
	}
	public String getActors() {
		return Actor;
	}
	public void setActor(String actor) {
		Actor = actor;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	public String getWriter() {
		return Writer;
	}
	public void setWriter(String writer) {
		Writer = writer;
	}
	
}
