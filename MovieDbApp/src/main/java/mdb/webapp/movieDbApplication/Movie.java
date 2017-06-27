
package mdb.webapp.movieDbApplication;
import java.util.ArrayList;

public class Movie {
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
