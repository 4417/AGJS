package agjs.dao.impl.journey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyPo;
import agjs.dao.journey.JourneyDao;

@Repository
public class JourneyDaoImpl implements JourneyDao {

	@PersistenceContext
	private Session session;

	@Override
	public int insert(JourneyPo beanPo) {

		System.out.println(beanPo);

		if (beanPo != null) {
			Serializable pk = session.save(beanPo);
			System.out.println(pk);
		}
		return -1;
	}

	@Override
	public int deleteById(JourneyPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(JourneyPo beanPo) {

		if (beanPo != null && beanPo.getJourneyId() != null) {
			JourneyPo temp = session.get(JourneyPo.class, beanPo.getJourneyId());
			if (temp != null) {
				JourneyPo update = (JourneyPo) session.merge(beanPo);
				System.out.println(update);
			}
		} else {
			return -1;
		}
		return 0;
	}

	@Override
	public JourneyPo select(Integer id) {

		if (id != null) {
			return session.get(JourneyPo.class, id);
		} else {
			return null;
		}
	}

	@Override
	public List<JourneyPo> select() {

		List<JourneyPo> journeyPoList = new ArrayList<JourneyPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<JourneyPo> criteriaQuery = criteriaBuilder.createQuery(JourneyPo.class);

			Root<JourneyPo> root = criteriaQuery.from(JourneyPo.class);
			criteriaQuery.select(root);

			Query<JourneyPo> query = session.createQuery(criteriaQuery);
			journeyPoList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return journeyPoList;
	}

	@Override
	public List<JourneyPo> selectByTypeId(Integer typeId) {

		String hql = "from JourneyPo where typeId = :typeId";
		System.out.println("ty=" + typeId);

		return session.createQuery(hql, JourneyPo.class).setParameter("typeId", typeId).list();
	}

	@Override
	public List<JourneyPo> selectBykeyword(String keyword) {

		String hql = "from JourneyPo where journeyName like :key";

		return session.createQuery(hql, JourneyPo.class).setParameter("key", "%" + keyword + "%").list();
	}

	@Override
	public boolean deleteByIdBatch(Integer[] idArray) {

		System.out.println("deleteByIdBatch");
		for (Integer id : idArray) {

			if (id != null) {
				JourneyPo temp = session.get(JourneyPo.class, id);
				if (temp != null) {
					session.delete(temp);
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public List<JourneyPo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

}
