package mdb.webapp.movieDbApplication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	@Query("SELECT DISTINCT actors from Movie WHERE UPPER(name) like UPPER(CONCAT('%',:name, '%')))")
	public List<Person> findByActorLike(@Param("name") String name);
	
	@Query("SELECT DISTINCT directors from Movie WHERE UPPER(name) like UPPER(CONCAT('%',:name, '%')))")
	public List<Person> findByDirectorLike(@Param("name") String name);
	
	@Query("SELECT DISTINCT authors from Movie WHERE UPPER(name) like UPPER(CONCAT('%',:name, '%')))")
	public List<Person> findByAuthorLike(@Param("name") String name);
}
