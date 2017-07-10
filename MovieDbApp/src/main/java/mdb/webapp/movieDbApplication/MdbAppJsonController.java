package mdb.webapp.movieDbApplication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@RestController
@EnableWebMvc
public class MdbAppJsonController {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private PersonRepository personRepository;

	// returns person object matching name
	@RequestMapping(path = "/api/person/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findPerson(@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/person GET " + name);
		List<Person> people = personRepository.findPersonsByNameLike(name);
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
	}

	// adds a new person - name must be in request body and must not already
	// exist
	@RequestMapping(path = "/api/person", method = RequestMethod.POST)
	public ResponseEntity<Person> createPerson(@RequestBody Person p) {
		System.out.println("/api/person POST " + p.name);
		if (p.getName() == null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		if (personRepository.findPersonByName(p.getName()) != null) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}

		personRepository.save(p);
		return new ResponseEntity<Person>(p, HttpStatus.CREATED);
	}

	// deletes an existing person whose ID matches parameter in URL
	@RequestMapping(path = "/api/person/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePerson(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/person/{id} DELETE " + id);
		if (id == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
		personRepository.delete(id);
		return new ResponseEntity<String>("Person deleted", HttpStatus.OK);
	}

	// update/merge for an existing person whose ID matches "id" in json body of
	// request
	@RequestMapping(path = "/api/person", method = RequestMethod.PUT)
	public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
		System.out.println("/api/person PUT ");
		if (p.getId() == 0) {
			return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		}
		Person existing = personRepository.findOne(p.getId());
		existing.merge(p);
		personRepository.save(existing);
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}


	// creates a new movie - at least title must be provided and must not
	// already exist
	@RequestMapping(path = "/api/movie", method = RequestMethod.POST)
	public ResponseEntity<Movie> createMovie(@RequestBody Movie m) {
		String title = m.getTitle();
		if (title == null) {
			System.out.println("/api/movie POST title is null");
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("/api/movie POST " + title);
		Movie test = movieRepository.findMovieByTitle(title);
		if (test != null) {
			System.out.println("/api/movie title of movie already exists " + title);
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		}
		List<Person> directors = m.getDirectors();
		if (directors != null) {
			System.out.println("Directors not null");
			List<Person> newDirectors = new ArrayList<Person>();
			for (Person director : directors) {
				Person existing = personRepository.findPersonByName(director.name);
				director.merge(existing);
				director = personRepository.save(director);
				newDirectors.add(director);
			}

			m.setDirectors(newDirectors);
		} else {
			System.out.println("Directors NULL");
		}

		List<Person> actors = m.getActors();

		if (actors != null) {
			System.out.println("Actors not null");
			List<Person> newActors = new ArrayList<Person>();
			for (Person actor : actors) {
				Person existing = personRepository.findPersonByName(actor.name);
				actor.merge(existing);
				actor = personRepository.save(actor);
				newActors.add(actor);
			}
			m.setActors(newActors);
		} else {
			System.out.println("Actors NULL");
		}

		List<Person> authors = m.getAuthors();

		if (authors != null) {
			System.out.println("Authors not null");
			List<Person> newAuthors = new ArrayList<Person>();
			for (Person author : authors) {
				Person existing = personRepository.findPersonByName(author.name);
				author.merge(existing);
				author = personRepository.save(author);
				newAuthors.add(author);
			}

			m.setActors(newAuthors);
		} else {
			System.out.println("Authors NULL");
		}


		movieRepository.save(m);
		return new ResponseEntity<Movie>(m, HttpStatus.CREATED);
	}

	// deletes an existing movie whose ID matches the URL parameter
	@RequestMapping(path = "/api/movie/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMovie(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/movie/{id} DELETE " + id);
		;
		if (id == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		if (movieRepository.findByID(id) == null) {
			System.out.println("/api/movie/{id} DELETE " + id + "  movie does not exist");
			return new ResponseEntity<String>("Movie does not exist", HttpStatus.BAD_REQUEST);
		}

		movieRepository.delete(id);
		return new ResponseEntity<String>("Movie deleted ", HttpStatus.OK);
	}

	// updates/merges an existing movie where id matches the id contained in the
	// json body of the request
	@RequestMapping(path = "/api/movie", method = RequestMethod.PUT)
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie m) {
		System.out.println(" api/movie  PUT  id is" + m.getId());
		if (m.getId() == 0) {
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		}

		Movie existing = movieRepository.findByID(m.getId());
        if (existing == null){
        	return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
        }
		if (m.getDirectors() != null) {
			System.out.println("Directors included in request");
			List<Person> directors = existing.getDirectors();
			List<Person> newDirectors = new ArrayList<Person>();
			if (directors.size() != 0) {
				System.out.println("existing directors for movie found");
				for (Person director : directors) {
					Person otherDirector = personRepository.findPersonByName(director.name);
					director.merge(otherDirector);
					director = personRepository.save(director);
					newDirectors.add(director);
				}

				m.setDirectors(newDirectors);
			} else {
				System.out.println("existing directors not found for movie");
				for (Person director : m.getDirectors()) {
					Person otherDirector = personRepository.findPersonByName(director.name);
					director.merge(otherDirector);
					director = personRepository.save(director);
					newDirectors.add(director);
				}
			}
			m.setDirectors(newDirectors);
		}

		if (m.getActors() != null) {
			System.out.println("Actors included in request");
			List<Person> Actors = existing.getActors();
			List<Person> newActors = new ArrayList<Person>();
			if (Actors.size() != 0) {
				System.out.println("existing Actors for movie found");
				for (Person Actor : Actors) {
					Person otherActor = personRepository.findPersonByName(Actor.name);
					Actor.merge(otherActor);
					Actor = personRepository.save(Actor);
					newActors.add(Actor);
				}

				m.setActors(newActors);
			} else {
				System.out.println("existing Actors not found for movie");
				for (Person Actor : m.getActors()) {
					Person otherActor = personRepository.findPersonByName(Actor.name);
					Actor.merge(otherActor);
					Actor = personRepository.save(Actor);
					newActors.add(Actor);
				}
			}
			m.setActors(newActors);
		}

		if (m.getAuthors() != null) {
			System.out.println("authors included in request");
			List<Person> authors = existing.getAuthors();
			List<Person> newauthors = new ArrayList<Person>();
			if (authors.size() != 0) {
				System.out.println("existing authors for movie found");
				for (Person author : authors) {
					Person otherauthor = personRepository.findPersonByName(author.name);
					author.merge(otherauthor);
					author = personRepository.save(author);
					newauthors.add(author);
				}

				m.setAuthors(newauthors);
			} else {
				System.out.println("existing authors not found for movie");
				for (Person author : m.getAuthors()) {
					Person otherauthor = personRepository.findPersonByName(author.name);
					author.merge(otherauthor);
					author = personRepository.save(author);
					newauthors.add(author);
				}
			}
			m.setAuthors(newauthors);
		}
		System.out.println("before merge of request with existing");
		existing.merge(m);
		movieRepository.save(existing);
		return new ResponseEntity<Movie>(m, HttpStatus.OK);
	}


	// returns movie object(s) matching whose title matches or partially matches
	// title in parameter, case insensitive
	@RequestMapping(path = "/api/movie/title/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByTitleLike(@PathVariable(name = "title", required = true) String title) {
		System.out.println("/api/movie/title/{title} is " + title);
		if (title == null) {

			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies = movieRepository.findByTitleLike(title);
		System.out.println("Size of movies is" + movies.size());
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// returns an array of movie objects where actors name at
	// least partially match name parameter
	@RequestMapping(path = "/api/movie/actor/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByActorLike(@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/movie/actor/{name} name is " + name);
		if (name == null) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies = movieRepository.findByActorLike(name);
		System.out.println("Size of movies is" + movies.size());
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// returns an array of movie objects where directors name at
	// least partially match name parameter
	@RequestMapping(path = "/api/movie/director/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByDirectorLike(@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/movie/director/{name} name is " + name);
		if (name == null) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies = movieRepository.findByDirectorLike(name);
		System.out.println("Size of movies is" + movies.size());
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// returns an array of movie objects where authors name at
	// least partially match name parameter
	@RequestMapping(path = "/api/movie/author/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByAuthorLike(@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/movie/director/{name} name is " + name);
		if (name == null) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies = movieRepository.findByAuthorLike(name);
		System.out.println("Size of movies is" + movies.size());
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// returns an array of movie objects whose genre matches or partially
	// matches URL parameter, case insensitive
	@RequestMapping(path = "/api/movie/genre/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByGenreLike(@PathVariable(name = "genre", required = true) String genre) {
		System.out.println("/api/movie/genre/ is " + genre);
		if (genre == null) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies = movieRepository.findByGenreLike(genre);
		System.out.println("Size of movies is" + movies.size());
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// returns an array of movie objects whose year matches or partially matches
	// URL parameter, case insensitive
	@RequestMapping(path = "/api/movie/year/{year}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByYearLike(@PathVariable(name = "year", required = true) String year) {
		System.out.println("/api/movie/year/ is " + year);
		if (year == null) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
		List<Movie> movies = movieRepository.findByYearLike(year);
		System.out.println("Size of movies is" + movies.size());
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	// returns an array of Person objects who are actors and whose name matches
	// or partially matches URL parameter, case insensitive
	@RequestMapping(path = "/api/actor/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findActorsByNameLike(
			@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/actor/name/ is " + name);
		if (name == null) {
			return new ResponseEntity<List<Person>>(HttpStatus.BAD_REQUEST);
		}
		List<Person> actors = personRepository.findActorsByNameLike(name);
		System.out.println("Size of actors is" + actors.size());
		return new ResponseEntity<List<Person>>(actors, HttpStatus.OK);

	}

	// returns an array of Person objects who are directors and whose name
	// matches or partially matches URL parameter, case insensitive
	@RequestMapping(path = "/api/director/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findDirectorsByNameLike(
			@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/director/name/ is " + name);
		if (name == null) {
			return new ResponseEntity<List<Person>>(HttpStatus.BAD_REQUEST);
		}
		List<Person> directors = personRepository.findDirectorsByNameLike(name);
		System.out.println("Size of directors is" + directors.size());
		return new ResponseEntity<List<Person>>(directors, HttpStatus.OK);

	}

	// returns an array of Person objects who are authors and whose name matches
	// or partially matches URL parameter, case insensitive
	@RequestMapping(path = "/api/author/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAuthorsByNameLike(
			@PathVariable(name = "name", required = true) String name) {
		System.out.println("/api/author/name/ is " + name);
		if (name == null) {
			return new ResponseEntity<List<Person>>(HttpStatus.BAD_REQUEST);
		}
		List<Person> authors = personRepository.findAuthorsByNameLike(name);
		System.out.println("Size of authors is" + authors.size());
		return new ResponseEntity<List<Person>>(authors, HttpStatus.OK);

	}

	// returns an array of Person objects who are directors
	@RequestMapping(path = "/api/directors", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAllDirectors() {
		System.out.println("/api/directors GET");

		List<Person> directors = personRepository.findAllDirectors();
		System.out.println("Size of directors is" + directors.size());
		return new ResponseEntity<List<Person>>(directors, HttpStatus.OK);

	}

	// returns an array of Person objects who are actors
	@RequestMapping(path = "/api/actors", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAllActors() {
		System.out.println("/api/actors GET");

		List<Person> actors = personRepository.findAllActors();
		System.out.println("Size of actors is" + actors.size());
		return new ResponseEntity<List<Person>>(actors, HttpStatus.OK);

	}

	// returns an array of Person objects who are authors
	@RequestMapping(path = "/api/authors", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAllAuthors() {
		System.out.println("/api/authors GET");

		List<Person> authors = personRepository.findAllAuthors();
		System.out.println("Size of authors is" + authors.size());
		return new ResponseEntity<List<Person>>(authors, HttpStatus.OK);

	}
	
	// returns an array of all movie objects 
		@RequestMapping(path = "/api/movies", method = RequestMethod.GET)
		public ResponseEntity<List<Movie>> findAllMovies() {
			System.out.println("/api/movies GET " );
			
			List<Movie> movies = movieRepository.findAllMovies();
			System.out.println("Size of movies is" + movies.size());
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

		}
		

}