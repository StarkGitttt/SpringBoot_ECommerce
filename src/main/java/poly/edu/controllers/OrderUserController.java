package poly.edu.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.dto.CartDTO;
import poly.edu.models.Account;
import poly.edu.models.Order;
import poly.edu.models.OrderDetail;
import poly.edu.models.PlaceReceipt;
import poly.edu.models.Product;
import poly.edu.repositories.OrderDAO;
import poly.edu.repositories.OrderDetailDAO;
import poly.edu.repositories.PlaceReceiptDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.services.SessionService;

@Controller
public class OrderUserController {
	
	@Autowired
	OrderDAO repoOrder;
	
	@Autowired
	OrderDetailDAO repoOrderDetail;
	
	@Autowired
	ProductDAO repoProduct;
	
	@Autowired
	PlaceReceiptDAO repoPlaceReceipt;
	
	@Autowired
	SessionService session;
	
	@GetMapping("/order/user")
	public String viewOrders(Model model) {
		Account user = session.get("user");
		if(user != null ) {
			List<Order> orders = repoOrder.findByUserId(user.getUsername());
			model.addAttribute("orders", orders);
		}
		return "home/order-user";
	}
	
	@GetMapping("order/details")
	public String viewOrder(@RequestParam("id") Optional<Integer> id, Model model) {
		Account user = session.get("user");
		if(id.isPresent() && user != null) {
			Order order = repoOrder.findByOIAndUserId(user.getUsername(), id.get());
			if(order == null) {
				return "redirect:/index";
			}
			List<OrderDetail> list = order.getOrderDetails();
			model.addAttribute("orderDetails", list);
		}
		return "home/order-detail";
	}
	@GetMapping("order/again")
	public String againOrder(@RequestParam("id") Optional<Integer> id) {
		
		Account user = session.get("user");
		
		if(id.isPresent() && user != null) {
			/* Lấy giỏ hàng  */
			Map<Integer, CartDTO> mapCart = session.get("cartDtos");
			/* Lấy hóa đơn đã đặt */
			Order order = repoOrder.findByOIAndUserId(user.getUsername(), id.get());
			List<OrderDetail> list = order.getOrderDetails();
			for (OrderDetail orderDetail : list) {
				CartDTO cartDto = new CartDTO();
				cartDto.setProduct(orderDetail.getProduct());
				cartDto.getProduct().setPrice(
						orderDetail.getProduct().getPrice() * ( (100 - orderDetail.getProduct().getDiscount() ) / 100 )
						);
				cartDto.setQuantity(orderDetail.getQuantity());
				/* Lưu vào giỏ hàng */
				mapCart.put(orderDetail.getProduct().getId(), cartDto);
			}
			session.set("cartDtos", mapCart);
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/order/history")
	public String history(Model model) {
		Account user = session.get("user");
		List<Order> orders = repoOrder.findApprovedOrderByUserId(user);
		model.addAttribute("orders", orders);
		return "home/history-order-user";
	}
	
	@GetMapping("/order/cancel")
	public String cancel(Model model) {
		Account user = session.get("user");
		List<Order> orders = repoOrder.findOrderCancel(user);
		model.addAttribute("orders", orders);
		return "home/history-order-cancel";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute(new PlaceReceipt());
		return "home/checkout";
	}
	
	@PostMapping("/checkout")
	public String checkout(Model model, 
			@Valid @ModelAttribute PlaceReceipt placeReceipt,
			BindingResult result) {
		if(!result.hasErrors()) {
			Map<Integer, CartDTO> mapCart = session.get("cartDtos");
			Account user = session.get("user");
			
			if(!mapCart.isEmpty() && user != null) {
				int total = 0;
				for (Map.Entry<Integer, CartDTO> entry : mapCart.entrySet()) {
					CartDTO val = entry.getValue();
					total+=(float)val.getQuantity() * val.getProduct().getPrice();
				}
				// Data cho Order
				Order order = repoOrder.save(new Order(user, new Date(), "Default address", total, false, true, false));
				// Data cho Order Details
				for (Map.Entry<Integer, CartDTO> entry : mapCart.entrySet()) {
					CartDTO val = entry.getValue();
					Product product = repoProduct.findById(entry.getKey()).get();
					// Data cho Order Details
					repoOrderDetail.save( new OrderDetail(order, product, val.getQuantity(), val.getProduct().getPrice()));
				}
				// Data cho PlaceReceipit
				repoPlaceReceipt.save(new PlaceReceipt(placeReceipt, order));
				// Reset giỏ hàng
				Map<Integer, CartDTO> resetCart = new HashMap<>();
				session.set("cartDtos", resetCart);
				model.addAttribute("messageSuccess", "Đặt hàng thành công vui lòng kiểm tra lại đơn hàng!");
			}
		}
		return "home/checkout";
	}
	
	@GetMapping("/order/address/receipt")
	public String addressReceipt(Model model, @RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				PlaceReceipt placeReceipt = repoPlaceReceipt.findByOrder(order.get());
				model.addAttribute(placeReceipt);
			}
		}
		return "home/address-receipt";
	}
	
	@GetMapping("/order/update/address/receipt")
	public String updateAddressReceipt(Model model, @RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				PlaceReceipt placeReceipt = repoPlaceReceipt.findByOrder(order.get());
				model.addAttribute(placeReceipt);
			}
		}
		return "home/update-address-receipt";
	}
	
	@PostMapping("/order/update/address/receipt")
	public String updateAddressReceipt(Model model,
			@RequestParam("id") Optional<Integer> id,
			@Valid @ModelAttribute PlaceReceipt placeReceipt,
			BindingResult result) {
		if(!result.hasErrors() && id.isPresent()) {
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				PlaceReceipt placeReceiptFromDb = repoPlaceReceipt.findByOrder(order.get());
				placeReceiptFromDb.setFullname(placeReceipt.getFullname());
				placeReceiptFromDb.setAddress(placeReceipt.getAddress());
				placeReceiptFromDb.setEmail(placeReceipt.getEmail());
				placeReceiptFromDb.setPhone(placeReceipt.getPhone());
				placeReceiptFromDb.setNote(placeReceipt.getNote());
				model.addAttribute(repoPlaceReceipt.save(placeReceiptFromDb));
				model.addAttribute("messageSuccess", "Cập nhập thông tin nhận hàng thành công!");
			}
			
		}
	

		return "home/update-address-receipt";
	}
	
}
