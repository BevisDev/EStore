package com.pro.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pro.entity.Product;
import com.pro.inter.ProductService;

@Controller
@RequestMapping("/product")
public class SearchUserController {

	@Autowired
	ProductService productService;
	
	// search theo keywords
	@RequestMapping("/search")
	public String search(Model model, @RequestParam("keywords") String keywords,
			@RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findByKeywords(keywords, pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}

	// lay ra san pham moi nhat
	@RequestMapping("/newest")
	public String latest(Model model, @RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findByLatest(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}

	// lay ra san pham co likeCount nhieu nhat va likeCount> 0
	@RequestMapping("/favorite")
	public String favorite(Model model, @RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findByFavorite(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}

	// lay ra san pham ban chay nhat
//		@RequestMapping("/bestseller")
//		public String bestseller(Model model,@RequestParam(name="page", defaultValue = "0") String pageNumber) {
//			Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
//			List<Product> page = productService.findByBestSellerIds(pageable);
//			model.addAttribute("page", page);
//			return "user/product/index";
//		}

	@RequestMapping("/special")
	public String special(Model model, @RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findBySpecial(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}
	
	@RequestMapping("/small-price")
	public String smallPrice(Model model, @RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findBySmallPrice(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}
	
	@RequestMapping("/medium-price")
	public String mediumPrice(Model model, @RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findByMediumPrice(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}
	@RequestMapping("/large-price")
	public String largePrice(Model model, @RequestParam(name = "page", defaultValue = "0") String pageNumber) {
		Pageable pageable = PageRequest.of(Integer.valueOf(pageNumber), 9);
		Page<Product> page = productService.findByLargePrice(pageable);
		model.addAttribute("page", page);
		return "user/product/index";
	}
	
}
