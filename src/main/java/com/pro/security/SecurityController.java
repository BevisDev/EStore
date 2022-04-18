package com.pro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/security/login/form")
	public String login() {
		return "security/login";
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model, Authentication auth) {
		System.out.println(auth.getAuthorities());
		model.addAttribute("message", "Login Success");
		return "forward:/security/login/form";
	}

	@RequestMapping("/security/login/failure")
	public String loginFailure(Model model) {
		model.addAttribute("message", "Login Failure");
		return "forward:/security/login/form";
	}

	@RequestMapping("/security/logout/success")
	public String logout(Model model) {
		model.addAttribute("message", "Sign Out System");
		return "forward:/security/login/form";
	}

	@RequestMapping("/security/access/denied")
	public String access(Model model) {
		model.addAttribute("message", "You don't have permit that");
		return "forward:/security/login/form";
	}

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	// login google & fb
	@RequestMapping("/oauth2/login/success")
	public String oauth2LoginSuccess(Model model, OAuth2AuthenticationToken oauth2) {
//		OAuth2User user = oauth2.getPrincipal();
//		System.out.println(user.getName());
//		System.out.println((String)user.getAttribute("email"));
//		System.out.println((String)user.getAttribute("name"));
		userDetailsServiceImpl.authenticate(oauth2);
		model.addAttribute("message", "Đăng nhập thành công");
		return "forward:/security/login/form";
	}
}
