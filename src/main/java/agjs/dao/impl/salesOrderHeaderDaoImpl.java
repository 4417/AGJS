package agjs.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.dao.SalesOrderHeaderDao;

@Repository
public class SalesOrderHeaderDaoImpl implements SalesOrderHeaderDao{
	@PersistenceContext
	private Session session;
	
	@Override
	public SalesOrderHeaderPo selectById(Integer salesOrderHeaderId) {
		if(salesOrderHeaderId!=null) {
			String hql = "from salesOrderHeaderDao where salesOrderHeaderId = :salesOrderHeaderId";
			return session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("salesOrderHeaderId", salesOrderHeaderId).uniqueResult();
		}
		return null;
	}


	@Override
	public List<SalesOrderHeaderPo> getAll() {
		List<SalesOrderHeaderPo> salesOrderHeaderPoList = new ArrayList<SalesOrderHeaderPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SalesOrderHeaderPo> criteriaQuery = criteriaBuilder.createQuery(SalesOrderHeaderPo.class);

			Root<SalesOrderHeaderPo> root = criteriaQuery.from(SalesOrderHeaderPo.class);
			criteriaQuery.select(root);

			Query<SalesOrderHeaderPo> query = session.createQuery(criteriaQuery);
			salesOrderHeaderPoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesOrderHeaderPoList;
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
	public boolean delete(Integer salesOrderHeaderId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<SalesOrderHeaderPo> selectByDate(Date orderStartDate) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SalesOrderHeaderPo> selectByStatus(Integer salesOrderStatusId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SalesOrderHeaderPo> selectByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
