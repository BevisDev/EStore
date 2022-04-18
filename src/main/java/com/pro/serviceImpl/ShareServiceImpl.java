package com.pro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.dao.ShareDAO;
import com.pro.entity.Share;
import com.pro.inter.ShareService;

@Service
public class ShareServiceImpl implements ShareService{
	
	@Autowired
	ShareDAO dao;

	@Override
	public void create(Share share) {
		dao.save(share);
	}
	
}
