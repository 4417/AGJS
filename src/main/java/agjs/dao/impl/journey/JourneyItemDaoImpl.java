package agjs.dao.impl.journey;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo;
import agjs.dao.journey.JourneyItemDao;

@Repository
public class JourneyItemDaoImpl implements JourneyItemDao {

	@PersistenceContext
	private Session session;
	@Autowired
	private DataSource dataSource;

	@Override
	public Serializable insert(JourneyItemPo beanPo) {
		System.out.println(beanPo);

		if (beanPo != null) {
			return session.save(beanPo);
		}
		return 0;
	}

	@Override
	public int deleteById(JourneyItemPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(JourneyItemPo beanPo) {
		return 0;
	}

	@Override
	public JourneyItemPo select(Integer id) {

		if (id != null) {
			return session.get(JourneyItemPo.class, id);
		} else {
			return null;
		}
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

	public List<JourneyItemVo> selectBySohIdJDBC(String sohId) {
		final String SQL = "SELECT ji.JOURNEY_ITEM_ID, ji.SALES_ORDER_HEADER_ID, j.JOURNEY_NAME, ji.ADULTS, ji.CHILDREN, ji.JOURNEY_DATE "
				+ "FROM JOURNEY_ITEM ji JOIN JOURNEY j ON ji.JOURNEY_ID = j.JOURNEY_ID "
				+ "WHERE ji.SALES_ORDER_HEADER_ID LIKE ? ";

		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (sohId != null) {
			try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL);) {

				stmt.setString(1, "%" + sohId + "%");
				ResultSet rset = stmt.executeQuery();

				while (rset.next()) {
					JourneyItemVo vo = new JourneyItemVo();
					vo.setJourneyItemId(rset.getInt("JOURNEY_ITEM_ID"));
					vo.setSohId(rset.getInt("SALES_ORDER_HEADER_ID"));
					vo.setJourneyName(rset.getString("JOURNEY_NAME"));
					vo.setAdults(rset.getInt("ADULTS"));
					vo.setChildren(rset.getInt("CHILDREN"));
					vo.setDateString(sdf.format(rset.getDate("JOURNEY_DATE")));
					System.out.println(vo);
					journeyItemVoList.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

		return journeyItemVoList;
	}

	public List<JourneyItemVo> selectByDateRange(java.util.Date startDate, java.util.Date endDate) {

//		SELECT * FROM JOURNEY_ITEM WHERE date BETWEEN 'START' AND 'END';
//		SELECT ji.JOURNEY_ITEM_ID, ji.SALES_ORDER_HEADER_ID, ji.ADULTS, ji.CHILDREN, ji.JOURNEY_DATE , j.JOURNEY_NAME
//		FROM JOURNEY_ITEM ji  JOIN JOURNEY j ON ji.JOURNEY_ID = j.JOURNEY_ID
//		WHERE ji.JOURNEY_DATE BETWEEN '2022-08-05' AND '2022-08-21';

		String hql = "select ji.journeyItemId, ji.sohId, ji.adults, ji.children, ji.journeyDate, j.journeyName  "
				+ "from JourneyItemPo as ji " + "join JourneyPo as j on ji.journeyId = j.journeyId "
				+ "where ji.journeyDate BETWEEN :start AND :end ";

		System.out.println("selectByDateRange");
		Query query = session.createQuery(hql);
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<Object[]> resultList2 = query.list();
		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();

		for (Object[] array : resultList2) {
			System.out.println(
					array[0] + ":" + array[1] + ":" + array[2] + ":" + array[3] + ":" + array[4] + ":" + array[5]);
			JourneyItemVo vo = new JourneyItemVo();
			vo.setJourneyItemId((Integer) array[0]);
			vo.setSohId((Integer) array[1]);
			vo.setAdults((Integer) array[2]);
			vo.setChildren((Integer) array[3]);
			vo.setJourneyDate((java.util.Date) array[4]);
			vo.setJourneyName((String) array[5]);
			journeyItemVoList.add(vo);
		}
//		for(Object[] objects : resultList2) {
//			JourneyItemVo vo =
//		}

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

//		System.out.println("method1()=" + resultList2);

		return journeyItemVoList;
	}

	@Override
	public List<JourneyItemPo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JourneyItemPo> selectByTypeId(Integer[] typeId) {

//		SELECT ji.JOURNEY_ITEM_ID, ji.SALES_ORDER_HEADER_ID, ji.JOURNEY_ID, ji.ADULTS, ji.CHILDREN, ji.JOURNEY_DATE, jt.JOURNEY_TYPE  
//		FROM JOURNEY_ITEM ji 
//		JOIN ( SELECT j.JOURNEY_ID, j.JOURNEY_TYPE_ID, jt.JOURNEY_TYPE 
//			FROM JOURNEY j JOIN JOURNEY_TYPE jt ON j.JOURNEY_TYPE_ID = jt.JOURNEY_TYPE_ID 
//			WHERE j.JOURNEY_TYPE_ID = 10000 ) jt
//		ON ji.JOURNEY_ID = jt.JOURNEY_ID

		System.out.println("selectByTypeId");
		List<JourneyItemPo> journeyItemPoList = new ArrayList<JourneyItemPo>();

		String hql = "select ji.journeyItemId, ji.sohId, ji.adults, ji.children, ji.journeyDate, jt.typeName "
				+ "from JourneyItemPo as ji join ( select j.journeyId, j.typeId, jt.typeName"
				+ "			from JourneyPo as j join JourneyTypePo as jt on j.typeId = jt.typeId "
				+ "			where j.typeId = :typeId ) as jt on ji.journeyId = jt.journeyId ";

		for (Integer id : typeId) {

			if (id != null) {
				Query query = session.createQuery(hql);
				query.setParameter("typeId", id);
				List<Object[]> resultList = query.list();
				for (Object[] array : resultList) {
					System.out.println(array[0] + ":" + array[1] + ":" + array[2] + ":" + array[3] + ":" + array[4]
							+ ":" + array[5]);
				}

//				System.out.println(po);
//				journeyItemPoList.add(po);
			} else {
				return null;
			}
		}

		return null;
	}

	@Override
	public List<JourneyItemVo> selectByTypeIdJDBC(Integer[] typeId) {

		final String SQL_SELECT_JRN_ITEM_BY_TYPEID = ""
				+ "SELECT ji.JOURNEY_ITEM_ID, ji.SALES_ORDER_HEADER_ID, ji.ADULTS, ji.CHILDREN, ji.JOURNEY_DATE, jt.JOURNEY_NAME "
				+ "FROM JOURNEY_ITEM AS ji JOIN ( SELECT j.JOURNEY_ID, j.JOURNEY_TYPE_ID, j.JOURNEY_NAME, jt.JOURNEY_TYPE "
				+ "									FROM JOURNEY j JOIN JOURNEY_TYPE jt "
				+ "									ON j.JOURNEY_TYPE_ID = jt.JOURNEY_TYPE_ID "
				+ "									WHERE j.JOURNEY_TYPE_ID = ? ) AS jt "
				+ "ON ji.JOURNEY_ID = jt.JOURNEY_ID ";
		final String SQL = "SELECT * FROM JOURNEY_ITEM ";

		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (Integer id : typeId) {
			if (id != null) {

				try (Connection conn = dataSource.getConnection();
						PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_JRN_ITEM_BY_TYPEID);) {

					System.out.println("TYPE ID=" + id);
					stmt.setInt(1, id);
					ResultSet rset = stmt.executeQuery();

					while (rset.next()) {
						JourneyItemVo vo = new JourneyItemVo();
						vo.setJourneyItemId(rset.getInt("JOURNEY_ITEM_ID"));
						vo.setSohId(rset.getInt("SALES_ORDER_HEADER_ID"));
						vo.setJourneyName(rset.getString("JOURNEY_NAME"));
						vo.setAdults(rset.getInt("ADULTS"));
						vo.setChildren(rset.getInt("CHILDREN"));
						vo.setDateString(sdf.format(rset.getDate("JOURNEY_DATE")));
						System.out.println(vo);
						journeyItemVoList.add(vo);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		}

		return journeyItemVoList;
	}

	@Override
	public List<JourneyItemPo> selectByJourneyId(Integer journeyId) {

		String hql = "from JourneyItemPo where journeyId = :typeId";
		System.out.println("ty=" + journeyId);

		return session.createQuery(hql, JourneyItemPo.class).setParameter("typeId", journeyId).list();

	}

	@Override
	public List<JourneyItemPo> selectBySohId(String sohId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDate(JourneyItemPo beanPo) {

		System.out.println(beanPo);
		if (beanPo.getJourneyItemId() != null) {
			JourneyItemPo temp = session.get(JourneyItemPo.class, beanPo.getJourneyItemId());
			if (temp != null) {
				temp.setJourneyDate(beanPo.getJourneyDate());
				JourneyItemPo update = (JourneyItemPo) session.merge(temp);
				System.out.println(update);
				return 1;
			}
		}
		return 0;
	}

}
