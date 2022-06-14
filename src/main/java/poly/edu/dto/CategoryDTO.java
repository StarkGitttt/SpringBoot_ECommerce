package poly.edu.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	@NotBlank(message =  "Vui lòng nhập trường này")
	private String id;
	
	@NotBlank(message =  "Vui lòng nhập trường này")
	private String name;

}
