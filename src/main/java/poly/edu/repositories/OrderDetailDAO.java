package poly.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.OrderDetail;
import poly.edu.models.Product;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
	@Query(name = "OD.getSellingProducts")
	List<Product> getSellingProducts();
}
