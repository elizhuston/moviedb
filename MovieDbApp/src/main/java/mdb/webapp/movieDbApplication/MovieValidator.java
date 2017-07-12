package mdb.webapp.movieDbApplication;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.stereotype.Component;

@Component("beforeCreateMovieValidator")
public class MovieValidator implements Validator {
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		 return MovieValidator.class.equals(clazz);
		
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		 Movie movie = (Movie) obj;
	        if (checkInputString(movie.getTitle())) {
	            errors.rejectValue("Title", "id.empty");
	        }
	    
	}
	  private boolean checkInputString(String input) {
	        return (input == null || input.trim().length() == 0);
	    }
}
