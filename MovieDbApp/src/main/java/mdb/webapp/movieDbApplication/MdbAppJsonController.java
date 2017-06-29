package mdb.webapp.movieDbApplication;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(path = "/person.json", method = RequestMethod.GET)
	public Person jsonHome(String name) {

		// if (dob == null) {
		// dob = "1/1/1900";
		// }
		return new Person(name);
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
		if (m.getId() == 0) {
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		}
		Movie existing = movieRepository.findOne(m.getId());
		existing.merge(m);
		movieRepository.save(existing);
		return new ResponseEntity<Movie>(m, HttpStatus.OK);
	}
	

}