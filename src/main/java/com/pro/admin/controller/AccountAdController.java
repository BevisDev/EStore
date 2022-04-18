package com.pro.admin.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.pro.admin.bean.AccountFilter;
import com.pro.entity.Account;
import com.pro.entity.Role;
import com.pro.inter.AccountService;
import com.pro.inter.RoleService;
import com.pro.session.SessionService;
import com.pro.upload.UploadService;

@Controller
@RequestMapping("/admin/account")
public class AccountAdController {
	@Autowired
	UploadService uploadService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/filter")
	public String filter(AccountFilter filter) {
		sessionService.set("filter", filter);
		return "forward:/admin/account/paginate/0";
	}
	
	@RequestMapping("/paginate/{pageNumber}")
	public String paginate(@PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		return "forward:/admin/account/index";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("form", new Account());
		return this.DanhSach(model);
	}

	@RequestMapping("/reset")
	public String reset() {
		return "redirect:/admin/account/index";
	}

	@RequestMapping("/create")
	public String create(Model model, @ModelAttribute("form") Account account,
			@RequestPart("avatar_file") MultipartFile avatar_file,
			@RequestParam("roleIds") Optional<List<String>> roleIds) {
		if (!avatar_file.isEmpty()) {
			File image = uploadService.save(avatar_file, "/images/account/");
			account.setAvatar(image.getName());
		}
		accountService.create(account, roleIds.orElse(List.of()));
		model.addAttribute("message", "Create successfully!");
		return this.DanhSach(model);
	}

	@RequestMapping("/update")
	public String update(Model model, @ModelAttribute("form") Account account,
			@RequestPart("avatar_file") MultipartFile avatar_file,
			@RequestParam("roleIds") Optional<List<String>> roleIds) {
		if (!avatar_file.isEmpty()) {
			File image = uploadService.save(avatar_file, "/images/account/");
			account.setAvatar(image.getName());
		}
		accountService.update(account, roleIds.orElse(List.of()));
		model.addAttribute("message", "Update successfully!");
		return this.DanhSach(model);
	}

	@RequestMapping("/edit/{username}")
	public String edit(Model model, @PathVariable("username") String username) {
		Account item = accountService.getByUsername(username);
		model.addAttribute("form", item);
		return this.DanhSach(model);
	}

	@RequestMapping("/delete/{username}")
	public String delete(Model model, @PathVariable("username") String username) {
		accountService.deleteByUsername(username);
		model.addAttribute("message", "Delete successfully!");

		model.addAttribute("form", new Account());
		return this.DanhSach(model);
	}

	public String DanhSach(Model model) {
		AccountFilter filter = sessionService.get("filter", new AccountFilter());
		model.addAttribute("filter", filter);
		
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 4);
		Page<Account> page = accountService.findPageByFilter(filter,pageable);
		model.addAttribute("page", page);
		return "admin/account/index";
	}
	
	//lay ra List Role
	@ModelAttribute("roles")
	public List<Role> getRoles(){
		return roleService.findAll();
	}
}
