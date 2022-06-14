package poly.edu.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.models.Order;
import poly.edu.models.OrderDetail;
import poly.edu.models.Product;
import poly.edu.repositories.OrderDAO;
import poly.edu.repositories.ProductDAO;

@Controller
@RequestMapping("/admin")
public class OrderController {
	
	@Autowired
	OrderDAO repoOrder;
	
	@Autowired
	ProductDAO repoProduct;
	
	@GetMapping("/order")
	public String order(Model model) {
		List<Order> orders = repoOrder.findUnapprovedOrder();
		model.addAttribute("orders", orders);
		return "home/order-admin";
	}

	
}
