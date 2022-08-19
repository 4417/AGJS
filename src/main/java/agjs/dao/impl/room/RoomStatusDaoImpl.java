package agjs.dao.impl.room;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.dao.room.RoomStatusDao;

@Repository
public class RoomStatusDaoImpl implements RoomStatusDao {

	@PersistenceContext
	private Session session;

	@Override
	public Serializable insert(Object beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Object beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object select(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List select(Object[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List select() {
		// TODO Auto-generated method stub
		return null;
	}

	// 日期範圍找空房的房型ID
	@Override
	public List<Integer> selectEmptyRoomByDateRange(Date startDate, Date endDate) {


		String hql = "SELECT roomStyleId FROM RoomPo as r WHERE r.roomId NOT IN ( SELECT roomId FROM RoomUsedRecordPo "
				+ "WHERE ( startDate < :end  ) AND ( endDate > :start ) )";

		System.out.println("日期範圍找空房ID");
		Query query = session.createQuery(hql, Integer.class);
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<Integer> resultList = query.list();

		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList;
		}
	}
}
