package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.dao.BrandDAO;
import com.pro.entity.Brand;
import com.pro.inter.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDAO dao;

	@Override
	public List<Brand> findAll() {
		return dao.findAll();
	}

	@Override
	public void create(Brand brand) {
		dao.save(brand);
	}

	@Override
	public void update(Brand brand) {
		dao.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
