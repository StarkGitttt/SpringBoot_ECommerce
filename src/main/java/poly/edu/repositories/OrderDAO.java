package poly.edu.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.Account;
import poly.edu.models.Order;

public interface OrderDAO extends JpaRepository<Order, Integer> {
	
	@Query(name = "Order.findByUserId")
	List<Order> findByUserId(String username);
	
	@Query(name = "Order.findByOIAndUserId")
	Order findByOIAndUserId(String username, int id);
	
	@Query(name = "Order.findUnapprovedOrder")
	List<Order> findUnapprovedOrder();
	
	@Query(name = "Order.findApprovedOrderByUserId")
	List<Order> findApprovedOrderByUserId(Account user);
	
	List<Order> findByStatusEqualsAndAvailableEquals(boolean status, boolean avaiable,  Sort sort);
	
	@Query(name = "Order.findOrderCancel")
	List<Order> findOrderCancel(Account user);
	
	@Query
	List<Order> detailRevenueToday(Date date);
}
