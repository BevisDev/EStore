package com.pro.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.pro.entity.Brand;
import com.pro.entity.Product;
import com.pro.inter.BrandService;
import com.pro.upload.UploadService;

@Controller
@RequestMapping("/admin/brand")
public class BrandAdController {
	@Autowired
	UploadService uploadService;

	@Autowired
	BrandService brandService;

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("form", new Brand());
		List<Brand> list = brandService.findAll();
		model.addAttribute("list", list);
		return "admin/brand/index";
	}

	@RequestMapping("/reset")
	public String reset() {
		return "redirect:/admin/brand/index";
	}

	@RequestMapping("/create")
	public String create(Model model,
			@RequestPart("imageLogo") MultipartFile imageLogo,
			@ModelAttribute("form") Brand brand) {
		if (!imageLogo.isEmpty()) {
			File image = uploadService.save(imageLogo, "/images/brand/");
			brand.setLogo(image.getName());
		}
		brandService.create(brand);
		model.addAttribute("message", "Create successfully!");
		return this.DanhSach(model);
	}

	@RequestMapping("/update")
	public String update(Model model, @ModelAttribute("form") Brand brand,
			@RequestPart("imageLogo") MultipartFile imageLogo) {
		if(!imageLogo.isEmpty()) {
			File image = uploadService.save(imageLogo, "/images/brand/");
			brand.setLogo(image.getName());
		}
		brandService.update(brand);
		model.addAttribute("message", "Update successfully!");
		return this.DanhSach(model);
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Brand item = brandService.getById(id);
		model.addAttribute("form", item);
		return this.DanhSach(model);
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		brandService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");

		model.addAttribute("form", new Brand());
		return this.DanhSach(model);
	}

	public String DanhSach(Model model) {
		model.addAttribute("list", brandService.findAll());
		return "admin/brand/index";
	}
}
