
package mdb.webapp.movieDbApplication;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "movie")

public class Movie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private int id;
	
	private String title;
	private String releaseDate;
	private String genre;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="DirecorId")
	private List<Person> directors;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="actorId")
	private List<Person> actors;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="authorId")
	private List<Person> authors;
	
	public Movie(String title, String releaseDate, String genre, List<Person> directors, List<Person> actors,List<Person> authors) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.genre = genre;
     	this.directors = directors;
		this.actors = actors;
		this.authors = authors;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Person> getDirectors() {
		return directors;
	}

	public void setDirector(List<Person> directors) {
		this.directors = directors;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}

	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

	public Movie() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;

	}	
	
	public void merge(Movie other) {
		if (other.title != null) {
			this.title = other.title;
		}
		if (other.releaseDate != null){
			this.releaseDate=other.releaseDate;
		}
		if (other.genre != null){
			this.genre=other.genre;

		}
		if (!other.directors.isEmpty()){
			this.directors=other.directors;
		}

	}
}
