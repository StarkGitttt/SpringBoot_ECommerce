package poly.edu.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@NamedQueries({
	@NamedQuery(
			name = "OD.getSellingProducts",
			query = "SELECT o.product FROM OrderDetail o WHERE o.product.available = TRUE"
					+ " GROUP BY o.product"
			),
	@NamedQuery(
			name = "Report.findSellingProducts",
			query = "SELECT new Report(o.product, COUNT(o.product)) FROM OrderDetail o"
					+ " WHERE o.product.available = TRUE AND o.order.status = TRUE"
					+ " GROUP BY o.product"
					+ " ORDER BY COUNT(o.product) DESC"
			)
})
@Data
@NoArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public OrderDetail(Order order, Product product, int quantity,  float price) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(name="Price")
	private float price; 
	
	@Column(name="Quantity")
	private  int quantity; 
	
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order; 
}
