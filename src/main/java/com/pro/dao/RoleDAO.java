package com.pro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Account;
import com.pro.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

}
