package com.pro.inter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pro.admin.bean.AccountFilter;
import com.pro.entity.Account;

public interface AccountService {
	void create(Account account);

	void update(Account account);

	List<Account> findAll();

	Account getByUsername(String username);
	
	void deleteByUsername(String username);

	Page<Account> findPage(Pageable pageable);

	Page<Account> findPageByFilter(AccountFilter filter, Pageable pageable);

	void create(Account account, List<String> roleIds);

	void update(Account account, List<String> roleIds);

	boolean existByUserName(String username);

	/* void setNewPassword(Account account, String newPassword); */
}
