package com.pro.admin.controller;

import java.io.File;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.pro.entity.Brand;
import com.pro.entity.Category;
import com.pro.entity.Product;
import com.pro.inter.BrandService;
import com.pro.inter.CategoryService;
import com.pro.inter.ProductService;
import com.pro.session.SessionService;
import com.pro.upload.UploadService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UploadService uploadService;
	
	/*
	 * @Autowired BrandService brandService;
	 */
	@Autowired
	SessionService sessionService;

	@RequestMapping("/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		return "forward:/admin/product/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("form", new Product());
		return this.DanhSach(model);
	}

	@RequestMapping("/reset")
	public String reset(Model model) {
		return "redirect:/admin/product/index";
	}

	@RequestMapping("/create")
	public String create(Model model,
			@RequestPart("image_file1") MultipartFile imageFile1,
			@RequestPart("image_file2") MultipartFile imageFile2,
			@RequestPart("image_file3") MultipartFile imageFile3,
			@ModelAttribute("form") Product item) {
		if (!imageFile1.isEmpty()) {
			File image1 = uploadService.save(imageFile1, "/images/product/");
			item.setImageOne(image1.getName());
		}
		if (!imageFile2.isEmpty()) {
			File image2 = uploadService.save(imageFile2, "/images/product/");
			item.setImageTwo(image2.getName());
		}
		if (!imageFile3.isEmpty()) {
			File image3 = uploadService.save(imageFile3, "/images/product/");
			item.setImageThree(image3.getName());
		}
		productService.create(item);
		model.addAttribute("message", "Create successfully!");
		return this.DanhSach(model);
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, 
			@PathVariable("id") Integer id) {
		Product item = productService.getById(id);
		model.addAttribute("form", item);
		return this.DanhSach(model);
	}
	
	@RequestMapping("/update")
	public String update(Model model, 
			@RequestPart("image_file1") MultipartFile imageFile1,
			@RequestPart("image_file2") MultipartFile imageFile2,
			@RequestPart("image_file3") MultipartFile imageFile3,
			@ModelAttribute("form") Product item) {
		if (!imageFile1.isEmpty()) {
			File image1 = uploadService.save(imageFile1, "/images/product/");
			item.setImageOne(image1.getName());
		}
		if (!imageFile2.isEmpty()) {
			File image2 = uploadService.save(imageFile2, "/images/product/");
			item.setImageTwo(image2.getName());
		}
		if (!imageFile3.isEmpty()) {
			File image3 = uploadService.save(imageFile3, "/images/product/");
			item.setImageThree(image3.getName());
		}
		productService.update(item);
		model.addAttribute("message", "Update successfully!");
		return this.DanhSach(model);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		productService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");
		model.addAttribute("form", new Product());
		return this.DanhSach(model);
	}
	
	// return ve List product
	public String DanhSach(Model model) {
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 5);
		Page<Product> page = productService.findAll(pageable);
		model.addAttribute("page", page);
		return "admin/product/index";
	}

	// lay ra List Categories
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryService.findAll();

	}
	
	//lay ra List brand
//	@ModelAttribute("brands")
//	public List<Brand> getBrands(){
//		return brandService.findAll();
//	}
}
