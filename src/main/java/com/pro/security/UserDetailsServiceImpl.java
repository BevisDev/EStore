package com.pro.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.pro.dao.AccountDAO;
import com.pro.entity.Account;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AccountDAO dao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = dao.findById(username).get();
			UserDetails userDetails = new UserDetailsImpl(account);
			return userDetails;
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + " not found!");
		}	
	}


	public void authenticate(OAuth2AuthenticationToken oauth2) {
		String id = oauth2.getName();
		String email = oauth2.getPrincipal().getAttribute("email");
		String fullname = oauth2.getPrincipal().getAttribute("name");
		
		if(!dao.existsById(id)) { 
			Account account = new Account(id, "", fullname, email, "" , "user.png", true,"", List.of(), List.of(), List.of());
			dao.save(account);
		}
		
		UserDetails user = this.loadUserByUsername(id);
		
		Authentication auth = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
