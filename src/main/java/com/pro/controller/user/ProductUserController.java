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

import com.pro.entity.Product;
import com.pro.entity.Share;
import com.pro.inter.ProductService;
import com.pro.inter.ShareService;

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
	public String index(Model model, @RequestParam(name="page", defaultValue = "0") String pageNumber){
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findAll(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}
	
	@RequestMapping("/paginate/")
	public String paginate(@RequestParam("page") String pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		return "forward:/user/product/index";
	}
	
	// hien thi list theo category
	@RequestMapping("/category")
	public String List(Model model,@RequestParam("id") String id,
			@RequestParam(name="page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findByCategoryId(Integer.valueOf(id), pageable);
		model.addAttribute("page", page);
		return "user/product/index";
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

	
	  @Autowired 
	  ShareService shareService;
	  
	  @Autowired 
	  MailerService mailerService;
	  
	  @ResponseBody
	  @RequestMapping("/share-send") 
	  public void shareSend(@RequestBody Share share){ 
		  shareService.create(share); //send mail
		  mailerService.sendShare(share);
	  
	  }
	 
}
