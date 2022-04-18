package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.cart.CartService;
import com.pro.dao.OrderDAO;
import com.pro.dao.OrderDetailDAO;
import com.pro.entity.Order;
import com.pro.entity.OrderDetail;
import com.pro.inter.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	@Override
	public void create(Order order) {
		dao.save(order);
	}

	@Override
	public void update(Order order) {
		dao.save(order);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Order getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Page<Order> findPageByStatusId(Integer statusId, Pageable pageable) {
		return dao.findPageByStatusId(statusId, pageable);
	}



	@Override
	public void create(Order order, CartService cartService) {
		dao.save(order);
		List<OrderDetail> list = cartService.getItems().stream().map(item -> {
			return new OrderDetail(order, item.getProduct(), item.getQty());
		}).toList();
		ddao.saveAll(list);
	}

	@Override
	public List<Order> findByUserName(String username) {
		return dao.findByUserName(username);
	}

}
