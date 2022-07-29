package agjs.dao.impl.room;

import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomInformationFacilitiesPo;
import agjs.dao.room.RoomInformationFacilitiesDao;

@Repository
public class RoomInformationFacilitiesDaoImpl implements RoomInformationFacilitiesDao {
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

	@Override
	public void add(RoomInformationFacilitiesPo roomInformationFacilitiesPo) {
		session.save(roomInformationFacilitiesPo);
	}

}
