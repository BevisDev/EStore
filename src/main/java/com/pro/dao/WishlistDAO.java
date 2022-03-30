package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Wishlist;

public interface WishlistDAO extends JpaRepository<Wishlist, Integer>{

}
