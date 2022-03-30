package com.pro.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.report.Report;
import com.pro.report.ReportService;

@CrossOrigin("*")
@Controller
public class ReportAdController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping("/admin/report/inventory")
	public String inventory() {
		return "admin/report/inventory";
	}
	
	@ResponseBody
	@RequestMapping("/api/inventory")
	public List<Report> getInventory() {
		return reportService.getInventoryByCategory();
	}
}
