package agjs.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.SalesOrderStatusPo;
import agjs.dao.SalesOrderStatusDao;

@Repository
public class SalesOrderStatusDaoImpl implements SalesOrderStatusDao{
	@PersistenceContext
	private Session session;
	
	@Override
	public List<SalesOrderStatusPo> select() {
		List<SalesOrderStatusPo> salesOrderStatusPoList = new ArrayList<SalesOrderStatusPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SalesOrderStatusPo> criteriaQuery = criteriaBuilder.createQuery(SalesOrderStatusPo.class);

			Root<SalesOrderStatusPo> root = criteriaQuery.from(SalesOrderStatusPo.class);
			criteriaQuery.select(root);

			Query<SalesOrderStatusPo> query = session.createQuery(criteriaQuery);
			salesOrderStatusPoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesOrderStatusPoList;
	}
	
	
	@Override
	public SalesOrderStatusPo select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public SalesOrderStatusPo insert(SalesOrderStatusPo salesOrderStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SalesOrderStatusPo update(SalesOrderStatusPo salesOrderStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SalesOrderStatusPo delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
