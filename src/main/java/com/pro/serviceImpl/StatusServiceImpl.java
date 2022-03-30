package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.dao.StatusDAO;
import com.pro.entity.Status;
import com.pro.inter.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDAO dao;
	
	@Override
	public List<Status> findAll() {
		return dao.findAll();
	}

}
