package poly.edu.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.dto.CategoryDTO;

@NamedQueries({
	
	@NamedQuery(			
			name = "Category.findCategoryExistProduct",
			query = "SELECT c FROM Category c WHERE c.products IS NOT EMPTY"
			)
	
})

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CategoryId")
	@NotBlank(message = "Vui lòng nhập trường này")
	private String categoryId;
	
	@Column(name = "Name")
	@NotBlank(message = "Vui lòng nhập trường này")
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
	public Category(CategoryDTO categoryDTO) {
		this.categoryId = categoryDTO.getId();
		this.name = categoryDTO.getName();
	}
}
