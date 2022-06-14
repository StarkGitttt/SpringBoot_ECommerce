package poly.edu.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.dto.ProductDTO;
import poly.edu.models.Category;
import poly.edu.models.Favorite;
import poly.edu.models.Product;
import poly.edu.repositories.CategoryDAO;
import poly.edu.repositories.ProductDAO;
import poly.edu.services.UploadService;

@Controller
@RequestMapping("/admin")
public class AdminProductController {
	
	@Autowired
	CategoryDAO repoCategory;
	
	@Autowired
	ProductDAO repoProduct;
	
	@Autowired
	UploadService upload;
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return repoCategory.findAll();
	}
	
	@GetMapping("/manage/product/add")
	public String productAdd(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", repoCategory.findAll());
		return "admin/product/product-add";
	}
	
	@PostMapping("/manage/product/add")
	public String productAdd(Model model, 
			@Valid @ModelAttribute("productDTO") ProductDTO productDTO, 
			BindingResult result, @RequestParam("attach") MultipartFile file,
			@RequestParam("categoryId") Optional<String> idCategory) {
		if(!result.hasErrors()) {
			/* Kiểm tra đã tồn tại tên sản phẩm hay chưa */
			Product product = repoProduct.findByNameEquals(productDTO.getName());
			if(product == null) {
					if(idCategory.isPresent()) {
						Optional<Category> category = repoCategory.findById(idCategory.get());
						if(category.isPresent()) {
							productDTO.setCategory(category.get());
							repoProduct.save(new Product(productDTO));
							model.addAttribute("saveSuccess","Thêm mới sản phẩm thành công");
						}
					}
					if(!file.isEmpty()) {
						upload.saveFile(file, "images-product/");
					}				
			} else {
				model.addAttribute("existProduct","Đã tồn tại sản phẩm với tên này");
			}
		} 
		return "admin/product/product-add";
	}
	
	@GetMapping("/manage/product/views")
	public String productViews(Model model) {
		List<Product> products = repoProduct.findByAvailableEquals(true);
		model.addAttribute("products", products);
		return "admin/product/product-views";
	}
	
	@GetMapping("/manage/product/update")
	public String productUpdate(Model model, 
			@RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Product> product = repoProduct.findById(id.get());
			if(product.isPresent()) {
				model.addAttribute(product.get());
			}
		}
		return "admin/product/product-update";
	}
	
	@PostMapping("/manage/product/update")
	public String productUpdate(Model model, Product p,
			@RequestParam("id") Optional<Integer> id,
			@RequestParam("attach") MultipartFile file) {
		if(id.isPresent()) {
			Optional<Product> product = repoProduct.findById(id.get());
			if(product.isPresent()) {
				p.setId(product.get().getId());
				p.setAvailable(product.get().isAvailable());
				p.setCategory(product.get().getCategory());
				p.setCreateDate(product.get().getCreateDate());
				p.setQuantity(p.getQuantity() + product.get().getQuantity());
				model.addAttribute(repoProduct.save(p));
				model.addAttribute("saveSuccess","Cập nhập sản phẩm thành công");
			}
		}
		return "admin/product/product-update";
	}
	
	@GetMapping("/manage/product/delete")
	public String productRemoved(Model model,
			@RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Product> product = repoProduct.findById(id.get());
			if(product.isPresent()) {
				product.get().setAvailable(false);
				repoProduct.save(product.get());
				List<Product> products = repoProduct.findByAvailableEquals(true);
				model.addAttribute("products", products);
			}
		}
		return "admin/product/product-views";
	}
	
	@GetMapping("/manage/views/product/deleted")
	public String viewsProductDeleted(Model model) {
		List<Product> products = repoProduct.findByAvailableEquals(false);
		model.addAttribute("products", products);
		return "admin/product/product-removed";
	}
	
	@GetMapping("/manage/product/restore")
	public String restoreProduct(Model model,
			@RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Product> product = repoProduct.findById(id.get());
			if(product.isPresent()) {
				product.get().setAvailable(true);
				repoProduct.save(product.get());
				List<Product> products = repoProduct.findByAvailableEquals(false);
				model.addAttribute("products", products);
			}
		}
		return "admin/product/product-removed";
	}
	
	@GetMapping("/manage/product/favorite")
	public String productFavorite(Model model) {
		model.addAttribute("products", repoProduct.findAll());
		return "admin/product/product-favorite";
	}
	
	@GetMapping("/manage/view/product/favorite")
	public String viewProductFavorite(Model model, @RequestParam("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<Product> product = repoProduct.findById(id.get());
			if(product.isPresent()) {
				List<Favorite> favorites = product.get().getFavorites();
				model.addAttribute("favorites", favorites);				
			}
		}
		return "admin/product/product-detail-favorite";
	}
	
}
