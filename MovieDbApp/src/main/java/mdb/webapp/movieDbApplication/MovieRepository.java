package mdb.webapp.movieDbApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
