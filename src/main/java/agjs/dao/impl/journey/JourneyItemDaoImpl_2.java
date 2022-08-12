package agjs.dao.impl.journey;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyPo;
import agjs.dao.journey.JourneyItemDao_2;

@Repository
public class JourneyItemDaoImpl_2 implements JourneyItemDao_2{

	@PersistenceContext
	private Session session;

	@Override
	public List<JourneyItemPo> selectBySohId(String sohId) {
		String hql = "from JourneyItemPo where SALES_ORDER_HEADER_ID = :sohId";
		return session.createQuery(hql, JourneyItemPo.class).setParameter("sohId", sohId).getResultList();
	}
	
	@Override
	public JourneyPo selectByJourneyId(Integer id) {
		String hql = "from JourneyPo where JOURNEY_ID = :id";
		return session.createQuery(hql, JourneyPo.class).setParameter("id", id).getSingleResult();
	}
}
