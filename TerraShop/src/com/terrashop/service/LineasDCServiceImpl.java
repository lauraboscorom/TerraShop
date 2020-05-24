package com.terrashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.LineaDCDao;
import com.terrashop.entity.Venta;

@Transactional
@Service
public class LineasDCServiceImpl implements LineasDCService {
	
	@Autowired
	LineaDCDao lineaDCDao;
	
}
