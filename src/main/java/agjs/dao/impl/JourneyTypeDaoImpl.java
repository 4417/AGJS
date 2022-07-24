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

import agjs.bean.JourneyTypePo;
import agjs.dao.JourneyTypeDao;

@Repository
public class JourneyTypeDaoImpl implements JourneyTypeDao {

	@PersistenceContext
	private Session session;

	@Override
	public int insert(JourneyTypePo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(JourneyTypePo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(JourneyTypePo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JourneyTypePo select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JourneyTypePo> select() {
		
		List<JourneyTypePo> journeyTypePoList = new ArrayList<JourneyTypePo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<JourneyTypePo> criteriaQuery = criteriaBuilder.createQuery(JourneyTypePo.class);

			Root<JourneyTypePo> root = criteriaQuery.from(JourneyTypePo.class);
			criteriaQuery.select(root);

			Query<JourneyTypePo> query = session.createQuery(criteriaQuery);
			journeyTypePoList = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return journeyTypePoList;
	}
}
