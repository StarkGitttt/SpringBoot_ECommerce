package poly.edu.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import poly.edu.models.Account;
import poly.edu.models.Category;
import poly.edu.models.Favorite;
import poly.edu.models.Product;
import poly.edu.repositories.AccountDAO;
import poly.edu.repositories.CategoryDAO;
import poly.edu.repositories.FavoriteDAO;
import poly.edu.repositories.OrderDetailDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.services.SessionService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryDAO repoCategory;
	
	@Autowired
	ProductDAO  repoProduct;
	
	@Autowired
	FavoriteDAO repoFavorite;
	
	@Autowired
	SessionService session;
	
	@Autowired
	OrderDetailDAO repoTestOD;
	
	@Autowired
	AccountDAO repoTestAcc;
	
	@GetMapping
	public String selectProductByCategoryId(@RequestParam("id") Optional<String> id, 
			@RequestParam("pageNo") Optional<Integer> pageNo, Model model) {
		// Tìm kiếm sản phẩm theo mã danh mục (categoryId)
		if(id.isPresent()) {
			Pageable pageable = PageRequest.of(pageNo.orElse(0), 8);
			Page<Product> page = repoProduct.findProductByCategoryId(id.get(), pageable);		
			model.addAttribute("products", page.getContent());
			model.addAttribute("page", page);
			model.addAttribute("category", repoCategory.findById(id.get()).get());
			// Lưu idCategory vào trong session dùng cho trường hợp Like sẽ load lại trang hiện tại
			session.set("idCategory", id.get());
			// Lưu pageNo vào trong session dùng cho trường hợp Like sẽ load lại trang đã truy xuất tới
			session.set("page", page);
		}
		// Tìm kiếm sản phẩm yêu thích của User
		Account account = session.get("user");
		if(account != null) {
			List<Favorite> favorities =repoFavorite.findByUserId(account.getUsername());
			model.addAttribute("favorites", favorities);
		}
		
		return "home/list-product";
	}
	
	@GetMapping("/test")
	public String selectProductByCategoryId() {
		/* Tìm kiếm sản phẩm yêu thích của user và nhóm theo danh mục */
		Account user = session.get("user");
		Map<Category, List<Product>> map = new HashMap<>();
		List<Category> categories = repoFavorite.findCategoryByUserId(user.getUsername());
		for (Category category : categories) {
			List<Product> list = repoFavorite.findProductByCIAndUserId(user.getUsername(), category);
			map.put(category, list);
		}
		for (Map.Entry<Category, List<Product>> entry : map.entrySet()) {
			Category key = entry.getKey();
			List<Product> val = entry.getValue();
			System.out.println("Category name: " + key.getName());
			for (Product product : val) {
				System.out.println(product.getName());
			}
	
		}
		return "redirect:/index";
	}
//	@GetMapping
//	public String selectProductByCategoryId(@RequestParam("id") Optional<String> id, Model model) {
//		// Tìm kiếm sản phẩm theo mã danh mục (categoryId)
//		if(id.isPresent()) {
//			List<Product> products = repoProduct.findProductByCategoryId(id.get());			
//			model.addAttribute("products", products);
//		}
//		// Liệt kê các danh mục có tồn tại sản phẩm ở trong
//		List<Category> categories = repoCategory.findCategoryExistProduct();
//		model.addAttribute("categories", categories);
	
//		#spring.jpa.database-platform=org.hibernate.dialect.SQLServer2014Dialect
	
//		// Tìm kiểm sản phẩm
//		List<Category> categoriess = repoProduct.findCategoryByProductName("%"+"0"+"%");
//		Map<Category, List<Product>> map = new HashMap<>();
//		for (Category category : categoriess) {
//			List<Product> products = repoProduct.findProductByCIAndPN(category.getCategoryId(), "%" + "a" +"%");
//			map.put(category, products);
//		}
//		for (Map.Entry<Category, List<Product>> entry : map.entrySet()) {
//			Category key = entry.getKey();
//			List<Product> vals = entry.getValue();
//			System.out.println(key.getName() +": ");
//			for (Product val : vals) {
//				System.out.println(val.getName());
//			}
//		}
//		// Liệt kê số lượt thích của từng sản phẩm
//		List<Report> countLikes = repoProduct.countLikeOfProduct();
//
//		
//		return "redirect:/index";
//	}
}
