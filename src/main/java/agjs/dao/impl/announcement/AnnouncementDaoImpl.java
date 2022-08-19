package agjs.dao.impl.announcement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.announcement.AnnouncementCountVo;
import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;
import agjs.dao.announcement.AnnouncementDao;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {

	@PersistenceContext
	private Session session;

	@Override
	public List<AnnouncementPo> searchKeyword(String keyword) {
		String hql = "from AnnouncementPo where anmTitle like :keyword or anmContent like :keyword";
		return session.createQuery(hql, AnnouncementPo.class).setParameter("keyword", "%" + keyword + "%").list();
	}

	@Override
	public AnnouncementPo insertAnm(AnnouncementPo announcementPo) {
		if (announcementPo != null) {
			Serializable pk = session.save(announcementPo);
			System.out.println(pk);
		}
		return announcementPo;
	}

	@Override
	public AnnouncementPo updateAnm(AnnouncementPo announcementPo) {
		if(announcementPo != null && announcementPo.getAnmId() != null) {
			AnnouncementPo temp = session.get(AnnouncementPo.class, announcementPo.getAnmId());
			if(temp != null) {
				announcementPo.setAdministratorId(1);
				return (AnnouncementPo) session.merge(announcementPo);
			}
		}
		return null;
	}

	@Override
	public List<AnnouncementPo> delete(AnnouncementPo announcementPo) {
		if(announcementPo.getAnmId() != null) {
			AnnouncementPo temp = session.get(AnnouncementPo.class, announcementPo.getAnmId());
			session.delete(temp);
		}
		return null;
	}

	@Override
	public List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo) {
		String hql = "from AnnouncementPo where anmTitle = :title and anmTypeId = :type and anmStartDate = :startDate";
		Query<AnnouncementPo> query = session.createQuery(hql, AnnouncementPo.class);
		query = query.setParameter("title", announcementPo.getAnmTitle());
		query = query.setParameter("type", announcementPo.getAnmTypeId());
		query = query.setParameter("startDate", announcementPo.getAnmStartDate());
		
		List<AnnouncementPo> anmPoList = query.list();
		
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> allAnm() {
		return session.createQuery("from AnnouncementPo order by anmId desc", AnnouncementPo.class).list();
	}

	@Override
	public List<AnnouncementPo> filter(AnnouncementFilterVo announcementFilterVo) {
		String hql = "from AnnouncementPo where 1=1 ";
		if (announcementFilterVo.getAnmStartDate() != null) {
			if(announcementFilterVo.getKeyword().equals("-1")) {
				hql += "and anmStartDate = :startDate ";
			}else {
				hql += "and anmStartDate >= :startDate and anmStartDate <= current_date() ";
			}
		}
		
		if (announcementFilterVo.getAnmStatus() != null) {
			hql += "and anmStatus in (:anmStatus) ";
		}
		if (announcementFilterVo.getAnmTypeId() != null) {
			hql += "and anmTypeId in :type ";
		}

		Query<AnnouncementPo> query = session.createQuery(hql, AnnouncementPo.class);
		
		if (announcementFilterVo.getAnmStartDate() != null) {
			query = query.setParameter("startDate", announcementFilterVo.getAnmStartDate());
		}
		if (announcementFilterVo.getAnmStatus() != null) {
			List<String> anmStatusList = new ArrayList<>();
			for (String anmStatus : announcementFilterVo.getAnmStatus()) {
				anmStatusList.add(anmStatus);
			}
			query = query.setParameter("anmStatus", anmStatusList);
		}
		if (announcementFilterVo.getAnmTypeId() != null) {
			List<Integer> anmTypeIdList = new ArrayList<>();
			for (Integer anmTypeId : announcementFilterVo.getAnmTypeId()) {
				anmTypeIdList.add(anmTypeId);
			}
			query = query.setParameter("type", anmTypeIdList);
		}
		List<AnnouncementPo> anmPoList = query.list();
		
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> publishedAnm(AnnouncementCountVo announcementCountVo) {
		String hql = "from AnnouncementPo where anmStatus = '已上架' order by anmOrderId asc, anmStartDate desc, anmId desc";
		Query<AnnouncementPo> query = session.createQuery(hql, AnnouncementPo.class);
		query.setFirstResult(announcementCountVo.getCount() * 10);			
		query.setMaxResults(10);
		List<AnnouncementPo> anmPoList = query.list();
		
		return anmPoList;
	}

}
