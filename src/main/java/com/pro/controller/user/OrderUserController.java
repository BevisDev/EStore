package com.pro.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderUserController {

	@RequestMapping("/checkout")
	public String checkout() {
		return "user/order/checkout";
	}
}
