package com.pro.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pro.entity.Order;

public interface ReportDAO extends JpaRepository<Order, Long>{

	@Query("SELECT o.category.name AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM Product o "
			+ " GROUP BY o.category.name")
	List<Report> getInventoryByCategory();

}
