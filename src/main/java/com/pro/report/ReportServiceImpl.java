package com.pro.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	ReportDAO dao;
	
	@Override
	public List<Report> getInventoryByCategory() {
		return dao.getInventoryByCategory();
	}



}
