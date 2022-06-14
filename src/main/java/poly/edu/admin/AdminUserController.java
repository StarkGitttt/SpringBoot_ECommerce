package poly.edu.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.models.Account;
import poly.edu.repositories.AccountDAO;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	
	@Autowired
	AccountDAO repoUser;
	
	@GetMapping("/manage/user/views")
	public String userViews(Model model) {
		model.addAttribute("users", repoUser.findByAdminEqualsAndActivateEquals(false, true));
		return "admin/user/user-views";
	}
	
	@GetMapping("/manage/user/favorite/views")
	public String userFavoriteViews(Model model,
			@RequestParam("id") Optional<String> id) {
		if(id.isPresent()) {
			Optional<Account> user = repoUser.findByUsernameEqualsAndAdminEquals(id.get(), false);
			if(user.isPresent()) {
				model.addAttribute("favorites", user.get().getFavorites());
			}
		}
		return "admin/user/user-detail-favorite";
	}
	
	@GetMapping("/manage/user/delete")
	public String userDelete(Model model,
			@RequestParam("id") Optional<String> id) {
		if(id.isPresent()) {
			Optional<Account> user = repoUser.findByUsernameEqualsAndAdminEquals(id.get(), false);
			if(user.isPresent()) {
				user.get().setActivate(false);
				repoUser.save(user.get());
				model.addAttribute("users", repoUser.findByAdminEqualsAndActivateEquals(false, true));
			}
		}
		return "admin/user/user-views";
	}
	
	@GetMapping("/manage/view/user/disabled")
	public String viewUseDisabled(Model model) {
		model.addAttribute("users", repoUser.findByAdminEqualsAndActivateEquals(false, false));
		return "admin/user/user-disabled";
	}
	
	@GetMapping("/manage/user/enabled")
	public String enabledUser(Model model, 
			@RequestParam("id") Optional<String> id) {
		if(id.isPresent()) {
			Optional<Account> user = repoUser.findByUsernameEqualsAndAdminEquals(id.get(), false);
			if(user.isPresent()) {
				user.get().setActivate(true);
				repoUser.save(user.get());
				model.addAttribute("users", repoUser.findByAdminEqualsAndActivateEquals(false, false));
			}
		}
		return "admin/user/user-disabled";
	}
	
	
	
}
