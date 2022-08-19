package agjs.dao.impl.announcement;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.announcement.AnnouncementTypePo;
import agjs.dao.announcement.AnnouncementTypeDao;

@Repository
public class AnnouncementTypeDaoImpl implements AnnouncementTypeDao {
	
	@PersistenceContext
	private Session session;

	@Override
	public int getAnmTypeId(String typeName) {
		AnnouncementTypePo anmTypePo = new AnnouncementTypePo();
		anmTypePo = session.createQuery("from AnnouncementTypePo where anmType = :typeName", AnnouncementTypePo.class).setParameter("typeName", typeName).uniqueResult();
		return anmTypePo.getAnmTypeId();
	}

	@Override
	public String getAnmType(int typeId) {
		AnnouncementTypePo anmTypePo = new AnnouncementTypePo();
		anmTypePo = session.createQuery("from AnnouncementTypePo where anmTypeId = :typeId", AnnouncementTypePo.class).setParameter("typeId", typeId).uniqueResult();
		return anmTypePo.getAnmType();
	}

}
