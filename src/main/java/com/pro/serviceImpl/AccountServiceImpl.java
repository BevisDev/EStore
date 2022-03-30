package com.pro.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pro.admin.bean.AccountFilter;
import com.pro.dao.AccountDAO;
import com.pro.dao.AuthorityDAO;
import com.pro.dao.RoleDAO;
import com.pro.entity.Account;
import com.pro.entity.Authority;
import com.pro.entity.Role;
import com.pro.inter.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDAO dao;
	
	@Autowired
	AuthorityDAO authorDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public void create(Account account) {
		dao.save(account);
	}

	@Override
	public void update(Account account) {
		dao.save(account);
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account getByUsername(String username) {
		return dao.getById(username);
	}

	@Override
	public void deleteByUsername(String username) {
		dao.deleteById(username);
	}

	@Override
	public Page<Account> findPage(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Page<Account> findPageByFilter(AccountFilter filter, Pageable pageable) {
		String keyword = "%" + filter.getKeyword() + "%";
		// role
		if (filter.getRole() == 2) {
			if (filter.getActivated() == 2) {
				return dao.findAccountByKeyword(keyword, pageable);
			}
			return dao.findAccountKeywordAndActiveted(keyword, filter.getActivated() ==1, pageable);
		}
		
		//customer 
		if (filter.getRole() == 0 ) {
			if (filter.getActivated() == 2) {
				return dao.findCustomerByKeyword(keyword, pageable);
			}
			return dao.findCustomerByKeywordAndActive(keyword, filter.getActivated() == 1, pageable);
		}
		
		//admin
		if(filter.getActivated() == 2) {
			return dao.findAdminByKeyword(keyword, pageable);
		}
		return dao.findAdminByKeywordAndActive(keyword, filter.getActivated() == 1, pageable);
	}

	@Transactional
	@Override
	public void create(Account account, List<String> roleIds) {
		List<Authority> authorities = roleIds.stream().map(rid ->{
			Role role = roleDAO.getById(rid);
			Authority authority = new Authority(null, role, account);
			return authority;
		}).toList();
		account.setAuthorities(authorities);
		// luu vao Account xuong sql
		dao.save(account);
		// luu vao authority
		authorDAO.saveAll(authorities);
	}
	
	@Transactional
	@Override
	public void update(Account account, List<String> roleIds) {
		authorDAO.deleteAll(dao
				.getById(account.getUsername())
				.getAuthorities());
		if (!roleIds.isEmpty()) {
			List<Authority> authorities =roleIds.stream().map(rid -> {
				Role role = roleDAO.getById(rid);
				Authority authority = new Authority(null, role, account);
				return authority;
			}).toList();
			// luu vao authority
			authorDAO.saveAll(authorities);
			// set vao account
			account.setAuthorities(authorities);
		}
	}

}
