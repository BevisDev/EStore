package com.pro.admin.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.entity.Order;
import com.pro.entity.Status;
import com.pro.inter.OrderService;
import com.pro.inter.StatusService;
import com.pro.session.SessionService;

@Controller
@RequestMapping("/admin/order")
public class OrderAdController {
	@Autowired
	StatusService statusService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/paginate/{pageNumber}")
	public String paginate(@PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		return "forward:/admin/order/index";
	}
	
	// luu id vao session để khi hiện 
	@RequestMapping("/status/{statusId}")
	public String StatusId(@PathVariable("statusId") Optional<Integer> statusId) {
		sessionService.set("statusId", statusId.orElse(1));
		return "forward:/admin/order/paginate/0";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("form", new Order());
		return this.DanhSach(model);
	}

	@RequestMapping("/reset")
	public String reset() {
		return "redirect:/admin/order/index";
	}

	@RequestMapping("create")
	public String create(Model model, @ModelAttribute("form") Order order) {
		orderService.create(order);
		model.addAttribute("message", "Create successfully!");
		return "forward:/admin/order/index";
	}

	@RequestMapping("/update")
	public String update(Model model, @ModelAttribute("form") Order order) {
		orderService.update(order);
		model.addAttribute("message", "Update successfully!");
		List<Order> list = orderService.findAll();
		model.addAttribute("list", list);
		return "forward:/admin/order/index";
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Order item = orderService.getById(id);
		model.addAttribute("form", item);
		return this.DanhSach(model);
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		orderService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");

		model.addAttribute("form", new Order());
		return this.DanhSach(model);
	}

	public String DanhSach(Model model) {
		Integer statusId = sessionService.get("statusId",0);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 4);
		Page<Order> page = orderService.findPageByStatusId(statusId, pageable);
		model.addAttribute("page", page);
		return "admin/order/index";
	}
	
	//lat ra List status
	@ModelAttribute("statuses")
	public List<Status> getStatus(){
		return statusService.findAll();
	}
	
}
