package mdb.webapp.movieDbApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableWebMvc
//@ComponentScan({"org.app.genesis.client.controller","org.app.genesis.commons.service",
//    "org.app.app.commons.security","org.app.genesis.inventory.service","org.app.genesis.client.auth"})
//@EnableJpaRepositories(basePackages = "org.app.genesis.*.repo")
//@EntityScan(basePackages = "org.app.genesis.*.model")
public class MdbApp {

	public static void main(String[] args) {
		SpringApplication.run(MdbApp.class, args);
	}
	
//	@Override
//    public void run(String... strings) throws Exception {
//        System.out.printf("The database contains %s movie.\n", movieRepository.count());
//    }
}
