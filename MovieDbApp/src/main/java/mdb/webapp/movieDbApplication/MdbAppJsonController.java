package mdb.webapp.movieDbApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MdbAppJsonController {
	
	@RequestMapping(path = "/person.json", method = RequestMethod.GET)
	public Person jsonHome(String name, String city, Integer age) {
		
		if (age == null) {
			age = 3;
		}
		return new Person(name, city, age);
	}
}