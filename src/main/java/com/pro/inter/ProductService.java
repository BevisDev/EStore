package com.pro.inter;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pro.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Page<Product> findByCategoryId(Integer id, Pageable pageable);

	Page<Product> findByKeywords(String keywords, Pageable pageable);

	Page<Product> findByLatest(Pageable pageable);

	Page<Product> findByFavorite(Pageable pageable);

	List<Product> findByBestSellerIds(Pageable pageable);

	Page<Product> findBySpecial(Pageable pageable);

	Product getById(Integer id);

	void update(Product item);

	void create(Product item);

	void deleteById(Integer id);

	Page<Product> findAll(Pageable pageable);


}
