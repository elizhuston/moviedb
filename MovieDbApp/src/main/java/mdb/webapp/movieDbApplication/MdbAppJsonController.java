package mdb.webapp.movieDbApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MdbAppJsonController {
	
	@RequestMapping(path = "/person.json", method = RequestMethod.GET)
	public Person jsonHome(String name, String dob) {
		
		if (dob == null) {
			dob = "1/1/1900";
		}
		return new Person(name);
	}
	
	@RequestMapping(path = "/api/movie", method = RequestMethod.POST)
	public Movie movie(@RequestBody Movie m)  {
		return m;
	}	

}