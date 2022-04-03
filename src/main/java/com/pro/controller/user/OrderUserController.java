package com.pro.controller.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.cart.CartService;
import com.pro.entity.Account;
import com.pro.entity.Order;
import com.pro.security.UserDetailsImpl;

@Controller
@RequestMapping("/order")
public class OrderUserController {

	@Autowired
	CartService cartService;
	
	@RequestMapping("/checkout")
	public String checkout(Model model, Authentication auth) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		Order order = new Order();
		order.setAccount(account);
		order.setOrderDate(new Date());
		order.setTotal(cartService.getAmount());
		
		model.addAttribute("order", order);
		model.addAttribute("cart", cartService);
		return "user/order/checkout";
	}
}
