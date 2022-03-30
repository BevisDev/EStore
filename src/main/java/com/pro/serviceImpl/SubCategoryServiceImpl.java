package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.dao.SubCategoryDAO;
import com.pro.entity.SubCategory;
import com.pro.inter.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	SubCategoryDAO dao;

	@Override
	public List<SubCategory> findAll() {
		return dao.findAll();
	}

}
