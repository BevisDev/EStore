package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Account;
import com.pro.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer>{

}
