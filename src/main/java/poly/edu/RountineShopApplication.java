package poly.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RountineShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(RountineShopApplication.class, args);
	}

}
