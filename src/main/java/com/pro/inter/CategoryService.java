package com.pro.inter;

import java.util.List;

import com.pro.entity.Category;

public interface CategoryService {
	List<Category> findAll();

	void create(Category cate);

	void update(Category cate);

	Category getById(Integer id);

	void deleteById(Integer id);

}
