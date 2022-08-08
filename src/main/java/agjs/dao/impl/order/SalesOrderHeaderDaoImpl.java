package agjs.dao.impl.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.room.RoomStylePo;
import agjs.bean.user.UserPo;
import agjs.dao.order.SalesOrderHeaderDao;

@Repository
public class SalesOrderHeaderDaoImpl implements SalesOrderHeaderDao{
	@PersistenceContext
	private Session session;
	
	@Override
	public SalesOrderHeaderPo selectById(Integer salesOrderHeaderId) {
		System.out.println("The id I get:" + salesOrderHeaderId);
		
		return session.get(SalesOrderHeaderPo.class, salesOrderHeaderId);
//		if(salesOrderHeaderId!=null) {
//			String hql = "from salesOrderHeaderPo where salesOrderHeaderId = :salesOrderHeaderId";
//			return session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("salesOrderHeaderId", salesOrderHeaderId).uniqueResult();
//		}
//		return null;
	}


	@Override
	public List<SalesOrderHeaderPo> getAll() {
		List<SalesOrderHeaderPo> allList = new ArrayList<SalesOrderHeaderPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SalesOrderHeaderPo> criteriaQuery = criteriaBuilder.createQuery(SalesOrderHeaderPo.class);

			Root<SalesOrderHeaderPo> root = criteriaQuery.from(SalesOrderHeaderPo.class);
			criteriaQuery.select(root);

			Query<SalesOrderHeaderPo> query = session.createQuery(criteriaQuery);
			allList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allList;
	}


	@Override
	public SalesOrderHeaderPo insert(SalesOrderHeaderPo bean) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean update(SalesOrderHeaderPo bean) {
		
//		Integer userId, Date createDate, Date orderStartDate, Date orderEndDate,
//		Date orderChangeDate, Integer salesOrderStatusId, String orderRemark, Integer roomPrice,
//		Integer journeyPrice, Integer salesOrderHeaderId
		
		if(bean != null && bean.getSalesOrderHeaderId() != null) {
			SalesOrderHeaderPo temp = session.get(SalesOrderHeaderPo.class, bean.getSalesOrderHeaderId());
			if(temp != null) {
				SalesOrderHeaderPo update = (SalesOrderHeaderPo) session.merge(bean);
				System.out.println(update);
				return true;
			}
		}
		return false;
	}
	

	@Override
	public boolean delete(Integer id) {
		String hql = "delete from salesOrderHeaderPo where salesOrderHeaderId= :salesOrderHeaderId";
		  Query query = session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("salesOrderHeaderId", id);
		  System.out.println(query.executeUpdate());
		return true;
	}

//需要一個日期區間的搜尋嗎?

//訂單開始日期的搜尋
	//hql = "from TeacherAward where awardState = 2  and  awardTime >= ? and awardTime <= ?  ";
	//Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	//query.setDate(0, startTime);
	//query.setDate(1,stopTime );
	
	
	@Override
	public List<SalesOrderHeaderPo> selectByStartDate(String date) {
		String hql = "from SalesOrderHeaderPo where orderStartDate= :orderStartDate";
		List<SalesOrderHeaderPo> list = session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("orderStartDate", date).list();
		
		System.out.println("The date I get(DAOimpl):" + list);
		return list;
		
		
		//Select * from SalesOrderHeaderPo where orderStartDate = :orderStartDate order by SALES_ORDER_HEADER_ID desc;
		
//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		CriteriaQuery<SalesOrderHeaderPo> criteriaQuery = criteriaBuilder.createQuery(SalesOrderHeaderPo.class);
//		
//		Root<SalesOrderHeaderPo> root = criteriaQuery.from(SalesOrderHeaderPo.class);
//		
//		
//		Predicate p1 = criteriaBuilder.equal(root.get("orderStartDate"), date);
//		
//		criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("salesOrderHeaderId")));
//
//		TypedQuery<SalesOrderHeaderPo> typedQuery = session.createQuery(criteriaQuery);
//		List<SalesOrderHeaderPo> list = typedQuery.getResultList();
//		
//		System.out.println("The date I get(DAOimpl):" + list);
//		return list;
	}


	@Override
	public List<SalesOrderHeaderPo> selectByStatus(Integer salesOrderStatusId) {
		if(salesOrderStatusId!=null) {
			String hql = "from salesOrderHeader where salesOrderStatusId = :salesOrderStatusId";
			return session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("salesOrderStatusId", salesOrderStatusId).list();
		}
		return null;
	}


	@Override
	public List<SalesOrderHeaderPo> selectByUserId(Integer userId) {
		try {
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SalesOrderHeaderPo> criteriaQuery = criteriaBuilder.createQuery(SalesOrderHeaderPo.class);
			
			//from SalesOrderHeaderPo
			Root<SalesOrderHeaderPo> root= criteriaQuery.from(SalesOrderHeaderPo.class);
			
			//userId= ?
			Predicate p1 = criteriaBuilder.equal(root.get("userId"), userId);
			
			//where userId= "3";
			criteriaQuery = criteriaQuery.where(p1);
			
			List<SalesOrderHeaderPo> list = session.createQuery(criteriaQuery).getResultList();
			
			System.out.println("p1:" + p1);
			System.out.println("result:" + list);
			return list;
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}



	

}
