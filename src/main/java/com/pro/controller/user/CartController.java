package com.pro.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.cart.CartService;
import com.pro.entity.Product;

@Controller
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
		model.addAttribute("cart", cartService);
		return "cart/index";
	}
	
	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		cartService.add(id);
		return "forward:/cart/info";
	}
	
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cartService.remove(id);
//		return "forward:/cart/info";
		return "redirect:/cart/view";
	}
	
	@RequestMapping("/cart/update/{id}/{qty}")
	public String update(@PathVariable("id") Integer id,
			@PathVariable("qty") Integer qty) {
		cartService.update(id, qty);
		return "forward:/cart/info";
	}
	
	@RequestMapping("/cart/clear")
	public String clear() {
		cartService.clear();
		return "redirect:/cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/cart/info")
	public Object getInfo() {
		return Map.of("count", cartService.getCount(), "amount", cartService.getAmount());
	}
	
	@RequestMapping("/detail/update/{id}")
	public String update(@PathVariable("id") Integer id,
			@RequestParam("qty") String qty) {
		cartService.update(id, Integer.valueOf(qty));
		return "forward:/cart/info";
	}
}
