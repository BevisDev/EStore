package com.pro.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.pro.dao.ProductDAO;
import com.pro.entity.Product;

@SessionScope
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	ProductDAO dao;
	
	Map<Integer, CartItem> map = new HashMap<>();
			
	@Override
	public Collection<CartItem> getItems() {
		return map.values();
	}

	@Override
	public void add(Integer id) {
		CartItem item = map.get(id);
		if(item == null) {
			Product product = dao.getById(id);
			// số lượng = 1
			item = new CartItem(product,1);
			map.put(id, item);
		}else {
			item.increase();
		}
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public void update(Integer id, int qty) {
		CartItem item = map.get(id);
		item.setQty(qty);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public int getCount() {
		return this.getItems().stream()
				.mapToInt(item -> item.getQty())
				.sum();
	}

	@Override
	public double getAmount() {
		return this.getItems().stream()
				.mapToDouble(item -> item.getAmount())
				.sum();
	}

	
}
