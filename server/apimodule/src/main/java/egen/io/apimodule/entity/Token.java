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
	@NamedQuery(name = "Token.findAll", query = "SELECT e FROM Token e ORDER BY e.id ASC"),
	@NamedQuery(name = "Token.findByToken", query = "SELECT e FROM Token e WHERE e.token=:pToken"),
})
public class Token {
	@Override
	public String toString() {
		return "Token [id=" + id + ", token=" + token + "]";
	}
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;
	private String token;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
