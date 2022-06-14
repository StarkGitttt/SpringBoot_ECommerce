package poly.edu.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.dto.CartDTO;
import poly.edu.models.Account;
import poly.edu.models.Order;
import poly.edu.models.OrderDetail;
import poly.edu.models.PlaceReceipt;
import poly.edu.models.Product;
import poly.edu.repositories.OrderDAO;
import poly.edu.repositories.OrderDetailDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.services.SessionService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	@Autowired
	ProductDAO repoProduct;
	
	@Autowired
	SessionService session;
	
	@Autowired
	OrderDAO repoOrder;
	
	@Autowired
	OrderDetailDAO repoOrderDetail;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping
	public String view(RedirectAttributes params) {		
		session.remove("message");
		return "home/cart";
	}
	
	@RequestMapping("/add")
	public String add(Model model, @RequestParam("id") Optional<Integer> id,
			@RequestParam("qty") Optional<Integer> qty) {
		if(id.isPresent()) {
			Product product = repoProduct.findById( id.get()).get();
			if(product != null) {
				// Lưu ý bổ sung thêm phần xử lý khi có giảm giá
				CartDTO cartDto = new CartDTO();
				cartDto.setProduct(product);
				cartDto.getProduct().setPrice(
						product.getPrice() * ((100 - product.getDiscount()) / 100)
						);
				cartDto.setQuantity(qty.orElse(1));
				Map<Integer, CartDTO> mapCart = session.get("cartDtos");
				mapCart.put(product.getId(), cartDto);
				session.set("cartDtos", mapCart);
			}
		}
		return "redirect:/cart";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("qty") List<Integer> qty) {
		Map<Integer, CartDTO> mapCart = session.get("cartDtos");
		Account user = session.get("user");
		
		if(!mapCart.isEmpty() && user != null) {
			List<Integer> list = qty;
			int index = 0;
			for (Map.Entry<Integer, CartDTO> entry : mapCart.entrySet()) {
				CartDTO val = entry.getValue();
				val.setQuantity(list.get(index));
				index++;
			}
			return "redirect:/checkout";
		} 
		return "redirect:/cart";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Map<Integer, CartDTO> mapCart = session.get("cartDtos");
			mapCart.remove(id.get());
			session.set("cartDtos", mapCart);
		}
		return "redirect:/cart";
	}
}
