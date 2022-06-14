package poly.edu.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.repositories.OrderDAO;
import poly.edu.repositories.ReportDAO;
import poly.edu.repositories.VisitorDAO;

@Controller
@RequestMapping("/admin")
public class AdminStatisticsController {

	@Autowired
	VisitorDAO repoVisitor;
	
	@Autowired
	OrderDAO repoOrder;
	
	@Autowired
	ReportDAO repoReport;
	
	@GetMapping("/index")
	public String index(Model model) {
		Long totalProductsSellToday = repoReport.totalProductsSellToday(new Date());
		Long countUserUseSystem = repoReport.countUserUseSystem();
		Long countVisitorToday = repoReport.countVisitorToday(new Date());
		Double totalRevenueToday = repoReport.totalRevenueToday(new Date());
		model.addAttribute("totalProductsSellToday", totalProductsSellToday);
		model.addAttribute("countUserUseSystem", countUserUseSystem);
		model.addAttribute("countVisitorToday",  countVisitorToday);
		model.addAttribute("totalRevenueToday", totalRevenueToday);
		return "admin/main/index";
	}
	@GetMapping("/view/detail/productselltoday")
	public String detailProductSellToday(Model model) {
		model.addAttribute("products", repoReport.detailTotalProductsSellToday(new Date()));
		return "admin/statistics/detail-sell-today";
	}
	@GetMapping("/view/visitor/today")
	public String viewVisitorToday(Model model) {
		model.addAttribute("visitors", repoVisitor.viewVisitorToday(new Date()));
		return "admin/statistics/view-visitor-today";
	}
	
	@GetMapping("/view/detail/revenue/today")
	public String viewRevenueToday(Model model) {
		model.addAttribute("orders", repoOrder.detailRevenueToday(new Date()));
		return "admin/statistics/view-revenue-today";
	}
	
	
}
