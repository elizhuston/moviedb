
package mdb.webapp.movieDbApplication;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

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
	
//	int directorId;
//	ArrayList<Actor> actors;
//	ArrayList<Author> authors;
	
	public Movie(String title, String releaseDate, String genre) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.genre = genre;
//		this.directorId = directorId;
//		this.actors = actors;
//		this.authors = authors;
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
}
