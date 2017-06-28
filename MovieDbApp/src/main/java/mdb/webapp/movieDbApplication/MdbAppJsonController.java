package mdb.webapp.movieDbApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public void movie(@RequestBody Movie m) {
		// save the movie
		movieRepository.save(m);
		;
	}

}