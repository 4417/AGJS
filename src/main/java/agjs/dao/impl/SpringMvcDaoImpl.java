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

import agjs.bean.RoomUsedRecordPo;
import agjs.dao.SpringMvcDao;

@Repository("SpringMvcDao1")
public class SpringMvcDaoImpl implements SpringMvcDao {

	@PersistenceContext
	private Session session;

	@Override
	public List<RoomUsedRecordPo> selectRoomUsedRecord() {

		List<RoomUsedRecordPo> roomUsedRedordPoList = new ArrayList<RoomUsedRecordPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<RoomUsedRecordPo> criteriaQuery = criteriaBuilder.createQuery(RoomUsedRecordPo.class);

			Root<RoomUsedRecordPo> root = criteriaQuery.from(RoomUsedRecordPo.class);
			criteriaQuery.select(root);

			Query<RoomUsedRecordPo> query = session.createQuery(criteriaQuery);
			roomUsedRedordPoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomUsedRedordPoList;

	}

}
