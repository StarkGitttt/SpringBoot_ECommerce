package poly.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.models.Order;
import poly.edu.models.PlaceReceipt;

public interface PlaceReceiptDAO extends JpaRepository<PlaceReceipt, Integer>{
	PlaceReceipt findByOrder(Order order);
}
