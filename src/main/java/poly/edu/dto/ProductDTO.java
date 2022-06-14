package poly.edu.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.models.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductDTO {
	
	@NotBlank(message = "Vui lòng nhập trường này")
	private String name;
	
	@NotBlank(message = "Vui lòng nhập trường này")
	private String linkImage;
	
	@DecimalMin(value = "1000", message =  "Vui lòng nhập giá lớn hơn 1000 VNĐ")	
	private float price;

	private float discount;
	
	private Date createDate = new Date();
	

	@DecimalMin(value = "1" , message =  "Vui lòng nhập số lượng lớn hơn 1" )
	private int amount;
	
	private boolean available = true;

	private Category category;
}
