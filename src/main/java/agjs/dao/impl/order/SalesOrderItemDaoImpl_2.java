package agjs.dao.impl.order;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.order.SalesOrderItemPo;
import agjs.dao.order.SalesOrderItemDao_2;

@Repository
public class SalesOrderItemDaoImpl_2 implements SalesOrderItemDao_2 {

	@PersistenceContext
	private Session session;

	@Override
	public Serializable insert(SalesOrderItemPo beanPo) {

		System.out.println(beanPo);

		if (beanPo != null) {
			Serializable pk = session.save(beanPo);
			System.out.println(pk);
			System.out.println(Integer.parseInt(pk.toString()));
			return pk;
		}
		return 0;
	}

	@Override
	public int deleteById(SalesOrderItemPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SalesOrderItemPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SalesOrderItemPo select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesOrderItemPo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesOrderItemPo> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
