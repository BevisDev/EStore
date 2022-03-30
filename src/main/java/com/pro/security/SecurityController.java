package com.pro.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

	@RequestMapping("/login/form")
	public String login() {
		return "security/login";
	}

	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message","Login Success");
		return "forward:/security/login/form";
	}

	@RequestMapping("/login/failure")
	public String loginFailure(Model model) {
		model.addAttribute("message","Login Failure");
		return "forward:/security/login/form";
	}

	@RequestMapping("/logout/success")
	public String logout(Model model) {
		model.addAttribute("message","Login Out System");
		return "forward:/security/login/form";
	}

	@RequestMapping("/access/denied")
	public String access(Model model) {
		model.addAttribute("message","You don't have permit that");
		return "forward:/security/login/form";
	}
}
