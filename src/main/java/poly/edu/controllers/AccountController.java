package poly.edu.controllers;

import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;
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

import poly.edu.dto.AccountDTO;
import poly.edu.dto.ForgotAccountDTO;
import poly.edu.models.Account;
import poly.edu.models.Visitor;
import poly.edu.repositories.AccountDAO;
import poly.edu.repositories.VisitorDAO;
import poly.edu.services.MailService;
import poly.edu.services.SessionService;
import poly.edu.services.UploadService;

@Controller
public class AccountController {

	@Autowired
	AccountDAO repoUser;

	@Autowired
	VisitorDAO repoVisitor;
	
	@Autowired
	UploadService upload;

	@Autowired
	SessionService session;
	
	@Autowired
	ForgotAccountDTO forgotAccountDTO;
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/login")
	public String login(@ModelAttribute Account account) {
		return "home/form/login";
	}

	@PostMapping("/login")
	public String login(Model model, @Valid @ModelAttribute Account account, BindingResult result) {
		
		Optional<Account> findUser;
		if (result.hasErrors() && result.getErrorCount() != 3) {
			return "home/form/login";
		} else {
			findUser = repoUser.findById(account.getUsername());
			if (findUser.isEmpty() || !findUser.get().getPassword().equals(account.getPassword())) {
				model.addAttribute("notExistAccount", "Tài khoản hoặc mật khẩu không hợp lệ");
				return "home/form/login";
			} else if(findUser.isPresent() && !findUser.get().isActivate()) {
				model.addAttribute("disabledAccount", "Tài khoản của bạn đã bị vô hiệu hóa");
				return "home/form/login";
			}
		}
		// Thực hiện tìm kiếm user
		Account user = findUser.get();
		// Add visitor 
		if(!user.isAdmin()) {
			repoVisitor.save(new Visitor(new Date(), user));			
		}
		
		user.setFullname(user.getFullname().substring(user.getFullname().lastIndexOf(" ") + 1));
		session.set("user", user);
		return "redirect:/index";
	}

	@GetMapping("/signup")
	public String signUp(@ModelAttribute Account account) {
		return "home/signup";
	}

	@PostMapping("/signup")
	public String signUp(Model model, @RequestParam("confirm_pw") String confirm_pw,
			@RequestParam("attach") MultipartFile file, @Valid @ModelAttribute Account account, BindingResult result) {
		if (!result.hasErrors()) {
			// Yêu cầu số điện thoại là 10 số
			boolean isValid = true;
			if (account.getPhone().length() != 10) {
				isValid = false;
				model.addAttribute("errPhone", "Vui lòng nhập 10 chữ số");
			}
			// Tìm kiếm đã tồn tài khoản chưa
			Optional<Account> findAcc = repoUser.findById(account.getUsername());
			if (findAcc.isEmpty()) {
				if (account.getPassword().equals(confirm_pw)) {
					if (!file.isEmpty()) {
						account.setPhoto(file.getOriginalFilename());
						upload.saveFile(file, "avatar/");
					}
					if (isValid) {
						account.setActivate(true);
						repoUser.save(account);
						model.addAttribute("messageSuccess", "Đăng ký thành công");
					}
				} else {
					model.addAttribute("errConfirmPw", "Xác nhận mật khẩu không chính xác");
				}
			} else {
				model.addAttribute("errExistAcc", "Tài khoản đã tồn tại");
			}
		}
		return "home/signup";
	}

	@GetMapping("/shutdown")
	public String shutdown() {
		session.remove("user");
		session.remove("cartDtos");
		return "redirect:/index";
	}

	@GetMapping("/update/info")
	public String editInfo(Model model) {
		Account account = session.get("user");
		model.addAttribute(account);
		return "home/edit-info";
	}

	@PostMapping("/update/info")
	public String editInfo(Model model, @Valid @ModelAttribute Account account, BindingResult result) {

		if (result.hasErrors() && result.getErrorCount() != 1) {
			return "home/edit-info";
		} else {
			Account accSession = session.get("user");
			// So khớp username trong session và username của người dùng nhập vào
			if (accSession.getUsername().equalsIgnoreCase(account.getUsername())) {
				account.setAdmin(accSession.isAdmin());
				account.setPassword(accSession.getPassword());
				account.setPhoto(accSession.getPhoto());
				account.setActivate(accSession.isActivate());
				repoUser.save(account);
				session.set("user", account);
				model.addAttribute("messageSuccess", "Cập nhập thành công");
			} else {
				model.addAttribute("messageErr", "Tài khoản không trùng khớp");
			}
		}
		return "home/edit-info";
	}
	
	@GetMapping("/update/password")
	public String editPassword(Model model) {
		Account user = session.get("user");
		AccountDTO userDTO = new AccountDTO();
		userDTO.setOldPassword(user.getPassword());
		model.addAttribute("accountDTO", userDTO);
		return "home/change-pw";
	}
	@PostMapping("/update/password")
	public String editPassword(Model model, @Valid @ModelAttribute AccountDTO accountDTO, BindingResult result) {
		if(!result.hasErrors()) {
			String newPass = accountDTO.getNewPassword();
			String confirmPass = accountDTO.getConfirmPassword();
			if(newPass.equals(confirmPass)) {
				Account user = session.get("user");
				user.setPassword(confirmPass);
				// Cập nhập lại vào session
				session.set("user", repoUser.save(user));
				// Cập nhập thành công thì xóa giá trị nhập mật khẩu mới
				accountDTO.setNewPassword("");
				model.addAttribute("messageSuccess", "Cập nhập mật khẩu thành công");
			} else {
				model.addAttribute("messageErr", "Vui lòng nhập lại mật khẩu chính xác");
			}
		}
		return "home/change-pw";
	}
	
	@GetMapping("/forgot/password")
	public String forgotPassword(Model model) {
		model.addAttribute("accountDTO", forgotAccountDTO);
		model.addAttribute("isGetCode", false);
		return "home/forgot-password";
	}
	
	@PostMapping("/forgot/password/getcode")
	public String forgotPassword(Model model,
			@Valid @ModelAttribute("accountDTO") ForgotAccountDTO accountDTO,
			BindingResult result) throws MessagingException {
		if(result.hasErrors()) {
				return "home/forgot-password";			
		} else {
			Optional<Account> user = repoUser.findById(accountDTO.getUsername());
			if(user.isPresent()) {
				int code = (int) Math.round((Math.random() * 100000) + 100000);
				session.set("code-verification", code);
				mailService.push("lcphan512@gmail.com", "Lấy lại mật khẩu", "Mã code của bạn: " + code);
				model.addAttribute("message", "Mã đã được gửi tới email đăng ký tài khoản");
				model.addAttribute("isGetCode", true);
				return "home/forgot-password";
			} else {
				model.addAttribute("isGetCode", false);
				model.addAttribute("notExistAccount", "Tài khoản không tồn tại");
				return "home/forgot-password";
			}
		}
	}
	@PostMapping("/fotgot/password/verificode")
	public String verificationCode(Model model,
			@Valid @ModelAttribute("accountDTO") ForgotAccountDTO accountDTO,
			BindingResult result) {
		if(!result.hasErrors()) {
			int code = session.get("code-verification");
			if(code ==Integer.parseInt(accountDTO.getCode())) {
				session.set("accountDTO", accountDTO);
				return "home/update-password";			
			} else {
				model.addAttribute("isGetCode", true);
				model.addAttribute("notMatchCode", "Vui lòng kiểm tra lại mã được nhận");
				return "home/forgot-password";
			}
		} else {
			return "home/forgot-password";			
		}
	}
	
	@RequestMapping("/forgot/password/getpass")
	public String updatePass(Model model, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPass) {
		ForgotAccountDTO accountDTO = session.get("accountDTO");
		if(accountDTO != null) {
			model.addAttribute("accountDTO", accountDTO);
			if(password.equals(confirmPass)) {
				Optional<Account> user = repoUser.findById(accountDTO.getUsername());
				if(user.isPresent()) {
					user.get().setPassword(confirmPass);
					repoUser.save(user.get());
					model.addAttribute("messageSuccess", "Cập nhập mật khẩu thành công");
				}
			} else {
				model.addAttribute("messageErr", "Mập khẩu nhập lại không chính xác");
			}
		}
		return "home/update-password";
	}
 
}
