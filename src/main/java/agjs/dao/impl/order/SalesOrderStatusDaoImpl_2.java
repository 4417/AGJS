package agjs.dao.impl.order;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.order.SalesOrderStatusPo;
import agjs.dao.order.SalesOrderStatusDao_2;

@Repository
public class SalesOrderStatusDaoImpl_2 implements SalesOrderStatusDao_2 {
	@PersistenceContext
	private Session session;

	//select * from SalesOrderStatus where salesOrderStatusId = ?
	@Override
	public SalesOrderStatusPo selectById(Integer id) {
		String hql="from SalesOrderStatusPo where salesOrderStatusId = :salesOrderStatusId";
		return session.createQuery(hql, SalesOrderStatusPo.class)
				.setParameter("salesOrderStatusId", id).uniqueResult();
	}
}
