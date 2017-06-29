
package mdb.webapp.movieDbApplication;

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
	@Id
    @GeneratedValue
    private int id;
	
	private String title;
	private String releaseDate;
	private String genre;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<Person> director;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<Person> actors;
	
	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<Person> author;
	
	public Movie(String title, String releaseDate, String genre, List<Person> director, List<Person> actors,List<Person> authors) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.genre = genre;
     	this.director = director;
		this.actors = actors;
		this.author = authors;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Person> getDirector() {
		return director;
	}

	public void setDirector(List<Person> director) {
		this.director = director;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}

	public List<Person> getAuthors() {
		return author;
	}

	public void setAuthors(List<Person> authors) {
		this.author = authors;
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
	}
}
