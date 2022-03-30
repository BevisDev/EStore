package com.pro.inter;

import java.util.List;

import com.pro.entity.Brand;

public interface BrandService {

	List<Brand> findAll();

	void create(Brand brand);

	void update(Brand brand);

	Brand getById(Integer id);

	void deleteById(Integer id);
}
