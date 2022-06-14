package poly.edu.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.Report;

public interface ReportDAO extends JpaRepository<Report, Serializable> {
	
	@Query(name = "Report.findSellingProducts")
	List<Report> findSellingProductsNoSort();
	
	@Query
	Page<Report> findSellingProducts(Pageable pageable);
	
	@Query
	Long totalProductsSellToday(Date date);
	
	@Query
	List<Report> detailTotalProductsSellToday(Date date);
	
	@Query
	Long countUserUseSystem();
	
	@Query
	Long countVisitorToday(Date date);
	
	@Query
	Double totalRevenueToday(Date date);
}
