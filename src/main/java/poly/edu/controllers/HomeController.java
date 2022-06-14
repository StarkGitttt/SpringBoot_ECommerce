package poly.edu.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.edu.dto.CartDTO;
import poly.edu.models.Account;
import poly.edu.models.Category;
import poly.edu.models.Favorite;
import poly.edu.models.Product;
import poly.edu.models.Report;
import poly.edu.repositories.CategoryDAO;
import poly.edu.repositories.FavoriteDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.repositories.ReportDAO;
import poly.edu.services.SessionService;

@Controller
public class HomeController {
	
	@Autowired
	SessionService session;

	@Autowired
	CategoryDAO repoCategory;
	
	@Autowired
	ProductDAO repoProduct;
	
	@Autowired
	FavoriteDAO repoFavorite;
	
	@Autowired
	ReportDAO repoReport;
	
	@GetMapping("test")
	public String test() {
		// Spring sẽ tìm kiếm dưới dạng contain
		/*
		 	VD: quy định đường dẫn của thuộc tính name trong tiles.xml là admin1/*
		 	Khi action method trả về "admin/index" vẫn thỏa mãn điều kiệu
		 * */
		return "admin/index";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		/* Tìm kiếm các danh mục tồn tại sản phẩm */
		List<Category> categories = repoCategory.findCategoryExistProduct();
		session.set("categories", categories);
		
		/* Sản phẩm mới ra mắt */
		Pageable page = PageRequest.of(0, 10);
		Page<Product> getNewProducts = repoProduct.getNewProducts(page);
		model.addAttribute("newProducts", getNewProducts.getContent());
		
		/* Sản phẩm nổi bật*/
		List<Report> reports = repoReport.findSellingProductsNoSort();
		model.addAttribute("reports", reports);
		
		
		/* Khởi tạo giỏ hàng */
		if(session.get("cartDtos") == null) {
			Map<Integer, CartDTO> mapCart = new HashMap<>();
			session.set("cartDtos", mapCart);
		}
		/* Tìm kiếm sản phẩm yêu thích của user */
		Account account = session.get("user");
		if(account != null) {
			List<Favorite> favorities =repoFavorite.findByUserId(account.getUsername());
			model.addAttribute("favorites", favorities);
		}

		return "home/index";
	}
	
}
