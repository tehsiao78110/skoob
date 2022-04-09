package model.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class AuthorBean {
	@Id
	private Integer authorid;

	private String authorname;
	private String authorintro;
	
	public Integer getAuthorid() {
		return authorid;
	}
	public void setAuthorid(Integer authorid) {
		this.authorid = authorid;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getAuthorintro() {
		return authorintro;
	}
	public void setAuthorintro(String authorintro) {
		this.authorintro = authorintro;
	}
	
	
}
