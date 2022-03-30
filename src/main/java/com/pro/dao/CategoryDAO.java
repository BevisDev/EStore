package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Account;
import com.pro.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
