package com.pro.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/home")
public class HomeAdController {

	@RequestMapping("/index")
	public String index() {
		return "admin/home/index";
	}
}
