package com.pro.cart;

import com.pro.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {

	Product product;
	int qty;
	
	//lấy Tổng tiền
	public double getAmount() {
		return qty * product.getPromotePrice();
	}
	
	// số lượng item tăng
	public void increase() {
		this.qty++;
	}
}
