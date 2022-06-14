package poly.edu.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "Username")
	@NotBlank
	private String username; 
	
	@Column(name = "Fullname")
	@NotBlank
	private String fullname; 
	
	@Column(name = "Password")
	@NotBlank
	private String password; 
	
	@Column(name = "Email")
	@NotBlank
	@Email
	private String email; 
	
	@Column(name = "Photo")
	private String photo; 

	@Column(name = "Phone")
	@NotBlank
	private String phone; 

	@Column(name = "Active")
	private boolean activate;  
	
	@Column(name = "Admin")
	private boolean admin; 
	
	@OneToMany(mappedBy = "account")
	private List<Order> orders;

	@OneToMany(mappedBy = "account")
	private List<Favorite> favorites;
	
	@OneToMany(mappedBy = "account")
	private List<Shipping> shippings;
	
	@OneToMany(mappedBy = "account")
	private List<Visitor> visitors;
}
