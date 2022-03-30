package com.pro.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pro.inter.CategoryService;

@Controller
@RequestMapping("/user/home")
public class HomeUserController {

	@RequestMapping("/index")
	public String index() {
		return "user/home/index";
	}
	

	
}
