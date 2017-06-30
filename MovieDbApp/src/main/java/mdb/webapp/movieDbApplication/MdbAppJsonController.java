package mdb.webapp.movieDbApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController

public class MdbAppJsonController {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(path = "/api/person", method = RequestMethod.GET)
	public Person findPerson(String name) {
		return new Person(name);
	}	
	
	@RequestMapping(path = "/api/person", method = RequestMethod.POST)
	public Person createPerson(@RequestBody Person p)  {
		personRepository.save(p);
		return p;
	}	
	
	@RequestMapping(path = "/api/director", method = RequestMethod.POST)
	public Person createDirector(@RequestBody Person p)  {
		personRepository.save(p);
		return p;
	}
	
	@RequestMapping(path = "/api/person/{id}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable(name="id", required=true) Integer id) {	
		personRepository.delete(id);
	}
	
	@RequestMapping(path = "/api/person", method = RequestMethod.PUT)
	public ResponseEntity<Person> updatePerson(@RequestBody Person p) {	
		if (p.getId() == 0) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		Person existing = personRepository.findOne(p.getId());
		existing.merge(p);
		personRepository.save(existing);
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}
		
	
	@RequestMapping(path = "/api/movie", method = RequestMethod.POST)
	public Movie createMovie(@RequestBody Movie m)  {
			movieRepository.save(m);
		return m;
	}
	
	public void movie(@RequestBody Movie m) {
		// save the movie
		System.out.println(m);
		movieRepository.save(m);
	}
	
	@RequestMapping(path = "/api/movie/{id}", method = RequestMethod.DELETE)
	public void deleteMovie(@PathVariable(name="id", required=true) Integer id) {	
		movieRepository.delete(id);
	}
	
	@RequestMapping(path = "/api/movie", method = RequestMethod.PUT)
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie m) {	
		System.out.println(" PUT/api/movie id is" + m.getId());
		if (m.getId() == 0) {
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		}
		Movie existing = movieRepository.findByID(m.getId());
		existing.merge(m);
		movieRepository.save(existing);
		return new ResponseEntity<Movie>(m, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/movie/title/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByTitleLike(@PathVariable(name="title", required=true) String title){
		System.out.println("/movie/title/ is " + title);
		if (title == null){
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies=movieRepository.findByTitleLike(title);
		System.out.println("Size of movies is" +  movies.size() );
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		
	}
	@RequestMapping(path = "/movie/person/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByPersonLike(@PathVariable(name="name", required=true) String name){
		System.out.println("/movie/name/ is " + name);
		if (name == null){
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies=movieRepository.findByPersonLike(name);
		System.out.println("Size of movies is" +  movies.size() );
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = "/movie/genre/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByGenreLike(@PathVariable(name="genre", required=true) String genre){
		System.out.println("/movie/genre/ is " + genre);
		if (genre == null){
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies=movieRepository.findByGenreLike(genre);
		System.out.println("Size of movies is" +  movies.size() );
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(path = "/movie/year/{year}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByYearLike(@PathVariable(name="year", required=true) String year){
		System.out.println("/movie/year/ is " + year);
		if (year == null){
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies=movieRepository.findByYearLike(year);
		System.out.println("Size of movies is" +  movies.size() );
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = "/actor/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findByActorLike(@PathVariable(name="name", required=true) String name){
		System.out.println("/actor/name/ is " + name);
		if (name == null){
			return new ResponseEntity<List<Person>>(HttpStatus.BAD_REQUEST);
		}
		List<Person> actors=personRepository.findByActorLike(name);
		System.out.println("Size of actors is" +  actors.size() );
		return new ResponseEntity<List<Person>>(actors, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = "/director/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findByDirectorLike(@PathVariable(name="name", required=true) String name){
		System.out.println("/director/name/ is " + name);
		if (name == null){
			return new ResponseEntity<List<Person>>(HttpStatus.BAD_REQUEST);
		}
		List<Person> directors=personRepository.findByDirectorLike(name);
		System.out.println("Size of directors is" +  directors.size() );
		return new ResponseEntity<List<Person>>(directors, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = "/author/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findByAuthorLike(@PathVariable(name="name", required=true) String name){
		System.out.println("/author/name/ is " + name);
		if (name == null){
			return new ResponseEntity<List<Person>>(HttpStatus.BAD_REQUEST);
		}
		List<Person> authors=personRepository.findByAuthorLike(name);
		System.out.println("Size of authors is" +  authors.size() );
		return new ResponseEntity<List<Person>>(authors, HttpStatus.OK);
		
	}
	
	
}