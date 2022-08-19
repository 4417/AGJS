package agjs.dao.impl.order;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.order.EcpayOrderPo;
import agjs.dao.order.EcpayOrderDao;

@Repository
public class EcpayOrderDaoImpl implements EcpayOrderDao {

	@PersistenceContext
	private Session session;

	@Override
	public Serializable insert(EcpayOrderPo beanPo) {

		System.out.println(beanPo);
		if (beanPo != null) {
			Serializable pk = session.save(beanPo);
			return pk;
		}
		return 0;
	}

	@Override
	public int deleteById(EcpayOrderPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(EcpayOrderPo beanPo) {

		if (beanPo != null && beanPo.getEcpayId() != null) {
			EcpayOrderPo temp = session.get(EcpayOrderPo.class, beanPo.getEcpayId());
			if (temp != null) {
				EcpayOrderPo update = (EcpayOrderPo) session.merge(beanPo);
				System.out.println(update);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<EcpayOrderPo> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EcpayOrderPo select(String id) {
		if (id != null) {
			return session.get(EcpayOrderPo.class, id);
		} else {
			return null;
		}
	}

	@Override
	public List<EcpayOrderPo> select(String[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

}
