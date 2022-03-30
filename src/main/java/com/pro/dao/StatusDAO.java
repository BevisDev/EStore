package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Account;
import com.pro.entity.Status;

public interface StatusDAO extends JpaRepository<Status, Integer>{

}
