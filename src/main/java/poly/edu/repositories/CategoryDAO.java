package poly.edu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
	@Query( name = "Category.findCategoryExistProduct")
	List<Category> findCategoryExistProduct();
	
	Optional<Category> findByNameEquals(String name);
}
