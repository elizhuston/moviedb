package mdb.webapp.movieDbApplication;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class MovieDbAppController {
	@Autowired
    private MovieRepository movieRepository;
	
	@RequestMapping(path = "/person", method = RequestMethod.GET)
	public String person(Model model, String name) {
		Person p = new Person(name);
		model.addAttribute("person", p);
		return "person";
	}
	
  	

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		model.addAttribute("name", session.getAttribute("userName"));
		return "home";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, String userName, String other) {
		System.out.println(other);
		session.setAttribute("userName", userName);
		return "redirect:/movie";
	}	
	
		
	
	@RequestMapping(path = "/movie", method = RequestMethod.GET)

	public String movie(Model model, String title, String releaseDate, String genre, List<Person> director, List<Person> actors,List<Person> authors) {
		Movie m = new Movie(title, releaseDate, genre, director,actors,authors);
		model.addAttribute("movie", m);
		return "movie";
	}

	
}