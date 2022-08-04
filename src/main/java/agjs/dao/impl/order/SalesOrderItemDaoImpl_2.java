package agjs.dao.impl.order;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.order.SalesOrderItemPo;

@Repository
public class SalesOrderItemDaoImpl_2 {
	@PersistenceContext
	private Session session;
	
	
}
