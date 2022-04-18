package com.pro.inter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pro.cart.CartService;
import com.pro.entity.Order;

public interface OrderService {

	void create(Order order);

	void update(Order order);

	List<Order> findAll();

	Order getById(Long id);

	void deleteById(Long id);

	Page<Order> findPageByStatusId(Integer statusId, Pageable pageable);

	void create(Order order, CartService cartService);

	List<Order> findByUserName(String username);

}
