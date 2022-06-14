package poly.edu.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.models.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartDTO {
	
	private Product product;

	private  int quantity;


}
