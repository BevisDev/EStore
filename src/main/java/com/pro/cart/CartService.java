package com.pro.cart;

import java.util.Collection;

public interface CartService {

	/**
	 * Đọc các mặt hàng trong giỏ
	 * */
	Collection<CartItem> getItems();
	
	/**
	 * Thêm mặt hàng vào giỏ hàng hoặc tăng số lượng lên 1 nếu đã tồn tại
	 * @param id mã mặt hàng
	 * @return mặt hàng đã được thêm vào hoặc cập nhật số lượng
	 * */
	void add(Integer id);
	
	/**
	 * Xóa mặt hàng ra khỏi giỏ 
	 * @param id mã mặt hàng
	 * @return mặt hàng đã bị xóa 
	 * */
	void remove(Integer id);
	
	/**
	 * thay đổi mặt hàng trong giỏ
	 * @param id mã mặt hàng
	 * @param qty số lượng mới
	 * @return mặt hàng đã được cập nhật số lượng
	 * */
	void update(Integer id, int qty);
	
	/**
	 * Xóa sạch các mặt hàng
	 * */
	void clear();
	
	/**
	 * lấy tổng số lượng các mặt hàng trong giỏ
	 * */
	int getCount();
	
	/**
	 * Xóa tổng số tiền các mặt hàng trong giỏ
	 * */
	double getAmount();
}
