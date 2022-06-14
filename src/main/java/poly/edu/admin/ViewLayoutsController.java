package poly.edu.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ViewLayoutsController {

	@GetMapping("/statistics/chartcircle")
	public String statisChartCircle() {
		return "admin/statistics/statistics-chartcircle";
	}
	
	@GetMapping("/notification")
	public String notification() {
		return "admin/main/notification";
	}
	

}
