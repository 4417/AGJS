package agjs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import agjs.bean.SalesOrderHeaderPo;
import agjs.dao.SalesOrderHeaderDao;
import agjs.service.SalesOrderHeaderService;

@Service
public class SalesOrderHeaderServiceImpl implements SalesOrderHeaderService {
	@Autowired
	private SalesOrderHeaderDao dao;


	@Transactional
	public SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader) {
//		//if login = true
//
//		//else create user account 並綁定訂單
//		
//		//create SO header
//		
//		//create SO items
//		
//		//create Journey
//		
//		//串綠界API
//		
//		//新增客房使用紀錄
	
		return null;
	}
	
	
	@Override
	public List<SalesOrderHeaderPo> getAll() {
		return dao.getAll();
	}
	
}
