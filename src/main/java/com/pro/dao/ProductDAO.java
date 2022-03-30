package com.pro.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pro.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT o FROM Product o WHERE o.category.id = ?1")
	Page<Product> findByCategoryId(Integer id, Pageable pageable);
	
	@Query("SELECT o FROM Product o "
			+ "WHERE o.name LIKE ?1")
	Page<Product> findByKeywords(String keywords, Pageable pageable);

	@Query("SELECT o FROM Product o "
			+ "ORDER BY o.productDate DESC")
	Page<Product> findByLatest(Pageable pageable);

	@Query("SELECT o FROM Product o "
			+ "WHERE o.likeCount > 0 ORDER BY o.likeCount DESC")
	Page<Product> findByFavorite(Pageable pageable);
	
	// lấy các nhóm id của product mà tổng tiền bán cao nhất)
	@Query("SELECT d.product.id "
			+ " FROM OrderDetail d "
			+ " GROUP BY d.product.id"
			+ " ORDER BY sum(d.unitPrice * d.quantity * (1 - d.discount)) DESC")
	Page<Integer> findByBestSellerIds(Pageable pageable);

	@Query("SELECT o FROM Product o "
			+ "WHERE o.special = true")
	Page<Product> findBySpecial(Pageable pageable);

	@Query("SELECT o FROM Product o WHERE o.category.id = ?1")
	Page<Product> findPageByCategoryId(Integer cid, Pageable pageable);
		
}
