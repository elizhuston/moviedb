package mdb.webapp.movieDbApplication;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query("Select id, genre, release_date,title from Movie where title like CONCAT ('%', :title, '%')")
	public List<Movie> findByTitleLike(@Param("title") String title);
}
