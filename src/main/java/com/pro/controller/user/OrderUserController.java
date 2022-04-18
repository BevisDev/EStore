package com.pro.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.cart.CartService;
import com.pro.entity.Account;
import com.pro.entity.Order;
import com.pro.entity.Product;
import com.pro.entity.Status;
import com.pro.inter.OrderService;
import com.pro.inter.ProductService;
import com.pro.mailer.MailerService;
import com.pro.security.UserDetailsImpl;

@Controller
@RequestMapping("/user/order")
public class OrderUserController {

	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/checkout")
	public String checkout(Model model, Authentication auth) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		Order order = new Order();
		order.setAccount(account);
		order.setOrderDate(new Date());
		order.setTotal(cartService.getAmount());
		
		model.addAttribute("item", order);
		model.addAttribute("cart", cartService);
		return "user/order/checkout";
	}
	
	@Autowired
	MailerService mailerService;
	
	@RequestMapping("/purchase")
	public String purchase(Model model, Authentication auth,
			@ModelAttribute("item") Order order) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		try {
			order.setAccount(account);
			orderService.create(order, cartService);
			mailerService.sendOrder(order);
			return "redirect:/user/order/detail/" + order.getId();
		} catch (Exception e) {
			model.addAttribute("message", "Đặt hàng lỗi");
			model.addAttribute("cart", cartService);
			return "/user/order/checkout";
		}
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model,@PathVariable("id") Long id) {
		Order order = orderService.getById(id);
		cartService.clear();
		model.addAttribute("item", order);
		return "user/order/detail";
	
	}
	
	@RequestMapping("/cancel/{id}")
	public String cancel(Model model,@PathVariable("id") Long id) {
		Order order = orderService.getById(id);
		order.setStatus(new Status(1, null, null, null, null));
		orderService.update(order);
		return "redirect:/user/order/detail/" + id;
	}
	
	
	
//	@Autowired
//	ProductService productService;
//	
//	@RequestMapping("/items-purchased")
//	public String ItemsPurchased(Model model,Authentication auth) {
//		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
//		Account account = userDetails.getAccount();
//		
//		List<Product> list = productService.findByUserName(account.getUsername());
//		model.addAttribute("list", list);
//		return "user/order/list";
//	}

}
