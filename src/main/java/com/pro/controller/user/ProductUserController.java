package com.pro.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import com.pro.entity.Product;
import com.pro.entity.Share;
import com.pro.inter.ProductService;
import com.pro.inter.ShareService;
import com.pro.mailer.Mail;
import com.pro.mailer.MailerService;
import com.pro.session.SessionService;

@Controller
@RequestMapping("/user/product")
public class ProductUserController {

	@Autowired
	ProductService productService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/index")
	public String index(Model model){
		Integer cid = sessionService.get("cid", 1);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber,10);
		Page<Product> page = productService.findByCategoryId(cid, pageable);
		model.addAttribute("page", page);
		return "user/product/list";
	}
	
	@RequestMapping("/paginate/{pageNumber}")
	public String paginate(@PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		return "forward:/user/product/index";
	}
	
	// hien thi list theo category
	@RequestMapping("/category/{id}")
	public String List(@PathVariable("id") Integer id) {
		sessionService.set("cid", id);
		return "forward:/user/product/index";
	}
	
	// search theo keywords
	@RequestMapping("/search")
	public String search(Model model, @RequestParam("keywords") String keywords) {
		sessionService.set("keywords", keywords);
		Pageable pageable = PageRequest.of(0, 1);
		Page<Product> page = productService.findByKeywords(keywords, pageable);
		model.addAttribute("page", page);
		return "user/product/list";
	}

	// lay ra san pham moi nhat
	@RequestMapping("/latest")
	public String latest(Model model) {
		Page<Product> page = productService.findByLatest(Pageable.unpaged());
		model.addAttribute("list", page.getContent());
		return "user/product/list";
	}

	// lay ra san pham co likeCount nhieu nhat va likeCount> 0
	@RequestMapping("/favorite")
	public String favorite(Model model) {
		Page<Product> page = productService.findByFavorite(Pageable.unpaged());
		model.addAttribute("list", page.getContent());
		return "user/product/list";
	}

	@RequestMapping("/bestseller")
	public String bestseller(Model model) {
		List<Product> list = productService.findByBestSellerIds(Pageable.unpaged());
		model.addAttribute("list", list);
		return "user/product/list";
	}

	@RequestMapping("/special")
	public String special(Model model) {
		Page<Product> page = productService.findBySpecial(Pageable.unpaged());
		model.addAttribute("list", page.getContent());
		return "user/product/list";
	}

	// hien thi chi tiet san pham
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = productService.getById(id);
		model.addAttribute("item", product);
		return "user/product/detail";
	}

	// rest api
	@ResponseBody
	@RequestMapping("/like/{id}")
	public Integer like(@PathVariable("id") Integer id) {
		Product product = productService.getById(id);
		product.setLikeCount(product.getLikeCount() + 1);
		productService.update(product);
		return product.getLikeCount();
	}

	/*
	 * @Autowired ShareService shareService;
	 * 
	 * @Autowired MailerService mailerService;
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/share-send") public void shareSend(@RequestBody Share
	 * share) { shareService.create(share); //send mail
	 * mailerService.sendShare(share);
	 * 
	 * }
	 */
}