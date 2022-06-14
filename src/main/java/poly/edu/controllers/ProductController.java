package poly.edu.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.models.Account;
import poly.edu.models.Category;
import poly.edu.models.Favorite;
import poly.edu.models.Product;
import poly.edu.models.Report;
import poly.edu.models.Size;
import poly.edu.repositories.FavoriteDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.repositories.ReportDAO;
import poly.edu.repositories.SizeDAO;
import poly.edu.services.SessionService;

@Controller
public class ProductController {
    
    @Autowired
    SessionService session;

    @Autowired
    ProductDAO repoProduct;

    @Autowired
    FavoriteDAO repoFavorite;
    
    @Autowired
    ReportDAO repoReport;

    @Autowired
    SizeDAO repoSize;
    
    @GetMapping("/product/all")
    public String viewProductsAll(Model model) {
    	Map<Category, List<Product>> mapProduct = new HashMap<>();
    	List<Category> categories = repoProduct.findCategoryInProducts();
    	for (Category category : categories) {
			List<Product> products = repoProduct.findProductsInCategory(category);
			mapProduct.put(category, products);
		}
    	model.addAttribute("mapProduct", mapProduct);
    	return "home/product-all";
    }
    
    
    @RequestMapping("/product/search")
    public String search(Model model, @RequestParam("keyword") Optional<String> keyword) {
  
    		/* Tìm kiếm sản phẩm yêu thích của user */
    		Account account = session.get("user");
    		if(account != null) {
    			List<Favorite> favorities =repoFavorite.findByUserId(account.getUsername());
    			model.addAttribute("favorites", favorities);
    		}
    		// Liệt kê số lượt thích của từng sản phẩm
    		List<Report> countLikes = repoProduct.countLikeOfProduct();
    		model.addAttribute("productsLike", countLikes);
    		// Tìm kiểm sản phẩm
    		List<Category> categoriess = repoProduct.findCategoryByProductName("%"+keyword.orElse("")+"%");
    		Map<Category, List<Product>> map = new HashMap<>();
    		for (Category category : categoriess) {
    			List<Product> products = repoProduct.findProductByCIAndPN(category.getCategoryId(), "%" + keyword.orElse("") +"%");
    			map.put(category, products);
    		}
    		model.addAttribute("mapProduct", map);
    		/* Dùng khi thích sẽ load lại page này */
    		session.set("keyword", keyword.orElse(""));
    		
    		return "home/search-product";
    	
    }
    
    @GetMapping("/product/like")
    public String like(RedirectAttributes params, @RequestParam("id") Optional<Integer> id,
    		@RequestParam("page") Optional<String> currentPage) {
    	if(id.isPresent()) {
    		Account user = session.get("user");
    		Favorite favorite = repoFavorite.findByUserIdAndProductId(user.getUsername(), id.get());
    		if(favorite != null) {
    			// Trường hợp đã thích sản phẩm tức có tồn tại trong Favorite
    			if(favorite.getIsLike()) {
    				favorite.setIsLike(false);    				
    			} else {
    				favorite.setIsLike(true);   
    			}
    			repoFavorite.save(favorite);
    		} else {
    			// Trường hợp chưa thích
    			Product product = repoProduct.findById(id.get()).get();
    			repoFavorite.save(new Favorite(user, product, new Date(), true));
    		}
    	}
    	// Khi user Like  ở trang Index
    	if(currentPage.isPresent()) {
    		if(currentPage.get().equalsIgnoreCase("index")) {
    			return "redirect:/index";    			
    		} else if(currentPage.get().equalsIgnoreCase("search")) {
    			params.addAttribute("keyword", session.get("keyword"));
    			return "redirect:/product/search";
    		} else if(currentPage.get().equalsIgnoreCase("favorite")) {
    			return "redirect:/favorite";
    		} else if(currentPage.get().equalsIgnoreCase("sale")) {
    			return "redirect:/product/sales";
    		}
    	}
    	// Khi user Like ở trang Category
    	String idCategory = session.get("idCategory");
    	if(idCategory != null) {
    		params.addAttribute("id", idCategory);		
    	}
    	Page<Product> page = session.get("page");
    	if(page != null) {
    		params.addAttribute("pageNo", page.getNumber());
    	}
    	return "redirect:/category";
    }
    
    @GetMapping("/product/details")
    public String details(Model model, @RequestParam("id") Optional<Integer> id) {
    	Optional<Product> product = repoProduct.findById(id.get());
    	if(product.isPresent()) {
    		Product p = product.get();
    		model.addAttribute("product", p);
    		// Get Size sản phẩm
    		List<Size> sizes = repoSize.findByIdProduct(id.get());
    		model.addAttribute("sizes", sizes);
    		// Gợi ý 4 sản phẩm cùng loại
    		List<Product> products = repoProduct.getSuggestProduct(p.getCategory().getCategoryId(), id.get());
    		List<Product> tempProducts = new ArrayList<>();
    		int count = 0;
    		for (Product product2 : products) {
				tempProducts.add(product2);
				count++;
				if(count == 4) {
					break;
				}
			}
    		model.addAttribute("products", tempProducts);
    	} else {
    		return "redirect:/index";
    	}
    	return "home/detail-product";
    }
    
    @GetMapping("/product/sales")
    public String sale(Model model) {
    	/* Tìm kiếm sản phẩm yêu thích của user */
		Account account = session.get("user");
		if(account != null) {
			List<Favorite> favorities =repoFavorite.findByUserId(account.getUsername());
			model.addAttribute("favorites", favorities);
		}
		
    	List<Category> categories = repoProduct.findCategoryByProductsSale();
    	Map<Category, List<Product>> mapProducts = new HashMap<>();
    	for (Category category : categories) {
			List<Product> products = repoProduct.findProductsSale(category.getCategoryId());
			mapProducts.put(category, products);
		}
    	model.addAttribute("mapProduct", mapProducts);
    	return "home/product-sale";
    }
    @GetMapping("/product/review")
    public String review(Model model, @RequestParam("id") Optional<Integer> id) {
    	if(id.isPresent()) {
    		Product p = repoProduct.findById(id.get()).get();
    		if( p != null ) {
    			
    		}
    	}
    	return "home/index";
    }
    @GetMapping("/product/selling")
    public String viewSellingProducts(Model model, 
    		@RequestParam("pageNo") Optional<Integer> pageNo) {
    	Pageable pageable = PageRequest.of(pageNo.orElse(0), 6);
		Page<Report> page = repoReport.findSellingProducts(pageable);
		model.addAttribute("products", page.getContent() );
		model.addAttribute("page", page);
    	return "home/best-selling-products";
    }
}
