package agjs.dao.impl;

import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agjs.bean.RoomInformationFacilitiesPo;
import agjs.dao.RoomInformationFacilitiesDao;

@Repository
public class RoomInformationFacilitiesDaoImpl implements RoomInformationFacilitiesDao<RoomInformationFacilitiesPo> {
	@Autowired
	private SessionFactory sessionFactory;
	@PersistenceContext
	private Session session;

	public Session getSession() {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void add(RoomInformationFacilitiesPo roomInformationFacilitiesPo) {
		getSession().save(roomInformationFacilitiesPo);
	}

}
