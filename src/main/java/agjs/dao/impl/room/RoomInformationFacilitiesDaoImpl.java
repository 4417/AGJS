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

	@PersistenceContext
	private Session session;

	@Override
	public void add(RoomInformationFacilitiesPo roomInformationFacilitiesPo) {
		session.save(roomInformationFacilitiesPo);
	}

}
