package poly.edu.admin;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.models.Account;
import poly.edu.repositories.AccountDAO;
import poly.edu.services.MailService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

		@Autowired
		AccountDAO repoUser;
		
		@Autowired
		MailService mailer;
		
		@GetMapping("/employee/views")
		public String employeeViews(Model model, 
				@RequestParam("pageNo") Optional<Integer> pageNo) {
			Pageable pageable = PageRequest.of(pageNo.orElse(0), 6);
			Page<Account> page = repoUser.findByAdminEquals(true, pageable);
			model.addAttribute("users", page.getContent());
			model.addAttribute("page", page);
			return "admin/employee/employee-views";
		}
		
		@GetMapping("/view/detail/employee")
		public String viewEmployeeDetail(Model model,
				@RequestParam("id") Optional<String> id) {
			if(id.isPresent()) {
				Optional<Account> user = repoUser.findById(id.get());
				if(user.isPresent()) {
					model.addAttribute("user", user.get());
				}
			}
			return "admin/employee/employee-view-detail";
		}
		
		@PostMapping("/employee/sendmail")
		public String sendMailForEmp(RedirectAttributes params,
				@RequestParam("emailTo") String emailTo,
				@RequestParam("content") String content) throws MessagingException {
			mailer.push(emailTo.trim(), "Nhân sự", content);
			params.addAttribute("message", "Emaill has been sent");
			return "redirect:/admin/employee/views";
		}
	
}
