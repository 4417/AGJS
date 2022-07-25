package agjs.dao.impl;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.SalesOrderStatusPo;
import agjs.dao.SalesOrderStatusDao;
@Repository
public class SalesOrderStatusDaoImpl implements SalesOrderStatusDao{
	@PersistenceContext
	private Session session;
	public Session getSession() {
		return session;
	}
	
	@Override
	public SalesOrderStatusPo select(SalesOrderStatusPo salesOrderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
