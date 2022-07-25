package agjs.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.SalesOrderHeaderPo;
import agjs.dao.salesOrderHeaderDao;

@Repository
public class salesOrderHeaderDaoImpl implements salesOrderHeaderDao{
	@PersistenceContext
	private Session session;
	public Session getSession() {
		return session;
	}
	
	
	@Override
	public SalesOrderHeaderPo select(SalesOrderHeaderPo salesOrderHeaderId) {
		if(salesOrderHeaderId!=null) {
			String hql = "from salesOrderHeaderDao where salesOrderHeaderId = :salesOrderHeaderId";
			return session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("salesOrderHeaderId", salesOrderHeaderId.getSalesOrderHeaderId()).uniqueResult();
		}
		return null;
	}


	@Override
	public List<SalesOrderHeaderPo> select() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SalesOrderHeaderPo insert(SalesOrderHeaderPo bean) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SalesOrderHeaderPo update(Integer userId, Date createDate, Date orderStartDate, Date orderEndDate,
			Date orderChangeDate, Integer salesOrderStatusId, String orderRemark, Integer roomPrice,
			Integer journeyPrice, Integer salesOrderHeaderId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean delete(SalesOrderHeaderPo salesOrderHeaderId) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
