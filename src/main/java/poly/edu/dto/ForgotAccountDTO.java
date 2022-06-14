package poly.edu.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ForgotAccountDTO {
	
	@NotBlank(message = "Vui lòng nhập trường này")
	private String username;

	private String code;
	
}
