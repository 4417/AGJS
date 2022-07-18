package agjs.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.RoomUsedRecordPo;
import agjs.common.util.HibernateUtil;
import agjs.model.SpringMvcDao;

@Repository("SpringMvcDao1")
public class SpringMvcDaoImpl implements SpringMvcDao {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	Transaction transaction = session.beginTransaction();

//	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	public SpringMvcDaoImpl() {
		System.out.println("SpringMvcDao init");
	}

	@Override
	public List<RoomUsedRecordPo> selectRoomUsedRecord() {

//		RoomUsedRecordPo roomUsedRedordPo = session.get(RoomUsedRecordPo.class, 4000);
//		System.out.println("select=" + roomUsedRedordPo);
		List<RoomUsedRecordPo> roomUsedRedordPoList = new ArrayList<RoomUsedRecordPo>();	
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<RoomUsedRecordPo> criteriaQuery = criteriaBuilder.createQuery(RoomUsedRecordPo.class);
			
			Root<RoomUsedRecordPo> root = criteriaQuery.from(RoomUsedRecordPo.class);
			criteriaQuery.select(root);
			
			Query<RoomUsedRecordPo> query = session.createQuery(criteriaQuery);
			roomUsedRedordPoList = query.getResultList();
			
			System.out.println("dao end");
			transaction.commit();
			session.close();
			HibernateUtil.closeSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

//		for (RoomUsedRecordPo dataPo : roomUsedRedordPoList) {
//			try {
//				java.util.Date date = sFormat.parse(dataPo.getStartDate().toString());
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			System.out.println(dataPo);
//		}


		return roomUsedRedordPoList;

	}

}
