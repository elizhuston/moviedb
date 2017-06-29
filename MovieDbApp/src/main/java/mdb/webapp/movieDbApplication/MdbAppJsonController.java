package mdb.webapp.movieDbApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//REST
@RestController
public class MdbAppJsonController {
	@Autowired
    private MovieRepository movieRepository;
	
	@RequestMapping(path = "/person.json", method = RequestMethod.GET)
	public Person jsonHome(String name, String dob) {
		
		if (dob == null) {
			dob = "1/1/1900";
		}
		return new Person(name);
	}
	
	@RequestMapping(path = "/api/movie", method = RequestMethod.POST)
	public Movie createMovie(@RequestBody Movie m)  {
		movieRepository.save(m);
		return m;
	}	
	@RequestMapping(path = "/api/movie/{id}", method = RequestMethod.DELETE)
	public void deleteMovie(@PathVariable(name="id", required=true) Integer id)  {
		movieRepository.delete(id);
		return;
	}	
	
	@RequestMapping(path = "/api/movie", method = RequestMethod.PUT)
	public Movie updateMovie(@RequestBody Movie m) {
		movieRepository.save(m);
		return m;
	}	

}