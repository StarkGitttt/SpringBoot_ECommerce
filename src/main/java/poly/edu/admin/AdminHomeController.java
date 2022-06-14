package poly.edu.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.models.Product;
import poly.edu.models.Report;
import poly.edu.repositories.ProductDAO;
import poly.edu.repositories.ReportDAO;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

	@Autowired
	ProductDAO repoProduct;
	
	@Autowired
	ReportDAO repoReport;
		
	@GetMapping("/outstanding/products")
	public String bestSellingProduct(Model model,
			@RequestParam("pageNo") Optional<Integer> pageNo) {
		Pageable pageable = PageRequest.of(pageNo.orElse(0), 9);
		Page<Report> page = repoReport.findSellingProducts(pageable);
		model.addAttribute("products", page.getContent() );
		return "admin/outstanding/outstanding-products";
	}
	
	@GetMapping("/outstanding/detail/product")
	public String detailBestSellingProduct (Model model,
			@RequestParam("id") Optional<Integer> id){
		if(id.isPresent()) {
			Optional<Product> product = repoProduct.findById(id.get());
			if(product.isPresent()) {
				model.addAttribute("product", product.get());
			}
		}
		return "admin/outstanding/outstanding-detail-product";
	}
	
}
