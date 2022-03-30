package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Account;
import com.pro.entity.Share;

public interface ShareDAO extends JpaRepository<Share, Long>{

}
