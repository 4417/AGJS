package agjs.dao.impl.room;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomStylePo;
import agjs.dao.room.RoomStyleDao;

@Repository
public class RoomStyleDaoImpl implements RoomStyleDao<RoomStylePo> {

//	@Autowired
//	private SessionFactory sessionFactory;
	@PersistenceContext
	private Session session;

//	public Session getSession() {
//		try {
//			session = sessionFactory.getCurrentSession();
//		} catch (HibernateException e) {
//			session = sessionFactory.openSession();
//		}
//		return session;
//	}

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	// @Transactional
	public List<RoomStylePo> getAll() {
//		Session session;
//
//		try {
//			session = sessionFactory.getCurrentSession();
//		} catch (HibernateException e) {
//			session = sessionFactory.openSession();
//		}
		Query<RoomStylePo> query = session.createQuery("FROM RoomStylePo", RoomStylePo.class);
		List<RoomStylePo> list = query.list();

		return list;
	}

	@Override
	public Integer add(RoomStylePo roomStylePo) {
		session.save(roomStylePo);
		return roomStylePo.getRoomStyleId();
	}

	public RoomStylePo getId(Integer id) {
		RoomStylePo getId = session.get(RoomStylePo.class, id);
		return getId;
	}

}
