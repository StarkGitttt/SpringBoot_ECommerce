package poly.edu.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.edu.models.Account;
import poly.edu.models.Category;
import poly.edu.models.Favorite;
import poly.edu.models.Product;
import poly.edu.repositories.FavoriteDAO;
import poly.edu.services.SessionService;

@Controller
public class FavoriteController {
	
	@Autowired
	SessionService session;
	
	@Autowired
	FavoriteDAO repoFavorite;
	
	
	@GetMapping("/favorite")
	public String myFavorite(Model model) {
		Account user = session.get("user");
		if(user != null) {
			/* Tìm kiếm sản phẩm yêu thích của user */
			List<Favorite> favorities =repoFavorite.findByUserId(user.getUsername());
			model.addAttribute("favorites", favorities);
			
			Map<Category, List<Product>> maps = new HashMap<>();
			List<Category> categories = repoFavorite.findCategoryByUserId(user.getUsername());
			for (Category category : categories) {
				List<Product> list = repoFavorite.findProductByCIAndUserId(user.getUsername(), category);
				maps.put(category, list);
			}			
			model.addAttribute("mapProducts", maps);
			return "home/my-favorite";
		} else {
			return "home/index";
		}
	}
}
