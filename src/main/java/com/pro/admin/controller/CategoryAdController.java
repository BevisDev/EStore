package com.pro.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.dao.CategoryDAO;
import com.pro.entity.Category;
import com.pro.inter.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("form", new Category());
		return this.DanhSach(model);
	}

	@RequestMapping("/reset")
	public String reset() {
		return "redirect:/admin/category/index";
	}

	@RequestMapping("/create")
	public String create(Model model, @ModelAttribute("form") Category cate) {
		categoryService.create(cate);
		model.addAttribute("message", "Create successfully!");
		return this.DanhSach(model);
	}

	@RequestMapping("/update")
	public String update(Model model, @ModelAttribute("form") Category cate) {
		categoryService.update(cate);
		model.addAttribute("message", "Update successfully!");
		return this.DanhSach(model);
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category item = categoryService.getById(id);
		model.addAttribute("form", item);
		return this.DanhSach(model);
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		categoryService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");
		model.addAttribute("form", new Category());
		return this.DanhSach(model);
	}

	public String DanhSach(Model model) {
		model.addAttribute("list",categoryService.findAll());
		return "admin/category/index";
	}

}
