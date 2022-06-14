package poly.edu.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.models.Order;
import poly.edu.models.OrderDetail;
import poly.edu.models.PlaceReceipt;
import poly.edu.models.Product;
import poly.edu.repositories.OrderDAO;
import poly.edu.repositories.PlaceReceiptDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.services.MailService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {
	
	@Autowired
	OrderDAO repoOrder;
	
	@Autowired
	PlaceReceiptDAO repoPlaceReceipt;
	
	@Autowired
	MailService mailer;
	
	@Autowired
	ProductDAO repoProduct;
	
	@GetMapping("/manage/order")
	public String orderWaiting(Model model, @RequestParam("status") Optional<String> status) {
		Sort sort = Sort.by(Direction.DESC, "ceateDate");
		if(status.isPresent()) {
			if(status.get().equalsIgnoreCase("waiting")) {
				
				model.addAttribute("orders", repoOrder.findByStatusEqualsAndAvailableEquals(false, true, sort));
				return "admin/order/order-waiting";				
				
			} else if(status.get().equalsIgnoreCase("confirmed")) {
				model.addAttribute("orders", repoOrder.findByStatusEqualsAndAvailableEquals(true, true, sort));
				return "admin/order/order-confirmed";		
				
			} else if(status.get().equalsIgnoreCase("removed")) {
				model.addAttribute("orders", repoOrder.findByStatusEqualsAndAvailableEquals(false, false, sort));
				return "admin/order/order-removed";
			}
		}
		return "redirect:/admin/index";
	}
	@GetMapping("/manage/view/address/receipt")
	public String viewAddressReceipt(Model model, 
			@RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				PlaceReceipt placeReceipt = repoPlaceReceipt.findByOrder(order.get());
				model.addAttribute(placeReceipt);
			}
		}
		return "admin/order/address-receipt";
	}
	@GetMapping("/manage/view/details/order")
	public String viewDetailsOrderWaiting(Model model,
			@RequestParam("id") Optional<Integer> id, 
			@RequestParam("status") Optional<String> status) {
		
		if(id.isPresent() && status.isPresent()) {
			
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				model.addAttribute("order", order.get());
			}
			if(status.get().equalsIgnoreCase("waiting")) {
				return "admin/order/order-details-waiting";			
	
			} else if(status.get().equalsIgnoreCase("confirmed")) {
				return "admin/order/order-details-confirmed";		
				
			} else if(status.get().equalsIgnoreCase("removed")) {
				return "admin/order/order-details-removed";		
				
			}
		}
		return "redirect:/admin/index";
	}
	
	@GetMapping("/manage/order/approval")
	public String orderApproval(Model model, @RequestParam("id") Optional<Integer> id) throws MessagingException {
		if(id.isPresent()) {
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				order.get().setStatus(true);
				order.get().setApprovalDate(new Date());
				repoOrder.save(order.get());
				// Gửi mail xác nhận đang giao hàng
				PlaceReceipt placeReceipt = repoPlaceReceipt.findByOrder(order.get());
				mailer.push(placeReceipt.getEmail(), "Thông tin đơn hàng", "Đơn hàng có mã:  " + order.get().getId() + " đã được chuyển tới đơn vị vận chuyển, " + "dự kiến giao hàng trong vòng 1 tuần\n" + "Routine cảm ơn sự tin tưởng của bạn" );
				// Cập nhập số lượng lại cho sản phẩm
				List<OrderDetail> orderDetails = order.get().getOrderDetails();
				for (OrderDetail orderDetail : orderDetails) {
					Product product = repoProduct.findById(orderDetail.getProduct().getId()).get();
					product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
					repoProduct.save(product);
				}
				Sort sort = Sort.by(Direction.DESC, "ceateDate");
				model.addAttribute("orders", repoOrder.findByStatusEqualsAndAvailableEquals(false, true, sort));
			}
		}
		return "admin/order/order-waiting";
	}
	
	@GetMapping("/manage/order/delete")
	public String orderRemoved(Model model, @RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Order> order = repoOrder.findById(id.get());
			if(order.isPresent()) {
				order.get().setAvailable(false);
				repoOrder.save(order.get());
				Sort sort = Sort.by(Direction.DESC, "ceateDate");
				model.addAttribute("orders", repoOrder.findByStatusEqualsAndAvailableEquals(false, true, sort));
			}
		}
		return "admin/order/order-waiting";
	}
}
