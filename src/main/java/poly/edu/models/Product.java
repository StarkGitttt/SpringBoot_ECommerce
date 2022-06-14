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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import poly.edu.dto.ProductDTO;

@NamedQueries({
	
	@NamedQuery(
			name = "Product.findProductByCategoryId",
			query = "SELECT p FROM Product p WHERE p.category.categoryId = :cid AND p.available = true"
			),
	@NamedQuery(
			name = "Category.findCategoryByProductName",
			query = "SELECT DISTINCT p.category FROM Product p WHERE p.name LIKE ?1 AND p.available = true"
			),
	@NamedQuery(
			name = "Product.findProductByCIAndPN",
			query = "SELECT p FROM Product p WHERE p.category.categoryId = ?1 AND "
					+ "p.name LIKE ?2 AND p.available = true"
			),
	@NamedQuery(
			name = "Product.suggestProduct",
			query = "SELECT p FROM Product p WHERE p.category.categoryId = ?1 "
					+ "AND p.id NOT LIKE ?2 AND  p.available = true "
			),
	@NamedQuery(
				name = "Product.getNewProducts",
				query = "SELECT p FROM Product p WHERE p.available = true ORDER BY p.createDate DESC"
			),
	@NamedQuery(
			name = "Product.findCategoryByProductsSale",
			query = "SELECT p.category FROM Product p WHERE p.discount > 0 AND p.available = TRUE"
			),
	@NamedQuery(
			name = "Product.findProductsSale",
			query = "SELECT p FROM Product p WHERE p.discount > 0 AND p.available = TRUE AND p.category.categoryId = ?1"
			),
	@NamedQuery(
			name = "Product.findCategoryInProducts",
			query = "SELECT DISTINCT p.category FROM Product p WHERE p.available = TRUE"),
	@NamedQuery(
			name = "Product.findProductsInCategory",
			query = "SELECT p FROM Product p WHERE p.available = TRUE AND p.category = ?1"
			)
	
})

@ToString
@Data
@Entity
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ProductId")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Image")
	private String image;
	
	@Column(name="Price")
	private float price;
	
	@Column(name = "Discount")
	private float discount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CreateDate")
	private Date createDate;
	
	@Column(name="Quantity")
	private  int quantity;
	
	@Column(name="Available")
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "product")
	private List<Size> sizes;

	@OneToMany(mappedBy = "product")
	private List<Favorite> favorites;
	
	public Product(ProductDTO productDTO) {
		this.name = productDTO.getName();
		this.image = productDTO.getLinkImage();
		this.price = productDTO.getPrice();
		this.discount = productDTO.getDiscount();
		this.quantity = productDTO.getAmount();
		this.createDate = productDTO.getCreateDate();
		this.available = productDTO.isAvailable();
		this.category = productDTO.getCategory();
	}
	
}
