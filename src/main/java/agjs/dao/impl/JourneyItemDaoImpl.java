package agjs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemPo;
import agjs.dao.JourneyItemDao;

@Repository
public class JourneyItemDaoImpl implements JourneyItemDao {

	@PersistenceContext
	private Session session;

	@Override
	public int insert(JourneyItemPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(JourneyItemPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(JourneyItemPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JourneyItemPo select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JourneyItemPo> select() {

		List<JourneyItemPo> journeyItemPoList = new ArrayList<JourneyItemPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<JourneyItemPo> criteriaQuery = criteriaBuilder.createQuery(JourneyItemPo.class);

			Root<JourneyItemPo> root = criteriaQuery.from(JourneyItemPo.class);
			criteriaQuery.select(root);

			Query<JourneyItemPo> query = session.createQuery(criteriaQuery);
			journeyItemPoList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return journeyItemPoList;
	}

	public List<JourneyItemPo> selectBySohId(String sohId) {
//		SELECT JOURNEY_ITEM_ID, SALES_ORDER_HEADER_ID, JOURNEY_NAME, ADULTS, CHILDREN, JOURNEY_DATE 
//		FROM JOURNEY_ITEM ji, JOURNEY j 
//		WHERE SALES_ORDER_HEADER_ID = 1000 
//		AND
//		ji.JOURNEY_ID = j.JOURNEY_ID;

//		String hql = "SELECT JOURNEY_ITEM_ID, SALES_ORDER_HEADER_ID, JOURNEY_NAME, ADULTS, CHILDREN, JOURNEY_DATE "
//				+ "FROM JOURNEY_ITEM ji , JOURNEY j  "
//				+ "WHERE SALES_ORDER_HEADER_ID = :id AND ji.JOURNEY_ID = j.JOURNEY_ID";
//		System.out.println("selectBySohIds");
//		List resultList = session.createQuery(hql).setParameter("id", sohId).list();
//		
//		JourneyItemVo vo = (JourneyItemVo) resultList;
//		System.out.println(vo.toString());

//		SELECT * FROM JOURNEY_ITEM where SALES_ORDER_HEADER_ID = '%?a%' order by SALES_ORDER_HEADER_ID desc;

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<JourneyItemPo> criteriaQuery = criteriaBuilder.createQuery(JourneyItemPo.class);

		// FROM product
		Root<JourneyItemPo> root = criteriaQuery.from(JourneyItemPo.class);

		// name like '%?%'
		Predicate p1 = criteriaBuilder.like(root.get("sohId"), "%" + sohId + "%");

		// where SALES_ORDER_HEADER_ID like '%?%'
		criteriaQuery = criteriaQuery.where(p1);

		// order by SALES_ORDER_HEADER_ID desc
		criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("sohId")));

		TypedQuery<JourneyItemPo> typedQuery = session.createQuery(criteriaQuery);
		List<JourneyItemPo> resultList = typedQuery.getResultList();

		System.out.println("method1()=" + resultList);

		return resultList;
	}

	public List<JourneyItemPo> selectByDateRange(String startDate, String endDate) {

//		SELECT * FROM JOURNEY_ITEM WHERE date BETWEEN 'START' AND 'END';
		String hql = "from JourneyItemPo ji , JourneyPo j  "
				+ "where ji.journeyDate BETWEEN :start AND :end AND ji.journeyId = j.journeyId";

		System.out.println("selectByDateRange");
		Query query = session.createQuery(hql, JourneyItemPo.class);
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<JourneyItemPo> resultList2 = query.list();

//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		CriteriaQuery<JourneyItemPo> criteriaQuery = criteriaBuilder.createQuery(JourneyItemPo.class);

		// FROM JOURNEY_ITEM
//		Root<JourneyItemPo> root = criteriaQuery.from(JourneyItemPo.class);

		// JOURNEY_DATE BETWEEN start AND end
//		Predicate p1 = criteriaBuilder.between(root.get("journeyDate"), startDate, endDate);

		// where JOURNEY_DATE BETWEEN start AND end
//		criteriaQuery = criteriaQuery.where(p1);

		// order by SALES_ORDER_HEADER_ID desc
//		criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("sohId")));

//		TypedQuery<JourneyItemPo> typedQuery = session.createQuery(criteriaQuery);
//		List<JourneyItemPo> resultList = typedQuery.getResultList();

		System.out.println("method1()=" + resultList2);

		return resultList2;
	}

}
