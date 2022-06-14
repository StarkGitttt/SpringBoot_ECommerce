package poly.edu.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQueries({
		@NamedQuery(
				name = "Order.findByUserId",
				query = "SELECT o FROM Order o WHERE o.account.username = ?1 AND status = FALSE"
				),
		@NamedQuery(
				name = "Order.findByOIAndUserId",
				query = "SELECT o FROM Order o WHERE o.account.username =?1 AND o.id = ?2 "
				),
		@NamedQuery(
				name = "Order.findUnapprovedOrder",
				query = "SELECT o FROM Order o WHERE o.available = TRUE ORDER BY o.status ASC"
				),
		@NamedQuery(
				name = "Order.findApprovedOrderByUserId",
				query = "SELECT o FROM Order o WHERE o.account = ?1 AND o.status = TRUE"
				),
		@NamedQuery(
				name = "Order.findOrderCancel",
				query = "SELECT o FROM Order o WHERE o.account = ?1 AND o.status = FALSE  AND o.available = FALSE"
				),
		@NamedQuery(
				name = "Order.detailRevenueToday",
				query = "SELECT o FROM Order o WHERE o.status = TRUE AND o.available = TRUE AND o.ceateDate = ?1"
						+ " ORDER BY o.total DESC"
				)
})
@Data
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Order(Account account, Date ceateDate, String address, float total, boolean status, boolean available, boolean delivery) {
		this.account = account;
		this.ceateDate = ceateDate;
		this.address = address;
		this.total = total;
		this.status = status;
		this.available = available;
		this.delivery = delivery;
	}
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	private Account account; 
	
	@Column(name="CreateDate")
	@Temporal(TemporalType.DATE)
	private Date ceateDate; 
	
	@Column(name="Address")
	private String address; 
	
	@Column(name = "Total")
	private float total;
	
	@Column(name = "Status")
	private boolean status;
	
	@Column(name = "Delivery")
	private boolean delivery;
	
	@Column(name="Avaiable")
	private boolean available;
	
	@Column(name = "ApprovalDate")
	@Temporal(TemporalType.DATE)
	private Date approvalDate; 
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "order")
	private List<Shipping> shippings;
	
	@OneToOne(mappedBy = "order")
	private PlaceReceipt placeReceipt;
}
