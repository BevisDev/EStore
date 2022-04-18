package com.pro.controller.user;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.pro.entity.Account;
import com.pro.entity.Order;
import com.pro.inter.AccountService;
import com.pro.inter.OrderService;
import com.pro.mailer.MailerService;
import com.pro.security.UserDetailsImpl;
import com.pro.session.SessionService;
import com.pro.upload.UploadService;

@Controller
@RequestMapping("/user/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	MailerService mailerService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/index")
	public String index(Model model, Authentication auth) {
		//get user
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		
		//List-orders
		List<Order> list = orderService.findByUserName(account.getUsername());
		model.addAttribute("list", list);

		//form edit-profile
		model.addAttribute("form", account);
		return "user/account/index";
	}
	
	@PostMapping("/edit-profile")
	public String editProfile(Model model, Authentication auth,
			@RequestPart("avatar_file") MultipartFile avatar,
			@ModelAttribute("form") Account account) {
		if (!avatar.isEmpty()) {
			File file = uploadService.save(avatar, "/images/account");
			account.setAvatar(file.getName());		
		}
		accountService.update(account);
		model.addAttribute("message", "Update successful");
		//update lai thong tin cua tai khoan login
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		userDetails.setAccount(account);
		return "forward:/user/account/index";
	}
	
	
	@PostMapping("/change-password")
	public String changePassword(Model model, Authentication auth,
			@RequestParam("password") String password,
			@RequestParam("newPass") String newPass,
			@RequestParam("confirm") String confirm) {
		if (!newPass.equals(confirm)) {
			model.addAttribute("message", "confirm password was wrong");
		}
		else {
			UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
			Account account = user.getAccount();
			if (!passwordEncoder.matches(password, account.getPassword()) ) {
				model.addAttribute("message", "Password was wrong");
			}
			else {
				account.setPassword(passwordEncoder.encode(newPass));
				accountService.update(account);
				model.addAttribute("message", "Change password successfully");
			}
		}
		return "forward:/user/account/index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Account account = new Account();
		model.addAttribute("form",account);
		return "user/account/register";
	}
	
	@PostMapping("/register")
	public String register(Model model,
			@ModelAttribute("form") Account account,
			@RequestParam("confirm") String confirm) {
		if (!confirm.equals(account.getPassword())) {
			model.addAttribute("message", "Invalid Confirm Password");
		}
		else if (accountService.existByUserName(account.getUsername())) {
			model.addAttribute("message", "Username was used");
		}
		else {
			String pw = passwordEncoder.encode(account.getPassword());
			account.setPassword(pw);
			accountService.create(account);
			mailerService.sendWellcome(account);
			model.addAttribute("message", "Please Check Email to Activate");
			return "forward:/security/login/form";
		}
		return "user/account/register";
	}
	
	@RequestMapping("/activate")
	public String activate(Model model, @RequestParam("username") String username) {
		Account account = accountService.getByUsername(username);
		account.setActivated(true);
		accountService.update(account);
		model.addAttribute("message", "Activate is successful!");
		return "forward:/security/login/form";
	}
	
	
	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "user/account/forgot-password";
	}
	
	@PostMapping("/forgot-password")
	public String forgotPassword(Model model,
			@RequestParam("username") String username,
			@RequestParam("email") String email) 
	{
		if (!accountService.existByUserName(username)) {
			model.addAttribute("message", username + "not exists !");
		}
		else {
			Account account = accountService.getByUsername(username);
			sessionService.set("username", username);
			if (!account.getEmail().equalsIgnoreCase(email)) {
				model.addAttribute("message", "Email was wrong!");
			}
			else {
				String token = Integer.toHexString(account.getPassword().hashCode());
				
				//send token by email
				mailerService.sendToken(token , email);
			
				model.addAttribute("message", "Please check email to get code");
				return "user/account/reset-password";
			}
		}
		return "user/account/forgot-password";
	}
	
	@RequestMapping("/reset-password")
	public String resetPassword(Model model,
			@RequestParam("token") String token,
			@RequestParam("newPass") String newPass,
			@RequestParam("confirm") String confirm) {
		String username = sessionService.get("username");
		if (!newPass.equals(confirm)) {
			model.addAttribute("message", "Confirm password was wrong!");
		} 
		else {
			Account account = accountService.getByUsername(username);
			String currentToken = Integer.toHexString(account.getPassword().hashCode());
			if (!token.equals(currentToken)) {
				model.addAttribute("message", "Code was wrong!");
			}
			else {
				account.setPassword(passwordEncoder.encode(newPass));
				accountService.update(account);
				model.addAttribute("message", "Change password successfully");
				return "forward:/security/login/form";
			}
		}
		
		return "user/account/reset-password";
	}
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/list-order")
	public String list(Model model,Authentication auth) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		
		List<Order> list = orderService.findByUserName(account.getUsername());
		model.addAttribute("list", list);
		return "user/account/index";
	}
}
