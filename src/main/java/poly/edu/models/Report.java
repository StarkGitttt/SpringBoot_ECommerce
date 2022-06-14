package poly.edu.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NamedQueries({
	
	@NamedQuery(
			name = "Report.totalProductsSellToday",
			query = "SELECT count(o.product) FROM OrderDetail o "
					+ "WHERE o.product.available = TRUE AND o.order.status = TRUE AND o.order.approvalDate = ?1"			
			),
	@NamedQuery(
			name = "Report.detailTotalProductsSellToday",
			query = "SELECT new Report(o.product, count(o.product)) FROM OrderDetail o "
					+ "WHERE o.product.available = TRUE AND o.order.status = TRUE AND o.order.approvalDate = ?1 "
					+ "GROUP BY o.product"			
			),
	@NamedQuery(
			name = "Report.countUserUseSystem",
			query = "SELECT count(u) FROM Account u WHERE u.admin = FALSE AND u.activate = TRUE"
			),
	@NamedQuery(
			name = "Report.countVisitorToday",
			query = "SELECT count(v) FROM Visitor v WHERE v.dateVisit = ?1"
			),
	@NamedQuery(
			name = "Report.totalRevenueToday",
			query = "SELECT SUM(o.total) FROM Order o WHERE  o.status = TRUE AND o.approvalDate = ?1 "
			)
})

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private Serializable group;
    private Long amountLike;
    private Long quantity;
    
    public Report(Serializable group, Long quantity) {
    	this.group = group;
    	this.quantity = quantity;
    }
}
