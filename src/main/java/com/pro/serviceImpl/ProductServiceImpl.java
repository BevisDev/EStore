package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pro.dao.ProductDAO;
import com.pro.entity.Product;
import com.pro.inter.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<Product> findByCategoryId(Integer id, Pageable pageable) {
		return dao.findByCategoryId(id, pageable);
	}

	@Override
	public Page<Product> findByKeywords(String keywords, Pageable pageable) {
		return dao.findByKeywords("%"+keywords+"%", pageable);
	}

	@Override
	public Page<Product> findByLatest(Pageable pageable) {
		return dao.findByLatest(pageable);
	}

	@Override
	public Page<Product> findByFavorite(Pageable pageable) {
		return dao.findByFavorite(pageable);
	}

//	@Override
//	public List<Product> findByBestSellerIds(Pageable pageable) {
//		Page<Integer> ids = dao.findByBestSellerIds(pageable);
//		return dao.findAllById(ids.getContent());
//	}

	@Override
	public Page<Product> findBySpecial(Pageable pageable) {
		return dao.findBySpecial(pageable);
	}

	@Override
	public Product getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void update(Product item) {
		dao.save(item);
		
	}

	@Override
	public void create(Product item) {
		dao.save(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Page<Product> findBySmallPrice(Pageable pageable) {
		return dao.findBySmallPrice(pageable);
	}

	@Override
	public Page<Product> findByMediumPrice(Pageable pageable) {
		return dao.findByMediumPrice(pageable);
	}

	@Override
	public Page<Product> findByLargePrice(Pageable pageable) {
		return dao.findByLargePrice(pageable);
	}

//	@Override
//	public List<Product> findByUserName(String username) {
//		return dao.findByUserName(username);
//	}


}
