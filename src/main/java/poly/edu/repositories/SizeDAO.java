package poly.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.Size;

public interface SizeDAO extends JpaRepository<Size, Integer> {
	
	@Query(name = "Size.findByIdProduct")
	List<Size> findByIdProduct(int idProduct);
}
