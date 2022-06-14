package poly.edu.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.Visitor;

public interface VisitorDAO extends JpaRepository<Visitor, Integer> {
	
	@Query
	List<Visitor> viewVisitorToday(Date date);
	
}
