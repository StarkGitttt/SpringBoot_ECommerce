package poly.edu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PlaceReceipt")
public class PlaceReceipt {

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "Fullname")
	@NotBlank(message = "Vui lòng nhập trường này")
	private String fullname;
	
	@Column(name = "Address")
	@NotBlank(message = "Vui lòng nhập trường này")
	private String address;
	
	@Column(name = "Email")
	@NotBlank(message = "Vui lòng nhập trường này")
	private String email;
	
	@Column(name = "Phone")
	@NotBlank(message = "Vui lòng nhập trường này")
	private String phone;
	
	@Column(name = "Note")
	private String note;
	
	@OneToOne
	@JoinColumn(name = "OrderId")
	private Order order;
	
	public PlaceReceipt(PlaceReceipt placeReceipt, Order order) {
		this.fullname = placeReceipt.getFullname();
		this.address = placeReceipt.getAddress();
		this.phone = placeReceipt.getPhone();
		this.email = placeReceipt.getEmail();
		this.note = placeReceipt.getNote();
		this.order = order;
	}
}
