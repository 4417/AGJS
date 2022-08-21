
package agjs.dao.impl.order;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.dao.order.SalesOrderHeaderDao_2;

@Repository
public class SalesOrderHeaderDaoImpl_2 implements SalesOrderHeaderDao_2 {

	@PersistenceContext
	private Session session;

	@Override
	public Serializable insert(SalesOrderHeaderPo beanPo) {

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
	public int deleteById(SalesOrderHeaderPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SalesOrderHeaderPo beanPo) {
		System.out.println(beanPo);
		if (beanPo.getSalesOrderHeaderId() != null) {
			SalesOrderHeaderPo temp = session.get(SalesOrderHeaderPo.class, beanPo.getSalesOrderHeaderId());
			if (temp != null) {
				temp.setSalesOrderHeaderId(beanPo.getSalesOrderHeaderId());
				SalesOrderHeaderPo update = (SalesOrderHeaderPo) session.merge(temp);
				System.out.println("update" + update);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public SalesOrderHeaderPo select(Integer id) {
		if (id != null) {
			return session.get(SalesOrderHeaderPo.class, id);
		}
		return null;
	}

	@Override
	public List<SalesOrderHeaderPo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesOrderHeaderPo> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
