package com.pro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pro.dao.AccountDAO;
import com.pro.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AccountDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = dao.findById(username).get();
			UserDetails userDetails = new UserDetailsImpl(account);
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found!");
		}
		return null;
	}

}
