package com.pro.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pro.dao.BrandDAO;
import com.pro.entity.Brand;
import com.pro.entity.Category;
import com.pro.entity.SubCategory;
import com.pro.inter.BrandService;
import com.pro.inter.CategoryService;
import com.pro.inter.SubCategoryService;

@Service
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BrandService brandService;

	@Autowired
	SubCategoryService subCategoryService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		List<Category> list = categoryService.findAll();
		request.setAttribute("categories", list);
		List<Brand> brands = brandService.findAll();
		request.setAttribute("brands", brands);
		List<SubCategory> subs = subCategoryService.findAll();
		request.setAttribute("subs", subs);
	}
}
