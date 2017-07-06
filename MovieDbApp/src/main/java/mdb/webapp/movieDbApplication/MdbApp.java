package mdb.webapp.movieDbApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MdbApp {

	public static void main(String[] args) {
		SpringApplication.run(MdbApp.class, args);
	}
	
//	@Override
//    public void run(String... strings) throws Exception {
//        System.out.printf("The database contains %s movie.\n", movieRepository.count());
//    }
}
