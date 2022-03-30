package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Account;
import com.pro.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
