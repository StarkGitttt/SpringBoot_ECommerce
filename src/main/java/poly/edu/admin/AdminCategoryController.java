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

import poly.edu.dto.CategoryDTO;
import poly.edu.models.Category;
import poly.edu.repositories.CategoryDAO;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {
	
	@Autowired
	CategoryDAO repoCategory;
	
	@GetMapping("/manage/category/add")
	public String categoryAdd(Model model) {
		model.addAttribute(new CategoryDTO( this.generationId(), ""));
		return "admin/category/category-add";
	}
	
	@PostMapping("/manage/category/add")
	public String categoryAdd(Model model,
			@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult result) {
		if(!result.hasErrors()) {
				Optional<Category> category = repoCategory.findByNameEquals(categoryDTO.getName());
				if(category.isPresent()) {
						model.addAttribute("existCategory", "Đã tồn tại tên danh mục này!");
				} else {
					repoCategory.save(new Category(categoryDTO));
					categoryDTO.setId(this.generationId());
					model.addAttribute("successAdd", "Thêm thành công!");
				}
		}
		return "admin/category/category-add";
	}
	
	@GetMapping("/manage/category/views")
	public String categoryViews(Model model) {
		model.addAttribute("categories",  repoCategory.findAll());
		return "admin/category/category-views";
	}
	
	@GetMapping("/manage/view/category")
	public String categoryViewDetail(Model model,
			@RequestParam("id") Optional<String> id) {
		if(id.isPresent()) {
			Optional<Category> category = repoCategory.findById(id.get());
			if(category.isPresent()) {
				model.addAttribute("products", category.get().getProducts());
			}
		}
		return "admin/category/category-view-detail";
	}
	
	@GetMapping("/manage/category/removed")
	public String categoryRemoved() {
		return "admin/category/category-removed";
	}
	public String generationId() {
		List<Category> categories = repoCategory.findAll();
		Category category = categories.get(categories.size() - 1 );
		String idCategory = category.getCategoryId();
		int idSub = Integer.parseInt(idCategory.substring(2));
		String strId = "";
		if(idSub >= 0 && idSub < 99) {
			strId = "CI0" + (idSub + 1);
		} else {
			strId = "CI" + (idSub + 1);
		}
		return strId;
	}
}
